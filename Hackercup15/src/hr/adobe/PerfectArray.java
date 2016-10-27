package hr.adobe;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class PerfectArray {
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
		System.out.println(sb.toString());
		sb.setLength(0);
	}

	//______________________________CP shorthand END__________________________

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int N = INT();
		int n2 = (N>>1);
		int x;
		int j,k;
		
		int[] arr = IAR(N);
		int[] odd = new int[n2];
		int[] even = new int[n2];
		
		j = k = 0;
		for (int i = 0; i < N ; i++ ) {
			x = arr[i];
			if ((x&1) ==0 ) {
				even[j++] = x;
			} else {
				odd[k++] = x;
			}
		}
		
		Arrays.sort(odd);
		Arrays.sort(even);
		
		int mismatch = 0;
		int mismatch1 = 0;
		int mismatch2 = 0;
		
		for (int i = 0; i < n2 ; i++ ) {
			if( arr[(i<<1)] != odd[i])
				mismatch1++;
			if( arr[(i<<1)+1] != even[i])
				mismatch2++;
		}
		
		SOP(mismatch1);
		SOP(mismatch2);
		mismatch = mismatch1+mismatch2;
		SOP(mismatch <= 1? mismatch : log2(mismatch) );//+ (int)Math.abs(mismatch2 - mismatch1));
		SOP(Math.min(log2(mismatch1), log2(mismatch2)));
		PRINT();
	}
	
	private static int log2(int n) {
		return (int) Math.ceil((Math.log(n) / Math.log(2)));
	}
}
