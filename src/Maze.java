import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Maze {
	
	private String maze;
	
	public static void main(String[] args) {
		readFile("res/easy maze.txt");
	}
	
	public static void readFile(String location) {
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(location),
					StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Not found");
			e.printStackTrace();
		}
	}
}
