package hr.euler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
/**
 * 
 * 
 * @author Kushal
 * {@link} http://mathworld.wolfram.com/Partition.html
 */

public class Partitions {
	
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
		int gk1;
		int gk2;
		int sign, p1 ,p2;
		long sum;
		String[] token;
//		int[] Pn = new int[60001];
//		Pn[0]	= 1;
//		Pn[1]	= 1;
//		Pn[2]	= 2;
	
		BigInteger[] biPn = new BigInteger[60001];
		biPn[0] = BigInteger.ONE;
		biPn[1] = BigInteger.ONE;
		biPn[2] = new BigInteger("2");
		BigInteger bp1, bp2, bsum, bsign;
		BigInteger minusONE = new BigInteger("-1");
		BigInteger BMOD = new BigInteger("1000000007");
		
		
		for (int i = 3; i < 60000; i++) {
			gk1 = 1;
//			sum = 0;
			bsum = BigInteger.ZERO;
			for(int k = 1; k <= i; k++){
				if(i - gk1 < 0)
					break;
				gk1	= (k*(3*k - 1)) >> 1; 
				gk2	= (k*(3*k + 1)) >> 1; 
//				sign = ((k & 1) == 1? 1: -1 ) ;
//				p1 = (i-gk1 < 0 ? 0 : Pn[i-gk1]);
//				p2 = (i-gk2 < 0 ? 0 : Pn[i-gk2]);
//				sum  = (sum + sign*((p1 >= 0? p1 : MOD -p1) + (p2 >=0? p2 :MOD -p2))) % MOD;
				bsign	= ((k & 1) == 1? BigInteger.ONE: minusONE ) ;
				bp1		= (i-gk1 < 0 ? BigInteger.ZERO : biPn[i-gk1]);
				bp2		= (i-gk2 < 0 ? BigInteger.ZERO : biPn[i-gk2]);
				bsum 	= bsum.add(bsign.multiply(bp1.add(bp2))); 
				
			}
//			Pn[i]	= (int) ((sum > 0)? sum :MOD -sum);
			biPn[i]	= bsum;
		}
		
		for (int t = 0; t < T; t++) {
			N = INT(READ());
			SOP(biPn[N].mod(BMOD));
		}
		PRINT();
	}
}
