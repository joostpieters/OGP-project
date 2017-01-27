package worms.model;

import java.util.HashMap;
import java.util.Map.Entry;

import worms.gui.game.IActionHandler;
import worms.model.programs.statements.*;
import worms.model.programs.types.*;
import worms.model.programs.expressions.*;
import worms.model.programs.expressions.EntityExpression;

public class Program {

	public Program(IActionHandler h, Statement s, HashMap<String, Type> globals, String text){
		this.handler = h;
		this.statement = s;
		this.instructionNb = 0;
		addGlobals(globals);
		this.text = text;
	}

	public String printText(){
		System.out.println(text);
		return text;
	}

	private String text;

	public IActionHandler getHandler(){
		return this.handler;
	}

	private IActionHandler handler;

	public Worm getPerformingWorm(){
		return this.performingWorm;
	}

	public void setPerformingWorm(Worm w){
		this.performingWorm = w;
	}

	private Worm performingWorm;

	public Statement getStatement() {
		return this.statement;
	}

	private Statement statement;

	public int getInstructionNb() {
		return this.instructionNb;
	}

	public void incrementInstructionNb() {
		this.instructionNb++;
	}

	private int instructionNb;

	public HashMap<String, Expression> getGlobals() {
		HashMap<String, Expression> all = new HashMap<String, Expression>();
		all.putAll(doubles);
		all.putAll(bools);
		all.putAll(entities);
		return all;
	}

	private void addGlobals(HashMap<String, Type> globals) {
		for(Entry<String, Type> entry : globals.entrySet()) {
			if (entry.getValue() instanceof DoubleType)
				addGlobal(entry.getKey(), new DoubleLiteral((DoubleType) entry.getValue(), -1, -1));
			else if(entry.getValue() instanceof BoolType)
				addGlobal(entry.getKey(), new BoolLiteral((BoolType) entry.getValue(), -1, -1));
			else if(entry.getValue() instanceof EntityType)
				addGlobal(entry.getKey(), new EntityExpression((EntityType) entry.getValue(), -1, -1));
		}
	}

	public void addGlobal(String name, Expression value) {
		if (value instanceof DoubleLiteral && !bools.containsKey(name) && !entities.containsKey(name))
			doubles.put(name, (DoubleLiteral) value);
		else if (value instanceof BoolLiteral && !doubles.containsKey(name) && !entities.containsKey(name))
			bools.put(name, (BoolLiteral) value);
		else if (value instanceof EntityExpression && !doubles.containsKey(name) && ! bools.containsKey(name))
			entities.put(name, (EntityExpression) value);
	}

	public HashMap<String, DoubleLiteral> getDoubles() {
		return this.doubles;
	}

	private HashMap<String, DoubleLiteral> doubles = new HashMap<String, DoubleLiteral>();

	public HashMap<String, BoolLiteral> getBools() {
		return this.bools;
	}

	private HashMap<String, BoolLiteral> bools = new HashMap<String, BoolLiteral>();

	public HashMap<String, EntityExpression> getEntities() {
		return this.entities;
	}

	private HashMap<String, EntityExpression> entities = new HashMap<String, EntityExpression>();

	public void run() {
		statement.execute(this);
		getPerformingWorm().getWorld().startNextTurn();
	}
}