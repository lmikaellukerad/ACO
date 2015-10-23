
public abstract class Square {
	private Point pos; 
	
	public final int getX() {
		return pos.getX();
	}

	public final void setX(int x) {
		pos.setX(x);
	}

	public final int getY() {
		return pos.getY();
	}

	public final void setY(int y) {
		pos.setY(y);
	}
	
	/**
	 * @return the pos
	 */
	public final Point getPos() {
		return pos;
	}
	
	/**
	 * 
	 * @param pos
	 */
	public final void setPos(Point pos) {
		this.pos = pos;
	}
}
