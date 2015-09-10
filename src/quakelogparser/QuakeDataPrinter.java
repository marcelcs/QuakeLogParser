package quakelogparser;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class QuakeDataPrinter {

	public static void printGamesData(List<QuakeGame> games) {
		int counter = 0; // used to track the current game's number
		int i;
		int number_of_players;
		String tab = "   "; // used for indentation
		String line; // used to compose each line that we will print
		String full = ""; // used to store the complete message that we will print at the end
		
		for (QuakeGame game : games) {
			//composing the first line, "game_X: {"
			line = "game_" + (++counter) + ": {\n";
			full+=line;
			
			//composing the second line, "total_kills: XX;"
			line = tab + "total_kills: " + game.getTotalKills() + ";\n";
			full+=line;
			
			//composing the third line, with a list of players
			line = tab + "players: [";
			for (String player_name : game.getPlayers()) {
				line += '"' + player_name + '"' + ',';
			}
			if (game.getPlayers().size()>0) line = line.substring(0, line.length()-1);
			line += "]\n";
			full+=line;
			
			//composing fourth line
			line = tab + "kills: {";
			full+=line;
			
			//composing list of player-score pairs
			number_of_players = game.getPlayers().size();
			if (number_of_players > 0) {
				full+="\n";
			}
			i = 0;
			for (Map.Entry<String, Integer> entry : game.getKills().entrySet()) {
				line = tab + tab + '"' + entry.getKey() + '"' + ": " + entry.getValue();
				if ((++i)<number_of_players) {line += ",\n";}
				full+=line;
			}
			
			//closing player-score list
			if (i > 0) {
				full+="\n" + tab + "}\n";
			} else {
				full+=" }\n";
			}
			
			//closing log for this particular game
			full+="}\n";
		}
		System.out.println(full);
	}
	
	public static void printOverallRank(Map<String, Integer> ranks) {
		int counter = 0; // used to track the current player's position/number
		String tab = "   "; // used for indentation
		String line; // used to compose each line that we will print
		String full = ""; // used to store the complete message that we will print at the end
		
		full += "Overall Player Rank\n";
		for (Map.Entry<String, Integer> player : ranks.entrySet()) {
			line = tab + "Player #";
			line += String.valueOf(++counter);
			line += ": ";
			line += '"' + player.getKey() + '"';
			line += " (" + player.getValue() + " kills)\n";
			full += line;
		}
		
		System.out.println(full);
	}
}
