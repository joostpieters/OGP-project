package worms.model.programs.expressions;

import worms.model.BallisticObject;
import worms.model.Program;
import worms.model.programs.types.EntityType;
import worms.util.Util;

public class SearchObject extends Expression {

	public SearchObject(Expression theta, int line, int column) {
		super(line, column);
		this.theta = (DoubleLiteral) theta;
	}
	
	@Override
	public BallisticObject getResult(Program program){
		final double theta = this.theta.getResult(program);
		double currAngle = program.getPerformingWorm().getVector().getDirection() + theta;
		double currDistance;
		double bestDistance = Double.MAX_VALUE;
		BallisticObject nearestObject = null;
		
		for (BallisticObject o : program.getPerformingWorm().getWorld().getWorms()){
			if (!isValidAngle(program, currAngle, o))
				continue;
			currDistance = program.getPerformingWorm().getVector().getPosition().getDistanceTo(o.getVector().getPosition());
			if (Util.fuzzyLessThanOrEqualTo(currDistance, bestDistance)){
				bestDistance = currDistance;
				nearestObject = o;
			}
		}
		return nearestObject;
	}

	private final DoubleLiteral theta;

	@Override
	public EntityExpression getValue(Program program) {
		return new EntityExpression(new EntityType(getResult(program)), getLine(), getColumn());
	}
	
	private boolean isValidAngle(Program program, double angle, BallisticObject o){
		final double EPS = Math.atan(o.getRadius() / 
				program.getPerformingWorm().getVector().getPosition().getDistanceTo(o.getVector().getPosition()));
		return Util.fuzzyEquals(program.getPerformingWorm().getVector().getPosition().getSlope(o.getVector().getPosition()), 
				angle, EPS);
	}
}
