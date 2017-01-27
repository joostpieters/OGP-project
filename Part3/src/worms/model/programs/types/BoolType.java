package worms.model.programs.types;

public class BoolType extends Type {
	
	public BoolType(Boolean value){
		this.value = value;
	}
	
	public BoolType(){
		this.value = false;
	}

	@Override
	public boolean equals(Type type) {
		return type.getValue() instanceof Boolean && 
				((Boolean) type.getValue()).compareTo(this.value) == 0;
	}

	@Override
	public Boolean getValue() {
		return this.value;
	}
	
	private Boolean value;
	
}
