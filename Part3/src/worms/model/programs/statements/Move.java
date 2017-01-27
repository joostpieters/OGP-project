package worms.model.programs.statements;

import worms.model.Program;

public class Move extends Statement {

	public Move(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean execute(Program program) {
		if (!program.getPerformingWorm().canMove())
			return false;
		program.getHandler().move(program.getPerformingWorm());
		super.afterExecution(program);
		return true;
	}
}