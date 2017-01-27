package worms.model.programs.expressions;

import worms.model.Entity;
import worms.model.Program;
import worms.model.programs.types.*;

public class Equality extends Expression {
	
	public Equality(Expression e1, Expression e2, int line, int column) {
		super(line, column);
		this.e1 = e1;
		this.e2 = e2;
	}

	private Expression e1;

	private Expression e2;

	@Override
	public BoolLiteral getValue(Program program) {
		return calculate(program);
	}
	
	@Override
	public Boolean getResult(Program program) {
		return calculate(program).getResult();
	}
	
	private BoolLiteral calculate(Program program) {
		if (e1 instanceof DoubleLiteral && e2 instanceof DoubleLiteral) {		
			return new BoolLiteral(new BoolType(((Double) e1.getResult(program)).compareTo((Double) e2.getResult(program)) == 0), getLine(), getColumn());
		} else if (e1 instanceof BoolLiteral && e2 instanceof BoolLiteral) {	
			return new BoolLiteral(new BoolType(((Boolean) e1.getResult(program)).compareTo((Boolean) e2.getResult(program)) == 0), getLine(), getColumn());
		} else if (e1 instanceof EntityExpression && e2 instanceof EntityExpression) {		
			return new BoolLiteral(new BoolType((Entity) e1.getResult(program) == (Entity) e2.getResult(program)), getLine(), getColumn());
		}
		return new BoolLiteral(new BoolType(false), getLine(), getColumn());
	}
}
