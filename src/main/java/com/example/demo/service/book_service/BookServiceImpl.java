package com.example.demo.service.book_service;

import com.example.demo.entity.Book;
import com.example.demo.repository.book_repository.BookRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "bookService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookServiceImpl implements BookService {
    final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }
    @Override
    public void saveBook(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> filterBookByGenre(String genre) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBookList() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(long idBook) {
        return repository.findById(idBook).orElse(null);
    }
}
