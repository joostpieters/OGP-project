package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BoolType;

public class BoolLiteral extends Expression {
	
	public BoolLiteral(BoolType bool, int line, int column) {
		super(line, column);
		this.value = new BoolType(bool.getValue());
	}
	
	public BoolLiteral(int line, int column) {
		this(new BoolType(false), line, column);
	}
	
	@Override
	public BoolLiteral getValue(Program program) {
		return this;
	}
	
	public Boolean getResult(){
		return this.value.getValue();
	}
	
	@Override
	public Boolean getResult(Program program) {
		return getResult();
	}

	private BoolType value;
}
