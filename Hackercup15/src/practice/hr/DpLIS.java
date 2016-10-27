package practice.hr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.InputMismatchException;

public class DpLIS {
	private static final int MOD = 1000000007;
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

	public static void main(String[] args) throws Exception {
		int L = INT();
		int N;
		int[] arr = new int[L];

		for (int t = 0; t < L; t++) {
			N = INT();
			arr[t] = N;
		}
		
//		SOP(LIS(arr));
//		SOP(LISlen(arr));
		SOP(LIS2(arr));
		PRINT();
	}

	//O(n log n)
	private static Object LIS2(int[] X) {
		int l = 0;
		int len = X.length;
		int[] P = new int[len]; 
		int[] M = new int[len+1]; 
		
		for (int i =0; i < len; i++) {
			// Binary search for the largest positive j <= L
			// such that X[M[j]] < X[i]
			int lo = 1;
			int hi = l;
			int mid;
			
			while (lo <= hi) {
			     mid = (int) Math.ceil((lo+hi)/2);
			     if (X[M[mid]] < X[i])
			       lo = mid+1;
			     else
			       hi = mid-1;
			}
			 // After searching, lo is 1 greater than the
			 // length of the longest prefix of X[i]
			int newL = lo;
			
		   // The predecessor of X[i] is the last index of 
		   // the subsequence of length newL-1
			P[i] = M[newL-1];
		    M[newL] = i;
		    
		    if (newL > l) {
		    	l = newL;
		    }
		}
		
		BitSet bs = new BitSet(5);
		bs.set(0,5);
		
		
		return l;
	}

	// O(n^2)
	private static Object LISlen(int[] arr) {
		int len = arr.length;
		int[] arl = new int[len];
		int max = 0;
		
		arl[0] = 1;
		
		for(int i = 1; i < len; i++) {
			for(int j=0; j<i ;j++) {
				 if (arr[j] < arr[i] && arl[i] < arl[j] + 1) {
					 arl[i] = arl[j] + 1;
				 }
			}
			max = Math.max(max, arl[i]);
		}
//		SOP(Arrays.toString(arl));
		
		arl = null;
		return max;
	}

	private static Object LIS(int[] arr) {
		int len = arr.length;
		ArrayList<Integer>[] al = new ArrayList[len];
		ArrayList<Integer> tempLst = null;
		int lmax = 0;
		
		al[0] = new ArrayList<>();
		al[0].add(arr[0]);
//		SOP(Arrays.toString(al));
		
		for(int i = 1; i < len; i++) {
			al[i] = new ArrayList<>();
			
			for(int j=0; j<i ;j++) {
				 if (arr[j] < arr[i] && al[i].size() < al[j].size() + 1) {
					 tempLst = al[j];
				 }
			}
			al[i].addAll(tempLst);
			al[i].add(arr[i]);
			lmax = Math.max(lmax, al[i].size());
		}
		
//		SOP(Arrays.toString(al));
		return lmax;
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
		System.out.println(sb.toString());
		sb.setLength(0);
	}

	//______________________________CP shorthand END__________________________

	
}
