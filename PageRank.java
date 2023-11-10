import java.io.FileNotFoundException;
import java.util.Scanner;
public class PageRank { //we are doing a class now because Louis said so
	//name of the thing (eg. Dolphin named "Double")
	public String name;
	//page rank before step
	public float old_pr;
	//page rank after step
	public float new_pr;
	//list of a node's links
	public String[] link_info;
	/**
	Reads a file from the input and creates a hashmap representation
	@input: Data, a file that contains the data
	@Param; Hashmap that represents the data
	**/
	public PageRank(String name, String fileName) {
		//make name a global value
		this.name = name;
		//keep track of total nodes to make first page rank (1/total)
		int total = 0;
		//scanner to read file
		Scanner scanner = new Scanner(fileName);
		//shows that commas seperate values
		scanner.useDelimiter(",");
		//while there is stuff left to read
		while(scanner.hasNext()) {
			//increment total
			total += 1;
			//find name of current node
			String current = scanner.next();
			//if it is the one we are looking for
			if(current == name) {
				//read 0 to get rid of it
				//add to link_info
				//read next 0 to get rid of it
			}
			//old pr is 1/total
			//new pr is 0 for now
		}
		
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		//Create a variable for the file
		//things need to be added here to clarify the pathway
		//run through readFile
	}
}
