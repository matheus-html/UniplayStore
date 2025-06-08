package com.uniplaystore.uniplay_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uniplaystore.uniplay_backend.catalog.Game;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByGenre(String genre);
    List<Game> findByTitleContainingIgnoreCase(String title);
}
