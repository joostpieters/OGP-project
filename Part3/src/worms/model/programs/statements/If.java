package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.*;

public class If extends Statement {

	public If(Expression condition, Statement then, Statement otherwise, int line, int column) {
		super(line, column);
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
	}

	public boolean execute(Program program) {
		if (condition == null || then == null || otherwise == null)
			return false; // Backup plan for unimplemented methods (one-person group left-outs)
		boolean myCondition;
		if (condition.getResult(program) instanceof BoolLiteral)
			myCondition = ((BoolLiteral) condition.getResult(program)).getResult();
		else
			myCondition = (Boolean) condition.getResult(program);

		if (cancelled == null){
			if (myCondition) {
				if (then.execute(program) == false) {
					cancelled = then;
					return false;
				}
			} else {
				if (otherwise.execute(program) == false) {
					cancelled = otherwise;
					return false;
				}	
			}
		} else
			if (cancelled.execute(program) == false)
				return false;

		cancelled = null;
		super.afterExecution(program);
		return true;
	}

	private Expression condition;

	private Statement then;

	private Statement otherwise;

	private Statement cancelled;

}