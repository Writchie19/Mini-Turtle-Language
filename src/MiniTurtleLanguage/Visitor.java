package MiniTurtleLanguage;

public abstract class Visitor 
{
	// The visitor pattern must know about the possible nodes with in the tree it is traversing:
	abstract void visitASTMove(ASTMove move);
	abstract void visitASTTurn(ASTTurn turn);
	abstract void visitASTRepeat(ASTRepeat repeat);
	abstract void visitASTPenUp(ASTPenUp penUp);
	abstract void visitASTPenDown(ASTPenDown penDown);
	abstract void visitASTVariable(ASTVariable var);
	abstract void visitASTConstant(ASTConstant constant);
	
	// The particular tree visitors will traversing requires a context, therefore must add methods to allow a visitor to hold a context
	// and give access to a context
	abstract TurtleContext getContext();
	abstract void setContext(TurtleContext context);
}
