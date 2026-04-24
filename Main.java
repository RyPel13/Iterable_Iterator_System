import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections; 
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		for (int factor : new FactorIterable(12)) {
		    System.out.println(factor); // prints 1, 2, 3, 4, 6, 12
		}
		System.out.println(" ---------------------------");
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> evens = Arrays.asList(2, 4, 6);

		for (int n : new SelectiveIterable<>(numbers, evens)) {
		    System.out.println(n); // prints 2, 4, 6
		}
		System.out.println(" ---------------------------");
		
		ArrayList<Integer> qualifyingScores = new ArrayList<Integer>();
		Collections.addAll(qualifyingScores, 10, 10);
		ArrayList<Integer> scores = new ArrayList<Integer>();
		Collections.addAll(scores, 10, 9, 5, 8, 7, 9, 3, 10);
		for (Integer i : new SelectiveIterable<Integer>(scores, qualifyingScores)) {System.out.println(i);}
		System.out.println(" ---------------------------");
		
		ArrayList<String> vipList = new ArrayList<String>();
		Collections.addAll(vipList, "John Doe", "Jane Doe");
		ArrayList<String> names = new ArrayList<String>();
		Collections.addAll(names, "Superman", "Mother Goose", "Jane Doe", "King Arthur");
		for (String s: new SelectiveIterable<String>(names, vipList)) {System.out.println(s);}
	}
	
}
