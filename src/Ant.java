import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * 
 * @author Luke
 *
 */
public class Ant {
	private ArrayList<Path> list;

	private HashSet<Path> denied;
	private Path current;
	private Path previous;
	private Path destination;
	private final double p;

	public Ant(double pheromones, Path start, Path destination) {
		p = pheromones;
		this.destination = destination;
		list = new ArrayList<Path>();
		this.denied = new HashSet<Path>();
		visit(start);
	}

	public void update() {
		double chance = 0;
		double total = 0;
		if (!arrived()) {
			ArrayList<Path> neighbours = current.getNeighbours();
			ArrayList<Path> potential = new ArrayList<Path>();
			for (Path n : neighbours) {
				if (!n.equals(previous) && !denied.contains(n)) {
					if (!n.isVisited()) {
						visit(n);
						return;
					} else {
						if (list.contains(n)) {
							abort(n);
							return;
						}
						if (!n.equals(destination)) {
							total += n.getPheremone();
							potential.add(n);
						} else {
							visit(n);
							return;
						}

					}
				}
			}
			if (potential.size() < 1) {
				back();
				return;
			}
			double random = new Random().nextDouble();
			for (Path n : potential) {
				chance += (n.getPheremone() / total);
				if (random < chance) {
					visit(n);
					return;
				}
			}
		}
	}

	public void updatePheremones() {
		double updated = p / (list.size() - 1);
		LinkedHashSet<Path> s = new LinkedHashSet<>(list);
		for (Path n : s) {
			n.increasePheremone(updated);
		}
	}

	private void visit(Path n) {
		list.add(n);
		previous = current;
		current = n;
		current.setVisited(true);
	}

	private void abort(Path path) {
		current = list.get(list.size() - 2);
		list.remove(list.size() - 1);
		if (list.size() - 2 > 0) {
			previous = list.get(list.size() - 2);
		} else {
			previous = null;
		}
		if (current.equals(path)) {
			return;
		} else {
			abort(path);
		}

	}

	private void back() {
		denied.add(current);
		current = list.get(list.size() - 2);
		list.remove(list.size() - 1);
		if (list.size() - 2 > 0) {
			previous = list.get(list.size() - 2);
		} else {
			previous = null;
		}
	}

	public boolean arrived() {
		return current.equals(destination);
	}

	/**
	 * @return the current
	 */
	public final Path getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public final void setCurrent(Path current) {
		this.current = current;
	}

	/**
	 * @return the list
	 */
	public final ArrayList<Path> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public final void setList(ArrayList<Path> list) {
		this.list = list;
	}

}