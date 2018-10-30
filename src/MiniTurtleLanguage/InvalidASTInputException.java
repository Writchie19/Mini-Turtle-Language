package assign3;

// Handles when an invalid input, as decreed by the abstraction, is sent to the constructor for some of the ASTExpressions, 
public class InvalidASTInputException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public InvalidASTInputException()
	{
		
	}
	
	public InvalidASTInputException(String message)
	{
		super(message);
	}

}
