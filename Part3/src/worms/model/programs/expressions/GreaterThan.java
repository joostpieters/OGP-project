package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BoolType;

public class GreaterThan extends Expression {

	public GreaterThan(Expression e1, Expression e2, int line, int column) {
		super(line, column);
		this.e1 = e1;
		this.e2 = e2;
	}

	private Expression e1;

	private Expression e2;

	@Override
	public BoolLiteral getValue(Program program) {
		return execute(program);
	}
	
	@Override
	public Boolean getResult(Program program) {
		return execute(program).getResult();
	}
	
	private BoolLiteral execute(Program program) {
		if (!(e1.getResult(program) instanceof Double && e2.getResult(program) instanceof Double))
			return null;
		return new BoolLiteral(new BoolType((Double) e1.getResult(program) > (Double) e2.getResult(program)), getLine(), getColumn());
	}

}
