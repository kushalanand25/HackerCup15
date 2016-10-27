package practice.hr;

import java.io.*;
import java.util.*;

public class InsertionSrt {

    public static void insertionSortPart2(int[] ar)
    {       
        int len  = ar.length;
        int v,j;
        for(int i=1; i< len ;i++){
        	v = ar[i];
            for(j=i; j>0 && ar[j-1] > v; j--){
            	ar[j] = ar[j-1];
            }
            ar[j] = v;
            printArray(ar);
        }
    }  
    
    
      
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
       int s = in.nextInt();
       int[] ar = new int[s];
       for(int i=0;i<s;i++){
            ar[i]=in.nextInt(); 
       }
       insertionSortPart2(ar);    
                    
    }    
    private static void printArray(int[] ar) {
      for(int n: ar){
         System.out.print(n+" ");
      }
        System.out.println("");
   }
}

