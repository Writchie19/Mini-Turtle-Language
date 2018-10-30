package junittests;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import assign3.*;

import junit.framework.TestCase;

public class TestTurtleProgram extends TestCase
{
	//Test Files
	File testFile1 = new File("C:\\Users\\William Ritchie\\Documents\\CS635\\Assignment3\\test1.txt");
	File testFile2 = new File("C:\\Users\\William Ritchie\\Documents\\CS635\\Assignment3\\test2.txt");
	File testFile3 = new File("C:\\Users\\William Ritchie\\Documents\\CS635\\Assignment3\\test3.txt");
	File testFile4 = new File("C:\\Users\\William Ritchie\\Documents\\CS635\\Assignment3\\test4.txt");
	
	// The correct final Locations that result from each file
	HashMap<File, Point2D.Double> correctLocations;
	final Double P1X = 22.99038105676658;
	final Double P1Y = 27.5;
	final Double P2X = 0.0;
	final Double P2Y = 1.2246467991473533E-15;
	final Double P3X = 19.999999999999996;
	final Double P3Y = -3.1222303571891626E-15;
	final Double P4X = 14.330127018922212;
	final Double P4Y = 22.499999999999993;
	
	// The correct Final directions that result from each file
	HashMap<File, Integer> correctDirection;
	final Integer DIRECTION1 = 30;
	final Integer DIRECTION2 = 180;
	final Integer DIRECTION3 = 360;
	final Integer DIRECTION4 = 660;
	
	// The correct final Distances that result from each file
	HashMap<File, Integer> correctDistance;
	final Integer DISTANCE1 = 45;
	final Integer DISTANCE2 = 20;
	final Integer DISTANCE3 = 60;
	final Integer DISTANCE4 = 95;
	
	TurtleProgram prog1;
	TurtleContext context1;
	ASTExpression ast;
	MementoVisitor mVisitor1;
	DistanceVisitor dVisitor1;
	Turtle turtle1;
	
	public TestTurtleProgram(String name)
	{
		// TestCase requires that super be called
		super(name);
	}
	
	@Override
	protected void setUp() throws IOException, InvalidASTInputException
	{
		correctLocations = new HashMap<File, Point2D.Double>();
		correctLocations.put(testFile1, new Point2D.Double(P1X, P1Y));
		correctLocations.put(testFile2, new Point2D.Double(P2X, P2Y));
		correctLocations.put(testFile3, new Point2D.Double(P3X, P3Y));
		correctLocations.put(testFile4, new Point2D.Double(P4X, P4Y));
		
		correctDirection = new HashMap<File, Integer>();
		correctDirection.put(testFile1, DIRECTION1);
		correctDirection.put(testFile2, DIRECTION2);
		correctDirection.put(testFile3, DIRECTION3);
		correctDirection.put(testFile4, DIRECTION4);
		
		correctDistance = new HashMap<File, Integer>();
		correctDistance.put(testFile1, DISTANCE1);
		correctDistance.put(testFile2, DISTANCE2);
		correctDistance.put(testFile3, DISTANCE3);
		correctDistance.put(testFile4, DISTANCE4);

		try 
		{
			prog1 = new TurtleProgram();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		context1 = new TurtleContext();
		ast = new ASTRepeat(new ASTConstant(1));
		mVisitor1 = new MementoVisitor();
		dVisitor1 = new DistanceVisitor();
		turtle1 = new Turtle();
	}
	
	public void testTurtleProgramFile1() throws IOException, InvalidASTInputException
	{
		try 
		{
			ast = prog1.parseInputFile(testFile1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Note: executing without a visitor, the visitor's will have their own tests later 
		ast.execute(context1);
		assertTrue(context1.getTurtle().location().equals(correctLocations.get(testFile1)));
		assertTrue(((Integer)context1.getTurtle().direction()).equals(correctDirection.get(testFile1)));
	}
	
	public void testTurtleProgramFile2() throws IOException, InvalidASTInputException
	{
		try 
		{
			ast = prog1.parseInputFile(testFile2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Note: executing without a visitor, the visitor's will have their own tests later
		ast.execute(context1);
		assertTrue(context1.getTurtle().location().equals(correctLocations.get(testFile2)));
		assertTrue(((Integer)context1.getTurtle().direction()).equals(correctDirection.get(testFile2)));
	}
	
	public void testTurtleProgramFile3() throws IOException, InvalidASTInputException
	{
		try 
		{
			ast = prog1.parseInputFile(testFile3);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Note: executing without a visitor, the visitor's will have their own tests later
		ast.execute(context1);
		assertTrue(context1.getTurtle().location().equals(correctLocations.get(testFile3)));
		assertTrue(((Integer)context1.getTurtle().direction()).equals(correctDirection.get(testFile3)));
	}
	
	public void testTurtleProgramFile4() throws IOException, InvalidASTInputException
	{
		try 
		{
			ast = prog1.parseInputFile(testFile4);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Note: executing without a visitor, the visitor's will have their own tests later
		ast.execute(context1);
		assertTrue(context1.getTurtle().location().equals(correctLocations.get(testFile4)));
		assertTrue(((Integer)context1.getTurtle().direction()).equals(correctDirection.get(testFile4)));
	}
	
	public void testMementoVisitorFile1()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(mVisitor1);
		List<Memento> mementos = mVisitor1.getMementos();
		
		//Restore to the initial state
		turtle1.restoreState(mementos.get(0));
		assertTrue("Direction of init state", turtle1.direction() == 0);
		assertTrue("location X of init state", turtle1.location().getX() == 0.0);
		assertTrue("location Y of init state", turtle1.location().getY() == 0.0);
		
		//Restore to the fourth state
		turtle1.restoreState(mementos.get(3));
		assertTrue("Direction of state 3", turtle1.direction() == 90);
		assertTrue("location X of state 3", turtle1.location().getX() == 10.000000000000002);
		assertTrue("location Y of state 3", turtle1.location().getY() == 20.0);
		
		//Restore to the final state
		turtle1.restoreState(mementos.get(mementos.size()-1));
		assertTrue("final state location", turtle1.location().equals(correctLocations.get(testFile1)));
		assertTrue("final state direction", ((Integer)turtle1.direction()).equals(correctDirection.get(testFile1)));
	}
	
	public void testMementoVisitorFile2()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(mVisitor1);
		List<Memento> mementos = mVisitor1.getMementos();
		
		//Restore to the initial state
		turtle1.restoreState(mementos.get(0));
		assertTrue("Direction of init state", turtle1.direction() == 0);
		assertTrue("location X of init state", turtle1.location().getX() == 0.0);
		assertTrue("location Y of init state", turtle1.location().getY() == 0.0);
		
		//Restore to the final state
		turtle1.restoreState(mementos.get(mementos.size()-1));
		assertTrue("final state location", turtle1.location().equals(correctLocations.get(testFile2)));
		assertTrue("final state direction", ((Integer)turtle1.direction()).equals(correctDirection.get(testFile2)));
	}
	
	public void testMementoVisitorFile3()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile3);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(mVisitor1);
		List<Memento> mementos = mVisitor1.getMementos();
		
		//Restore to the initial state
		turtle1.restoreState(mementos.get(0));
		assertTrue("Direction of init state", turtle1.direction() == 0);
		assertTrue("location X of init state", turtle1.location().getX() == 0.0);
		assertTrue("location Y of init state", turtle1.location().getY() == 0.0);
		
		//Restore to the fourth state
		turtle1.restoreState(mementos.get(3));
		assertTrue("Direction of state 3", turtle1.direction() == 90);
		assertTrue("location X of state 3", turtle1.location().getX() == 10.0);
		assertTrue("location Y of state 3", turtle1.location().getY() == 10.0);
		
		//Restore to the final state
		turtle1.restoreState(mementos.get(mementos.size()-1));
		assertTrue("final state location", turtle1.location().equals(correctLocations.get(testFile3)));
		assertTrue("final state direction", ((Integer)turtle1.direction()).equals(correctDirection.get(testFile3)));
	}
	
	public void testMementoVisitorFile4()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile4);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(mVisitor1);
		List<Memento> mementos = mVisitor1.getMementos();
		
		//Restore to the initial state
		turtle1.restoreState(mementos.get(0));
		assertTrue("Direction of init state", turtle1.direction() == 0);
		assertTrue("location X of init state", turtle1.location().getX() == 0.0);
		assertTrue("location Y of init state", turtle1.location().getY() == 0.0);
		
		//Restore to the fifth state
		turtle1.restoreState(mementos.get(4));
		assertTrue("Direction of state 4", turtle1.direction() == 30);
		assertTrue("location X of state 4", turtle1.location().getX() == 10.000000000000002);
		assertTrue("location Y of state 4", turtle1.location().getY() == 20.0);
		
		//Restore to the eigth state Note: checking for if the pen is up status is stored in the memento
		turtle1.restoreState(mementos.get(7));
		assertTrue("Pen is Up", turtle1.isPenUp());
		
		//Restore to final state
		turtle1.restoreState(mementos.get(mementos.size()-1));
		assertTrue("final state location", turtle1.location().equals(correctLocations.get(testFile4)));
		assertTrue("final state direction", ((Integer)turtle1.direction()).equals(correctDirection.get(testFile4)));
	}
	
	public void testDistanceVisitorFile1()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ast.accept(dVisitor1);
		assertTrue("Correctly Calculate Distance in File 1", dVisitor1.getDistance() == correctDistance.get(testFile1));
	}
	
	public void testDistanceVisitorFile2()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(dVisitor1);
		assertTrue("Correctly Calculate Distance in File 2", dVisitor1.getDistance() == correctDistance.get(testFile2));
	}
	
	public void testDistanceVisitorFile3()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile3);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(dVisitor1);
		assertTrue("Correctly Calculate Distance in File 3", dVisitor1.getDistance() == correctDistance.get(testFile3));
	}
	
	public void testDistanceVisitorFile4()
	{
		try 
		{
			ast = prog1.parseInputFile(testFile4);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ast.accept(dVisitor1);
		assertTrue("Correctly Calculate Distance in File 4", dVisitor1.getDistance() == correctDistance.get(testFile4));
	}
	
	@Override
	protected void tearDown()
	{
		correctLocations = null;
		correctDirection = null;
		correctDistance = null;
		prog1 = null;
		context1 = null;
		turtle1 = null;
		mVisitor1 = null;
		dVisitor1 = null;
	}
}
