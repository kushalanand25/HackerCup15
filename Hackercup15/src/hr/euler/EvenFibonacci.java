package hr.euler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class EvenFibonacci {

    public static void main(String[] args) {
    	long limit	= 40000_0000_0000_0000l;
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long[] En	= new long[50];
        En[1]	= 2;
        En[2]	= 8;
        
        for (int i=3; En[i-1] < limit; i++) {
        	En[i]	= 4l * En[i-1] + En[i-2];
        }
        
        PrintWriter pw  = new PrintWriter(System.out, false);
        long sn;
        int idx;
        for(int a0 = 0; a0 < t; a0++){
            long n = in.nextLong();
            sn	= 0l;
            idx	= 0;
            while (En[idx] <= n) {
            	sn += En[idx++];
            }
            pw.println(sn);
        }
        pw.flush();
    }
}

