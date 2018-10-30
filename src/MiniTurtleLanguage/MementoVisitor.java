package assign3;

import java.util.ArrayList;
import java.util.List;

// A visitor that saves the state of the turtle by collecting mementos as it traverses the abstract syntax tree
public class MementoVisitor extends Visitor
{
	private TurtleContext context;
	private List<Memento> mementos; // Use a ArrayList as the underlying data structure to hold mementos, this lends itself to the ability to 
	// step through the various states of the turtle
	
	public MementoVisitor()
	{
		context = new TurtleContext();
		mementos = new ArrayList<Memento>();
	}
	
	public MementoVisitor(TurtleContext context)
	{
		this.context = context;
		mementos = new ArrayList<Memento>();
	}
	
	public MementoVisitor(TurtleContext context, List<Memento> mementos)
	{
		this.context = context;
		this.mementos = mementos;
	}

	public List<Memento> getMementos()
	{
		return mementos;
	}
	
	@Override
	public void visitASTMove(ASTMove move) 
	{
		move.execute(context);
		mementos.add(context.getTurtle().createMemento());
	}
	
	@Override
	public void visitASTTurn(ASTTurn turn) 
	{
		turn.execute(context);
		mementos.add(context.getTurtle().createMemento());
	}

	@Override
	public void visitASTRepeat(ASTRepeat repeat) 
	{
		// Note: there is no need to save the state when executing a repeat, as it does not directly affect the turtle
		// This seems counter intuitive but it is actually the branches of repeat that affect the turtle, not the repeat itself
		repeat.execute(context);
	}

	@Override
	public void visitASTPenUp(ASTPenUp penUp) 
	{
		penUp.execute(context);
		mementos.add(context.getTurtle().createMemento());
	}

	@Override
	public void visitASTPenDown(ASTPenDown penDown)
	{
		penDown.execute(context);
		mementos.add(context.getTurtle().createMemento());
	}

	@Override
	public void visitASTVariable(ASTVariable var) 
	{
		// Note: there is no need to save the state when executing a variable, as it does not directly affect the turtle
		var.execute(context);
	}

	@Override
	public void visitASTConstant(ASTConstant constant) 
	{
		// Note: there is no need to save the state when executing a constant, as it does not directly affect the turtle
		constant.execute(context);
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
