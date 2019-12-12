import java.lang.Math; 

/**
 * Data Structures in Java 
 * COMS W3134, Columbia University - Fall 2019
 * Basic structure of a binary tree.
 */
public class BinaryTree<T> {

    // The BinaryTree is essentially just a wrapper around the linked 
    // structure of BinaryNodes, rooted in root.
    protected BinaryNode<T> root;

    /**
     * Represents a binary subtree.
     */
    protected static class BinaryNode<T>{

        public T             data;  // the data 
        public BinaryNode<T> left;  // left subtree
        public BinaryNode<T> right; // right subtree
    
        /**
         * Construct a new binary node. 
         */
        public BinaryNode( T theData, BinaryNode<T> leftChild, 
                                      BinaryNode<T> rightChild ) {
            data    = theData; 
            left    = leftChild;
            right   = rightChild;
        }

        public BinaryNode(T theData) {
            data = theData;
            left = null;
            right = null;
        }

        public void printTree(int indent) {
        
            for (int i=0; i<indent; i++)
                System.out.print(" ");

            System.out.println(data);
            if (left != null) 
                left.printTree(indent + 2);
            if (right != null)
                right.printTree(indent + 2);
        }

        /**
         * Return a bracketed string represention of this tree.        
         */
        public String toString() {

            if (left == null && right == null) // if this is a leaf
                return data.toString();
           
            StringBuilder sb = new StringBuilder( "("); 
            sb.append(data);
            sb.append(" ");
            if (left != null)
                sb.append(left.toString());
            else 
                sb.append("*");
            sb.append(" ");
            if (right != null) 
                sb.append(right.toString());
            else
                sb.append("*");
            sb.append(")");
            return sb.toString();
        }


    } // Nested class BinaryNode ends here.
   

 
    /**
     * Construct a new empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a new BinaryTree wrapper around the BinaryNode rootNode.
     */
    public BinaryTree(BinaryNode<T> rootNode) {
        root = rootNode;
    }

    public void printTree() {
        //root.printTree(0); 
        printTreeTwo(root, 0);
    }

    public void printTreeTwo(BinaryNode<T> parent, int indent) {
        
        for (int i = 0; i<indent; i++)
            System.out.print(" ");
    
        System.out.println(parent.data);

        if (parent.left != null) 
            printTreeTwo(parent.left, indent+2);
        if (parent.right != null) 
            printTreeTwo(parent.right, indent+2);

    }

    public int height(BinaryNode<T> parent) {
        
        if ((parent.left == null) && (parent.right == null)) 
            return 0; 

        int leftHeight = -1; 
        int rightHeight = -1;
        
        if (parent.left!=null)
            leftHeight = height(parent.left);
        
        if (parent.right!=null) 
            rightHeight = height(parent.right); 

        return Math.max(rightHeight, leftHeight) + 1; 

    }


    public int height_shorter(BinaryNode<T> parent) {
        
        if (parent == null)
            return -1; 

        leftHeight = height_shorter(parent.left);
        rightHeight = height_shorter(parent.right); 

        return Math.max(rightHeight, leftHeight) + 1; 

    }


    public int height() {
        return height(root);
    } 

    /** 
     * Factory method that creates a new BinaryTree with two subtrees, that contains theItem
     * as the data object attached to its root.  
     * The two btree methods make it possible to easily construt binary trees like this: 
     * BinaryTree<Integer> t = btree(1,btree(2,btree(3),btree(4)),btree(5));
     * @return a new BinaryTree with two children.  
     */ 
    public static <T> BinaryTree<T> btree(T theItem, BinaryTree<T> t1, BinaryTree<T> t2) {
        BinaryNode<T> root = new BinaryNode(theItem, t1.root, t2.root);
        return new BinaryTree(root); 
    }
    
    /**
     * Factory method that creates a new BinaryTree with no children, which contains 
     * theItem as data object attached to its root.
     * @return a new BinaryTree with no children.
     */
    public static <T> BinaryTree<T> btree(T theItem) {
        return new BinaryTree<T>(new BinaryNode<T>(theItem));
    }

    public String toString() {
        if (root == null) 
            return "()";
        else 
            return root.toString();
    }

    /**
     * Test method: Create and print a BinaryTree. 
     */ 
    public static void main(String[] args) {
        
        BinaryTree<Integer> tree = btree(3, btree(4, btree(5), btree(6)), btree(8));

        tree.printTree();
        System.out.println(tree);
        System.out.println(tree.height());


    }

}
