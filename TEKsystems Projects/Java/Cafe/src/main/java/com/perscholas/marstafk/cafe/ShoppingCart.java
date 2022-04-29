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
public class ShoppingCart {
    
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    } 
    
}
