import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class KBestCounter<T extends Comparable<? super T>> {

    PriorityQueue<T> heap;
    int k;

    public KBestCounter(int k) {
		this.k = k;
		heap = new PriorityQueue<T>();
    }

    public void count(T x) {
		if(k <= 0)
			return;
		if(heap.size() < k)
			heap.add(x);
		else if(x.compareTo(heap.peek()) > 0){
			heap.poll();
			heap.add(x);
        }
    }

    public List<T> kbest() {
        List<T> l = new ArrayList<>();
		while(heap.size() > 0)
			l.add(heap.poll());
		for(T i : l)
			count(i);
		return l;
    }
    
}