package worms.model.programs.statements;

import java.util.List;

import worms.model.Program;

public class Sequence extends Statement {

	public Sequence(List<Statement> statements, int line, int column) {
		super(line, column);
		this.statements = statements;
	}

	private List<Statement> statements;

	@Override
	public boolean execute(Program program) {
		for (Statement statement : statements)
			if (statement.execute(program) == false)
				return false;

		super.afterExecution(program);
		return true;
	}

}