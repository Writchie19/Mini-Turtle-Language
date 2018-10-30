package MiniTurtleLanguage;

// provides the base functionality for all rules in the turtle language
public abstract class ASTExpression 
{
	abstract public void accept(Visitor aVisitor);
	abstract public int execute(TurtleContext context);
	abstract public String toString();
}
