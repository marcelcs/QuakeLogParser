package quakelogparser;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuakePlayerTest {
	
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

	@Test
	public void testScoreAndGetKills() {
		QuakePlayer tester = new QuakePlayer("tester");
		int i, score = tester.getKills();
		for(i=1 ; i<10 ; i++) {
			tester.score();
			assertEquals(tester.getKills(), score+i);
		}
	}

	@Test
	public void testSuicideAndSuicideCount() {
		QuakePlayer tester = new QuakePlayer("tester");	
		int suicides = tester.suicideCount();
		for(int i=1 ; i<10 ; i++) {
			tester.suicide();
			assertEquals(tester.suicideCount(), suicides+i);
		}
	}

	@Test
	public void testGetName() {
		String name = "Not a name";
		QuakePlayer named = new QuakePlayer(name);
		assertEquals(named.getName(), name);
	}

}
