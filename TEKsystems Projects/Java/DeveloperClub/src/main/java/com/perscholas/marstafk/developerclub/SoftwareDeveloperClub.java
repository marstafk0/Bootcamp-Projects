/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.developerclub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author boss_
 */
public class SoftwareDeveloperClub {
    
    private final String MEMBERS = "members.txt";
    private final String DELIMETER = "::";
    ArrayList<ClubMember> memberss = new ArrayList<>();
    
    public SoftwareDeveloperClub() {
        
    }
    
    public void loadMembers() {
    Scanner scanner = new Scanner(System.in);
  
    try {
        scanner = new Scanner(new BufferedReader(new FileReader(MEMBERS)));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        }
        
        String currentLine;
        //headerLine removes header to not make an object
        String headerLine = scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            
            String[] memberTokens = currentLine.split(DELIMETER);
            ClubMember member = new ClubMember(memberTokens[0], memberTokens[1], memberTokens[2], memberTokens[3]);
            
            memberss.add(member);
            //members.put(index, member);
            //index++;
        }
        scanner.close();
    }
    
    private String saveToText(ClubMember member) {
        
        String memberAsText = member.getName() + DELIMETER;
        memberAsText += member.getCity() + DELIMETER;
        memberAsText += member.getState() + DELIMETER;
        memberAsText +=  member.getFavLang();
        
        return memberAsText;
    }
    
    public void writeMember() {
        
        PrintWriter out = new PrintWriter(System.out);
        
        try {
            out = new PrintWriter(new FileWriter(MEMBERS));
        } catch (IOException e) {
            System.out.println("Could not save data");
        }
        
        String memberAsText;
        //Collection<ClubMember> membersList = members.values();
        out.println("NAME::CITY::STATE::FAVORITE PROGRAMMING LANGUAGE");
        for(ClubMember m : memberss) {
            memberAsText = saveToText(m);
            out.println(memberAsText);
            out.flush();
        }
        out.close();
    }
    
    public int getIndexByProperty(String str) {
        for(int i = 0; i < memberss.size(); i++) {
            ClubMember c = memberss.get(i);
            if (c != null && c.getName().equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    } 
    
}
