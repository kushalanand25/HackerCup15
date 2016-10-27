import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MergeSort {
	/*
	 * Complete the function below.
	 */

	/* Write your custom functions here */
	static void mergeArray(int []a, int []b, int M ){
	    int idxA = 0;
	    int j	= M;;
	    
	    for(int i = 0; i < 2*M; i++ ){
	    	if(idxA < M && a[idxA] < b[i]){
	    		j	= idxA+M;
	    		while(j > i){
	    			b[j]=b[j-1];
	    			j--;
	    		}
	    		b[i]= a[idxA];
	    		idxA++;
	    	}
	    }
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int _a_cnt = 0;
		int[] _a = new int[100001];
		int[] _b = new int[200002];
		
		try {
			_a_cnt = sc.nextInt();
		}catch (Exception e) {
			 System.out.println("Here: " + e.getMessage()); 
		} 

		for( int i = 0;i < _a_cnt;i++ ){			_a[i] = sc.nextInt();		
	}
	for( int i = 0;i < _a_cnt;i++ ){
			_b[i] = sc.nextInt();		
		}	
		mergeArray(_a ,_b,_a_cnt);
		for( int i = 0;i < 2 * _a_cnt;i++ ){
			System.out.print(_b[i] + " ");		
		}
	}
}
