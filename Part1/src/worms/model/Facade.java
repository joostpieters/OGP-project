package worms.model;

/**
 * A class implementing IFacade and its methods.
 * 
 * @version 1.0
 * @author Mathias Van Herreweghe, Bachelor Informatics, https://github.com/mathiasvh/worms
 * 
 */
public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) throws ModelException {
		try {
			return Worm.createWorm(x, y, direction, radius, name);
		} catch (IllegalRadiusException eRadius){
			throw new ModelException("Illegal value for radius");
		} catch (IllegalNameException eName){
			throw new ModelException("Illegal value for name");
		}
		
	}
	
	@Override
	public Worm createWorm() {
		return createWorm(0, 0, Math.PI / 2, 0.5, "default");
	}
	
	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) throws ModelException{
		try {
			worm.move(nbSteps);		
		} catch (IllegalMoveException e){
			throw new ModelException("Worm does not satisfy the requirements " +
										"to move");
		}
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) throws ModelException {
		try {
			worm.turn(angle);		
		} catch (IllegalTurnException e){
			throw new ModelException("Worm does not satisfy the requirements " +
										"to turn");
		}
	}

	@Override
	public void jump(Worm worm) throws ModelException {
		try {
			worm.jump();
		}
		catch (IllegalJumpException e){
			throw new ModelException("Worm does not satisfy the requirements " +
										"to jump");
		}
	}

	@Override
	public double getJumpTime(Worm worm) throws ModelException {
		try {
			return worm.getJumpTime();
		}
		catch (IllegalJumpException e){
			throw new ModelException("Worm does not satisfy the requirements " +
										"to jump");
		}
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.getJumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getVector().getCoordinate().getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getVector().getCoordinate().getY();
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
		} catch (IllegalRadiusException e){
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
	public void rename(Worm worm, String newName) throws ModelException{
		try {
			worm.setName(newName);
		} catch (IllegalNameException e){
			throw new ModelException("Illegal value for name");
		}
		
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
