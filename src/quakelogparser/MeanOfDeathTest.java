package quakelogparser;

/*
 * QuakeLogParse - MeanOfDeathTest class
 * This class contains tests for the MeanOfDeath methods
 * The tests consists of creating a MoD object and using its methods to check the behavior
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class MeanOfDeathTest {
	
	//Constructor test: Object creation and initial state
	@Test
	public void testMeanOfDeath() {
		MeanOfDeath mean = new MeanOfDeath("mean");
		assertEquals(	"Name should be 'mean'",
						mean.getMOD(),
						"mean");
		assertEquals(	"Initial kill count should be 0",
						mean.getKills(),
						0);
	}

	//Score test: call score() a number of times, verifying the object's kill count
	@Test
	public void testScore() {
		MeanOfDeath mean = new MeanOfDeath("mean");
		int i, score = mean.getKills();
		for(i=1 ; i<10 ; i++) {
			mean.score();
			assertEquals(mean.getKills(), score+i);
		}
	}

	//GetMOD test: verify the name of the MoD object
	@Test
	public void testGetMOD() {
		String name = "Not a name";
		QuakePlayer named = new QuakePlayer(name);
		assertEquals(named.getName(), name);
	}

}
