package quakelogparser;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		//First, we load the texts from the .log file into a list of Strings (each String is a line from the log)
		List<String> game_log = GameLogBufferedReader.LogReader("games.log");		
		
		//Then, we parse the information from the log into a list of QuakeGame objects,
		// which contains relevant data extracted from the logs for each game
		List<QuakeGame> games_data = QuakeLogParser.GameKillsLog(game_log);
		
		//Finally, we read the data for each game and print it to the console
		QuakeDataPrinter.printData(games_data);
	}

}
