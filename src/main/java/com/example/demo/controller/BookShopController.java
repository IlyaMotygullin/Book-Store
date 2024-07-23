package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.service.book_service.BookService;
import com.example.demo.service.user_service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookShopController {
    final UserService userService;
    final BookService bookService;

    public BookShopController
            (
                    @Qualifier(value = "userService") UserService userService,
                    @Qualifier(value = "bookService") BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }


    /*
    регистрация пользователя
     */
    @GetMapping("/register_user")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register_user";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/book_list/" + user.getId();
    }

    /*
    вход пользователя
     */
    @GetMapping("/entrance_user")
    public String entranceUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "entrance_user";
    }

    @PostMapping("/entrance")
    public String checkUser(@ModelAttribute("user") User user) {
        /*
        с помощью @ModelAttribute получаем пользователя из формы с конструктором из двух полей: email, password.
        с помощью userService получаем пользователя из @ModelAttribute по паролю и email
        если выполняется условие, то этот пользователь admin и перенаправляем его на соотвествующую страницу
        если условие не выполняется, то пользователь поподает на страницу с катологом книг
         */
        User getUser = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        Logger.getLogger(BookShopController.class.getName()).info("Пользователь: " + getUser);
        if (getUser.getEmail().equals("admin@yandex.ru") && getUser.getPassword().equals("11122")) {
            return "redirect:/admin";
        }
        return "redirect:/book_list/" + getUser.getId();
    }

    /*
    список книг
     */
    @GetMapping("/book_list/{idUser}")
    public String showBookList(Model model, @PathVariable long idUser) {
        List<Book> bookList = bookService.getBookList();
        model.addAttribute("books", bookList);
        model.addAttribute("user", userService.getUserById(idUser));
        return "book_list";
    }
}
