package practice.hr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class PrivateReflection {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int num=Integer.parseInt(br.readLine().trim());
		Object o;
		
		PrivateReflection inst = new PrivateReflection();
		Class k = Private.class;
		
		Constructor cons = k.getDeclaredConstructor(inst.getClass());	
		cons.setAccessible(true);
		o = cons.newInstance(inst );
	
		Method m = k.getDeclaredMethod("powerof2", int.class);
		m.setAccessible(true);
		
		System.out.println(m.invoke(o, num));
		
		System.out.println("An instance of class: "+o.getClass().getSimpleName()+" has been created");
		
		display(64.0d);
	}//end of main
  	class Private
 	{
       private String powerof2(int num)
 	   {
           return ((num&num-1)==0)?"power of 2":"not a power of 2";
       }
  	}//end of Privtate
  	
  	public static void display(Object obj){
        if (obj instanceof Double)
            System.out.format("%.3f",obj);   
        else
            System.out.println(obj.toString());
    }
}//end of solution