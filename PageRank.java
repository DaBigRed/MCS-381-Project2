import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
public class PageRank {
	/**
	Reads a file from the input and creates a hashmap representation
	@input: Data, a file that contains the data
	@Param; Hashmap that represents the data
	**/
	public HashMap<Integer, Integer>[] readFile(FileInputStream data) {
		HashMap<Integer, Integer> numOut= new HashMap<Integer, Integer>();
		HashMap<Integer, Float> pageRank = new HashMap<Integer, Float>();
		HashMap<Integer, LinkedList<Integer>> incoming = new HashMap<Integer, LinkedList<Integer>>();
		
		
		return ;
	}
	public static void main(String[] args) throws FileNotFoundException {
		//Create a variable for the file
		//things need to be added here to clarify the pathway
		FileInputStream data = new FileInputStream(args[0]);
		HashMap<Integer, Integer> dataMap = readFile(data);
		//run through readFile
	}
}
