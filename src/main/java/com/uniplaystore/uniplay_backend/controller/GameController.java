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

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/register")
    public ResponseEntity<String> postGame(@RequestBody @Valid GameRequestDTO body){
        boolean exists = gameRepository.findByTitleContainingIgnoreCase(body.title()).stream().findFirst().isPresent();
        if(exists){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("TÍTULO DO JOGO JÁ EXISTENTE.");
        }

        Game game = new Game(body);
        this.gameRepository.save(game);
        return ResponseEntity.ok("Jogo cadastrado com sucesso.");
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
}
