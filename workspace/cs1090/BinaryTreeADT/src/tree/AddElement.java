package tree;

public class AddElement implements ExpressionElement {

	@Override
	public int evaluate(Integer lv, Integer rv) {
		return lv + rv;
	}

	@Override
	public void print() {
		System.out.print(" + ");
	}

}
