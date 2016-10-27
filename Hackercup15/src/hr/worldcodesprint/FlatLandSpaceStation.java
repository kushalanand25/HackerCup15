package hr.worldcodesprint;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class FlatLandSpaceStation {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        if(n == m) {
            System.out.println("0");
            in.close();
            return;
        }
        
        //int c[] = new int[m];
        int v;
        BitSet bs = new BitSet(n);
        for(int c_i=0; c_i < m; c_i++){
            v = in.nextInt();
            bs.set(v+1);
        }
        in.close();
        
        //Arrays.sort(c);
        int[] l = new int[n];
        int dist=-1;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++){
            if(bs.get(i+1))
                dist = 0;
            else
                dist = (dist == -1) ? -1 : dist+1;
            l[i] = dist;
        }
        
        dist = -1;
        for(int i=n-1; i>=0; i--){
            if(bs.get(i+1))
                dist = 0;
            else
                dist = (dist == -1) ? -1 : dist+1;
            v = l[i];
            l[i] = (dist == -1) ? v : (v == -1) ? dist : Math.min(dist, v);
        }
        
        for(int i =0; i < n;i++)
            max = Math.max(max, l[i]);
        
        System.out.println(Arrays.toString(l));
        System.out.println(max);
    }
}
