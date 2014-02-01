import tree.AddElement;
import tree.BinaryTree;
import tree.ExpressionElement;
import tree.ExpressionEvaluationTour;
import tree.LinkedBinaryTree;
import tree.IntegerElement;
import tree.MultiplyElement;
import tree.BTPosition;

/**
 * @author carr
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedBinaryTree<ExpressionElement> tree = new LinkedBinaryTree<ExpressionElement>();
		
		BTPosition<ExpressionElement> node1 = tree.addRoot(new AddElement());
		
		BTPosition<ExpressionElement> node2 = tree.addLeft(node1,new MultiplyElement());
		BTPosition<ExpressionElement> node3 = tree.addRight(node1,new MultiplyElement());
		
		BTPosition<ExpressionElement> node4 = tree.addLeft(node2,new IntegerElement(2));
		BTPosition<ExpressionElement> node5 = tree.addRight(node2,new AddElement());
		
		BTPosition<ExpressionElement> node6 = tree.addLeft(node5,new IntegerElement(6));
		BTPosition<ExpressionElement> node7 = tree.addRight(node5,new IntegerElement(1));
		
		BTPosition<ExpressionElement> node8 = tree.addLeft(node3,new IntegerElement(3));
		BTPosition<ExpressionElement> node9 = tree.addRight(node3,new IntegerElement(4));

		ExpressionEvaluationTour tour = new ExpressionEvaluationTour();
		
		System.out.println("Value = "+tour.execute((BinaryTree<ExpressionElement>) tree));
		
	}

}
