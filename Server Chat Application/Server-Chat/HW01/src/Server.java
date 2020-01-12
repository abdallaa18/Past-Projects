// GOALS
//
// 1. to show sample Server code
// Note that the server is running on LOCALHOST (which is THIS computer) and the 
// port number associated with this server program is 4444.
// The accept() method just WAITS until some client program tries to access this server
//
// 2. to show how a thread is created to handle client request
//

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null; 
		int clientNum = 0; 

	
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
		
			System.exit(-1);
		}

		while (true) { // 3.
			Socket clientSocket = null;
			try {

			
				System.out.println("Waiting for client "
						+ " to connect!");
				clientSocket = serverSocket.accept(); // // 4.
				ClientHandler client = new ClientHandler(clientSocket, clientNum+1);
				clients.add(client);
				Thread thread = new Thread(client);
				thread.start();
			
				System.out.println("Server got connected to a client");
				
				

			} catch (IOException e) {
				System.exit(-1);
			}

		

		} // end while loop

	} // end of main method

} // end of class MyServer

class ClientHandler extends Server implements Runnable {
	Socket s; // this is socket on the server side that connects to the CLIENT
	int num; // keeps track of its number just for identifying purposes
	Scanner in;
	PrintWriter out;
	String string = " ";

	ClientHandler(Socket s, int n) {
		this.s = s;
		num = n;
	}


	public void run() { 
	
		try {
	
			in = new Scanner(new BufferedInputStream(s.getInputStream())); 
			out = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
	
		while(true) { 
			 string = in.nextLine();
			 handleRequest(string);
			 for(ClientHandler c : clients) {
				c.getWriter().println(string);
				c.getWriter().flush();
			 }
		}
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	} 
	
	void handleRequest(String s) {
		System.out.println("(server side) " + s);
	}
	public PrintWriter getWriter() {
		return out;
	}

}
