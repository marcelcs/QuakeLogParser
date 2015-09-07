package quakelogparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogBufferedReader {
	public static List<String> LogReader(String filename) {
		List<String> log = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
						
			while ((sCurrentLine = br.readLine()) != null) {
				log.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return log;
	}
}
