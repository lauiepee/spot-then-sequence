import java.io.*;
import java.net.*;

public class client_pres1{
	public static void main(String[] args) throws Exception{
		Socket sock = new Socket("192.168.0.106", 8888);
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = sock.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		String receiveMessage, sendMessage;

		//System.out.println("Start the conversation, type and press Enter key");

		while(true){

			if((receiveMessage = receiveRead.readLine()) != null){
				System.out.println(receiveMessage);
			}

			sendMessage = keyRead.readLine();
			pwrite.println(sendMessage);
			pwrite.flush();

			if((receiveMessage = receiveRead.readLine()) != null){
				System.out.println(receiveMessage);
			}

			sendMessage = keyRead.readLine();
			pwrite.println(sendMessage);
			pwrite.flush();
		}
	}
}