import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {

	Socket serverSocket;
	ServerListener sl;
	Scanner nameScanner = new Scanner(System.in);
	String name = "";
	Scanner messageScanner = new Scanner(System.in);
	String message = " ";

	MyClient() {
		System.out.println("Enter your Name: (After giving input “your name”, need to press Enter)");
		name = nameScanner.nextLine();
		try {
			serverSocket = new Socket("localhost", 4444);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		sl = new ServerListener(this, serverSocket);
		new Thread(sl).start();

		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));
			while(true) {
			message = messageScanner.nextLine();
			if(message == "quit"){
				break;
			}
			
			out.println(name + ": " + message); 			
			out.flush();
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		System.exit(-1);

	}

	public void handleMessage(String s) {
	
			System.out.println(s);
			
		
	
	}

	public static void main(String[] args) {
		MyClient lc = new MyClient();
	} 

}

class ServerListener implements Runnable {
	MyClient lc;
	Scanner in; 

	ServerListener(MyClient lc, Socket s) {
		try {
			this.lc = lc;
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) { 
			String s = in.nextLine();
			lc.handleMessage(s);
		}

	}
}
