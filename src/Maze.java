import java.util.ArrayList;

public class Maze {

	private Square[][] maze;
	private ArrayList<Point> products;
	private Point start;
	private Point end;

	public Maze(Square[][] maze, Point start, Point end) {
		this.setMaze(maze);
		this.setStart(start);
		this.setEnd(end);
	}

	/**
	 * @return the maze
	 */
	public Square[][] getMaze() {
		return maze;
	}

	/**
	 * @param maze
	 *            the maze to set
	 */
	public void setMaze(Square[][] maze) {
		this.maze = maze;
	}

	/**
	 * @return the width of this board
	 */
	public int getWidth() {
		return maze.length;
	}

	/**
	 * @return the height of this board
	 */
	public int getHeight() {
		return maze[0].length;
	}

	/**
	 * @return the start
	 */
	public Point getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Point start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Point getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Point end) {
		this.end = end;
	}

	public ArrayList<Point> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Point> products) {
		this.products = products;
	}

	public Square squareAt(int x, int y) {
		return maze[x][y];
	}
	
	public Square squareAt(Point p) {
		return maze[p.getX()][p.getY()];
	}
	
	public Path startPath() {
		return (Path) squareAt(start);
	}
	
	public Path endPath() {
		return (Path) squareAt(end);
	}
	
	public void decreasePheremones(double factor) {
		for (Square[] m : maze) {
			for (Square s : m) {
				if (s instanceof Path) {
					Path p = (Path) s;
					p.decreasePheremone(factor);
				}
			}
		}
	}
}
