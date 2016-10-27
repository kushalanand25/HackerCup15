package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimeWords {
	
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
		int H = INT(line);
		int M = INT(READ());
		
		String[] words	= {"zero","one","two","three","four","five","six","seven","eight","nine","ten",
				"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty",
				"twenty one","twenty two","twenty three","twenty four","twenty five","twenty six","twenty seven","twenty eight","twenty nine"};
		
		if(M == 0){
			SOP(words[H]+ " o' clock");
		}else if(M == 30){
			SOP("half past "+ words[H]);
		}else if(M < 30){ 
			if(M == 1){
				SOP(words[M]+" minute past "+words[H]);
            }else if(M == 15){
                SOP("quarter past "+words[H]);
            }else{
				SOP(words[M]+" minutes past "+words[H]);
			}
		}else if(M < 60) {
			if(M == 59){
				SOP(words[60-M]+" minute to "+words[H+1]);
			}else if(M == 45){
                SOP("quarter to "+words[H+1]);
            }else{
				SOP(words[60-M]+" minutes to "+words[H+1]);
			}
		}else{
			SOP(words[H+1]+ " o' clock");
		}
		
		
		PRINT();
	}
}
