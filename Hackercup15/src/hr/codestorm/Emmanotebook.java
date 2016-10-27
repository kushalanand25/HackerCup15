package hr.codestorm;

import java.util.Scanner;

public class Emmanotebook {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long n = t >> 1;
        
        if( (t & 1) == 0)
            System.out.println( (((n * (n+1)) + (n+1)* (n+2)) >> 1) - 1);
        else
            System.out.println( ((n+1)* (n+2)) - 1);
    }
}
