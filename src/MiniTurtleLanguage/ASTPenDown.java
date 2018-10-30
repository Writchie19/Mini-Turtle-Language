package assign3;


// PenDown rule, affects turtle by calling its penDown method
public class ASTPenDown extends ASTExpression
{
	@Override
	public void accept(Visitor aVisitor) 
	{
		aVisitor.visitASTPenDown(this);
	}

	@Override
	public int execute(TurtleContext context)
	{
		context.getTurtle().penDown();
		return 0; // returns zero to denote the pen now down
	}

	@Override
	public String toString() 
	{
		return null;
	}
}
