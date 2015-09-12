package quakelogparser;

/*
 * QuakeLogParse - QuakePlayer class
 * This is a class of objects that represent a Quake Player and
 * its respective name and counter for both kills and suicides
 */

public class QuakePlayer {	
	private String playerName; //the nickname for the player
	private int rawKills; //number of times this player has killed another player
	private int suicides; //number of times this player was killed by the environment (NOT by other players)

	public QuakePlayer(String name) {
		this.playerName = name;
		this.rawKills = 0;
		this.suicides = 0;
	}
	
	public void score() {
		this.rawKills++;
	}
	
	public void suicide() {
		this.suicides++;
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public int getKills() {
		return this.rawKills;
	}
	
	public int suicideCount() {
		return this.suicides;
	}
	
	//This is used in JUnit tests, comparing two QuakePlayer objects and telling if they`re equals
	public boolean equals(Object other){
		//checking type compatibility
	    if((other == null) || (getClass() != other.getClass()))return false;
	    
	    //validating data equality
        QuakePlayer otherPlayer = (QuakePlayer)other;
        if (!playerName.equals(otherPlayer.getName())) return false;
        if (rawKills != otherPlayer.getKills()) return false;
        if (suicides != otherPlayer.suicideCount()) return false;
        
        //upon meeting requirements, confirm True for this==other
	    return true;
	}
}
