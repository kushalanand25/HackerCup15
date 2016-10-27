package practice.hr;

import java.util.Scanner;

public class IPAddress {
	public static void main(String []argh)
    {
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
        {
            String IP = in.next();
            System.out.println(IP.matches(new myRegex().pattern));
        }

    }
}

class myRegex{
	public String pattern;
	public myRegex() {
		this.pattern = "(([0]{1,3}\\.|25[0-5]\\.)|2[0-4][0-9]\\.|[0-1]{0,1}[0-9]{1,2}\\.|[00]{0,1}[0-9]\\.){3}([0]{1,3}|25[0-5]|2[0-4][0-9]|[0-1]{0,1}[0-9]{1,2}|[00]{0,1}[0-9])";
	}
}
