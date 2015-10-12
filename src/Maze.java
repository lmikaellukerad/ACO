import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Maze {
	
	private static int [][] maze;
	private static ArrayList<String> data = new ArrayList<String>();
	
	public static void main(String[] args) {
		readFile("res/easy maze.txt");
	}
	
	public static void parseMaze() {
		String[] format = data.get(0).split("\\s+");
		int width = Integer.parseInt(format[0]);
		int height = Integer.parseInt(format[1]);
		maze = new int[width][height];
		data.remove(0);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				maze[x][y] = Character.digit(data.get(y).charAt(x), 10);
				System.out.println(maze[x][y]);
			}
		}

	}
	
	public static void readFile(String location) {
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(location));
			String line = bufferedReader.readLine();
			data.add(line);
			while ((line = bufferedReader.readLine()) != null) {
				line.replaceAll("\\s+","");
				data.add(line);
				System.out.println(line);
			}
			parseMaze();
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Not found");
			e.printStackTrace();
		}
	}
}
