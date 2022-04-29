/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.library.entities;

/**
 *
 * @author boss_
 */
public class KidUsers implements LibraryUser  {
    
    int age;
    String bookType;

    public KidUsers(int age, String bookType) throws UserException {
        try {
            this.age = age;
            this.bookType = bookType;
        } catch (Exception e) {
            throw new UserException("Incorrect data type");
        }
        
    }
    
    @Override
    public void registerAccount() throws UserException {
        if (age <= 12) {
            System.out.println("You have successfully registered under a Kids account");
        } else if (age > 12){
            System.out.println("Sorry, Age must be less than 12 to register as a kid");
        } else {
            throw new UserException("Must use numbers");
        }
    }

    @Override
    public void requestBook() {
        if (bookType.equals("Kids")) {
            System.out.println("Book issued successfully, please return the book within 10 days");
        } else {
            System.out.println("Oops, you are allowed to take only kids books");
        }
    }
    
}
