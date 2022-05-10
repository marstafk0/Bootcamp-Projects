/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demoTDDTesting;

/**
 *
 * @author boss_
 */
class Player {
    
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Player{" + "playerName=" + playerName + '}';
    }
    
}
