/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.cafe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boss_
 */
public class Store {
    
    private final Product coffee = new Coffee("Coffee", 2.99, "Delicious and perfect for an early morning.");
    private final Product espresso = new Espresso("Espresso", 3.99, "Extra kick for the day.");
    private final Product cap = new Cappuccino("Cappuccino", 4.99, "Delicious.");
    private final List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        products.add(coffee);
        products.add(espresso);
        products.add(cap);
        return products;
    }
    
}
