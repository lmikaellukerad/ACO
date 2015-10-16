import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeBuilder {

	private Maze maze;
	private Point start;
	private Point end;
	private MazeUI ui;
	private ArrayList<String> data = new ArrayList<String>();

	public static void main(String[] args) {
		new MazeBuilder().launch();
	}

	public void launch() {
		readCoordinates("res/medium coordinates.txt");
		maze = readFile("res/medium maze.txt");
//	readCoordinates("res/INSANE start-finish.txt");
//		maze = readFile("res/INSANE");
		
		ui = new MazeUI(maze);
		ui.start();
		
		int width = maze.getWidth();
		int height = maze.getHeight();	
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Square s = maze.getMaze()[x][y];
				if (s instanceof Path) {
					Path p = (Path) s;
					p.printNeighbours();
				}
			}
		}
	}

	/**
	 * Parses data to maze.
	 * 
	 * @return
	 */
	public Maze parseMaze() {
		String[] format = data.get(0).split("\\s+");
		int width = Integer.parseInt(format[0]);
		int height = Integer.parseInt(format[1]);
		Square[][] maze = new Square[width][height];
		data.remove(0);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (data.get(y).charAt(x) == '1') {
					maze[x][y] = new Path();
					maze[x][y].setX(x);
					maze[x][y].setY(y);
				} else {
					maze[x][y] = new Wall();
				}

			}
		}
		Maze m = new Maze(maze, start, end);
		setNeighbours(m);
		return m;
	}
	
	public void setNeighbours(Maze maze) {
		Square[][] squares = maze.getMaze();
		int width = maze.getWidth();
		int height = maze.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Square s = squares[x][y];
				if (s instanceof Path) {
					Path p = (Path) s;
					if (x + 1 < width) {
						Square n = squares[x + 1][y];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					} if (x - 1 >= 0) {
						Square n = squares[x - 1][y];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					} if (y - 1 >= 0) {
						Square n = squares[x][y - 1];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					} if (y + 1 < height) {
						Square n = squares[x][y + 1];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					}
				}
			}
		}
		
	}

	/**
	 * Reads file and parses maze.
	 * 
	 * @param location
	 *            location of the file
	 */
	public Maze readFile(String location) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					location));
			String line = bufferedReader.readLine();
			data.add(line);
			while ((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll("\\s+", "");
				data.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseMaze();
	}

	public void readCoordinates(String location) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					location));
			String line = bufferedReader.readLine();
			line = line.replaceAll(",", "");
			line = line.replaceAll(";", "");
			String[] format = line.split("\\s+");
			start = new Point(Integer.parseInt(format[0]), Integer.parseInt(format[1]));
			line = bufferedReader.readLine();
			line = line.replaceAll(",", "");
			line = line.replaceAll(";", "");
			format = line.split("\\s+");
			end = new Point(Integer.parseInt(format[0]), Integer.parseInt(format[1]));
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
