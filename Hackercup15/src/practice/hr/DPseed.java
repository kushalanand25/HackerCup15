package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 2 6
13 8
1 1

1 1



50 1000000
204330 918937 672290 23567 634769 718594 518795 49640 752750 667624 581506 337547 582850 839125 832402 675188 56801 532933 280541 453117 878598 851407 558462 575731 788610 962732 510609 689448 775762 540936 499137 980093 459873 687779 3660 94642 406373 38807 660635 675476 706431 242141 13023 289282 597618 361778 480822 170771 894711 277716
623889 289662 645476 698703 381745 434086 177787 892354 639886 953550 433290 655376 449995 409515 343155 453655 20510 265881 8814 681145 941357 231597 439638 470732 37231 553608 832510 518054 724379 243573 795770 864619 49587 957597 79673 431331 391682 257460 840037 547919 727361 789678 203294 177355 199193 62801 147361 219702 845033 156174


868539134 947640315 974461629 752562338 573203161 263117230 108093061 199037511 285493009 920706549 446034667 341678574 681433029 800403958 249289495 361548500 171848187 69703065 254319311 822077349 450745295 742839010 980895744 348825913 98865496 429863750 323439893 767617990 251264797 348994090 766475465 138831582 594713247 719740380 712633266 35805497 881269170 352797241 787244438 124468555 199780572 190429505 555816545 591101260 308039969 945789412 743948711 287641115 163960781 240476189

 * 
 * 
 * @author Kushal
 *
 */
public class DPseed {
	
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
		int N,K;
		String[] token = SPLICE(line);
        N = INT(token[0]);
        K = INT(token[1]);
        
        int[] F = new int[N];
        long[] C = new long[N];
        int[] seed = new int[K+N-1];
        seed = new int[100];
        
        token = SPLICE();
        for(int i=N-1; i >= 0; i-- ){
            F[i] = INT(token[N-1-i]);
//            seed[K-1+i] = F[i];
            seed[i] = F[i];
        }
        
        token = SPLICE();
        for(int i=0;i < N; i++){
            C[i] = LONG(token[i]);
        }
        
        
        long prd;
        int val;
        int l;
        /*seed[0] = M;
        for(int k = 1; k <= K ;k++){
        	l = 0;
        	while(l < N && l < k ){
        		prd	= (C[l] * seed[k-1-l]);
        		seed[k] += (prd > 1000000007) ? prd % 1000000007 : prd;
        		if(seed[k] > 1000000007){
        			seed[k] -= 1000000007;
        		}
        		l++;
        	}
        }
//        SOP(Arrays.toString(seed));
        M = F[0] / seed[K-1];*/
        
        //----------------- Approach 2
        
/*        for(int k = K-N; k >=0; k--){
        	l = 0;
        	prd = 0;
        	while(l < N-1){
        		prd += C[l] * seed[k+N-l-1];
        		prd %= 1000000007;
        		l++;
        	}
			val = (int) ( (seed[k+N] - prd)/C[l]);
			seed[k] = (val > 0)? val : 1000000007 - val;
        }
        
        for(int i=0; i < N ; i++){
        	SOPrint(seed[i] + " ");
        }
*/      
        for(int i=0; i < MOD; i++){
        	l = 0;
        	prd = 0;
        	while(l < N){
        		prd += C[l] * seed[(i+l)%100];
        		prd %= MOD;
        		l++;
        	}
        	seed[(N+i)%100] = (int) prd;
        	if(prd == F[N-1] || prd == 204330 || prd == 277716){
        		SOP(i+ " - "+prd);
        		break;
        	}
        }
        
        

		PRINT();
	}
}
