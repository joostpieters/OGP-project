package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.DoubleLiteral;
import worms.model.programs.expressions.Expression;
import worms.model.programs.types.DoubleType;

public class Fire extends Statement {

	public Fire(Expression yield, int line, int column) {
		super(line, column);
		this.yield = (DoubleLiteral) yield;
	}

	@Override
	public boolean execute(Program program) {
		if (!program.getPerformingWorm().canShoot())
			return false;
		program.getHandler().fire(program.getPerformingWorm(), DoubleType.toInt(yield.getResult(program)));
		super.afterExecution(program);
		return true;
	}

	private DoubleLiteral yield;


}