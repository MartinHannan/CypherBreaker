package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadStuff {
	static int k = 0;
	
	

		public static void ReleaseTheThreads(String x, BlockingQueue<Resultable>bq){
			//move this into a Thread Class
			ExecutorService executor = Executors.newFixedThreadPool((x.length()/2));
		        
		        for(int i=2; i<x.length()/2; i++) {
		            executor.submit(new Decryptor(bq,x,i));
		            k++;
		        }
		        
		        executor.shutdown();
		        
		        try {
					executor.awaitTermination(1, TimeUnit.DAYS);
				} 
		       catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		}
		
		public static int getNum() {
			return k;
		}
}
