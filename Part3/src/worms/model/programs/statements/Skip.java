package worms.model.programs.statements;

import worms.model.Program;

public class Skip extends Statement {

	public Skip (int line, int column) {
		super(line, column);
	}

	@Override
	public boolean execute(Program program) {
		super.afterExecution(program);
		return true;
	}


}