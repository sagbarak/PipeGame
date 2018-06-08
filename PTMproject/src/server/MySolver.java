package server;

import java.util.ArrayList;

import searcher.BestFS;
import searcher.PipeGameBoard;
import searcher.State;

public class MySolver implements Solver<ArrayList<String>> {

	ArrayList<String> problem=new ArrayList<String>();
	ArrayList<String> solution=new ArrayList<String>();

	/*-----------------Constructor---------------------*/
	public MySolver() {}

	public MySolver(ArrayList<String> problem) {
		this.problem=problem;
	}
	/*------------------------------------------------*/

	/*Ask solver for a solution, need to decide which algorithm to use*/
	@Override
	public ArrayList<String> solve(ArrayList<String> problem) { //ask Solver for a solution
		//		BFS<ArrayList<String>> searcher=new BFS<ArrayList<String>>();
		//		DFS<ArrayList<String>> searcher=new DFS<ArrayList<String>>();
		BestFS<ArrayList<String>> searcher=new BestFS<ArrayList<String>>();
		//		HillClimbing<ArrayList<String>>  searcher=new HillClimbing<ArrayList<String>>(10000,new StateGraderPG());
		State<ArrayList<String>> state=new State<ArrayList<String>>(problem);
		ArrayList<ArrayList<String>> solution=searcher.search(new PipeGameBoard(state));
		ArrayList<String> actions=new ArrayList<String>();
		actions=solutionActions(solution);
		return actions;
	}

	/* Transfer solution as gets from searcher to PipeGame actions
	 * Takes 2 states (Initial state and goal state) and looking for the differences between them, 
	 * than return the number of rotates that have made for each block
	 */
	public ArrayList<String> solutionActions(ArrayList<ArrayList<String>> solution){
		ArrayList<ArrayList<String>> problemArr=new ArrayList<ArrayList<String>>(solution);
		ArrayList<String> initial=problemArr.get(problemArr.size()-1);
		ArrayList<String> goal=problemArr.get(0);
		ArrayList<String> actions=new ArrayList<String>();
		int rotate=0;

		for(int i=0;i<initial.size();i++) {
			for(int j=0;j<initial.get(i).length();j++) {
				if(initial.get(i).charAt(j)!=goal.get(i).charAt(j)) {
					if(initial.get(i).charAt(j)=='-' || initial.get(i).charAt(j)=='|') {
						rotate=1;
					}
					else if(initial.get(i).charAt(j)=='J') {
						switch(goal.get(i).charAt(j)) {
						case 'L': rotate=1;
						break;
						case 'F': rotate=2;
						break;
						case '7': rotate=3;
						break;
						}
					}
					else if(initial.get(i).charAt(j)=='L') {
						switch(goal.get(i).charAt(j)) {
						case 'F': rotate=1;
						break;
						case '7': rotate=2;
						break;
						case 'J': rotate=3;
						break;
						}
					}
					else if(initial.get(i).charAt(j)=='F') {
						switch(goal.get(i).charAt(j)) {
						case '7': rotate=1;
						break;
						case 'J': rotate=2;
						break;
						case 'L': rotate=3;
						break;
						}
					}
					else if(initial.get(i).charAt(j)=='7') {
						switch(goal.get(i).charAt(j)) {
						case 'J': rotate=1;
						break;
						case 'L': rotate=2;
						break;
						case 'F': rotate=3;
						break;
						}
					}
					actions.add(i+","+j+","+rotate);
				}
			}
		}
		return actions;
	}
}

