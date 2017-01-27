package worms.model.programs.expressions;

import worms.model.BallisticObject;
import worms.model.Program;

public class GetRadius extends Expression {

	public GetRadius(Expression entity, int line, int column) {
		super(line, column);
		this.entity = entity;
	}

	@Override
	public Expression getValue(Program program) {
		return entity;
	}
	
	@Override
	public Double getResult(Program program) {
		if (!(entity.getResult(program) instanceof BallisticObject))
			return null;
		return ((BallisticObject) entity.getResult(program)).getRadius();
	}

	private final Expression entity;
}
