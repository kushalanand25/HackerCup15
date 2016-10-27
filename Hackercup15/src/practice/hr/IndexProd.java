package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class IndexProd {
	
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
	
	private static final int[] INTAR() throws IOException{
		String[] tkns = SPLICE();
		int len = tkns.length;
		int[] array = new int[len];
		
		for (int i = 0; i < len; i++) {
			array[i] = INT(tkns[i]);
		}
		return array;
	}

	// CP shorthand END

	public static void main(String[] args) throws Exception {
		String line = READ();
		int N = INT(line);
		String[] token;

		token = SPLICE();
		SOP(getMaxIndexProd(token, N));
		
		PRINT();
	}

	private static long getMaxIndexProd(String[] token, int n) {
		int[] array = new int[n];
		
		for (int i = 0; i < n; i++) {
			array[i] = INT(token[i]);
		}
		
		int L,R,j;
		long prod=0L;
		long prodMax=0L;
		
		for(int i=1; i < (n-1) ; i++){
			L=0;
			R=0;
			j = i-1;
			if(array[i] == array[i-1])
				continue;
			
			while(j >= 0){
				if(array[j--] > array[i]){
					L = j+2;
					break;
				}
			}

			j = i+1;
			while(j < n){
				if(array[j++] > array[i]){
					R = j;
					break;
				}
			}
			
			prod = (L > 0 && R > 0)? (long)L*R : 0L;
			
			if( prod > prodMax){
				prodMax = prod;
			}
		}
		
		return prodMax;
	}

	private static long getMaxIndexProdExp(String[] token, int n) {
		int[] array = new int[n];
		int[] arL	= new int[n];
		int[] arR	= new int[n];
		Stack<Integer> stk  = new Stack<Integer>();
		
		for (int i = 0; i < n; i++) {
			array[i] = INT(token[i]);
		}
		
		int L,R,j;
		long prod=0L;
		long prodMax=0L;
		
		/*for(int i=1; i < (n-1) ; i++){
			L=0;
			R=0;
			j = i-1;
			
			while(j >= 0){
				if(array[j--] > array[i]){
					L = j+2;
					break;
				}
			}

			j = i+1;
			while(j < n){
				if(array[j++] > array[i]){
					R = j;
					break;
				}
			}
			
			prod = (L > 0 && R > 0)? (long)L*R : 0L;
			
			if( prod > prodMax){
				prodMax = prod;
			}
		}*/
		arL[0] 		= 0;
		arL[n-1]	= 0;
		
		for(int i=1; i < (n-1) ; i++){
			L=0;
			R=0;
			j = i-1;
			while(j>=0 || !stk.isEmpty()){
				if(array[j] > array[i]){
					stk.push(j);
					arL[i] = j;
					break;
				}
				if(stk.isEmpty()){
					j--;
				}else{
					j = stk.pop();
				}
			}
		}
		
		stk.setSize(0);
		for(int i=n-2; i > 1 ; i--){
			L=0;
			R=0;
			j = i+1;
			while(j < n || !stk.isEmpty() ){
				if(array[j] > array[i]){
					stk.push(j);
					arR[i] = j;
					break;
				}
				if(stk.isEmpty()){
					j++;
				}else{
					j = stk.pop();
				}
			}
		}
		
		
		for(int i=1; i < (n-1) ; i++){
			prod = (long)(arL[i]+1) * (arR[i]+1);
			if(prod > prodMax){
				prodMax = prod;
			}
		}
		
		return prodMax;
	}
}
