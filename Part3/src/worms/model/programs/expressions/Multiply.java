package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Multiply extends Expression {

	public Multiply(Expression e1, Expression e2, int line, int column) {
		super(line, column);
		this.e1 = e1;
		this.e2 = e2;
	}

	private Expression e1;

	private Expression e2;

	@Override
	public DoubleLiteral getValue(Program program) {
		return execute(program);
	}
	
	@Override
	public Double getResult(Program program) {
		return execute(program).getResult();
	}
	
	private DoubleLiteral execute(Program program) {
		if (!(e1.getResult(program) instanceof Double && e2.getResult(program) instanceof Double))
			return null;
		if (((Double) e1.getResult(program) * (Double) e2.getResult(program) > Double.MAX_VALUE) || 
				((Double) e1.getResult(program) * (Double) e2.getResult(program) < -(Double.MAX_VALUE)))
			return new DoubleLiteral(new DoubleType(Double.MAX_VALUE), getLine(), getColumn());
		return new DoubleLiteral(new DoubleType((Double) e1.getResult(program) * (Double) e2.getResult(program)), getLine(), getColumn());
	}
}
