package worms.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import worms.exceptions.*;
import worms.model.Position;
import worms.model.Vector;
import worms.model.World;
import worms.model.Worm;
import worms.util.Util;
import static org.hamcrest.CoreMatchers.*;

/**
 * A class collecting tests for the class of worms, including vector and coordinate
 * objects.
 * 
 * @version 1.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 *
 */
public class WormTest {

	/**
	 * Variable registering the value that 2 numbers can differ.
	 */
	private static final double EPS = Util.DEFAULT_EPSILON;

	/**
	 * Variables referencing worms with radius 1, respectively with radius 0.5.
	 */
	private static Worm wormRadius1, wormRadius0dot5;
	
	/**
	 * Variable referencing a worm with radius 1, which both shall be mutated during 
	 * execution.
	 */
	private static Worm mutableWormRadius1;
	
	/**
	 * Variables referencing worms that are faced downwards, with a negative
	 * direction, respectively a positive direction.
	 */
	private static Worm wormFacingDownNegative, wormFacingDownPositive;
	
	/**
	 * Variable referencing a world with width and height set to 0,
	 * the passable map en random generator are set to null.
	 */
	private static World world;

	/**
	 * Set up a mutable test fixture.
	 * 
	 * @post	The variable mutableWormRadius1 references a new worm with a
	 * 			radius of 1.
	 */
	@Before
	public void setupMutableFixture() {
		mutableWormRadius1 = new Worm(new Vector(new Position(0,0), Math.PI / 2), 
										1, "Test", world, null);
	}
	
	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post	The variable wormRadius1 references a new worm with radius 1.
	 * @post	The variable wormRadius0dot5 references a new worm with radius
	 * 			0.5.
	 * @post	The variable wormFacingDownNegative references a new worm that
	 * 			is faced downwards, and has a negative direction.
	 * @post	The variable wormFacingDownPositive references a new worm that
	 * 			is faced downwards, and has a positive direction.
	 */
	@BeforeClass
	public static void setupImmutableFixture() {
		world = new World(0, 0, null, null);
		wormRadius1 = new Worm(new Vector(new Position(0,0), Math.PI / 2), 
							1, "Test", world, null);
		wormRadius0dot5 = new Worm(new Vector(new Position(0,0), Math.PI / 2), 
							0.5, "Test", world, null);
		wormFacingDownNegative = new Worm(new Vector(new Position(0,0), -(Math.PI / 2)), 
							1, "Test", world, null);
		wormFacingDownPositive = new Worm(new Vector(new Position(0,0), 3 * Math.PI / 2), 
							1, "Test", world, null);	
		
	}

	@Test
	public void extendedConstructor_LegalCase() {
		Worm aWorm = new Worm(new Vector(new Position(0,0), Math.PI / 2), 1, "Test", world, null);
		assertThat(aWorm.getVector().getPosition().getX(), is(0.0));
		assertThat(aWorm.getVector().getPosition().getY(), is(0.0));
		assertThat(aWorm.getVector().getDirection(), is(Math.PI / 2));
		assertThat(aWorm.getRadius(), is(1.0));
		assertThat(aWorm.getName(), is("Test"));
		assertEquals(4448.495197, aWorm.getMass(), EPS);
		assertThat(aWorm.getActionPoints(), is(4448));
		assertThat(aWorm.getMaxActionPoints(), is(4448));
	}
	
	@Test
	public void isValidMinRadius_NegativeValue(){
		assertFalse(Worm.isValidMinRadius(-10));
	}
	
	@Test
	public void isValidMinRadius_MaximumValue(){
		assertTrue(Worm.isValidMinRadius(Double.MAX_VALUE));
	}
	
	@Test
	public void canHaveAsAsRadius_IllegalValue(){
		assertFalse(wormRadius0dot5.canHaveAsRadius(wormRadius0dot5.getMinRadius() / 2));
	}
	
	@Test
	public void canHaveAsAsRadius_LegalValue(){
		assertTrue(wormRadius0dot5.canHaveAsRadius(wormRadius0dot5.getMinRadius() * 2));
	}
	
	@Test(expected = IllegalRadiusException.class)
	public void changeRadius_IllegalValue(){
		mutableWormRadius1.changeRadius(-10);
	}
	
	@Test
	public void changeRadius_LegalValue(){
		mutableWormRadius1.changeRadius(1.7);
		
	}
	
	@Test
	public void canAcceptForChangeRadius_BelowCurrentActionPoints(){
		assertFalse(wormRadius0dot5.canAcceptForChangeRadius(0.3));
	}
	
	@Test
	public void canAcceptForChangeRadius_NegativeValue(){
		assertFalse(wormRadius0dot5.canAcceptForChangeRadius(-0.3));
	}
	
	@Test
	public void canAcceptForChangeRadius_LegalValue(){
		assertTrue(wormRadius0dot5.canAcceptForChangeRadius(0.7));
	}
	
	@Test
	public void canHaveAsMass_LegalValue(){
		assertTrue(wormRadius0dot5.canHaveAsMass(556.0618996853933));
	}
	
	@Test
	public void canHaveAsMass_IllegalValue(){
		assertFalse(wormRadius0dot5.canHaveAsMass(10));
	}
	
	@Test
	public void canHaveAsMaxActionPoints_LegalValue(){
		assertTrue(wormRadius0dot5.canHaveAsMaxActionPoints(556));
	}
	
	@Test
	public void canHaveAsMaxActionPoints_IllegalValue(){
		assertFalse(wormRadius0dot5.canHaveAsMaxActionPoints(10));
	}
	
	@Test
	public void useActionPoints_LegalValue(){
		mutableWormRadius1.useActionPoints(20);
		assertThat(mutableWormRadius1.getActionPoints(), is(4428));
	}
	
	@Test
	public void useActionPoints_IllegalValue(){
		mutableWormRadius1.useActionPoints(mutableWormRadius1.getActionPoints() + 1);
		assertThat(mutableWormRadius1.getActionPoints(), 
				not(equalTo(mutableWormRadius1.getActionPoints()
						-(mutableWormRadius1.getActionPoints()+1))));
	}
	
	@Test
	public void canHaveAsActionPoints_LegalCase(){
		assertTrue(wormRadius1.canHaveAsActionPoints(wormRadius1.getMaxActionPoints() / 2));
	}
	
	@Test
	public void canHaveAsActionPoints_NegativeValue(){
		assertFalse(wormFacingDownNegative.canHaveAsActionPoints(Integer.MIN_VALUE));
	}
	
	@Test
	public void canHaveAsActionPoints_GreaterThanMaxActionPointsValue(){
		assertFalse(wormFacingDownPositive.canHaveAsActionPoints(
						wormFacingDownPositive.getMaxActionPoints() + 1));
	}
	
	@Test
	public void setName_LegalValue(){
		mutableWormRadius1.setName("James o'Hara");
	}
	
	@Test(expected = IllegalNameException.class)
	public void setName_StartsLowerCase(){
		mutableWormRadius1.setName("james o'Hara");
	}
	
	@Test(expected = IllegalNameException.class)
	public void setName_IllegalChar(){
		mutableWormRadius1.setName("James. o'Hara");
	}
	
	@Test(expected = IllegalNameException.class)
	public void setName_LengthShortherThanTwoChars(){
		mutableWormRadius1.setName("J");
	}
	
	@Test
	public void isValidName_LegalValue(){
		assertTrue(Worm.isValidName("James o'Hara"));
	}
	
	@Test
	public void isValidName_StartsLowerCase(){
		assertFalse(Worm.isValidName("james o'Hara"));
	}
	
	@Test
	public void isValidName_IllegalChar(){
		assertFalse(Worm.isValidName("James. o'Hara"));
	}
	
	@Test
	public void isValidName_LengthShortherThanTwoChars(){
		assertFalse(Worm.isValidName("J"));
	}
	
	@Test
	public void getJumpForce(){
		assertEquals(65864.8354283981, wormRadius1.getJumpForce(), EPS);
	}
	
	@Test
	public void canJump_NoMoreActionPoints(){
		mutableWormRadius1.useActionPoints(mutableWormRadius1.getActionPoints());
		assertFalse(mutableWormRadius1.canJump());
	}
	
	@Test(expected=AssertionError.class)
	public void changeDirection_IllegalValue(){
		mutableWormRadius1.getVector().changeDirection(Double.MAX_VALUE);
		assertThat(mutableWormRadius1.getVector().getDirection(), 
					not(is(Math.PI / 2 + Double.MAX_VALUE)));
	}
	
	@Test
	public void changeDirection_LegalValue(){
		mutableWormRadius1.getVector().changeDirection(Math.PI);
		assertEquals(Math.PI / 2 + Math.PI, 
				mutableWormRadius1.getVector().getDirection(), EPS);
	}
	
	@Test
	public void isValidDirection_LegalValue(){
		assertTrue(Vector.isValidDirection(5));
	}
	
	@Test
	public void isValidDirection_IllegalValue(){
		assertFalse(Vector.isValidDirection(Double.NaN));
	}
	
	@Test
	public void canAcceptForChangeDirection_LegalCase(){
		assertTrue(wormRadius1.getVector().canAcceptForChangeDirection(30));
	}
	
	@Test
	public void canAcceptForChangeDirection_LegalCase2(){
		assertTrue(wormRadius1.getVector().canAcceptForChangeDirection(-Double.MAX_VALUE));
	}
	
	@Test
	public void canAcceptForChangeDirection_IllegalCase3(){
		assertFalse(wormFacingDownPositive.getVector().canAcceptForChangeDirection(Double.MAX_VALUE));
	}
	
	@Test
	public void canAcceptForChangeDirection_IllegalCase(){
		assertFalse(wormFacingDownNegative.getVector().canAcceptForChangeDirection(-Double.MAX_VALUE));
	}
	
	@Test
	public void isValidPosition_LegalCase(){
		assertTrue(Position.isValidCoordinate(Double.MAX_VALUE));
	}
	
	@Test
	public void isValidPosition_IllegalCase(){
		assertFalse(Position.isValidCoordinate(Double.NaN));
	}

}
