package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title_book")
    String title;

    @Column(name = "author_book")
    String author;

    @Column(name = "img_book")
    String imgBook;

    @Column(name = "genre")
    String genre;

    @ManyToMany(mappedBy = "bookSet")
    Set<User> userSet = new HashSet<>();

    public Book(String title, String author, String genre, String imgBook) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.imgBook = imgBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgBook='" + imgBook + '\'' +
                '}';
    }
}
