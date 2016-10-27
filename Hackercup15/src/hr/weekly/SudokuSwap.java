package hr.weekly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.InputMismatchException;

/**
 * weekly challenge 15
 * @author Kushal
2
2 1 9 7 5 3 4 8 6
3 7 5 8 6 4 9 1 2
8 4 6 2 9 1 3 5 7
1 9 8 6 7 5 2 4 3
5 6 4 1 3 2 7 9 8
7 2 3 4 8 9 5 6 1
4 4 7 3 1 6 8 2 9
9 8 1 5 2 7 6 3 4
6 3 2 9 5 8 1 7 5
4 6 2 5 7 1 9 8 3
7 9 1 2 8 3 4 6 5
5 8 3 6 4 9 2 7 1
6 1 7 4 9 8 5 3 2
9 4 8 3 5 2 6 1 7
2 3 5 1 6 7 8 9 4
1 7 6 9 2 4 3 5 8
3 5 4 8 1 6 7 2 9
8 2 9 7 3 5 1 4 6

 *
 */
public class SudokuSwap {
	private static final int MOD = 1000000007;
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
	
	private static void skip(){
		while (getByte() != -1 && isSpaceChar());
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
	
	
	private static final int[] IAR(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = INT();
		}
		return a;
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
		int T = INT();
		boolean isValidSudoku;
		boolean rowSwap;
		boolean colSwap;
		int[][] sudoku;
		int[][] sudokuCopy;
		int val,val2;
		int x1,x2,y1,y2,c1,r1,c12,r12;
		int sumR,sumC;
		int[] bsI = new int[9];
		int[] bsJ = new int[9];
		
		for (int t = 1; t <= T; t++) {
			
			sudoku = new int[9][];
			for(int i =0 ; i < 9; i++){
				sudoku[i] = IAR(9);
			}
			
			isValidSudoku = true;
			rowSwap = false;
			colSwap = false;
			x1=x2=y1=y2= c1=r1=c12=r12= -1;
			
			// check sudoku
			for(int i=0; i < 9;i++){
				Arrays.fill(bsI, 0);
				Arrays.fill(bsJ, 0);
				sumR = 0;
				sumC = 0;
				
				for(int j=0; j < 9 ; j++){
					// col check - SIDE Scan
					val = sudoku[i][j];
					sumR += val;
					if (bsI[val-1] == 1){
						c1 = j;
//						SOP(val+"-col:"+i+","+j);
					} else {
						bsI[val-1]++;
					}
					
					// row check - TOP scan
					val2 = sudoku[j][i];
					sumC += val2;
					if (bsJ[val2-1] == 1){
						r1 = j;
//						SOP(val2+"-row:"+j+","+i);
					} else {
						bsJ[val2-1]++;
					}
					
//					SOP(x1+","+y1+" -> "+x2+","+y2);
				}
				if (sumR != 45){
					rowSwap = true;
//					SOP("Row-"+i+":"+(45 - sumR));
					if (sumR > 45){
						x1 = i;
					} else {
						x2 = i;
					}
				}
				
				if (sumC != 45){
					colSwap = true;
//					SOP("Col-"+i+":"+(45 - sumC));
					if (sumC > 45){
						y1 = i;
					} else {
						y2 = i;
					}
				}
			}
			
			isValidSudoku	= !(rowSwap|colSwap);
			if (rowSwap ^ colSwap){
				if (colSwap){
					x1 = r1;
					x2 = r1;
					
					for(int i = 0;i < 9; i++){
						if (i != r1 && sudoku[r1][y1] == sudoku[i][y1] && sudoku[r1][y2] == sudoku[i][y2]){
							r12 = i;
							break;
						}
					}
				}
				if (rowSwap){
					y1 = c1;
					y2 = c1;
					
					for(int j = 0;j < 9; j++){
						if (j != c1 && sudoku[x1][c1] == sudoku[x1][j] && sudoku[x2][c1] == sudoku[x2][j]){
							c12 = j;
							break;
						}
					}
				}
			}
			
			sudokuCopy = new int[9][];
			for(int i =0 ; i < 9 ; i++){
				sudokuCopy[i] = Arrays.copyOf(sudoku[i],9);
			}
			
			
			SOP("Case #" + t + ":");
			if (isValidSudoku){
				SOP("Serendipity");
			} else {
				if (c12 != -1 || r12 != -1){
					if (colSwap){
						val = sudokuCopy[r12][y1];
						sudokuCopy[r12][y1] = sudokuCopy[r12][y2];
						sudokuCopy[r12][y2] = val;
						
						if (checkValid(sudokuCopy)){						
							printSolution(r12,y1,r12,y2);
						}
					} else {
						val = sudokuCopy[x1][c12];
						sudokuCopy[x1][c12] = sudokuCopy[x2][c12];
						sudokuCopy[x2][c12] = val;
						
						if (checkValid(sudokuCopy)) {
							printSolution(x1,c12,x2,c12);
						}
					}
				}
				
				val = sudoku[x1][y1];
				sudoku[x1][y1] = sudoku[x2][y2];
				sudoku[x2][y2] = val;
				
				if (checkValid(sudoku)) {
					printSolution(x1,y1,x2,y2);
				}else{
					SOPrint("X-x-X-x-X");
					printSolution(x1,y1,x2,y2);
				}
			}
		}
		
		PRINT();
	}
	
	private static void printSolution(int x1, int y1, int x2, int y2){
		x1++;
		x2++;
		y1++;
		y2++;
		
		if (x1 < x2 || y1 < y2){
			SOP("("+x1+","+y1+") <-> ("+x2+","+y2+")");
		} else {
			SOP("("+x2+","+y2+") <-> ("+x1+","+y1+")");
		}
	}
	
	private static boolean checkValid(int[][] sudoku){
		int[] dgt = new int[10];
		boolean isValid = true;
		
		outer : 
		for(int row=0; row < 3;row++){
			for(int col=0; col < 3;col++){
				Arrays.fill(dgt, 0);
				
				for(int i=0; i < 3;i++){
					for(int j = 0;j < 3; j++){
						if (dgt[sudoku[i+(row*3)][j+(col*3)]] == 1){
							isValid = false;
							break outer;
						}
						dgt[sudoku[i+(row*3)][j+(col*3)]]++;
					}
				}
			}
		}
		
		return isValid;
	}
	
}
