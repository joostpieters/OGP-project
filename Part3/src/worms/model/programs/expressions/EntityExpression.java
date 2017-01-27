package worms.model.programs.expressions;

import worms.model.BallisticObject;
import worms.model.Program;
import worms.model.programs.types.EntityType;

public class EntityExpression extends Expression {

	public EntityExpression(EntityType entity, int line, int column) {
		super(line, column);
		this.value = entity;
	}
	
	@Override
	public EntityExpression getValue(Program program) {
		return this;
	}
	
	public BallisticObject getResult(){
		return this.value.getValue();
	}
	
	@Override
	public BallisticObject getResult(Program program) {
		return getResult();
	}

	private final EntityType value;
}
