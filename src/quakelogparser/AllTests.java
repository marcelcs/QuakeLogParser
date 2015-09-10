package quakelogparser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	GameLogBufferedReaderTest.class,
	//QuakeDataPrinterTest.class,
	QuakeGameTest.class,
	QuakeLogParserTest.class,
	QuakePlayerTest.class })
public class AllTests {
//actual class is empty
}
