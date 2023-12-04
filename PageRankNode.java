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
	
	/**
	 * --init--
	 *  @param name: String of the name of the node
	 *  @return none
	 **/
	public PageRankNode(String name) {
		//initialize values
		this.name = name;
		oldPRScore = 0;
		newPRScore = 0;
		repeating = false;
		incomingLinks = new LinkedList<PageRankNode>();
		numOutgoing = 0;
	}
	/**
	 * add an incoming connection from current node to inputed node
	 * @param connect: a PageRankNode to connect to the current
	 * @return none
	 **/
	public void addConnection(PageRankNode connect){
		//add new connection to incoming links
		incomingLinks.add(connect);
	}
	/**
	 * calculate initial PR Score
	 * @param nodeTotal: total nodes in the system\
	 * @return none
	 **/
	public void startStep(int nodeTotal){
		//make initial pageRank with nodeTotal
		oldPRScore = 1.0/nodeTotal;
	}
	/**
	 * perform one step of page rank
	 * @param int: number of nodes in the system
	 * @return none
	 **/
	public void pageRankStep(int numNodes){
		//incomingLinks[i].oldPRScore for each incoming
		double incomScore = 0;
		//iterate through incoming links
		for(int i = 0; i < incomingLinks.size(); i++) {
			//add into a sum variable
			//PR(wi)/c(wi)
			incomScore += incomingLinks.get(i).oldPRScore / incomingLinks.get(i).numOutgoing;
		}
		//dampen
		double dampAdd = 0.15/numNodes;
		double dampScore = dampAdd + 0.85*(incomScore);
		newPRScore = dampScore;
	}
	/**
	 * Move newly calculated score to old PR Score to prepare for the next step
	 * @return none
	 **/
	public void endStep(){
		//pput calculated score into the reserved score
		oldPRScore = newPRScore;
		
	}
	/**
	 * @return String representation of a page rank node
	 **/
	public String toString() {
		return "Name: " + name + ", Page Rank: " + oldPRScore + "\n";
	}

	//here is sorting part
	/**
	 * Bubble sort page rank nodes
	 * @param nodes: A linked list of page rank nodes to be sorted
	 * @return none
	 **/
	public static void sort(LinkedList<PageRankNode> nodes){
		
		int size = nodes.size();
		//for the swap
		PageRankNode temp;

		//for each
		for(int i = 0; i < size-1; i++){
			//for each
			for(int j = 1; j < (size - i); j++){
				//if
				if(nodes.get(j-1).oldPRScore < nodes.get(j).oldPRScore){
					//swap
					temp = nodes.get(j-1);
					nodes.set(j-1, nodes.get(j));
					nodes.set(j, temp);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		//record starting time
		double starting = System.currentTimeMillis();
		//scan file in
		//scanner to read file
		//Change for using different files
		//File file = new File("dolphins.csv);
		//File file = new File("polblogs.csv");
		File file = new File("karate.csv");
		Scanner scanner = new Scanner(file);
		
		//shows that commas and new lines separate values
		scanner.useDelimiter(",|\\n");
		//create linked list for nodes in the system
		LinkedList<PageRankNode> nodes = new LinkedList<PageRankNode>();
		//create linked list of strings to check if a node already exists in previous list
		LinkedList<String> nodeNameList = new LinkedList<String>();
		//while there is stuff left to read
		while(scanner.hasNext()) {
			//find name of current node
			String stringNode1 = scanner.next();
			//gives first 0
			String tmp1 = scanner.next();
			int value1 = Integer.parseInt(tmp1);
			//find name of the next node
			String stringNode2 = scanner.next();
			//tmp2 stores 0 or 1 depending on if the graph is undirected
			//or directed, respectfully
			String tmp2 = scanner.next();
			//System.out.println(tmp2);
			int value2 = Integer.parseInt(tmp2);
			//create generic nodes for error, to be replaced
			PageRankNode node1 = new PageRankNode("error");
			PageRankNode node2 = new PageRankNode("error");

			//if node2 doesn't already exist in the nodes linked list
			if(!(nodeNameList.contains(stringNode2))){
				//add node2 and it's name to respective list
				node2 = new PageRankNode(stringNode1);
				nodes.add(node2);
				nodeNameList.add(stringNode2);
			}
			else {//if already in the list
			        //find where in nodes list the current node is
				for(int i = 0; i < nodes.size(); i++){
					if(nodes.get(i).name.equals(stringNode2)){
						//put that node into a variable
						node2 = nodes.get(i);
					}
				}
			}
			//same as above but for node1
			if (!(nodeNameList.contains(stringNode1))) {
				node1 = new PageRankNode(stringNode1);
				nodes.add(node1);
				nodeNameList.add(stringNode1);

			}
			else {
				for(int i = 0; i < nodes.size(); i++){
				       if(nodes.get(i).name.equals(stringNode1)){
				       	        node1 = nodes.get(i);
				       }
				}
			}		
			//add a connection from node1 to node2
			node2.addConnection(node1);
			//add one outgoing for node1
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
		//call sort on all nodes
		sort(nodes);
		//print
		System.out.println(nodes);
		//get ending time
		double ending = System.currentTimeMillis();
		//print total time in milliseconds
		System.out.println("Elapsed time: " + (ending - starting));
	}
		//happy swenson
}
