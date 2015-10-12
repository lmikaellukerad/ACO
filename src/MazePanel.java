import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MazePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int SQUARE_SIZE = 10;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private Maze maze;

    public MazePanel(final Maze maze) {
	super();
	this.maze = maze;

	int w = maze.getWidth() * SQUARE_SIZE;
	int h = maze.getHeight() * SQUARE_SIZE;

	Dimension size = new Dimension(w, h);
	setMinimumSize(size);
	setPreferredSize(size);
    }

    @Override
    public void paint(Graphics g) {
	assert g != null;
	draw(maze, g, getSize());
    }

    public void draw(Maze maze, Graphics g, Dimension window) {
	int cellW = window.width / maze.getWidth();
	int cellH = window.height / maze.getHeight();

	g.setColor(BACKGROUND_COLOR);
	g.fillRect(0, 0, window.width, window.height);

	for (int y = 0; y < maze.getHeight(); y++) {
	    for (int x = 0; x < maze.getWidth(); x++) {
		int cellX = x * cellW;
		int cellY = y * cellH;
		Square square = maze.squareAt(x, y);
		if (square instanceof Path) {
		    g.setColor(Color.RED);
		} else {
		    g.setColor(Color.BLUE);
		}
		g.fillRect(cellX, cellY, cellW, cellH);
	    }
	}
    }

}
