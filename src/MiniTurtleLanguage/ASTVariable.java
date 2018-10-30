package assign3;

// Variable rule, does not affect the turtle directly, is to be used by other rules to affect the turtle 
public class ASTVariable extends ASTExpression
{
	String varName;
	Integer value; // Should use the wrapper class Integer in order to be able to set value to null, which is used as a necessary check in the
	// execute method
	
	public ASTVariable(String varName, int value) 
	{
		this.varName = varName;
		this.value = value;
	}

	public ASTVariable(String varName) 
	{
		this.varName = varName;
		value = null;
	}
	
	@Override
	public void accept(Visitor aVisitor) 
	{
		aVisitor.visitASTVariable(this);
	}
	
	@Override
	public int execute(TurtleContext context) 
	{
		// if value is not null then you know that this particular instance of the Variable rule is being used to change the value of 
		// (or initialise) a variable
		if (null != value)
		{
			context.setContextValue(varName, value); // The contexts holds the value of the variable inorder for other rules to be able to use it
			return value;
		}
		else
		{
			// At runtime will get the CURRENT value of the variable
			return context.getContextValue(varName);
		}
	}

	@Override
	public String toString() 
	{
		return null;
	}
}
