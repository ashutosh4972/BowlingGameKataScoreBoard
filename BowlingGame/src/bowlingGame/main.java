package bowlingGame;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		bowling bowlingGame = new bowling();
		Scanner scanner = new Scanner(System.in);
		
		//throws upto 9th frame
		for(int frame = 0 ; frame < 9 ; frame++) {
			System.out.println("Frame" + (frame + 1));
			//Validating each throw
			int firstThrow = bowlingGame.validateFirstThrow("First", scanner);
			bowlingGame.throwball(firstThrow);
			if(firstThrow < 10) {
				int secondThrow = bowlingGame.validateSecondThrow(firstThrow, scanner);
				bowlingGame.throwball(secondThrow);
			} else {
				System.out.println("Strike:X");
			}
		}
		
		//10th frame : Spare and strike
		System.out.println("This is your lsat frame - 10th frame");
		
		int lastFrameFirstThrow = bowlingGame.validateFirstThrow("Final First" , scanner);
		bowlingGame.throwball(lastFrameFirstThrow);
		
		//If first ball is strike throw next two balls	
		if(lastFrameFirstThrow == 10) {
			System.out.println("Strike:X");
			int secondFinalThrow = bowlingGame.validateFirstThrow("Final Second" ,scanner);
			bowlingGame.throwball(secondFinalThrow);
			
			if(secondFinalThrow <= 10) {
				int thirdThrow = bowlingGame.validateFirstThrow("Final Third" ,scanner);
				bowlingGame.throwball(thirdThrow);
			}
			
		}
		
		//If its spare throw the third ball
		else {
			int lastFrameFinalThrow = bowlingGame.validateSecondThrow(lastFrameFirstThrow, scanner);
			bowlingGame.throwball(lastFrameFinalThrow);
			
			if(lastFrameFirstThrow + lastFrameFinalThrow == 10) {
				
				System.out.println("Spare:/");
				int thirdThrow = bowlingGame.validateFirstThrow("Final Third" , scanner);
				bowlingGame.throwball(thirdThrow);
				
			} else {
				System.out.println("Game Over!");
			}
			
		}
		
		bowlingGame.getScore();
		scanner.close();
	}	
}
