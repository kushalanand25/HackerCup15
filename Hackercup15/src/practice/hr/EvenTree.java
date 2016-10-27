package practice.hr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Stack;

public class EvenTree {
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
	
	private static boolean isSpaceChar(){
		return !(byt >= 33 && byt <= 126);
	}
	
	private static void skip(){
		while (getByte() != -1 && isSpaceChar());
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
	
//	private static final String READ(){
//		skip();
//		StringBuilder sbRet = new StringBuilder();
//		while (!(isSpaceChar())) {
//			sbRet.appendCodePoint(byt);
//			getByte();
//		}
//		return sbRet.toString();
//	}
	
	private static final int[] IAR(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = INT();
		}
		return a;
	}
	
//	private static final char[][] MAZE(int m, int n) {
//		char[][] maze = new char[m][];
//		char[] row;
//		int pos;
//		for (int i = 0; i < m; i++) {
//			row = new char[n];
//			pos = 0;
//			skip();
//			while (pos < n && !isSpaceChar()) {
//				row[pos++] = (char) byt;
//				getByte();
//			}
//			maze[i] = ((n == pos) ? row : Arrays.copyOf(row, pos));
//		}
//		return maze;
//	}
//	
//	private static final String[] SPLIT(String s) {
//		return ((s == null) ? null : s.split("[\\s]"));
//	}
	
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
		int N = INT();
		int M = INT();
		int a,b;
//		int[][] adjMat = new int[N][N];
		al = new ArrayDeque[N+1];
		visit = new boolean[N+1];
		
		for(int i =0; i < M; i++){
			a = INT();
			b = INT();
			
//			adjMat[a][b] = 1;
//			adjMat[b][a] = 1;
			if(al[a] == null) al[a] = new ArrayDeque<Integer>();
			if(al[b] == null) al[b] = new ArrayDeque<Integer>();
			
			al[a].add(b);
			al[b].add(a);
		}
		
//		int r = decompose(al);
		dfs(1);
		
		SOP(ans);
		PRINT();
	}

	private static ArrayDeque<Integer>[] al;
	private static boolean[] visit;
	static int ans;
	
	private static int dfs(int node) {
		int vertex = 0;
		int nodes;
		visit[node] = true;
		Integer v;
		
		for (Iterator<Integer> itr = al[node].iterator(); itr.hasNext();){
			v = itr.next();
			if (!visit[v]){
				nodes = dfs(v);
				if ((nodes & 1) == 0){
					ans++;
				} else {
					vertex += nodes;
				}
			}
		}
		return vertex+1;
	}

//	private static int decompose(int[][] adjMat) {
	/*private static int decompose(ArrayDeque<Integer>[] al) {
		int ret = 0;
		BitSet bs 			= new BitSet(al.length);
		Stack<Integer> stk	= new Stack<Integer>();
		stk.push(1);
		bs.set(1);
		Integer val;
		
		Iterator<Integer> itr;
		int vertex = 0;
		int nodes;
		
		while(!stk.isEmpty()){
			for(itr = al[stk.pop()].iterator(); itr.hasNext(); ){
				val = itr.next();
				if(!bs.get(val)){
					stk.push(val);
				}
			}
		}
		
		return ret;
	}*/
	
}
