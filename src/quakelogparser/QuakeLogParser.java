package quakelogparser;

/*
 * QuakeLogParse - QuakeLogParser class
 * This class is responsible for the actual Parse step.
 */

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
	
	//Aux method, extracts the list of QuakePlayers from a map of Names and respective QuakePlayer objects
	public static List<QuakePlayer> generatePList (Map<String, QuakePlayer> players) {
		List<QuakePlayer> pList = new ArrayList<QuakePlayer>();
		
		for (Map.Entry<String, QuakePlayer> entry : players.entrySet()) {
			pList.add(entry.getValue());
		}
		
		return pList;
	}
	
	//Aux method, extracts the list of MoDs from a map of Names and respective MeanOfDeath objects
	public static List<MeanOfDeath> generateMODList (Map<String, MeanOfDeath> meansOfDeath) {
		List<MeanOfDeath> modList = new ArrayList<MeanOfDeath>();
		
		for (Map.Entry<String, MeanOfDeath> entry : meansOfDeath.entrySet()) {
			modList.add(entry.getValue());
		}
		
		return modList;
	}
	
	//Parse the list of Strings (the Log), looking for keywords and registering the
	// game's data into a QuakeGame object.
	public static List<QuakeGame> GameKillsLog(List<String> gameLog) {
		List<QuakeGame> games = new ArrayList<QuakeGame>();
		Map<String, QuakePlayer> players = new HashMap<String, QuakePlayer>();
		Map<String, MeanOfDeath> means = new HashMap<String, MeanOfDeath>();
		QuakePlayer player;
		MeanOfDeath mean;
		
		String[] words;
		String killer, killed, mod;
		int i;
		
		for (String line : gameLog) {
			words = line.split("\\s+");
			while(words[0].equals("")) {
				line = line.substring(1);
				words = line.split("\\s+");
			}
			if (words[1].equals("InitGame:")) {						//register the beginning of a game
				players.clear();
				means.clear();
			} else if (words[1].equals("ShutdownGame:")) {			//register the end of a game
				games.add(new QuakeGame(generatePList(players), generateMODList(means)));
			} else if (words[1].equals("ClientUserinfoChanged:")) { //register a new player
				String newPlayer = line.split("\\\\")[1];
				if (!players.containsKey(newPlayer)) {
					player = new QuakePlayer(newPlayer);
					players.put(newPlayer, player);
				}
			} else if (words[1].equals("Kill:")) {	//register the occurrence of a death event
				mod = words[words.length-1];
				if (!means.containsKey(mod)) {	//if the MOD was not yet registered, register it
					mean = new MeanOfDeath(mod);
					means.put(mod, mean);
				}
				means.get(mod).score();	//increment number of kills for the used Mean of Death
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

	//Parse the list of games registered from a Log, keeping track of scores for all players
	// across all games - compiling a global score for everyone and ranking them by scores
	public static Map<String, Integer> overallRank(List<QuakeGame> games) {
		HashMap<String, Integer> kills = new HashMap<String, Integer>();
		TreeMap<String, Integer> rank = new TreeMap<String, Integer>();
		String key;
		int score, currentScore;
		for (QuakeGame game : games) {
			kills = game.getKills();
			for (Map.Entry<String, Integer> entry : kills.entrySet()) {
				key = entry.getKey();
				score = entry.getValue();
				if (rank.containsKey(key)){
					currentScore = rank.get(key);
					rank.remove(key);
					rank.put(key, currentScore + score);
				} else {
					rank.put(key, score);
				}
			}
		}
		return sortByComparator(rank, false);
	}
	
	//Aux method, needed to rearrange for the overallRank method.
	//This method rearranges the order of elements in a map, sorted by the Integer value (the Score)
	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsorted, final boolean ascend) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsorted.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if (ascend) { //parse from smaller to greater values
						return o1.getValue().compareTo(o2.getValue());
					} else { //descend: parse from greater to smaller values
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
