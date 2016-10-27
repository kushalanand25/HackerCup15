package codesprint.blackrock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
/**
 * 2
1.1837 1.3829 0.6102
1.1234 1.2134 1.2311

 * @author Kushal
 *
 */
public class Arbritrage {
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
		int N;
		double USDEUR;
		double EURGBP;
		double GBPUSD;
		double arb;

		for (int t = 0; t < T; t++) {
			USDEUR	= Double.parseDouble(READ());
			EURGBP	= Double.parseDouble(READ());
			GBPUSD	= Double.parseDouble(READ());
			arb = USDEUR * EURGBP * GBPUSD;
			N = (int) (100000 / arb);
			N = N - 100000;
			
			SOP(N > 0 ? N : 0);
		}
		PRINT();
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
