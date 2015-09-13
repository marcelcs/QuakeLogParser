package quakelogparser;

/*
 * QuakeLogParse - AllTests class
 * This particular class is our JUnit Tests Suite.
 * When called upon, it calls every listed Test Class
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	GameLogBufferedReaderTest.class,
	//QuakeDataPrinterTest.class, //test excluded - I could not make this work
	QuakeGameTest.class,
	QuakeLogParserTest.class,
	QuakePlayerTest.class,
	MeanOfDeathTest.class})
public class AllTests {
//actual class is empty
}
