package practice.hr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class ShortestReach2 {
	private static final int MOD = 1000000007;
	private static final String INPUT = "";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static StringBuilder sb;
	private static InputStream is;
	private volatile static int byt;
	static{
		inbuf = new byte[1024];
		sb = new StringBuilder();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());//new BufferedInputStream(new FileInputStream(INPUT));
	}
	
	/**
	 * CP shorthand methods START
	 */
	
	private static int getByte() throws InputMismatchException {
		if (lenbuf == -1) {
			throw new InputMismatchException("empty buffer");
		}
		if (curbuf >= lenbuf) {
			curbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException(e.getMessage());
			}
			if (lenbuf <= 0) {
				return byt = -1;
			}
		}
		return byt = inbuf[curbuf++];
	}
	
	private static final int INT(){
		return (int) LONG();
	}
	
	private static final long LONG(){
		long num = 0;
		boolean minus = false;
		while (getByte() != -1 && !((byt >= '0' && byt <= '9') || byt == '-'));
		if (byt == '-') {
			minus = true;
			getByte();
		}
		for (;;) {
			if (byt >= '0' && byt <= '9') {
				num = (num * 10) + (byt - '0');
			} else {
				break;
			}
			getByte();
		}
		return (minus ? -num : num);
	}
	
	private static final void SOP(Object obj) {
		SOPrint(obj);
		sb.append(NEWLINE);
	}
	
	private static final void SOPrint(Object obj) {
		if (obj == null) {
			sb.append("null");
		} else if (obj instanceof String) {
			sb.append(obj);
		} else {
			sb.append(obj.toString());
		}
	}
	
	private static final void PRINT(){
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
//______________________________CP shorthand END__________________________
	
	
	public static void main(String[] args) throws Exception {
		int T = INT();
		int N;
		int M;
		int x,y,r;
		int start;
		ArrayList<Node> al;
		Map<Integer,ArrayList<Node>> adjList;
		ArrayList<Node> emptyList = new ArrayList<Node>();
		
		for (int t = 0; t < T; t++) {
			adjList = new TreeMap<Integer,ArrayList<Node>>();
			N = INT();
			M = INT();
			
			for(int i = 0; i < M ; i++){
				x	= INT();
				y	= INT();
				r	= INT();
				
				al	= adjList.containsKey(x) ? adjList.get(x) : new ArrayList<Node>();
				al.add(new Node(y,r));
				adjList.put(x,al);
				
				al	= adjList.containsKey(y) ? adjList.get(y) : new ArrayList<Node>();
				al.add(new Node(x,r));
				adjList.put(y,al);
			}
			start = INT();
			
			
			for(int n = 1; n <= N; n++) {
				if(!adjList.containsKey(n)) {
					adjList.put(n, emptyList);
				}
			}
			
			dijkstra(adjList, start);
		}
		PRINT();
	}

	private static String dijkstra(Map<Integer, ArrayList<Node>> adjList, int start) {
		int[] dist 				= new int[adjList.size()+1];
//		int[] prev				= new int[adjList.size()+1];
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		
		Arrays.fill(dist, Integer.MAX_VALUE>>1);

		for (int i = 1; i < dist.length; i++) {
			que.add(new Node(i,dist[i]));

			if (i == start) {
				dist[start] 	= 0;
				que.add(new Node(i,dist[i]));
			}
		}
		
//		prev[start] 	= 0;
		
		while (!que.isEmpty()) {
			Node nd 	= que.remove();
			Integer u	= nd.val;
			for (Node ndAdj : adjList.get(u)) {
				Integer adj = ndAdj.val;
				int alt		= dist[u] + ndAdj.cost;
				if (alt < dist[adj]) {
					dist[adj] = alt;
					que.add(new Node(adj, alt));
				}
		
			}
		}
		
		for(int j = 1 ; j < dist.length; j++) {
			int dst = dist[j];
			if (0 == dst)
				continue;
			SOPrint(dst == Integer.MAX_VALUE>>1 ? -1 : dst);
			SOPrint(" ");
		}
		SOPrint(NEWLINE);
		
		return null;
	}
	
	private static class Node  implements Comparator<Node>, Comparable<Node>{
		
		public Integer val;
		public int cost;
		
		public Node(Integer node, int cost) {
			this.val = node;
			this.cost = cost;
		}

		public int compare(Node node1, Node node2) {
			if (node1.cost < node2.cost)
				return -1;
			if (node1.cost > node2.cost)
				return 1;
			return 0;
		}

		@Override
		public int compareTo(Node nd2) {
			if (this.cost < nd2.cost)
				return -1;
			if (this.cost > nd2.cost)
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [").append(val).append("(").append(cost).append(")]");
			return builder.toString();
		}
	}
	
}
