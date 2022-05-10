package com.example;

public class AppSystem extends TheSystem {
    
    AppSystem() {
    }

    @Override
    public void display() {
        
        String name = "Name";
        String desc = "Description";
        String price = "Price";
        String q = "Available Quantity";
        System.out.println("AppSystem Inventory:");
        
        System.out.printf("%-20s %-20s %-10s %-10s%n", name, desc, price, q);
        
        for(Item i : this.getItemCollection().values()) {
            System.out.printf("%-20s %-20s %-10.2f %-10d%n", i.getItemName(), i.getItemDesc(), i.getItemPrice(), i.getAvailableQuantity());
        }
    }

    @Override      // this overwrites the parents class add method 
    public Boolean add(Item item) {
        if(item == null) {
            return false;
        }
        if(this.getItemCollection().containsValue(item)) {
            this.getItemCollection().get(item.getItemName()).setQuantity(this.getItemCollection().get(item.getItemName()).getQuantity() + 1);
            return false;
        } else if (!this.getItemCollection().containsValue(item)) {
            this.getItemCollection().put(item.getItemName(), item);
            return true;
        }
        return false;
    }

    public Item reduceAvailableQuantity(String item_name) {
        if(this.getItemCollection().containsKey(item_name)) {
            Item item = this.getItemCollection().get(item_name);
            item.setAvailableQuantity(item.getAvailableQuantity() - 1);
            if(item.getAvailableQuantity() == 0) {
                this.getItemCollection().remove(item_name, item);
            }
            return item;
        } else {
            return null;
        }
    }
}
