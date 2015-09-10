package quakelogparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class QuakeLogParserTest {

	//This method receives 2 vectors with #kills and #suicides for all players
	//and parse them into a HashMap of Strings and QuakePlayer objects
	private Map<String, QuakePlayer> pMapSetup(int[] kills, int[] suicides){
		int i, j;
		QuakePlayer player;
		Map<String, QuakePlayer> players_dict = new HashMap<String, QuakePlayer>();
		for (i=0 ; i<kills.length ; i++) {
			player = new QuakePlayer(String.valueOf(i));
			for (j=0 ; j<kills[i] ; j++) {player.score();}
			for (j=0 ; j<suicides[i] ; j++) {player.suicide();}
			players_dict.put(String.valueOf(i), player);
		}
		return players_dict;
	}
	
	//This method receives 2 vectors with #kills and #suicides for all players
	//and parse them into an ArrayList of QuakePlayer objects
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
	public void testGeneratePList() {
		int[] no_kills = new int[]{};
		int[] no_suicides = new int[]{};
		int[] kills0 = new int[]{0,0,0};
		int[] suicides0 = new int[]{0,0,0};
		int[] kills1 = new int[]{1,3,5};
		int[] suicides1 = new int[]{4,2,0};
		int[] kills2 = new int[]{0,0,0};
		int[] suicides2 = new int[]{1,3,5};
		int[] kills3 = new int[]{3,5,7};
		int[] suicides3 = new int[]{0,0,0};
		
		Map<String, QuakePlayer> empty_pdict = pMapSetup(no_kills,no_suicides);
		List<QuakePlayer> empty_plist = pListSetup(no_kills,no_suicides);
		Map<String, QuakePlayer> players_dict0 = pMapSetup(kills0,suicides0);
		List<QuakePlayer> plist0 = pListSetup(kills0,suicides0);
		Map<String, QuakePlayer> players_dict1 = pMapSetup(kills1,suicides1);
		List<QuakePlayer> plist1 = pListSetup(kills1,suicides1);
		Map<String, QuakePlayer> players_dict2 = pMapSetup(kills2,suicides2);
		List<QuakePlayer> plist2 = pListSetup(kills2,suicides2);
		Map<String, QuakePlayer> players_dict3 = pMapSetup(kills3,suicides3);
		List<QuakePlayer> plist3 = pListSetup(kills3,suicides3);
		
		int i;
		
		assertEquals(empty_plist,QuakeLogParser.generatePList(empty_pdict));
		List<QuakePlayer> gen_list0 = QuakeLogParser.generatePList(players_dict0);
		for (i=0 ; i<plist0.size() ; i++){	assertEquals(plist0.get(i),gen_list0.get(i));}
		List<QuakePlayer> gen_list1 = QuakeLogParser.generatePList(players_dict1);
		for (i=0 ; i<plist1.size() ; i++){	assertEquals(plist1.get(i),gen_list1.get(i));}
		List<QuakePlayer> gen_list2 = QuakeLogParser.generatePList(players_dict2);
		for (i=0 ; i<plist1.size() ; i++){	assertEquals(plist2.get(i),gen_list2.get(i));}
		List<QuakePlayer> gen_list3 = QuakeLogParser.generatePList(players_dict3);
		for (i=0 ; i<plist3.size() ; i++){	assertEquals(plist3.get(i),gen_list3.get(i));}
	}

	@Test
	public void testGameKillsLog() {
		//player data for each game
		int[] no_kills = new int[]{};
		int[] no_suicides = new int[]{};
		int[] kills0 = new int[]{0,0};
		int[] suicides0 = new int[]{0,0};
		int[] kills1 = new int[]{0,1,2};
		int[] suicides1 = new int[]{2,1,0};
		
		//player object lists
		List<QuakePlayer> empty_plist = pListSetup(no_kills,no_suicides);
		List<QuakePlayer> plist0 = pListSetup(kills0,suicides0);
		List<QuakePlayer> plist1 = pListSetup(kills1,suicides1);
		
		//game objects
		QuakeGame empty_game = new QuakeGame(empty_plist);
		QuakeGame game0 = new QuakeGame(plist0);
		QuakeGame game1 = new QuakeGame(plist1);
		
		//game lists
		List<QuakeGame> glist0 = new ArrayList<QuakeGame>();
		List<QuakeGame> glist1 = new ArrayList<QuakeGame>();
		glist0.add(empty_game);
		glist1.add(game0);
		glist1.add(game1);
		
		//string lists for GameKillsLog
		List<String> slist0 = new ArrayList<String>(); //this one should be empty (aka Have no Game Data)
		slist0.add("  0:00 InitGame: /sv_no_data_for_no_game/");
		slist0.add("Loren ipsum et cetera");
		slist0.add("  0:02 ShutdownGame:");
		List<String> slist1 = new ArrayList<String>(); //this one should contain the logs for game0 and game1
		//part of the log for game0
		slist1.add(" 10:30 InitGame: /sv_somedata_that_we_really_dont_care/");
		slist1.add(" 10:34 ClientUserinfoChanged: 2 n\\0\\t\\whatever\\");
		slist1.add(" 10:35 ClientUserinfoChanged: 3 n\\1\\t\\who cares\\");
		slist1.add(" 10:40 ShutdownGame:");
		//part of the log for game1
		slist1.add(" 14:00 InitGame: /sv_some_other_data_that_we_dont_care/");
		slist1.add(" 14:13 ClientUserinfoChanged: 2 n\\0\\t\\WHATson\\");
		slist1.add(" 14:14 ClientUserinfoChanged: 3 n\\1\\t\\dr who\\");
		slist1.add(" 14:15 ClientUserinfoChanged: 4 n\\2\\t\\dumbledore\\");
		slist1.add(" 14:16 Kill: 1022 2 22: <world> killed 0 by MOD_TRIGGER_HURT");
		slist1.add(" 14:18 Kill: 2 3 7: 1 killed 0 by MOD_RAILGUN");
		slist1.add(" 14:19 Kill: 2 3 7: 2 killed 1 by MOD_ROCKET_SPLASH");
		slist1.add(" 14:22 Kill: 1022 2 22: <world> killed 0 by MOD_TRIGGER_HURT");
		slist1.add(" 14:25 Kill: 2 3 7: 2 killed 0 by MOD_ROCKET");
		slist1.add(" 14:28 Kill: 1022 2 22: <world> killed 1 by MOD_TRIGGER_HURT");
		slist1.add(" 14:30 ShutdownGame:");
		
		//generating Games lists from our mocked strings lists
		List<QuakeGame> gen_glist0 = QuakeLogParser.GameKillsLog(slist0);
		List<QuakeGame> gen_glist1 = QuakeLogParser.GameKillsLog(slist1);
		
		//checking if content for mocked and generated game in the list are the same
		assertEquals(gen_glist0.get(0).getTotalKills(), glist0.get(0).getTotalKills());
		assertEquals(gen_glist0.get(0).getPlayers(), glist0.get(0).getPlayers());
		assertTrue(gen_glist0.get(0).getKills().equals(glist0.get(0).getKills()));
		
		//making the same checks, but for each game in the list
		QuakeGame mock, generated;	
		for (int i=0 ; i<2 ; i++) {
			mock = glist1.get(i);
			generated = gen_glist1.get(i);
			assertEquals(mock.getTotalKills(),generated.getTotalKills());
			assertEquals(mock.getPlayers(),generated.getPlayers());
			assertTrue(mock.getKills().equals(generated.getKills()));
		}
	}
	
	@Test
	public void testOverallRank() {
		//player data for each game
		int[] kills0 = new int[]{0,4};
		int[] suicides0 = new int[]{0,1};
		int[] kills1 = new int[]{0,1,2};
		int[] suicides1 = new int[]{2,1,0};
		//preparing player lists
		List<QuakePlayer> plist0 = pListSetup(kills0,suicides0);
		List<QuakePlayer> plist1 = pListSetup(kills1,suicides1);
		//preparing quake games
		QuakeGame game0 = new QuakeGame(plist0);
		QuakeGame game1 = new QuakeGame(plist1);
		//preparing the game list
		List<QuakeGame> glist = new ArrayList<QuakeGame>();
		glist.add(game0);
		glist.add(game1);
		
		//composing expected rank
		Map<String, Integer> rank = new LinkedHashMap<String, Integer>();
		rank.put("1", 3);
		rank.put("2", 2);
		rank.put("0", -2);
		//running overallRank
		Map<String, Integer> gen_rank = QuakeLogParser.overallRank(glist);
		
		assertEquals(rank.keySet(),gen_rank.keySet());
		for(Map.Entry<String, Integer> entry : rank.entrySet()) {
			assertEquals(entry.getValue(),gen_rank.get(entry.getKey()));
		}
		
	}
}
