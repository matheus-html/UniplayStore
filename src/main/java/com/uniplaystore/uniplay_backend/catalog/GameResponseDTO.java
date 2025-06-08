package com.uniplaystore.uniplay_backend.catalog;

public record GameResponseDTO(String id, String title, double price, String genre, String description) {
    public GameResponseDTO(Game game){
        this(game.getId(), game.getTitle(), game.getPrice(), game.getGenre(), game.getDescription());
    }
}

