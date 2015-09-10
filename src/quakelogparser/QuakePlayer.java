package quakelogparser;

public class QuakePlayer {	
	private String player_name;
	private int raw_kills;
	private int suicides;

	public QuakePlayer(String name) {
		this.player_name = name;
		this.raw_kills = 0;
		this.suicides = 0;
	}
	
	public void score() {
		this.raw_kills++;
	}
	
	public void suicide() {
		this.suicides++;
	}
	
	public String getName() {
		return this.player_name;
	}
	
	public int getKills() {
		return this.raw_kills;
	}
	
	public int suicideCount() {
		return this.suicides;
	}
	
	public boolean equals(Object other){
		//checking type compatibility
	    if((other == null) || (getClass() != other.getClass()))return false;
	    
	    //validating data equality
        QuakePlayer other_player = (QuakePlayer)other;
        if (!player_name.equals(other_player.getName())) return false;
        if (raw_kills != other_player.getKills()) return false;
        if (suicides != other_player.suicideCount()) return false;
        
        //upon meeting requirements, confirm True for this==other
	    return true;
	}
}
