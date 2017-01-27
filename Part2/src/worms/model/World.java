package worms.model;

import java.util.LinkedList;
import java.util.Random;

import worms.exceptions.IllegalGameStateException;
import worms.exceptions.IllegalSizeException;
import worms.exceptions.IllegalWormException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of worlds involving a width, height, passable map and a random generator.
 * 
 * @invar Each world can have its width as its width. | isValidWidth(getWidth())
 * @invar Each world can have its height as its height. |
 *        isValidHeight(getHeight())
 * @invar Each world can have at most one live projectile |
 *        isValidAmountOfProjectiles(getProjectiles())
 * @invar At all times, each game object is located in at most one world. | ???
 * @invar Once a game has been started, it is not permitted to add further worms
 *        to that game world. ?? | canAddWorm()
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics,
 *         https://github.com/mathiasvh/worms
 * 
 */

public class World {

	public World(final double width, final double height,
			final boolean[][] passableMap, final Random random) throws IllegalSizeException {
		if (!isValidWidth(width) || !isValidHeight(height))
			throw new IllegalSizeException(this, width, height);
		this.WIDTH = width;
		this.HEIGHT = height;
		this.passableMap = passableMap;
		this.random = random;
		this.running = false;
	}

	/**
	 * Check whether the given width is a valid width.
	 * 
	 * @param width
	 *            The width to check.
	 * @return True if and only if the given width is positive and if the width
	 *         is less than or equal to the upper bound width. | result == (0 <=
	 *         width && width <= UPPERBOUND_WIDTH)
	 */
	public static boolean isValidWidth(final double width) {
		return LOWERBOUND_WIDTH <= width && width <= UPPERBOUND_WIDTH && !Double.isInfinite(width);
	}

	/**
	 * Return the width of this world. The width of a world expresses the width
	 * in meters from that world.
	 */
	@Basic
	@Raw
	public double getWIDTH() {
		return this.WIDTH;
	}

	/**
	 * Variable registering the width (in meters) of a world.
	 */
	private final double WIDTH;

	/**
	 * Check whether the given height is a valid height.
	 * 
	 * @param height
	 *            The height to check.
	 * @return True if and only if the given height is positive and if the
	 *         height is less than or equal to the upper bound height. | result
	 *         == (0 <= height && height <= UPPERBOUND_HEIGHT)
	 */
	public static boolean isValidHeight(final double height) {
		return LOWERBOUND_HEIGHT <= height && height <= UPPERBOUND_HEIGHT && !Double.isInfinite(height);
	}

	/**
	 * Return the height of this world. The height of a world expresses the
	 * height in meters from that world.
	 */
	@Basic
	@Raw
	public double getHEIGHT() {
		return this.HEIGHT;
	}

	/**
	 * Variable registering the height (in meters) of a world.
	 */
	private final double HEIGHT;

	/**
	 * Return the upper bound width of a world. The upper bound with of a world
	 * determines how big the width of the world can be, at max.
	 */
	@Basic
	@Immutable
	public static double getUPPERBOUND_WIDTH() {
		return UPPERBOUND_WIDTH;
	}

	/**
	 * Variable registering the upper bound width (in meters) for all worlds.
	 */
	private static final double UPPERBOUND_WIDTH = Double.MAX_VALUE;

	/**
	 * Return the upper bound height of a world. The upper bound height of a
	 * world determines how big the height of the world can be, at max.
	 */
	@Basic
	@Immutable
	public static double getUPPERBOUND_HEIGHT() {
		return UPPERBOUND_HEIGHT;
	}

	/**
	 * Variable registering the upper bound height (in meters) for all worlds.
	 */
	private static final double UPPERBOUND_HEIGHT = Double.MAX_VALUE;

	public static double getLOWERBOUND_WIDTH(){
		return LOWERBOUND_WIDTH;
	}
	/**
	 * Variable registering the lower bound width (in meters) for all worlds.
	 */
	private static final double LOWERBOUND_WIDTH = 0;

	public static double getLOWERBOUND_HEIGHT(){
		return LOWERBOUND_HEIGHT;
	}
	
	/**
	 * Variable registering the lower bound height (in meters) for all worlds.
	 */
	private static final double LOWERBOUND_HEIGHT = 0;

	/**
	 * Constructs and returns a worm, consisting of the given properties. This
	 * new worm will also be added to this world.
	 * 
	 * @param x
	 *            The x-coordinate for the new worm.
	 * @param y
	 *            The y-coordinate for the new worm.
	 * @param direction
	 *            The direction for the new worm.
	 * @param radius
	 *            The radius for the new worm.
	 * @param name
	 *            The name for the new worm.
	 * @post The new x-coordinate of the new worm is equal to the given
	 *       x-coordinate. | worm.getVector().getPosition().getX() == x
	 * @post The new y-coordinate of the new worm is equal to the given
	 *       y-coordinate. | worm.getVector().getPosition().getY() == y
	 * @post The new direction of the new worm is equal to the given direction.
	 *       | worm.getVector().getDirection() == direction
	 * @post The new radius of the new worm is equal to the given radius. |
	 *       worm.getRadius() == radius
	 * @post The new name of the new worm is equal to the given name. |
	 *       worm.getName() == name
	 * @post The mass of the new worm is equal to the outcome of the formula
	 *       intended for it. | worm.getMass() == calculateMass(radius)
	 * @post The maximum hit points of the new worm is equal to the formula
	 *       intended for it. | worm.getMaximumHitPoints() ==
	 *       calculateMaxHitPoints(worm.getMass())
	 * @post The new hit points of the new worm is equal to its maximum hit
	 *       points. | worm.getHitPoints() == worm.getMaxHitPoints()
	 * @post The maximum action points of the new worm is equal to the formula
	 *       intended for it. | worm.getMaximumActionPoints() ==
	 *       calculateMaxActionPoints(getMass())
	 * @post The new action points of the new worm is equal to its maximum
	 *       action points. | worm.getActionPoints() ==
	 *       worm.getMaxActionPoints()
	 * @return The newly created worm. | result == new Worm(new Vector(new
	 *         Position(x,y), direction), | radius, name)
	 */
	@Raw
	public Worm addWorm(final double x, final double y, final double direction, final double radius,
		final String name) throws IllegalWormException {
		final Worm worm = new Worm(new Vector(new Position(x, y), direction),
				radius, name, this);
		if(!canHaveAsWorm(worm))
			throw new IllegalWormException(worm);
		if (!canAddNewWorm(worm))
			throw new IllegalGameStateException(this);
		this.worms.add(worm);
		randomWormNumber++;
		return worm;
	}
	
	public boolean canAddNewWorm(final Worm worm) {
		return !this.running;
	}
	
	public boolean canHaveAsWorm(final Worm worm) {
		return worm.getVector().getPosition() != null &&
				isAdjacentPosition(worm.getVector().getPosition(), worm.getRadius()) && 
				liesWithinWorld(worm.getVector().getPosition(), true);
	}

	public void addNewWorm() {
		final double x = LOWERBOUND_WIDTH + this.random.nextDouble() * this.WIDTH;
		final double y = LOWERBOUND_HEIGHT + this.random.nextDouble() * this.HEIGHT;
		final double minRadius = Worm.getPREDEFINED_MIN_RADIUS1();
		final double radius = minRadius + (minRadius * 2 - minRadius)
				* this.random.nextDouble();
		final double direction = 2 * Math.PI * this.random.nextDouble();
		final Position pos = this.findAdjacentPosition(new Position(x, y), radius);
		final String name = "Random worm " + randomWormNumber;
		try {
			addWorm(pos.getX(), pos.getY(), direction, radius, name);
		} catch (NullPointerException e){
			throw new IllegalWormException(new Worm(new Vector(pos, direction),
					radius, name, this));
		}
	}
	
	private static int randomWormNumber = 1;

	/**
	 * Return the worms of this world. The worms of this world determines the
	 * worms that are situated in this world.
	 */
	@Basic
	@Raw
	public LinkedList<Worm> getWorms() {
		return this.worms;
	}
	
	public Worm getOverlappingWorms(final Projectile projectile, final Position currPos) {
		for (Worm worm : worms)
			if (worm.overlapsWith(projectile, currPos) && worm != projectile.getOwner())
				return worm;
		return null;
	}
	
	public boolean hasOverlappingWorm(final  Projectile projectile, final Position currPos){
		return getOverlappingWorms(projectile, currPos) != null;
	}
	
	public boolean hasNoDoubleWorms(){
		int sameWorm = 0;	
	
		for (Worm wormA : worms) {
			if (sameWorm >= 2)
				return false;
			sameWorm = 0;		
			for(Worm wormB : worms)
				if (wormA == wormB)
					sameWorm++;
		}
		return true;
	}

	/**
	 * Variable registering the worms of a world.
	 */
	private LinkedList<Worm> worms = new LinkedList<Worm>();

	/**
	 * Return the only active projectile of this world. The active projectile of
	 * a world determines the only active projectile.
	 */
	@Basic
	@Raw
	public Projectile getActiveProjectile() {
		return this.activeProjectile;
	}
	
	public void setActiveProjectile(final Projectile projectile) {
		this.activeProjectile = projectile;
	}

	/**
	 * Variable registering the only active projectile of this world
	 */
	private Projectile activeProjectile = null;

	/**
	 * Calculates scale of the width of this world.
	 * 
	 * @return The width of this world (in meters) divided by the length of this
	 *         world its passable map. | result ==
	 *         this.WIDTH / this.passableMap[0].length
	 */
	public double getWidthScale() {
		return this.WIDTH / this.passableMap[0].length;
	}

	/**
	 * Calculates scale of the height of this world.
	 * 
	 * @return The height of this world (in meters) divided by the depth of this
	 *         world its passable map. | result ==
	 *         this.HEIGHT / this.passableMap.length
	 */
	public double getHeightScale() {
		return this.HEIGHT / this.passableMap.length;
	}
	
	public double getMinimumScale() {
		return Math.min(getWidthScale(), getHeightScale());
	}

	private Position getCenter(){
		return new Position(this.WIDTH / 2, this.HEIGHT / 2);
	}
	
	private Position findAdjacentPosition(final Position from, final double radius){
		final Position centerPos = getCenter();
		double tmpX = from.getX();
		double tmpY = from.getY();
		Position currPos = new Position(tmpX, tmpY);

		while (centerPos.getDistanceTo(currPos) > radius && 
				!isAdjacentPosition(currPos, radius)) {
			if (tmpX < centerPos.getX()) {
				tmpX += getMinimumScale();
			}
			if (tmpX > centerPos.getX()) {
				tmpX -= getMinimumScale();
			}
			if (tmpY < centerPos.getY()) {
				tmpY += getMinimumScale();
			}
			if (tmpY > centerPos.getY()) {
				tmpY -= getMinimumScale();
			}
			currPos.setX(tmpX);
			currPos.setY(tmpY);
				
		}
		if (centerPos.getDistanceTo(currPos) > radius)
			return currPos;
		else
			return null;
	}

	public boolean isAdjacentPosition(final Position pos, final double radius){
		if (!this.isPassableSpot(pos, radius))
			return false;
			
		for (double radiusToCheck : Entity.getRadiuses(radius, 0.1, false, 0.01))
			if (!this.isPassableSpot(pos, radiusToCheck))
				return true;
		return false;
	}
	
	public boolean isPassableSpot(final Position center, final double radius){
		Position[] toCheckList = center.getCircularPositions(radius);
		
		for (Position toCheck : toCheckList)
			if (liesWithinWorld(toCheck, true) && 
					!this.isPassablePosition(toCheck))
				return false;
		
		return true;
	}

	private boolean isPassablePosition(final Position pos) {	
		Position posInPix = pos.getPixelPosition(getWidthScale(), getHeightScale());
		
		if(posInPix.getX() < 0 || passableMap.length - posInPix.getY() < 0 || 
				posInPix.getX() >= passableMap[0].length || passableMap.length - posInPix.getY() >= passableMap.length) 
			return false;

		return passableMap[passableMap.length - (int) posInPix.getY()][(int) posInPix.getX()];
	}
	
	public boolean isImpassableSpot(final Position center, final double radius){
		return !isPassableSpot(center, radius);
	}

	/**
	 * Variable registering a map which holds boolean values whether or not a
	 * specific zone is passable.
	 */
	private final boolean[][] passableMap;

	/**
	 * Variable registering a random generator.
	 */
	private final Random random;

	public void startNextTurn() throws IllegalGameStateException{
		this.activeProjectile = null;
		removeDeadWorms();
		if (!canStartNextTurn())
			throw new IllegalGameStateException(this);
		currentWormIdx++;
		if (currentWormIdx >= worms.size())
			currentWormIdx=0;
		worms.get(currentWormIdx).initializeForTurn();
	}
	
	public boolean canStartNextTurn() {
		return activeProjectile == null;
	}
	
	public boolean shouldStartNextTurn() {
		return getCurrentWorm().getActionPoints() == 0 || getCurrentWorm().getHitPoints() == 0;
	}
	
	public boolean canStartGame() {
		return canStartNextTurn() && worms.size() > 0;
	}
	
	public void startGame() throws IllegalGameStateException {
		if (!canStartGame())
			throw new IllegalGameStateException(this);
		this.running = true;
		this.currentWormIdx = 0;
	}

	public boolean isGameFinished() {
		return !this.running;
	}

	/**
	 * Variable registering whether or not this world is still running.
	 */
	private boolean running;
	
	public Worm getCurrentWorm(){
		try {
			return worms.get(currentWormIdx);
		} catch (IndexOutOfBoundsException e){
			currentWormIdx++;
			if (currentWormIdx >= worms.size())
				currentWormIdx=0;
			return worms.get(currentWormIdx);
		}		
	}
	
	/**
	 * Variable registering the index of the current worm in the worms collection.
	 */
	private int currentWormIdx;
	
	public String getWinnerName(){
		if (this.worms.size() > 0)
			return this.worms.getFirst().getName();
		else
			return "absent";
	}
	
	public boolean liesWithinWorld(final Position pos, final double radius, final boolean inMeters){
		Position tempPos = new Position(pos.getX(), pos.getY());
				if (!inMeters)
					tempPos = pos.getMeterPosition(getWidthScale(), getHeightScale());
				if(tempPos.getX() - radius < LOWERBOUND_WIDTH || 
						tempPos.getY() - radius < LOWERBOUND_HEIGHT || 
						tempPos.getX() + radius > UPPERBOUND_WIDTH || 
						tempPos.getY() + radius > UPPERBOUND_HEIGHT) 
					return false;
		return true;
	}
	
	public boolean liesWithinWorld(final Position pos, final boolean inMeters){
		return liesWithinWorld(pos, 0, inMeters);
	}
	
	public void terminate(final BallisticObject o){
		o.setInActive();
		if (o instanceof Worm)
			worms.remove(o);
	}
	
	public void removeDeadWorms() {
		for (Worm worm : worms)
			if (!worm.isActive() || worm.getHitPoints() <= 0)
				terminate(worm);
		if (worms.size() <= 1)
			this.running = false;
	}
	
	/**
	 * Return the standard earth acceleration.
	 * 	The standard earth acceleration determines how fast the standard
	 * 	acceleration (m/s²) on Earth is.
	 */
	@Basic
	@Immutable
	public static double getSTD_ACCEL_EARTH(){
		return STD_ACCEL_EARTH;
	}
	
	/**
	 * Variable registering the Earth's standard acceleration(m/s²).
	 */
	private static final double STD_ACCEL_EARTH = 9.80665;
}
