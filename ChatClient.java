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
import java.util.Calendar;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
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
public class ChatClient extends JDialog{

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    //JTextField textField = new JTextField(40);
    //JTextArea messageArea = new JTextArea(8, 40);
    String[] symbols = {"a","b","c","d","e","f","g","h","i","j"}; 
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JLabel D1;
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
    public ChatClient() {
        setBounds(100, 100, 1041, 736);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        JLabel a10 = new JLabel("");
        a10.setBounds(669, 11, 61, 58);
        a10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a2 = new JLabel("");
        a2.setBounds(85, 11, 61, 58);
        a2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a3 = new JLabel("");
        a3.setBounds(158, 11, 61, 58);
        a3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a4 = new JLabel("");
        a4.setBounds(231, 11, 61, 58);
        a4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a5 = new JLabel("");
        a5.setBounds(304, 11, 61, 58);
        a5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a6 = new JLabel("");
        a6.setBounds(377, 11, 61, 58);
        a6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a7 = new JLabel("");
        a7.setBounds(450, 11, 61, 58);
        a7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a8 = new JLabel("");
        a8.setBounds(523, 11, 61, 58);
        a8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a9 = new JLabel("");
        a9.setBounds(596, 11, 61, 58);
        a9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel a1 = new JLabel("");
        a1.setBounds(12, 11, 61, 58);
        a1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "A1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B10 = new JLabel("");
        B10.setBounds(669, 81, 61, 58);
        B10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B2 = new JLabel("");
        B2.setBounds(85, 81, 61, 58);
        B2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B3 = new JLabel("");
        B3.setBounds(158, 81, 61, 58);
        B3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B4 = new JLabel("");
        B4.setBounds(231, 81, 61, 58);
        B4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B5 = new JLabel("");
        B5.setBounds(304, 81, 61, 58);
        B5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B6 = new JLabel("");
        B6.setBounds(377, 81, 61, 58);
        B6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B7 = new JLabel("");
        B7.setBounds(450, 81, 61, 58);
        B7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B8 = new JLabel("");
        B8.setBounds(523, 81, 61, 58);
        B8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B9 = new JLabel("");
        B9.setBounds(596, 81, 61, 58);
        B9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel B1 = new JLabel("");
        B1.setBounds(12, 81, 61, 58);
        B1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "B1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C10 = new JLabel("");
        C10.setBounds(669, 151, 61, 58);
        C10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        
        
        JLabel C2 = new JLabel("");
        C2.setBounds(85, 151, 61, 58);
        C2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C3 = new JLabel("");
        C3.setBounds(158, 151, 61, 58);
        C3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C4 = new JLabel("");
        C4.setBounds(231, 151, 61, 58);
        C4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C5 = new JLabel("");
        C5.setBounds(304, 151, 61, 58);
        C5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C6 = new JLabel("");
        C6.setBounds(377, 151, 61, 58);
        C6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C7 = new JLabel("");
        C7.setBounds(450, 151, 61, 58);
        C7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C8 = new JLabel("");
        C8.setBounds(523, 151, 61, 58);
        C8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C9 = new JLabel("");
        C9.setBounds(596, 151, 61, 58);
        C9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel C1 = new JLabel("");
        C1.setBounds(12, 151, 61, 58);
        C1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "C1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D1 = new JLabel("");
        D1.setBounds(12, 221, 61, 58);
        D1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
            
        JLabel D2 = new JLabel("");
        D2.setBounds(85, 221, 61, 58);
        D2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D3 = new JLabel("");
        D3.setBounds(158, 221, 61, 58);
        D3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D4 = new JLabel("");
        D4.setBounds(231, 221, 61, 58);
        D4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D5 = new JLabel("");
        D5.setBounds(304, 221, 61, 58);
        D5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D6 = new JLabel("");
        D6.setBounds(377, 221, 61, 58);
        D6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D7 = new JLabel("");
        D7.setBounds(450, 221, 61, 58);
        D7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D8 = new JLabel("");
        D8.setBounds(523, 221, 61, 58);
        D8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D9 = new JLabel("");
        D9.setBounds(596, 221, 61, 58);
        D9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel D10 = new JLabel("");
        D10.setBounds(669, 221, 61, 58);
        D10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "D10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E1 = new JLabel("");
        E1.setBounds(12, 291, 61, 58);
        E1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E2 = new JLabel("");
        E2.setBounds(85, 291, 61, 58);
        E2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E3 = new JLabel("");
        E3.setBounds(158, 291, 61, 58);
        E3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E4 = new JLabel("");
        E4.setBounds(231, 291, 61, 58);
        E4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E5 = new JLabel("");
        E5.setBounds(304, 291, 61, 58);
        E5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E6 = new JLabel("");
        E6.setBounds(377, 291, 61, 58);
        E6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E7 = new JLabel("");
        E7.setBounds(450, 291, 61, 58);
        E7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E8 = new JLabel("");
        E8.setBounds(523, 291, 61, 58);
        E8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E9 = new JLabel("");
        E9.setBounds(596, 291, 61, 58);
        E9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel E10 = new JLabel("");
        E10.setBounds(669, 291, 61, 58);
        E10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "E10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F1 = new JLabel("");
        F1.setBounds(12, 361, 61, 58);
        F1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F2 = new JLabel("");
        F2.setBounds(85, 361, 61, 58);
        F2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F3 = new JLabel("");
        F3.setBounds(158, 361, 61, 58);
        F3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });

        JLabel F4 = new JLabel("");
        F4.setBounds(231, 361, 61, 58);
        F4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F4" + "\t" + formatter.format(now.getTime())+"\n");
            }
         });
        
        JLabel F5 = new JLabel("");
        F5.setBounds(304, 361, 61, 58);
        F5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F6 = new JLabel("");
        F6.setBounds(377, 361, 61, 58);
        F6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F7 = new JLabel("");
        F7.setBounds(450, 361, 61, 58);
        F7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F8 = new JLabel("");
        F8.setBounds(523, 361, 61, 58);
        F8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F9 = new JLabel("");
        F9.setBounds(596, 361, 61, 58);
        F9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel F10 = new JLabel("");
        F10.setBounds(669, 361, 61, 58);
        F10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "F10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G1 = new JLabel("");
        G1.setBounds(12, 431, 61, 58);
        G1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G2 = new JLabel("");
        G2.setBounds(85, 431, 61, 58);
        G2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G3 = new JLabel("");
        G3.setBounds(158, 431, 61, 58);
        G3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G4 = new JLabel("");
        G4.setBounds(231, 431, 61, 58);
        G4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G5 = new JLabel("");
        G5.setBounds(304, 431, 61, 58);
        G5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G6 = new JLabel("");
        G6.setBounds(377, 431, 61, 58);
        G6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G7 = new JLabel("");
        G7.setBounds(450, 431, 61, 58);
        G7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G8 = new JLabel("");
        G8.setBounds(523, 431, 61, 58);
        G8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G9 = new JLabel("");
        G9.setBounds(596, 431, 61, 58);
        G9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel G10 = new JLabel("");
        G10.setBounds(669, 431, 61, 58);
        G10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "G10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H1 = new JLabel("");
        H1.setBounds(12, 500, 61, 58);
        H1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H2 = new JLabel("");
        H2.setBounds(85, 500, 61, 58);
        H2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H3 = new JLabel("");
        H3.setBounds(158, 500, 61, 58);
        H3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H4 = new JLabel("");
        H4.setBounds(231, 500, 61, 58);
        H4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H5 = new JLabel("");
        H5.setBounds(304, 501, 61, 58);
        H5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H6 = new JLabel("");
        H6.setBounds(377, 500, 61, 58);
        H6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H7 = new JLabel("");
        H7.setBounds(450, 501, 61, 58);
        H7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H8 = new JLabel("");
        H8.setBounds(523, 500, 61, 58);
        H8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H9 = new JLabel("");
        H9.setBounds(596, 501, 61, 58);
        H9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel H10 = new JLabel("");
        H10.setBounds(669, 501, 61, 58);
        H10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "H10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I1 = new JLabel("");
        I1.setBounds(12, 570, 61, 58);
        I1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I2 = new JLabel("");
        I2.setBounds(85, 570, 61, 58);
        I2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I3 = new JLabel("");
        I3.setBounds(158, 570, 61, 58);
        I3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I4 = new JLabel("");
        I4.setBounds(231, 570, 61, 58);
        I4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I5 = new JLabel("");
        I5.setBounds(304, 571, 61, 58);
        I5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I6 = new JLabel("");
        I6.setBounds(377, 570, 61, 58);
        I6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I7 = new JLabel("");
        I7.setBounds(450, 571, 61, 58);
        I7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I8 = new JLabel("");
        I8.setBounds(523, 570, 61, 58);
        I8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I9 = new JLabel("");
        I9.setBounds(596, 571, 61, 58);
        I9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel I10 = new JLabel("");
        I10.setBounds(669, 572, 61, 58);
        I10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "I10" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J1 = new JLabel("");
        J1.setBounds(12, 640, 61, 58);
        J1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J1" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J2 = new JLabel("");
        J2.setBounds(85, 640, 61, 58);
        J2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J2" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J3 = new JLabel("");
        J3.setBounds(158, 640, 61, 58);
        J3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J3" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J4 = new JLabel("");
        J4.setBounds(231, 640, 61, 58);
        J4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J4" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J5 = new JLabel("");
        J5.setBounds(304, 640, 61, 58);
        J5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J5" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J6 = new JLabel("");
        J6.setBounds(377, 640, 61, 58);
        J6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J6" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J7 = new JLabel("");
        J7.setBounds(450, 641, 61, 58);
        J7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J7" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J8 = new JLabel("");
        J8.setBounds(523, 640, 61, 58);
        J8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J8" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J9 = new JLabel("");
        J9.setBounds(596, 641, 61, 58);
        J9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J9" + "\t" + formatter.format(now.getTime())+"\n");
            }
        });
        
        JLabel J10 = new JLabel("");
        J10.setBounds(669, 640, 61, 58);
        J10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageArea_1.append(name + ": " + "J10" + "\t" + formatter.format(now.getTime())+"\n");
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
                textField_1.setBounds(739, 6, 291, 33);
                textField_1.setColumns(10);
                
                        // Layout GUI
                        textField_1.setEditable(false);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(739, 45, 291, 662);
                        
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
        String data[] = new String[6];
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

        name = name+getName();
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(name);
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField_1.setEditable(true);
                JOptionPane.showMessageDialog(frame, "Welcome! Please wait for others to join the conversation...");
            } else if (line.startsWith("SYMBOLS")) {
                out.println(getCardSymbol());
                JOptionPane.showMessageDialog(frame, getCardSymbol());
                messageArea_1.append(line + "\n");
            } else if (line.startsWith("MESSAGE")) {
                messageArea_1.append(line.substring(8) + "\n");
            }
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        try {
            ChatClient dialog = new ChatClient();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}