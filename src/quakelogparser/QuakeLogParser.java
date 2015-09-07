package quakelogparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuakeLogParser {
	
	public static List<QuakePlayer> generatePList (Map<String, QuakePlayer> players) {
		List plist = new ArrayList<QuakePlayer>();
		
		for (Map.Entry<String, QuakePlayer> entry : players.entrySet()) {
			plist.add(entry.getValue());
		}
		
		return plist;
	}
	
	public static List<QuakeGame> GameKillsLog(List<String> game_log) {
		Map<String, QuakePlayer> players = new HashMap<String, QuakePlayer>();
		List<QuakeGame> games = new ArrayList<QuakeGame>();
		QuakePlayer player;
		
		String[] words;
		String killer, killed;
		int i;
		
		for (String line : game_log) {
			words = line.split("\\s+");
			while(words[0].equals("")) {
				line = line.substring(1);
				words = line.split("\\s+");
			}
			if (words[1].equals("InitGame:")) {												
				players.clear();
			} else if (words[1].equals("ShutdownGame:")) {					
				games.add(new QuakeGame(generatePList(players)));
			} else if (words[1].equals("Kill:")) {
				killer = words[5];
				for (i=6; !words[i].equals("killed"); i++) {					
					killer += " " + words[i];
				}
				killed = words[++i];
				for (i++ ; !words[i].equals("by"); i++) {
					killed += " " + words[i];
				}
				if (killer.equals("<world>")) {				//if the <world> killed a player, register suicide (subtract 1 point in player score)
					if (players.containsKey(killed)) {		//if player is already registered, get it and update its status
						players.get(killed).suicide();						
					} else {								//if player is not yet registered, create it, update it and register it in the parser
						player = new QuakePlayer(killed);						
						player.suicide();
						players.put(killed, player);
					}
				} else {									//if a player kills another, the killer earns a point					
					if (players.containsKey(killer)) {		//if player is already registered, get it and update its status
						players.get(killer).score();
					} else {								//if player is not yet registered, create it, update it and register it in the parser
						player = new QuakePlayer(killer);						
						player.score();
						players.put(killer, player);
					}
				}
			}
		}
		return games;
	}
}
