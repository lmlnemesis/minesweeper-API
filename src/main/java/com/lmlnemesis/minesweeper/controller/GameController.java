package com.lmlnemesis.minesweeper.controller;

import com.lmlnemesis.minesweeper.dto.request.NewGameRequest;
import com.lmlnemesis.minesweeper.dto.response.BoardResponse;
import com.lmlnemesis.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping(path = "/new-game", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> newGame(@RequestBody @Valid NewGameRequest newGameRequest) {
        return new ResponseEntity<>(
                gameService.createNewGame(newGameRequest.getMineAmount(), newGameRequest.getSize()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/board/{boardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponse> getBoard(@PathVariable(value = "boardId") Integer boardId) {
        return new ResponseEntity<>(gameService.getBoard(boardId), HttpStatus.OK);
    }

}
