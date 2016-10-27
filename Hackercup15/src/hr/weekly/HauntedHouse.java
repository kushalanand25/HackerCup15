package hr.weekly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * weekly  challenge 15
 * @author Kushal
6
1 2
1 4
0 3
0 1
3 4
0 2

 * 3
 */
class HauntedHouse {
	private static final String INPUT = "";
	private static final String NEWLINE = System.lineSeparator();
	private static byte[] inbuf;
	private static int lenbuf, curbuf;
	private static StringBuilder sb;
	private static InputStream is;
	private volatile static int byt;
	static{
		inbuf = new byte[1024];
		sb = new StringBuilder();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());//new BufferedInputStream(new FileInputStream(INPUT));
	}
	
	/**
	 * CP shorthand methods START
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
	
	private static boolean isSpaceChar(){
		return !(byt >= 33 && byt <= 126);
	}
	
	private static final int INT(){
		return (int) LONG();
	}
	
	private static final long LONG(){
		long num = 0;
		boolean minus = false;
		while (getByte() != -1 && !((byt >= '0' && byt <= '9') || byt == '-'));
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
	
	private static final void PRINT(){
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
//______________________________CP shorthand END__________________________
	
	public static void main(String[] args) throws Exception {
		int N = INT();
		int L,H;
		int posL,posH;
//		MyTuple[] seg = new MyTuple[(N<<1)+1];
		int[] segLine	= new int[(N<<1)+2];
		int[] segPos	= new int[(N<<1)+2];
		int prevPos;
		
		int l,h;
		
		/*for (int t = 0; t < N; t++) {
			
			posL = INT();
			posH = INT();
			
			L = posL + N;
			H = posH + N;
			prevPos = 0;
			
			while(L >= 1){
				segLine[L]++;
				segPos[L] = (L > N)? Math.min(segPos[L]+1, (posL==0)? 0: posL+1) : Math.max(segPos[L<<1], segPos[(L<<1)+1]);
				L = L>>1;
			}
			while(H >= 1){
				segLine[H]++;
				segPos[H] = (H > N)? Math.min(segPos[H]+1, (posH==0)? 0: posH+1) : Math.max(segPos[H<<1], segPos[(H<<1)+1]);
				H = H>>1;
			}
		}
		SOP(segPos[1]);*/
		
		
		//approch 2
		/*int[] pts = new int[300001];
		Arrays.fill(pts, 0);
		int max = -1;
		int idx, limitH =-1;
		
		for(int n=0; n<N; n++){
			l = INT();
			h = INT();
			
			L = (l+1 > h)? -1 : l+1;
			H = h+1;
			limitH = Math.max(limitH, H);
			
//			SOP(L+","+H);
			
			idx = (L >= 0)? L : 0;
			while(idx <= H){
				pts[idx]++;
				idx++;
			}
		}
		
		int val;
		for(int i =0; i < limitH; i++ ){
			val = Math.min(i+1, pts[i]);
			max = Math.max(max, val);
		}
		
		SOP(max);*/
		
		Point[] pts = new Point[N];
		
		for(int n=0; n<N; n++){
			l = INT();
			h = INT();
			
			pts[n] = new Point(l,h);
		}
		
		Arrays.sort(pts);
		
//		SOP(Arrays.deepToString(pts));
		
		PRINT();
	}
	
	private static class Point implements Comparable {
		private int x;
		private int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public int compareTo(Object o) {
			int ret = Integer.MIN_VALUE;
			if (o instanceof Point) {
				ret = (((Point) o).getX() == x)? y - ((Point) o).getY() :  x - ((Point) o).getX();
			}
			return ret;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("(").append(x).append(",").append(y).append(")");
			return builder.toString();
		}

	}
}


