import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.*;
import java.text.SimpleDateFormat;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * A simple Swing-based client for the chat server.  Graphically
 * it is a frame with a text field for entering messages and a
 * textarea to see the whole dialog.
 *
 * The client follows the Chat Protocol which is as follows.
 * When the server sends "SUBMITNAME" the client replies with the
 * desired screen name.  The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are
 * already in use.  When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all
 * chatters connected to the server.  When the server sends a
 * line beginning with "MESSAGE " then all characters following
 * this string should be displayed in its message area.
 */
public class seq_client extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String playermatch = "";
	
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    
    String[] symbols = {"a","b","c","d","e","f","g","h","i","j"};
    String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};
	
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField textField_1 = new JTextField(40);
    JTextArea messageArea_1 = new JTextArea(8, 40);
    Calendar now = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    String color;
    int num2;
    
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public seq_client() {
    	setTitle("Spot Then Sequence");
        setBounds(100, 100, 1251, 766);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 1242, 710);
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);

        Random rand = new Random();
		int num = rand.nextInt(images.length-1) + 1;

        table = new JTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    out.println(row+","+col);
                    table.setValueAt(color,row,col);
                }
                hasWinner(table);
             }
            });
        table.setBounds(6, 6, 727, 700);
        table.setEnabled(false);
        table.setToolTipText("null\n");
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setForeground(Color.WHITE);
        table.setBorder(new LineBorder(new Color(0, 255, 255), 3));
        table.setBackground(Color.DARK_GRAY);
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
        table.setRowHeight(70);
                
                textField_1 = new JTextField();
                textField_1.setBounds(739, 10, 260, 30);
                textField_1.setColumns(10);
                
                        // Layout GUI
                        textField_1.setEditable(false);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(739, 44, 260, 181);
                        
                        messageArea_1 = new JTextArea();
                        scrollPane.setViewportView(messageArea_1);
                        messageArea_1.setEditable(false);
                        contentPanel.setLayout(null);
                        contentPanel.add(table);
                        contentPanel.add(textField_1);
                        contentPanel.add(scrollPane);
                        

                        
                        
                        JPanel panel = new JPanel();
                        panel.setBounds(739, 231, 483, 475);
                        contentPanel.add(panel);
                        panel.setLayout(null);
                        Image img = new ImageIcon(this.getClass().getResource(images[num])).getImage();
                        
                        JLabel topsymbol = new JLabel("");
                        topsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent arg0) {

                				switch (num){
                					case 0:
                						playermatch = "TREE";
                						matching(playermatch, num, num2);
                						break;
                					case 1:
                						playermatch = "BALLOON";
                						matching(playermatch, num, num2);
                						break;
                					case 2:
                						playermatch = "HAND";
                						matching(playermatch, num, num2);
                						break;
                					case 3:
                						playermatch = "CANDLE";
                						matching(playermatch, num, num2);
                						break;
                					case 4:
                						playermatch = "TARGET";
                						matching(playermatch, num, num2);
                						break;
                					case 5:
                						playermatch = "ICE";
                						matching(playermatch, num, num2);
                						break;
                					case 6:
                						playermatch = "BOMB";
                						matching(playermatch, num, num2);
                						break;
                					case 7:
                						playermatch = "LIPS";
                						matching(playermatch, num, num2);
                						break;
                					case 8:
                						playermatch = "DOLPHIN";
                						matching(playermatch, num, num2);
                						break;
                					case 9:
                						playermatch = "SHADES";
                						matching(playermatch, num, num2);
                						break;
                					case 10:
                						playermatch = "BULB";
                						matching(playermatch, num, num2);
                						break;
                					case 11:
                						playermatch = "YINYANG";
                						matching(playermatch, num, num2);
                						break;
                					case 12:
                						playermatch = "TARGET";
                						matching(playermatch, num, num2);
                						break;
                				}                        		
                        	}
                        });
                        topsymbol.setBounds(177, 141, 78, 74);
                        panel.add(topsymbol);
                        
                        JLabel leftsymbol = new JLabel("");
                        leftsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                				
                				switch (num){
                				case 0:
                					playermatch = "ICE";
                					matching(playermatch, num, num2);
                					break;
                				case 1:
                					playermatch = "YINYANG";
                					matching(playermatch, num, num2);
                					break;
                				case 2:
                					playermatch = "TREE";
                					matching(playermatch, num, num2);
                					break;
                				case 3:
                					playermatch = "LIPS";
                					matching(playermatch, num, num2);
                					break;
                				case 4:
                					playermatch = "CHEESE";
                					matching(playermatch, num, num2);
                					break;
                				case 5:
                					playermatch = "HAND";
                					matching(playermatch, num, num2);
                					break;
                				case 6:
                					playermatch = "SHADES";
                					matching(playermatch, num, num2);
                					break;
                				case 7:
                					playermatch = "DOLPHIN";
                					matching(playermatch, num, num2);
                					break;
                				case 8:
                					playermatch = "BOMB";
                					matching(playermatch, num, num2);
                					break;
                				case 9:
                					playermatch = "CANDLE";
                					matching(playermatch, num, num2);
                					break;
                				case 10:
                					playermatch = "BALLOON";
                					matching(playermatch, num, num2);
                					break;
                				case 11:
                					playermatch = "CHEESE";
                					matching(playermatch, num, num2);
                					break;
                				case 12:
                					playermatch = "BULB";
                					matching(playermatch, num, num2);
                					break;
                			}
                        		
                        	}
                        });
                        leftsymbol.setBounds(128, 204, 69, 74);
                        panel.add(leftsymbol);
                        
                        JLabel bottomsymbol = new JLabel("");
                        bottomsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                				
                				switch (num){
                				case 0:
                					playermatch = "BULB";
                					matching(playermatch, num, num2);
                					break;
                				case 1:
                					playermatch = "TREE";
                					matching(playermatch, num, num2);
                					break;
                				case 2:
                					playermatch = "BOMB";
                					matching(playermatch, num, num2);
                					break;
                				case 3:
                					playermatch = "TARGET";
                					matching(playermatch, num, num2);
                					break;
                				case 4:
                					playermatch = "BALLOON";
                					matching(playermatch, num, num2);
                					break;
                				case 5:
                					playermatch = "CANDLE";
                					matching(playermatch, num, num2);
                					break;
                				case 6:
                					playermatch = "ICE";
                					matching(playermatch, num, num2);
                					break;
                				case 7:
                					playermatch = "HAND";
                					matching(playermatch, num, num2);
                					break;
                				case 8:
                					playermatch = "YINYANG";
                					matching(playermatch, num, num2);
                					break;
                				case 9:
                					playermatch = "DOLPHIN";
                					matching(playermatch, num, num2);
                					break;
                				case 10:
                					playermatch = "CANDLE";
                					matching(playermatch, num, num2);
                					break;
                				case 11:
                					playermatch = "LIPS";
                					matching(playermatch, num, num2);
                					break;
                				case 12:
                					playermatch = "SHADES";
                					matching(playermatch, num, num2);
                					break;
                				}
                        	}
                        });
                        bottomsymbol.setBounds(177, 265, 78, 63);
                        panel.add(bottomsymbol);
                        
                        JLabel rightsymbol = new JLabel("");
                        rightsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                				
                				switch (num){
                				case 0:
                					playermatch = "DOLPHIN";
                					matching(playermatch, num, num2);
                					break;
                				case 1:
                					playermatch = "SHADES";
                					matching(playermatch, num, num2);
                					break;
                				case 2:
                					playermatch = "CHEESE";
                					matching(playermatch, num, num2);
                					break;
                				case 3:
                					playermatch = "TREE";
                					matching(playermatch, num, num2);
                					break;
                				case 4:
                					playermatch = "ICE";
                					matching(playermatch, num, num2);
                					break;
                				case 5:
                					playermatch = "YINYANG";
                					matching(playermatch, num, num2);
                					break;
                				case 6:
                					playermatch = "LIPS";
                					matching(playermatch, num, num2);
                					break;
                				case 7:
                					playermatch = "BALLOON";
                					matching(playermatch, num, num2);
                					break;
                				case 8:
                					playermatch = "TARGET";
                					matching(playermatch, num, num2);
                					break;
                				case 9:
                					playermatch = "CHEESE";
                					matching(playermatch, num, num2);
                					break;
                				case 10:
                					playermatch = "BOMB";
                					matching(playermatch, num, num2);
                					break;
                				case 11:
                					playermatch = "BULB";
                					matching(playermatch, num, num2);
                					break;
                				case 12:
                					playermatch = "HAND";
                					matching(playermatch, num, num2);
                					break;
                			}
                        	}
                        });
                        rightsymbol.setBounds(232, 204, 78, 63);
                        panel.add(rightsymbol);
                        
                        JLabel spotitcard = new JLabel("");
                        spotitcard.setBounds(119, 141, 199, 197);
                        panel.add(spotitcard);
                        spotitcard.setIcon(new ImageIcon(img));
                        
                        
        // Add Listeners
        textField_1.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                out.println(textField_1.getText());
                textField_1.setText("");
            }


        });
        
        textField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.equals('\n')) {
                    out.println(textField_1.getText());
                    textField_1.setText("");
                }
            }
        });
    }

    private void hasWinner(JTable table){
        int count;
           //vertical
            for (int col=0;col<10;col++){
                count = 0;
                for (int row=0; row<6;row++){
                    if( (table.getModel().getValueAt(row,col) != null) && (table.getModel().getValueAt(row,col) == table.getModel().getValueAt(row+1,col)) ){
                        count++;
                    }else{
                        count=1;
                    }

                    if(count>=5){
                        out.println("WINNER!");
                        //socket.close();
                    }
                }
            }

            //horizontal
            for (int row=0; row<10; row++){
                count=0;
                for (int col=0; col<6; col++){
                    if( (table.getModel().getValueAt(row,col) != null) && (table.getModel().getValueAt(row,col) == table.getModel().getValueAt(row,col+1)) ){
                        count++;
                    }else{
                        count=1;
                    }

                    if(count>=5){
                        out.println("WINNER!");
                        //socket.close();
                    }
                }
            }

            //diagonal

    } 

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    public String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }

    private String getCardSymbol() {
        Random r = new Random();
        int rand_num_client = r.nextInt(((symbols.length-1) - 0) + 1) + 0;

        return symbols[rand_num_client];
    }

    public String getColor() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a color: \n (blue, pink, red, green, white, orange)",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField_1.setEditable(true);
                JOptionPane.showMessageDialog(frame, "Welcome! Please wait for others to join the conversation...");
            } else if (line.startsWith("SYMBOLS")) {
                out.println(getCardSymbol());
                JOptionPane.showMessageDialog(frame, getCardSymbol());
                messageArea_1.append(line + '\n');
            } else if (line.startsWith("MESSAGE")) {
                messageArea_1.append(line.substring(8) + '\n');
            } else if (line.startsWith("SUBMITCOLOR")) {
                color = getColor();
            } else if (line.startsWith("SERVER")) {
                num2 = Integer.parseInt(line.substring(7));
                System.out.println("PAGPASA SA CLIENT: " + num2);
                
                JPanel panel_1 = new JPanel();
                panel_1.setBounds(1004, 4, 229, 221);
                contentPanel.add(panel_1);
        
                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setBounds(0, 5, 141, 216);
                panel_1.add(lblNewLabel);
                System.out.println("CHECK NATIN ANG VALUE NG NUM2: " + num2);
                Image img2 = new ImageIcon(this.getClass().getResource(images[num2])).getImage();
                lblNewLabel.setIcon(new ImageIcon(img2));
            } else if (line.startsWith("CORRECT")) {
                messageArea_1.append(line.substring(8) + '\n');
            }
        }
    }
    
    private void matching(String playermatch, int num, int num2){
    	Random rand = new Random();
		int num3 = rand.nextInt(images.length-1) + 1;
		if(num2 == 0){
				switch(num){
					case 1:
					case 2:
					case 3:
						if(playermatch == "TREE"){
    						out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 4:
					case 5:
					case 6:
						if(playermatch == "ICE"){
    						out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 7:
					case 8:
					case 9:
						if(playermatch == "DOLPHIN"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 10:
					case 11:
					case 12:
						if(playermatch == "BULB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 1){
				switch(num){
					case 0:
					case 2:
					case 3:
						if(playermatch == "TREE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 4:
					case 7:
					case 10:
						if(playermatch == "BALLOON"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 5:
					case 8:
					case 11:
						if(playermatch == "YINYANG"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 6:
					case 9:
					case 12:
						if(playermatch == "SHADES"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 2){
				switch(num){
					case 0:
					case 1:
					case 3:
						if(playermatch == "TREE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 4:
					case 9:
					case 11:
						if(playermatch == "CHEESE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 5:
					case 7:
					case 12:
						if(playermatch == "HAND"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 6:
					case 8:
					case 10:
						if(playermatch == "BOMB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 3){
				switch(num){
					case 0:
					case 1:
					case 3:
						if(playermatch == "TREE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 4:
					case 8:
					case 12:
						if(playermatch == "TARGET"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 5:
					case 9:
					case 10:
						if(playermatch == "CANDLE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 6:
					case 7:
					case 11:
						if(playermatch == "LIPS"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 4){
				switch(num){
					case 0:
					case 5:
					case 6:
						if(playermatch == "ICE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 7:
					case 10:
						if(playermatch == "BALLOON"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 9:
					case 11:
						if(playermatch == "CHEESE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 8:
					case 12:
						if(playermatch == "TARGET"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 5){
				switch(num){
					case 0:
					case 4:
					case 6:
						if(playermatch == "ICE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 8:
					case 11:
						if(playermatch == "YINYANG"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 7:
					case 12:
						if(playermatch == "HAND"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 9:
					case 10:
						if(playermatch == "CANDLE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 6){
				switch(num){
					case 0:
					case 4:
					case 5:
						if(playermatch == "ICE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 8:
					case 10:
						if(playermatch == "BOMB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 7:
					case 11:
						if(playermatch == "LIPS"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 9:
					case 12:
						if(playermatch == "SHADES"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if (num2 == 7){
				switch(num){
					case 3:
					case 6:
					case 11:
						if(playermatch == "LIPS"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 0:
					case 8:
					case 9:
						if(playermatch == "DOLPHIN"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 4:
					case 10:
						if(playermatch == "BALLOON"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 5:
					case 12:
						if(playermatch == "HAND"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 8){
				switch(num){
					case 0:
					case 7:
					case 9:
						if(playermatch == "DOLPHIN"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 6:
					case 10:
						if(playermatch == "BOMB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 4:
					case 12 :
						if(playermatch == "TARGET"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 5:
					case 11:
						if(playermatch == "YINYANG"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 9){
				switch(num){
					case 1:
					case 6:
					case 12:
						if(playermatch == "SHADES"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 5:
					case 10:
						if(playermatch == "CANDLE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 4:
					case 11 :
						if(playermatch == "CHEESE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 0:
					case 7:
					case 8:
						if(playermatch == "DOLPHIN"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		}else if(num2 == 10){
				switch(num){
					case 0:
					case 10:
					case 11:
						if(playermatch == "BULB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 4:
					case 7:
						if(playermatch == "BALLOON"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 6:
					case 8 :
						if(playermatch == "BOMB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 5:
					case 9:
						if(playermatch == "CANDLE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 11){
				switch(num){
					case 1:
					case 5:
					case 8:
						if(playermatch == "YINYANG"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 4:
					case 9:
						if(playermatch == "CHEESE"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 0:
					case 10:
					case 12 :
						if(playermatch == "BULB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 3:
					case 6:
					case 7:
						if(playermatch == "LIPS"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 12){
				switch(num){
					case 3:
					case 4:
					case 8:
						if(playermatch == "TARGET"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 0:
					case 10:
					case 11:
						if(playermatch == "BULB"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 2:
					case 5:
					case 7 :
						if(playermatch == "HAND"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					case 1:
					case 6:
					case 9:
						if(playermatch == "SHADES"){
							out.println("CORRECT");
							JOptionPane.showMessageDialog(null, "CORRECT");
						}
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		}
	}

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        try {
            seq_client dialog = new seq_client();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
