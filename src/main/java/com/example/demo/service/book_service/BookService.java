package com.example.demo.service.book_service;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBookById(long idBook);
    void saveBook(Book book);
    List<Book> filterBookByGenre(String genre);
    void deleteBook(Book book);
}
