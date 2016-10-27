package practice.hr;

import java.util.*;
public class QuickSort {
       
    static void quickSort(int[] ar) {
        qsort(ar, 0 ,ar.length -1);
//        printArray(ar);
    }
    
    static int[] qsort2(int[] ar, int x, int y){
        if( x >= y)
            return ar;
        int v = ar[x];
        int i = x;
        int j = y;
        int temp;
        
        while(i<j){
        	while(i < j && ar[i] < v)
        		i++;
        	while(j > x && ar[j] > v)
        		j--;
        	if(ar[i] > ar[j]){
                temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
            }else if(ar[j] == v){
            	j--;
            }
        	
        }
        ar[i] = v;
        
        ar = qsort(ar,x, i-1);
        ar = qsort(ar, i+1, y);
        
        for(int n = x; n <=y ; n++ ){
            System.out.print(ar[n] + " ");
        }
        System.out.println("");
        
		return ar;
    }
 
    static int[] qsort(int[] ar, int x, int y){
        if( x >= y)
            return ar;
        /*int v = ar[x];
        int i = x;
        int j = y;
        int temp;
        
        while(i<j){
            if(ar[i] > ar[j]){
                temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
                i++;
            }else{
                j--;
            }
        }*/
        int i = partition(ar, x , y);
        ar = qsort(ar,x, i-1);
        ar = qsort(ar, i+1, y);
        
        for(int n = x; n <=y ; n++ ){
            System.out.print(ar[n] + " ");
        }
        System.out.println("");
        
        return ar;
    }
    
    static int partition(int[] ar, int x, int y) {
        int[] lt = new int[y-x];
        int[] gt = new int[y-x+1];
        int Lcount = 0;
        int Gcount = 0;
        int v = ar[x];
        
        for(int i = x; i <= y; i++){
            if(ar[i] < v){
                lt[Lcount++] = ar[i];
            }else{
                gt[Gcount++] = ar[i];
            }
        }
        for(int i=x; i <= y; i++){
            if(i-x < Lcount){
                ar[i] = lt[i-x];
            }else /*if(i == Lcount){
                ar[i] = v;
            }else*/{
                ar[i] = gt[i - Lcount -x];
            }
        }
        return Lcount+x;
    }
    
    static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
       
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt(); 
        }
        quickSort(ar);
    }    
}

