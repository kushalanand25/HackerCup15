package kone.hackercup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AutoComplete {
	private static final int ALPH = 26;
	static Node root;

	private static class Node{
		Node[] next = new Node[ALPH];
		boolean isWord;
		private int count;
		
		public void addString(String s)
	    {
			Node temp = this;
			int k;
			int limit = s.length();
			for(k=0; k < limit; k++)
			{
			    int index = s.charAt(k) - 'a';
			    if (temp.next[index] == null){
			    	temp.count++;
			    	temp.next[index] = new Node();
			    }
			    temp = temp.next[index];
			}
			temp.isWord = true;
	    }
		
		public int getPrefix(String s){
			Node nd = this;
			int len = 0;
			int index;
			int prevMatch = 0;
			
			for(int x=0;x < s.length(); x++){
				index = s.charAt(x) - 'a';
				if(nd.next[index] == null ){
					break;
				}
				
				if(nd.next[index].isWord){
					len	= x;
				} 
				if(nd.count > 1 ){
					prevMatch = x+1;
				}
				
				nd	= nd.next[index];
			}
			
			if(prevMatch!=0 && nd.isWord){
				System.out.println("\t:"+ s.substring(0, prevMatch));
				return prevMatch;
			}
			
			System.out.println("\t"+ s.substring(0, len));
			return len;
		}
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] token;
		int T = Integer.parseInt(line);
		int N;
		long keys;
		
		for (int t = 0; t < T; t++) {
			line = br.readLine();
			N = Integer.parseInt(line);
			root = new Node();
			keys = 0;
			TrieST trie	= new TrieST<Integer>();
			
			for(int n=0;n < N; n++){
				line = br.readLine();
				root.addString(line);
				
				keys += root.getPrefix(line);
//				trie.put(line, n);
//				System.out.println(trie.longestPrefixOf(line));;
			}
			
			
			System.out.println(keys);
		}
	}

}
