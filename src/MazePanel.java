import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazePanel extends JPanel {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final int SQUARE_SIZE = 9;
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private final boolean drawAnts = false;
	private Colony maze;

	public MazePanel(final Colony colony) {
		super();
		this.maze = colony;

		int w = maze.getMaze().getWidth() * SQUARE_SIZE;
		int h = maze.getMaze().getHeight() * SQUARE_SIZE;

		Dimension size = new Dimension(w, h);
		setMinimumSize(size);
		setPreferredSize(size);
	}

	/**
	 * @return the maze
	 */
	public final Colony getMaze() {
		return maze;
	}

	/**
	 * @param maze
	 *            the maze to set
	 */
	public final void setMaze(Colony maze) {
		this.maze = maze;
	}

	@Override
	public void paint(Graphics g) {
		assert g != null;
		draw(maze, g, getSize());
	}

	public void draw(Colony colony, Graphics g, Dimension window) {
		Maze maze = colony.getMaze();
		ArrayList<Ant> ants = colony.getAnts();
		int cellW = window.width / maze.getWidth();
		int cellH = window.height / maze.getHeight();
		double maximum = 10;
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, window.width, window.height);

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/ant.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int y = 0; y < maze.getHeight(); y++) {

			for (int x = 0; x < maze.getWidth(); x++) {
				int cellX = x * cellW;
				int cellY = y * cellH;

				Square square = maze.squareAt(x, y);
				if (square instanceof Path) {
					Path path = (Path) square;

					if (path.getPheremone() > maximum
							&& path.getPheremone() > 0.5) {
						maximum = path.getPheremone();
					}
					g.setColor(new Color(1f, (float) Math.min(1f,
							path.getPheremone() / maximum), 1f));
					
					if (colony.getDenied().contains(path)) {
						g.setColor(Color.RED);
					}
					if (colony.getPath().contains(path)) {
						g.setColor(Color.GREEN);
					}
				

				} else {
					g.setColor(Color.BLACK);
				}
				if (maze.getStart().getX() == x && maze.getStart().getY() == y) {
					g.setColor(Color.BLUE);
				}
				if (maze.getEnd().getX() == x && maze.getEnd().getY() == y) {
					g.setColor(Color.GREEN);
				}

				g.fillRect(cellX, cellY, cellW, cellH);
				if (drawAnts) {
					for (Ant a : ants) {
						if (a.getCurrent().equals(square)) {
							g.drawImage(image, cellX, cellY, cellW, cellH, null);
						}
					}
				}
			}
		}
	}

}
