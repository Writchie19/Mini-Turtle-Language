package MiniTurtleLanguage;

import java.util.HashMap;

// A context that is used to help evaluate the abstract syntax tree 
public class TurtleContext 
{
	private HashMap<String, Integer> context; // is used for variable assignment
	private Turtle turtle; // the turtle to be affected by the rules of the abstract syntax tree
	
	public TurtleContext()
	{
		context = new HashMap<String, Integer>();
		turtle = new Turtle();
	}
	
	public Turtle getTurtle()
	{
		return turtle;
	}
	
	public int getContextValue(String varName)
	{
		return context.get(varName);
	}
	
	public void setTurtle(Turtle turtle)
	{
		this.turtle = turtle;
	}
	
	// Note: setting a context value with a variable name that already exists, will overwrite the value of the current variable of the same name
	// effectively "reassigning" the value of the variable to a new value
	public void setContextValue(String varName, int value)
	{
		context.put(varName, value);
	}
}