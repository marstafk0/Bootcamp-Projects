/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perscholas.marstafk.erikssandbox;

import java.util.ArrayList;

/**
 *
 * @author boss_
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
//		Java Files and Java IO: slide 7, 30, 36,37,53
//		Absolute Path
//		Relative Path  

//		Byte streams perform in and out of 8-bit bytes
		
//		FileReader in = null;
//		FileWriter out = null;
//		
//		try {
//		in= new FileReader("input.txt");
//		out = new FileWriter("output.txt");
//		
//		int num;
//		
//		while((num = in.read()) != -1) {
//			out.write(num);
//			}
//		}finally {
//			if(in != null) {
//				in.close();
//				System.out.println("ran in");
//			}
//			if(out != null){
//				out.close();
//				System.out.println("ran out");
//			}
//		}
//		
//		InputStreamReader streamIn = null;
//		
//		try {
//			streamIn = new InputStreamReader(System.in);
//			System.out.println("Enter Q to quit");
//			
//			char Q;
//			do {
//				Q = (char)streamIn.read();
//				System.out.println(Q);
//			}while(Q != 'q');
//			
//		}finally {
//			if(streamIn != null) {
//				streamIn.close();
//			}
//		}
		
		
//		Character Streams
//		Standard Streams
//		Reading and Writing Files
		
//		// created a CSV file
//		String path = "newfile.CSV";
//		
//		// Try to run this code first
//		try {
//			// created a file obj
//		File file = new File(path);
//		// scanner to read this and print it out in the console
//		Scanner input = new Scanner(file);
//		
//		// Arraylist using the obj of course
//		ArrayList<Course> data = new ArrayList<>();
//		
//		// 
//		while(input.hasNextLine()) {
//			String[] line = input.nextLine().split(",");
//			data.add(new Course(line[0],line[1], line[2]));
//		}
//		//
//		for(Course c : data) {
//			System.out.format("%-15s | %-35s | %-25s\n", 
//					c.getCode(), c.getName(), c.getInstructor());
//			
//		}
//		
//		
//		String inputLine = input.nextLine();
//		}catch(FileNotFoundException e){
//			System.out.println("No file here");
//		}
//		
		
		
//		System.out.println(file.isDirectory());
//		
//		// return a array of files
//		File [] FilewithPath = file.listFiles();
		
		
// functional programming 1 slide 3 - 6
		
		// stringJoiner 
//		
//		StringJoiner joinNames = new StringJoiner(",","[","]");
//		
//		// The add method from the StringJoiner 
//		joinNames.add("Erik");
//		joinNames.add("Brook");
//		joinNames.add("Yusuf");
//		joinNames.add("Sara");
//		
//		
//		StringJoiner joinName2 = new StringJoiner("-","(",")");
//		joinName2.add("Joe");
//		joinName2.add("Dana");
//		
//		
//		StringJoiner m = joinName2.merge(joinNames);
//		
//		System.out.println(m);
//		
//		StringBuffer sb = new StringBuffer("Core Java ");
//		sb.append("True");
//		sb.insert(3, "false");
//		sb.replace(0, 1, "jav");
//		sb.delete(0, 4);
//		sb.reverse();
//		System.out.println(sb);

            ArrayList<Integer> i = new ArrayList<>(1);
            System.out.println(i.size());

	}
}