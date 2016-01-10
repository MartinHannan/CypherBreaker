package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.*; 

public class TextScorer {
	private static Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	
	public TextScorer(Map<String, Double> m){
		this.map = m;
	}
	
	public static double getScore(String text){
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				score += computeLogScore(text.substring(i, i + QuadGramMap.GRAM_SIZE));
			}
		}
		//System.out.println(score+ ": " + text);
		return score;
	}
	
	public static double computeLogScore(String quadgram){
		if (map.containsKey(quadgram)){
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
		}else{
			return 0f;
		}
	}
}
