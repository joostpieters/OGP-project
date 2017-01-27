package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.Expression;

public class Print extends Statement {

	public Print(Expression e, int line, int column) {
		super(line, column);
		this.e = e;
	}

	@Override
	public boolean execute(Program program) {
		System.out.println(e.getResult(program));
		super.afterExecution(program);
		return true;
	}

	private Expression e;

}