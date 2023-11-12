import java.io.FileNotFoundException;
import java.util.Scanner;
public class PageRankNode { //we are doing a class now because Louis said so
	//name of the thing (eg. Dolphin named "Double")
	public String name;
	//page rank before step
	public float oldPRScore;
	//page rank after step
	public float newPRScore;
	//list of a node's links
	public PageRankNode[] incomingLinks;
	public int nodeTotal;
	public bool repeating;
	/**
	Reads a file from the input and creates a hashmap representation
	
	**/
	public PageRankNode(String name, int nodeTotal) {
		/* makes all nodes which we will then connect */

		//make name a global value
		this.name = name;
		//keep track of total nodes to make first page rank (1/total)
		this.nodeTotal = nodeTotal;
		oldPRScore = 1/nodeTotal;
		newPRScore = 0;
		repeating = false;
		//scanner to read file
		//Scanner scanner = new Scanner(fileName);
		//shows that commas seperate values
		//scanner.useDelimiter(",");
		//while there is stuff left to read
		//while(scanner.hasNext()) {
			//increment total
		//	total += 1;
			//find name of current node
		//	String current = scanner.next();
			//if it is the one we are looking for
		//	if(current == name) {
				//read 0 to get rid of it
				//add to link_info
				//read next 0 to get rid of it
			//}
			//old pr is 1/total
			//new pr is 0 for now
		//}
		
		
	}
	//add a connection from current node to inputed node
	public void addConnection(PageRankNode connect){
		/* make a connection from current node to "connect" node */

	}

	public void PageRankStep(){
		/*view page rank of incoming nodes and calculate new page rank*/

		//incomingLinks[i].oldPRScore for each incoming

		//add  into a sum variable

		//normalize

		//put into newPRScore
	}

	public void endStep(){
		/* change oldPRScore to newPRScore for one node */
		//check if values are the same
			//make repeating true
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Create fileInputStream

		//Every 4 values is 	
			//1st name
			//2nd 0 or 1
			//3rd name2
			//4th 0 or 1

		//If doesnt already exists
			//create pageRankNode for 1st name
		//either way
		//pageRankNode.addConnection(name2)

		//while not over number of steps and while any node not repeating
			//pageRankStep for all nodes
			//EndStep for all nodes

		//return thing if we need to
		//happy swenson
	}
}


