package kone.hackercup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


class LazerMaze {
	private static char[][][] MAZE;
	private static int gx,gy;
	private static int M,N;
	
//	private static int state;

	public static void main(String[] args) throws Exception {
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		String line			= br.readLine();
		String[] token;
		
		int T	= Integer.parseInt(line);
		int sx	= -1;
		int	sy	= -1;
		
		for(int t=1; t <= T; t++){
			line	= br.readLine();
			token	= line.split("[\\s]");
			
			M	= Integer.parseInt(token[0]);
			N	= Integer.parseInt(token[1]);
			
			MAZE	= new char[4][M][N];
			
			for(int x=0; x<M; x++){
				line	= br.readLine();
				MAZE[0][x]	= line.toCharArray();
				if(line.indexOf('S') != -1){
					sx	= x;
					sy	= line.indexOf('S');
					MAZE[0][x][sy]	= 'V';	// set visited
				}
				if(line.indexOf('G') != -1){
					gx	= x;
					gy	= line.indexOf('G');
					MAZE[0][x][gy]	= '.';
				}
			}
			
			analyse();
			
			int result	= path(sx,sy,0);
			System.out.printf("Case #%d: %s%n",t, result == Integer.MAX_VALUE? "impossible" : ""+result);
		}
		
	}
	
	private static int path(int sx, int sy, int steps) {
		if( sx == gx && sy == gy){
			return steps;
		}
		
		MAZE[state(steps)][sx][sy]	= 'V';
		int min	= Integer.MAX_VALUE;
		
		if(isSpace(state(steps+1),sx+1,sy,true)){
			min	= Math.min(min, path(sx+1, sy, steps+1));
		}
		if(isSpace(state(steps+1),sx,sy+1,true)){
			min	= Math.min(min, path(sx, sy+1, steps+1));
		}
		if(isSpace(state(steps+1),sx-1,sy,true)){
			min	= Math.min(min, path(sx-1, sy, steps+1));
		}
		if(isSpace(state(steps+1),sx,sy-1,true)){
			min	= Math.min(min, path(sx, sy-1, steps+1));
		}
		
//		System.out.println(steps);
		printMaze(state(steps));
		if(min == Integer.MAX_VALUE){
			MAZE[state(steps)][sx][sy]	= '*';
		}
		return min;
	}
	
	private static int state(int steps){
		return steps % 4;
	}

	private static void analyse() {
		char ch;
		
		//copy maze , rotate lasers
		for(int s=1; s<4; s++){
			for(int x=0; x<M; x++){
				for(int y=0; y<N; y++){
					ch				= MAZE[s-1][x][y];
					
					switch(ch){
					case 'V': ch	= '.'; break;
					case '>': ch	= 'v'; break;
					case 'v': ch	= '<'; break;
					case '<': ch	= '^'; break;
					case '^': ch	= '>'; break;
					default:break;
					}
					
					MAZE[s][x][y]	= ch;
				}
			}
		}
		
		
		//define barrier
		int i;
		int j;
		
		for(int s=0; s<4; s++){
			for(int x=0; x<M; x++){
				for(int y=0; y<N; y++){
					ch				= MAZE[s][x][y];
					i	= x;
					j	= y;
					switch(ch){
					case '>': {
						while(j < N-1 && isSpace(s,i,++j,false) ){
							MAZE[s][i][j]	= '*';
						}
						break;
					}
					case 'v': {
						while(i < M-1 && isSpace(s,++i,j,false) ){
							MAZE[s][i][j]	= '*';
						}
						break;
					}
					case '<': {
						while(--j >= 0 && isSpace(s,i,j,false) ){
							MAZE[s][i][j]	= '*';
						}
						break;
					}
					case '^': {
						while(--i >= 0 && isSpace(s,i,j,false) ){
							MAZE[s][i][j]	= '*';
						}
						break;
					}
					default:break;
					}
				}
			}
			printMaze(s);
		}
	}
	
	private static void printMaze(int state){
		StringBuilder sb	= new StringBuilder(M*N);
		
		for(int x = 0; x < M; x++){
			sb.append(MAZE[state][x]).append("\n");
		}
		
//		System.out.println(sb);
		
	}
	
	private static boolean isSpace(int s, int x, int y,boolean checkLaser){
		if((x < 0 || x >= M )||(y < 0 || y >= N )){
			return false;
		}
		
		char ch	= MAZE[s][x][y];
		switch(ch){
//		case '\u0000':  
		case '*': return (!checkLaser); 
		case 'V':  
		case '#':  
		case '>':  
		case 'v':  
		case '<':  
		case '^': return false; 
		default: return true;
		}
	}

}
