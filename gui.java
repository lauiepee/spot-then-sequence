import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;
import java.util.Calendar;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

import java.awt.Component;
import javax.swing.border.BevelBorder;

public class gui {

	private String playername = "";
	private String playermatch = "";
	
	private JFrame frmSpotThenSequence;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmSpotThenSequence.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public gui() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {

		playername = JOptionPane.showInputDialog("What is your player name?");
		JOptionPane.showMessageDialog(null, "Welcome, " + playername + "!");
		
		frmSpotThenSequence = new JFrame();
		frmSpotThenSequence.setLocationByPlatform(true);
		frmSpotThenSequence.setResizable(false);
		frmSpotThenSequence.setVisible(true);
		frmSpotThenSequence.setFont(new Font("Courier New", Font.PLAIN, 12));
		frmSpotThenSequence.setForeground(SystemColor.desktop);
		frmSpotThenSequence.setBackground(SystemColor.desktop);
		frmSpotThenSequence.setTitle("Spot Then Sequence");
		frmSpotThenSequence.setBounds(100, 100, 554, 536);
		frmSpotThenSequence.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpotThenSequence.getContentPane().setLayout(null);
		
		
		String[] images = {"/a.png", "/b.png", "/c.png", "/d.png", "/e.png", "/f.png", "/g.png", "/h.png", "/i.png", "/j.png", "/k.png", "/l.png", "/m.png"};
		Random rand = new Random();
		int num = rand.nextInt(images.length-1) + 1;
		
		Random rand2 = new Random();
		int num2 = rand2.nextInt(images.length-1) + 1;
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\lauri\\Downloads\\rank.png"));
		lblNewLabel_1.setBounds(398, 35, 39, 53);
		frmSpotThenSequence.getContentPane().add(lblNewLabel_1);
		Image img = new ImageIcon(this.getClass().getResource(images[num])).getImage();
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(698, 549, 115, 29);
		frmSpotThenSequence.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 227, 548, 272);
		frmSpotThenSequence.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 16, 518, 242);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(img));
		
		JTextArea textArea = new JTextArea();
		textArea.setDisabledTextColor(SystemColor.desktop);
		textArea.setBackground(SystemColor.menu);
		textArea.setForeground(SystemColor.desktop);
		textArea.setFont(new Font("SimSun", Font.PLAIN, 12));
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(295, 83, 243, 128);
		frmSpotThenSequence.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Calendar cal = Calendar.getInstance();
				textArea.append(playername + " " + cal.getTime());

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
		lblNewLabel.setBounds(176, 16, 63, 76);
		panel_1.add(lblNewLabel);
		
		// RIGHT
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Calendar cal = Calendar.getInstance();
				textArea.append(playername + " " + cal.getTime());
				
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
		label.setBounds(236, 73, 63, 97);
		panel_1.add(label);
		
		// BOTTOM
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Calendar cal = Calendar.getInstance();
				textArea.append(playername + " " + cal.getTime());
				
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
		label_1.setBounds(176, 124, 57, 76);
		panel_1.add(label_1);
		
		// LEFT
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Calendar cal = Calendar.getInstance();
				textArea.setText("" + cal.getTime());
				
				switch (num){
				case 0:
					playermatch = "ICE";
					JOptionPane.showMessageDialog(null, "ICE");
					break;
				case 1:
					playermatch = "YINYANG";
					JOptionPane.showMessageDialog(null, "YINYANG");
					break;
				case 2:
					playermatch = "TREE";
					JOptionPane.showMessageDialog(null, "TREE");
					break;
				case 3:
					playermatch = "LIPS";
					JOptionPane.showMessageDialog(null, "LIPS");
					break;
				case 4:
					playermatch = "CHEESE";
					JOptionPane.showMessageDialog(null, "CHEESE");
					break;
				case 5:
					playermatch = "HAND";
					JOptionPane.showMessageDialog(null, "HAND");
					break;
				case 6:
					playermatch = "SHADES";
					JOptionPane.showMessageDialog(null, "SHADES");
					break;
				case 7:
					playermatch = "DOLPHIN";
					JOptionPane.showMessageDialog(null, "DOLPHIN");
					break;
				case 8:
					playermatch = "BOMB";
					JOptionPane.showMessageDialog(null, "BOMB");
					break;
				case 9:
					playermatch = "CANDLE";
					JOptionPane.showMessageDialog(null, "CANDLE");
					break;
				case 10:
					playermatch = "BALLOON";
					JOptionPane.showMessageDialog(null, "BALLOON");
					break;
				case 11:
					playermatch = "CHEESE";
					JOptionPane.showMessageDialog(null, "CHEESE");
					break;
				case 12:
					playermatch = "BULB";
					JOptionPane.showMessageDialog(null, "BULB");
					break;
			}
			}
		});
		label_2.setBounds(119, 73, 63, 81);
		panel_1.add(label_2);
		lblNewLabel_2.setBounds(108, 0, 211, 236);
		panel_1.add(lblNewLabel_2);
		
		System.out.println("NUM: " + num);
		System.out.println("NUM2 (Server): " + num2);
		
		JButton btnPass = new JButton("PASS");
		btnPass.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPass.setBackground(SystemColor.activeCaption);
		btnPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPass.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPass.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPass.setFont(new Font("Surabanglus", Font.PLAIN, 25));
		btnPass.setBounds(370, 97, 115, 40);
		panel_1.add(btnPass);
		btnPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JTextArea txtrPlayerName = new JTextArea();
		txtrPlayerName.setEditable(false);
		txtrPlayerName.setLineWrap(true);
		txtrPlayerName.setText(playername);
		txtrPlayerName.setFont(new Font("Surabanglus", Font.PLAIN, 20));
		txtrPlayerName.setBackground(SystemColor.menu);
		txtrPlayerName.setBounds(398, 0, 135, 42);
		frmSpotThenSequence.getContentPane().add(txtrPlayerName);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(65, 16, 202, 197);
		frmSpotThenSequence.getContentPane().add(lblNewLabel_3);
		Image img_server = new ImageIcon(this.getClass().getResource(images[num2])).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(img_server));
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\lauri\\Downloads\\spot-then-sequence-master\\spot-then-sequence-master\\Back_1.png"));
		lblNewLabel_4.setBounds(40, 16, 202, 195);
		frmSpotThenSequence.getContentPane().add(lblNewLabel_4);
		
		
		
		JTextArea txtrRanks = new JTextArea();
		txtrRanks.setEditable(false);
		txtrRanks.setFont(new Font("Surabanglus", Font.BOLD, 35));
		txtrRanks.setBackground(SystemColor.control);
		txtrRanks.setText("RANKS");
		txtrRanks.setBounds(435, 19, 98, 69);
		frmSpotThenSequence.getContentPane().add(txtrRanks);
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
}
