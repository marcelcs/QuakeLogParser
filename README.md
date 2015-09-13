Quake Log Parser

#1 Technology
The entire application was developed using Java SE and JUnit on Eclipse (Kepler Release)

#2 Execution
There are 2 main ways to run this application
2.1- You can import the entire project into Eclipse and Run Main.java
2.2- You can compile via command line:
		javac -cp .;<absolute_path_to_junit> src/quakelogparser/*.java
	And then run the application with another command line:
		java -cp .\src;<absolute_path_to_junit>;<absolute_path_to_hamcrest> quakelogparser.Main
	Be aware that out of 19 JUnit tests, 3 are failing when you run Main from Console
	(all tests are working when called from the Eclipse Console)
Method 2.1 is strongly recommended	

#3 Running the Log
The application works directly in the Console. It presents a number of options,
 each with the possibility to use a default .log file or specify another, specific file 
There is a Default games.log file at the root folder, you can replace it if you want.
To use another .log file without having to replace the default file,
 it's recommended to have the file's absolute Path in the system's clipboard,
 so that you may paste it directly in the console when prompted.
 
#4 Features
There are four main features in the application:
4.1- game.log Parse, which reads a .log file and outputs the player kills information, per game
 (with options for Default or Custom files)
4.2- Player Rank, which reads a .log file and outputs the players global rankings
 (with options for Default or Custom files)
4.3- Means of Death, which reads a .log file and outputs MoD kills information, per game
 (with options for Default or Custom files)
4.4- JUnit tests, which executes the entire TestSuite at once.(Completely automatic)

#5 Application Architecture
- Main provides an interface of use, for both input and output.
The Main class also handles user options input by calling static methods featured in other classes.
- GameLogBufferedReader provides a static method to read the .log files
- QuakeDataPrinter provides static methods to handle data output to the Console
- QuakeLogParser provides static methods to analyze logs and compile organized data
These static methods work and communicate via instances or collections of objects of specific classes
- MeanOfDeath objects represent types of damage, and also counts the number of times a player died by it
- QuakePlayer objects represent each of the participants in a match, and also counts the number of opponents killed and the number of suicides
- QuakeGame objects stores data from game matches, such as names for each player, their scores, means of death that occurred (and how many times) and total number of kills that occurred

#About
QuakeLogParser is the result of my work as a candidate for BackEnd Developer job at Codeminer 42
Feel free to contact me at marcel.c.santos@gmail.com - or by phone: +55(19)981-893-138