package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cavity {
	
	/**
	 * @author Kushal
	 * 
	 * CP shorthand methods START
	 */
	private static StringBuilder sb;
	private static BufferedReader br;
	private static final String NEWLINE = System.lineSeparator();
	static {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static final int INT(String n) {
		return Integer.parseInt(n);
	}

	private static final long LONG(String n) {
		return Long.parseLong(n);
	}

	private static final String[] SPLICE() throws IOException {
		return SPLICE(br.readLine());
	}

	private static final String[] SPLICE(String s) {
		if (s != null) {
			return s.split("[\\s]");
		}
		return null;
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
		System.out.println(sb.toString());
		sb.setLength(0);
	}

	private static final String READ() throws IOException {
		return br.readLine();
	}

	// CP shorthand END

	public static void main(String[] args) throws Exception {
		String line = READ();
		int T = INT(line);
		int N;
		String[] token;
        int[][] plane = new int[T][T];

		for (int t = 0; t < T; t++) {
			line = READ();
			for(int j = 0; j < T; j++){
                plane[t][j] = INT(""+line.charAt(j));
            }
		}
        
        for(int i = 0; i < T;i++ ){
            for(int j=0; j <T; j++){
                if(i> 0 && i < T-1 && j>0 && j< T-1 &&  isCavity(plane,i,j)){
                    SOPrint("X");
                }else{
                	SOPrint(plane[i][j]);
                }
            }
            SOPrint(NEWLINE);
        }
        
		PRINT();
	}
    
    private static boolean isCavity(int[][] plane, int x, int y){
        int v   = plane[x][y];
        if(plane[x-1][y] == 0 || plane[x-1][y] >= v){
            return false;
        }else if(plane[x][y-1] == 0 || plane[x][y-1] >= v){
            return false;
        }else if(plane[x+1][y] == 0 || plane[x+1][y] >= v){
            return false;
        }else if(plane[x][y+1] == 0 || plane[x][y+1] >= v){
            return false;
        }
        return true;
    }
}
