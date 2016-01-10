package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	protected BlockingQueue<Resultable> queue = new ArrayBlockingQueue<Resultable>(1000);
	private Resultable finalResult;
	private double highScore=-1000;
	private int i;
	private int j =0;
	
	public Resultable getFinalResult(){
		//finalResult= new Result("hello", 3, 100);
		return finalResult;
	}
	
	public Consumer(BlockingQueue<Resultable> queue, int i){
		this.queue = queue;
		this.i = i;
	}
	
	public void run() {
		while(j<i){
			try {
				Resultable r = queue.take();
				double score = r.getScore();
				
				if(score>highScore){
					highScore=score;
					finalResult=r;
				}
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j++;
			
		}
	}
}
