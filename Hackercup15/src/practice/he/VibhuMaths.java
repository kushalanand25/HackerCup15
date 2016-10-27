package practice.he;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

class VibhuMaths {
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
	
	static int[] fn;
	public static void main(String[] args) throws Exception {
		int T = INT();
		int N;
		
		getSeries();
		
		for (int t = 0; t < T; t++) {
			N = INT();
			
			SOP(fn[N]);
		}
		PRINT();
	}
	
	private static int[] getSeries() {
		fn = new int[1000001];
		
		for(int i=0; i < 1000001; i++){
			fn[i] = dp(i);
		}
		
		return fn;
	}

	private static int dp(int n) {
		if(n <= 1){
			return 1;
		}
		
		int prod = (int)( ((long)(n-1)*fn[n-2]) % MOD);
		return (fn[n-1] + prod) % MOD;
	}
	
}
