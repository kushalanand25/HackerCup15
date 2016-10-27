package hr.weekly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Arrays;
import java.util.InputMismatchException;

public class Ghosts {
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
	 * 
	 * @param args
	 * @throws Exception
	 * @site https://www.hackerrank.com/contests/w18/challenges/ghosts
	 */
	public static void main(String[] args) throws Exception {
		
		assert(divBy3(6));
		assert(divBy4(8));
		assert(divBy5(10));
		assert(gcd(1,2) ==1);
		int A = INT();	// towns
		int B = INT();	// street
		int C = INT();	// houses
		int D = INT();	// apartments
		int N = 0;

		for (int a = 1; a <= A; a++) {
			for (int b=1; b<= B; b++) {
				for (int c=1; c<= C; c++) {
					for (int d=1; d<= D; d++) {
						if ( divBy3(Math.abs(a-b)) && divBy5(b+c) && divBy4(c*a) && gcd(a,d) == 1 ) {
//							SOP(a+","+b+","+c+","+d);
							N++;
						}
					} 
				}
			}
		}
		SOP(N);
		PRINT();
	}

	private static boolean divBy3(int num) {
//		int sum = Integer.bitCount(abs & 1431655765) - Integer.bitCount(abs & 715827882);
//		return sum % 3 == 0;
		int sum = 0;
	    while (num > 3) {
	        sum = (num >> 2) + sum;
	        num = (num >> 2) + (num & 3);
	    }
	    return (num == 0 || num == 3);
	}
	
	private static boolean divBy4(int abs) {
		return (abs & 3) == 0;
	}
	
	private static boolean divBy5(int abs) {
		int last = abs % 10;
		return last == 0 || last == 5;
	}
	
	private static int gcd(int a, int b) {
		if ( b == 0 )
			return a;
		return gcd ( b , a - b * ( a / b ) ); 
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
