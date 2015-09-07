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
}
