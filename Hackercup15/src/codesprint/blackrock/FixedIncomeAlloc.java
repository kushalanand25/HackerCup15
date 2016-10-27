package codesprint.blackrock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FixedIncomeAlloc {
	private static final int MOD = 1000000007;
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

	public static void main(String[] args) throws Exception {
		int T = INT();
		int min_trade_size	= INT();
		int incr			= INT();
		int aval_units		= INT();
		String port_id;	
		int port_order;	
		long total_order	= 0l;
		Map<String,Integer> port_orders	= new TreeMap<>();
		
		for (int t = 0; t < T; t++) {
			port_id = READ();
			port_order = INT();
			total_order += port_order;
			port_orders.put(port_id, port_order);
		}
		
		int alloc_unit	= 0;
		for (Entry<String, Integer> entry : port_orders.entrySet()) {
			port_id		= entry.getKey();
			port_order	= entry.getValue();
			
			alloc_unit	= getAllocUnits(min_trade_size, aval_units,port_order, total_order, incr);
			
			aval_units	-= alloc_unit;
			total_order	-= port_order;
			
			SOP(port_id + " " + alloc_unit);
		}
		PRINT();
	}

	private static int getAllocUnits(int min_trade_size, int aval_units, long port_order, long total_order, int incr) {
		double prop_alloc	= ((double)port_order/total_order) * aval_units;
		int allocated_units	= 0;
		if (prop_alloc < (min_trade_size / 2)) {
			allocated_units	= 0;
		} else {
			allocated_units	= getTradeableAmount(min_trade_size, incr, port_order);
		}
		return allocated_units;
	}

	private static int getTradeableAmount(int min_trade_size, int incr, long port_order) {
		int trdAmnt	= 0;
		int incrN	= 0;
		
		if (port_order < min_trade_size) {
			trdAmnt	= min_trade_size;
		} else {
			incrN	= (int) ((port_order - min_trade_size)%incr);
			if ((min_trade_size + (incr * incrN)) <= port_order ) {
				trdAmnt	= (min_trade_size + (incr * incrN));
			}
		}
		
		return trdAmnt;
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

	private static final int[] IAR(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = INT();
		}
		return a;
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

	private static final String READ() {
		skip();
		StringBuilder sbRet = new StringBuilder();
		while (!(isSpaceChar())) {
			sbRet.appendCodePoint(byt);
			getByte();
		}
		return sbRet.toString();
	}

	private static final String[] SPLIT(String s) {
		return ((s == null) ? null : s.split("[\\s]"));
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

	
}
