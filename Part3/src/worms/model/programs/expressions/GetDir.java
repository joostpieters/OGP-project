package worms.model.programs.expressions;

import worms.model.BallisticObject;
import worms.model.Program;

public class GetDir extends Expression {

	public GetDir(Expression entity, int line, int column) {
		super(line, column);
		this.entity = (EntityExpression) entity;
	}

	@Override
	public EntityExpression getValue(Program program) {
		return entity;
	}

	public Double getResult() {
		if (!(entity.getResult() instanceof BallisticObject))
			return null;
		return ((BallisticObject) entity.getResult()).getVector().getDirection();
	}
	
	@Override
	public Double getResult(Program program) {
		return getResult();
	}

	private final EntityExpression entity;
}
