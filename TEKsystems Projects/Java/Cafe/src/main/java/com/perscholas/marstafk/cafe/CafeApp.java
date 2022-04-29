/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author boss_
 */
public class CafeApp {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean keepRunning = true;
        int user;
        Scanner scan = new Scanner(System.in);
        Store store = new Store();
        List<Product> products = store.getProducts();
        double salesTax = 1.08;
        double subtotal = 0;
        ShoppingCart cart = new ShoppingCart();
        List<Product> theCart = new ArrayList<>();
        
        do {
            System.out.println("""
                               
                               Please make a selection:
                               1. Coffee
                               2. Espresso
                               3. Cappuccino
                               4. Check out
                               5. Exit""");
            
            user = Integer.parseInt(scan.nextLine());
            
            switch(user) {
                
                case 1 -> {
                    
                    Product coffee = products.get(0);
                    
                    System.out.println("How many Coffee would you like?");
                    coffee.setQuantity(Integer.parseInt(scan.nextLine()));
                    
                    System.out.println(coffee.getName() + " $" + coffee.getPrice() + " " + coffee.getDescription() + "\n" + 
                            "Subtotal: $" + subtotal);
                    
                    System.out.println("Would you like to add Milk? (y/n)");
                    String option = scan.nextLine();
                    
                    System.out.println("Would you like to add Sugar?");
                    String option2 = scan.nextLine();
                    
                    coffee.addOptions(option, option2);
                    subtotal += coffee.calculateProductTotal();
                    theCart.add(coffee);
                    
                }
                case 2 -> {
                    
                    Product espresso = products.get(1);
                    
                    System.out.println("How many Espresso would you like?");
                    espresso.setQuantity(Integer.parseInt(scan.nextLine()));
                    
                    System.out.println(espresso.getName() + " $" + espresso.getPrice() + " " + espresso.getDescription() + "\n" + 
                            "Subtotal: $" + subtotal);
                    
                    System.out.println("Would you like to add an extra shot? +$2 (y/n)");
                    String option = scan.nextLine();
                    
                    System.out.println("Would you like it in Macchiato style? +$1");
                    String option2 = scan.nextLine();
                    
                    espresso.addOptions(option, option2);
                    subtotal += espresso.calculateProductTotal();
                    theCart.add(espresso);
                    
                }
                case 3 -> {
                    
                    Product cappuccino = products.get(2);
                    
                    System.out.println("How many Cappuccino would you like?");
                    cappuccino.setQuantity(Integer.parseInt(scan.nextLine()));
                    
                    System.out.println(cappuccino.getName() + " $" + cappuccino.getPrice() + " " + cappuccino.getDescription() + "\n" + 
                            "Subtotal: $" + subtotal + "\n");
                    
                    System.out.println("Would you like to add peppermint? +$2 (y/n)");
                    String option = scan.nextLine();
                    
                    System.out.println("Would you like to add whipped cream? +$1");
                    String option2 = scan.nextLine();
                    
                    cappuccino.addOptions(option, option2);
                    subtotal += cappuccino.calculateProductTotal();
                    theCart.add(cappuccino);
                    
                }
                case 4 -> {
                    
                    cart.setProducts(theCart);
                    
                    System.out.println("Proceed to checkout.\n");
                    
                    theCart.forEach(p -> {
                        System.out.println("Item: " + p.getName() + " Price: $" + p.getuPrice() + " Qty: "
                                + p.getQuantity() + " Subtotal: $" + (p.getPrice() * p.getQuantity()) + "\n " + p.printOptions() + "\n");
                    });
                    
                    System.out.println("Purchase Subtotal: $" + subtotal + "\nSales Tax: $" + salesTax + "\nPurchase Total: $" + (subtotal + salesTax) + "\n");
                    
                    keepRunning = false;
                    
                }
                
                case 5 -> keepRunning = false;
                
            }
            
        } while(keepRunning);
        
        
    }
    
}
