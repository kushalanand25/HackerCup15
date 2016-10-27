package kone.hackercup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;



class NutriPlan {
	private static int limit;
	private static TreeSet<Tuple> ts;
	private static Tuple[] tummy;
	private static final Tuple target	= new Tuple(0,0,0);
	

	public static void main(String[] args) throws Exception {
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		String line			= br.readLine();
		String[] token;
		
		int T	= Integer.parseInt(line);
		int N;
		Tuple alex;
		
		for(int t=0; t < T; t++){
			ts		= new TreeSet<Tuple>();
			
			line	= br.readLine();
			token	= line.split("[\\s]");
			
			alex	= new Tuple(token);
			
			line	= br.readLine();
			N		= Integer.parseInt(line);
			Tuple food;
			
			for(int n = 0; n < N; n++){
				line	= br.readLine();
				token	= line.split("[\\s]");
				
				food	= new Tuple(token);
				
				if((food.isLessThan(alex) || alex.equals(food))&& !ts.contains(food)){
					ts.add(food);
				}else{
					/* skip food item */
					//System.out.println("\t\t\t" + food);
				}
			}
			
			initTree();
			System.out.printf("Case #%d: %s%n",t+1, dp(alex, -1)? "yes" : "no");
		}
		
	}

	private static boolean dp(Tuple item, int n) {
		if(target.equals(item)){
			return true;
		}else if(n >= limit || item.isLessThan(target)){
			return false;
		}
//		System.out.println(item);
	
		return ( dp(item,n+1) || dp(item.sub(tummy[n+1]), n+1) );
	}
	
	
	private static void initTree(){
		limit	= ts.size()-1;
		tummy	= new Tuple[limit+1];
		ts.toArray(tummy);
	}

	
	/**
	 * Inner Class tree
	 * 
	 * @author Kushal
	 *
	 */
	private static class Tuple implements Comparable<Tuple>{
		int P,C,F;
		
		public Tuple(int p,int c, int f){
			this.P	= p;
			this.C	= c;
			this.F	= f;
		}
		
		public Tuple(String[] strPCF){
			this.P	= Integer.parseInt(strPCF[0]);
			this.C	= Integer.parseInt(strPCF[1]);
			this.F	= Integer.parseInt(strPCF[2]);
		}
		
		boolean isLessThan(Tuple t){
			return (this.P < t.P && this.C < t.C && this.F < t.F);
		}
				
		Tuple sub(Tuple t){
			return new Tuple(P - t.P,C- t.C, F- t.F);
		}

		@Override
		public int compareTo(Tuple t) {
			int ret	= 0;
			if(equals(t)){
				return 0;
			}
			
			int diff = (P - t.P);
			ret	+= diff * 1000000;
			
			diff = (C - t.C);
			ret	+= diff * 1000;
			
			diff = (F - t.F);
			ret	+= diff;
			
			return ret;
		}
		
		@Override
		public String toString() {
			return new StringBuilder().
				append(P).append(".").
				append(C).append(".").
				append(F).
				toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Tuple) {
				Tuple new_name = (Tuple) obj;
				return (new_name.P == P && new_name.C == C && new_name.F == F);
			}
			return false;
		}
	}
}
