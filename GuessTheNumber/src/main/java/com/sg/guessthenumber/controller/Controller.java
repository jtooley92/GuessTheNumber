/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.GameDaoDb;
import com.sg.guessthenumber.entity.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jtooleyful
 */
@RestController
@RequestMapping("/api/todo")
public class Controller {
    private final GameDao dao;

    public Controller(GameDao dao) {
        this.dao = dao;
    }
    
    Game game = new Game();
    
    @PostMapping
    public Game all(){
        game.setGeneratedNumber("0123");
        game.setStatus(false);
        System.out.println(dao.addGame(game));
        
        return game;
    }
    
    @GetMapping
    public String hi(){
        return "hi";
    }
}