import java.util.Iterator; 

/**
 *  A Range iterable that can be used to iterate over a sequence of integers
 *  (similar to Python's range function).
 */
public class Range implements Iterable<Integer> {
  // you probably need some variables here and an inner class.
  private class RangeIterator implements Iterator<Integer> {
		
		private int cursor;
		
		public RangeIterator() {
			cursor = 0;
		}
		
		public boolean hasNext() {
			return cursor < theSize;
		}
		
		public Integer next() {
			return theItems[cursor++];
		}	
		
	}
	
	private Integer [] theItems;
	private int theSize;
	
	private static final int DEFAULT_CAPACITY = 100;
	
  public Range(int start, int stop, int step) {
		theSize = 0;
		theItems = new Integer[DEFAULT_CAPACITY];
		if(step > 0)
			for(int i=start; i<stop; i+=step) {
				theItems[theSize++] = i;
			}
		else
			for(int i=start; i>stop; i+=step) {
				theItems[theSize++] = i;
			}
  }

	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}
	
}

