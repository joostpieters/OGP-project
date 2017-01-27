package worms.model.programs.expressions;

import worms.model.Program;

public class NullLiteral extends Expression {
	
	public NullLiteral(int line, int column){
		super(line, column);
	}

	@Override
	public NullLiteral getValue(Program program) {
		return this;
	}
	
	public Object getResult(){
		return null;
	}
	
	@Override
	public Object getResult(Program program) {
		return getResult();
	}

}
