package quakelogparser;

/*
 * QuakeLogParse - GameLogBufferedReader class
 * This class is responsible for the reading of .log files.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogBufferedReader {
	
	//LogReader outputs an ArrayList of Strings - Each String is a line from the .log file.
	public static List<String> LogReader(String filename) {
		//preparing a list of Strings
		List<String> log = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			
			//reading line from the file
			while ((sCurrentLine = br.readLine()) != null) {
				//adding line as a single String to the list
				log.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return log;
	}
}
