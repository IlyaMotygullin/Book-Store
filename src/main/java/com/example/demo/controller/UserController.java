package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.admin_service.AdminService;
import com.example.demo.service.book_service.BookService;
import com.example.demo.service.order_service.OrderService;
import com.example.demo.service.user_service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

@Controller(value = "userController")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;
    final BookService bookService;
    final OrderService orderService;
    final AdminService adminService;

    public UserController(UserService userService, BookService bookService, OrderService orderService, AdminService adminService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.adminService = adminService;
    }
    /*
    добавление книг и перенаправление пользователя на страницу с книгами
     */
    @GetMapping("/add_book/{idBook}/{idUser}")
    public String addBook(@PathVariable long idBook, @PathVariable long idUser) {
        Logger.getLogger(BookShopController.class.getName()).info("Книга: " + bookService.getBookById(idBook));
        Logger.getLogger(BookShopController.class.getName()).info("Пользователь: " + userService.getUserById(idUser));

        User getUser = userService.getUserById(idUser);
        Book getBook = bookService.getBookById(idBook);

        getBook.getUserSet().add(getUser);
        getUser.getBookSet().add(getBook);

        userService.saveUser(getUser);
        bookService.saveBook(getBook);
        return "redirect:/book_list/" + userService.getUserById(idUser).getId();
    }

    /*
    отображение всех книг пользователя
     */
    @GetMapping("/personal_account/{idUser}")
    public String personalAccountUsers(@PathVariable long idUser, Model model) {
        Set<Book> bookList = userService.getUserById(idUser).getBookSet();
        model.addAttribute("books", bookList);
        model.addAttribute("user", userService.getUserById(idUser));
        return "personal_account";
    }

    /*
    создание даты заказа
     */
    private Date createDateOrder() {return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());}

    /*
    Заказ книг
     */
    @GetMapping("/create_order/{idUser}")
    public String createOrder(@PathVariable long idUser) {
        User getUser = userService.getUserById(idUser);
        Admin admin = adminService.getAdminById(1);
        Logger.getLogger(UserController.class.getName()).info("User: " + getUser);

        /*
        создание заказа
         */
        Order order = new Order(createDateOrder());
        orderService.createOrder(order);

        order.getUserSet().add(getUser);
        getUser.getOrders().add(order);

        /*
        добавление заказа для админа
         */
        order.setAdmin(admin);
        admin.getOrderList().add(order);

        adminService.createAdmin(admin);
        userService.saveUser(getUser);
        orderService.createOrder(order);

        return "redirect:/personal_account/" + getUser.getId();
    }

    /*
    удаление книги
     */
    @GetMapping("/delete_book/{idBook}/{idUser}")
    public String deleteBook(@PathVariable long idBook, @PathVariable long idUser) {
        User getUser = userService.getUserById(idUser);
        Book getBook = bookService.getBookById(idBook);

        Logger.getLogger(UserController.class.getName()).info("Пользователь: " + getUser);
        Logger.getLogger(UserController.class.getName()).info("Книга: " + getBook);

        getUser.getBookSet().remove(getBook);
        getBook.getUserSet().remove(getUser);

        userService.saveUser(getUser);
        bookService.saveBook(getBook);

        return "redirect:/personal_account/" + getUser.getId();
    }
}
