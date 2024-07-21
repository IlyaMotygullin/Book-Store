package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.service.book_service.BookService;
import com.example.demo.service.user_service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller(value = "bookController")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {
    /*
    TODO: реализовать вход или регистрацию, когда пользователь добовляет книги в лк
    TODO: реализовать заказ для пользователя
     */
    final BookService bookService;
    final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/roman_book")
    public String getRomanBook(@RequestParam(value = "param") String roman, Model model) {
        List<Book> bookList = bookService.filterBookByGenre(roman);
        model.addAttribute("booksGenreRoman", bookList);
        return "books_genre/book_roman";
    }

    @GetMapping("/save_book/{idBook}")
    public String saveBook(@PathVariable long idBook, HttpSession session) {
        User user = (User) session.getAttribute("user"); // получение пользователя
        if (user == null) {
            return "redirect:/register_user";
        }

        Book getBook = bookService.getBookById(idBook);
        user.getBookSet().add(getBook);
        getBook.getUserSet().add(user);

        bookService.saveBook(getBook);
        userService.saveUser(user);
        return "redirect:/roman_book?param=роман";
    }

















//    @GetMapping("/detective_book")
//    public String getDetectiveBook(@RequestParam(value = "param") String detective, Model model) {
//        List<Book> bookList = bookService.filterBookByGenre(detective);
//        model.addAttribute("booksGenreDetective", bookList);
//        return "books_genre/book_detective";
//    }
//
//    @GetMapping("/fantasy_book")
//    public String getFantasyBook(@RequestParam(value = "param") String fantasy, Model model) {
//        List<Book> bookList = bookService.filterBookByGenre(fantasy);
//        model.addAttribute("booksGenreFantasy", bookList);
//        return "books_genre/book_fantasy";
//    }
//
//    @GetMapping("/lyrics_book")
//    public String getLyricsBook(@RequestParam(value = "param") String lyrics, Model model) {
//        List<Book> bookList = bookService.filterBookByGenre(lyrics);
//        model.addAttribute("booksGenreLyrics", bookList);
//        return "books_genre/book_lyrics";
//    }
}
