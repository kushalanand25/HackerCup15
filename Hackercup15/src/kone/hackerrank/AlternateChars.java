package kone.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AlternateChars {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringBuilder sb;
		String[] token;
		int T = Integer.parseInt(line);
		int N;

		for (int t = 0; t < T; t++) {
			line = br.readLine();
			token	= line.trim().split("");
			N	= token.length;
			sb	= new StringBuilder();
			
			for(int i =0; i< N;i++){
				sb.append(token[i]);
			}
			
			System.out.println();
		}
	}

}
