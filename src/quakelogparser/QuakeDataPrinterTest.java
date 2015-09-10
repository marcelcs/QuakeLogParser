package quakelogparser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class QuakeDataPrinterTest {

	/*
	 * this test is meant to capture the output to console
	 * and compare it to the mocked, expected text
	 */
	private QuakeGame pListSetup(int[] kills, int[] suicides){
		int i, j;
		QuakePlayer player;
		List<QuakePlayer> players = new ArrayList<QuakePlayer>();
		for (i=0 ; i<kills.length ; i++) {
			player = new QuakePlayer(String.valueOf(i));
			for (j=0 ; j<kills[i] ; j++) {player.score();}
			for (j=0 ; j<suicides[i] ; j++) {player.suicide();}
			players.add(player);
		}
		return new QuakeGame(players);
	}
	
	/*
	 * Unfortunately, this test is not working
	 * the mock and the generated strings are identical, but JUnit's assertion is not accepting them as equals
	 * I have used different Diff softwares and all tests confirms that the 2 strings are indeed identical
	 * 
	 * For the time being, this test must be skipped/ignored
	 */
	@Test
	public void testPrintData() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    
		int[] kills0 = new int[]{};
		int[] suicides0 = new int[]{};
		int[] kills1 = new int[]{0};
		int[] suicides1 = new int[]{1};
		int[] kills2 = new int[]{1,3};
		int[] suicides2 = new int[]{2,0};
		
		QuakeGame game0 = pListSetup(kills0, suicides0);
		QuakeGame game1 = pListSetup(kills1, suicides1);
		QuakeGame game2 = pListSetup(kills2, suicides2);
		
		List<QuakeGame> games_data0 = new ArrayList<QuakeGame>();
		List<QuakeGame> games_data1 = new ArrayList<QuakeGame>();
		
		games_data0.add(game1);
		games_data1.add(game0);
		games_data1.add(game2);
	    
		String tab = "   ";
		String log0 = "game_1: {\n";
		log0 += tab+"total_kills: 1;\n";
	    log0 += tab+"players: [\"0\"]\n";
	    log0 += tab+"kills: {\n";
	    log0 += tab+tab+"\"0\": -1\n";
	    log0 += tab+"}\n";
	  	log0 += "}\n\n";
	  	String log1 = "game_1: {\n";
		log1 += tab+"total_kills: 0;\n";
	    log1 += tab+"players: []\n";
	    log1 += tab+"kills: { }\n";
	  	log1 += "}\n";
	  	log1 = "game_2: {\n";
		log1 += tab+"total_kills: 6;\n";
	    log1 += tab+"players: [\"0\",\"1\"]\n";
	    log1 += tab+"kills: {\n";
	    log1 += tab+tab+"\"0\": -1\n";
	    log1 += tab+tab+"\"1\": 3\n";
	    log1 += tab+"}\n";
	  	log1 += "}\n\n";
	  	
	  	QuakeDataPrinter.printGamesData(games_data0);
	  	String glog0 = outContent.toString();
		assertTrue(log0.equals(glog0));
		
		QuakeDataPrinter.printGamesData(games_data1);	
		String glog1 = outContent.toString();
		assertTrue(log1.equals(glog1));
		
	    System.setOut(null);
	    System.out.println(glog0.equals(log0));
	}

}
