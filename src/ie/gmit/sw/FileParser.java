package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileParser {
	
	private Map<String, Double> temp = new ConcurrentHashMap<String, Double>();
	private String text;
	private double number = 0;
	
	//put into its own class
	public  Map<String, Double> parse(String file) throws IOException {
		
		 
		
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String next= null;
		
		while((next=br.readLine())!=null){
				// ADD EACH LINE TO THE PARSE
				String [] stuff = next.split(" ");
				//map.put(stuff[0]),stuff[1]);
				text = stuff[0];
				number = (double) Long.parseLong(stuff[1]);
				temp.put(text, number);
		}
		return temp;
		
		
	}
}
