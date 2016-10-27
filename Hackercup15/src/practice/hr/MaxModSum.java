package practice.hr;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.TreeSet;

public class MaxModSum {
	private static final int MOD = 1000000007;
	private static final String INPUT = "D:\\Apps\\testCases\\practice.hr\\MaxModSum1.txt";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static StringBuilder sb;
	private static InputStream is;
	private volatile static int byt;
	static{
		inbuf = new byte[1024];
		sb = new StringBuilder();
		try {
			is = INPUT.isEmpty() ? System.in : new BufferedInputStream(new FileInputStream(INPUT));//new ByteArrayInputStream(INPUT.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		int T = INT();
		int N;
		long M;
		long max, localMax, elem;
		Long higher;
		TreeSet<Long> set = new TreeSet<Long>();
		
		
		for (int t = 0; t < T; t++) {
			N = INT();
			M = LONG();
			localMax	= 0l;
			max 		= -1l;
			set 		= new TreeSet<Long>();
			
			for(int i = 0; i < N; i++){
				elem = LONG();
				
				localMax	+= elem;
				localMax 	= (localMax < M)? localMax : localMax % M;
//				SOPrint(localMax+" ");
				
				max = (max < localMax )? localMax : max ;
				higher	= set.higher(localMax);
				if(higher != null){
					max = Math.max(max, localMax - higher + M );
				}
				
				set.add(localMax);
				
			}
//			SOPrint(NEWLINE);
			
			SOP(max);
		}
		PRINT();
	}
}
