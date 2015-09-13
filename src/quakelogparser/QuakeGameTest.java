package quakelogparser;

/*
 * QuakeLogParse - QuakeGameTest class
 * This class contains tests for QuakeGame objects and its methods
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class QuakeGameTest {
	
	//Contructor test: object creation and initial state
	@Test
	public void testQuakeGame() {
		int[] kills = new int[]{1,2,3,4,5};
		int[] suicides = new int[]{5,4,3,2,1};
		int[] weaponize = new int[]{15,15};
		List<QuakePlayer> players = TestTools.pListSetup(kills, suicides);
		List<MeanOfDeath> means = TestTools.modListSetup(weaponize);
		QuakeGame game = new QuakeGame(players, means);
		
		assertEquals(30, game.getTotalKills());
		
		List<String> playersStrings = new ArrayList<String>();
		HashMap<String, Integer> killsMap = new HashMap<String, Integer>();
		for (int i=0 ; i<5 ; i++) {
			playersStrings.add(String.valueOf(i));
			killsMap.put(playersStrings.get(i), kills[i]-suicides[i]);
		}
		assertEquals(playersStrings, game.getPlayers());
		assertEquals(killsMap, game.getKills());
		
		List<String> meansStrings = new ArrayList<String>();
		HashMap<String, Integer> killsByMOD = new HashMap<String, Integer>();
		for (int i=0 ; i<2 ; i++) {
			meansStrings.add(String.valueOf(i));
			killsByMOD.put(meansStrings.get(i), weaponize[i]);
		}
		assertEquals(meansStrings, game.getMODs());
		assertEquals(killsByMOD, game.getKillsByMODs());
	}

	//GetTotalKills test: mock some game match scenarios and test QuakeGame's ability to calculate
	//global kills count
	@Test
	public void testGetTotalKills() {
		int[] kills0 = new int[]{0,0,0};
		int[] suicides0 = new int[]{0,0,0};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{1,3,5};
		int[] suicides1 = new int[]{4,2,0};
		int[] weaponize1 = new int[]{6,9};
		int[] kills2 = new int[]{0,0,0};
		int[] suicides2 = new int[]{1,3,5};
		int[] weaponize2 = new int[]{9};
		int[] kills3 = new int[]{3,5,7};
		int[] suicides3 = new int[]{0,0,0};
		int[] weaponize3 = new int[]{3,5,7};
		
		//Setting up players for each test
		List<QuakePlayer> players0 = TestTools.pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = TestTools.pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = TestTools.pListSetup(kills2, suicides2);
		List<QuakePlayer> players3 = TestTools.pListSetup(kills3, suicides3);
		
		//Setting up MoDs for each test
		List<MeanOfDeath> means0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> means1 = TestTools.modListSetup(weaponize1);
		List<MeanOfDeath> means2 = TestTools.modListSetup(weaponize2);
		List<MeanOfDeath> means3 = TestTools.modListSetup(weaponize3);
		
		//Setting up games
		QuakeGame game0 = new QuakeGame(players0, means0);
		QuakeGame game1 = new QuakeGame(players1, means1);
		QuakeGame game2 = new QuakeGame(players2, means2);
		QuakeGame game3 = new QuakeGame(players3, means3);

		//Checking TotalKills values
		assertEquals("testGetTotalKills Game0 failed!", 0, game0.getTotalKills());
		assertEquals("testGetTotalKills Game1 failed!", 15, game1.getTotalKills());
		assertEquals("testGetTotalKills Game2 failed!", 9, game2.getTotalKills());
		assertEquals("testGetTotalKills Game3 failed!", 15, game3.getTotalKills());
	}

	//GetPlayers test: mock some game scenarios and check if generated names are correctly registered
	// in the QuakeGame objects
	@Test
	public void testGetPlayers() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] weaponize1 = new int[] {};
		int[] kills2 = new int[]{1,2,3};
		int[] suicides2 = new int[]{3,2,1};
		int[] weaponize2 = new int[]{6,6};
		
		List<QuakePlayer> players0 = TestTools.pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = TestTools.pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = TestTools.pListSetup(kills2, suicides2);
		List<MeanOfDeath> means0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> means1 = TestTools.modListSetup(weaponize1);
		List<MeanOfDeath> means2 = TestTools.modListSetup(weaponize2);
		
		QuakeGame game0 = new QuakeGame(players0, means0);
		QuakeGame game1 = new QuakeGame(players1, means1);
		QuakeGame game2 = new QuakeGame(players2, means2);
		
		List<String> playersStrings0 = new ArrayList<String>();
		List<String> playersStrings1 = new ArrayList<String>();
		List<String> playersStrings2 = new ArrayList<String>();
		
		playersStrings1.add("0");
		for (int i=0 ; i<3 ; i++) {
			playersStrings2.add(String.valueOf(i));
		}
		
		assertEquals(playersStrings0, game0.getPlayers());
		assertEquals(playersStrings1, game1.getPlayers());
		assertEquals(playersStrings2, game2.getPlayers());
	}

	//GetMODs test: mock some game scenarios and check if the generated names are correctly registered
	// in the QuakeGame objects
	@Test
	public void testGetMODs() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] weaponize1 = new int[]{};
		int[] kills2 = new int[]{1,2,3};
		int[] suicides2 = new int[]{3,2,1};
		int[] weaponize2 = new int[]{6,6};
		
		List<QuakePlayer> players0 = TestTools.pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = TestTools.pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = TestTools.pListSetup(kills2, suicides2);
		List<MeanOfDeath> means0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> means1 = TestTools.modListSetup(weaponize1);
		List<MeanOfDeath> means2 = TestTools.modListSetup(weaponize2);
		
		QuakeGame game0 = new QuakeGame(players0, means0);
		QuakeGame game1 = new QuakeGame(players1, means1);
		QuakeGame game2 = new QuakeGame(players2, means2);
		
		List<String> meansStrings0 = new ArrayList<String>();
		List<String> meansStrings1 = new ArrayList<String>();
		List<String> meansStrings2 = new ArrayList<String>();
		
		meansStrings2.add("0");
		meansStrings2.add("1");
		
		assertEquals(meansStrings0, game0.getMODs());
		assertEquals(meansStrings1, game1.getMODs());
		assertEquals(meansStrings2, game2.getMODs());
	}

	//GetKills test: mock some game scenarios and check if the QuakeGame objects are correctly creating
	// the mapping for Player Names and their respective Scores
	@Test
	public void testGetKills() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] weaponize1 = new int[]{};
		int[] kills2 = new int[]{0};
		int[] suicides2 = new int[]{1};
		int[] weaponize2 = new int[]{1};
		int[] kills3 = new int[]{1};
		int[] suicides3 = new int[]{0};
		int[] weaponize3 = new int[]{1};
		int[] kills4 = new int[]{2};
		int[] suicides4 = new int[]{2};
		int[] weaponize4 = new int[]{2,2};
		
		List<QuakePlayer> players0 = TestTools.pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = TestTools.pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = TestTools.pListSetup(kills2, suicides2);
		List<QuakePlayer> players3 = TestTools.pListSetup(kills3, suicides3);
		List<QuakePlayer> players4 = TestTools.pListSetup(kills4, suicides4);
		List<MeanOfDeath> means0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> means1 = TestTools.modListSetup(weaponize1);
		List<MeanOfDeath> means2 = TestTools.modListSetup(weaponize2);
		List<MeanOfDeath> means3 = TestTools.modListSetup(weaponize3);
		List<MeanOfDeath> means4 = TestTools.modListSetup(weaponize4);
		
		QuakeGame game0 = new QuakeGame(players0, means0);
		QuakeGame game1 = new QuakeGame(players1, means1);
		QuakeGame game2 = new QuakeGame(players2, means2);
		QuakeGame game3 = new QuakeGame(players3, means3);
		QuakeGame game4 = new QuakeGame(players4, means4);
		
		HashMap<String, Integer> killsMap0 = new HashMap<String, Integer>();
		HashMap<String, Integer> killsMap1 = new HashMap<String, Integer>();
		HashMap<String, Integer> killsMap2 = new HashMap<String, Integer>();
		HashMap<String, Integer> killsMap3 = new HashMap<String, Integer>();
		HashMap<String, Integer> killsMap4 = new HashMap<String, Integer>();
		
		List<String> playersStrings1 = new ArrayList<String>();
		List<String> playersStrings2 = new ArrayList<String>();
		List<String> playersStrings3 = new ArrayList<String>();
		List<String> playersStrings4 = new ArrayList<String>();
		
		playersStrings1.add("0");
		playersStrings2.add("0");
		playersStrings3.add("0");
		playersStrings4.add("0");
		
		killsMap1.put(playersStrings1.get(0), kills1[0]-suicides1[0]);
		killsMap2.put(playersStrings2.get(0), kills2[0]-suicides2[0]);
		killsMap3.put(playersStrings3.get(0), kills3[0]-suicides3[0]);
		killsMap4.put(playersStrings4.get(0), kills4[0]-suicides4[0]);

		assertEquals(killsMap0, game0.getKills());
		assertEquals(killsMap1, game1.getKills());
		assertEquals(killsMap2, game2.getKills());
		assertEquals(killsMap3, game3.getKills());
		assertEquals(killsMap4, game4.getKills());
	}

	//GetKillsByMODs test: mock some game scenarios and check if the QuakeGame objects are correctly
	//creating the mapping for Means of Death and the number of times they were inflicted
	@Test
	public void testGetKillsByMODs() {
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{0};
		int[] weaponize1 = new int[]{};
		int[] kills2 = new int[]{0};
		int[] suicides2 = new int[]{1};
		int[] weaponize2 = new int[]{1};
		int[] kills3 = new int[]{1};
		int[] suicides3 = new int[]{0};
		int[] weaponize3 = new int[]{1};
		int[] kills4 = new int[]{2};
		int[] suicides4 = new int[]{2};
		int[] weaponize4 = new int[]{2,2};
		
		List<QuakePlayer> players0 = TestTools.pListSetup(kills0, suicides0);
		List<QuakePlayer> players1 = TestTools.pListSetup(kills1, suicides1);
		List<QuakePlayer> players2 = TestTools.pListSetup(kills2, suicides2);
		List<QuakePlayer> players3 = TestTools.pListSetup(kills3, suicides3);
		List<QuakePlayer> players4 = TestTools.pListSetup(kills4, suicides4);
		List<MeanOfDeath> means0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> means1 = TestTools.modListSetup(weaponize1);
		List<MeanOfDeath> means2 = TestTools.modListSetup(weaponize2);
		List<MeanOfDeath> means3 = TestTools.modListSetup(weaponize3);
		List<MeanOfDeath> means4 = TestTools.modListSetup(weaponize4);
		
		QuakeGame game0 = new QuakeGame(players0, means0);
		QuakeGame game1 = new QuakeGame(players1, means1);
		QuakeGame game2 = new QuakeGame(players2, means2);
		QuakeGame game3 = new QuakeGame(players3, means3);
		QuakeGame game4 = new QuakeGame(players4, means4);
		
		HashMap<String, Integer> modsMap0 = new HashMap<String, Integer>();
		HashMap<String, Integer> modsMap1 = new HashMap<String, Integer>();
		HashMap<String, Integer> modsMap2 = new HashMap<String, Integer>();
		HashMap<String, Integer> modsMap3 = new HashMap<String, Integer>();
		HashMap<String, Integer> modsMap4 = new HashMap<String, Integer>();
		
		List<String> meansStrings2 = new ArrayList<String>();
		List<String> meansStrings3 = new ArrayList<String>();
		List<String> meansStrings4 = new ArrayList<String>();
		
		meansStrings2.add("0");
		meansStrings3.add("0");
		meansStrings4.add("0");
		meansStrings4.add("1");
		
		modsMap2.put(meansStrings2.get(0), weaponize2[0]);
		modsMap3.put(meansStrings3.get(0), weaponize3[0]);
		modsMap4.put(meansStrings4.get(0), weaponize4[0]);
		modsMap4.put(meansStrings4.get(1), weaponize4[1]);

		assertEquals(modsMap0, game0.getKillsByMODs());
		assertEquals(modsMap1, game1.getKillsByMODs());
		assertEquals(modsMap2, game2.getKillsByMODs());
		assertEquals(modsMap3, game3.getKillsByMODs());
		assertEquals(modsMap4, game4.getKillsByMODs());
	}

}
