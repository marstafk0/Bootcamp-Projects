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
public class Loops {
    
    public void loops() {
        
        System.out.println("Loops:");
        //Write a program that uses a for-loop to loop through the numbers 1-10 and prints out each number.
        
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        
        //Write a program that uses a while loop to loop through the numbers 0-100 in increments of 10 and prints out each number.
        int i = 0;
        while (i <= 100) {
            System.out.println(i);
            i += 10;
        }   
        
        //Write a program that uses a do-while-loop to loop through the numbers 1-10 and prints out each number.
        int n = 1;
        do {
            System.out.println(n);
            n++;
        } while (n <= 10);
        
        //Write a program that uses a for-loop to loop through the numbers 1-100. Print out each number if is a multiple of 5, 
        //but do not print out any numbers between 25 and 75. Use the “continue” statement to accomplish this.
        
        for (int u = 0; u <= 100; u++) {
            
            if (u >= 25 && u <= 75) {
                continue;
            } else if (u % 5 == 0) {
                System.out.println(u);
            }
        }
        
        //Write a program that uses a for-loop to loop through the numbers 1-100. Print out each number if is a multiple of 5, 
        //but do not print out any numbers greater than 50. Use the “break” keyword to accomplish this.
        
        for (int u = 0; u <= 100; u++) {
            
            if (u > 50) {
                break;
            } else if (u % 5 == 0) {
                System.out.println(u);
            }
        }
        
        //Write a program that uses nested for-loops to output the following:
        int weeks = 2;
        int days = 7;
        for (int u = 1; u <= weeks; ++u) {
            System.out.println("Week: " + u);
            for (int j = 1; j <= days; ++j) {
                System.out.println("Day: " + j);
            }
        }
        
        //Write a program that returns all the available palindromes within 10 and 200. The following output will be produced:
        
        for (int o = 10; o <= 200; o++){
            if (isPalindrome(o)==1)
                System.out.print(o + " ");
        }
        
        //Write a program that prints the Fibonacci Sequence from 0 to 50. The following output will be produced: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
        
        int count = 50, num1 = 0, num2 = 1;
        System.out.println("Fibonacci Series of "+count+" numbers:");

        for (int o = 1; o <= count; ++o)
        {
            System.out.print(num1+" ");

            /* On each iteration, we are assigning second number
             * to the first number and assigning the sum of last two
             * numbers to the second number
             */
            int sumOfPrevTwo = num1 + num2;
            num1 = num2;
            num2 = sumOfPrevTwo;
        }
        
        // Write a program that nests a for-loop inside another. Print out the inner and outer variable (e.g., i or j) in the inner loop 
        //(e.g., System.out.println("Inner loop: i: " + i + ", j: " + j);).
        
        // outer loop
        for (int o = 1; o <= 10; ++o) {
          // codes

          // inner loop
          for(int j = 1; j <= 10; ++j) {
              System.out.println("i: " + o + " j: " + j);
          }
        }
        
     
    }
    
    public int isPalindrome(int n){
    
        // Find reverse of n
        int rev = 0;
        for (int i = n; i > 0; i /= 10)
            rev = rev * 10 + i % 10;
             
        // If n and rev are same,
        // then n is palindrome
        return(n == rev) ? 1 : 0;
    }
    
}
