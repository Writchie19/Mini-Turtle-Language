package assign3;

import java.awt.geom.Point2D;

// The turtle that is affected by inputs consisting of the turtle language
public class Turtle implements Memento
{
	private int currentDirection; //In degrees not radians
	private Point2D.Double currentLocation;
	private boolean penUp; //Flag to keep track if the pen up or down
	private final double oneRadian = 180.0;
	private double y; // This is needed in order to help save a deep copy of the currentLocations x coordinate
	private double x; // This is needed in order to help save a deep copy of the currentLocations y coordinate
	
	public Turtle()
	{
		currentDirection = 0; // The starting point of the turtle program should be to face degree 0
		currentLocation = new Point2D.Double(); // default constructor initializes to 0,0, which is what is required as the initial location for
		// a turtle
		penUp = true;
		y = 0;
		x = 0;
	}
	
	public void move(int distance)
	{
		if (false == penUp)
		{
			double radians = (Math.PI * (double)currentDirection) / oneRadian;  //Converts currentDirection from degrees to radians 
			double deltaY = Math.sin(radians) * (double)distance; // Calculate the change in Y due to a change in distance and/or change in direction
			double deltaX = Math.cos(radians) * (double)distance; // Calculate the change in X due to a change in distance and/or change in direction
			currentLocation.setLocation((currentLocation.getX() + deltaX), (currentLocation.getY() + deltaY)); //Add the new change to the current coordinate
		}
	}
	
	public void turn(int degrees)
	{
		currentDirection += degrees;
	}
	
	public void penUp()
	{
		penUp = true;
	}
	
	public void penDown()
	{
		penUp = false;
	}
	
	public boolean isPenUp()
	{
		return penUp;
	}
	
	public int direction()
	{
		return currentDirection;
	}
	
	public Point2D.Double location()
	{
		return currentLocation;
	}
	
	// Return a clone of the current state of this turtle, note that clone has been overriden to make a deeper copy
	public Memento createMemento()
	{
		Memento currentState = null;
		try
		{
			currentState = (Memento) this.clone();
		}
		catch (CloneNotSupportedException notReachable)
		{
			
		}
		return currentState;
	}
	
	// Restore to a previous state, note currentLocation is not set equal to newStates currentDirection, this is because the clone copy is 
	// not deep enough for the Point2D object, hence why primitive type are used with the variables x and y , to set the currentlocation
	public void restoreState(Memento savedState)
	{
		Turtle newState = (Turtle) savedState;
		currentDirection = newState.direction();
		x = newState.x;
		y = newState.y;
		currentLocation.setLocation(x, y);
		penUp = newState.isPenUp();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		Turtle thisCloned = (Turtle) super.clone();
		thisCloned.currentDirection = (int) currentDirection;
		thisCloned.y = (double)((Point2D.Double) currentLocation).y;
		thisCloned.x = (double)((Point2D.Double) currentLocation).x;
		thisCloned.penUp = (boolean) penUp;
		return thisCloned;
	}
}
