package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "date_order")
    Date date;

    @ManyToMany
    @JoinTable(
            name = "order_user",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_users")
    )
    Set<User> userSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_admin")
    Admin admin;

    public Order(Date date) {
        this.date = date;
    }

}
