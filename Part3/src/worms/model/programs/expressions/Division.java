package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Division extends Expression {

	public Division(Expression e1, Expression e2, int line, int column) {
		super(line, column);
		this.e1 = (DoubleLiteral) e1;
		this.e2 = (DoubleLiteral) e2;
	}

	private DoubleLiteral e1;

	private DoubleLiteral e2;

	@Override
	public DoubleLiteral getValue(Program program) {
		return calculate(program);
	}

	@Override
	public Double getResult(Program program) {
		return calculate(program).getResult();
	}
	
	private DoubleLiteral calculate(Program program) {
		if ((e1.getResult() / e2.getResult() > Double.MAX_VALUE)
				|| (e1.getResult() / e2.getResult() < -(Double.MAX_VALUE)))
			return new DoubleLiteral(new DoubleType(Double.MAX_VALUE), getLine(), getColumn());
		return new DoubleLiteral(new DoubleType(e1.getResult() / e2.getResult()), getLine(), getColumn());
	}
}