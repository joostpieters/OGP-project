package worms.model;

import java.util.Collection;
import java.util.Random;
import java.util.Stack;

import worms.exceptions.*;

/**
 * A class implementing IFacade and its methods.
 * 
 * @version 2.0
 * @author Mathias Van Herreweghe, Bachelor Informatics,
 *         https://github.com/mathiasvh/worms
 *         
 *         Due to unexpected circumstances, I have not been able to complete the documentation and test classes.
 *         This will be done on sunday 27 april, on which you will find the documented code on my repository
 *         on github. Sorry for the inconvenience caused.
 * 
 */
public class Facade implements IFacade {

	@Override
	public Worm createWorm(World world, double x, double y, double direction, 
			double radius, String name) throws ModelException {
		try {
			return world.addWorm(x, y, direction, radius, name);
		} catch (IllegalRadiusException e) {
			throw new ModelException("Illegal value for radius");
		} catch (IllegalNameException e) {
			throw new ModelException("Illegal value for name");
		} catch (IllegalWormException e) {
			throw new ModelException("One or more illegal values for worm");
		}
	}

	@Override
	public boolean canMove(Worm worm) { 
		return worm.canMove();
	}

	@Override
	public void move(Worm worm) throws ModelException { 
		try {
			worm.move();
		} catch (IllegalMoveException e) {
			throw new ModelException("Worm does not satisfy the requirements "
					+ "to move");
		}
	}

	@Override
	public boolean canTurn(Worm worm, double angle) { 
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) { 
			worm.turn(angle);
	}

	@Override
	public void jump(Worm worm, double timeStep) throws ModelException { 
		try {
			worm.jump(timeStep);
		} catch (IllegalJumpException e) {
			throw new ModelException("Worm does not satisfy the requirements "
					+ "to jump");
		}
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) throws ModelException {
		try {
			return worm.getJumpTime(timeStep);
		} catch (IllegalJumpException e) {
			throw new ModelException("Worm does not satisfy the requirements "
					+ "to jump");
		}
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.getJumpStep(t);
	}

	@Override
	public double getX(Worm worm) { 
		return worm.getVector().getPosition().getX();
	}

	@Override
	public double getY(Worm worm) { 
		return worm.getVector().getPosition().getY();
	}

	@Override
	public double getOrientation(Worm worm) { 
		return worm.getVector().getDirection();
	}

	@Override
	public double getRadius(Worm worm) { 
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) throws ModelException { 
		try {
			worm.changeRadius(newRadius);
		} catch (IllegalRadiusException e) {
			throw new ModelException("Illegal value for radius");
		}
	}

	@Override
	public double getMinimalRadius(Worm worm) { 
		return worm.getMinRadius();
	}

	@Override
	public int getActionPoints(Worm worm) { 
		return worm.getActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) { 
		return worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) { 
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) throws ModelException { 
		try {
			worm.setName(newName);
		} catch (IllegalNameException e) {
			throw new ModelException("Illegal value for name");
		}
	}

	@Override
	public double getMass(Worm worm) { 
		return worm.getMass();
	}

	@Override
	public void addEmptyTeam(World world, String newName) { 
	}

	@Override
	public void addNewFood(World world) {
	}

	@Override
	public void addNewWorm(World world) {
		/* NOTE: PlayGameScreen.addWorm() doesn't catch ModelException while it should,
		 *	according to the assignment, this method should have been worked out defensively, 
		 *  but due to this bug I won't throw a new ModelException in my catches. */
			try {
				world.addNewWorm();
			}	catch (IllegalWormException e){} 
				catch (IllegalGameStateException e){}
	}

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public Food createFood(World world, double x, double y) {
		return null;
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		return new World(width, height, passableMap, random);
	}

	@Override
	public void fall(Worm worm) {
		worm.fall();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile();
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.getCurrentWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return new Stack<Food>();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) throws ModelException {
			return projectile.getJumpStep(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) throws ModelException { 
		try {
			return projectile.getJumpTime(timeStep);
		} catch (IllegalShootException e) {
			throw new ModelException("Projectile owner does not meet requirements.");
		} catch (IllegalJumpException e) {
			throw new ModelException("Projectile does not meet requirements.");
		}
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxHitPoints();
	}

	@Override
	public double getRadius(Food food) {
		return -1.0;
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) { 
		return worm.getCurrentWeaponName();
	}

	@Override
	public String getTeamName(Worm worm) {
		return null;
	}

	@Override
	public String getWinner(World world) {
		return world.getWinnerName();
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getWorms();
	}

	@Override
	public double getX(Food food) {
		return -1.0;
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getVector().getPosition().getX();
	}

	@Override
	public double getY(Food food) {
		return -1.0;
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getVector().getPosition().getY();
	}

	@Override
	public boolean isActive(Food food) {
		return false;
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return projectile.isActive();
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacentPosition(new Position(x, y), radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.isActive();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		return world.isImpassableSpot(new Position(x, y), radius);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		projectile.jump(timeStep);
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) throws ModelException {
		try {
			worm.shoot(yield);
		} catch (IllegalShootException e){
			throw new ModelException("This worm can't shoot.");
		} catch (IllegalJumpException e){
			throw new ModelException("Requirements aren't met to shoot with this worm.");
		} catch (IllegalYieldException e){
			throw new ModelException("Illegal value for yield");
		}
	}

	@Override
	public void startGame(World world) throws ModelException { 
		try {
			world.startGame();
		} catch (IllegalGameStateException e) {
			throw new ModelException("World does not meet requirements to start a new game.");
		}
		
	}

	@Override
	public void startNextTurn(World world) { 
		try {
			world.startNextTurn();
		} catch (IllegalGameStateException e) {
			throw new ModelException("World does not meet requirements to start a next turn.");
		}
	}

}
