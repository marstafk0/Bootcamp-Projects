/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.developerclub;

import java.util.Scanner;


/**
 *
 * @author boss_
 */
public class ClubMemberApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean keepRunning = true;
        Scanner sc = new Scanner(System.in);
        int user = 0;
        
        SoftwareDeveloperClub s = new SoftwareDeveloperClub();
        
        s.loadMembers();
        
        do {
            
            System.out.println("""
                               Select from the following menu:
                               1. Display Members
                               2. Remove Member
                               3. Add Member
                               4. Quit
                               """);
            try {
               user = Integer.parseInt(sc.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry!");
            }
            
            switch(user) {
                case 1 -> {
                    
                    s.memberss.forEach(m -> {
                        System.out.println("Member Name: " + m.getName() +
                                "\nLocation: " + m.getCity() + ", " + m.getState() +
                                "\nFavorite Language: " + m.getFavLang() + "\n");
                });
                    
                }
                case 2 -> {
                    System.out.println("Enter name of member to remove:");
                    try {
                        int index = s.getIndexByProperty(sc.nextLine());
                        s.memberss.remove(index);
                        System.out.println("Member removed.\n");
                    } catch (Exception e) {
                        System.out.println("Could not remove!");
                    }
                    
                }
                
                case 3 -> {
                    
                    System.out.println("Enter Member Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter City:");
                    String city = sc.nextLine();
                    System.out.println("Enter State:");
                    String state = sc.nextLine();
                    System.out.println("Enter Favorite Language:");
                    String fav = sc.nextLine();
                    
                    ClubMember m = new ClubMember(name, city, state, fav);
                    
                    s.memberss.add(m);
                    System.out.println("Member Added.\n");
                    
                }
                
                case 4 -> {
                    keepRunning = false;
                    s.writeMember();
                }
            }
            
        } while(keepRunning);
        
    }
    
}
