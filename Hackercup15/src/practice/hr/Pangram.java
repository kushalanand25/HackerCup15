package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pangram {
	
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
		int N ;
		line = line.replaceAll("[\\s]", "").toLowerCase();
		N = line.length();
		boolean[] alpha = new boolean[26];
		boolean allFound = true;
		int idx;
		
		for (int t = 0; t < N; t++) {
			idx	= line.charAt(t) - 'a';
			if(!alpha[idx])
				alpha[idx] = true;
		}
		
		for(int i =0; i < 26; i++){
			if(alpha[i] == false){
				allFound = false;
				break;
			}
		}
		
		if(!allFound){
			SOPrint("not ");
		}
		SOPrint("pangram");
		PRINT();
	}
}
