import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Jadeja {
	static String[] arrPlayers = new String[] { "Rohit", "Dhawan", "Kohli", "Yuvraj", "Raina", "Dhoni", "Sir Jadeja" };
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			int k = Integer.parseInt(br.readLine());
			sb.append(findKthPlayer(k)).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static String findKthPlayer(int k) {
		ArrayList<String> players = new ArrayList<>(Arrays.asList(arrPlayers));
		if (k <= players.size()) {
			return players.get(k - 1);
		}
		int idx=0;
		for (idx = 0; idx < k; idx++) {
			String player = players.get(idx);
			players.add(player);
			players.add(player);
			idx <<=1; // *= 2;
		}
		
		int teamN = idx/7;
		System.out.println(teamN);
		
//		int x=0;
//		String last="";
//		while(x!=k){
//			last=
//		}
		
		return players.get(k-1);
	}
}
