package com.example.demo.service.user_service;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUserById(long idUser);
    User getUserByEmailAndPassword(String email, String password);
    List<User> getUserList();
    void deleteUser(User user);
}
