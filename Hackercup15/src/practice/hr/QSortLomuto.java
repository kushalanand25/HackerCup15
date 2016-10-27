package practice.hr;

import java.util.Scanner;

public class QSortLomuto {
	static void quickSort(int[] ar) {
        qsort(ar, 0 ,ar.length -1);
    }
    
    static int[] qsort(int[] ar, int x, int y){
        if(x >= y){
            return ar;
        }
        
        int idx = lumotoPartition(ar, x , y);
        printArray(ar);

        ar = qsort(ar, x , idx-1);
        ar = qsort(ar, idx+1 , y);
        
        return ar;
    }
    
    static int lumotoPartition(int[] ar, int x , int y){
        int i = x; 
        int j = x;
        int v = ar[y];
        int temp;
        for(j = x; j < y ; j++){
            if(ar[j] <= v){
                temp = ar[j];
                ar[j] = ar[i];
                ar[i++] = temp;
            }
        }
        temp = ar[i];
        ar[i] = ar[y];
        ar[y] = temp;

        return i;
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
