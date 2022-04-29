/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.java_basics.corejavabasics;

import java.util.Scanner;

/**
 *
 * @author boss_
 */
public class Conditional {
    
    public void condish() {
    
        int c = 7;
        if (c < 10) {
            System.out.println("Less than 10");
        }
        
        if (c < 10) {
            System.out.println("Less than 10");
        } else {
            System.out.println("Greater than 10");
        }
        
        c = 15;
        if (c < 10) {
            System.out.println("Less than 10");
        } else if (c > 10 && c < 20) {
            System.out.println("Between 10 and 20");
        } else {
            System.out.println("Greater than or equal to 20");
        }
        
        if (c < 10 || c>20) {
            System.out.println("Out of range");
        } else {
            System.out.println("In range");
        }
        
        Scanner scan = new Scanner(System.in);
        int g = scan.nextInt();
        
        if (g >= 90 || g <= 100) {
            System.out.println("A");
        } else if (g >= 80 || g <= 89) {
            System.out.println("B");
        }else if (g >= 70 || g <= 79) {
            System.out.println("C");
        }else if (g >= 60 || g <= 69) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
        
        
    }
}
