package tech.nb.agregadorinvestimentos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.nb.agregadorinvestimentos.dto.AccountResponseDto;
import tech.nb.agregadorinvestimentos.dto.CreateAccountDto;
import tech.nb.agregadorinvestimentos.dto.CreateUserDto;
import tech.nb.agregadorinvestimentos.dto.UpdateUserDTO;
import tech.nb.agregadorinvestimentos.entity.User;
import tech.nb.agregadorinvestimentos.service.UserService;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<FailureResponse> createUser(@RequestBody CreateUserDto createUserDto) {   
        var failures = service.validatePass(createUserDto.password());

        if (failures.isEmpty()) {
            var userId = service.createUser(createUserDto);
            return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
        }

        return ResponseEntity.badRequest().body(new FailureResponse(failures));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = service.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        var users = service.getAllUser();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FailureResponse> updateUserById(@PathVariable("userId") String userId,
                                                @RequestBody UpdateUserDTO updateUserDTO){

        var failures = service.validatePass(updateUserDTO.password());
        if (failures.isEmpty()) {
            service.updateUserById(userId, updateUserDTO);
            return ResponseEntity.noContent().build(); 
        }
        
        return ResponseEntity.badRequest().body(new FailureResponse(failures));                
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
        service.deleteById(userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> semnome(@PathVariable("userId") String userId,
                                @RequestBody CreateAccountDto createAccountDto) {

        service.createAccount(userId, createAccountDto);  

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> getUserAccountsById(@PathVariable("userId") String userId) {
        
        var accounts = service.listAccounts(userId);

        return ResponseEntity.ok(accounts);
    }
    
}
