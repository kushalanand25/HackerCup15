package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AltStones {
	
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
		return Integer.parseInt(n.trim());
	}

	private static final long LONG(String n) {
		return Long.parseLong(n.trim());
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
		int N, a, b ;
		String[] token;
		Set<Integer> solution;

		for (int t = 0; t < T; t++) {
			N = INT(READ());
			a = INT(READ());
			b = INT(READ());
			solution	= new TreeSet<Integer>();
			N--;
			
            for(int x=0;x <=N ;x++){
            	solution.add(a*x + b*(N-x));
            }
            
            for (Iterator iterator = solution.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				
				SOPrint(integer +" ");
			}
            SOPrint(NEWLINE);
		}
		PRINT();
	}
}
