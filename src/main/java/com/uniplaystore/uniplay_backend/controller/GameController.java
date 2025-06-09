package com.uniplaystore.uniplay_backend.controller;

import com.uniplaystore.uniplay_backend.catalog.Game;
import com.uniplaystore.uniplay_backend.catalog.GameRequestDTO;
import com.uniplaystore.uniplay_backend.catalog.GameResponseDTO;
import com.uniplaystore.uniplay_backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/register")
    public ResponseEntity<String> postGame(@RequestBody @Valid GameRequestDTO body){
        Optional<Game> existingGame = gameRepository.findByTitleIgnoreCase(body.title());
        if(existingGame.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("TÍTULO DO JOGO JÁ EXISTENTE.");
        }

        Game game = new Game(body);
        this.gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body("Jogo cadastrado com sucesso.");
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<GameResponseDTO>> getGameByGenre(@PathVariable("genre") String genre){
        List<GameResponseDTO> gameResponseDTOList = gameRepository.findByGenre(genre)
                .stream()
                .map(GameResponseDTO::new)
                .toList();
        if(gameResponseDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gameResponseDTOList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<GameResponseDTO>> getAllGames(){
        List<GameResponseDTO> gameResponseDTOList = this.gameRepository.findAll().stream().map(GameResponseDTO::new).toList();
        if (gameResponseDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gameResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGame(@PathVariable String id, @RequestBody @Valid GameRequestDTO body){
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Game existingGame = optionalGame.get();

        if (!existingGame.getTitle().equalsIgnoreCase(body.title())) {
            Optional<Game> gameWithNewTitle = gameRepository.findByTitleIgnoreCase(body.title());
            if (gameWithNewTitle.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        existingGame.setTitle(body.title());
        existingGame.setGenre(body.genre());
        existingGame.setPrice(body.price());
        existingGame.setStock(body.stock());
        existingGame.setDescription(body.description());

        Game updatedGame = gameRepository.save(existingGame);
        return ResponseEntity.ok(new GameResponseDTO(updatedGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id){
        if (!gameRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
