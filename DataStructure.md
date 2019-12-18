<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=default"></script>
[TOC]
# General Concept
### Abstract Data Types vs. Data Structures:
#### Data Types
- Basic data types: booleans, bytes, integers, characters  
- Simple abstractions: array, **String**  
- More complex data types: List, Stack, Tree, Set, Graph  
- A *data structure* is an actual implementation of a particular ADT

#### ADT
- ADT is a collection of data together with a set of operations  
- does not specify *how* operations are implemented  
- Can be specified as **interfaces**  
- Can be implemented as **classes**  

#### ADTs in Java
*Example*: **String**  
 We can call methods such as *length()*, but we don't have to know how they are stored

###Recursion
- Series
	+ Arithmetic Series: \\(\sum_{i=1}^{N}a + (i-1)d = N\cdot \frac{2a+(N-1)d}{2}\\)
	+ Geometric Series: \\(\sum_{i=0}^{N}s\cdot A^i=\frac{s-s\cdot A^{N+1}}{1-A}\\)

###Basic Proofs
####Types
- induction
- contradiction
- counterexample

####Proof by Induction
1. Base case; we know that T holds for some small value
2. Inductive Hypothesis: Assume T holds for all cases up to k
3. Show that T also holds for k+1

####Proof by Structural Induction
1. Assume that the property holds for some smaller structure
2. Show that it also needs to hold for larger structures

####Proof by Contradiction
1. Assume T is false
2. Show that this assumption leads to a contradiction

####Proof by Counter-example


#Java Concepts
### Three Important OOP Concepts
* **Encapsulation**: Objects bundle data and functionality; Separation of interface and functionality allows us to hide implementation details.  
* **Polymorphism**: Allows us to process objects of different types through a uniform interface.  
* **Inheritance**: Classes can inherit functionality from their parent classes(**type hierarchy**).  

### Interfaces
*Example*:
```java
package java.lang;  
public interface Comparable<AnyType> {  
	int compareTo(AnyType o);  
}
```

### Generic
* Class  
* Interface  
* Wildcard: express subclasses/superclasses of parameter types
* Class Parameter: <T>
* Bound: upper bound - extends;
* Type erasure: type -> bounds  
*Example*： Binary Search
```java
public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a, AnyType x) { 
	int low = 0; 
	int high = a.length -1; 
	int mid;  
	while (low<=high) {            
		mid = (low+high) /2; 
		if (a[mid].compareTo(x) <0) { // x is in second half                 
		low = mid +1;             
		} elseif (a[mid].compareTo(x) >0) { // x is in first half                
			high = mid -1;             
			} else { 
				return mid;             
			}          
		} 
		return-1;     
	}
```

### Nested Classes vs. Inner Classes
Nested | Inner
- | -
static | non-static
can't access instance member of outer class | can access

### Iterator and Iterable
```java
package java.lang;

interface Iterator<T> {
	boolean hasNext();
	T next();
	void remove();
}

interface Iterable<T> {
	Iterator<T> iterator();
}

Iterator<T> someIterator = someIterable.iterator();

while (someIterator.hasNext()) {
	T nextItem = someIterator.next();
	System.out.println(nextItem.toString());
}
```
**Never implement iterable and iterator in the same class!**
<br><br>

# Analysis of Algorithms
### Big-O notation for asymptotic running time
- Upper Bound: \\(T(N) = O(f(N))\\) if there are positive constants \\(c\\) and \\(n_0\\) such that \\(T(N)\leq c f(N)\\)
- Lower Bound: \\(T(N) = \Omega(f(N))\\) if there are positive constants \\(c\\) and \\(n_0\\) such that \\(T(N)\geq c f(N)\\)
- Tight Bound: \\(T(N) = \Theta(f(N))\\) if \\(T(N) = \Omega(f(N))\\) and \\(T(N) = O(f()N)\\)
- Rules for Big-O:
	+ polynomial of degree \\(k\\)
	+ \\((log_(N))^k = O(N)\\)
	+ \\(log_a(N) = \Theta(log_2(N))\\)
	+ \\(T_1(N) + T_2(N) = O(f(N) + g(N)) = O(max(f(N),g(N)))\\)
	+ \\(T_1(N)\cdot T_2(N) = O(f(N)\cdot g(N))\\)
- General Rules: Basic *for*-loops, nested loops, consecutive statements, if/else conditions, calling methods
- Logarithms in the Running time: *e.g. binary search*
- Typical growth functions for algorithms
### Recursion
Consider Fibonacci prog:
```java
public long fibonacci(int k) {
	if(k == 1 || k == 2)
		return 1;
	else
		return fibonacci(k-1) + fibonacci(k-2);
}
```
$$T(k) = O(T(k-1) + T(k-2))$$
$$T(1) = O(c), T(2) = O(c)$$
- Four rules for Recursion  
	1. Base case  
	2. Making Progress  
	3. Design Rule  
	4. Compound Interest Rules - *Never duplicate work*  
- The Towers of Hanoi  
	1. move top n-1 disks from A to B  
	2. move n-th to C  
	3. move top n-1 disks from B to C  
$$T(n) = 2\cdot T(N-1) + 1,T(1) = 1$$
$$T(N)=\sum_{j=0}^{N-1}2^j=2^N-1$$
- Tail Recursion
A method that the last thing it does before returning is call itself

# Lists

List ADT: including typical List operations

### Array Lists
Running time:  

Operation | Number of Steps
- | -
printList | N
find(x) | N
findKth(k) | 1
insert(x,k) | N
remove(x) | N

Increasing Capacity
```java
newCapacity = arr.length * 2;
Integer[ ] old = theItems;
theItems =newInteger[newCapacity];
for( int i = 0; i < size( ); i++ )    
	theItems[ i ] = old[ i ];
```

###Linked List
####Single Linked List
```java
//single linked list
public class Node {
	public Integer data;
	public Node next;
	public Node(Integer d, Node n) {
		data = d;
		next = n;
	}
}
```

- Running time:

Operation | Number of Steps
- | -
printList | N
find(x) | N
findKth(k) | K
next() | 1
insert(x,k) | search time + 1
remove(k) | search time + 1

- Linked List should remember the **head** and **tail**

#### Doubly Linked List
- Also maintain refernce to previous node
- Speeds up append at end of list

```java
//doubly linked list
public class Node {
	public Integer data;
	public Node next;
	public Node prev;
	public Node(Integer d, Node n, Node p) {
		data = d;
		next = n;
		prev = p;
	}
}
```

#Stack and Queue
### The Stack ADT
- push(x), top(), pop()
- LIFO

### Implementing Stacks
- Can be implemented using any List implementation
- Push and pop run in O(1) time with ArrayList or LinkedList

### The Queue ADT
- enqueue(x), dequeue()
- FIFO
- *Deque* can be Queue or Stack

### Implementing Queues
- Can be implemented using LinkedList or array
- enqueue and dequeue run in O(1) with LinkedList
- Dequeue on ArrayList:
	+ Need large space
	+ Circular array
- Banker's Queue
	+ implements a queue using 2 stacks "in" & "out"
	+ *enqueue(x)*: push x to the "in"
	+ *dequeue(x)*: if the "out" is empty, shuffle all elements from "in" to "out", then pop from "out"

### Applications
- Balancing Symbols
- Detecting Palindromes
- Evaluating Postfix Expressions
```
for c in input:
	if c is an operand: push
	if c is an operator x:
		pop the top 2 operands a1 and a2
		push a3 = a1 x a2
	pop result
```
- Converting Infix to Postfix
```
for c in input:
	if c is an operand: print c
	if c is "+", "*":
		while stack is not empty and priority(stack.top()) >= priority(c):
		print stack.pop()
	if c is ")": reduce stack until matching "("
	push c
while stack is not empty:
	print stack.pop()
```
- Method Call Stacks
	+ represent current state of execution of this function
	+ when a method is called:
		* execution of current method suspend
		* new activation record pushed to the stack
		* new function run
- Runaway Recursion
	+ overflow
- Counting out game with queue

#Tree
###Tree ADT
- Consist of: root node, subtrees, some operations

### Terminology
- Node: parent, children, grandchildren
- Path, Length, Depth, Height

###Representing Trees
- Linked list: takes longer to find node from root
- Array list: only reasonable for small or constant number of children
- Every node has **fixed** number of reference to children: more memory efficient than array, but same limit

#### Storing Bianry Trees in Arrays
- leftChild(i) = 2i
- rightChild(i) = 2i + 1
- parent() = i/2(integral)

###Binary Trees
- Number of children at most 3
- Full: 0/2 children
- Complete: all levels except last are completely filled
- Perfect: all levels are completely filled
- e.g. Expression Tree

###Tree Traversals
- Post-order:
	+ left -> right -> root
- Pre-order
	+ root -> left -> right
- In-order
	+ left -> root -> right
- Recursion
```java
public void printTree(int indent) {
	for (i=0;i>indent;i++)
		System.out.print(" ");
	System.out.println(data);
	if(left != null)
		left.printTree(indent+1);
	if(right != null)
		right.printTree(indent+1);
}
```

###Construct
- Constructing Expression Tree using Stack
```
for c in input:
	if c is an operand: push a tree
	if c is an operator:
		pop the top 2 trees t1 and t2
		push new tree(root: c, left: t1, right: t2)
pop the result
```

###Structural Induction for Binary Trees
- base case: property holds for single node
- inductive step
	+ assume property holds for each subtree
	+ show it holds for all trees by combining subtrees with a parent

####height
- want to show: a perfect binary tree of height h has \\(2^{h+1}-1\\) nodes
- Base case: A tree of height 0 has 1 nodes
- Inductive Step:
	+ Hypothesis: Assume any perfect binary tree of height k has \\(2^{k+1}-1\\)nodes
	+ Show that any perfect binary tree of height k has \\(2^{k+2}-1\\) nodes

####number of nodes
- prove that any full binary tree of N nodes has (N+1)/2 leaves
- Base case: a tree with 1 node has 1 leaves
- Inductive Step
	+ Hypothesis: Assume a full binary tree with k nodes has (k+1)/2 leaves
	+ The new tree of k+2 nodes has \\(\frac{(k+2)-1}{2}\\)	

###Binary Search Tree
####BST Property
- Goal: reduce finding an item to O(logN)
- For every node n with key x
	+ the key of all nodes in left subtree of n are smaller than x
	+ the key of all nodes in the right subtree of n are larger than x

####BST ADT
- No key appears more than once
- Operations
	+ contains(x)
```java
private boolean contains(Integer x, BinaryNode t) {
	if(t == null) return false;
	if(x < t.data) return contains(x, t.left);
	else if (x > t.data) return contains(x, t.right);
	else return true;
}
```
	+ findMin() / findMax()
```java
private BinaryNode findMin(BinaryNode t) {
	if(t == null) return null;
	else if(t.left == null) return t;
	return findMin(t.left);
	+ insert(x)
}
```
	+ insert(x)
```java
private BinaryNode insert(Integer x, BinaryNode t) {
	if(t == null) return new BinaryNode(x, null, null);
	if(x < t.data) t.left = insert(x, t.left);
	else if (t.data < x) t.right = insert(x, t.right);
	return t;
}
```
	+ remove(x)
```java
private BinaryNode remove(Integer x, BinaryNode t) {
	if(t == null) return t;

	if(x < t.data) t.left = remove(x, t.left);
	else if(x > t.data) t.right = remove(x, t.right);

	else {
		if(t.left != null && t.right != null) {
			t.data = findMin(t.right).data;
			t.right = remove(t.data, t.right);
		}
		else if(t.left != null)
			return t.left;
		else return t.right;
	}
}
```
- Worst and Best Case
	+ worst: height(T) = O(N)
	+ best: height(T) = O(log N), complete binary tree
	+ Average depth of a node in a random BST of N nodes is $$2logN=O(logN)$$
	+ Expected depth after insertion/deletion pairs$$\Theta (\sqrt(N)) = \Theta(N^{1/2})$$
- Lazy deletion: when the number of deletions is small, just mark the node as deleted
	+ advantage: makes delete simple to implement
	+ disadvantage: if there are many delete operations without reinsert, it is wasteful of space; increase length of paths

####Comparing Complex Items
- generic BSTs that can contain (key,value) pairs
- we must be able to sort the elements(implement **Comparable**)
```java
private class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K,?>> {
	public K key;
	public V value;

	public Pair(K theKey, V theValue) {
		key = theKey; value = theValue;
	}

	@Override
	public int compareTo(Pair<K,?> other) {
		return key.compareTo(other.key);
	}
}
```

####Balanced BSTs
- balance condition: guarantee that the BST is always close to a *complete* binary tree.
	+ then the height of the tree will be \\(O(log N)\\) and all BST operations will run in \\(O(log N)\\)
- There are different implementations of self-balancing BSTs:
	+ AVL trees, Red-Black trees, B+- trees, ...
- height of an empty subtree: -1

####AVL Tree Condition
- an AVL tree is a Binary Search Tree in which the following **balance condition** holds after each operation
	+ for every node, the height of the left and right subtree differs by at most 1
	+ height of an AVL tree is at most \\(O(log N)\\)
- "outside" imbalance - single rotation:
	+ left subtree of left child too high
	+ right subtree of right child too high
- "inside" imbalance - double rotation:
	+ left subtree of right child too high
	+ right subtree of left child too high
- maintain the balance condition
	+ rebalance the tree after each modification
	+ rebalancing must be cheap
	+ steps:
		* after each modification, find the **lowest node k** that violates the balance condition, starting at the insertion site
		* perform rotation to re-balance the tree.
		* rotation maintains original height of subtree under k before the insertion. No further rotations are needed.
		* invariant: after each modification, the tree maintains both the BST property and the AVL condition
	+ single rotation
	+ double rotation (2 single rotation)

#### M-ary Trees
- each node have M subnodes
- height if a complete M-ary tree is \\(log_m N\\)
- generalize BST to M-ary search trees

#### 2-3-4 tree: balanced 4-Ary search tree
- three types of internal nodes:
	+ 2-node: 1 item, 2 children
	+ 3-node: 2 item, 3 children
	+ 4-node: 3 item, 4 children
- balance condition: all leaves have same depth
- *contains*:
	+ at each level: 2 steps = \\(O(c)\\)
	+ if not found, down the tree; at most \\(O(height(T)) = O(log N)\\) references
- *insert*:
	+ do *contains*
	+ if found, do nothing
	+ if still space in the leaf that should contain X, add
	+ if full, evenly split it into 2 nodes
		* choose median m of values
		* left node contains items < m, right node contains items > m
		* add median item to parent, add references to the 2 new nodes left and right of it
		* if parent is also full, continue to split
		* if root is full, create a new root with old root as single child; this is the **only** time height increases
- *remove*:
	+ item in 3- or 4-leaf can just be removed
	+ removal of an item v from internal node:
		* continue down the tree to find the leaf with the next highest item w
		* replace v with w
		* remove w
	+ removal of an item from a leaf 2-node t:
		* move down an item from parent of t
		* replenish the parent by moving item from one of t's siblings
		* if there is no 3- or 4-node siblings: **fuse** the sibling node with one of the parent nodes
		* all modification are local and therefore \\(O(c)\\), remove runs is \\(O(log N)\\)
		
#### B-tree
- a b-tree is a generalization of the 2-3-4 tree to M-ary search trees
- every internal node (except for root) has \\(\frac{M}{2}d \leq d \leq M\\) children and contains \\(d-1\\) values
- all leaves contain \\(\frac{L}{2}d \leq d \leq L\\) values (usually \\(L=M-1\\))
- all leaves have same depth
- often used to store large tables on hard disk drives (memory access is **much** faster than disk access)
- large BST on Disk #TODO, lec11
	+ Assume we have a very large database table, represented as binary search tree:
		* 10 million items, 236 bytes each
		* 6 disk accesses per second
	+ disk access time for finding a node in an unbalanced BST:
		* depth of searched node is N in the worst case:
		* **expected** depth is \\(1.38 log N\\)
	+ AVL: worst/average - \\(log_{2}{N}\\)
	+ B-trees
		* read an entrie B-tree node (containing M items) into memory in *single disk access*, find the next reference using binary search
		* worst case height of the B-tree is about \\(log_{M/2}{N}\\)
- Hard disk drive layout
	+ **sector**: minimal unit of data that can be read from the disk
	+ **physical sector size**: 512 byte
	+ **blocks**: logical units of adjacent sectors, typical sizes are 1kb, 2kb
- Estimating the ideal M for a B-Tree
	+ Assume 8kb block size
	+ Assume every data item is 256 byte
	+ An M-ary B-tree contains at most M-1 data items + M block addresses of other nodes (assume an 8 byte pointer each)
	+ How big can we make the nodes? $$(M-1) * 256 byte + M * 8 byte = 8192 byte$$ $$M=32$$
- Calculating access time
	+ assume we represent 10,000,000 items in a B-tree with M=32
	+ the tree has a worst-case height of \\(log_{M/2}{N}\\) $$log_{32/2}{10,b000,000} $$
	+ worst-case time to find an item is 6 accesses / 200 disk accesses per second = 30ms
- B+ trees
	+ only leafs store full (key,value) pairs
	+ internal nodes only contain keys to help find the right leaf
	+ insert/removal only at leafs (slightly simpler, see book
	+ assume keys are 32 bytes $$(M-1)*32byte+M*8byte=8192byte$$
	+ we can fit at most M=205 keys in each node
	+ worst case height for 10 million keys: $$log_{205/2}{10,000,000}=3$$
		* 3 accesses/200 seconds per access = 15ms

## Set ADT
- A Set is a collection of data that does not allow duplicates
- operations:
	+ insert(x)
	+ remove(x)
	+ contains(x)
	+ isEmpty()
	+ size()	
	+ addAll(s) / union(s)
	+ removeAll(s)
	+ retainAll(s) / intersection(s)

### OrderedSet ADT
- A set with a total order defined on the items (all pairs of items are in a '>' or '<' relation to each other)
- Operations: all **Set** operations and
	+ findMin()
	+ findMax()
- Naive implementation: LinkedList, ArrayList (bad)
	+ need to be able to check for item equality'
	+ running time of all operations at least \\(O(n)\\), becuase we need to check for mentorship first
- Better: implement ordered sets as search trees
	+ With balanced search trees: \\(O(log N)\\) for insert, remove, contains
	+ Need to be able to compare every pair of items, implement the **Comparable** interface

##Map ADT
- keys are unique, values need not be

### Arrays as Maps
- get(key), put(key, value), O(1)

### Hash Tables
- define a table (an array) of some length *TableSize*
- define a function **hash(key)** that maps key objects to an integer index in the range 0 ... *TableSize* - 1
- lookup/get: just hash the key to find the index
- assuming hash(key) takes constant time, get and put run in O(1)

#### Hash function:
- properties
	+ depends on: type of keys we expect and *TableSize*
	+ spread out the keys as much as possible in the table (ideal: uniform distribution)
	+ make sure that all table cells can be reached
- Integers:
		* hash(x) = x % *TableSize*
```java
public static int hash(Integer key, int TableSize) {
	return key % TableSize;
}
```
- Strings - idea1:
	+ Sum up the ASCII values of all characters in the String
```java
public static int hash(String key, int tableSize) {
	int hashVal = 0;
	for(int i=0;i<key.length();i++)
		hashVal = hashVal + key.charAt(i);
	return hashVal % tableSize;
}
```
	+ problem: doesn't work for large table sizes
- Strings - idea2:
	+ only look at prefix, spread out the value of each character
```java
public static int hash(Integer key, int tableSize) {
	return (key.charAt(0) + 
		27 * key.charAt(1) + 
		27 * 27 * key.charAt(2)) % tableSize;
}
```
	+ problem: assuming that all three letter combinations are equally likely at the beginning of a string
- combining
	+ multiply the hash of each member cariable with some distinct, prime number
- table size and ash functions:
	+ good practices: keep tableSize and factors when combining hash values **prime numbers**
	+ minimizes collisions
	
#### Keys
- immutable

#### Dealing with Collisions: Separate Chaining
- keep all items whose key hashes to same value on a linked list (most frequently used)
- Time Analysis:
	+ Time to find a key = time to compute hash function + time to traverse the linked list
	+ Load factor: the average length of a list $$\lambda=\frac{N}{TableSize}$$
	+ if lookup fails: need to search all \\(\lambda\\) nodes in the list
	+ if lookup succeeds: 
		* there will be \\(\lambda\\) other nodes in the list
		* on average we search half the list and the target element, so we touch \\(\lambda/2+1\\) nodes
- Design rule: keep \\(\lambda \simeq 1\\)
- Problem:
	+ requires allocation of new list nodes, which introduces overhead
	+ requires more code

#### Probing
- keep keys in the hash array itself
- collision -> move to another array position
- look up: search the table, starting from the cell the key was hashed to, \\(\lambda \leq 1\\)
- insert: probe other table cells in a **systematic way**
$$hash(x)+f(i))%TableSize$$
- Linear probing \\(f(i) = i\\):
	+ Good: can always find an empty cell
	+ bad: **Primary Clustering**, full cells tend to cluster
- Quadratic probing \\(f(i) = i^2\\):
	+ *TableSize* should be a prime number, otherwise possible not find an empty cell
	+ bad: if \\(\lambda > 0.5\\), possible empty cell unreachable
	+ **Therorem: if TableSize is prime, then the first \\(\frac{TableSize}{2}\\) cells visited by quadratic probing are distinct**
		* proof see lecture 13
- Double Hashing \\(f(i) = i \cdot hash_2(x)\\):
	+ good choice: \\(hash_2(x)=R-(x%R)\\), tableSize prime

#### Rehashing
- allocate a new table of twice the size as the original one
- cannot simply copy entries to the new array
	+ different modulo wrap around won't cause the same colisions
	+ Since the hash function is based on the TableSize, keys won't be in the correct cell
- Remove all N items an reinsert, O(N)

## Priority Queues(Binary Heaps)

### The Priority Queue ADT
- a collection Q of comparable elements, that supports the folling operations
	+ insert(x)
	+ deleteMin(), return the min

### Implementing Priority Queues
1. Linked List
	- insert(x): O(1)
	- deleteMin(): O(N)
2. Binary Search Tree
	- insert(x): O(log N)
	- deleteMin(): O(log N)
3. Heap
	- getMin: O(1)
	- n*insert(x): O(N)
	- gives a sorting algorithm in O(N log N)

### Heap
a heap is a complete binary tree stored in an array, with the follwing **heap order property**: for every node n with value x, the values of all nodes in the subtree rooted in n are greater or equal than x

#### Representations
- array: leftChild(i) = 2i, rightChild(i) = 2i+1, parent(i) = i/2

#### Property
- complete binary tree
- for every node, the values of all nodes in the subtree are greater or equal to x

#### Max Heap
#### Min Heap
- insert(x): attempt to insert at last array position
	+ if heap order violated, **percolate up**(swap with parent)
	+ worst: O(log N)
- deleteMin(): 
	+ remove lowest item, creating an empty cell in the root
	+ place last item in heap into root, if order violated, **percolate down**(swap w/ smaller child)
	+ worst: O(log N)
- getMin(): O(1)

#### Building a heap bottom-up
- start with an unordered array
- precolateDown(i) assumes that both subtrees under i are already heaps
	+ make sure all subtrees in the two last layers are heaps
	+ then move up layer by layer
``` java
	+ for i = n/2 ... 1 
		precolateDown(i)
```
- worst running time: O(n)

## Sorting
### Insertion sort
1. perform N passes through the array, p = 1...N-1
2. assume array[0...p-1] is already sorted
3. take the element x at position p
4. repeatedly swap x with its left neighbor until x is in the correct position

### Selection sort
1. perform N passes through the array, p = 0...N-1
2. assume array[0..p-1] is already sorted
3. find the minimum element in the unsorted partition
4. swap the element at position p with the minimum

### Heap sort
1. convert an unordered array into a heap in O(N) time
2. perform N deleteMin operations to retrieve the elements in sorted order, each deleteMin is O(logN)
- re-use the freed space after each deleteMin

### Merge sort
- split the array in half, recursively sort each half
- merge the two sorted lists
```java
void mergeSort() {
	if(left < right) {
		int center = (left + right) / 2;
		mergeSort(a, tmpArray, left, center);
		mergeSort(a, tmpArray, center + 1, right);
		merge(a, tmpArray, left, center + 1, right);
	}
}
```

#### Merge sorted sublists
1. keep a pointers for each sub-list in the array
2. in each step, compare the elements they point two
3. if a[Actr] < a[Bctr], copy a[Actr] to tmp and advance Actr
4. otherwise, copy a[Bctr] to the output and advance Bctr
```java
void merge() {
	int leftEnd = bCtr - 1;
	int tmpPos = aCtr;
	int numElements = rightEnd - aCtr + 1;

	while(aCtr <= leftEnd && bCtr <= rightEnd) {
		if(a[aCtr].compareTo(a[bCtr]) <= 0)
			tmpArray[tmpPos++] = a[aCtr++];
		else
			tmpArray[tmpPos++] = a[bCtr++];
	}

	while(aCtr <= leftEnd)
		tmpArray[tmpPos++] = a[aCtr++];

	while(bCtr <= rightEnd)
		tmpArray[tmpPos++] = a[bCtr++];

	for(int i = 0; i < numElements; i++, rightEnd--)
		a[rightEnd] = tmpArray[rightEnd];
}
```

### Quick sort
1. pick any pivot element v
2. partition the array into elements
3. x <= v and x >= v
4. recursively sort the partitions then concatenate them

#### Partitioning the array
```java
void quicksort() {
	if(right > left) {
		int v = find_pivot_index(a, left, right);
		int i = left; int j = right-1;

		Integer tmp = a[v]; a[v] = a[right]; a[right] = tmp;

		while(1) {
			while(a[++i] < v) {};
			while(a[--j] > v) {};
			if(i >= j) break;
			tmp = a[i]; a[i] = a[j]; a[j] = tmp;
		}

		tmp = a[i]; a[i] = a[right]; a[right] = tmp;

		quicksort(a, left, i-1); quicksort(a, i+1, right);
	}
}
```

#### Choosing pivot
- worst: pivot is always the smallest or largest, O(N^2)
- best: pivot is always the median, O(NlogN)
- Median of Three

### Sorting stability
- stable if the relative order of duplicate items in the input is preserved

### Analysis of Sorting Algorithms
| | T<sub>worst</sub> | T<sub>best</sub> | T<sub>avg</sub> | Space | Stable |  
| | ----------------- | ---------------- | --------------- | ----- | ------ |
| Selection Sort | O(N^2) | O(N^2) | O(N^2) | O(1) | x |
| Insertion Sort | O(N^2) | O(N) | O(N^2) | O(1) | √ |
| Heap Sort | O(NlogN) | O(NlogN) | O(NlogN) | O(1) | x |
| Merge Sort | O(NlogN) | O(NlogN) | O(NlogN) | O(N) | √ |
| Quick Sort | O(N^2) | O(NlogN) | O(NlogN) | O(1) | x |

## Graphs
### Definitions
- Graph: a pair of two sets G=(V, E):
- V: the set of vertices
- E: the set of edges
- directed/undirected: edge pairs are (not) ordered
- weight: cost associated with edges
- simple path: a path that contains every node only once (except the first and last)
- DAG: directed graph that contains no cycles
- connectivity:
	+ an undirected graph is connected if there is a path from every vertex to every other vertex
	+ weakly connected: a directed graph that has undirected path from every vertex to every other vertex
	+ strongly connected: a directed graph that has path from every vertex to every other vertex
- complete graph: has edges between every pair of vertices, has \\(\frac{N \dot (N-1)}{2}\\) edges
- dense / sparse: contain much less than |V|^2 edges?

### Representations
#### Adjacency matrix
- N * N
- A[u][v] = cost(u,v)
- space: O(|V|^2), for sparse graphs, lot of array space wasted

#### Adjacency lists
- for each vertex, keep list of all adjacent vertices
- space: O(|V| + |E|)

#### Topological sorting
- an ordering of its vertices such that if there is a path from u to w, u appears before w
1. annotate each vertex with the number of **indegree**
2. while the queue not empty, dequeue a vertex, print and decrement the indegree of adjacent nodes
3. if the indegree of any new vertex becomes 0, enqueue it
- O(|V|+|E|)

#### BFS
```java
Queue q
q.enqueue(start);
start.visited = 1;
while(!q.empty()) {
	u = q.dequeue();

	for(v in u.adjacent)
		if(!v.visited)
			q.enqueue(v);
}
```
- unweighted shortest paths
```java
for all v:
	v.cost = INT_MAX;
	v.prev = null;

start.cost = 0;

Queue q
q.enqueue(start)

while(q is not empty)
	u = q.dequeue;
	
	for each v adjacent to u:
		if v.cost == INT_MAX
			v.cost = u.cost + 1
			v.prev = u
			q.enqueue(v)
```
- O(|V|+|E|)

#### Dijkstra's algorithm
```java
for all v:
	v.cost = INT_MAX
	v.visited = false
	v.prev = null
start.cost = 0

PriorityQueue q
q.insert(start)

while(q is not empty):
	u = q.pollMin()
	u.visited = true

	for each v adjacent to u:
		if not v.visited:
			c = u.cost + cost(u,v)
			if(c < v.cost)
				v.cost = c
				v.prev = u
				q.insert(v)
```
- running time O(|E|log|V|) = O(log|V|)

#### Minimum Spanning Trees:
- spanning tree: a tree that connects all vertices in an undirected connected graph; T is acyclic, there is a single path between any pair of vertices
- Prim's algorithm
```java
for all v:
	v.cost = INT_MAX;
	v.visited = false;
	v.prev = null;
start.cost = 0;

PriorityQueue q;
q.insert(start)

while(q is not empty):
	u = q.pollMin();
	u.visited = true;

	for each v adjacent to u:
		if not v.visited:
			if(cost(u,v) < v.cost):
				v.cost = cost(u,v)
				v.prev = u
				q.insert(v)
```
- Running time: O(|E|log|V|)

#### Euler circuits
- Euler paths: a path through an undirected graph that visits every edge exactly once
- Euler Circuit: an Euler path that begins and ends at the same node
- conditions:
	+ euler path: exactly 0 or 2 vertices must have odd degree; start with one of the odd vertices and end in the other one
	+ euler circuit: all vertices need to have even degree
- finding euler circuits:
1. start with any vertex s; use DFS find any circuit starting and ending in s; mark all edges on the circuit as visited
2. while there are still edges in the graph that are not marked visited:
3. find the first vertex v on the circuit that has unvisited edges
4. find a circuit starting in v and splice this path into the first circuit

#### Hamilton cycle
- hamiltonian path: a path through an undirected graph that visits **every vertex** exactly once
- hamiltonian cycle: a Hamiltonian Path that starts and ends in the same node
- No polynomial time solution to if a graph contains a Hamiltonian path/cycle is known

#### Traveling Salesperson Problem
- TSP: given a complete, undirected graph G=(V,E), find the shortest simple cycle that visits all vertices
- there are \\(\frac{(N-1)!}{2}\\) possibilities
- Brute Force Approach: try all possible tours and return the shortest one; O(N!)
- Nearest Neighbor: start with node D, always follow the lowest edge to an unvisited vertex until all vertices have been visited; not guaranteed to find an optimal solution