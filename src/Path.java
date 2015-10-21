import java.util.ArrayList;


public class Path extends Square {
	private double pheremone;
	private ArrayList<Path> neighbours;
	private boolean visited;

	public Path() {
		neighbours = new ArrayList<Path>();
		visited = false;
	}
	
	public double getPheremone() {
		return pheremone;
	}
	
	public void setPheremone(double pheremone) {
		this.pheremone = pheremone;
	}
	
	public void decreasePheremone(double factor) {
		setPheremone(Math.max(0.1, pheremone * (1 - factor)));
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

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean isIntersection() {
		if (neighbours.size() > 2) {
			return true;
		}
		return false;
	}
	
	public boolean isClosed() {
		if (neighbours.size() < 2) {
			return true;
		}
		return false;
	}
	
	public boolean isOpen() {
		for (Path p : neighbours) {
			if ((p.getNeighbours().size() - 1) < 3) {
				return false;
			}
		}
		return isIntersection();
	}
	
	public boolean equals(Object o) {
		if (o instanceof Path) {
			Path p = (Path) o;
			return (p.getX() == getX() && p.getY() == getY()); 
		}
		return false;
	}

}
