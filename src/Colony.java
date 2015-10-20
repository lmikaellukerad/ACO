import java.util.ArrayList;

public class Colony {

	private Maze maze;
	private ArrayList<Ant> ants;
	private ArrayList<Path> path;

	private final double initialPheremone = 10000;
	private final int antsNumber = 1000;
	private final int maximumIterations = 10;
	private final int maximumDistance = 10000;
	private final double evaporationConstant = 0.1;
	private final int delay = 0;

	public Colony(Maze maze) {
		setMaze(maze);
		setPath(new ArrayList<Path>());
		initialize();
	}

	private void initialize() {
		ants = new ArrayList<Ant>();
		for (int i = 0; i < antsNumber; i++) {
			Ant a = new Ant(initialPheremone, maze.startPath(), maze.endPath());
			ants.add(a);
		}
	}

	private boolean completed() {
		for (Ant a : ants) {
			if (!a.arrived()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Main algorithm.
	 * @return shortest found path in list
	 */
	public final ArrayList<Path> runAnt() {
		int shortest = maximumDistance;
		double startTime = System.currentTimeMillis();
		ArrayList<Path> path = new ArrayList<Path>();
		for (int i = 0; i < maximumIterations; i++) {
			int completed = 0;
			for (int j = 0; j < maximumDistance; j++) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (Ant a : ants) {
					a.update();
				}

				if (completed()) {
					break;
				}
			}
			maze.decreasePheremones(evaporationConstant);
			for (Ant a : ants) {
				if (a.arrived()) {
					completed++;
					a.updatePheremones();
					if (a.getList().size() < shortest) {
						shortest = a.getList().size();
						path = a.getList();
					}
				}
			}
//			if (shortest < 200) {
//				break;
//			}
			System.out.println(completed);
			initialize();
		}
		System.out.println("Shortest path:");
		System.out.println(shortest);
		System.out.println("Time taken:");
		System.out.println((System.currentTimeMillis() - startTime) / 1000 + "seconds");
		setPath(path);
		return path;
	}

	/**
	 * @return the maze
	 */
	public final Maze getMaze() {
		return maze;
	}

	/**
	 * @param maze
	 *            the maze to set
	 */
	public final void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * @return the ants
	 */
	public final ArrayList<Ant> getAnts() {
		return ants;
	}

	/**
	 * @param ants
	 *            the ants to set
	 */
	public final void setAnts(ArrayList<Ant> ants) {
		this.ants = ants;
	}

	public ArrayList<Path> getPath() {
		return path;
	}

	public void setPath(ArrayList<Path> shortest) {
		this.path = shortest;
	}

}
