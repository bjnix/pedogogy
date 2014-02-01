package tree;

public class ExpressionEvaluationTour extends EulerTour<ExpressionElement,Integer> {
	
	public void visitRight(Position<ExpressionElement> p, TourResult<Integer> r) {
		r.out = ((ExpressionElement)p.element()).evaluate(r.left,r.right);
	}

	@Override
	public Integer execute(BinaryTree<ExpressionElement> T) {
		init(T);
		return eulerTour((BTPosition<ExpressionElement>) T.root());
	}

}
