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
    //JTextField textField = new JTextField(40);
    //JTextArea messageArea = new JTextArea(8, 40);
    String[] symbols = {"a","b","c","d","e","f","g","h","i","j"}; 
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField textField_1 = new JTextField(40);
    JTextArea messageArea_1 = new JTextArea(8, 40);
    Calendar now = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    String name = "";
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public seq_client() {
        setBounds(100, 100, 1173, 766);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 1151, 710);
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);

        String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};
		Random rand = new Random();
		int num = rand.nextInt(images.length-1) + 1;
		
		Random rand2 = new Random();
		int num2 = rand2.nextInt(images.length-1) + 1;

        JLabel a10 = new JLabel("");
        a10.setBounds(669, 11, 61, 58);
        a10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A10");
            }
        });
        
        JLabel a2 = new JLabel("");
        a2.setBounds(85, 11, 61, 58);
        a2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A2");
            }
        });
        
        JLabel a3 = new JLabel("");
        a3.setBounds(158, 11, 61, 58);
        a3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A3");
            }
        });
        
        JLabel a4 = new JLabel("");
        a4.setBounds(231, 11, 61, 58);
        a4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A4");
            }
        });
        
        JLabel a5 = new JLabel("");
        a5.setBounds(304, 11, 61, 58);
        a5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A5");
            }
        });
        
        JLabel a6 = new JLabel("");
        a6.setBounds(377, 11, 61, 58);
        a6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A6");
            }
        });
        
        JLabel a7 = new JLabel("");
        a7.setBounds(450, 11, 61, 58);
        a7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A7");
            }
        });
        
        JLabel a8 = new JLabel("");
        a8.setBounds(523, 11, 61, 58);
        a8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A8");
            }
        });
        
        JLabel a9 = new JLabel("");
        a9.setBounds(596, 11, 61, 58);
        a9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A9");
            }
        });
        
        JLabel a1 = new JLabel("");
        a1.setBounds(12, 11, 61, 58);
        a1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("A1");
            }
        });
        
        JLabel B10 = new JLabel("");
        B10.setBounds(669, 81, 61, 58);
        B10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B10");
            }
        });
        
        JLabel B2 = new JLabel("");
        B2.setBounds(85, 81, 61, 58);
        B2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B2");
            }
        });
        
        JLabel B3 = new JLabel("");
        B3.setBounds(158, 81, 61, 58);
        B3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B3");
            }
        });
        
        JLabel B4 = new JLabel("");
        B4.setBounds(231, 81, 61, 58);
        B4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B4");
            }
        });
        
        JLabel B5 = new JLabel("");
        B5.setBounds(304, 81, 61, 58);
        B5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B5");
            }
        });
        
        JLabel B6 = new JLabel("");
        B6.setBounds(377, 81, 61, 58);
        B6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B6");
            }
        });
        
        JLabel B7 = new JLabel("");
        B7.setBounds(450, 81, 61, 58);
        B7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B7");
            }
        });
        
        JLabel B8 = new JLabel("");
        B8.setBounds(523, 81, 61, 58);
        B8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B8");
            }
        });
        
        JLabel B9 = new JLabel("");
        B9.setBounds(596, 81, 61, 58);
        B9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B9");
            }
        });
        
        JLabel B1 = new JLabel("");
        B1.setBounds(12, 81, 61, 58);
        B1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("B1");
            }
        });
        
        JLabel C10 = new JLabel("");
        C10.setBounds(669, 151, 61, 58);
        C10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C10");
            }
        });
        
        
        
        JLabel C2 = new JLabel("");
        C2.setBounds(85, 151, 61, 58);
        C2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C2");
            }
        });
        
        JLabel C3 = new JLabel("");
        C3.setBounds(158, 151, 61, 58);
        C3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C3");
            }
        });
        
        JLabel C4 = new JLabel("");
        C4.setBounds(231, 151, 61, 58);
        C4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C4");
            }
        });
        
        JLabel C5 = new JLabel("");
        C5.setBounds(304, 151, 61, 58);
        C5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C5");
            }
        });
        
        JLabel C6 = new JLabel("");
        C6.setBounds(377, 151, 61, 58);
        C6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C6");
            }
        });
        
        JLabel C7 = new JLabel("");
        C7.setBounds(450, 151, 61, 58);
        C7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C7");
            }
        });
        
        JLabel C8 = new JLabel("");
        C8.setBounds(523, 151, 61, 58);
        C8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C8");
            }
        });
        
        JLabel C9 = new JLabel("");
        C9.setBounds(596, 151, 61, 58);
        C9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C9");
            }
        });
        
        JLabel C1 = new JLabel("");
        C1.setBounds(12, 151, 61, 58);
        C1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("C1");
            }
        });
        
        JLabel D1 = new JLabel("");
        D1.setBounds(12, 221, 61, 58);
        D1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D1");
            }
        });
            
        JLabel D2 = new JLabel("");
        D2.setBounds(85, 221, 61, 58);
        D2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D2");
            }
        });
        
        JLabel D3 = new JLabel("");
        D3.setBounds(158, 221, 61, 58);
        D3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D3");
            }
        });
        
        JLabel D4 = new JLabel("");
        D4.setBounds(231, 221, 61, 58);
        D4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D4");
            }
        });
        
        JLabel D5 = new JLabel("");
        D5.setBounds(304, 221, 61, 58);
        D5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D5");
            }
        });
        
        JLabel D6 = new JLabel("");
        D6.setBounds(377, 221, 61, 58);
        D6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D6");
            }
        });
        
        JLabel D7 = new JLabel("");
        D7.setBounds(450, 221, 61, 58);
        D7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D7");
            }
        });
        
        JLabel D8 = new JLabel("");
        D8.setBounds(523, 221, 61, 58);
        D8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D8");
            }
        });
        
        JLabel D9 = new JLabel("");
        D9.setBounds(596, 221, 61, 58);
        D9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D9");
            }
        });
        
        JLabel D10 = new JLabel("");
        D10.setBounds(669, 221, 61, 58);
        D10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("D10");
            }
        });
        
        JLabel E1 = new JLabel("");
        E1.setBounds(12, 291, 61, 58);
        E1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E1");
            }
        });
        
        JLabel E2 = new JLabel("");
        E2.setBounds(85, 291, 61, 58);
        E2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E2");
            }
        });
        
        JLabel E3 = new JLabel("");
        E3.setBounds(158, 291, 61, 58);
        E3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E3");
            }
        });
        
        JLabel E4 = new JLabel("");
        E4.setBounds(231, 291, 61, 58);
        E4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E4");
            }
        });
        
        JLabel E5 = new JLabel("");
        E5.setBounds(304, 291, 61, 58);
        E5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E5");
            }
        });
        
        JLabel E6 = new JLabel("");
        E6.setBounds(377, 291, 61, 58);
        E6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E6");
            }
        });
        
        JLabel E7 = new JLabel("");
        E7.setBounds(450, 291, 61, 58);
        E7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E7");
            }
        });
        
        JLabel E8 = new JLabel("");
        E8.setBounds(523, 291, 61, 58);
        E8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E8");
            }
        });
        
        JLabel E9 = new JLabel("");
        E9.setBounds(596, 291, 61, 58);
        E9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E9");
            }
        });
        
        JLabel E10 = new JLabel("");
        E10.setBounds(669, 291, 61, 58);
        E10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("E10");
            }
        });
        
        JLabel F1 = new JLabel("");
        F1.setBounds(12, 361, 61, 58);
        F1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F1");
            }
        });
        
        JLabel F2 = new JLabel("");
        F2.setBounds(85, 361, 61, 58);
        F2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F2");
            }
        });
        
        JLabel F3 = new JLabel("");
        F3.setBounds(158, 361, 61, 58);
        F3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F3");
            }
        });

        JLabel F4 = new JLabel("");
        F4.setBounds(231, 361, 61, 58);
        F4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F4");
            }
         });
        
        JLabel F5 = new JLabel("");
        F5.setBounds(304, 361, 61, 58);
        F5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F5");
            }
        });
        
        JLabel F6 = new JLabel("");
        F6.setBounds(377, 361, 61, 58);
        F6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F6");
            }
        });
        
        JLabel F7 = new JLabel("");
        F7.setBounds(450, 361, 61, 58);
        F7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F7");
            }
        });
        
        JLabel F8 = new JLabel("");
        F8.setBounds(523, 361, 61, 58);
        F8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F8");
            }
        });
        
        JLabel F9 = new JLabel("");
        F9.setBounds(596, 361, 61, 58);
        F9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F9");
            }
        });
        
        JLabel F10 = new JLabel("");
        F10.setBounds(669, 361, 61, 58);
        F10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("F10");
            }
        });
        
        JLabel G1 = new JLabel("");
        G1.setBounds(12, 431, 61, 58);
        G1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G1");
            }
        });
        
        JLabel G2 = new JLabel("");
        G2.setBounds(85, 431, 61, 58);
        G2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G2");
            }
        });
        
        JLabel G3 = new JLabel("");
        G3.setBounds(158, 431, 61, 58);
        G3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G3");
            }
        });
        
        JLabel G4 = new JLabel("");
        G4.setBounds(231, 431, 61, 58);
        G4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G4");
            }
        });
        
        JLabel G5 = new JLabel("");
        G5.setBounds(304, 431, 61, 58);
        G5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G5");
            }
        });
        
        JLabel G6 = new JLabel("");
        G6.setBounds(377, 431, 61, 58);
        G6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G6");
            }
        });
        
        JLabel G7 = new JLabel("");
        G7.setBounds(450, 431, 61, 58);
        G7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G7");
            }
        });
        
        JLabel G8 = new JLabel("");
        G8.setBounds(523, 431, 61, 58);
        G8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G8");
            }
        });
        
        JLabel G9 = new JLabel("");
        G9.setBounds(596, 431, 61, 58);
        G9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G9");
            }
        });
        
        JLabel G10 = new JLabel("");
        G10.setBounds(669, 431, 61, 58);
        G10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("G10");
            }
        });
        
        JLabel H1 = new JLabel("");
        H1.setBounds(12, 500, 61, 58);
        H1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H1");
            }
        });
        
        JLabel H2 = new JLabel("");
        H2.setBounds(85, 500, 61, 58);
        H2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H2");
            }
        });
        
        JLabel H3 = new JLabel("");
        H3.setBounds(158, 500, 61, 58);
        H3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H3");
            }
        });
        
        JLabel H4 = new JLabel("");
        H4.setBounds(231, 500, 61, 58);
        H4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H4");
            }
        });
        
        JLabel H5 = new JLabel("");
        H5.setBounds(304, 501, 61, 58);
        H5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H5");
            }
        });
        
        JLabel H6 = new JLabel("");
        H6.setBounds(377, 500, 61, 58);
        H6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H6");
            }
        });
        
        JLabel H7 = new JLabel("");
        H7.setBounds(450, 501, 61, 58);
        H7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H7");
            }
        });
        
        JLabel H8 = new JLabel("");
        H8.setBounds(523, 500, 61, 58);
        H8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H8");
            }
        });
        
        JLabel H9 = new JLabel("");
        H9.setBounds(596, 501, 61, 58);
        H9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H9");
            }
        });
        
        JLabel H10 = new JLabel("");
        H10.setBounds(669, 501, 61, 58);
        H10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("H10");
            }
        });
        
        JLabel I1 = new JLabel("");
        I1.setBounds(12, 570, 61, 58);
        I1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I1");
            }
        });
        
        JLabel I2 = new JLabel("");
        I2.setBounds(85, 570, 61, 58);
        I2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I2");
            }
        });
        
        JLabel I3 = new JLabel("");
        I3.setBounds(158, 570, 61, 58);
        I3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I3");
            }
        });
        
        JLabel I4 = new JLabel("");
        I4.setBounds(231, 570, 61, 58);
        I4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I4");
            }
        });
        
        JLabel I5 = new JLabel("");
        I5.setBounds(304, 571, 61, 58);
        I5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I5");
            }
        });
        
        JLabel I6 = new JLabel("");
        I6.setBounds(377, 570, 61, 58);
        I6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I6");
            }
        });
        
        JLabel I7 = new JLabel("");
        I7.setBounds(450, 571, 61, 58);
        I7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I7");
            }
        });
        
        JLabel I8 = new JLabel("");
        I8.setBounds(523, 570, 61, 58);
        I8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I8");
            }
        });
        
        JLabel I9 = new JLabel("");
        I9.setBounds(596, 571, 61, 58);
        I9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I9");
            }
        });
        
        JLabel I10 = new JLabel("");
        I10.setBounds(669, 572, 61, 58);
        I10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("I10");
            }
        });
        
        JLabel J1 = new JLabel("");
        J1.setBounds(12, 640, 61, 58);
        J1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J1");
            }
        });
        
        JLabel J2 = new JLabel("");
        J2.setBounds(85, 640, 61, 58);
        J2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J2");
            }
        });
        
        JLabel J3 = new JLabel("");
        J3.setBounds(158, 640, 61, 58);
        J3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J3");
            }
        });
        
        JLabel J4 = new JLabel("");
        J4.setBounds(231, 640, 61, 58);
        J4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J4");
            }
        });
        
        JLabel J5 = new JLabel("");
        J5.setBounds(304, 640, 61, 58);
        J5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J5");
            }
        });
        
        JLabel J6 = new JLabel("");
        J6.setBounds(377, 640, 61, 58);
        J6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J6");
            }
        });
        
        JLabel J7 = new JLabel("");
        J7.setBounds(450, 641, 61, 58);
        J7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J7");
            }
        });
        
        JLabel J8 = new JLabel("");
        J8.setBounds(523, 640, 61, 58);
        J8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J8");
            }
        });
        
        JLabel J9 = new JLabel("");
        J9.setBounds(596, 641, 61, 58);
        J9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 out.println("J9");
            }
        });
        
        JLabel J10 = new JLabel("");
        J10.setBounds(669, 640, 61, 58);
        J10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                out.println("J10");
            }
        });
        
        table = new JTable();
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
                textField_1.setBounds(739, 6, 260, 33);
                textField_1.setColumns(10);
                
                        // Layout GUI
                        textField_1.setEditable(false);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(739, 44, 260, 183);
                        
                        messageArea_1 = new JTextArea();
                        scrollPane.setViewportView(messageArea_1);
                        messageArea_1.setEditable(false);
                        contentPanel.setLayout(null);
                        
                        contentPanel.add(F4);
                        contentPanel.add(a1);
                        contentPanel.add(a2);
                        contentPanel.add(a3);
                        contentPanel.add(B3);
                        contentPanel.add(B4);
                        contentPanel.add(B7);
                        contentPanel.add(B9);
                        contentPanel.add(C1);
                        contentPanel.add(C2);
                        contentPanel.add(C4);
                        contentPanel.add(C5);
                        contentPanel.add(C6);
                        contentPanel.add(C8);
                        contentPanel.add(C9);
                        contentPanel.add(C10);
                        contentPanel.add(D1);
                        contentPanel.add(D3);
                        contentPanel.add(D4);
                        contentPanel.add(D5);
                        contentPanel.add(D6);
                        contentPanel.add(D8);
                        contentPanel.add(D9);
                        contentPanel.add(D10);
                        contentPanel.add(E1);
                        contentPanel.add(E2);
                        contentPanel.add(E6);
                        contentPanel.add(E8);
                        contentPanel.add(E9);
                        contentPanel.add(E10);
                        contentPanel.add(F1);
                        contentPanel.add(F5);
                        contentPanel.add(F7);
                        contentPanel.add(F8);
                        contentPanel.add(F9);
                        contentPanel.add(F10);
                        contentPanel.add(G4);
                        contentPanel.add(G5);
                        contentPanel.add(G6);
                        contentPanel.add(G7);
                        contentPanel.add(G8);
                        contentPanel.add(G9);
                        contentPanel.add(G10);
                        contentPanel.add(H2);
                        contentPanel.add(H5);
                        contentPanel.add(H6);
                        contentPanel.add(H7);
                        contentPanel.add(H10);
                        contentPanel.add(I2);
                        contentPanel.add(I4);
                        contentPanel.add(I5);
                        contentPanel.add(I6);
                        contentPanel.add(I7);
                        contentPanel.add(I8);
                        contentPanel.add(I10);
                        contentPanel.add(J1);
                        contentPanel.add(J3);
                        contentPanel.add(J5);
                        contentPanel.add(J6);
                        contentPanel.add(J8);
                        contentPanel.add(a10);
                        contentPanel.add(a4);
                        contentPanel.add(H9);
                        contentPanel.add(J2);
                        contentPanel.add(I1);
                        contentPanel.add(H4);
                        contentPanel.add(B10);
                        contentPanel.add(J10);
                        contentPanel.add(H8);
                        contentPanel.add(a6);
                        contentPanel.add(B5);
                        contentPanel.add(a7);
                        contentPanel.add(B1);
                        contentPanel.add(J4);
                        contentPanel.add(E7);
                        contentPanel.add(J7);
                        contentPanel.add(H3);
                        contentPanel.add(B8);
                        contentPanel.add(I3);
                        contentPanel.add(J9);
                        contentPanel.add(G3);
                        contentPanel.add(D2);
                        contentPanel.add(E4);
                        contentPanel.add(F3);
                        contentPanel.add(I9);
                        contentPanel.add(E5);
                        contentPanel.add(E3);
                        contentPanel.add(D7);
                        contentPanel.add(C7);
                        contentPanel.add(H1);
                        contentPanel.add(B6);
                        contentPanel.add(B2);
                        contentPanel.add(G2);
                        contentPanel.add(a8);
                        contentPanel.add(a5);
                        contentPanel.add(a9);
                        contentPanel.add(G1);
                        contentPanel.add(F2);
                        contentPanel.add(F6);
                        contentPanel.add(C3);
                        contentPanel.add(table);
                        contentPanel.add(textField_1);
                        contentPanel.add(scrollPane);
                        
                        JPanel panel = new JPanel();
                        panel.setBounds(739, 231, 406, 475);
                        contentPanel.add(panel);
                        panel.setLayout(null);
                        Image img = new ImageIcon(this.getClass().getResource(images[num])).getImage();
                        
                        JLabel topsymbol = new JLabel("New label");
                        topsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent arg0) {
                        		Calendar cal = Calendar.getInstance();
                				messageArea_1.append("" + cal.getTime());

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
                        
                        JLabel leftsymbol = new JLabel("New label");
                        leftsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                        		Calendar cal = Calendar.getInstance();
                				messageArea_1.setText("" + cal.getTime());
                				
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
                        
                        JLabel bottomsymbol = new JLabel("New label");
                        bottomsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                        		Calendar cal = Calendar.getInstance();
                				messageArea_1.append("" + cal.getTime());
                				
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
                        
                        JLabel rightsymbol = new JLabel("New label");
                        rightsymbol.addMouseListener(new MouseAdapter() {
                        	@Override
                        	public void mousePressed(MouseEvent e) {
                        		Calendar cal = Calendar.getInstance();
                				messageArea_1.append(" " + cal.getTime());
                				
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
                        
                        JPanel panel_1 = new JPanel();
                        panel_1.setBounds(1004, 6, 141, 221);
                        contentPanel.add(panel_1);
                
        
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
            }
        }
    }
    
    private void matching(String playermatch, int num, int num2){
		
		if(num2 == 0){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 1:
					case 2:
					case 3:
						if(playermatch == "TREE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 4:
					case 5:
					case 6:
						if(playermatch == "ICE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 7:
					case 8:
					case 9:
						if(playermatch == "DOLPHIN")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 10:
					case 11:
					case 12:
						if(playermatch == "BULB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 1){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 2:
					case 3:
						if(playermatch == "TREE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 4:
					case 7:
					case 10:
						if(playermatch == "BALLOON")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 5:
					case 8:
					case 11:
						if(playermatch == "YINYANG")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 6:
					case 9:
					case 12:
						if(playermatch == "SHADES")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 2){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 1:
					case 3:
						if(playermatch == "TREE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 4:
					case 9:
					case 11:
						if(playermatch == "CHEESE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 5:
					case 7:
					case 12:
						if(playermatch == "HAND")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 6:
					case 8:
					case 10:
						if(playermatch == "BOMB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 3){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 1:
					case 3:
						if(playermatch == "TREE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 4:
					case 8:
					case 12:
						if(playermatch == "TARGET")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 5:
					case 9:
					case 10:
						if(playermatch == "CANDLE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 6:
					case 7:
					case 11:
						if(playermatch == "LIPS")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 4){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 5:
					case 6:
						if(playermatch == "ICE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 7:
					case 10:
						if(playermatch == "BALLOON")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 9:
					case 11:
						if(playermatch == "CHEESE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 8:
					case 12:
						if(playermatch == "TARGET")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 5){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 4:
					case 6:
						if(playermatch == "ICE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 8:
					case 11:
						if(playermatch == "YINYANG")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 7:
					case 12:
						if(playermatch == "HAND")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 9:
					case 10:
						if(playermatch == "CANDLE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 6){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 4:
					case 5:
						if(playermatch == "ICE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 8:
					case 10:
						if(playermatch == "BOMB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 7:
					case 11:
						if(playermatch == "LIPS")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 9:
					case 12:
						if(playermatch == "SHADES")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if (num2 == 7){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 3:
					case 6:
					case 11:
						if(playermatch == "LIPS")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 0:
					case 8:
					case 9:
						if(playermatch == "DOLPHIN")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 4:
					case 10:
						if(playermatch == "BALLOON")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 5:
					case 12:
						if(playermatch == "HAND")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 8){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 7:
					case 9:
						if(playermatch == "DOLPHIN")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 6:
					case 10:
						if(playermatch == "BOMB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 4:
					case 12 :
						if(playermatch == "TARGET")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 5:
					case 11:
						if(playermatch == "YINYANG")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 9){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 1:
					case 6:
					case 12:
						if(playermatch == "SHADES")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 5:
					case 10:
						if(playermatch == "CANDLE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 4:
					case 11 :
						if(playermatch == "CHEESE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 0:
					case 7:
					case 8:
						if(playermatch == "DOLPHIN")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		}else if(num2 == 10){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 0:
					case 10:
					case 11:
						if(playermatch == "BULB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 4:
					case 7:
						if(playermatch == "BALLOON")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 6:
					case 8 :
						if(playermatch == "BOMB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 5:
					case 9:
						if(playermatch == "CANDLE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 11){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 1:
					case 5:
					case 8:
						if(playermatch == "YINYANG")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 4:
					case 9:
						if(playermatch == "CHEESE")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 0:
					case 10:
					case 12 :
						if(playermatch == "BULB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 3:
					case 6:
					case 7:
						if(playermatch == "LIPS")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else if(num2 == 12){
			System.out.println("STRING: " + playermatch);
				switch(num){
					case 3:
					case 4:
					case 8:
						if(playermatch == "TARGET")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 0:
					case 10:
					case 11:
						if(playermatch == "BULB")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 2:
					case 5:
					case 7 :
						if(playermatch == "HAND")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					case 1:
					case 6:
					case 9:
						if(playermatch == "SHADES")
							JOptionPane.showMessageDialog(null, "CORRECT");
						break;
					default:
						JOptionPane.showMessageDialog(null, "THERE IS SUMTHING WRONG");
						break;
				}
		} else{
				System.out.println("STRING: " + playermatch);
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