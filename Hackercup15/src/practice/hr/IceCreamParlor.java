package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class IceCreamParlor {
	
	/**
	 * @author Kushal
	 * 
	 * CP shorthand methods START
	 */
	private static final int MOD = 1000000007;
	private static StringBuilder sb;
	private static BufferedReader br;
	private static final String NEWLINE = System.lineSeparator();
	static {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static final int INT(String n) {
		return Integer.parseInt(n);
	}

	private static final long LONG(String n) {
		return Long.parseLong(n);
	}

	private static final String[] SPLICE() throws IOException {
		return SPLICE(br.readLine());
	}

	private static final String[] SPLICE(String s) {
		if (s != null) {
			return s.split("[\\s]");
		}
		return null;
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

	private static final String READ() throws IOException {
		return br.readLine();
	}

	// CP shorthand END

	public static void main(String[] args) throws Exception {
		String line = READ();
		int T = INT(line);
		int M,N;
		String[] token;

		for (int t = 0; t < T; t++) {
			M = INT(READ());
			N = INT(READ());
			token = SPLICE();
			SOP(getIndex(token, M ));
		}
		PRINT();
	}

	private static String getIndex(String[] token, int m) {
		int len = token.length;
		int[] vals	= new int[len];
		int[] diffs = new int[len];
		
		
		for (int i = 0; i < len; i++) {
			vals[i] = INT(token[i]);
			diffs[i] = m - vals[i];
		}
		
		int idx1 = 0;
		int idx2 = 0;
		for(int i=0; i < len; i++){
			for(int j = len-1; j > i; j--){
				if(vals[i] == diffs[j] ){
					idx1 = i;
					idx2 = j;
				}
			}
		}
		
		return "" + (idx1+1) +" "+ (idx2+1);
	}
}
