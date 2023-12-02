import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
public class PageRankNode { //we are doing a class now because Louis said so
	//name of the thing (eg. Dolphin named "Double")
	public String name;
	//page rank before step
	public double oldPRScore;
	//page rank after step
	public double newPRScore;
	//list of a node's links
	public LinkedList<PageRankNode> incomingLinks;
	public boolean repeating;
	public int numOutgoing;
	/**
	Reads a file from the input and creates a hashmap representation
	
	**/
	public PageRankNode(String name) {
		/* makes 1 node which we will then connect */

		//make name a global value
		this.name = name;
		//keep track of total nodes to make first page rank (1/total)
		oldPRScore = -1;
		newPRScore = -1;
		repeating = false;
		incomingLinks = new LinkedList<PageRankNode>();
		numOutgoing = 0;
		
		
	}
	//add a connection from current node to inputed node
	public void addConnection(PageRankNode connect){
		/* make a connection from current node to "connect" node */
		incomingLinks.add(connect);
	}
	public void startStep(int nodeTotal){
		oldPRScore = 1/nodeTotal;
	}

	public void pageRankStep(int numNodes){
		/*view page rank of incoming nodes and calculate new page rank*/
		//incomingLinks[i].oldPRScore for each incoming
		float incomScore = 0;
		for(int i = 0; i < incomingLinks.size(); i++) {
			//if uninitialized, newPRScore sends back our error value
			if(incomingLinks.get(i).oldPRScore == -1) {
				newPRScore = -1;
				return;
			}
			//add into a sum variable
			//PR(wi)/c(wi)
			incomScore += incomingLinks.get(i).oldPRScore / incomingLinks.get(i).numOutgoing;
		}
		//dampen
		numNodes += 0.0;
		double dampAdd = 0.15/numNodes;
		double dampScore = dampAdd + 0.15*(incomScore);
		oldPRScore = dampScore;
	}

	public void endStep(){
		/* change oldPRScore to newPRScore for one node */
		//check if values are the same
		if (oldPRScore == newPRScore) {
			//make repeating true
			repeating = true;
		}
		oldPRScore = newPRScore;	
		newPRScore = -1;
		
	}
	public String toString() {
		return "Name: " + name + ", Page Rank: " + oldPRScore;
	}
	//public LinkedList<PageRankNode> sort(LinkedList<PageRankNode> nodes){
		
		//return 
	//}

	public static void main(String[] args) throws FileNotFoundException {
		//Create fileInputStream

//scan file in
			//scanner to read file
		Scanner scanner = new Scanner("temp.replace");
			//shows that commas seperate values
		scanner.useDelimiter(",");
			//while there is stuff left to read

		//define variables for while loop
		String stringNode1;
		int value1;
		String stringNode2;
		int value2;
		LinkedList<PageRankNode> nodes = new LinkedList<PageRankNode>();
		while(scanner.hasNext()) {
				//increment total
				//find name of current node
			stringNode1 = scanner.next();
			//gives first 0
			String tmp1 = scanner.next();
			value1 = Integer.parseInt(tmp1);
			stringNode2 = scanner.next();
			String tmp2 = scanner.next();
			value2 = Integer.parseInt(tmp2);
			PageRankNode node1 = new PageRankNode(stringNode1);
			PageRankNode node2 = new PageRankNode(stringNode2);
			//if node already exists
			if(!(nodes.contains(node2))){
				//add connection to node (possibly double sided?)
				//if 2 sided
				nodes.add(node2);
			}
			if (!(nodes.contains(node1))) {
				nodes.add(node1);
			}
			node2.addConnection(node1);
			node1.numOutgoing += 1;
			if(value1 == value2) {
				node1.addConnection(node2);
				node2.numOutgoing += 1;
			}
		}//end while 
		for(int i = 0; i < nodes.size(); i++) {
			nodes.get(i).startStep(nodes.size());
		}

		boolean allRepeat = false;
		int steps = 0;

		while(!(allRepeat) && (steps<101)) {
			steps += 1;
			for(int i = 0; i < nodes.size(); i++) {
				nodes.get(i).pageRankStep(nodes.size());
			}
			for(int i = 0; i < nodes.size(); i++) {
				nodes.get(i).endStep();
			}

			//define an accumulator 
			int accum = 0;
			for(int i = 0; i <nodes.size(); i++) {

				//if the old and new PR scores are the same, 
				//accum is incrimented by one
				if(nodes.get(i).repeating) {
					accum +=1;
				}	
			}

			//if all PR scores stayed the same, then set all repeat to
			//true, which will break the loop
			if(accum == nodes.size()) {
				allRepeat = true;
			}
		}//end while
		scanner.close();
		//LinkedList<PageRankNode> sortedNodes = sort(nodes);
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
