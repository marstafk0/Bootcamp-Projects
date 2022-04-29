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
public class OpsAndNums {
    
    public void opsandnumbs() {
        
        //Write the following integers in binary notation. Do not use any Java functions or online conversion applications to calculate the answer 
        //as this will hinder the learning process and your understanding of the concept. However, you may check your answers using Java methods.
        
        //1
        //1000
        //100001
        //1001110
        //1100010011
        //1000010011000011
        
        //Convert the following binary numbers to decimal notation. Do not use any Java functions or online conversion applications to calculate 
        //the answer as this will hinder the learning process and your understanding of the concept. However, you may check your answers using Java methods.
        
        //2
        //9
        //3 4
        //7 2
        //2 1 15
        //2 12 6 7
        
        //Write a program that declares an integer a variable x and assigns the value 2 to it and prints out the binary string version of the number ( Integer.toBinaryString(x) ). 
        //Now, use the left shift operator (<<) to shift by 1 and assign the result to x. Before printing the results, write a comment with the predicted decimal value and binary 
        //string. Now, print out x in decimal form and in binary notation.
        
        int x = 2;
        System.out.println("Binary: " + Integer.toBinaryString(x));
        x = x << 1;
        //4, 10
        System.out.println(x);
        x = 9;
        System.out.println("Binary: " + Integer.toBinaryString(x));
        x = x << 1;
        System.out.println(x);
        x = 17;
        System.out.println("Binary: " + Integer.toBinaryString(x));
        x = x << 1;
        System.out.println(x);
        x = 88;
        System.out.println("Binary: " + Integer.toBinaryString(x));
        x = x << 1;
        System.out.println(x);
        
        //Write a program that declares a variable x and assigns 150 to it and prints out the binary string version of the number. Now use the right shift operator (>>) to shift by 2 and assign the result to x. 
        //Write a comment indicating what you anticipate the decimal and binary values to be. Now print the value of x and the binary string.
        
        int y = 150;
        System.out.println("Binary: " + Integer.toBinaryString(y));
        y = y >> 2;
        System.out.println(y);
        y = 255;
        System.out.println("Binary: " + Integer.toBinaryString(y));
        y = y >> 2;
        System.out.println(y);
        y = 1555;
        System.out.println("Binary: " + Integer.toBinaryString(y));
        y = y >> 2;
        System.out.println(y);
        y = 32456;
        System.out.println("Binary: " + Integer.toBinaryString(y));
        y = y >> 2;
        System.out.println(y);
        
        //Write a program that declares 3 int variables x, y, and z. Assign 7 to x and 17 to y. Write a comment that indicates what you predict will be the result of the bitwise & operation on x and y.
        //Now use the bitwise & operator to derive the decimal and binary values and assign the result to z.
        
        x = 7;
        y = 17;
        int z;
        System.out.println(x & y);
        System.out.println(x | y);
        
        //Write a program that declares an integer variable, assigns a number, and uses a postfix increment operator to increase the value. Print the value before and after the increment operator.
        int f = 4;
        System.out.println(f);
        f++;
        System.out.println(f);
        
        //Write a program that demonstrates at least 3 ways to increment a variable by 1 and does this multiple times. Assign a value to an integer variable, print it, increment by 1, print it again, increment by 1, and then print again.
        
        x = 1;
        System.out.println(x);
        x++;
        System.out.println(x);
        x = 1;
        System.out.println(x);
        x = x+1;
        System.out.println(x);
        x = 1;
        System.out.println(x);
        x+=1;
        System.out.println(x);
        
        //Write a program that declares 2 integer variables, x, and y, and then assigns 5 to x and 8 to y. Create another variable sum and assign the value of ++x added to y and print the result. 
        //Notice the value of the sum (should be 14). Now change the increment operator to postfix (x++) and re-run the program. Notice what the value of sum is. 
        //The first configuration incremented x and then calculated the sum while the second configuration calculated the sum and then incremented x.
        
        x = 5;
        y = 8;
        int sum = ++x + y;
        System.out.println(sum);
        x = 5;
        y = 8;
        sum = x++ + y;
        System.out.println(sum);
        
    }
    
}
