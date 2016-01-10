package ie.gmit.sw;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public class Runner {

	
	public static void main(String[] args) throws Exception{
		
		FileParser fp = new FileParser();
		Map<String, Double> temp = new ConcurrentHashMap<String, Double>();
		Resultable resultFinal;
		int j = 0;
		temp= fp.parse("4grams.txt");
		
		/*
		for(Map.Entry<String, Double> entity : temp.entrySet()){
			System.out.println(" Key " +  entity.getKey() + " Value " + entity.getValue());
			
		}*/
		
		
		TextScorer ts = new TextScorer(temp);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter some text :");
		String text = scanner.nextLine();
		text = text.toUpperCase();
		System.out.println("Please enter key :");
		int rows = scanner.nextInt();
		String s = new RailFence().encrypt(text, rows);
		System.out.println(">" + s);
		
		BlockingQueue<Resultable> queue = new ArrayBlockingQueue<Resultable>(text.length());
		
		ThreadStuff.ReleaseTheThreads(s, queue);
		j = ThreadStuff.getNum();
		Consumer martin = new Consumer(queue, j);
		Thread t = new Thread(martin);
		t.start();
		t.join();
		Resultable finalRes = martin.getFinalResult();
		System.out.println(finalRes.getPlainText());
	}
	

}
