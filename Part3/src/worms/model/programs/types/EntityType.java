package worms.model.programs.types;

import worms.model.BallisticObject;

public class EntityType extends Type {

	public EntityType(BallisticObject value){
		this.value = value;
	}
	
	public EntityType(){
		this.value = null;
	}
	
	@Override
	public boolean equals(Type type) {
		return type.getValue() instanceof BallisticObject && 
				type.getValue().equals(this.value);
	}

	@Override
	public BallisticObject getValue() {
		return this.value;
	}
	
	private BallisticObject value;

}
