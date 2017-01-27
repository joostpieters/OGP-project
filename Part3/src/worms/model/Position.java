package worms.model;

import worms.exceptions.IllegalCoordinateException;
import worms.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of positions involving a x-coordinate and a y-coordinate.
 * 
 * @invar The x-coordinate must hold a valid value. 
 * 		| isValidCoordinate(getX())
 * @invar The y-coordinate must hold a valid value. 
 * 		| isValidCoordinate(getY())
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe
 * 
 */
public class Position {
	
	/**
	 * Initialize new position with given position x and y.
	 * 
	 * @param	x
	 * 			The x-coordinate for this new position.
	 * @param	y
	 * 			The y-coordinate for this new position.
	 * @post 	The new x-coordinate of this new position is equal to the given
	 *       	x-coordinate. 
	 *       	| new.getX() == x
	 * @post 	The new y-coordinate of this new position is equal to the given
	 *       	y-coordinate. 
	 *       	| new.getY() == y
	 * @throws	IllegalCoordinateException(x)
	 * 			This new position can not have the given x-coordinate as its
	 * 			x-coordinate.
	 * 			| !isValidCoordinate(x)
	 * @throws	IllegalCoordinateException(y)
	 * 			This new position can not have the given y-coordinate as its
	 * 			y-coordinate.
	 * 			| !isValidCoordinate(y)
	 */
	@Raw
	public Position(double x, double y) throws IllegalCoordinateException {
		if (!isValidCoordinate(x))
			throw new IllegalCoordinateException(x);
		this.x = x;
		if (!isValidCoordinate(y))
			throw new IllegalCoordinateException(y);
		this.y = y;
	}
	
	/**
	 * Check whether a position can have the given coordinate as a coordinate.
	 * 
	 * @param	cor
	 * 			The coordinate to check.
	 * @return	True if and only if the given coordinate is not Not-A-Number.
	 * 			| result == !Double.isNaN(cor)
	 */
	public static boolean isValidCoordinate(double cor) {
		return !Double.isNaN(cor);
	}
	
	/**
	 * Return the x-coordinate of this position.
	 *	The x-coordinate of a position determines what the position is on 
	 *	a x-axis.
	 */
	@Basic
	@Raw
	public double getX(){
		return this.x;
	}
	
	/**
	 * Set the x-coordinate for this position to the given x-coordinate.
	 * 
	 * @param	x
	 * 			The new x-coordinate for this position.
	 * @post	The new x-coordinate for this position is equal to the given
	 * 			x-coordinate.
	 * 			| new.getX() == x
	 * @throws	IllegalCoordinateException(x)
	 * 			This position cannot have the given x-coordinate as its x-coordinate.
	 * 			| !isValidCoordinate(x)
	 */
	@Raw
	public void setX(double x) throws IllegalCoordinateException{
		if (!isValidCoordinate(x))
			throw new IllegalCoordinateException(x);
		this.x = x;
	}

	/**
	 * Variable registering the x-coordinate of this position in meters.
	 */
	private double x;
	
	/**
	 * Return the y-coordinate of this position.
	 *	The y-coordinate of a position determines where the position is on
	 *	a y-axis.
	 */
	@Basic
	@Raw
	public double getY(){
		return this.y;
	}
	
	/**
	 * Set the y-coordinate for this position to the given y-coordinate.
	 * 
	 * @param	y
	 * 			The new y-coordinate for this position.
	 * @post	The new y-coordinate for this position is equal to the given
	 * 			y-coordinate.
	 * 			| new.getY() == y
	 * @throws	IllegalCoordinateException(y)
	 * 			This position cannot have the given y-coordinate as its y-coordinate.
	 * 			| !isValidCoordinate(y)
	 */
	@Raw
	public void setY(double y) throws IllegalCoordinateException{
		if (!isValidCoordinate(y))
			throw new IllegalCoordinateException(y);
		this.y = y;
	}

	/**
	 * Variable registering the y-coordinate of this position in meters.
	 */
	private double y;
	
	/**
	 * Get the pixel position of this position, dependant on the given 
	 * 	widthscale and heightscale.
	 * @param	widthScale
	 * 			The width scale used for the formula.
	 * @param	heightScale
	 * 			The height scale used for the formula.
	 * @return	The pixel position of this position.
	 * 			| result == new Position(Math.round(getX() / widthScale),
	 *			|				Math.round(getY() / heightScale))
	 */
	public Position getPixelPosition(double widthScale, double heightScale){
		return new Position(Math.floor(getX() / widthScale),
							Math.ceil(getY() / heightScale));
	}
	
	/**
	 * Get the meter position of this position (in pixels), dependant on the given 
	 * 	widthscale and heightscale.
	 * @param	widthScale
	 * 			The width scale used for the formula.
	 * @param	heightScale
	 * 			The height scale used for the formula.
	 * @return	The meter position of this position.
	 * 			| result == new Position(getX() * widthScale,
	 *			|				getY() * heightScale)
	 */
	public Position getMeterPosition(double widthScale, double heightScale){
		return new Position(getX() * widthScale,
							getY() * heightScale);
	}
	
	/**
	 * Get the pixel x-coordinate of the x-coordinate of this position (in meters), 
	 * 	dependant on the given scale.
	 * @param	scale
	 * 			The scale used for the formula.
	 * @return	The x-coordinate (in pixels) of the x-coordinate of this position.
	 * 			| result == (int) Math.round(getX() / scale)
	 */
	public int toXPixel(double scale, int max){
		return (int) Math.floor(getX() / scale);
	}
	
	/**
	 * Get the pixel y-coordinate of the y-coordinate of this position (in meters), 
	 * 	dependant on the given scale.
	 * @param	scale
	 * 			The scale used for the formula.
	 * @return	The y-coordinate (in pixels) of the y-coordinate of this position.
	 * 			| result == (int) Math.round(getY() / scale)
	 */
	public int toYPixel(double scale, int max){
		return (int) Math.ceil(getY() / scale);
	}
	
	/**
	 * Get the meter x-coordinate of the x-coordinate of this position (in pixels), 
	 * 	dependant on the given scale.
	 * @param	scale
	 * 			The scale used for the formula.
	 * @return	The x-coordinate (in meters) of the x-coordinate of this position.
	 * 			| result == getX() * scale
	 */
	public double toXMeters(double scale){
		return getX() * scale;
	}
	
	/**
	 * Get the meter y-coordinate of the y-coordinate of this position (in pixels), 
	 * 	dependant on the given scale.
	 * @param	scale
	 * 			The scale used for the formula.
	 * @return	The y-coordinate (in meters) of the y-coordinate of this position.
	 * 			| result == getY() * scale
	 */
	public double toYMeters(double scale){
		return getY() * scale;
	}
	
	/**
	 * Calculates and returns positions around the center of this position,
	 * at a distance equal to the given radius.
	 * 
	 * @param	radius
	 * 			The radius that will be used as distance to calculate the positions for.
	 * @return	The positions around this position at a distance equal to the given radius.
	 * 			| result == getCircularPositions(radius, 0, 2 * Math.PI, defaultAngleAccuracy)
	 */
	public Position[] getCircularPositions(double radius){
		return getCircularPositions(radius, 0, 2 * Math.PI, defaultAngleAccuracy);	
	}
	
	/**
	 * Calculates and returns positions around the center of this position,
	 * 	at a distance equal to the given radius, only positions between minAngle and maxAngle
	 * 	will be calculated and returned.
	 * 
	 * @param	radius
	 * 			The radius that will be used as distance to calculate the positions for.
	 * @param	minAngle
	 * 			The angle for which the calculations for positions will be started upon.
	 * @param	maxAngle
	 * 			The angle for which the calculations for positions will be ended.
	 * @param	intervalAngle
	 * 			The interval angle steps that will be used to iterate from minAngle to maxAngle.
	 * @return	The positions around this position at a distance equal to the given radius.
	 */
	public Position[] getCircularPositions(double radius, double minAngle, double maxAngle, double intervalAngle){
		final int amountOfPositions = (int) Math.round(((maxAngle - minAngle) / intervalAngle));
		Position[] positions = new Position[amountOfPositions];
		Position tmpPos = null;
		double currAngle = minAngle;
		final double objX = getX();
		final double objY = getY();
		
		for (int counter = 0; counter < positions.length; counter++){
			tmpPos = new Position
					(objX + Math.cos(currAngle) * (radius - Util.DEFAULT_EPSILON),
					 objY + Math.sin(currAngle) * (radius - Util.DEFAULT_EPSILON));
			positions[counter] = tmpPos;
			currAngle += intervalAngle;
		}
		return positions;		
	}
	
	/**
	 * A default angle accuracy for calculations.
	 */
	private static final double defaultAngleAccuracy = Math.PI/8;
	
	/**
	 * Calculates and returns the distance between this position and the given position.
	 * 
	 * @param	end
	 * 			The end position for which the distance will be calculated.
	 * @return	The distance between this position and the given position.
	 * 			| result == Math.sqrt( Math.pow(getX() - end.getX(), 2) + 
	 * 			|				Math.pow( getY() - end.getY(), 2))
	 */
	public double getDistanceTo(Position end) {
		return Math.sqrt( Math.pow(getX() - end.getX(), 2) + Math.pow( getY() - end.getY(), 2));
	}

	/**
	 * Calculates and returns the slope between this position and the given position.
	 * 
	 * @param	end
	 * 			The end position for which the slope will be calculated.
	 * @return	Zero if and only if the x-coordinate of this position is equal to the x-coordinate
	 * 			of the given position.
	 * 			| result == 0.0
	 * @return	Otherwise, the slope between this position and the given position will be returned.
	 * 			| result == Math.atan(( getY() - end.getY()) 
	 *			|				/ (getX() - end.getX()))
	 */
	public double getSlope(Position end){
		if (getX() == end.getX())
			return 0.0;
		return Math.atan(( getY() - end.getY()) 
					/ (getX() - end.getX()));
	}
	
	public boolean equals (Position other){
		return getX() == other.getX() && getY() == other.getY();
	}
}
