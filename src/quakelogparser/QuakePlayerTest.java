package quakelogparser;

/*
 * QuakeLogParse - QuakePlayerTest class
 * This class contains tests for the QuakePlayer object and its methods
 * The tests consists of creating a QuakePlayer object and using its methods to check the behavior
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class QuakePlayerTest {
	
	//Constructor test: Object creation and initial state
	@Test
	public void testQuakePlayer() {
		QuakePlayer tester = new QuakePlayer("tester");
		assertEquals(	"Name should be 'tester'",
						tester.getName(),
						"tester");
		assertEquals(	"Initial score should be 0",
						tester.getKills(),
						0);
		assertEquals(	"Initial suicide count should be 0",
						tester.suicideCount(),
						0);
	}

	//Score test: call score() a number of times, verifying the player's kill count
	@Test
	public void testScoreAndGetKills() {
		QuakePlayer tester = new QuakePlayer("tester");
		int i, score = tester.getKills();
		for(i=1 ; i<10 ; i++) {
			tester.score();
			assertEquals(tester.getKills(), score+i);
		}
	}

	//Suicide test: call suicide() a number of times, verifying the object's suicide count
	@Test
	public void testSuicideAndSuicideCount() {
		QuakePlayer tester = new QuakePlayer("tester");	
		int suicides = tester.suicideCount();
		for(int i=1 ; i<10 ; i++) {
			tester.suicide();
			assertEquals(tester.suicideCount(), suicides+i);
		}
	}

	//GetName test: verify the name of the QuakePlayer object
	@Test
	public void testGetName() {
		String name = "Not a name";
		QuakePlayer named = new QuakePlayer(name);
		assertEquals(named.getName(), name);
	}

}
