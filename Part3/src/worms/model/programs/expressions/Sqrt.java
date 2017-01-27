package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Sqrt extends Expression {

	public Sqrt(Expression e, int line, int column) {
		super(line, column);
		this.e = e;
	}

	private Expression e;

	@Override
	public DoubleLiteral getValue(Program program) {
		return execute(program);
	}
	
	@Override
	public Double getResult(Program program) {
		return execute(program).getResult();
	}
	
	private DoubleLiteral execute(Program program) {
		if (!(e.getResult(program) instanceof Double))
			return null;
		if (Double.isInfinite(Math.sqrt((Double) e.getResult(program))))
			return new DoubleLiteral(new DoubleType(0.0), getLine(), getColumn());
		return new DoubleLiteral(new DoubleType(Math.sqrt((Double) e.getResult(program))), getLine(), getColumn());
	}
}