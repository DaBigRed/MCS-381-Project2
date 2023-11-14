import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
public class PageRankNode { //we are doing a class now because Louis said so
	//name of the thing (eg. Dolphin named "Double")
	public String name;
	//page rank before step
	public float oldPRScore;
	//page rank after step
	public float newPRScore;
	//list of a node's links
	public LinkedList<PageRankNode> incomingLinks;
	public int nodeTotal;
	public bool repeating;
	/**
	Reads a file from the input and creates a hashmap representation
	
	**/
	public PageRankNode(String name, int nodeTotal) {
		/* makes 1 node which we will then connect */

		//make name a global value
		this.name = name;
		//keep track of total nodes to make first page rank (1/total)
		this.nodeTotal = nodeTotal;
		oldPRScore = 1/nodeTotal;
		newPRScore = 0;
		repeating = false;
		
		
	}
	//add a connection from current node to inputed node
	public void addConnection(PageRankNode connect){
		/* make a connection from current node to "connect" node */

	}
	public void StartStep(int nodeTotal){
		oldPRScore = 1/nodeTotal;
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

//scan file in
			//scanner to read file
		Scanner scanner = new Scanner(fileName);
			//shows that commas seperate values
		scanner.useDelimiter(",");
			//while there is stuff left to read

		//define variables for while loop
		String node1;
		int value1;
		String node2;
		int value2;
		LinkedList<PageRankNode> nodes;
		while(scanner.hasNext()) {
				//increment total
			int totalNodes += 1;
				//find name of current node
			node1 = scanner.next();
			//gives first 0
			value1 = scanner.next();
			node2 = scanner.next();
			value2 = scanner.next()
			//if node already exists
			if(nodes.contains(node1)){
				//add connection to node (possibly double sided?)
				//if 2 sided
				node1.addConnection(node2);

				if(value1 == value2 && nodes.contains(node2)) node2.addConnection(node1);
				else if (value1 == value2) {
					//create node2

					//connect node2 to node1
					node2.addConnection(node1);
				}
				else; //do nothing
			}

		}
			//else
				//create node, add connection(Double sided?)
	}

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
