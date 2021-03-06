import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Font;

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
    
    String player;
    String playermatch = "";
    int pindot = 0;
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    
    String[] symbols = {"a","b","c","d","e","f","g","h","i","j"};
    String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};
    String[] players = new String[10];
    String[] chips = {"/blue.png", "/green.png", "/pink.png", "/white.png", "/orange.png", "/red.png"};
    
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
    Calendar now = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    String color;
    String playername;
    JLabel a1;
    
    int num_server, num;
    int num2, i, j;

    boolean ctr = true;

    private String name;
    private JTextField textField;
    
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public seq_client() {
        setBounds(100, 100, 1051, 804);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 1229, 759);
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
        
        a1 = new JLabel("");
        a1.setBounds(79, 6, 72, 71);
        contentPanel.add(a1);
        a1.setEnabled(false);
        
        num = randnumclient();
        spotitlabels();
        

        Image img2 = new ImageIcon(this.getClass().getResource(images[0])).getImage();
        cardserver.setIcon(new ImageIcon(img2));
        panel_1.setBounds(739, 6, 285, 344);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);
        
        cardserver.setBounds(43, 71, 201, 198);
        panel_1.add(cardserver);
        
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
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(70);
        contentPanel.add(table);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(6, 709, 1018, 34);
        contentPanel.add(panel_2);
        panel_2.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("Thohir Ke Badreah", Font.PLAIN, 14));
        textField.setDisabledTextColor(Color.BLACK);
        textField.setEnabled(false);
        textField.setEditable(false);
        textField.setBounds(0, 0, 1018, 34);
        panel_2.add(textField);
        textField.setColumns(10);
    }

    // For Sequence
    private void hasWinner(String color, JTable table){
        int vcount = 0, hcount = 0, dcount = 0;
           //vertical
            for (int col = 0; col < 9; col++){
                for (int row = 0; row < 9; row++){
                    if( (table.getValueAt(row,col) != null) && (table.getValueAt(row,col).toString().equals(color))){
                        if (table.getValueAt(row,col).equals(table.getValueAt(row+1,col)))
                            vcount++;
                    }
                }
            }

            //horizontal
            for (int row = 0; row < 9; row++){
                for (int col = 0; col < 9; col++){
                    if( (table.getValueAt(row,col) != null) && (table.getValueAt(row,col).toString().equals(color))){
                        if (table.getValueAt(row,col).equals(table.getValueAt(row,col+1)))
                            hcount++;
                    }
                }
            }

            //diagonal
            for (int row = 0; row < 9; row++){
                for (int col = 0; col < 9; col++){
                    if( (table.getValueAt(row,col) != null) && (table.getValueAt(row,col).toString().equals(color))){
                        if (table.getValueAt(row,col).equals(table.getValueAt(row+1,col+1)))
                            dcount++;
                    }
                }
            }
            
            if(hcount == 4 || vcount == 4 || dcount == 4){
                JOptionPane.showMessageDialog(null, "YOU WON THE GAME!");
                out.println("WINNER!");
            }
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
        player =  JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
        
        return player;
    }


    public String getColor() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a color: \n (RED, GREEN, BLUE, PINK, ORANGE, YELLOW)",
            "Color selection",
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
            }
            
            else if (line.startsWith("NAMEACCEPTED")) {
                textField_1.setEditable(true);
                JOptionPane.showMessageDialog(frame, "Welcome! Please wait for others to join the conversation...");
            }
            
            else if (line.startsWith("MESSAGE")) {
                textField.setText(line.substring(8));
                textField.setBackground(Color.LIGHT_GRAY);
            } 
            
            else if (line.startsWith("SUBMITCOLOR")) {
                this.color = getColor();
                out.println(color);
                setTitle("Spot Then Sequence - Player " + this.name + " (" + this.color + ")");
            } 
            
            else if (line.startsWith("SERVER")) {
                num_server = Integer.parseInt(line.substring(7));
                num2 = num_server;
                
                Image img2 = new ImageIcon(this.getClass().getResource(images[num_server])).getImage();
                cardserver.setIcon(new ImageIcon(img2));
                
                num = randnumclient();
                if(num2 == num){
                	num = randnumclient();
                }
                spotitlabels();
            } 
                        
            else if (line.startsWith("CORRECT")) {
            	textField.setFont(new Font("Thohir Ke Badreah", Font.PLAIN, 14));
                textField.setText(line.substring(8) + " gets the correct answer.\n");
                textField.setBackground(Color.CYAN);
                playername = line.substring(8);
                
                ctr = true;
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                     public void mousePressed(java.awt.event.MouseEvent evt) {
                            if(ctr == true && name.equals(playername)){
                                int row = table.rowAtPoint(evt.getPoint());
                                int col = table.columnAtPoint(evt.getPoint());
                                if (row >= 0 && col >= 0 && table.getValueAt(row, col) == null) {
                            		out.println("COLOR" + row + "," + col + "," + color);
                            		table.setValueAt(color,row,col);
                            		ctr = false;
                                }
                                else{
	                                ctr = true;
                                }
                            }
                     }
                    });
                
                out.println("NEXT");
            } 
            
            else if(line.startsWith("WINNER")){
            	int dialogButton = JOptionPane.showConfirmDialog (null, "Would you like to play another game?","Warning",JOptionPane.YES_NO_OPTION);
            	
            	if(dialogButton == JOptionPane.NO_OPTION){
            		JOptionPane.showMessageDialog(null, "Okay, thank you for playing!");
            		System.exit(0);
            	}
            	else{
                    this.table.setModel(new DefaultTableModel(
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
                    table.setDefaultEditor(Object.class, null);
            		textField.setText("New game!");
            		textField.setBackground(Color.GREEN);
            	}
            }
            
            else if(line.startsWith("COLOR")){
                int row = Integer.parseInt(line.substring(5,6));
                int column = Integer.parseInt(line.substring(7,8));
                String curr_color = line.substring(9);
                
                table.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(4).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(5).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(6).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(7).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(8).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(9).setCellRenderer(new CustomRenderer());

                table.setValueAt(curr_color, row, column);
                hasWinner(color, table);
            }
            
            i++;
        }
        
    }

class CustomRenderer extends DefaultTableCellRenderer 
{
private static final long serialVersionUID = 6703872492730589499L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        value = table.getModel().getValueAt(row, column);

        if(value.toString().equalsIgnoreCase("BLUE")){
            cellComponent.setBackground(Color.BLUE);
            cellComponent.setForeground(Color.BLUE);
        } else if (value.toString().equalsIgnoreCase("RED")){
            cellComponent.setBackground(Color.RED);
            cellComponent.setForeground(Color.RED);
        } else if (value.toString().equalsIgnoreCase("GREEN")){
            cellComponent.setBackground(Color.GREEN);
            cellComponent.setForeground(Color.GREEN);
        } else if (value.toString().equalsIgnoreCase("PINK")){
            cellComponent.setBackground(Color.MAGENTA);
            cellComponent.setForeground(Color.MAGENTA);
        } else if (value.toString().equalsIgnoreCase("ORANGE")){
            cellComponent.setBackground(Color.ORANGE);
            cellComponent.setForeground(Color.ORANGE);
        } else if (value.toString().equalsIgnoreCase("YELLOW")){
            cellComponent.setBackground(Color.YELLOW);
            cellComponent.setForeground(Color.YELLOW);
	    }
        return cellComponent;
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
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                        break;
                    case 4:
                    case 5:
                    case 6:
                        if(playermatch == "ICE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                        break;
                    case 7:
                    case 8:
                    case 9:
                        if(playermatch == "DOLPHIN"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                        break;
                    case 10:
                    case 11:
                    case 12:
                        if(playermatch == "BULB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 1){
                switch(num){
                    case 0:
                    case 2:
                    case 3:
                        if(playermatch == "TREE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 4:
                    case 7:
                    case 10:
                        if(playermatch == "BALLOON"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 5:
                    case 8:
                    case 11:
                        if(playermatch == "YINYANG"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 6:
                    case 9:
                    case 12:
                        if(playermatch == "SHADES"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 2){
                switch(num){
                    case 0:
                    case 1:
                    case 3:
                        if(playermatch == "TREE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 4:
                    case 9:
                    case 11:
                        if(playermatch == "CHEESE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 5:
                    case 7:
                    case 12:
                        if(playermatch == "HAND"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 6:
                    case 8:
                    case 10:
                        if(playermatch == "BOMB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 3){
                switch(num){
                    case 0:
                    case 1:
                    case 3:
                        if(playermatch == "TREE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 4:
                    case 8:
                    case 12:
                        if(playermatch == "TARGET"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 5:
                    case 9:
                    case 10:
                        if(playermatch == "CANDLE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 6:
                    case 7:
                    case 11:
                        if(playermatch == "LIPS"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 4){
                switch(num){
                    case 0:
                    case 5:
                    case 6:
                        if(playermatch == "ICE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 7:
                    case 10:
                        if(playermatch == "BALLOON"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 9:
                    case 11:
                        if(playermatch == "CHEESE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 8:
                    case 12:
                        if(playermatch == "TARGET"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 5){
                switch(num){
                    case 0:
                    case 4:
                    case 6:
                        if(playermatch == "ICE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 8:
                    case 11:
                        if(playermatch == "YINYANG"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 7:
                    case 12:
                        if(playermatch == "HAND"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 9:
                    case 10:
                        if(playermatch == "CANDLE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 6){
                switch(num){
                    case 0:
                    case 4:
                    case 5:
                        if(playermatch == "ICE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 8:
                    case 10:
                        if(playermatch == "BOMB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 7:
                    case 11:
                        if(playermatch == "LIPS"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 9:
                    case 12:
                        if(playermatch == "SHADES"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if (num2 == 7){
                switch(num){
                    case 3:
                    case 6:
                    case 11:
                        if(playermatch == "LIPS"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 0:
                    case 8:
                    case 9:
                        if(playermatch == "DOLPHIN"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 4:
                    case 10:
                        if(playermatch == "BALLOON"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 5:
                    case 12:
                        if(playermatch == "HAND"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 8){
                switch(num){
                    case 0:
                    case 7:
                    case 9:
                        if(playermatch == "DOLPHIN"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 6:
                    case 10:
                        if(playermatch == "BOMB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 4:
                    case 12 :
                        if(playermatch == "TARGET"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 5:
                    case 11:
                        if(playermatch == "YINYANG"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 9){
                switch(num){
                    case 1:
                    case 6:
                    case 12:
                        if(playermatch == "SHADES"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 5:
                    case 10:
                        if(playermatch == "CANDLE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 4:
                    case 11 :
                        if(playermatch == "CHEESE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 0:
                    case 7:
                    case 8:
                        if(playermatch == "DOLPHIN"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        }else if(num2 == 10){
                switch(num){
                    case 0:
                    case 10:
                    case 11:
                        if(playermatch == "BULB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 4:
                    case 7:
                        if(playermatch == "BALLOON"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 6:
                    case 8 :
                        if(playermatch == "BOMB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 5:
                    case 9:
                        if(playermatch == "CANDLE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 11){
                switch(num){
                    case 1:
                    case 5:
                    case 8:
                        if(playermatch == "YINYANG"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 4:
                    case 9:
                        if(playermatch == "CHEESE"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 0:
                    case 10:
                    case 12 :
                        if(playermatch == "BULB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 3:
                    case 6:
                    case 7:
                        if(playermatch == "LIPS"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
                        break;
                }
        } else if(num2 == 12){
                switch(num){
                    case 3:
                    case 4:
                    case 8:
                        if(playermatch == "TARGET"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 0:
                    case 10:
                    case 11:
                        if(playermatch == "BULB"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 2:
                    case 5:
                    case 7 :
                        if(playermatch == "HAND"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case 1:
                    case 6:
                    case 9:
                        if(playermatch == "SHADES"){
                            out.println("CORRECT");
                            //JOptionPane.showMessageDialog(null, "The answer is: " + playermatch + "!", "CORRECT!", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    default:
                        out.println("SAME");
                        
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
    
    private int randnumclient(){
        Random rand = new Random();
        int num1 = rand.nextInt(images.length-1) + 1;
        
    	return num1;
    }
    
    private void spotitlabels(){    	
        // For the spot it card
        panel.setBounds(739, 354, 285, 352);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        Image img = new ImageIcon(this.getClass().getResource(images[num])).getImage();
        
       
        
        
        bottomsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bottomsymcheck(num);
            }
        });
        bottomsymbol.setBounds(104, 194, 78, 63);
        panel.add(bottomsymbol);
        
        rightsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rightsymcheck(num);
            }
        });
        rightsymbol.setBounds(162, 130, 78, 63);
        panel.add(rightsymbol);
        
        leftsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                leftsymcheck(num);                              
            }
        });
        leftsymbol.setBounds(56, 130, 69, 79);
        panel.add(leftsymbol);
        
        topsymbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                topsymcheck(num);
            }
        });
        topsymbol.setBounds(104, 82, 69, 68);
        panel.add(topsymbol);
        
        spotitcard.setBounds(43, 71, 199, 197);
        panel.add(spotitcard);
        spotitcard.setIcon(new ImageIcon(img));
        
        // End of if for spot it card     
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
