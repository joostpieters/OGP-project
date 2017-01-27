package worms.model.programs.expressions;

import worms.model.Entity;
import worms.model.Program;
import worms.model.programs.types.*;

public class InEquality extends Expression {
		
		public InEquality(Expression e1, Expression e2, int line, int column) {
			super(line, column);
			this.e1 = e1;
			this.e2 = e2;
		}

		private Expression e1;

		private Expression e2;

		@Override
		public BoolLiteral getValue(Program program) {
			return execute(program);
		}
		
		@Override
		public Boolean getResult(Program program) {
			return execute(program).getResult();
		}
		
		private BoolLiteral execute(Program program) {
			if (e1.getResult(program) instanceof Double && e2.getResult(program) instanceof Double) {		
				return new BoolLiteral(new BoolType(((Double) e1.getResult(program)).compareTo((Double) e2.getResult(program)) != 0), getLine(), getColumn());
			} else if (e1.getResult(program) instanceof Boolean && e2.getResult(program) instanceof Boolean) {	
				return new BoolLiteral(new BoolType(((Boolean) e1.getResult(program)).compareTo((Boolean) e2.getResult(program)) != 0), getLine(), getColumn());
			} else if (e1.getResult(program) instanceof Entity && e2.getResult(program) instanceof Entity) {		
				return new BoolLiteral(new BoolType(!(e1.getResult(program) == e2.getResult(program))), getLine(), getColumn());
			}
			return new BoolLiteral(new BoolType(false), getLine(), getColumn());
		}
	}