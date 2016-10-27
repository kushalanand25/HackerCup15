package hr.weekly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Arrays;
import java.util.InputMismatchException;

public class Target {
//	private static final int MOD = 1000000007;
	private static final String INPUT = "";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static final InputStream is;
	private static StringBuilder sb;
	private volatile static int byt;

	static {
		inbuf = new byte[1024];
		sb = new StringBuilder();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());//new BufferedInputStream(new FileInputStream(INPUT));
	}

	/**
	 * 5 6
10 8 6 4 2
0 0
1 1
2 2
3 3
4 4
5 5
	 * @param args
	 * @throws Exception
	 * @site https://www.hackerrank.com/contests/w18/challenges/target
	 */
	public static void main(String[] args) throws Exception {
		int K = INT();
		int N = INT();
		int points = 0;
		int x,y,p;
		long xy2;
		int[] arr = IAR(K);
		long[] sqr = new long[K];
		for( int i=0 ; i < K; i++) {
			sqr[i] = (long)arr[i] * arr[i];
		}

		for (int t = 0; t < N; t++) {
			x = INT();
			y = INT();
			xy2 = (long)x*x + (long)y*y;
			p=0;
			for (int j=0; j < K && xy2 <= sqr[j]; j++) {
				p++;
			}
			points += p;
//			SOPrint(p+"+");
		}
		SOP(points);
		PRINT();
	}

	/**
	 * ______________________________ CP shorthand methods START __________________________
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

	private static final void PRINT() {
		System.out.print(sb.toString());
		sb.setLength(0);
	}

	//______________________________CP shorthand END__________________________

	
}
