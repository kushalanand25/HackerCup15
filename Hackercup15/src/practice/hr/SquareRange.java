package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareRange {
	
	/**
	 * @author Kushal
	 * 
	 * CP shorthand methods START
	 */
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
		int A,B;
		String[] token;
		int limit = (int) Math.ceil(Math.sqrt(1000000000));
		int[] sqr = new int[limit+1];
		int count;
		
		for (int i = 0; i <= limit; i++) {
			sqr[i] = i*i;
		}

		for (int t = 0; t < T; t++) {
			token = SPLICE();
			A	= INT(token[0]);
			B	= INT(token[1]);
			count	= 0;
			
			limit = (int) Math.floor(Math.sqrt(A));
			for(int idx = limit; sqr[idx] <= B; idx++){
				if(sqr[idx] < A){
					continue;
				}
				count++;
			}
			
			SOP(count);
		}
		PRINT();
	}
}
