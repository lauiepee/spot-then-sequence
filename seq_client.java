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
    
    private static final long serialVersionUID = 1L;

    String playermatch = "";
    
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    
    String[] symbols = {"a","b","c","d","e","f","g","h","i","j"};
    String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};
    String[] players = new String[10];
    
    JPanel panel = new JPanel();
    JPanel panel_1 = new JPanel();
    JLabel topsymbol = new JLabel("");
    JLabel leftsymbol = new JLabel("");
    JLabel bottomsymbol = new JLabel("");
    JLabel rightsymbol = new JLabel("");
    JLabel cardserver = new JLabel("");
    JPanel contentPanel = new JPanel();
    JTable table = new JTable();
    JTextField textField_1 = new JTextField(40);
    
    JLabel spotitcard = new JLabel("");
    JTextArea messageArea_1 = new JTextArea(8, 30);
    Calendar now = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    String color;
    String playername;
    
    int num_server;
    int num2, i, j;

    boolean ctr = true;

    private String name;
    
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
        contentPanel.setBounds(0, 0, 1229, 710);
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);

        
        table.setBounds(6, 6, 727, 700);
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
        textField_1.setBounds(739, 6, 260, 30);
        textField_1.setColumns(10);
        
        // Layout GUI
        textField_1.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(739, 40, 260, 185);
        
        scrollPane.setViewportView(messageArea_1);
        messageArea_1.setEditable(false);
        contentPanel.setLayout(null);
        contentPanel.add(table);
        contentPanel.add(textField_1);
        contentPanel.add(scrollPane);
        
        spotitlabels();
        

        panel_1.setBounds(1004, 6, 218, 219);
        contentPanel.add(panel_1);
        
        cardserver.setBounds(0, 5, 141, 216);
        panel_1.add(cardserver);
                        
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

    // For Sequence
    private void hasWinner(JTable table){
        int count=0;
           //vertical
            for (int col=0;col<10;col++){
                for (int row=0; row<6;row++){
                    if( (table.getModel().getValueAt(row,col) != null) && (table.getModel().getValueAt(row,col) == table.getModel().getValueAt(row+1,col)) ){
                        count++;
                    }

                    if(count == 4){
                        JOptionPane.showMessageDialog(null, "YOU WON THE GAME!");
                        out.println("WINNER!");
                        //socket.close();
                    }
                }
            }

            //horizontal
            for (int row=0; row<10; row++){
                for (int col=0; col<6; col++){
                    if( (table.getModel().getValueAt(row,col) != null) && (table.getModel().getValueAt(row,col) == table.getModel().getValueAt(row,col+1)) ){
                        count++;
                    }

                    if(count == 4){
                        JOptionPane.showMessageDialog(null, "YOU WON THE GAME!");
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
        @SuppressWarnings("resource")
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        // Process all messages from server, according to the protocol.
        while (true) {
            
            String line = in.readLine();

            if (line.startsWith("SUBMITNAME")) {
                this.name = getName();
                out.println(name);
                System.out.println(name);
            } 
            
            else if (line.startsWith("NAMEACCEPTED")) {
                textField_1.setEditable(true);
                JOptionPane.showMessageDialog(frame, "Welcome! Please wait for others to join the conversation...");
            
            }
            
            else if (line.startsWith("PLAYERS")){
                String temp = line.substring(8);
                
                i = 0;
                temp = temp.replaceAll("\\[|\\]", ""); 
                temp = temp.replaceAll(",", "");
                
                StringTokenizer st = new StringTokenizer(temp);
                while (st.hasMoreTokens()) {
                    players[i] = st.nextToken();
                    i++;
                }
                
            }
            
            else if (line.startsWith("MESSAGE")) {
                messageArea_1.append(line.substring(8) + '\n');
            } 
            
            else if (line.startsWith("SUBMITCOLOR")) {
                color = getColor();
            } 
            
            else if (line.startsWith("SERVER")) {
                num_server = Integer.parseInt(line.substring(7));
                num2 = num_server;
                
                
                Image img2 = new ImageIcon(this.getClass().getResource(images[num_server])).getImage();
                cardserver.setIcon(new ImageIcon(img2));
                
            } 
            
            else if (line.startsWith("CORRECT")) {
                messageArea_1.append("---------------\n" + line.substring(8) + " gets the correct answer. \n-----------------\n");
                playername = line.substring(8);
                
                ctr = true;
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                     public void mousePressed(java.awt.event.MouseEvent evt) {
                            if(ctr == true && name.equals(playername)){
                                int row = table.rowAtPoint(evt.getPoint());
                                int col = table.columnAtPoint(evt.getPoint());
                                if (row >= 0 && col >= 0) {
                                    //out.println("COLOR" + row + "," + col + "," + color);
                                    table.setValueAt(color,row,col);
                                    hasWinner(table);
                                }
                                ctr = false;

                            }
                     }
                    });
                
                out.println("NEXT");
                
            } 
            
            else if(line.startsWith("COLOR")){
                int row = Integer.parseInt(line.substring(5,6));
                int column = Integer.parseInt(line.substring(7,8));
                String curr_color = line.substring(9);
                
                table.setValueAt(curr_color, row, column);
                hasWinner(table);
            }
            
            i++;
        }
    }
    
    private void matching(String playermatch, int num, int num2){
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
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
                        out.println("HMM");
                        JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
                        break;
                }
        }
    }
    
    private void topsymcheck(int num){
        switch (num){
            case 0:
                out.println("TREE");
                playermatch = "TREE";
                matching(playermatch, num, num2);
                break;
            case 1:
                out.println("BALLOON");
                playermatch = "BALLOON";
                matching(playermatch, num, num2);
                break;
            case 2:
                out.println("HAND");
                playermatch = "HAND";
                matching(playermatch, num, num2);
                break;
            case 3:
                out.println("CANDLE");
                playermatch = "CANDLE";
                matching(playermatch, num, num2);
                break;
            case 4:
                out.println("TARGET");
                playermatch = "TARGET";
                matching(playermatch, num, num2);
                break;
            case 5:
                out.println("ICE");
                playermatch = "ICE";
                matching(playermatch, num, num2);
                break;
            case 6:
                out.println("BOMB");
                playermatch = "BOMB";
                matching(playermatch, num, num2);
                break;
            case 7:
                out.println("LIPS");
                playermatch = "LIPS";
                matching(playermatch, num, num2);
                break;
            case 8:
                out.println("DOLPHIN");
                playermatch = "DOLPHIN";
                matching(playermatch, num, num2);
                break;
            case 9:
                out.println("SHADES");
                playermatch = "SHADES";
                matching(playermatch, num, num2);
                break;
            case 10:
                out.println("BULB");
                playermatch = "BULB";
                matching(playermatch, num, num2);
                break;
            case 11:
                out.println("YINYANG");
                playermatch = "YINYANG";
                matching(playermatch, num, num2);
                break;
            case 12:
                out.println("TARGET");
                playermatch = "TARGET";
                matching(playermatch, num, num2);
                break;
        } 
    }
    
    private void leftsymcheck(int num){
        switch (num){
            case 0:
                out.println("ICE");
                playermatch = "ICE";
                matching(playermatch, num, num2);
                break;
            case 1:
                out.println("YINYANG");
                playermatch = "YINYANG";
                matching(playermatch, num, num2);
                break;
            case 2:
                out.println("TREE");
                playermatch = "TREE";
                matching(playermatch, num, num2);
                break;
            case 3:
                out.println("LIPS");
                playermatch = "LIPS";
                matching(playermatch, num, num2);
                break;
            case 4:
                out.println("CHEESE");
                playermatch = "CHEESE";
                matching(playermatch, num, num2);
                break;
            case 5:
                out.println("HAND");
                playermatch = "HAND";
                matching(playermatch, num, num2);
                break;
            case 6:
                out.println("SHADES");
                playermatch = "SHADES";
                matching(playermatch, num, num2);
                break;
            case 7:
                out.println("DOLPHIN");
                playermatch = "DOLPHIN";
                matching(playermatch, num, num2);
                break;
            case 8:
                out.println("BOMB");
                playermatch = "BOMB";
                matching(playermatch, num, num2);
                break;
            case 9:
                out.println("CANDLE");
                playermatch = "CANDLE";
                matching(playermatch, num, num2);
                break;
            case 10:
                out.println("BALLOON");
                playermatch = "BALLOON";
                matching(playermatch, num, num2);
                break;
            case 11:
                out.println("CHEESE");
                playermatch = "CHEESE";
                matching(playermatch, num, num2);
                break;
            case 12:
                out.println("BULB");
                playermatch = "BULB";
                matching(playermatch, num, num2);
                break;
        }
    }
    
    private void bottomsymcheck(int num){
        switch (num){
            case 0:
                out.println("BULB");
                playermatch = "BULB";
                matching(playermatch, num, num2);
                break;
            case 1:
                out.println("TREE");
                playermatch = "TREE";
                matching(playermatch, num, num2);
                break;
            case 2:
                out.println("BOMB");
                playermatch = "BOMB";
                matching(playermatch, num, num2);
                break;
            case 3:
                out.println("TARGET");
                playermatch = "TARGET";
                matching(playermatch, num, num2);
                break;
            case 4:
                out.println("BALLOON");
                playermatch = "BALLOON";
                matching(playermatch, num, num2);
                break;
            case 5:
                out.println("CANDLE");
                playermatch = "CANDLE";
                matching(playermatch, num, num2);
                break;
            case 6:
                out.println("ICE");
                playermatch = "ICE";
                matching(playermatch, num, num2);
                break;
            case 7:
                out.println("HAND");
                playermatch = "HAND";
                matching(playermatch, num, num2);
                break;
            case 8:
                out.println("YINYANG");
                playermatch = "YINYANG";
                matching(playermatch, num, num2);
                break;
            case 9:
                out.println("DOLPHIN");
                playermatch = "DOLPHIN";
                matching(playermatch, num, num2);
                break;
            case 10:
                out.println("CANDLE");
                playermatch = "CANDLE";
                matching(playermatch, num, num2);
                break;
            case 11:
                out.println("LIPS");
                playermatch = "LIPS";
                matching(playermatch, num, num2);
                break;
            case 12:
                out.println("SHADES");
                playermatch = "SHADES";
                matching(playermatch, num, num2);
                break;
        }
    }
    
    private void rightsymcheck(int num){
        switch (num){
            case 0:
                out.println("DOLPHIN");
                playermatch = "DOLPHIN";
                matching(playermatch, num, num2);
                break;
            case 1:
                out.println("SHADES");
                playermatch = "SHADES";
                matching(playermatch, num, num2);
                break;
            case 2:
                out.println("CHEESE");
                playermatch = "CHEESE";
                matching(playermatch, num, num2);
                break;
            case 3:
                out.println("TREE");
                playermatch = "TREE";
                matching(playermatch, num, num2);
                break;
            case 4:
                out.println("ICE");
                playermatch = "ICE";
                matching(playermatch, num, num2);
                break;
            case 5:
                out.println("YINYANG");
                playermatch = "YINYANG";
                matching(playermatch, num, num2);
                break;
            case 6:
                out.println("LIPS");
                playermatch = "LIPS";
                matching(playermatch, num, num2);
                break;
            case 7:
                out.println("BALLOON");
                playermatch = "BALLOON";
                matching(playermatch, num, num2);
                break;
            case 8:
                out.println("TARGET");
                playermatch = "TARGET";
                matching(playermatch, num, num2);
                break;
            case 9:
                out.println("CHEESE");
                playermatch = "CHEESE";
                matching(playermatch, num, num2);
                break;
            case 10:
                out.println("BOMB");
                playermatch = "BOMB";
                matching(playermatch, num, num2);
                break;
            case 11:
                out.println("BULB");
                playermatch = "BULB";
                matching(playermatch, num, num2);
                break;
            case 12:
                out.println("HAND");
                playermatch = "HAND";
                matching(playermatch, num, num2);
                break;
        }
    }
    
    private void spotitlabels(){
        Random rand = new Random();
        int num = rand.nextInt(images.length-1) + 1;
        
        System.out.println("PASSED NUM: " + num);
        
        // For the spot it card
        panel.setBounds(739, 231, 483, 475);
        contentPanel.add(panel);
        panel.setLayout(null);
        Image img = new ImageIcon(this.getClass().getResource(images[num])).getImage();
        
        topsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                topsymcheck(num);
            }
        });
        topsymbol.setBounds(177, 141, 78, 74);
        panel.add(topsymbol);
        
        leftsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                leftsymcheck(num);                              
            }
        });
        leftsymbol.setBounds(128, 204, 69, 74);
        panel.add(leftsymbol);
        
        
        bottomsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bottomsymcheck(num);
            }
        });
        bottomsymbol.setBounds(177, 265, 78, 63);
        panel.add(bottomsymbol);
        
        rightsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rightsymcheck(num);
            }
        });
        rightsymbol.setBounds(232, 204, 78, 63);
        panel.add(rightsymbol);
        
        spotitcard.setBounds(119, 141, 199, 197);
        panel.add(spotitcard);
        spotitcard.setIcon(new ImageIcon(img));
        // End of of for spot it card     
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
