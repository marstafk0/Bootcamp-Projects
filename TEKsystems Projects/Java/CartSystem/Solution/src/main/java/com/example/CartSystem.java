package com.example;

public class CartSystem extends TheSystem {
    
    CartSystem() {
    }

    @Override
    public void display() {
        
        double sub_total = 0;
        for(Item i : this.getItemCollection().values()) {
            sub_total += (i.getItemPrice() * i.getQuantity());
        }
        double tax = sub_total * 0.05;
        double total = sub_total + tax;
       
        String name = "Name";
        String desc = "Description";
        String price = "Price";
        String q = "Quantity";
        System.out.println("Cart:");
        
        System.out.printf("%-20s %-20s %-10s %-10s %-10s%n", name, desc, price, q, "Sub Total");
        
        for(Item i : this.getItemCollection().values()) {
            System.out.printf("%-20s %-20s %-10.2f %-10d %-10.2f%n", i.getItemName(), i.getItemDesc(), i.getItemPrice(), i.getQuantity(), sub_total);
        }
        
        System.out.printf("%-20s %-20.2f%n", "Pre-tax Total", sub_total);
        System.out.printf("%-20s %-20.2f%n", "Tax", tax);
        System.out.printf("%-20s %-20.2f%n", "Total", total);
        
    }
}
