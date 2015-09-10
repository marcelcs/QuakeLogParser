package quakelogparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class QuakeLogParser {
	
	public static List<QuakePlayer> generatePList (Map<String, QuakePlayer> players) {
		List<QuakePlayer> plist = new ArrayList<QuakePlayer>();
		
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
			if (words[1].equals("InitGame:")) {						//register the beginning of a game
				players.clear();
			} else if (words[1].equals("ShutdownGame:")) {			//register the end of a game
				games.add(new QuakeGame(generatePList(players)));
			} else if (words[1].equals("ClientUserinfoChanged:")) { //register a new player
				String new_player = line.split("\\\\")[1];
				if (!players.containsKey(new_player)) {
					player = new QuakePlayer(new_player);
					players.put(new_player, player);
				}
			} else if (words[1].equals("Kill:")) {
				killer = words[5];							//with this, we will build the killer's name
				for (i=6; !words[i].equals("killed"); i++) {					
					killer += " " + words[i];
				}
				killed = words[++i];						//with this, we will build the killed player's name
				for (i++ ; !words[i].equals("by"); i++) {
					killed += " " + words[i];
				}
				if (killer.equals("<world>")) {				//if the <world> killed a player, register suicide (subtract 1 point in player score)
						players.get(killed).suicide();						
				} else {									//if a player kills another, the killer earns a point
						players.get(killer).score();
				}
			}
		}
		return games;
	}

	public static Map<String, Integer> overallRank(List<QuakeGame> games) {
		HashMap<String, Integer> kills = new HashMap<String, Integer>();
		TreeMap<String, Integer> rank = new TreeMap<String, Integer>();
		String key;
		int score, current_score;
		for (QuakeGame game : games) {
			kills = game.getKills();
			for (Map.Entry<String, Integer> entry : kills.entrySet()) {
				key = entry.getKey();
				score = entry.getValue();
				if (rank.containsKey(key)){
					current_score = rank.get(key);
					rank.remove(key);
					rank.put(key, current_score + score);
				} else {
					rank.put(key, score);
				}
			}
		}
		return sortByComparator(rank, false);
	}
	
	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsorted, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsorted.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if (order) {
						return o1.getValue().compareTo(o2.getValue());
					} else {
						return o2.getValue().compareTo(o1.getValue());
					}
				}
		});
		
		Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sorted.put(entry.getKey(), entry.getValue());
		}
		return sorted;
	}

}
