import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A multithreaded chat room server.  When a client connects the
 * server requests a screen name by sending the client the
 * text "SUBMITNAME", and keeps requesting a name until
 * a unique one is received.  After a client submits a unique
 * name, the server acknowledges with "NAMEACCEPTED".  Then
 * all messages from that client will be broadcast to all other
 * clients that have submitted a unique screen name.  The
 * broadcast messages are prefixed with "MESSAGE ".
 *
 * Because this is just a teaching example to illustrate a simple
 * chat server, there are a few features that have been left out.
 * Two are very useful and belong in production code:
 *
 *     1. The protocol should be enhanced so that the client can
 *        send clean disconnect messages to the server.
 *
 *     2. The server should do some logging.
 */
public class seq_server {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 9001;

    /**
     * The set of all names of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<String> colors = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    private static String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};

    private static int num2;
    
    private static JTable table = new JTable();
    /**
     * The appplication main method, which just listens on a port and
     * spawns handler threads.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        Random rand2 = new Random();
        num2 = rand2.nextInt(images.length-1) + 1;
        System.out.println("FROM SERVER: " + num2);
        
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name,color;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a
         * screen name until a unique one has been submitted, then
         * acknowledges the name and registers the output stream for
         * the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
        	table.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                    }, new String[] { null, null, null, null, null, null, null, null, null, null}
                    ));
        	
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                    	
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

                 while (true) {
                    out.println("SUBMITCOLOR");
                    color = in.readLine();
                    if (color == null) {
                        return;
                    }
                    synchronized (colors) {
                        if (!colors.contains(color)) {
                            colors.add(color);
                            break;
                        }
                    }
                }

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                //out.println("NAMEACCEPTED");
                writers.add(out);

                for (PrintWriter writer: writers){
            		writer.println("MESSAGE " + name + " has joined the game using color " + color + ".\n");
            	}

                out.println("SERVER " + num2);
                
                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while (true) {
                    String input = in.readLine();
                    
                    if (input == null) {
                        return;
                    }

                    Random rand2 = new Random();
                    num2 = rand2.nextInt(images.length-1) + 1;
                    
                    for (PrintWriter writer : writers) {
                    	if (input.startsWith("CORRECT")){
                            writer.println("CORRECT " + name);
                        }
                                                
                        else if (input.startsWith("NEXT") || input.startsWith("SAME")){
                            writer.println("SERVER " + num2);
                        }
                    	
                        else if (input.startsWith("COLOR")){
                        	int row = Integer.parseInt(input.substring(5,6));
                        	int col = Integer.parseInt(input.substring(7,8));
                        	String curr_color = input.substring(9);
                        	
	                        writer.println("COLOR" + row + "," + col + "," + curr_color);
                        }
                        
                        else if (input.startsWith("WINNER")){
                        	writer.println("WINNER");
                        }
                        
                        else{
                            Calendar now = Calendar.getInstance();
                            SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                            //writer.println("MESSAGE " + name + ": " + input + "\t" + formatter.format(now.getTime()));
                            writer.println("MESSAGE " + formatter.format(now.getTime()));
                        } 
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                	for (PrintWriter writer : writers) {
                		writer.println("MESSAGE " + name + " has left the game.");
                	}
                    names.remove(name);
                    
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
