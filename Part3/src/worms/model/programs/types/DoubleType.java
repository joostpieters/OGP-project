package worms.model.programs.types;

import worms.util.Util;

public class DoubleType extends Type implements Comparable<DoubleType> {

	public DoubleType(Double value){
		this.value = value;
	}
	
	public DoubleType(){
		this.value = 0.0;
	}
	
	@Override
	public boolean equals(Type type) {
		return type.getValue() instanceof Double && 
				Util.fuzzyEquals(compareTo((DoubleType) type), 0);
	}

	@Override
	public Double getValue() {
		return this.value;
	}
	
	public static Integer toInt(double value){
		if (value > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else if (value < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		else if (value < 0)
			return (int) Math.ceil(value);
		else 
			return (int) Math.floor(value);
	}
	
	private Double value;

	@Override
	public int compareTo(DoubleType o) {
		return this.value.compareTo(o.getValue());
	}

}
