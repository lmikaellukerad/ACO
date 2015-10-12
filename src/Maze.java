public class Maze {

    private Square[][] maze;

    public Maze(Square[][] maze) {
	this.setMaze(maze);
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

    public Square squareAt(int x, int y) {
	// TODO Auto-generated method stub
	return maze[x][y];
    }
}
