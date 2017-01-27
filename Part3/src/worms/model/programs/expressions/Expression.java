package worms.model.programs.expressions;

import worms.model.Program;

public abstract class Expression {

	public Expression(int line, int column) {
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

	public abstract Expression getValue(Program program);

	public abstract Object getResult(Program program);

}
