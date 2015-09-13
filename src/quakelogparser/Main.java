package quakelogparser;

/*
 * QuakeLogParse - Main class
 * This class renders the application's menu and handles the selected options.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main {

	public static void main(String[] args) {
		Main.greet();
	}

	private static void greet() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select your Use Mode");
		System.out.println("'D' for GameLog Parse at the Default log location");
		System.out.println("'C' for GameLog Parse with a Customized log path");
		System.out.println("'R' for Player Rank at the Default log location");
		System.out.println("'P' for Player Rank with a Customized log path");
		System.out.println("'W' for Means of Death at the Default log location");
		System.out.println("'M' for Means of Death with a Customized log path");
		System.out.println("'J' for JUnit tests");
		System.out.println("'E' to Exit application");
		
		String option;
		try{
			option = br.readLine();
        }catch(IOException e){
        	option = "E"; //Default is default
        }
		
		
		switch(option) {
			case "D":
			case "d":
				Main.parser("default");
				break;
			case "C":
			case "c":
				Main.parser("custom");
				break;
			case "W":
			case "w":
				Main.weaponize("default");
				break;
			case "M":
			case "m":
				Main.weaponize("custom");
				break;
			case "R":
			case "r":
				Main.ranker("default");
				break;
			case "P":
			case "p":
				Main.ranker("custom");
				break;
			case "J":
			case "j":
				Main.jUnitRunner();
				break;
			case "E":
			case "e":
			default:
				return;
		}
	}
	
	private static void parser(String mode) {
		String file = "games.log";
		
		if (mode.equals("custom")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please insert the absolute path to your Log file:");
			try{
				file = br.readLine();
	        }catch(IOException e){
	        	file = "games.log"; //In case of problems, we go to the default file
	        }
		}
		
		//First, we load the texts from the .log file into a list of String
		// (each String is a line from the log)
		List<String> gameLog = GameLogBufferedReader.LogReader(file);		
		
		//Then, we parse the information from the log into a list of QuakeGame objects,
		// which contains relevant data extracted from the logs for each game
		List<QuakeGame> gamesData = QuakeLogParser.GameKillsLog(gameLog);
		
		//Finally, we read the data for each game and print it to the console
		QuakeDataPrinter.printGamesData(gamesData);
		
		Main.greet();
	}
	
	private static void weaponize(String mode) {
		String file = "games.log";
		
		if (mode.equals("custom")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please insert the absolute path to your Log file:");
			try{
				file = br.readLine();
	        }catch(IOException e){
	        	file = "games.log"; //In case of problems, we go to the default file
	        }
		}
		
		//First, we load the texts from the .log file into a list of String
		// (each String is a line from the log)
		List<String> gameLog = GameLogBufferedReader.LogReader(file);		
		
		//Then, we parse the information from the log into a list of QuakeGame objects,
		// which contains relevant data extracted from the logs for each game
		List<QuakeGame> gamesData = QuakeLogParser.GameKillsLog(gameLog);
		
		//Finally, we read the data for each game and print it to the console
		QuakeDataPrinter.printMODData(gamesData);
		
		Main.greet();
	}
	
	private static void ranker(String mode) {
		String file = "games.log";
		
		if (mode.equals("custom")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please insert the absolute path to your Log file:");
			try{
				file = br.readLine();
	        }catch(IOException e){
	        	file = "games.log"; //In case of problems, we go to the default file
	        }
		}
		
		//First, we load the texts from the .log file into a list of String
		// (each String is a line from the log)
		List<String> gameLog = GameLogBufferedReader.LogReader(file);		
		
		//Then, we parse the information from the log into a list of QuakeGame objects,
		// which contains relevant data extracted from the logs for each game
		List<QuakeGame> gamesData = QuakeLogParser.GameKillsLog(gameLog);
		
		//Now, we calculate the Ranks for all players
		Map<String, Integer> ranks = QuakeLogParser.overallRank(gamesData);
		
		//Finally, we output the calculated Ranks
		QuakeDataPrinter.printOverallRank(ranks);
		
		Main.greet();
	}
	
	private static void jUnitRunner() {
		//First, we setup a JUnit Core object
		JUnitCore jUnitCore = new JUnitCore();
		
		//Then, we run our test suite, AllTests.class
		Result result = jUnitCore.run(AllTests.class);
		
		//Finally, we present our results
		if(result.wasSuccessful()) {
			System.out.println("All " + result.getRunCount() + " tests were successful!");
		} else {
			///Not sure how to proceed upon finding a JUnit error
			/// this next part is not being printed
			System.out.println(result.getFailureCount() + " tests failed out of " + result.getRunCount());
		}
		System.out.println();
		Main.greet();
	}
}
