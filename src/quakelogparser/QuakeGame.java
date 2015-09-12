package quakelogparser;

/*
 * QuakeLogParse - QuakeGame class
 * This class represents a QuakeGame and all its meaningful data.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuakeGame {
	private int totalKills;							//global number of kills that occurred during the match
	private List<String> players;					//list of player names
	private List<String> means;						//list of MoD names that were present
	private HashMap<String, Integer> kills;			//map of player names and their respective scores
	private HashMap<String, Integer> killsByMeans;	//map of MoD names and the number of times they were inflicted
	
	public QuakeGame(List<QuakePlayer> playerList, List<MeanOfDeath> modList) {
		this.players = new ArrayList<String>();
		this.means = new ArrayList<String>();
		this.kills = new HashMap<String, Integer>();
		this.killsByMeans = new HashMap<String, Integer>();
		this.totalKills = 0;
		
		for(QuakePlayer player : playerList) {
			this.players.add(player.getName());
			this.kills.put(player.getName(), player.getKills() - player.suicideCount());
			this.totalKills += player.getKills() + player.suicideCount();
		}
		
		for(MeanOfDeath mean : modList) {
			this.means.add(mean.getMOD());
			this.killsByMeans.put(mean.getMOD(), mean.getKills());
		}
	}
	
	public int getTotalKills() {
		return this.totalKills;
	}
	
	public List<String> getPlayers() {
		return this.players;
	}
	
	public List<String> getMODs() {
		return this.means;
	}
	
	public HashMap<String, Integer> getKills() {
		return this.kills;
	}
	
	public HashMap<String, Integer> getKillsByMODs() {
		return this.killsByMeans;
	}
}
