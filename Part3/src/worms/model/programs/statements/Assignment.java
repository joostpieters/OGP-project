package worms.model.programs.statements;

import worms.model.Entity;
import worms.model.Program;
import worms.model.programs.expressions.*;
import worms.model.programs.types.*;
import worms.model.BallisticObject;

public class Assignment extends Statement {

	public Assignment(String n, Expression v, int line, int column) {
		super(line, column);
		this.name = n;
		this.value = v;
	}

	@Override
	public boolean execute(Program program) {
		if (value.getResult(program) instanceof DoubleLiteral ||
				value.getResult(program) instanceof BoolLiteral ||
					value.getResult(program) instanceof EntityExpression)
			program.addGlobal(name, (Expression) value.getResult(program));
		else if (value.getResult(program) instanceof Double)
			program.addGlobal(name, new DoubleLiteral(new DoubleType((Double) value.getResult(program)), -1, -1));
		else if (value.getResult(program) instanceof Boolean)
			program.addGlobal(name, new BoolLiteral(new BoolType((Boolean) value.getResult(program)), -1, -1));
		else if (value.getResult(program) instanceof Entity)
			program.addGlobal(name, new EntityExpression(new EntityType((BallisticObject) value.getResult(program)), -1, -1));
		super.afterExecution(program);
		return true;
	}

	private String name;

	private Expression value;
}