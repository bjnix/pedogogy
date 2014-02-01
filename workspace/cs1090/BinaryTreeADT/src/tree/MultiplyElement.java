package tree;

public class MultiplyElement implements ExpressionElement {

	@Override
	public int evaluate(Integer lv, Integer rv) {
		return lv * rv;
	}

	@Override
	public void print() {
		System.out.print(" * ");
	}

}
