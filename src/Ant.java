
public class Ant {
	private double ph;
	private int x;
	private	int y;
	
	public Ant(double pheromones, int cx, int cy)
	{
		ph = pheromones;
		x = cx;
		y = cy;
		
	}
	
	public boolean intersection(int ix , int iy )
	{	
		int l = lees(ix,iy)
			if (l => 3)
				{return true;}
			else 
				{return false;}
	}
	
	
	public void decide(double pheromones)
	{
		if ( ph == 0)
		{ //choose by probability
		}
		
			
}
	
	public void droppheromones(double pheromones)
	{
		//drop at chosen intersection
	}

	public void stop(int ex, int ey)
	{
		if ( x == ex && y == ey)
			//stop
	}
	
	public void walk( int wx, int wy)
	{
		//if (clockpulse = high)
			//then walk
	}
}