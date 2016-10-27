package hr.worldcodesprint;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class EmaSuperComp {
	private static final String INPUT = "";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static final InputStream is;
	private static StringBuilder sb;
	private volatile static int byt;

	static {
		inbuf = new byte[1024];
		sb = new StringBuilder();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());//new BufferedInputStream(new FileInputStream(INPUT));
	}
/**
 * 6 6
BGBBGB
GGGGGG
BGBBGB
GGGGGG
BGBBGB
BGBBGB

25

5 * 5
 * @param args
 * @throws Exception
 * @author Kushal
 * @see https://www.hackerrank.com/contests/worldcodesprint/challenges/two-pluses
 */
//	public static void main(String[] args) throws Exception {
//		int N,M;
//		
//		N = INT();
//		M = INT();
//
//		char[][] maze	= MAZE(N, M);
//		byte[][] plus	= new byte[N][M];
//		Set<Point> set = new HashSet<>();
//			
//		for (int i =0 ; i < N; i++){
//			for(int j =0; j < M; j++) {
//				plus[i][j] = getPlus(maze,N,M, i, j);
//				if(plus[i][j] > 1) {
//					set.add(new Point(i,j));
//				}
//				
//			}
//		}
////		SOP(Arrays.deepToString(maze));
////		SOP(Arrays.deepToString(plus));
//		SOP(set);
//		int maxProd = 0;
//		
//		for (int i =0 ; i < N; i++){
//			for(int j =0; j < M; j++) {
//				if(plus[i][j] > 1) {
//					
//				}
//			}
//		}
//		
//		SOP(maxProd);
//		PRINT();
//	}
	
	public static void main(String[] args) {

		int N,M;
		
		N = INT();
		M = INT();

		char[][] maze	= MAZE(N, M);
		int ans = 0;
		
		for (int x=0;x<N;x++)
		    for (int y=0;y<M;y++){
		    	
		        int r=0;
		        while ( x-r >= 0 && y-r >= 0 && x+r < N && y+r < M && maze[x+r][y]=='G' && maze[x-r][y]=='G' && maze[x][y+r]=='G' && maze[x][y-r]=='G') {
		            maze[x+r][y]=maze[x-r][y]=maze[x][y+r]=maze[x][y-r]='g';
		            
		                for (int X=0;X<N;X++)
		                for (int Y=0;Y<M;Y++){
		                    
		                    for (int R=0; X-R >= 0 && Y-R >= 0 && X+R < N && Y+R < M && maze[X+R][Y]=='G' && maze[X-R][Y]=='G' && maze[X][Y+R]=='G' && maze[X][Y-R]=='G'; R++)
		                        ans=Math.max(ans,(1+4*r)*(1+4*R));
		                    }
		            r++;
		        }
		        
		        for ( r=0; x-r >= 0 && y-r >= 0 && x+r < N && y+r < M && maze[x+r][y]=='g' && maze[x-r][y]=='g' && maze[x][y+r]=='g' && maze[x][y-r]=='g'; r++)
		            maze[x+r][y]=maze[x-r][y]=maze[x][y+r]=maze[x][y-r]='G';
		    }
		
		SOP(ans);
		PRINT();
	}

	private static byte getPlus(char[][] maze,int n, int m, int i, int j) {
		if (maze[i][j] != 'G')
			return 0;
		
		byte size = 1;
		for (int p = 1; i-p >= 0 && j-p >= 0 && i+p < n && j+p < m; p++ ) {
			if (maze[i-p][j] == 'G' && maze[i+p][j] == 'G' && maze[i][j-p] == 'G' && maze[i][j+p] == 'G') {
				size+=4;
			} else {
				break;
			}
		}
		return size;
	}

	/**
	 * ______________________________ CP shorthand methods START __________________________
	 */

	private static int getByte() throws InputMismatchException {
		if (lenbuf == -1) {
			throw new InputMismatchException("empty buffer");
		}
		if (curbuf >= lenbuf) {
			curbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException(e.getMessage());
			}
			if (lenbuf <= 0) {
				return byt = -1;
			}
		}
		return byt = inbuf[curbuf++];
	}

	private static final int INT() {
		return (int) LONG();
	}

	private static final long LONG() {
		long num = 0;
		boolean minus = false;
		while (getByte() != -1 && !((byt >= '0' && byt <= '9') || byt == '-'))
			;
		if (byt == '-') {
			minus = true;
			getByte();
		}
		for (;;) {
			if (byt >= '0' && byt <= '9') {
				num = (num * 10) + (byt - '0');
			} else {
				break;
			}
			getByte();
		}
		return (minus ? -num : num);
	}

	private static final char[][] MAZE(int m, int n) {
		char[][] maze = new char[m][];
		char[] row;
		int pos;
		for (int i = 0; i < m; i++) {
			row = new char[n];
			pos = 0;
			skip();
			while (pos < n && !isSpaceChar()) {
				row[pos++] = (char) byt;
				getByte();
			}
			maze[i] = ((n == pos) ? row : Arrays.copyOf(row, pos));
		}
		return maze;
	}

	private static boolean isSpaceChar() {
		return !(byt >= 33 && byt <= 126);
	}

	private static void skip() {
		while (getByte() != -1 && isSpaceChar())
			;
	}

	private static final void SOP(Object obj) {
		SOPrint(obj);
		sb.append(NEWLINE);
	}

	private static final void SOPrint(Object obj) {
		if (obj == null) {
			sb.append("null");
		} else if (obj instanceof String) {
			sb.append(obj);
		} else {
			sb.append(obj.toString());
		}
	}

	private static final void PRINT() {
		System.out.print(sb.toString());
		sb.setLength(0);
	}

	//______________________________CP shorthand END__________________________

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			if (x == p.x) {
				return y - p.y;
			} else {
				return x - p.x;
			}
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[").append(x).append(",").append(y).append("]");
			return builder.toString();
		}
	}
}
