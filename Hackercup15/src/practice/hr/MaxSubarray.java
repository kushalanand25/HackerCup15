package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxSubarray {
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
        int[] array;
        int max;
        int local_start = -1;
        int start_idx;
        int end_idx;
        int val;
        int localMax = -1;
        int arrayMax;

		for (int t = 0; t < T; t++) {
			N = INT(READ());
            array    = new int[N];
            token   = SPLICE();
            max         = Integer.MIN_VALUE;
            val      = 0;
            localMax    = 0;
            arrayMax    = 0;
            
            for(int n=0; n<N; n++){
                array[n]    = INT(token[n]);
                val 		= array[n];
                if(val < 0 && localMax == 0){
                	localMax = val;
                }else{
                	if(localMax <= 0)
                		localMax = 0;
                	localMax    += val;
                }
                
                if(localMax == 0){
                    local_start = n;
                    localMax    = 0;
                }
                
                if(val > 0){
                	if(arrayMax <= 0)
                		arrayMax = 0;
                    arrayMax += array[n];
                }else if(val < 0 && arrayMax <= 0){
                	arrayMax = (arrayMax == 0) ? val : Math.max(arrayMax, val);
                }
                
                if(localMax > max){
                    max = localMax;
                    start_idx = local_start;
                    end_idx = n;
                }
            }
			
			SOP(max + " "+arrayMax);
		}
		PRINT();
	}
}
