package practice.concur;

import java.util.ArrayList;
import java.util.List;

public class ProdConsumer {
	
	private ArrayList<Integer> sharedList ;
	
	public static void main(String[] args) {
		int limit	= 20;
		ProdConsumer inst	= new ProdConsumer();
		inst.sharedList		= new ArrayList<Integer>();
		
		Producer odd	= inst.new Producer(inst.sharedList, 1, limit);
		Producer even	= inst.new Producer(inst.sharedList, 0, limit);
		Consumer cons	= inst.new Consumer(inst.sharedList, limit);
		
		Thread p1 = new Thread(odd, "odd");
		Thread p2 = new Thread(even, "even");
		Thread c1 = new Thread(cons, "Consumer");
		
		p1.start();
		p2.start();
		
		c1.start();
	}
	
	/**
	 * Producer
	 * 
	 * 
	 */
	
	class Producer implements Runnable {
		
		private Integer start;
		private int limit;
		
		public Producer (List<Integer> que, Integer init, int limit){
			this.start = init;
			this.limit = limit;
		}

		@Override
		public void run() {
			try {
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		private void update() throws InterruptedException{
			while ( start < limit + 1) {
				synchronized (sharedList){
					if( start == 0 || (sharedList.size() > 0 && (start - sharedList.get(sharedList.size() -1)) == 1 ) || (start == 1 && sharedList.size() == 1) ){
						sharedList.add(start);
//						System.out.println("-> "+ start +" : "+sharedList);
						start 	+= 2;
					} 
					
					sharedList.notify();
					
					if(start < limit + 1)
						sharedList.wait();
				}
			}
			
			if(start >= limit){
				System.out.println(Thread.currentThread().getName() + " : Completed");
			}
		}
	}
	
	
	
	/**
	 * Consumer
	 * 
	 * @author Kushal
	 *
	 */
	
	class Consumer implements Runnable {
		
		int limit;
		
		public Consumer(List<Integer> que, int limit) {
			this.limit = limit;
		}
		
		@Override
		public void run() {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private Object consume() throws InterruptedException {
			Object obj = null;
			synchronized(sharedList){
				while(sharedList.isEmpty() || sharedList.size() < limit){
					System.out.println(Thread.currentThread().getName() + " is Waiting");
					sharedList.notify();
					sharedList.wait();
				}
				
				obj = sharedList;
				System.out.println(obj.toString());
			}
			return obj;
		}
	}
}
