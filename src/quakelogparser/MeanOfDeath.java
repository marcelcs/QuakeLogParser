package quakelogparser;

/*
 * QuakeLogParse - MeanOfDeath class
 * This is a class of objects that represent a Mean of Death, and its respective kill count.
 */

public class MeanOfDeath {
	private String meanOfDeath;	//ID of the Mean of Death
	private int kills;		//number of times this MoD was used to kill a player

	public MeanOfDeath(String name) {
		this.meanOfDeath = name;
		this.kills = 0;
	}
	
	public void score() {
		this.kills++;
	}

	
	public String getMOD() {
		return this.meanOfDeath;
	}
	
	public int getKills() {
		return this.kills;
	}
	
	//This is used in JUnit tests, comparing two MeanOfDeath and telling if they`re equals
	public boolean equals(Object other){
		//checking type compatibility
	    if((other == null) || (getClass() != other.getClass()))return false;
	    
	    //validating data equality
        MeanOfDeath otherMean = (MeanOfDeath)other;
        if (!meanOfDeath.equals(otherMean.getMOD())) return false;
        if (kills != otherMean.getKills()) return false;
        
        //upon meeting requirements, confirm True for this==other
	    return true;
	}
	
}
