package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of coordinates involving a position x and y.
 * 
 * @invar The position x must hold a valid value. 
 * 		| isValidPosition(getX())
 * @invar The position y must hold a valid value. 
 * 		| isValidPosition(getY())
 * 
 * @version 1.0
 * @author Mathias Van Herreweghe
 * 
 */

public class Coordinate {
	
	/**
	 * Initialize this new coordinate with given position x and y.
	 * 
	 * @param	x
	 * 				The x position for this new coordinate.
	 * @param	y
	 * 				The y position for this new coordinate.
	 * @post 	The new x position of this new coordinate is equal to the given x
	 *       	position. 
	 *       	| new.getX() == x
	 * @post 	The new y position of this new coordinate is equal to the given y
	 *       	position. 
	 *       	| new.getY() == y
	 * @throws	ModelException("Illegal value for x")
	 * 			This new coordinate can not have the given x position as its
	 * 			x position.
	 * 			| !isValidPosition(x)
	 * @throws	ModelException("Illegal value for y")
	 * 			This new coordinate can not have the given y position as its
	 * 			y position.
	 * 			| !isValidPosition(y)
	 */
	@Raw
	public Coordinate(double x, double y) throws ModelException {
		if (!isValidPosition(x))
			throw new ModelException("Illegal value for x");
		this.x = x;
		if (!isValidPosition(y))
			throw new ModelException("Illegal value for y");
		this.y = y;
	}
	
	/**
	 * Check whether this vector can have the given x position as its x position.
	 * 
	 * @param	pos
	 * 			The position to check.
	 * @return	True if and only if the given position is not Not-A-Number.
	 * 			| result == !Double.isNaN(pos)
	 */
	public static boolean isValidPosition(double pos) {
		return !Double.isNaN(pos);
	}
	
	/**
	 * Return the x position of this vector.
	 *	The x position of a vector determines where the vector is located on 
	 *	the x-axis.
	 */
	@Basic
	@Raw
	public double getX(){
		return this.x;
	}
	
	/**
	 * Set the x position for this vector to the given x position.
	 * 
	 * @param	x
	 * 			The new x position for this vector.
	 * @post	The new x position for this vector is equal to the given x
	 * 			position.
	 * 			| new.getX() == x
	 * @throws	ModelException("Illegal value for x")
	 * 			This vector cannot have the given x position as its x position.
	 */
	@Raw
	public void setX(double x) throws ModelException{
		if (!isValidPosition(x))
			throw new ModelException("Illegal value for x");
		this.x = x;
	}

	/**
	 * Variable registering the x position of this vector in meters.
	 */
	private double x;
	
	/**
	 * Return the y position of this vector.
	 *	The y position of a vector determines where the vector is located on the
	 *	y-axis.
	 */
	@Basic
	@Raw
	public double getY(){
		return this.y;
	}
	
	/**
	 * Set the y position for this vector to the given y position.
	 * 
	 * @param	y
	 * 			The new y position for this vector.
	 * @post	The new y position for this vector is equal to the given y
	 * 			position.
	 * 			| new.getY() == y
	 * @throws	ModelException("Illegal value for y")
	 * 			This vector cannot have the given y position as its y position.
	 */
	@Raw
	public void setY(double y) throws ModelException{
		if (!isValidPosition(y))
			throw new ModelException("Illegal value for y");
		this.y = y;
	}

	/**
	 * Variable registering the y position of this vector in meters.
	 */
	private double y;

}
