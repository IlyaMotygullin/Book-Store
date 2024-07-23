package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name_user")
    String name;

    @Column(name = "last_name")
    String lastname;

    @Column(name = "email_user")
    String email;

    @Column(name = "password_user")
    String password;

    @ManyToMany
    @JoinTable
            (
                    name = "book_users",
                    joinColumns = @JoinColumn(name = "id_user"),
                    inverseJoinColumns = @JoinColumn(name = "id_book")
            )
    Set<Book> bookSet = new HashSet<>();

    @ManyToMany
    Set<Order> orders = new HashSet<>();

    public User(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
