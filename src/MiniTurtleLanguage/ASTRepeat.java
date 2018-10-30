package assign3;

import java.util.ArrayList;

// Repeat rule, does not directly affect the turtle, instead allows other rules to repeat k times and affect the turtle
public class ASTRepeat extends ASTExpression
{
	ArrayList<ASTExpression> repeatStruct; // Structure that holds the rules that are to be repeated
	ASTExpression numRepeats; // numRepeats is not an integer because it's value is contingent on runtime execution, so it will return 
	// the number of Repeats as an integer at runtime
	
	// As is defined by the turtle language, Repeat should only except a constant or variable from the language as an input to its contructor,
	// throw an exception if its anything else
	public ASTRepeat(ASTExpression exp) throws InvalidASTInputException 
	{
		if (exp.getClass().equals(new ASTConstant(1).getClass()) || exp.getClass().equals(new ASTVariable("NA").getClass()))
		{
			numRepeats = exp;
		}
		else 
		{
			throw new InvalidASTInputException("Not a valid input to ASTRepeat");
		}
		
		repeatStruct = new ArrayList<ASTExpression>();
	}
	
	public void add(ASTExpression exp)
	{
		repeatStruct.add(exp);
	}

	@Override
	public void accept(Visitor aVisitor) 
	{
		// Note: this temporary variable is only necessary in order to check that the number of times to repeat is not a negative number
		int tempNumRepeats = numRepeats.execute(aVisitor.getContext());
		
		// If the number is negative then ensure repeat never runs
		if (tempNumRepeats < 0)
		{
			tempNumRepeats = 0;
		}
		
		for (int i = 0; i < tempNumRepeats; i++)
		{
			for (ASTExpression exp : repeatStruct)
			{
				exp.accept(aVisitor);
			}
		}
	}

	@Override
	public int execute(TurtleContext context) 
	{
		// Note: this temporary variable is necessary in order to check that the number of times to repeat is not a negative number
		// and to be used as a return value for this function
		int tempNumRepeats = numRepeats.execute(context);
		
		// If the number is negative then ensure repeat never runs
		if (tempNumRepeats < 0)
		{
			tempNumRepeats = 0;
		}
	
		for (int i = 0; i < tempNumRepeats; i++)
		{
			for (ASTExpression exp : repeatStruct)
			{
				exp.execute(context);
			}
		}
		
		return tempNumRepeats;
	}

	@Override
	public String toString() 
	{
		return null;
	}
}
