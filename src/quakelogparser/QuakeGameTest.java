package quakelogparser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class QuakeGameTest {

	private List<QuakePlayer> pListSetup(int[] kills, int[] suicides){
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
	
	@Test
	public void testQuakeGame() {
		int[] kills = new int[]{1,2,3,4,5};
		int[] suicides = new int[]{5,4,3,2,1};
		List<QuakePlayer> players = pListSetup(kills, suicides);
		QuakeGame game = new QuakeGame(players);
		
		assertEquals(30, game.getTotalKills());
		
		List<String> players_strings = new ArrayList<String>();
		HashMap<String, Integer> kills_map = new HashMap<String, Integer>();
		for (int i=0 ; i<5 ; i++) {
			players_strings.add(String.valueOf(i));
			kills_map.put(players_strings.get(i), kills[i]-suicides[i]);
		}
		assertEquals(players_strings, game.getPlayers());
		assertEquals(kills_map, game.getKills());
	}

	@Test
	public void testGetTotalKills() {
		int[] kills0 = new int[]{0,0,0};
		int[] suicides0 = new int[]{0,0,0};
		int[] kills1 = new int[]{1,3,5};
		int[] suicides1 = new int[]{4,2,0};
		int[] kills2 = new int[]{0,0,0};
		int[] suicides2 = new int[]{1,3,5};
		int[] kills3 = new int[]{3,5,7};
		int[] suicides3 = new int[]{0,0,0};
		
		List<String> players_strings = new ArrayList<String>();
		
		List<QuakePlayer> players0 = pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = pListSetup(kills2, suicides2);
		List<QuakePlayer> players3 = pListSetup(kills3, suicides3);
		
		QuakeGame game0 = new QuakeGame(players0);
		QuakeGame game1 = new QuakeGame(players1);
		QuakeGame game2 = new QuakeGame(players2);
		QuakeGame game3 = new QuakeGame(players3);
		
		HashMap<String, Integer> kills_map0 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map1 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map2 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map3 = new HashMap<String, Integer>();
		
		for (int i=0 ; i<3 ; i++) {
			players_strings.add(String.valueOf(i));
			kills_map0.put(players_strings.get(i), kills0[i]-suicides0[i]);
			kills_map1.put(players_strings.get(i), kills1[i]-suicides1[i]);
			kills_map2.put(players_strings.get(i), kills2[i]-suicides2[i]);
			kills_map3.put(players_strings.get(i), kills3[i]-suicides3[i]);
		}
		
		assertEquals("testGetTotalKills Game0 failed!", 0, game0.getTotalKills());
		assertEquals("testGetTotalKills Game0 failed!", 15, game1.getTotalKills());
		assertEquals("testGetTotalKills Game0 failed!", 9, game2.getTotalKills());
		assertEquals("testGetTotalKills Game0 failed!", 15, game3.getTotalKills());
	}

	@Test
	public void testGetPlayers() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] kills2 = new int[]{1,2,3};
		int[] suicides2 = new int[]{3,2,1};
		
		List<QuakePlayer> players0 = pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = pListSetup(kills2, suicides2);
		
		QuakeGame game0 = new QuakeGame(players0);
		QuakeGame game1 = new QuakeGame(players1);
		QuakeGame game2 = new QuakeGame(players2);
		
		List<String> players_strings0 = new ArrayList<String>();
		List<String> players_strings1 = new ArrayList<String>();
		List<String> players_strings2 = new ArrayList<String>();
		
		players_strings1.add("0");
		for (int i=0 ; i<3 ; i++) {
			players_strings2.add(String.valueOf(i));
		}
		
		assertEquals(players_strings0, game0.getPlayers());
		assertEquals(players_strings1, game1.getPlayers());
		assertEquals(players_strings2, game2.getPlayers());
	}

	@Test
	public void testGetKills() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] kills2 = new int[]{0};
		int[] suicides2 = new int[]{1};
		int[] kills3 = new int[]{1};
		int[] suicides3 = new int[]{0};
		int[] kills4 = new int[]{2};
		int[] suicides4 = new int[]{2};
		
		List<QuakePlayer> players0 = pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = pListSetup(kills2, suicides2);
		List<QuakePlayer> players3 = pListSetup(kills3, suicides3);
		List<QuakePlayer> players4 = pListSetup(kills4, suicides4);
		
		QuakeGame game0 = new QuakeGame(players0);
		QuakeGame game1 = new QuakeGame(players1);
		QuakeGame game2 = new QuakeGame(players2);
		QuakeGame game3 = new QuakeGame(players3);
		QuakeGame game4 = new QuakeGame(players4);
		
		HashMap<String, Integer> kills_map0 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map1 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map2 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map3 = new HashMap<String, Integer>();
		HashMap<String, Integer> kills_map4 = new HashMap<String, Integer>();
		
		List<String> players_strings1 = new ArrayList<String>();
		List<String> players_strings2 = new ArrayList<String>();
		List<String> players_strings3 = new ArrayList<String>();
		List<String> players_strings4 = new ArrayList<String>();
		
		players_strings1.add("0");
		players_strings2.add("0");
		players_strings3.add("0");
		players_strings4.add("0");
		
		kills_map1.put(players_strings1.get(0), kills1[0]-suicides1[0]);
		kills_map2.put(players_strings2.get(0), kills2[0]-suicides2[0]);
		kills_map3.put(players_strings3.get(0), kills3[0]-suicides3[0]);
		kills_map4.put(players_strings4.get(0), kills4[0]-suicides4[0]);


		assertEquals(kills_map0, game0.getKills());
		assertEquals(kills_map1, game1.getKills());
		assertEquals(kills_map2, game2.getKills());
		assertEquals(kills_map3, game3.getKills());
		assertEquals(kills_map4, game4.getKills());
	}

}
