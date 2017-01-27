package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.*;

public class While extends Statement {

	public While(Expression condition, Statement body, int line, int column) {
		super(line, column);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public boolean execute(Program program) {
		finishedNestedStatement = true;
		while (checkCondition(program) && finishedNestedStatement) {
			finishedNestedStatement = body.execute(program);
		}

		if (!finishedNestedStatement)
			return false;
		super.afterExecution(program);
		return true;
	}

	private boolean checkCondition(Program program) {
		if (condition.getResult(program) instanceof BoolLiteral)
			return ((BoolLiteral) condition.getResult(program)).getResult();
		else
			return (Boolean) condition.getResult(program);
	}

	private Expression condition;

	private Statement body;

	private boolean finishedNestedStatement;
}