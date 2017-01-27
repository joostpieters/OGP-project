package worms.model.programs.statements;

import worms.model.Program;

public class ToggleWeapon extends Statement {

	public ToggleWeapon(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean execute(Program program) {
		program.getHandler().toggleWeapon(program.getPerformingWorm());
		super.afterExecution(program);
		return true;
	}


}