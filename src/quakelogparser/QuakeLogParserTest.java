package quakelogparser;

/*
 * QuakeLogParse - QuakeLogParserTest class
 * This class contains tests for the QuakeLogParser methods
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class QuakeLogParserTest {

	//GeneratePList test: mock some game match scenarios and test QuakeLogParser's ability to generate
	// a list of QuakePlayer objects out of a name-player HashMap
	@Test
	public void testGeneratePList() {
		int[] noKills = new int[]{};
		int[] noSuicides = new int[]{};
		int[] kills0 = new int[]{0,0,0};
		int[] suicides0 = new int[]{0,0,0};
		int[] kills1 = new int[]{1,3,5};
		int[] suicides1 = new int[]{4,2,0};
		int[] kills2 = new int[]{0,0,0};
		int[] suicides2 = new int[]{1,3,5};
		int[] kills3 = new int[]{3,5,7};
		int[] suicides3 = new int[]{0,0,0};
		
		Map<String, QuakePlayer> emptyPDict = TestTools.pMapSetup(noKills,noSuicides);
		List<QuakePlayer> emptyPList = TestTools.pListSetup(noKills,noSuicides);
		Map<String, QuakePlayer> playersDict0 = TestTools.pMapSetup(kills0,suicides0);
		List<QuakePlayer> pList0 = TestTools.pListSetup(kills0,suicides0);
		Map<String, QuakePlayer> playersDict1 = TestTools.pMapSetup(kills1,suicides1);
		List<QuakePlayer> pList1 = TestTools.pListSetup(kills1,suicides1);
		Map<String, QuakePlayer> playersDict2 = TestTools.pMapSetup(kills2,suicides2);
		List<QuakePlayer> pList2 = TestTools.pListSetup(kills2,suicides2);
		Map<String, QuakePlayer> playersDict3 = TestTools.pMapSetup(kills3,suicides3);
		List<QuakePlayer> pList3 = TestTools.pListSetup(kills3,suicides3);
		
		int i;
		
		assertEquals(emptyPList,QuakeLogParser.generatePList(emptyPDict));
		List<QuakePlayer> genList0 = QuakeLogParser.generatePList(playersDict0);
		for (i=0 ; i<pList0.size() ; i++){	assertEquals(pList0.get(i),genList0.get(i));}
		List<QuakePlayer> genList1 = QuakeLogParser.generatePList(playersDict1);
		for (i=0 ; i<pList1.size() ; i++){	assertEquals(pList1.get(i),genList1.get(i));}
		List<QuakePlayer> genList2 = QuakeLogParser.generatePList(playersDict2);
		for (i=0 ; i<pList1.size() ; i++){	assertEquals(pList2.get(i),genList2.get(i));}
		List<QuakePlayer> genList3 = QuakeLogParser.generatePList(playersDict3);
		for (i=0 ; i<pList3.size() ; i++){	assertEquals(pList3.get(i),genList3.get(i));}
	}

	//GenerateMODList test: mock some game match scenarios and test QuakeLogParser's ability to generate
	// a list of MeanOfDeath objects out of a name-MoD HashMap
	@Test
	public void testGenerateMODList() {
		int[] noWeapons = new int[]{};
		int[] weaponize1 = new int[]{3};
		int[] weaponize2 = new int[]{6,9};
		
		Map<String, MeanOfDeath> emptyMODDict = TestTools.modMapSetup(noWeapons);
		List<MeanOfDeath> emptyMODList = TestTools.modListSetup(noWeapons);
		Map<String, MeanOfDeath> modDict1 = TestTools.modMapSetup(weaponize1);
		List<MeanOfDeath> modList1 = TestTools.modListSetup(weaponize1);
		Map<String, MeanOfDeath> modDict2 = TestTools.modMapSetup(weaponize2);
		List<MeanOfDeath> modList2 = TestTools.modListSetup(weaponize2);
		
		int i;
		
		assertEquals(emptyMODList,QuakeLogParser.generateMODList(emptyMODDict));
		List<MeanOfDeath> genList1 = QuakeLogParser.generateMODList(modDict1);
		for (i=0 ; i<modList1.size() ; i++){assertEquals(modList1.get(i),genList1.get(i));}
		List<MeanOfDeath> genList2 = QuakeLogParser.generateMODList(modDict2);
		for (i=0 ; i<modList2.size() ; i++){assertEquals(modList2.get(i),genList2.get(i));}
	}
	
	//GameKillsLog test: mock some game match scenarios and test QuakeLogParser's ability to detect
	// keywords in a list of strings and organize meaningful data in a way that makes sense
	@Test
	public void testGameKillsLog() {
		//player data for each game
		int[] noKills = new int[]{};
		int[] noSuicides = new int[]{};
		int[] noWeapons = new int[]{};
		int[] kills0 = new int[]{0,0};
		int[] suicides0 = new int[]{0,0};
		int[] weaponize0 = new int[]{};
		int[] kills1 = new int[]{0,1,2};
		int[] suicides1 = new int[]{2,1,0};
		int[] weaponize1 = new int[]{3,3};
		
		//player object lists
		List<QuakePlayer> emptyPList = TestTools.pListSetup(noKills,noSuicides);
		List<QuakePlayer> pList0 = TestTools.pListSetup(kills0,suicides0);
		List<QuakePlayer> pList1 = TestTools.pListSetup(kills1,suicides1);
		
		//player object lists
		List<MeanOfDeath> emptyMODList = TestTools.modListSetup(noWeapons);
		List<MeanOfDeath> modList0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> modList1 = TestTools.modListSetup(weaponize1);
		
		//game objects
		QuakeGame emptyGame = new QuakeGame(emptyPList, emptyMODList);
		QuakeGame game0 = new QuakeGame(pList0, modList0);
		QuakeGame game1 = new QuakeGame(pList1, modList1);
		
		//game lists
		List<QuakeGame> gList0 = new ArrayList<QuakeGame>();
		List<QuakeGame> gList1 = new ArrayList<QuakeGame>();
		gList0.add(emptyGame);
		gList1.add(game0);
		gList1.add(game1);
		
		//string lists for GameKillsLog
		List<String> sList0 = new ArrayList<String>(); //this one should be empty (aka Have no Game Data)
		sList0.add("  0:00 InitGame: /sv_no_data_for_no_game/");
		sList0.add("Loren ipsum et cetera");
		sList0.add("  0:02 ShutdownGame:");
		List<String> sList1 = new ArrayList<String>(); //this one should contain the logs for game0 and game1
		//part of the log for game0
		sList1.add(" 10:30 InitGame: /sv_somedata_that_we_really_dont_care/");
		sList1.add(" 10:34 ClientUserinfoChanged: 2 n\\0\\t\\whatever\\");
		sList1.add(" 10:35 ClientUserinfoChanged: 3 n\\1\\t\\who cares\\");
		sList1.add(" 10:40 ShutdownGame:");
		//part of the log for game1
		sList1.add(" 14:00 InitGame: /sv_some_other_data_that_we_dont_care/");
		sList1.add(" 14:13 ClientUserinfoChanged: 2 n\\0\\t\\WHATson\\");
		sList1.add(" 14:14 ClientUserinfoChanged: 3 n\\1\\t\\dr who\\");
		sList1.add(" 14:15 ClientUserinfoChanged: 4 n\\2\\t\\dumbledore\\");
		sList1.add(" 14:16 Kill: 1022 2 22: <world> killed 0 by 0");
		sList1.add(" 14:18 Kill: 2 3 7: 1 killed 0 by 1");
		sList1.add(" 14:19 Kill: 2 3 7: 2 killed 1 by 1");
		sList1.add(" 14:22 Kill: 1022 2 22: <world> killed 0 by 0");
		sList1.add(" 14:25 Kill: 2 3 7: 2 killed 0 by 1");
		sList1.add(" 14:28 Kill: 1022 2 22: <world> killed 1 by 0");
		sList1.add(" 14:30 ShutdownGame:");
		
		//generating Games lists from our mocked strings lists
		List<QuakeGame> genGList0 = QuakeLogParser.GameKillsLog(sList0);
		List<QuakeGame> genGList1 = QuakeLogParser.GameKillsLog(sList1);
		
		//checking if content for mocked and generated game in the list are the same
		assertEquals(genGList0.get(0).getTotalKills(), gList0.get(0).getTotalKills());
		assertEquals(genGList0.get(0).getPlayers(), gList0.get(0).getPlayers());
		assertEquals(genGList0.get(0).getMODs(), gList0.get(0).getMODs());
		assertTrue(genGList0.get(0).getKills().equals(gList0.get(0).getKills()));
		assertTrue(genGList0.get(0).getKillsByMODs().equals(gList0.get(0).getKillsByMODs()));
		
		//making the same checks, but for each game in the list
		QuakeGame mock, generated;	
		for (int i=0 ; i<2 ; i++) {
			mock = gList1.get(i);
			generated = genGList1.get(i);
			assertEquals(mock.getTotalKills(),generated.getTotalKills());
			assertEquals(mock.getPlayers(),generated.getPlayers());
			assertEquals(mock.getMODs(), generated.getMODs());
			assertTrue(mock.getKills().equals(generated.getKills()));
			assertTrue(mock.getKillsByMODs().equals(generated.getKillsByMODs()));
		}
	}
	
	//OverallRank test: mock some game match scenarios and test QuakeLogParser's ability to analyze
	// a list of games, calculate global scores for each player across the games and organize the
	// compiled data in a descendant, sorted order
	@Test
	public void testOverallRank() {
		//player data for each game
		int[] kills0 = new int[]{0,4};
		int[] suicides0 = new int[]{0,1};
		int[] weaponize0 = new int[]{1,4};
		int[] kills1 = new int[]{0,1,2};
		int[] suicides1 = new int[]{2,1,0};
		int[] weaponize1 = new int []{3,3};
		//preparing player lists
		List<QuakePlayer> pList0 = TestTools.pListSetup(kills0,suicides0);
		List<QuakePlayer> pList1 = TestTools.pListSetup(kills1,suicides1);
		//preparing MoDs lists
		List<MeanOfDeath> modList0 = TestTools.modListSetup(weaponize0);
		List<MeanOfDeath> modList1 = TestTools.modListSetup(weaponize1);
		//preparing quake games
		QuakeGame game0 = new QuakeGame(pList0, modList0);
		QuakeGame game1 = new QuakeGame(pList1, modList1);
		//preparing the game list
		List<QuakeGame> gList = new ArrayList<QuakeGame>();
		gList.add(game0);
		gList.add(game1);
		
		//composing expected rank
		Map<String, Integer> rank = new LinkedHashMap<String, Integer>();
		rank.put("1", 3);
		rank.put("2", 2);
		rank.put("0", -2);
		//running overallRank
		Map<String, Integer> genRank = QuakeLogParser.overallRank(gList);
		
		assertEquals(rank.keySet(),genRank.keySet());
		for(Map.Entry<String, Integer> entry : rank.entrySet()) {
			assertEquals(entry.getValue(),genRank.get(entry.getKey()));
		}
		
	}
}
