/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.library.entities;

import java.util.Scanner;

/**
 *
 * @author boss_
 */
public class LibraryInterfaceDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
    
        //LibraryUser kid2 = new KidUsers(18, "Fiction");
        boolean yes = true;
        while (yes) {
            
            try {
                try {
                    System.out.println("Enter age:");
                    int age = Integer.parseInt(scan.nextLine());

                    System.out.println("Enter book type:");
                    String bookType = scan.nextLine();

                    LibraryUser kid = new KidUsers(age, bookType);
                    kid.registerAccount();
                    kid.requestBook();
                    yes = false;
                } catch (UserException | NumberFormatException e) {
                    System.out.println(e);
                    throw new UserException("Incorrect characters");
                }
            } catch (UserException e) {
                System.out.println(e);
            }
            
        }
        
        
        
        
        
        
       // kid2.registerAccount();
        
        
        
       // kid2.requestBook();
        
        LibraryUser adult = new AdultUser(5, "Kids");
        LibraryUser adult2 = new AdultUser(23, "Fiction");
        
       // adult.registerAccount();
        
       // adult2.registerAccount();
        
       //adult.requestBook();
        
        //adult2.requestBook();
        
    }
    
}
