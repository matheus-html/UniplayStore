package com.uniplaystore.uniplay_backend.repository;

import com.uniplaystore.uniplay_backend.catalog.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {

    List<Game> findByGenre(String genre);
    Optional<Game> findByTitleIgnoreCase(String title);
    List<Game> findByTitleContainingIgnoreCase(String title);
}
