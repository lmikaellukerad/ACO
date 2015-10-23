
/**
 * Object representing a point in 2d space.
 * @author Luke
 *
 */
public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		String s = "(" + x + ", " + y + ")";
		return s;
	}

	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}
	
	/**
	 * 
	 */
	public final boolean equals(Object other) {
		if (other instanceof Point) {
			Point o = (Point) other;
			return (o.getX() == getX() && o.getY() == getY());
		}
		return false;
	}

}
