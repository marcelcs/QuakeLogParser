package quakelogparser;

/*
 * QuakeLogParse - TestTools class
 * This class contains common methods used to generate QuakeGame games and
 * other useful collections 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTools {

	//This method receives 2 vectors with #kills and #suicides for all players
	// and parse them into a HashMap of Strings and QuakePlayer objects
	protected static Map<String, QuakePlayer> pMapSetup(int[] kills, int[] suicides){
		int i, j;
		QuakePlayer player;
		Map<String, QuakePlayer> playersDict = new HashMap<String, QuakePlayer>();
		for (i=0 ; i<kills.length ; i++) {
			player = new QuakePlayer(String.valueOf(i));
			for (j=0 ; j<kills[i] ; j++) {player.score();}
			for (j=0 ; j<suicides[i] ; j++) {player.suicide();}
			playersDict.put(String.valueOf(i), player);
		}
		return playersDict;
	}
	
	//This method receives 2 vectors with #kills and #suicides for all players
	// and parse them into an ArrayList of QuakePlayer objects
	protected static List<QuakePlayer> pListSetup(int[] kills, int[] suicides){
		int i, j;
		QuakePlayer player;
		List<QuakePlayer> players = new ArrayList<QuakePlayer>();
		for (i=0 ; i<kills.length ; i++) {
			player = new QuakePlayer(String.valueOf(i));
			for (j=0 ; j<kills[i] ; j++) {player.score();}
			for (j=0 ; j<suicides[i] ; j++) {player.suicide();}
			players.add(player);
		}
		return players;
	}
	
	//This method receives a vector with scores per weapon
	//and parse them into a HashMap of Strings and MeanOfDeath objects
	protected static Map<String, MeanOfDeath> modMapSetup(int[] weaponize){
		int i, j;
		MeanOfDeath mean;
		Map<String, MeanOfDeath> modsDict = new HashMap<String, MeanOfDeath>();
		for (i=0 ; i<weaponize.length ; i++) {
			mean = new MeanOfDeath(String.valueOf(i));
			for (j=0 ; j<weaponize[i] ; j++) {mean.score();}
			modsDict.put(String.valueOf(i), mean);
		}
		return modsDict;
	}
	
	//This method receives a vector with scores perweapon
	//and parse them into an ArrayList of MeanOfDeath objects
	protected static List<MeanOfDeath> modListSetup(int[] weaponize){
		int i, j;
		MeanOfDeath mean;
		List<MeanOfDeath> means = new ArrayList<MeanOfDeath>();
		for (i=0 ; i<weaponize.length ; i++) {
			mean = new MeanOfDeath(String.valueOf(i));
			for (j=0 ; j<weaponize[i] ; j++) {mean.score();}
			means.add(mean);
		}
		return means;
	}
}
