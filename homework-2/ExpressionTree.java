public class ExpressionTree {
	
	protected ExpressionNode root;
	
    public ExpressionTree(String postfix) {
		 Queue<Integer> numStack = new TwoStackQueue<Integer>();
		 Stack<ExpressionNode> treeStack = new ArrayStack<ExpressionNode>();
		 char[] postfixChar = postfix.toCharArray();
		 root = null;
         for(char c: postfixChar) {
			 if(c >= '0' && c <= '9')
				 numStack.enqueue((int)c - (int)'0');
			 else if((c == ' ') && !numStack.isEmpty()) {
				 int num = 0;
				 while(!numStack.isEmpty())
					 num = num * 10 + numStack.dequeue() ;
				 treeStack.push(new ExpressionNode(num));
			 }
			 else if(c != ' '){
				 try {
					 ExpressionNode rightChild = treeStack.pop();
					 ExpressionNode leftChild = treeStack.pop();
					 ExpressionNode newRoot = new ExpressionNode(c, leftChild, rightChild);
					 treeStack.push(newRoot);
				 }catch(IndexOutOfBoundsException e) {
					 throw new RuntimeException("Can't match symbols");
				 }
			 }	 
		 }
		if(!treeStack.isEmpty())
			root = treeStack.pop();
		else
			throw new RuntimeException("Empty expression");
		if(!treeStack.isEmpty() || !numStack.isEmpty())
			throw new RuntimeException("Can't match symbols");
    }
	
	protected static class ExpressionNode {
		
		public Object data;
		public ExpressionNode left;
		public ExpressionNode right; 
		
		public ExpressionNode(Object thedata, ExpressionNode leftChild, ExpressionNode rightChild) {
			data = thedata;
			left = leftChild;
			right = rightChild;
		}
		
		public ExpressionNode(Object thedata){
			data = thedata;
			left = null;
			right = null;
		}
		
		public String postfix() {
			String tempString = "";
			if(left != null)
				tempString += left.postfix();
			if(right != null)
				tempString += right.postfix();
			tempString += " " + this.toString();
			return tempString;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
		
		public Integer eval(Stack<Integer> s) {
			if(left != null) {
				s.push(left.eval(s));
			}
			if(right != null) {
				s.push(right.eval(s));
			}
			if(data instanceof Integer)
				return Integer.valueOf(this.toString());
			else {
				int num2 = s.pop();
				int num1 = s.pop();
				//System.out.println(num1 + this.toString() + num2);
				if(this.toString().equals("+"))
					return num1 + num2;
				else if(this.toString().equals("-"))
					return num1 - num2;
				else if(this.toString().equals("*"))
					return num1 * num2;
				else if(this.toString().equals("/"))
					return num1 / num2;
				else throw new IllegalArgumentException("Wrong input");
			} 
		}
	}
   
    public int eval() {
		Stack<Integer> s = new ArrayStack<Integer>();
        return root.eval(s);
    }
    
    public String getPostfix() {
		String ans = root.postfix();
        return ans.trim();
    }
    
}