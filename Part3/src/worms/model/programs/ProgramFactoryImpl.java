package worms.model.programs;

import java.util.List;

import worms.model.programs.statements.*;
import worms.model.programs.types.*;
import worms.model.programs.expressions.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type> {

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(new DoubleType(d), line, column);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BoolLiteral(new BoolType(b), line, column);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return null;
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return null;
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Expression createNull(int line, int column) {
		return new NullLiteral(line, column);
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new SelfWormExpression(line, column);
	}

	@Override
	
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(e, line, column);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetY(e, line, column);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(e, line, column);
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		return new GetDir(e, line, column);
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		return new GetAP(e, line, column);
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new GetMaxAP(e, line, column);
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		return new GetHP(e, line, column);
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		return new GetMaxHP(e, line, column);
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SearchObject(e, line, column);
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWorm(e, line, column);
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		return new VariableAccess(name, type, line, column);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(e1, e2, line, column);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThan(e1, e2, line, column);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualTo(e1, e2, line, column);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(e1, e2, line, column);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new Equality(e1, e2, line, column);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new InEquality(e1, e2, line, column);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Add(e1, e2, line, column);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Substraction(e1, e2, line, column);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiply(e1, e2, line, column);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(e1, e2, line, column);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new Sqrt(e, line, column);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle, line, column);
	}

	@Override
	public Statement createMove(int line, int column) {
		return new Move(line, column);
	}

	@Override
	public Statement createJump(int line, int column) {
		return new Jump(line, column);
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ToggleWeapon(line, column);
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new Fire(yield, line, column);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip(line, column);
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assignment(variableName, rhs, line, column);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new If(condition, then, otherwise, line, column);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(condition, body, line, column);
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return null;
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(statements, line, column);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e, line, column);
	}

	@Override
	public Type createDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type createBooleanType() {
		return new BoolType();
	}

	@Override
	public Type createEntityType() {
		return new EntityType();
	}
}
