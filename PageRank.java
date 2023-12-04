import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.lang.*;

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
	
	public PageRankNode(String name) {
		/* makes 1 node which we will then connect */

		//make name a global value
		this.name = name;
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
		double dampScore = dampAdd + 0.85*(incomScore);
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
		
	}
	public String toString() {
		return "Name: " + name + ", Page Rank: " + oldPRScore;
	}

	//here is sorting part

	public static void sort(LinkedList<PageRankNode> nodes){
		
		int size = nodes.size();
		//for the swap
		PageRankNode temp;

		//for each
		for(int i = 0; i < size; i++){
			//for each
			for(int j = 1; j < (size - i); j++){
				//if
				if(nodes.get(j-1).oldPRScore > nodes.get(j).oldPRScore){
					//swap
					temp = nodes.get(j-1);
					nodes.set(j-i, nodes.get(j));
					nodes.set(j, temp);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		//record starting time
		double starting = System.currentTimeMillis();

		//Create fileInputStream
		//scan file in
		//scanner to read file
		File file = new File("resources/dolphins.csv");
		Scanner scanner = new Scanner(file);
		
		//shows that commas separate values
		scanner.useDelimiter(",");

		//define variables for while loop
		String stringNode1;
		int value1;
		String stringNode2;
		int value2;
		LinkedList<PageRankNode> nodes = new LinkedList<PageRankNode>();

		//while there is stuff left to read
		while(scanner.hasNextLine()) {
			//find name of current node
			stringNode1 = scanner.next();
			System.out.println(stringNode1);
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
		scanner.close();
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

		sort(nodes);
		System.out.println(nodes);

		//get ending time
		double ending = System.currentTimeMillis();
		//print total time in milliseconds
		System.out.println("Elapsed time: " + (ending - starting));
	}
		//happy swenson
}

