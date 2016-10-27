package hr.epic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class WhiteFalconSequence {
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
	
	private static final int[] IAR(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = INT();
		}
		return a;
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
	
	private static long[][] dp;
	private static int[] arr;
	
	public static void main(String[] args) throws Exception {
		int N = INT();
		arr = IAR(N);
		long max = Long.MIN_VALUE;
		long sum = 0l;
		
//		for (int n = N>>1; n >= 1; n--) {
//			for (int f=0; f <= N-(n<<1); f++) {
//				for(int r=N-1; r >= f+(n<<1)-1; r--){
//					sum = 0l;
//					
//					for(int i=0; i < n; i++){
//						sum += ar[f+i] * ar[r-i];
//					}
//					
//					max = Math.max(sum,max);
//				}
//			}
//		}

		dp = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Long.MIN_VALUE);
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i][i] = arr[i - 1];
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				if (dp[i][j] == Long.MIN_VALUE) {
					dp[i][j] = f(i, j);
				}
			}
		}
		
//		SOP(Arrays.deepToString(dp));
		
		for (int i = 1; i <= N ; i++) {
			for (int j = i; j <= N ; j++) {
				if (dp[i][j] > max)
					max = dp[i][j];
			}
		}
		
		SOP(max);
		PRINT();
	}
	
	private static long f(int i,int j)
	{
		if (dp[i][j] != Long.MIN_VALUE) {
			return dp[i][j];
		}
		if (j == i + 1) {
			return (long) arr[i - 1] * arr[j - 1];
		}
		dp[i][j] = Math.max((long) arr[i - 1] * arr[j - 1],
				(((long)arr[i - 1] * arr[j - 1]) + f(i + 1, j - 1)));
		return dp[i][j];
	}
}
