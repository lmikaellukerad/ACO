import java.util.ArrayList;
import java.util.HashSet;


public class Path extends Square {
	private double pheremone;
	private ArrayList<Path> neighbours;
	private ArrayList<Wall> surrounding;
	private boolean visited;

	public Path() {
		neighbours = new ArrayList<Path>();
		surrounding = new ArrayList<Wall>();
		visited = false;
	}
	
	public double getPheremone() {
		return pheremone;
	}
	
	public void setPheremone(double pheremone) {
		this.pheremone = pheremone;
	}
	
	public void decreasePheremone(double factor) {
		setPheremone(Math.max(1, pheremone * (1 - factor)));
	}
	
	public void increasePheremone(double factor) {
		setPheremone(pheremone + factor);
		//System.out.println(pheremone);
	}
	
	public String toString() {
		Point p = new Point(getX(), getY());
		return p.toString();
	}
	
	public void addNeighbour(Path path) {
		neighbours.add(path);
	}

	public ArrayList<Path> getNeighbours() {
		return neighbours;
	}

	public void addSurrounding(Wall wall) {
		surrounding.add(wall);
	}
	
	/**
	 * @return the surrounding
	 */
	public ArrayList<Wall> getSurrounding() {
		return surrounding;
	}

	/**
	 * @param surrounding the surrounding to set
	 */
	public void setSurrounding(ArrayList<Wall> surrounding) {
		this.surrounding = surrounding;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Path) {
			Path p = (Path) o;
			return (p.getX() == getX() && p.getY() == getY()); 
		}
		return false;
	}

}
