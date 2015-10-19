
/**
 * Class representing a point in 2d space.
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
		String s = "x: " + x + " y: " + y;
		return s;
	}

	/**
	 * @return the x
	 */
	protected final int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	protected final int getY() {
		return y;
	}

}
