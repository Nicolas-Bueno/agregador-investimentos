package tech.nb.agregadorinvestimentos.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech.nb.agregadorinvestimentos.dto.CreateAccountDto;
import tech.nb.agregadorinvestimentos.dto.CreateUserDto;
import tech.nb.agregadorinvestimentos.dto.UpdateUserDTO;
import tech.nb.agregadorinvestimentos.entity.Account;
import tech.nb.agregadorinvestimentos.entity.BillingAddress;
import tech.nb.agregadorinvestimentos.entity.User;
import tech.nb.agregadorinvestimentos.repository.AccountRepository;
import tech.nb.agregadorinvestimentos.repository.BillingAddressRepository;
import tech.nb.agregadorinvestimentos.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    public UUID createUser(CreateUserDto createUserDto){

        // DTO -> Entity
        var entity = new User(
            UUID.randomUUID(),
            createUserDto.username(),
            createUserDto.email(),
            createUserDto.password(),
            Instant.now(),
            null,null);

        var userSaved = repository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){

        return repository.findById(UUID.fromString(userId));

    }

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDTO updateUserDTO){
        var id = UUID.fromString(userId);

        var userEntity = repository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }

            if (updateUserDTO.password()!= null) {
                user.setPassword(updateUserDTO.password());
            }

            repository.save(user);
        }
    }

    public void deleteById(String userId){
        var id = UUID.fromString(userId);

        var userExists = repository.existsById(id);

        if (userExists) {
            repository.deleteById(id);
        }
    }

    public List<String> validatePass(String password){
        List<String> failures = new ArrayList<>();

        validateLength(password, failures);
        validateUppercase(password,failures);
        validateLowerCase(password,failures);
        validateNumber(password,failures);
        validateSpecialCharacter(password,failures);

        return failures;
        
    }

    private void validateLength(String password, List<String> failures){
        if (password != null && password.length() < 8) {
            failures.add("A senha deve possuir pelo menos 8 caracteres.");
        }
    }

    private void validateUppercase(String password, List<String> failures){
        if (!Pattern.matches(".*[A-Z].*", password)) {
            failures.add("A senha deve possuir pelo menos uma letra maiúscula.");
        }
    }

    private void validateLowerCase(String password, List<String> failures){
        if (!Pattern.matches(".*[a-z].*", password)) {
            failures.add("A senha deve possuir pelo menos uma letra minuscula.");
        }
    }

    private void validateNumber(String password, List<String> failures){
        if (!Pattern.matches(".*[0-9].*", password)) {
            failures.add("A senha deve possuir pelo menos um numero");
        }
    }

    private void validateSpecialCharacter(String password, List<String> failures){
        if (!Pattern.matches(".*[\\W].*", password)) {
            failures.add("A senha deve possuir pelo menos um caracter especial");
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
       
        var user = repository.findById(UUID.fromString(userId))
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        var account =  new Account(
            UUID.randomUUID(),
            createAccountDto.description(),
            user,
            null,
            new ArrayList<>()
            );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(accountCreated.getAccountId(),
            account,
            createAccountDto.street(),
            createAccountDto.number()
            );

        billingAddressRepository.save(billingAddress);
    }
}
