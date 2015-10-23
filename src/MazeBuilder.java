import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeBuilder {

	private Maze maze;
	private Point start;
	private Point end;
	private MazeUI ui;
	private ArrayList<String> data = new ArrayList<String>();
	private final String difficulty = "hard";

	public static void main(String[] args) {
		new MazeBuilder().launch();
	}

	public void launch() {
		start = readStartCoordinates("res/" + difficulty + " coordinates.txt");
		end = readEndCoordinates("res/" + difficulty + " coordinates.txt");
		maze = readFile("res/" + difficulty + " maze.txt");

		Colony colony = new Colony(maze);
		ui = new MazeUI(colony);
		ui.start();
		writeFile(colony.runAnt());
	}

	public void writeFile(ArrayList<Path> path) {
		ArrayList<Integer> actions = translatePath(path);
		try {
			FileWriter writer = new FileWriter("actions.txt", false);
			writer.write(actions.size() + ";");
			writer.write("\r\n"); // write new line
			writer.write(start.getX() + ", " + start.getY() + ";");
			writer.write("\r\n");
			for (int i : actions) {
				writer.write(i + ";");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<Integer> translatePath(ArrayList<Path> path) {
		ArrayList<Integer> actions = new ArrayList<Integer>();
		for (int i = 0; i < path.size() - 1; i++) {
			Path current = path.get(i);
			Path next = path.get(i + 1);
			if (next.getX() > current.getX()) {
				actions.add(0);
			} else if (next.getY() < current.getY()) {
				actions.add(1);
			} else if (next.getX() < current.getX()) {
				actions.add(2);
			} else if (next.getY() > current.getY()) {
				actions.add(3);
			}
		}
		return actions;

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
					Path p = new Path();
					p.setPos(new Point(x, y));
					p.setPheremone(1);
					maze[x][y] = p;
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
					}
					if (x - 1 >= 0) {
						Square n = squares[x - 1][y];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					}
					if (y - 1 >= 0) {
						Square n = squares[x][y - 1];
						if (n instanceof Path) {
							p.addNeighbour((Path) n);
						}
					}
					if (y + 1 < height) {
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
	 * Reads file and returns a maze objects.
	 * 
	 * @param location
	 *            location of the file
	 * @return a maze object
	 */
	public final Maze readFile(final String location) {
		try {
			File file = new File(location);
			Scanner sc = new Scanner(file);
			String line = sc.nextLine();
			data.add(line);
			while (sc.hasNextLine()) {
				line = sc.nextLine().replaceAll("\\s+", "");
				data.add(line);
			}
			sc.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return parseMaze();
	}

	public Point readStartCoordinates(String location) {
		Point p = null;
		try {
			Scanner sc = new Scanner(new File(location));
			String line = sc.nextLine();
			line = line.replaceAll(",", "");
			line = line.replaceAll(";", "");
			String[] format = line.split("\\s+");
			sc.close();
			p = new Point(Integer.parseInt(format[0]), Integer.parseInt(format[1]));
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return p;
	}

	public Point readEndCoordinates(String location) {
		Point p = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			line = line.replaceAll(",", "");
			line = line.replaceAll(";", "");
			String[] format = line.split("\\s+");
			bufferedReader.close();
			p = new Point(Integer.parseInt(format[0]), Integer.parseInt(format[1]));
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return p;
	}
}
