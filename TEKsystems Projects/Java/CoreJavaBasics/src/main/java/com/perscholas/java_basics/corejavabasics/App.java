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
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Write a program that declares 2 integer variables, assigns an integer to each, and adds them together. Assign the sum to a variable. Print out the result.
        int num = 5;
        int numTwo = 6;
        int sum = num + numTwo;
        System.out.println("Sum: " + sum);
        
        //Write a program that declares 2 double variables, assigns a number to each, and adds them together. Assign the sum to a variable. Print out the result.
        double one = 5.5;
        double two = 6.6;
        double sumTwo = one + two;
        System.out.println("Sum: " + sumTwo);
        
        //Write a program that declares an integer variable and a double variable, assigns numbers to each, and adds them together. Assign the sum to a variable. Print out the result. What variable type must the sum be?
        int intt = 8;
        double dub = 3.2;
        //needs to be a double because of ddecimal.
        double sumThree = intt + dub;
        System.out.println("Sum: " + sumThree);
        
        //Write a program that declares 2 integer variables, assigns an integer to each, and divides the larger number by the smaller number. 
        //Assign the result to a variable. Print out the result. Now change the larger number to a decimal. What happens? What corrections are needed?
        int hi = 10;
        int ho = 2;
        int sum4 = hi / ho;
        System.out.println("Result: " + sum4);
        //hi = 3.3; can't change types, needs to be made a double.
        
        //Write a program that declares 2 double variables, assigns a number to each, and divides the larger by the smaller. Assign the result to a variable. 
        //Print out the result. Now, cast the result to an integer. Print out the result again.
        double ug = 2.2;
        double pug = 3.8;
        double res = pug / ug;
        System.out.println("Result: " + res);
        int myInt = (int) res;
        System.out.println("Result: " + myInt);
        
        //Write a program that declares 2 integer variables, x, and y, and assign 5 to x and 6 to y. Declare a variable q and assign y/x to it and print q. Now, cast y to a double and assign to q. Print q again.
        int x = 5;
        int y = 6;
        double q = y / x;
        System.out.println("Result: " + q);
        double myD = (double) y;
        q = myD;
        System.out.println("Result: " + q);
        
        //Write a program that declares a named constant and uses it in a calculation. Print the result.
        final int me = 5;
        final int yk = 5;
        System.out.println(me + yk);
        
        //Write a program where you create 3 variables that represent products at a cafe. The products could be beverages like coffee, cappuccino, espresso, green tea, etc. 
        //Assign prices to each product. Create 2 more variables called subtotal and totalSale and complete an “order” for 3 items of the first product, 4 items of the second product, and 2 items of the third product. 
        //Add them all together to calculate the subtotal. Create a constant called SALES_TAX and add sales tax to the subtotal to obtain the totalSale amount. Be sure to format the results to 2 decimal places.
        double coffee, cappuccino, espresso;
        coffee = 2.99;
        cappuccino = 5.99;
        espresso = 4.99;
        double subTotal;
        double totalSale;
        double order = coffee * 3 + cappuccino * 4 + espresso * 2;
        subTotal = order;
        final double SALES_TAX = 6.75;
        totalSale = SALES_TAX + subTotal;
        totalSale = Math.round(totalSale * 100.0) / 100.0;
        System.out.println("Total: " + totalSale);
        
        OpsAndNums yea = new OpsAndNums();
        Loops l = new Loops();
        Arrays a = new Arrays();
        
        //yea.opsandnumbs();
        //l.loops();
        a.arr();
        
    }
    
}
