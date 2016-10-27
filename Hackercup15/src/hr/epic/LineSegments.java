package hr.epic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class LineSegments {
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
	
	
	public static void main(String[] args) throws Exception {
		int N = INT();
		long[] pts = new long[N];
		int A,B;
		long pt;
		long prev = -1l;
		int j=0;
		
		for (int i = 0; i < N; i++) {
			A = INT();
			B = INT();
			
			pt = A;
			pt = (pt<<32) + B;
			
			if(prev != pt){
				pts[j] = pt;
				j++;
				prev = pt;
			}
		}
		
//		pts = Arrays.copyOf(pts, j); 
//		Arrays.sort(pts);
		
		int preA, preB;
		preA = preB = -1;
//		ArrayList<Integer> start = new ArrayList<>();
//		ArrayList<Integer> stop = new ArrayList<>();
//		ArrayList<Integer> count = new ArrayList<>();
		int[] start = new int[100000];
		int[] stop = new int[100000];
		int[] count = new int[100000];
		int curCount; 
		int len = 0;
		boolean added;
		
		for(long cur : pts){
			A = (int) (cur >> 32);
			B = (int) (cur & 0xffffffff);
			
//			SOP(A+","+B);
			added = false;
//			len = start.size();
			
			inner:
			for(int i = 0; i <= len ; i++){
//				preA = start.get(i);
//				preB = stop.get(i);
//				curCount = count.get(i);
				preA = start[i];
				preB = stop[i];
				curCount = count[i];
				
				if(A < preB && preB >= B && preA <= A ){
					
				} else {
					added = true;
//					start.set(i, A);
//					stop.set(i, B);
//					count.set(i, curCount+1);
					start[i] = A;
					stop[i] = B;
					count[i] = curCount+1;
				}
			}
			
			if(!added){
//				start.add(A);
//				stop.add(B);
//				count.add(1);
				len++;
				start[len] = A;
				stop[len] = B;
				count[len] = 1;
			}
		}
		
		int max = -1;
		for(int i = 0; i <= len ; i++){
			max = Math.max(max, count[i]);
		}
		
		SOP(max);
		
//		int x=0,c=0,i=0;
//		while (i < N) {
//			if (getB(pts[i + 1]) > getB(pts[i])) {
//				x++;
//				i++;
//			} else {
//				int t = 0, p = i;
//				while (getB(pts[p + 1]) <= getB(pts[p])) {
//					t++;
//					p++;
//				}
//				if (t > x) {
//					i++;
//				} else {
//					i += t;
//				}
//			}
//
//		}
//		SOP(x);
		PRINT();
	}
	
	private static int getB (long lpt){
		return (int) (lpt & 0xffffffff);
	}
}
