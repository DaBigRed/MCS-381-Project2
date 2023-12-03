import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.*;

public class PageRankNode { //we are doing a class now because Louis said so
	//name of the thing (eg. Dolphin named "Double")
	public String name;
	//page rank before step
	public double oldPRScore;
	//page rank after step
	public double newPRScore;
	//list of a node's links
	public LinkedList<PageRankNode> incomingLinks;
	//a boolean to record if oldPRScore is the same is newPRScore
	//after a step
	public boolean repeating;
	//an int to keep track of # of outgoing links
	public int numOutgoing;
	/**
	Reads a file from the input and creates a hashmap representation
what is this comment
	**/
	public PageRankNode(String name) {
		/* makes 1 node which we will then connect */

		//make name a global value
		this.name = name;
		//keep track of total nodes to make first page rank (1/total)
//are we actually doing the above comment here? I don't think so.
//we need to somewhere though
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

		//turn numNodes into a float
		numNodes += 0.0;
		double dampAdd = 0.15/numNodes;

//I think the 0.15 should be 0.85 according to the formula in the slides
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
//why are we setting newPRScore to -1? this just overwrites the oldPR --> 
//newPR movement in the line above
		newPRScore = -1;
		
	}
	public String toString() {
		return "Name: " + name + ", Page Rank: " + oldPRScore;
	}
	//public LinkedList<PageRankNode> sort(LinkedList<PageRankNode> nodes){
		
		//return 
	//}

	public static void main(String[] args) throws FileNotFoundException {
		
		//record starting time
		starting = System.currentTimeMillis();

		//Create fileInputStream
//scan file in
		//scanner to read file
		Scanner scanner = new Scanner("temp.replace");
		//shows that commas seperate values
		scanner.useDelimiter(",");

		//define variables for while loop
		String stringNode1;
		int value1;
		String stringNode2;
		int value2;
		LinkedList<PageRankNode> nodes = new LinkedList<PageRankNode>();

		//while there is stuff left to read
		while(scanner.hasNext()) {
//increment total
//What is the above comment mentioning?
			//find name of current node
			stringNode1 = scanner.next();
			//gives first 0
			String tmp1 = scanner.next();
			value1 = Integer.parseInt(tmp1);
			//find name of the next node
			stringNode2 = scanner.next();
			//tmp2 stores 0 or 1 depending on if the graph is undirected
			//or directed, respectfully
			String tmp2 = scanner.next();
			value2 = Integer.parseInt(tmp2);

			//create nodes for each of the nodes in the current line of the
			//csv file
			PageRankNode node1 = new PageRankNode(stringNode1);
			PageRankNode node2 = new PageRankNode(stringNode2);

			//if node2 doesn't already exist in the nodes linked list
			if(!(nodes.contains(node2))){
				//add node2
				nodes.add(node2);
			}
			//same as above but for node1
			if (!(nodes.contains(node1))) {
				nodes.add(node1);
			}
			//add a connection from node1 to node2
			node2.addConnection(node1);
			node1.numOutgoing += 1;

			//if the connection is undirected
			if(value1 == value2) {
				//connect the other way as well
				node1.addConnection(node2);
				node2.numOutgoing += 1;
			}
		}//end while 

		//do the start step for each node
		for(int i = 0; i < nodes.size(); i++) {
			nodes.get(i).startStep(nodes.size());
		}

		//define variables
		boolean allRepeat = false;
		int steps = 0;

		//main while loop
		while(!(allRepeat) && (steps<101)) {
			steps += 1;

			//for each node
			for(int i = 0; i < nodes.size(); i++) {
				//do a pageRankStep
				nodes.get(i).pageRankStep(nodes.size());
			}

			//for each node
			for(int i = 0; i < nodes.size(); i++) {
				//do the end step
				nodes.get(i).endStep();
			}

			//define an accumulator which will track how many oldPR and 
			//newPR scores are the same
			int accum = 0;

			//for each node
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

//need to work on sorting nodes
		//LinkedList<PageRankNode> sortedNodes = sort(nodes);

		//get ending time
		ending = System.currentTimeMillis();
		//print total time in milliseconds
		System.out.println("Elapsed time: " + (ending - starting));
	}
	

//we need to add timing mechanism too





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
