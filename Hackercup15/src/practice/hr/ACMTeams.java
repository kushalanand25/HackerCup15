package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACMTeams {
	
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
		String line;
		String[] token= SPLICE();;
		int N,M;
		N	= INT(token[0]);
		M	= INT(token[1]);
		int max	=0, count=0, x;
		String[] talent	= new String[N];
		
		for (int t = 0; t < N; t++) {
			line = READ();
			talent[t] = line;
		}
		
		for(int i =0 ; i < N-1; i++){
			for(int j = i+1 ; j< N; j++){
				line = maxTalent(talent[i], talent[j]);
				x = countOne(line);
				if(x > max){
					max = x;
					count = 1;
				}else if(x == max){
					count++;
				}
			}
		}
		
		SOP(max);
		SOP(count);
		PRINT();
	}

	private static int countOne(String line) {
		int ones	= 0;
		for(char ch : line.toCharArray()){
			if(ch == '1'){
				ones++;
			}
		}
		return ones;
	}

	private static String maxTalent(String str1, String str2) {
		char[] a,b;
		a	= str1.trim().toCharArray();
		b	= str2.trim().toCharArray();
		
		int len = a.length;
		char[] arrChar	= new char[len];
		
		for (int i = 0; i < len; i++) {
			arrChar[i] = (char) (a[i]|b[i]);
		}
		
		return new String(arrChar);
	}
}
