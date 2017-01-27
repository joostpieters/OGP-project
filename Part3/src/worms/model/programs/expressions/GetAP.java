package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;

public class GetAP extends Expression {

	public GetAP(Expression entity, int line, int column) {
		super(line, column);
		this.entity = (EntityExpression) entity;
	}

	@Override
	public EntityExpression getValue(Program program) {
		return entity;
	}

	@Override
	public Integer getResult(Program program) {
		if (!(entity.getResult() instanceof Worm))
			return null;
		return ((Worm) entity.getResult()).getActionPoints();
	}

	private final EntityExpression entity;
}
