package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class DoubleLiteral extends Expression {
	
	public DoubleLiteral(DoubleType value, int line, int column) {
		super(line, column);
		if (Double.MAX_VALUE - value.getValue() < 0)
			value = new DoubleType(Double.MAX_VALUE);
		this.setValue(new DoubleType(value.getValue()));
	}
	
	public DoubleLiteral(int line, int column){
		this(new DoubleType(0.0), line, column);
	}
	
	@Override
	public DoubleLiteral getValue(Program program) {
		return this;
	}

	public void setValue(DoubleType value) {
		this.value = value;
	}
	
	public Double getResult(){
		return this.value.getValue();
	}
	
	@Override
	public Double getResult(Program program) {
		return this.value.getValue();
	}

	private DoubleType value;
}
