package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;

public class SelfWormExpression extends Expression {
	
	public SelfWormExpression(int line, int column) {
		super(line, column);
	}
	
	@Override
	public SelfWormExpression getValue(Program program) {
		return this;
	}
	
	@Override
	public Worm getResult(Program program) {
		return program.getPerformingWorm();
	}
}
