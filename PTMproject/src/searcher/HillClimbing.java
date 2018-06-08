package searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HillClimbing<T> extends CommonSearcher<T>{
	
	private long timeToRun;
	StateGrader<T> grader;
	
	public HillClimbing(long timeToRun,StateGrader<T> grader){
		this.timeToRun=timeToRun;
		this.grader=grader;
	}
	
	public ArrayList<T> search(Searchable<T> s){
		State<T> next=s.getInitialState();
		ArrayList<T> solution=new ArrayList<T>();
		solution.add(next.getState());

		long time0=System.currentTimeMillis();
		while(System.currentTimeMillis()-time0<timeToRun) {
				if(s.isGoalState(next)) {
					solution.add(next.getState());
					return backTrace(next,s.getInitialState());
				}
				else {
					List<State<T>> successors=s.getAllPossibleStates(next);
					if(Math.random()<0.7) {
						int grade=0;
						for(State<T> st: successors) {
							int g=grader.grade(st);
							if(g<grade){
								grade=g;
								st.setCameFrom(next);
								next=st;							
							}	
						}		
					}
					else {
						next=successors.get(new Random().nextInt(successors.size()));
					}
				}
				solution.add(next.getState());
			}
		return backTrace(next,s.getInitialState());
		}
	}

