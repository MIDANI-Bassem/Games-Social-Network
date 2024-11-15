package org.gameloom.connect.game.game.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.game.dto.BorrowedGameResponse;
import org.gameloom.connect.game.game.dto.GameResponse;
import org.gameloom.connect.game.game.bll.GameService;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("games")
@RequiredArgsConstructor
@Tag(name="Game")
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Integer> saveGame(
            @Valid @RequestBody GameRequest request,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.save(request, connectedUser));
    }
    @GetMapping("{game-id}")
    public ResponseEntity<GameResponse> findGameById(
            @PathVariable("game-id") Integer gameId
    ){
        return ResponseEntity.ok(gameService.findById(gameId));

    }
    @GetMapping
    public ResponseEntity<PageResponse<GameResponse>> findAllGames(
            @RequestParam(name = "page", defaultValue="0", required = false) int page,
            @RequestParam(name = "page", defaultValue="10", required = false) int size,
            Authentication connectedUser

            ){
        return ResponseEntity.ok(gameService.findAllGames(page, size, connectedUser));

    }
    @GetMapping("/owner")
    public ResponseEntity<PageResponse<GameResponse>> findAllGamesByOwner(
            @RequestParam(name = "page", defaultValue="0", required = false) int page,
            @RequestParam(name = "page", defaultValue="10", required = false) int size,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.findAllGamesByOwner(page, size, connectedUser));

    }
    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedGameResponse>> findAllBorrowedGames(
            @RequestParam(name = "page", defaultValue="0", required = false) int page,
            @RequestParam(name = "page", defaultValue="10", required = false) int size,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.findAllBorrowedGames(page, size, connectedUser));

    }
    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedGameResponse>> findAllReturnedGames(
            @RequestParam(name = "page", defaultValue="0", required = false) int page,
            @RequestParam(name = "page", defaultValue="10", required = false) int size,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.findAllReturnedGames(page, size, connectedUser));

    }
    @PatchMapping("shareable/{game-id}")
    public ResponseEntity<Integer> updateGameStatus(
            @PathVariable("game-id") Integer gameId,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.updateShareableStatus(gameId, connectedUser));

    }
    @PostMapping("/borrow/{game-id}")
    public ResponseEntity<Integer> borrowGame(
            @PathVariable("game-id") Integer gameId,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.borrowGame(gameId, connectedUser));

    }
    @PatchMapping("/borrow/return/{game-id}")
    public ResponseEntity<Integer> returnGame(
            @PathVariable("game-id") Integer gameId,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.returnGame(gameId, connectedUser));

    }
    @PatchMapping("/borrow/return/approve/{game-id}")
    public ResponseEntity<Integer> approveReturnGame(
            @PathVariable("game-id") Integer gameId,
            Authentication connectedUser

    ){
        return ResponseEntity.ok(gameService.approveReturnGame(gameId, connectedUser));

    }
    @PostMapping(value="/image/{game-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadGameImage(
            @PathVariable("game-id") Integer gameId,
            @Parameter
            @RequestPart("file") MultipartFile file,
            Authentication connectedUser
            ){
        gameService.uploadGameImage(file, connectedUser, gameId);
        return ResponseEntity.accepted().build();

    }




}
