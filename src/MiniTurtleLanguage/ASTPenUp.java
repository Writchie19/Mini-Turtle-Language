package MiniTurtleLanguage;

// PenUp rule, affects the trutle by calling its penUp method
public class ASTPenUp extends ASTExpression
{
	@Override
	public void accept(Visitor aVisitor) 
	{
		aVisitor.visitASTPenUp(this);
	}

	@Override
	public int execute(TurtleContext context) 
	{
		context.getTurtle().penUp();
		return 1; // returns 1 to denote the pen now up
	}

	@Override
	public String toString() 
	{
		return "penUp";
	}

}
