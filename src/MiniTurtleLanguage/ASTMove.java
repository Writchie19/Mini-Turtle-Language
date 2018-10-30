package MiniTurtleLanguage;

// Move rule, affects a turtle by calling its move method, using a constant or a variable's value at runtime as the input
public class ASTMove extends ASTExpression
{
	private ASTExpression moveAmount; // moveAmount is not an integer because it's value is contingent on runtime execution, so it will return 
	// the moveAmount as an integer at runtime
	
	// As is defined by the turtle language, Move should only except a constant or variable from the language as an input to its contructor,
	// throw an exception if its anything else
	public ASTMove(ASTExpression exp) throws InvalidASTInputException 
	{
		if (exp.getClass().equals(new ASTConstant(1).getClass()) || exp.getClass().equals(new ASTVariable("NA").getClass()))
		{
			moveAmount = exp;
		}
		else 
		{
			throw new InvalidASTInputException("Not a valid input to ASTMove");
		}
	}
	
	// This allows for viewing the amount to be moved without having to alter the state of the turtle
	public int getMoveAmount(TurtleContext context)
	{
		// it is safe to call execute here as moveAmount will either be a constant or variable, neither of which directly affect the state of the turtle
		return moveAmount.execute(context);
	}
	
	// There is no setter to move amount as it breaks the abstraction of being a rule of the language, the value of moveAmount can be
	// changed by altering the context when callign execute

	@Override
	public void accept(Visitor aVisitor) 
	{
		aVisitor.visitASTMove(this);
	}

	@Override
	public int execute(TurtleContext context) 
	{
		// temp int is needed mainly to be returned by the function, otherwise moveAmount could be executed when calling a turtles move function
		int tempMoveAmount = moveAmount.execute(context);
		context.getTurtle().move(tempMoveAmount);
		return tempMoveAmount;
	}

	@Override
	public String toString() 
	{
		return null;
	}
}
