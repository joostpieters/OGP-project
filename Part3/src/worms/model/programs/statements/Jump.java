package worms.model.programs.statements;

import worms.model.Program;

public class Jump extends Statement {

	public Jump(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean execute(Program program) {
		if (!program.getPerformingWorm().canJump())
			return false;
		program.getHandler().jump(program.getPerformingWorm());
		super.afterExecution(program);
		return true;
	}
}