package assign3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Starts the turtle program by reading in lines from a file, parsing those lines, and building an abstract syntax tree based on the tokens
// formed by the parsed lines 
public class TurtleProgram 
{
	private ASTRepeat inputTokens; //Starting with the repeat rule in the AST grammar for the turtle language, this is because its functionality causes
	// branching, which will allow an abstract syntax tree to form if this is the root node 
	public TurtleProgram() throws IOException, InvalidASTInputException
	{
		inputTokens = new ASTRepeat(new ASTConstant(1)); // it is important that the repeat rule's constructor take in a constant that is 
		// set to one because in this case it is acting as the root node, and therefore should only access each of its branches once
	}
	
	@SuppressWarnings({ "resource" })
	public ASTRepeat parseInputFile(File file) throws IOException, InvalidASTInputException
	{
		BufferedReader buff = new BufferedReader(new FileReader(file));
		String input = "hello world"; // One line of input from the file
		String[] tempInputArray; // takes in the result of parsing input, using spaces as a delimiter
		ASTRepeat repeat; 
		
		while(null != (input = buff.readLine()))
		{
			tempInputArray = input.split("\\s+"); //delimmits the inputs on spaces
			
			// Must first check for "repeat" input, since it is the only input that requires additional reading from the file
			if (tempInputArray[0].equals("repeat"))
			{
				repeat = new ASTRepeat(assignASTExpression(tempInputArray[1])); //Since we are assuming each input is valid for the turtle language
				// we can safely assume index 1 is the k needed for the repeat rule

				// Since repeat uses other rules inthe language, must keep reading in lines from the file and create more branches with in the repeat rule
				// until "end is reached
				while ((input != null) && ((input = buff.readLine()).equals("end") != true))
				{
					repeat.add(assignASTExpression(input.split("\\s+")));
				}
				
				inputTokens.add(repeat);
			}
			else
			{
				inputTokens.add(assignASTExpression(tempInputArray)); 
			}
	  	}
		return inputTokens;
	}
	
	// Assigns meaning to the input tokens, turning them from an array of strings, into some ASTExpression
	private ASTExpression assignASTExpression(String[] inputTokens) throws InvalidASTInputException
	{
		String firstToken = inputTokens[0];
		
		if (firstToken.equals("move"))
		{ 
			return new ASTMove(assignASTExpression(inputTokens[1]));
		}
		else if (firstToken.equals("turn"))
		{
			return new ASTTurn(assignASTExpression(inputTokens[1]));
		}
		else if (firstToken.equals("penUp"))
		{
			return new ASTPenUp();
		}
		else if (firstToken.equals("penDown"))
		{
			return new ASTPenDown();
		}
		else if (firstToken.charAt(0) == '#')
		{
			return new ASTVariable(inputTokens[0], Integer.parseInt(inputTokens[2]));
		}
		else
		{
			return null;
		}
	}

	// Overload assignASTExpression to take in a single token as input, this is because other ASTExpressions often take certain ASTExpressions
	// as input to their constructors
	private ASTExpression assignASTExpression(String token) 
	{
		if (token.charAt(0) == '#')
		{
			return new ASTVariable(token);
		}
		else 
		{
			return new ASTConstant(Integer.parseInt(token));
		}
	}
}
