package com.uniplaystore.uniplay_backend.catalog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "games")
@Entity(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column
    private String genre;
    @Column
    private double price;
    @Column
    private int stock;
    @Column
    private String description;

    public Game(GameRequestDTO data) {
        this.title = data.title();
        this.genre = data.genre();
        this.price = data.price();
        this.stock = data.stock();
        this.description = data.description();
    }
}
