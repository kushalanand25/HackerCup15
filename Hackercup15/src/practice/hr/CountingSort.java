package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
20
0 ab
6 cd
0 ef
6 gh
4 ij
0 ab
6 cd
0 ef
6 gh
0 ij
4 that
3 be
0 to
1 be
5 question
1 or
2 not
4 is
2 to
4 the

- - - - - to be or not to be - that is the question - - - -

 * @author Kushal
 *
 */
public class CountingSort {
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
		int N;
		String[] token;
        int[] numCnt = new int[100];
        Arrays.fill(numCnt,0);
        
        int mid = T >>1;
        String[] words = new String[T];
        int[] keys = new int[T];
        
		for (int t = 0; t < T; t++) {
			token = SPLICE();
			N = INT(token[0]);
			words[t] = (t < mid)? "-" : token[1];
			keys[t] = N;
            numCnt[N]++;			
		}
		

        int[] numHlp = new int[100];
        int[] hlpcurr = new int[100];
		
        int j =0;
        for(int i=0; i<100; i++){
            j += numCnt[i] ;
            numHlp[i] = j;
        }
        
        String[] sorted = new String[T];
        int idx;
        for(int i =0 ; i < T; i++){
        	j = keys[i];
        	idx = ((j > 0)? numHlp[j-1] : 0) + hlpcurr[j];
        	sorted[idx] = words[i];
        	hlpcurr[j]++;
        }
        
        for(String str: sorted){
        	SOPrint(str);
        	SOPrint(" ");
        }
        
		PRINT();
	}
}