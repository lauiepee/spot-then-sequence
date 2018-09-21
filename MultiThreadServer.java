import java.io.*;
import java.net.*;

public class MultiThreadServer implements Runnable{
	Socket csocket;
	
	MultiThreadServer(Socket csocket) {
		this.csocket = csocket;
	}

	public static void main(String[] args) throws Exception{

		ServerSocket ssock = new ServerSocket(3000);
		System.out.println("Listening");

		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected");
			new Thread(new MultiThreadServer(sock)).start();
		}
	}

	public void run(){


		//ServerSocket sersock = new ServerSocket(3000);
		//Socket sock = sersock.accept( );

		
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = csocket.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);
		InputStream istream = csocket.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		
		pwrite.println("Do you want to join the game?");
		pwrite.flush();

		String receiveMessage, sendMessage;
		String answer = "", playername = "";

		while(true){
			
			if((receiveMessage = receiveRead.readLine()) != null && (receiveMessage.equals("yes") || receiveMessage.equals("YES"))){
				pwrite.println("Enter Player Name: ");
				pwrite.flush();
			}
			else if((receiveMessage = receiveRead.readLine()) != null && (receiveMessage.equals("no") || receiveMessage.equals("NO"))){
				answer = "Okay, good bye!";
				pwrite.println(answer);
				pwrite.flush();
			}

			playername = receiveRead.readLine();
			pwrite.println("WELCOME, " + playername + "!!!");
			pwrite.flush();

			System.out.println(playername + " has joined the game.");
		}
	}
}