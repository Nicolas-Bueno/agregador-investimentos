package tech.nb.agregadorinvestimentos.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.nb.agregadorinvestimentos.dto.CreateUserDto;
import tech.nb.agregadorinvestimentos.entity.User;
import tech.nb.agregadorinvestimentos.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UUID createUser(CreateUserDto createUserDto){

        // DTO -> Entity
        var entity = new User(
            UUID.randomUUID(),
            createUserDto.username(),
            createUserDto.email(),
            createUserDto.password(),
            Instant.now(),
            null);

        var userSaved = repository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){

        return repository.findById(UUID.fromString(userId));

    }

    public List<User> getAllUser(){
        return repository.findAll();
    }
}
