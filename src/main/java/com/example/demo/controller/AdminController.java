package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "adminController")
public class AdminController {

    // TODO: реализовать функционал для admin
    // TODO: реализовать просмотр всех заказов
    // TODO: реализовать просмотр всех книг

    /*
    страница для администратора
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


}
