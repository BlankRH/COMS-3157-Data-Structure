import java.util.Arrays;

public class MergeSort {

  /**
   * Method that merges two sorted halves of a subarray (from Weiss,
   * Data Structures and Algorithm Analysis in Java)
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param leftPos
   *          the left-most index of the subarray.
   * @param rightPos
   *          the index of the start of the second half.
   * @param rightEnd
   *          the right-most index of the subarray.
   */
  private static void merge(Integer[] a, Integer[] tmp, int leftPos, int rightPos, int rightEnd) {

        int aCtr = leftPos;
        int bCtr = rightPos; 
        int cCtr = leftPos; 

        while (aCtr < rightPos && bCtr <= rightEnd) {
            if (a[aCtr] <= a[bCtr]) 
                tmp[cCtr++] = a[aCtr++];
            else 
                tmp[cCtr++] = a[bCtr++];
        }
        
        if (aCtr >= rightPos )  // copy remaining elements in right partion 
            while (bCtr <= rightEnd) 
                tmp[cCtr++] = a[bCtr++];

        if (bCtr > rightEnd ) // copy remaining elements in left partion
            while (aCtr < rightPos) 
                tmp[cCtr++] = a[aCtr++]; 
            
        for (int i=leftPos; i<=rightEnd; i++) {
            a[i] = tmp[i];
        }
	  System.out.println("leftPos: " + leftPos + " rightPos: " + rightPos + " rightEnd " + rightEnd + " array" + Arrays.toString(tmp));
	 

  }

 
  /* 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param left
   *          the left-most index of the subarray.
   * @param right
   *          the right-most index of the subarray.
   */
  private static void mergeSort(Integer[] inputArray) {
       
        int subSize = 2, size = inputArray.length;
		while(subSize / 2 < size) {
			for(int i=1; i*subSize < size - 1 + subSize; i++) {
				int leftPos = subSize * (i-1);
				int rightPos = subSize * (i-1) + subSize/2 < size ? subSize * (i-1) + subSize/2 : size;
				int rightEnd = subSize * i - 1 < (size - 1) ? subSize * i - 1 : (size - 1);
				Integer[] temp = new Integer[size];
				merge(inputArray, temp, leftPos, rightPos, rightEnd);
			}
			System.out.println("array at subsize" + subSize + ": " + Arrays.toString(inputArray));
			subSize *= 2;
		}
        return; 
  }

  public static void main(String[] args) {
    Integer[] a = {-4,0,7,4,9,-5,-1,0,-7,-1};
    System.out.println(Arrays.toString(a)); 
    MergeSort.mergeSort(a);
    System.out.println(Arrays.toString(a)); 
    
    
  }
}
