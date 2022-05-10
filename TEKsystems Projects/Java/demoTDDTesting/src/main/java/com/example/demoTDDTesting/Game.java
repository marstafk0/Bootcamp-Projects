/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demoTDDTesting;

import java.util.ArrayList;

/**
 *
 * @author boss_
 */
public class Game {
    
    private String player;
    private ArrayList<Player> playerList;
    
    public Game(String player) {
        this.player = player;
    }
    
    public Game() {}

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
    
    public boolean add(Player player) {
        playerList.add(player);
        return true;
    }
    
    public void getWinner() {
        System.out.println("Winner!");
    }

    @Override
    public String toString() {
        return "Game{" + "player=" + player + ", playerList=" + playerList + '}';
    }
    
}
