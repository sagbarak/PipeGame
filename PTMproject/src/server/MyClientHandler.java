package server;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyClientHandler implements ClientHandler {

	Solver<ArrayList<String>> solver;
	CacheManager<ArrayList<String>> cm;
	/*---------------Constructors---------------*/
	public MyClientHandler() {
		this.solver=new MySolver();
		this.cm=new MyCacheManager();
	}
	/*------------------------------------------*/

	public void outToClient(PrintWriter out,ArrayList<String> solution) { //write solution to client
		for(String s: solution) { 
			out.write(s+"\r\n");
			out.flush();
		}
		out.write("done");
		out.flush();
	}

	@Override
	public void handleClient(ArrayList<String> board, OutputStream outToClient) {
		PrintWriter out=new PrintWriter(outToClient); //write to Client
		ArrayList<String> solForClient=new ArrayList<String>(); //Solution for the client's problem
		cm.readCM(); //load hash map from file, read every time handle client called. maybe should be run only once.

		solForClient=cm.getSolutioncm(board); //get solution from CM

		if(solForClient!=null) { //if solution exist
			outToClient(out,solForClient);
		}
		else { //if there's no solution in CM, ask solver for a solution
			Solver<ArrayList<String>> solver=new MySolver(board);
			solForClient=solver.solve(board); 
			cm.addSolution(solForClient, board); // add solution's problem to CM

			outToClient(out,solForClient);
		}
	}
}


