package com.example.demoTDDTesting;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class DemoTddTestingApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoTddTestingApplication.class, args);
                
                Player p1 = new Player();
                p1.setPlayerName("Erik");
                
                Game g1 = new Game();
                g1.add(p1);
                
                System.out.println(g1.getPlayerList());
                g1.getWinner();
	}

}
