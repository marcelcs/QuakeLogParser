package quakelogparser;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class QuakeDataPrinter {

	public static void printData(List<QuakeGame> games) {
		PrintWriter pw = new PrintWriter(System.out);
		int counter = 0; // used to track the current game's number
		int aux;
		int number_of_players;
		String tab = "   "; // used for identation
		String line; // used to compose each line that we will print
		
		for (QuakeGame game : games) {
			//composing the first line, "game_X: {"
			line = "game_" + (++counter) + ": {";
			pw.println(line);
			
			//composing the second line, "total_kills: XX;"
			line = tab + "total_kills: " + game.getTotalKills() + ";";
			pw.println(line);
			
			//composing the third line, with a list of players
			line = tab + "players: [";
			for (String player_name : game.getPlayers()) {
				line += '"' + player_name + '"' + ',';
			}
			if (game.getPlayers().size()>0) line = line.substring(0, line.length()-1);
			line += "]";
			pw.println(line);
			
			//composing fourth line
			line = tab + "kills: {";
			pw.print(line);
			
			//composing list of player-score pairs
			number_of_players = game.getPlayers().size();
			if (number_of_players > 0) { pw.println(); }
			aux = 0;
			for (Map.Entry<String, Integer> entry : game.getKills().entrySet()) {
				line = tab + tab + '"' + entry.getKey() + '"' + ": " + entry.getValue();
				if ((++aux)<number_of_players) {line += ",";}
				pw.println(line);
			}
			
			//closing player-score list
			if (aux > 0) {
				pw.println(tab + "}");
			} else {
				pw.println(" }");
			}
			
			//closing log for this particular game
			pw.println("}");
		}
		
		pw.flush();
		pw.close();
	}
	
}
