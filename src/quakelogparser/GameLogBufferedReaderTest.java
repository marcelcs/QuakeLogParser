package quakelogparser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GameLogBufferedReaderTest {

	@Test
	public void testEmptyFile() {
		List<String> empty_log = GameLogBufferedReader.LogReader("TestResources/empty.log");
		assertTrue("List should be empty!", empty_log.isEmpty());
	}
	
	@Test
	public void testOnThenOff() {
		List<String> onoff_log = GameLogBufferedReader.LogReader("TestResources/OnOff.log");
		assertTrue("List should have size 2", onoff_log.size()==2);
		assertEquals(	"First line should be GameInit",
						"  0:00 InitGame:",
						onoff_log.get(0));
		assertEquals(	"Second line should be ShutdownGame",
						" 23:59 ShutdownGame:",
						onoff_log.get(0));
}

}
