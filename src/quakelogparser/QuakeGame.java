package quakelogparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuakeGame {
	private int total_kills;
	private List<String> players;
	private HashMap<String, Integer> kills;
	
	public QuakeGame(List<QuakePlayer> player_list) {
		this.players = new ArrayList<String>();
		this.kills = new HashMap<String, Integer>();
		this.total_kills = 0;
		
		for(QuakePlayer player : player_list) {
			this.players.add(player.getName());
			this.kills.put(player.getName(), player.getKills() - player.suicideCount());
			this.total_kills += player.getKills() + player.suicideCount();
		}
	}
	
	public int getTotalKills() {
		return this.total_kills;
	}
	
	public List<String> getPlayers() {
		return this.players;
	}
	
	public HashMap<String, Integer> getKills() {
		return this.kills;
	}
}
