package worms.model.programs.statements;

import worms.model.Program;

public abstract class Statement {
	
	public Statement(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return this.line;
	}

	private int line;

	public int getColumn() {
		return this.column;
	}
	
	private int column;

	public abstract boolean execute(Program program);

	public void afterExecution(Program program) {
		program.incrementInstructionNb();
	}

}
