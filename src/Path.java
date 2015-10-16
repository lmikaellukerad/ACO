import java.util.ArrayList;


public class Path extends Square {
	private double pheremone;
	private ArrayList<Path> neighbours;


	public Path() {
		neighbours = new ArrayList<Path>();
	}
	
	public double getPheremone() {
		return pheremone;
	}
	
	public void setPheremone(double pheremone) {
		this.pheremone = pheremone;
	}
	
	public void decreasePheremone(double factor) {
		setPheremone(Math.max(0, getPheremone() - factor));
	}
	
	public void print() {
		System.out.println("Path: " + getX() + ", " + getY());
	}
	
	public void printNeighbours() {
		System.out.println("Path: " + getX() + ", " + getY() + " neighbours:");
		for (Path p : neighbours) {
			p.print();
		}
	}
	
	public void addNeighbour(Path path) {
		neighbours.add(path);
	}

	public ArrayList<Path> getNeighbours() {
		return neighbours;
	}

}
