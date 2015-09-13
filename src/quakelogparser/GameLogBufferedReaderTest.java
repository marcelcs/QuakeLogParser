package quakelogparser;

/*
 * QuakeLogParse - GameLogBufferedReaderTest class
 * This class contains tests for the GameLogBufferedReader methods
 * This test reads a few preset files and verify the List of Strings the tested class outputs
 */

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class GameLogBufferedReaderTest {

	//If a file is empty, LogReader should return an empty list
	@Test
	public void testEmptyFile() {
		List<String> emptyLog = GameLogBufferedReader.LogReader("TestResources/empty.log");
		assertTrue("List should be empty!", emptyLog.isEmpty());
	}
	
	//If a file has lines, each line should be placed in the list as a separate String
	@Test
	public void testOnThenOff() {
		List<String> onOffLog = GameLogBufferedReader.LogReader("TestResources/onOff.log");
		assertTrue("List should have size 2", onOffLog.size()==2);
		assertEquals(	"First line should be GameInit",
						"  0:00 InitGame:",
						onOffLog.get(0));
		assertEquals(	"Second line should be ShutdownGame",
						" 23:59 ShutdownGame:",
						onOffLog.get(1));
	}
}
