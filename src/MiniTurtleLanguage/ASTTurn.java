package MiniTurtleLanguage;


// Turn rule, affects a turtle by calling its turn method, using a constant or a variable's value at runtime as the input
public class ASTTurn extends ASTExpression
{
	ASTExpression turnAmount; // turnAmount is not an integer because it's value is contingent on runtime execution, so it will return 
	// the turnAmount as an integer at runtime
	
	
	// As is defined by the turtle language, Turn should only except a constant or variable from the language as an input to its contructor,
	// throw an exception if its anything else
	public ASTTurn(ASTExpression exp) throws InvalidASTInputException 
	{
		if (exp.getClass().equals(new ASTConstant(1).getClass()) || exp.getClass().equals(new ASTVariable("NA").getClass()))
		{
			turnAmount = exp;
		}
		else 
		{
			throw new InvalidASTInputException("Not a valid input to ASTMove");
		}
	}

	@Override
	public void accept(Visitor aVisitor)
	{
		aVisitor.visitASTTurn(this);
	}

	@Override
	public int execute(TurtleContext context) 
	{
		// temp int is needed mainly to be returned by the function, otherwise moveAmount could be executed when calling a turtles move function
		int tempTurnAmount = turnAmount.execute(context);
		context.getTurtle().turn(tempTurnAmount);
		return tempTurnAmount;
	}

	@Override
	public String toString() 
	{
		return null;
	}

}
