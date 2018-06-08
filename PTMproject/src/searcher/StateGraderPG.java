package searcher;

import java.util.ArrayList;
// To be used with HillClimbing!
public class StateGraderPG implements StateGrader<ArrayList<String>> {
	/*----------------------------Constructors------------------------------*/
	public StateGraderPG() {}
	/*----------------------------------------------------------------------*/
	@Override
	public int grade(State<ArrayList<String>> state) {
		int rows=state.getState().size(),columns=state.getState().get(0).length();
		int max=rows+columns+2;
		Square start = new Square(),goal = new Square();
		int grade=max,tmpGrade;

		start=PipeGameBoard.findStart(state);
		goal=PipeGameBoard.findGoal(state);

		int goalColumn = goal.column;
		int goalRow = goal.row;

		ArrayList<Square> sqrs= PipeGameBoard.getLastIndex(state); 

		if (sqrs.size()==1)
		{
			return max;
		}
		int i=0;
		while(i<4) {
			start = sqrs.get(i);
			int column=start.column;		
			int row=start.row;
			column=goalColumn-column;
			row=goalRow-row;
			if(column<0)
			{
				column=column*(-1);
			}
			if(row<0)
			{
				row=row*(-1);
			}
			tmpGrade=row+column;

			if (i>0)
			{

				if(tmpGrade<grade)
				{
					grade=tmpGrade;
				}
			}
			else 
			{
				grade=tmpGrade;
			}

			i++;
		}
		return grade;
	}
}
