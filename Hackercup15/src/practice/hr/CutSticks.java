package practice.hr;
import java.io.*;
import java.util.*;

public class CutSticks {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] token;
		int N = Integer.parseInt(line);
        line = br.readLine();
        token = line.split("[\\s]");
        int len = token.length;
        int[] dgt = new int[len];
        int i = 0;
        
        for(String num: token){
            dgt[i++] = Integer.parseInt(num);
        }
        
        Arrays.sort(dgt);
        int x = dgt[0];
        System.out.println(len);
        
        for(i=0;i<len; i++ ){
            while(x == dgt[i]){
                i++;
            }
            System.out.println(len -i);
            x = dgt[i];
        }
    }
}