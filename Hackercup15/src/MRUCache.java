import java.util.HashMap;
import java.util.Map;

public class MRUCache<K,V> {
	private int limit;
	private Map<K, Node<K,V>> cache;
	Node<K,V> head;
	Node<K,V> tail;
	
	public MRUCache() {
		limit	= 10;
		cache	= new HashMap<>();
	}
	
	MRUCache(int capacity) {
		limit	= capacity;
	}
	private static class Node<K,V> {
		public K key;	
		public V val;
		public Node<K,V> prev;
		public Node<K,V> next;
		
		public Node(K k, V v) {
			key	= k;
			val	= v;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [key=").append(key).append(", val=").append(val).append("]");
			return builder.toString();
		}
	}
	
	public void put (K k, V v) {
		Node<K,V> n	= null;
		if (cache.containsKey(k)) {
			n	= cache.get(k);
			remove(n);
			n.val	= v;
		} else {
			n = new Node<>(k, v);
			
			if (cache.size() >= limit) {
				cache.remove(tail.key);
				remove(tail);
			}
		}
		cache.put(k,n);
		setHead(n);
	}
	public V get(K k) {
		Node<K,V> n	= null;
		if (cache.containsKey(k)) {
			n	= cache.get(k);
			remove(n);
			setHead(n);
			return n.val;
		}
		return null;
	}

	private void setHead(Node<K, V> n) {
		n.next	= head;
		n.prev	= null;
		
		if (head != null) {
			head.prev	= n;
		}
		
		head = n;
		
		if(tail == null) {
			tail	= head;
		}
	}

	private void remove(Node<K, V> n) {
		if (n.prev == null) {
			head	= n.next;
		} else {
			n.prev.next	= n.next;
		}
		
		if (n.next == null) {
			tail = n.prev;
		} else {
			n.next.prev	= n.prev;
		}
		
	}
}
