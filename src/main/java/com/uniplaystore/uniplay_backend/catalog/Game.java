package com.uniplaystore.uniplay_backend.catalog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Game {
    @Id
    private String id;
    private String title;
    private String genre;
    private double price;
    private int stock;
    private String description;

    public Game(GameRequestDTO data) {
        this.title = data.title();
        this.genre = data.genre();
        this.price = data.price();
        this.stock = data.stock();
        this.description = data.description();
    }
}
