package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admins")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin {
    @Id
    @Column(name = "id_admin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "email_admin")
    String email;

    @Column(name = "password_admin")
    String password;

    @OneToMany(mappedBy = "admin")
    List<Order> orderList;

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
