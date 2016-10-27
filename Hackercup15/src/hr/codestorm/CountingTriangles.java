package hr.codestorm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class CountingTriangles {
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

	/***
	 * 6
2 3 9 10 12 15

	 * @param args
	 * @throws Exception
	 */
	static long countAc = 0;
	static long countRt = 0;
	static long countOb = 0;
	static int p,q,r;
	
	public static void main(String[] args) throws Exception {
		int N = INT();
		int[] arr	= IAR(N);
		long[] sqr	= new long[N];
		int t = 0;
//		int count = 0;

		for (int i = 0; i < N; i++) {
			t = arr[i];
			sqr[i] = (long) Math.pow(t, 2);
		}
		
		/*for (int i = 0; i < N-2; i++) {
			int k = i+2;
			
			for (int j = i+1; j < N; j++) {
				for ( ;k < N && arr[i] + arr[j] > arr[k]; k++ ) {
					if (j!=k) {
						incrTriangle(arr,sqr,i,j,k);
					}
				}
//				count += k - j - 1;
				
				if (j!=k-1) {
					int k2 = (k < N) ? k-1 : N-1;
					incrTriangle(arr,sqr,i,j,k2);
				}
			}
		}*/
		
		for (int i = 0; i < N-2; i++) {
			int m = i+1;
			int n = i+1;
			
			for (int j = i+1; j < N; j++) {
				for(;m<N-1 && sqr[i]+sqr[j]>=sqr[m+1]; m++);
				
				n = Math.max(m, n);
				for(;n<N-1 && arr[i]+arr[j]>arr[n+1]; n++);
				
				if (sqr[i]+sqr[j]==sqr[m]) {
					countAc += Math.max(m-1-j, 0);
					countRt ++;
					countOb += n-m;
				} else {
					countAc += Math.max(m-j, 0);
					countOb += n-m;
				}
			}
		}
		
//		SOP(count);
		SOP(countAc+" "+countRt+" "+countOb);
		PRINT();
	}
	
	private static void incrTriangle(int[] arr, long[] sqr, int i, int j, int k){
		
		if(!(i > p || j > q || k > r))
			return;
		
		p = i;
		q = j;
		r = k;
		long sum;
		
//		SOP("\t"+arr[i]+","+arr[j]+","+arr[k]);
		
		sum = sqr[i] + sqr[j];
		if (sum ==  sqr[k] ) {
			countRt++;
		} else if(sum < sqr[k] ) {
			countOb++;
		} else {
			countAc++;
		}
	}
	
}
