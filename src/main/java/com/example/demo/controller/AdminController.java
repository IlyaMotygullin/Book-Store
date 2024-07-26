package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.book_service.BookService;
import com.example.demo.service.order_service.OrderService;
import com.example.demo.service.user_service.UserService;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller(value = "adminController")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    // TODO: реализовать функционал для admin
    // TODO: реализовать просмотр всех книг, добавление книг, удаление книг

    final UserService userService;
    final OrderService orderService;
    final BookService bookService;

    public AdminController(UserService userService, OrderService orderService, BookService bookService) {
        this.userService = userService;
        this.orderService = orderService;
        this.bookService = bookService;
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
        getUser.getOrders().forEach(e -> e.setUserSet(null));
        getUser.getBookSet().forEach(e->e.setUserSet(null));
        userService.deleteUser(getUser);
        return "redirect:/check_users";
    }


    /*
    показ списка заказов
     */
    @GetMapping("/check_orders")
    public String showOrders(Model model) {
        List<Order> orderList = orderService.getOrderList();
        model.addAttribute("orders", orderList);
        return "admin/order_list";
    }

    /*
    удаление заказа
     */
    @GetMapping("/delete_order/{idOrder}")
    public String deleteOrder(@PathVariable long idOrder) {
        Order getOrder = orderService.getOrderById(idOrder);
        getOrder.getUserSet().forEach(e -> e.setOrders(null));
        orderService.deleteOrder(getOrder);
        return "redirect:/check_orders";
    }

    /*
    подробная информация о заказе
     */
    @GetMapping("/info_order/{idOrder}")
    public String infoOrder(@PathVariable long idOrder, Model model) {
        Set<User> userSet = orderService.getOrderById(idOrder).getUserSet();
        model.addAttribute("users", userSet);
        return "admin/order-info";
    }

    @GetMapping("/exit")
    public String exit() {
        return "redirect:/check_orders";
    }

    /*
    показ всех книг
     */
    @GetMapping("/check_books")
    public String showBooks(Model model) {
        List<Book> bookList = bookService.getBookList();
        model.addAttribute("books", bookList);
        return "admin/books";
    }

    /*
    добавить книгу
     */
    @GetMapping("/create_book")
    public String createBooks(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "admin/create_book";
    }

    @SneakyThrows
    @PostMapping("/create")
    public String create(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/check_books";
    }

    /*
    удаление книги
     */
    @GetMapping("/delete_book/{idBook}")
    public String deleteBook(@PathVariable long idBook) {
        Book getBook = bookService.getBookById(idBook);
        getBook.getUserSet().forEach(e->e.setBookSet(null));
        bookService.deleteBook(getBook);
        return "redirect:/check_books";
    }

    /*
    exit button(check_books)
     */
    @GetMapping("/exits")
    public String exits() {
        return "redirect:/check_books";
    }
}
