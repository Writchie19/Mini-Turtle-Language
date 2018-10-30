package assign3;

public class DistanceVisitor extends Visitor
{
	private TurtleContext context;
	private int distance;
	private boolean penDown; // Must keep track of if the pen is down or not, as the distance should increase only if the pen is up
	
	public DistanceVisitor()
	{
		context = new TurtleContext();
		distance = 0;
		penDown = false;
	}
	
	public DistanceVisitor(TurtleContext context)
	{
		this.context = context;
		distance = 0;
		penDown = false;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	@Override
	public void visitASTMove(ASTMove move) 
	{
		if (penDown)
		{
			distance += move.getMoveAmount(context);
		}
	}

	@Override
	public void visitASTTurn(ASTTurn turn) 
	{
		//Do Nothing
	}

	@Override
	public void visitASTRepeat(ASTRepeat repeat) 
	{
		// Do not execute the repeat, as that will cause all of the branches of repeat to execute
		repeat.accept(this);
	}

	@Override
	public void visitASTPenUp(ASTPenUp penUp) 
	{
		penDown = false;
	}

	@Override
	public void visitASTPenDown(ASTPenDown penDown) 
	{
		this.penDown = true;
	}

	@Override
	public void visitASTVariable(ASTVariable var) 
	{
		// execute the variable in case it is an instance where it will at runtime change the context
		// If is is not this case then nothing negative will happen
		var.execute(context);
	}

	@Override
	public void visitASTConstant(ASTConstant constant) 
	{
		//Do nothing
	}

	@Override
	public TurtleContext getContext() 
	{
		return context;
	}

	@Override
	public void setContext(TurtleContext context) 
	{
		this.context = context;
	}
}
