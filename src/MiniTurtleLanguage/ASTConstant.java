package MiniTurtleLanguage;

// Constant rule, does not affect the turtle directly, is to be used by other rules to affect the turtle
public class ASTConstant extends ASTExpression
{
	private final int value;
	
	public ASTConstant(int value) 
	{
		this.value = value;
	}

	@Override
	public void accept(Visitor aVisitor) 
	{
		aVisitor.visitASTConstant(this);
	}

	// Note: execute does not actually affect the turtle or turtlecontext at all, this is because constants main functionality is to be used by
	// other rules in the language
	@Override
	public int execute(TurtleContext context) 
	{
		return value;
	}

	@Override
	public String toString() 
	{
		return null;
	}


}
