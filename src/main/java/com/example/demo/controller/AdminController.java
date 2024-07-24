package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.user_service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller(value = "adminController")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    // TODO: реализовать функционал для admin
    // TODO: реализовать просмотр всех заказов, удаление книг
    // TODO: реализовать просмотр всех книг, добавление книг, удаление книг

    final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /*
    страница для администратора
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    /*
    просмотр всех пользователей
     */
    @GetMapping("/check_users")
    public String showUsers(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "admin/user_list";
    }

    /*
    удаление пользователя
     */
    @GetMapping("/delete_user/{idUser}")
    public String deleteUser(@PathVariable long idUser) {
        User getUser = userService.getUserById(idUser);
        getUser.getOrders().forEach(e->e.setUserSet(null));
        getUser.getBookSet().forEach(e->e.setUserSet(null));
        userService.deleteUser(getUser);
        return "redirect:/check_users";
    }

}
