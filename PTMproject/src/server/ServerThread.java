package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread implements Runnable,Comparable<ServerThread>{
	private Socket client=new Socket();
	private ClientHandler ch=new MyClientHandler();
	private ArrayList<String> boardFromClient;
	
	
	/*-------------------Constructor----------------------*/
	// on creation ServerThread read from client and save game board to data member
	public ServerThread(Socket client) {
		this.client=client;
		this.boardFromClient=new ArrayList<String>();
		
		InputStream inFromClient;
		try {
			inFromClient = this.client.getInputStream();
			this.boardFromClient=readFromClient(inFromClient);
			inFromClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
/*----------------------------------------------------------*/
	public ArrayList<String> getBoard(){
		return this.boardFromClient;
	}
	

	public ArrayList<String> readFromClient(InputStream inFromClient){ //read inputs from client
		ArrayList<String> board=new ArrayList<String>();
		String line;
		BufferedReader in=new BufferedReader(new InputStreamReader(inFromClient)); 
		
		try {
			while((line=in.readLine())!=null && !(line.equals("done"))) { //read problem from client's input (line by line)
				System.out.println(line);
				board.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}
	
	@Override
	public void run() {
		try {
		OutputStream OutToClient=client.getOutputStream();
		ch.handleClient(boardFromClient,OutToClient);		
		OutToClient.close();
		client.close();
		
		}catch(IOException e) {
			//System.out.println(e.getMessage());
		}
	}

	public int compareTo(ServerThread clientB) {
		return ((this.boardFromClient.size()*this.boardFromClient.get(0).length())-clientB.getBoard().size()*
				clientB.getBoard().get(0).length());
	}

}
