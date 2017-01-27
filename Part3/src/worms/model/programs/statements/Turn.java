package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.*;

public class Turn extends Statement {

	public Turn(Expression angle, int line, int column) {
		super(line, column);
		this.angle = angle;
	}

	@Override
	public boolean execute(Program program) {
		if (!program.getPerformingWorm().canTurn(((Double) angle.getResult(program))))
			return false;
		program.getHandler().turn(program.getPerformingWorm(), (Double) angle.getResult(program));
		super.afterExecution(program);
		return true;
	}

	private Expression angle;

}