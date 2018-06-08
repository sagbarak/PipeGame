package searcher;

import java.util.ArrayList;
import searcher.StateGraderPG;

import searcher.Moveable.Direction;

public class PipeGameBoard extends CommonSearchable<ArrayList<String>>{
	public StateGraderPG grader=new StateGraderPG();
	
	/*---------------------Constructors---------------------------------------*/
	public PipeGameBoard(State<ArrayList<String>> state) {
		this.state=state;
	}
	/*----------------------Getters/Setters-----------------------------------*/
	@Override
	public State<ArrayList<String>> getInitialState() {
		this.state.setCost(0);
		return this.state;
	}

	public int grade(State<ArrayList<String>> state) {
		return grader.grade(state);
	}
	/*-----------------------------------------------------------------------*/
	
	public boolean isGoalState(State<ArrayList<String>> state) {
		Square test=new Square();
		Square goal=new Square();
		
		goal=findGoal(state);
		test=getLastIndex(state).get(0);
		if(test.getRow()==goal.getRow() && test.getColumn()==goal.getColumn()) {
			return true;
		}
		return false;
	}

	//returns the most far index you can get (connection wise)
	static ArrayList<Square> getLastIndex(State<ArrayList<String>> state) {
		int rows=state.getState().size();
		int columns=state.getState().get(0).length();
		Square start=new Square();
		Square goal=new Square();
		Square index=new Square();
		Square nextsq=new Square();
		ArrayList<Square> squareArr=new ArrayList<Square>();
		int count=0;


		char[][] board=stateToChar(state);
		
		start=findStart(state);
		goal=findGoal(state);

		index.setColumn(start.getColumn());
		index.setRow(start.getRow());

		index=start;
		while (index != goal) { //need to compare with row and column?
			if (index==start) 
			{
				switch (count)
				{
				case 0:
					start.dirOut= Direction.right;
					break;
				case 1: 
					start.dirOut= Direction.down;
					break;
				case 2: 
					start.dirOut= Direction.up;
					break;
				case 3: 
					start.dirOut= Direction.left;
					break;
				}
			}
			int flag=0;
			if (index.dirOut == Direction.down) {

				if (index.moveIndex(rows, columns, board)) {
					nextsq.setRow(index.getRow()+1);
					nextsq.setColumn(index.getColumn());
					nextsq.dirIn = Direction.up;
					if (board[nextsq.getRow()][nextsq.getColumn()] == '|') {
						nextsq.dirOut = Direction.down;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'J') {
						nextsq.dirOut = Direction.left;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'L') {
						nextsq.dirOut = Direction.right;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'g') {
						nextsq = goal;
					}
					flag=1; //if action has succeed
				}
			}

			else if (index.dirOut == Direction.up) {
				if (index.moveIndex(rows, columns, board)) {
					nextsq.setRow(index.getRow()-1);
					nextsq.setColumn(index.getColumn());
					nextsq.dirIn = Direction.down;
					if (board[nextsq.getRow()][nextsq.getColumn()] == '|') {
						nextsq.dirOut = Direction.up;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == '7') {
						nextsq.dirOut = Direction.left;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'F') {
						nextsq.dirOut = Direction.right;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'g') {
						nextsq = goal;
					}
					flag=1;//if action has succeed
				}
			}

			else if (index.dirOut == Direction.right) {

				if (index.moveIndex(rows, columns, board)) {
					nextsq.setRow(index.getRow());
					nextsq.setColumn(index.getColumn()+1);
					nextsq.dirIn = Direction.left;
					if (board[nextsq.getRow()][nextsq.getColumn()] == '-') {
						nextsq.dirOut = Direction.right;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'J') {
						nextsq.dirOut = Direction.up;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == '7') {
						nextsq.dirOut = Direction.down;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'g') {
						nextsq=goal;
					}
					flag=1; //if action has succeed
				}
			}


			else if (index.dirOut == Direction.left) {
				if (index.moveIndex(rows, columns, board)) {
					nextsq.setRow(index.getRow());
					nextsq.setColumn(index.getColumn()-1);
					nextsq.dirIn = Direction.right;
					if (board[nextsq.getRow()][nextsq.getColumn()] == '-') {
						nextsq.dirOut = Direction.left;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'L') {
						nextsq.dirOut = Direction.up;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'F') {
						nextsq.dirOut = Direction.down;
					}
					else if (board[nextsq.getRow()][nextsq.getColumn()] == 'g') {
						nextsq=goal;
					}
					flag=1; //if action has succeed
				}
			}

			if (flag==0) {
				squareArr.add(index);
				count++;
				index=start;}
			else {
				index=nextsq;
			}
			if (count==4)
				return squareArr;
		}
		squareArr.clear();
		squareArr.add(index);
		return squareArr;
	}
	
	//transform a state to char[][] board
	static char[][] stateToChar(State<ArrayList<String>> state){
		char[][] board=new char[state.getState().size()][state.getState().get(0).length()]; //copy problem to 2D char array
		int i,j;
		
		for(i=0;i<state.getState().size();i++) {
			for(j=0;j<state.getState().get(i).length();j++) {
				board[i][j]=state.getState().get(i).charAt(j);
			}
		}
		return board;
	}
	//rotate char to the right
	public char rotate(char c) {
		char rc = 0;
		switch(c) {
		case '-': rc='|';
			break;
		case '|': rc= '-';
			break;
		case 'L': rc= 'F';
			break;
		case 'F': rc= '7';
			break;
		case '7': rc= 'J';
			break;
		case 'J': rc= 'L';
			break;
		}
		return rc;
	}
	
	/*returns the number of rotates need to be done, 0 for no rotations
	 * in order to skip unwanted and not necessaries states
	 */
	public int rotateCon(char c,char[][] board,int row,int column,int i,int j) {
		if(i==0) {	
			if(rotate(c)=='|')
				return 0;
			if(rotate(c)=='J')
				return 3;
			if(rotate(c)=='L')
				return 2;
		}	
		if(i==row-1) {
			if(rotate(c)=='|')
				return 0;
			if(rotate(c)=='F')
				return 3;
			if(rotate(c)=='7')
				return 2;
			
		}
		if(j==0) {
			if(rotate(c)=='-')	
				return 0;
			if(rotate(c)=='J')
				return 2;
			if(rotate(c)=='7')
				return 3;
		}
		if(j==column-1) {
			if(rotate(c)=='-')	
				return 0;
			if(rotate(c)=='F')
				return 2;
			if(rotate(c)=='L')
				return 3;
		}
		return 1;
	}
	/* returns an array of all possible states that can be given by a current state*/
	public ArrayList<State<ArrayList<String>>> getAllPossibleStates(State<ArrayList<String>> state){
		ArrayList<State<ArrayList<String>>> stateArr=new ArrayList<State<ArrayList<String>>>(); //array of all possible states
		ArrayList<String> orgState=new ArrayList<String>(state.getState()); //adapt state to String array
		int rows=orgState.size(),columns=orgState.get(0).length();
		char[][] board=new char[rows][columns];
		int rotateNum=0;

		board=stateToChar(state);

		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {//for the char in the [i][j] place
				if(board[i][j]!='s' && board[i][j]!='g' && board[i][j]!=' ') { //if s g or space end loop
					char[][] newBoard=new char[rows][columns]; //create board copy to new board
					for(int n=0;n<rows;n++) {
						for(int m=0;m<columns;m++) {
							newBoard[n][m]=board[n][m];
						}
					}
					rotateNum=rotateCon(newBoard[i][j],newBoard,rows,columns,i,j); //rotate condition throw unwanted states
					if(rotateNum>0 && rotateNum<4) { //rotate the number of time rotateCon returned 
					for(int l=0;l<rotateNum;l++) {
						newBoard[i][j]=rotate(newBoard[i][j]);
					}
						ArrayList<String> newProb=new ArrayList<String>(); //create a ArrayList problem
						for(int k=0;k<rows;k++) {
							newProb.add(new String(newBoard[k]));
						}
						State<ArrayList<String>> newState=new State<ArrayList<String>>(newProb); //create a state
						newState.setCameFrom(state);
						newState.setCost(grade(newState)); //give grade to each state
						stateArr.add(newState);
					}
				}
			}
		}

		return stateArr;
	}
	
	/*return the index of the pipe's starting point*/
	static Square findStart(State<ArrayList<String>> state) {
		Square start=new Square();
		char[][] board=stateToChar(state);
		int rows=state.getState().size();
		int columns=state.getState().get(0).length();

		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(board[i][j]=='s') {
					start.setRow(i);
					start.setColumn(j);
				}
			}
		}
		return start;
	}
	
	/*return the index of the pipe's ending point*/
	static Square findGoal(State<ArrayList<String>> state) {
		Square goal=new Square();
		char[][] board=stateToChar(state);
		int rows=state.getState().size();
		int columns=state.getState().get(0).length();

		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(board[i][j]=='g') {
					goal.setRow(i);
					goal.setColumn(j);
				}
			}
		}
		return goal;
	}
}
