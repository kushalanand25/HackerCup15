package practice.he;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ConfusedMonk {
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
	
	
	public static void main(String[] args) throws Exception {
		int N = INT();
		int[] arr = IAR(N);
		long prod = 1;
		int gcd;
		long fxgx = 1;
		
		Arrays.sort(arr);
		
		//f(x) = (a[ 0 ] * a[ 1 ] * a[ 2 ]... * a[n-1] )
		for(int i=0; i< N ;i++){
			prod *= arr[i];
			prod = (prod > MOD)? prod % MOD: prod;
		}
		
		//g(x) = GCD (a[ 0 ], a[ 1 ], a[ 2 ]... a[n-1] )
		gcd = arr[0];
		for(int i=1; i < N;i++){
			gcd = GCD(arr[i],gcd);
			if(gcd == 1)
				break;
		}
		
		for(int j = 0; j < gcd; j++){
			fxgx *= prod;
			fxgx = (fxgx > MOD)? fxgx % MOD: fxgx;
		}
			
		SOP(fxgx);
		PRINT();
	}

	private static int GCD(int a, int b) {
		if(b == 0){
			return a;
		}
		return GCD(b, a%b);
	}
	
}
