/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.GameDaoDb;
import com.sg.guessthenumber.dao.GuessTheNumberDaoException;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import com.sg.guessthenumber.service.GuessTheNumberService;
import com.sg.guessthenumber.service.GuessTheNumberServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jtooleyful
 */
@RestController
@RequestMapping("/game")
public class Controller {
    private GuessTheNumberService service;
    
    @Autowired
    public Controller(GuessTheNumberService service) {
        this.service = service;
    }
    
    Game game = new Game();
    
    @PostMapping
    public Game addGame(){
        service.addGame(game);
        
        return game;
    }
    
    @PostMapping("/round{gameId}")
    public Round addRound(@RequestBody Round round, @PathVariable int gameId) throws GuessTheNumberDaoException{
        
         round = service.addRound(gameId, round.getNumberGuess());
        
        return round;
    }
    
    @GetMapping("/gamestatus")
    public Game getGame(int gameId){
        Game game = service.getGame(gameId);
        
        return game;
    }
      
    @GetMapping("/allgames")
     public List<Game> getGames(){
         List<Game> games = service.getAllGames();
         
         return games;
     }
     
     @GetMapping("/roundsforgame")
      public List<Round> getRoundsForGame(int gameId){
           List<Round> rounds = service.getRoundsByGameId(gameId);
           
           return rounds;
      }
}   