package bowlingGame;

import java.util.Scanner;

public class bowling {
	
	private int[] frameCount = new int[21];
	private int throwCount = 0;
	
	//When player throws a ball this method is called and it validates the input
	public void throwball(int pins) {
		if(pins < 0 || pins > 10) {
			throw new IllegalArgumentException("Invalid nuber of pins");
		}
		
		if(throwCount >= 21) {
			throw new IllegalArgumentException("Thank you for playing! Game Over.");
		}
		
		frameCount[throwCount++] = pins;
	}
	
	
	//To check if the throw is strike or not
	private boolean isStrike(int roll) {
		return frameCount[roll] == 10;
	}
	
	//To check if the throw is spare or not
	private boolean isSpare(int roll) {
		return frameCount[roll] + frameCount[roll + 1] == 10;
	}
	
	//If strike: Calculate next two throws
	private int strikePoints(int roll) {
		return frameCount[roll + 1] + frameCount[roll+2];
	}
	
	//If spare: Calculate next frame first throw
	private int sparePoints(int roll) {
		return frameCount[roll + 2];
	}
	
	//To calculate final score and display score of all 10 frames
	public void getScore() {
		int frame = 1;
		int roll_number = 0;
		int totalScore = 0;
		System.out.println("Frame\tRoll 1\tRoll 2\tTotal Score ");
		while(frame <= 10) {
			int roll1 = frameCount[roll_number];
			int roll2 = frameCount[roll_number + 1];
			int total = getFrameTotal(frame);
			
			if(frame == 10 && (isSpare(roll_number))) {
				int roll3 = frameCount[roll_number + 2];
				//Total += roll3
				total += sparePoints(roll_number + 2);
				totalScore = total;
				System.out.println(frame + "\t" + roll1 + "\t" + roll2 + "\t" + roll3 + "\t" + total);
				break;
			} 
			
			if(frame == 10 && (isStrike(roll_number))) {
				int roll3 = frameCount[roll_number+2];
				//total += roll3
				total += strikePoints(roll_number+2);
				totalScore = total;
				System.out.println(frame + "\t" + roll1 + "\t" + roll2 + "\t" + roll3 + "\t" + total);
				break;
			}
			
			if(isStrike(roll_number)) {
				System.out.println(frame+ "\tX\t\t\t" + total );
				roll_number++;
			} else if(isSpare(roll_number)) {
				System.out.println(frame+ "\t" + roll1 + "\t/\t\t" + total);
				roll_number += 2;
			} else {
				System.out.println(frame + "\t" + roll1 + "\t" + roll2 + "\t\t" + total);
				roll_number += 2;
			}
			
			frame++;
			totalScore = total;
			
		}
		
		System.out.println("Final Score is : " + totalScore);
	}
	
	//to get total of each frame
	public int getFrameTotal(int frame) {
		int total = 0;
		int roll_number = 0;
		for(int i = 0 ; i < frame ; i++) {
			if(isStrike(roll_number)) {
				total+=10 + strikePoints(roll_number);
				roll_number++;
			} else if(isSpare(roll_number)) {
				total+=10 + sparePoints(roll_number);
				roll_number++;
			} else {
				total += frameCount[roll_number] + frameCount[roll_number + 1] ;
				roll_number += 2;
			}
		}
		
		return total;
	}
	
	//To validate pins knocked down in first throw
	public int validateFirstThrow(String throwCount , Scanner scanner) {
		int firstThrow = 0;
		boolean valid = false;
		System.out.println("Enter the pins knocked down in " + throwCount + "throw:");
		firstThrow = scanner.nextInt();
		if(firstThrow >= 0 && firstThrow <= 10) {
			valid = true;
		} else {
			System.out.println("Invalid number entered. Please enter number between 0 and 10");
		}
		
		return firstThrow;
	}
	
	//To validate pins knocked down in second throw
	public int validateSecondThrow(int throwTwo, Scanner scanner) {
		int secondThrow = 0;
		boolean valid = false;
		System.out.println("Enter the pins knocked down in second throw: ");
		secondThrow = scanner.nextInt();
		if(secondThrow >= 0 && secondThrow <= (10- throwTwo)) {
			valid = true;
 		} else {
 			System.out.println("Invalid Throw! Please enter pin knocked down between 0 to " + (10 - throwTwo));
 		}
		
		return secondThrow;
	}
	

}
