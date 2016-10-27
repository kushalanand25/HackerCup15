package kone.hackerrank;

import java.util.Scanner;

public class solve3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
      int t;
      long sum;
      long _a;
      t = in.nextInt();
      for (int i=0;i<t;i++) {
         _a = in.nextLong();
         sum = solveMeSecond(_a);
         System.out.println(sum);
         }
    }
    
    public static long solveMeSecond(long n){
        return Long.parseLong(Long.toBinaryString(-n-1).substring(32, 64),2);
    	/*BigInteger biMAX	= new BigInteger(Integer.toString(Integer.MAX_VALUE));
    	return new BigInteger(Integer.toString(n)).xor(biMAX).intValue();*/
    }
}