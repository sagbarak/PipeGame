package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MyServer implements Server{

	private int port;
	private ClientHandler ch;
	private boolean stop;
	private int numberOfClients=2;
	private PriorityBlockingQueue<Runnable> clientsQueue=new PriorityBlockingQueue<Runnable>();
	private ThreadPoolExecutor threadPool=new ThreadPoolExecutor(numberOfClients, numberOfClients, 3, TimeUnit.SECONDS,clientsQueue);

	/*-----------------Constructor---------------------*/
	public MyServer(int port) {
		this.port=port;
		stop=false;
		this.ch=new MyClientHandler();
	}
	/*-------------------------------------------------*/
	
	
	
	@Override
	public void start(ClientHandler ch){
		new Thread(()->{
			try {
				runServer(ch);
			} catch (Exception e) {
				//e.printStackTrace();	
			}
		}).start();
	}

	@Override
	public void runServer(ClientHandler ch) throws IOException {
		ServerSocket server = new ServerSocket(port);

		while(!stop) {
			try {
				Socket aClient = server.accept(); 
				threadPool.execute(new ServerThread(aClient));
			}	
			catch(SocketTimeoutException e) {
				//System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		server.close();
	}

	@Override
	public void stop() {
		this.stop = true;
	}
}



