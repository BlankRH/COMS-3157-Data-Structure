public class GenericMethods {
    
  public static <T extends Comparable<T>> int findMin(T[] arr) {
		int index = 0;
		for(int i=1; i<arr.length; i++) {
			if(arr[i].compareTo(arr[index]) < 0)
				index = i;
		}
    return index; // replace this
  }
  
  public static <T extends Comparable<T>> int findMinRecursive(T[] arr) {
    return findMinRecursivehelper(arr, 0); // replace this
  }
	
	private static <T extends Comparable<T>> int findMinRecursivehelper(T[] arr, int curIndex) {
		if(arr.length - 1 == curIndex)
			return curIndex;
		int minIndex = findMinRecursivehelper(arr, curIndex + 1);
		if(arr[curIndex].compareTo(arr[minIndex]) <= 0) {
			minIndex = curIndex;	
		}
		return minIndex;
	}
  
  public static <T extends Comparable<T>> int binarySearch(T[] arr, T x) {
    return binarySearchHelper(arr, x, 0, arr.length-1);
  }
  
	private static <T extends Comparable<T>> int binarySearchHelper(T[] arr, T x, int start, int end) {
		if(start == end) {
			if(arr[start].compareTo(x) == 0)
				return start;
			return -1;
		}
		int mid = (start + end) / 2;
		if(arr[mid].compareTo(x) < 0)
			return binarySearchHelper(arr, x, mid+1, end);
		else if(arr[mid].compareTo(x) > 0)
			return binarySearchHelper(arr, x, start, mid-1);
		else
			return mid;
	}
}