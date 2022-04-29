/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.java_basics.corejavabasics;

/**
 *
 * @author boss_
 */
public class Arrays {
    
    public void arr() {
        
        // 1.
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(nums[0]);
        System.out.println(nums[1]);
        System.out.println(nums[2]);
        
        // 2.
        int[] nums2 = {13, 5, 7, 68, 2};
        System.out.println(nums2[2]);
        
        // 3.
        String[] str = {"red", "green", "blue", "yellow"};
        System.out.println(str.length);
        System.out.println(str.toString());
        System.out.println(str.clone().toString());
        
        // 4.
        System.out.println(nums[0]);
        System.out.println(nums[nums.length-1]);
        //System.out.println(nums[nums.length]);
        //nums[5] = 6;
        
        // 5.
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        
        // 6.
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i * 2;
        }
        
        // 7.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 2) {
                continue;
            }
            System.out.println(nums[i]);
        }
        
        // 8.
        nums2[0] = (nums[0] + nums[2]) - (nums[2] = nums[0]);
        
        // 9. (Yes I used a bubble sort)
        int[] sort = {4, 2, 9, 13, 1, 0};
        int temporary = 0;
  
        // Sort the array 'sort' elements in ascending order
        // using nested for loops
        for (int i = 0; i < sort.length; i++) {
            for (int j = i + 1; j < sort.length; j++) {
                if (sort[i] > sort[j]) {
                    temporary = sort[i];
                    sort[i] = sort[j];
                    sort[j] = temporary;
                }
            }
        }
  
        // Displaying elements of array after sorting
        System.out.println(
            "Elements of array sorted in ascending order: ");
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i] + " ");
        }
    
        // 10.
        Object[] objs = new Object[5];
        objs[0] = 5;
        objs[1] = "hi";
        objs[2] = "hi";
        objs[3] = "hi";
        objs[4] = 3.7;
    }
    
}
