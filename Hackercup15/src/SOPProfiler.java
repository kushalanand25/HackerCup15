

public class SOPProfiler {


	   public static void main(String...args) throws Exception {
	     
	     long max = 1l;
	     long count = Long.parseLong("1000000000000000000");
	     StringBuffer sbuf = new StringBuffer();
	     StringBuilder sbld = new StringBuilder();
	     
	     long start = System.currentTimeMillis();
	     for (int i = 0; i < 100000; i++) {
//	    	 count = Long.parseLong("1000000000000000000");
	       System.out.println(max+" "+count);
	     }
	     System.err.println("SOP time: " +
	       (System.currentTimeMillis() - start));	// 1520 ms
	     
	     
	     /**
	      * String Builder takes less time if console is empty 240 ms
	      * otherwise takes same/greater time	320 ms
	      */
	     start = System.currentTimeMillis();
	     for (int i = 0; i < 100000; i++) {
	    	 
//	    	 count = Long.parseLong("1000000000000000000");
	    	 sbld.append(max);
	    	 sbld.append(" ");
	    	 sbld.append(count);
	    	 sbld.append("\n");
	     }
	     System.out.print(sbld.toString());
	     System.err.println("SBld time: " +
	    		 (System.currentTimeMillis() - start));	// 312 ms	SBld(0)
	     
	     
	     /**
	      * string buffer takes average time if console is empty 270 ms
	      * but takes lot more time if console is filled 	300ms-450ms  max 1020ms
	      */
	     start = System.currentTimeMillis();
	     for (int i = 0; i < 100000; i++) {
	    	 
//	    	 count = Long.parseLong("1000000000000000000");
	    	 sbuf.append(max);
	    	 sbuf.append(" ");
	    	 sbuf.append(count);
	    	 sbuf.append("\n");
	     }
	     System.out.print(sbuf.toString());
	     System.err.println("SBUf time: " +
	    		 (System.currentTimeMillis() - start));	// 270 ms	SBUf(0)
	   }
}

