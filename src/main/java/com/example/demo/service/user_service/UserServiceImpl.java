package com.example.demo.service.user_service;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.user_repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service(value = "userService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUserById(long idUser) {
        return repository.findById(idUser).orElse(null);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return repository.findUserByEmailAndPassword(email, password);
    }
}
