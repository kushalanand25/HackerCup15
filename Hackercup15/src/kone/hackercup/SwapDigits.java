package kone.hackercup;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class SwapDigits {

	public static void main(String[] args) throws Exception {
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		String line			= br.readLine();
//		String[] token;
		char[] digits;
		char dgt;
		
		int T	= Integer.parseInt(line);
//		int N;
		int pMax	= 0;
		int pMin	= 0;
		int len;
		
		for(int t=0; t< T; t++ ){
			line	= br.readLine();
//			N		= Integer.parseInt(line);
			len		= line.length();
			
			digits	= new char[line.length()];
			line.getChars(0, len, digits, 0);
			
			pMax	= 0;
			pMin	= 0;

			for(int i=len - 1; i > 0; i-- ){
				dgt	= digits[i];
				
				pMax	= (digits[pMax] < dgt)? i : pMax;
				pMin	= (digits[pMin] > dgt)? i : pMin;
			}
			
//			System.out.printf("digit[%d]: %c, digit[%d]: %c%n",pMax,digits[pMax],pMin,digits[pMin]);
			System.out.printf("Case #%d: %s %s%n", t+1, printSwap(digits, pMin), printSwap(digits, pMax) );
		}
	}
	
	private static String printSwap(char[] digits, int pos){
		StringBuilder sbResult	= new StringBuilder(10);
		
		sbResult.append(digits);
		if(digits[pos] != '0'){
			sbResult.setCharAt(0, digits[pos]);
			sbResult.setCharAt(pos, digits[0]);
		}
		
		return sbResult.toString();
	}

}
