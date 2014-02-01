package tree;

public class IntegerElement implements ExpressionElement {

	private int value;
	
	public IntegerElement(int v) {
		value = v;
	}
	@Override
	public int evaluate(Integer lv, Integer rv) {
		return value;
	}
	@Override
	public void print() {
		System.out.print(value);
	}

}
