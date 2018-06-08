package server;

import java.io.IOException;

public interface Server {

	public void start(ClientHandler ch);
	public void runServer(ClientHandler ch) throws IOException;
	public void stop();
}
