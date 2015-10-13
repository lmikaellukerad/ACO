
public class Path extends Square {
	private double pheremone;
	
	public double getPheremone() {
		return pheremone;
	}
	
	public void setPheremone(double pheremone) {
		this.pheremone = pheremone;
	}
	
	public void decreasePheremone(double factor) {
		setPheremone(Math.max(0, getPheremone() - factor));
	}
}
