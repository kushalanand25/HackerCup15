package hr.weekly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class BagsApple {
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
	
	private static final String READ(){
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
	
	private static final void PRINT(){
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
//______________________________CP shorthand END__________________________
	
	
	public static void main(String[] args) throws Exception {
		int N = INT();
//		int[] rem = new int[3];
//		Arrays.fill(rem, 0);
		int[] bags;
		int idx;
		int apples = 0;
		int items;
		
		bags = IAR(N);
		Arrays.sort(bags);

		int x;
		int[][] dp 	= new int[N+1][3];
		Arrays.fill(dp[0], 0);
		
		for (int i = 1; i <= N; i++) {
			items	= bags[i-1];
			idx		= items % 3;
			
//			if (idx == 0){
//				apples += items;
//				continue;
//			}
			
//			idx--;
//			left[idx][rem[idx]] = items;
//			rem[idx]++;
			
			for(int j = 0; j<3; j++){
				x = (j+3 - idx) % 3;
				dp[i][j] = Math.max(dp[i-1][x] + items, dp[i-1][j]);
			}
			
		}
		
		
		/*apples += (div[2]/3) * 6;
		apples += (div[1]/3);
		
		div[2] = div[2]%3;
		div[1] = div[1]%3;
		
		apples += Math.min(div[1],div[2] ) * 3;*/
		
		SOP(apples);
		PRINT();
	}
	
}
