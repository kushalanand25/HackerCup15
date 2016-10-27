package hr.adobe;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ModularQueries {
	private static final int MOD = 1000000007;
	private static final String INPUT = "";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static StringBuilder sb;
	private static InputStream is;
	private volatile static int byt;

	static {
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

	private static boolean isSpaceChar() {
		return !(byt >= 33 && byt <= 126);
	}

	private static void skip() {
		while (getByte() != -1 && isSpaceChar())
			;
	}

	private static final int INT() {
		return (int) LONG();
	}

	private static final long LONG() {
		long num = 0;
		boolean minus = false;
		while (getByte() != -1 && !((byt >= '0' && byt <= '9') || byt == '-'))
			;
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

	private static final String READ() {
		skip();
		StringBuilder sbRet = new StringBuilder();
		while (!(isSpaceChar())) {
			sbRet.appendCodePoint(byt);
			getByte();
		}
		return sbRet.toString();
	}

	private static final int[] IAR(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = INT();
		}
		return a;
	}

	private static final char[][] MAZE(int m, int n) {
		char[][] maze = new char[m][];
		char[] row;
		int pos;
		for (int i = 0; i < m; i++) {
			row = new char[n];
			pos = 0;
			skip();
			while (pos < n && !isSpaceChar()) {
				row[pos++] = (char) byt;
				getByte();
			}
			maze[i] = ((n == pos) ? row : Arrays.copyOf(row, pos));
		}
		return maze;
	}

	private static final String[] SPLIT(String s) {
		return ((s == null) ? null : s.split("[\\s]"));
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

	private static final void PRINT() {
		System.out.println(sb.toString());
		sb.setLength(0);
	}

	//______________________________CP shorthand END__________________________

	public static void main(String[] args) throws Exception {
		int N = INT();
		int[] arr = IAR(N);
		int Q = INT();
		int[] query = new int[3];
		
		SegmentTree seg = new SegmentTree(N);
		seg.constructSegmentTree(arr);

		for (int q = 0; q < Q; q++) {
			query = IAR(3);
			if (query[1] == 0) {
				SOP(seg.getSum(query[1],query[2]));
			} else {
				seg.update(query[2], query[1], arr);;
			}
		}
		
		SOP(N);
		PRINT();
	}

	private static void set(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private static Object get(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class SegmentTree
	{
	    private int[] tree;
	    private int maxsize;
	    private int height;
	 
	    private  final int STARTINDEX = 0; 
	    private  final int ENDINDEX;
	    private  final int ROOT = 0;
	 
	    public SegmentTree(int size)
	    {
	        height = (int)(Math.ceil(Math.log(size) /  Math.log(2)));
	        maxsize = 2 * (int) Math.pow(2, height) - 1;
	        tree = new int[maxsize];
	        ENDINDEX = size - 1; 
	    }
	 
	    private int leftchild(int pos)
	    {
	        return 2 * pos + 1;
	    }
	 
	    private int rightchild(int pos)
	    {
	        return 2 * pos + 2;
	    }
	 
	    private int mid(int start, int end)
	    {
	        return (start + (end - start) / 2); 
	    }
	 
	    private int getSumUtil(int startIndex, int endIndex, int queryStart, int queryEnd, int current)
	    {
	        if (queryStart <= startIndex && queryEnd >= endIndex )
	        {
	            return tree[current];
	        }
	        if (endIndex < queryStart || startIndex > queryEnd)
	        {
	            return 0;
	        }
	        int mid = mid(startIndex, endIndex);
	        return  getSumUtil(startIndex, mid, queryStart, queryEnd, leftchild(current)) 
	                 + getSumUtil( mid + 1, endIndex, queryStart, queryEnd, rightchild(current));
	    }
	 
	    public int getSum(int queryStart, int queryEnd)
	    {
	        if(queryStart < 0 || queryEnd > tree.length)
	        {
	            return -1;
	        }
	        return getSumUtil(STARTINDEX, ENDINDEX, queryStart, queryEnd, ROOT);
	    }
	 
	    private int constructSegmentTreeUtil(int[] elements, int startIndex, int endIndex, int current)
	    {
	        if (startIndex == endIndex)
	        {
	            tree[current] = elements[startIndex];
	            return tree[current];	
	        }
	        int mid = mid(startIndex, endIndex);
	        tree[current] = constructSegmentTreeUtil(elements, startIndex, mid, leftchild(current))
	                           + constructSegmentTreeUtil(elements, mid + 1, endIndex, rightchild(current));
	        return tree[current];
	    }
	 
	    public void constructSegmentTree(int[] elements)
	    {
	        constructSegmentTreeUtil(elements, STARTINDEX, ENDINDEX, ROOT);	
	    }
	 
	    private void updateTreeUtil(int startIndex, int endIndex, int updatePos, int update, int current)
	    {
	        if ( updatePos < startIndex || updatePos > endIndex)
	        {
	            return;
	        }
	        tree[current] = tree[current] + update % MOD;
	        if (startIndex != endIndex)
	        {
	            int mid = mid(startIndex, endIndex);
	            updateTreeUtil(startIndex, mid, updatePos, update, leftchild(current));
	            updateTreeUtil(mid+1, endIndex, updatePos, update, rightchild(current));
	        }
	    }
	 
	    public void update(int update, int updatePos, int[] elements)
	    {
	        int updatediff = update - elements[updatePos]  ;
	        elements[updatePos] = update;
	        updateTreeUtil(STARTINDEX, ENDINDEX, updatePos, updatediff, ROOT);
	    }
	 
	}
}
