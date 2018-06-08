package server;

import java.io.OutputStream;
import java.util.ArrayList;

public interface ClientHandler {
	void handleClient(ArrayList<String> inFromClient,OutputStream outToClient);
}
