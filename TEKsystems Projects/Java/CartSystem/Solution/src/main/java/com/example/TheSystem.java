package com.example;

import java.io.*;
import java.util.*;

public abstract class TheSystem {
   
    private HashMap<String, Item> itemCollection;

    TheSystem() {
        this.itemCollection = new HashMap<String, Item>();
        if(getClass().getSimpleName().equals("AppSystem")) {
            
            Scanner scanner = new Scanner(System.in);
  
    try {
        scanner = new Scanner(new BufferedReader(new FileReader("./resources/sample.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        }
        
        String currentLine;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            
            String[] itemTokens = currentLine.split("\\s ");
            Item item = new Item(itemTokens[0], itemTokens[1], Double.parseDouble(itemTokens[2]), Integer.parseInt(itemTokens[3]));
            
            this.itemCollection.put(itemTokens[0], item);
            //members.put(index, member);
            //index++;
        }
        scanner.close();
        }
    }
    
    public HashMap<String, Item> getItemCollection(){
        return this.itemCollection;
    }
    
    public void setItemCollection(HashMap<String, Item> itemCollection){
        this.itemCollection = itemCollection;
    }
    
    public Boolean checkAvailability(Item item) {
        if(item == null) {
            return false;
        }
        if(item.getQuantity() < item.getAvailableQuantity()){
            return true;
        }
        if(item.getQuantity() >= item.getAvailableQuantity()) {
            System.out.println("System is unable to add " + item.getItemName() + " to the card. System only has " + item.getAvailableQuantity() + " " +                 item.getItemName() + "s.");
            return false;
        } 
        return true;
    }
    
    public Boolean add(Item item) {
        if(item == null) {
            return false;
        }
        if(this.itemCollection.containsValue(item)) {
            this.itemCollection.get(item.getItemName()).setQuantity(item.getQuantity()+1);
            return true;
        } else if (!this.itemCollection.containsValue(item)) {
            this.itemCollection.put(item.getItemName(), item);
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        if(this.itemCollection.containsKey(itemName)) {
            Item item = this.itemCollection.get(itemName);
            this.itemCollection.remove(itemName);
            return item;
        } else {
            return null;
        }
    }

    public abstract void display();
    
    
}
