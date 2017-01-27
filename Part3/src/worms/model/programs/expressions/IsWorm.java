package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.BoolType;

public class IsWorm extends Expression {
	
	public IsWorm(Expression e, int line, int column) {
		super(line, column);
		this.entity = (EntityExpression) e;
	}
	
	@Override
	public BoolLiteral getValue(Program program) {
		return execute(program);
	}
	
	@Override
	public Boolean getResult(Program program) {
		return execute(program).getResult();
	}
	
	private BoolLiteral execute(Program program) {
		return new BoolLiteral(new BoolType(this.entity.getResult() instanceof Worm), getLine(), getColumn());
	}
	
	private EntityExpression entity;

}
