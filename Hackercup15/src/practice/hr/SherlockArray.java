package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class SherlockArray {
	
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
		int N;
		String[] token;
		int[] ars;
		int sum;

		for (int t = 0; t < T; t++) {
			N = INT(READ());
			
			ars	= new int[N];
			token = SPLICE();
			sum = 0;
			
			for (int i = 0; i < N; i++) {
				sum += INT(token[i]);
				ars[i] = sum;
			}
			
			int preSum;
			int pstSum;
			boolean flag = false;
			for(int i = 1; i < N; i++){
				preSum = ars[i-1];
				pstSum = sum - ars[i];
				if(preSum == pstSum){
					flag = true;
					break;
				}
			}
			
			SOP((N==1||flag)? "YES" : "NO");
		}
		PRINT();
	}
}
