package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.*;
import worms.model.programs.types.*;

public class VariableAccess extends Expression {

	public VariableAccess(String n, Type t, int line, int column) {
		super(line, column);
		this.name = n;
		this.type = t;
	}

	@Override
	public Expression getValue(Program program) {
		Object outcome = execute(program);
		if (outcome instanceof Double)
			return new DoubleLiteral(new DoubleType((Double) outcome), getLine(), getColumn());
		else if (outcome instanceof Boolean)
			return new BoolLiteral(new BoolType((Boolean) outcome), getLine(), getColumn());
		else if (outcome instanceof BallisticObject)
			return new EntityExpression(new EntityType((BallisticObject) outcome), getLine(), getColumn());
		else
			return null;
	}

	@Override
	public Object getResult(Program program) {
		return execute(program);
	}
	
	private Object execute(Program program) {
		if (type instanceof DoubleType) {
			if (program.getDoubles().containsKey(name))
				return program.getDoubles().get(name).getResult();
			else
				return new Double(0.0);
		} else if (type instanceof BoolType) {
			if (program.getBools().containsKey(name))
				return program.getBools().get(name).getResult();
			else
				return new Boolean(false);
		} else if (type instanceof EntityType) {
			if (program.getEntities().containsKey(name))
				return program.getEntities().get(name).getResult();
			else
				return new Worm(null, 0, null, null, program);
		} else
			return null;
	}

	private String name;

	private Type type;

}
