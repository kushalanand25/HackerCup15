import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FrameworkClass {
	/**
	 * @author Kushal
	 * 
	 * CP shorthand methods START
	 */
	private static StringBuilder sb;
	private static BufferedReader br;
	static{
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private static final int INT(String n){
		return Integer.parseInt(n);
	}
	
	private static final long LONG(String n){
		return Long.parseLong(n);
	}
	
	private static final String[] SPLICE() throws IOException{
		return SPLICE(br.readLine());
	}
	
	private static final String[] SPLICE(String s){
		if(s != null){
			return s.split("[\\s]");
		}
		return null;
	}
	
	private static final void SOP(Object obj){
		if(obj == null){
			sb.append("null");
		}else if(obj instanceof String){
			sb.append(obj);
		}else{
			sb.append(obj.toString());
		}
		sb.append("\n");
	}
	
	private static final void PRINT(){
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
	private static final String READ() throws IOException{
		return br.readLine();
	}
	// CP shorthand END
	
	
	public static void main(String[] args) throws Exception {
		String line 	= READ();
		int T 			= INT(line);
		int N;
		String[] token;
		
		for(int t=0; t<T; t++){
			N	= INT(READ());
			SOP(N);
		}
		PRINT();
	}

}
