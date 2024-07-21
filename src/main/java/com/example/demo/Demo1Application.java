package com.example.demo;

import com.example.demo.service.book_service.BookService;
import com.example.demo.service.user_service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Demo1Application {

    /*
     todo: admin может добавлять книги, удалять книги,
     todo: посмотреть всех пользователей, изменять книги,
     todo: посмотреть все книги
     */

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}
