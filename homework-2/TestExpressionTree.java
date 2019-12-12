public class TestExpressionTree {
	public static void main(String[] args) {
		String testString = "34 2 - 9 +";
		ExpressionTree tree = new ExpressionTree(testString);
		System.out.println("eval: " + tree.eval());
		System.out.println("Postfix: " + tree.getPostfix());
	}
	
}