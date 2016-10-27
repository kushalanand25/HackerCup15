package practice.hr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Permission;

class Calculate {
    public Calculate output;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public Calculate() {
        output = this;
    }
    
    public void display(Object obj){
        if (obj instanceof Double)
            System.out.format("%.3f%n",obj);   
        else
            System.out.println(obj.toString());
    }
    
    public static Calculate get_Vol() {
        return new Calculate();
    }
    
    public int getINTVal() throws IOException {
        return Integer.parseInt(br.readLine().trim());
    }
    
    public double getDoubleVal() throws IOException {
        return Double.parseDouble(br.readLine().trim());
    }
    
    public double main (int a) {
        validateArgs(a);
        return ((double)a) * a * a;
    }
    public double main (double r) {
        validateArgs(r);
        return ( (Math.PI * 2) / 3 ) * r * r * r;
    }
    public double main (int l, int b, int h) {
        validateArgs(l,b,h);
        return ((double)l) * b * h;
    }
    public double main (double r, double h) {
        validateArgs(r,h);
        return Math.PI * r * r * h ;
    }
    
    private void validateArgs(double... r) {
        for(double d : r){
            if (d <= 0)
                throw new NumberFormatException("All the values must be positive");
        }
    }
    
    private void validateArgs(int... n) {
        for(int d : n){
            if (d <= 0)
                throw new NumberFormatException("All the values must be positive");
        }
    }
}

public class Volume {
	public static void main(String[] args) {
		Do_Not_Terminate.forbidExit();		
		try{
			Calculate cal=new Calculate();
			int T=cal.getINTVal();
			while(T-->0){
			double volume = 0.0d;		
			int ch=cal.getINTVal();			
			if(ch==1){
			
				int a=cal.getINTVal();
				volume=Calculate.get_Vol().main(a);
				
				
			}
			else if(ch==2){
			
				int l=cal.getINTVal();
				int b=cal.getINTVal();
				int h=cal.getINTVal();
				volume=Calculate.get_Vol().main(l,b,h);
				
			}
			else if(ch==3){
			
				double r=cal.getDoubleVal();
				volume=Calculate.get_Vol().main(r);
				
			}
			else if(ch==4){
			
				double r=cal.getDoubleVal();
				double h=cal.getDoubleVal();
				volume=Calculate.get_Vol().main(r,h);
				
			}
			cal.output.display(volume);
			}
					
		}
		catch (NumberFormatException e) {
			System.out.print(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Do_Not_Terminate.ExitTrappedException e) {
			System.out.println("Unsuccessful Termination!!");
		}
		
		
	}
}

class Do_Not_Terminate {
	 
    public static class ExitTrappedException extends SecurityException {
    }
 
    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}//end of Do_Not_Terminate	
	