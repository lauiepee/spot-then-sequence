import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class application {

	private JFrame frame = new JFrame("Spot then Sequence");
	private JTextField address;
	private JTextField name;
	private BufferedReader in;
    private PrintWriter out;
    private String[] symbols = {"a","b","c","d","e","f","g","h","i","j"}; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					application window = new application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 840, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSpotThenSequence = new JLabel("Spot then Sequence");
		lblSpotThenSequence.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpotThenSequence.setFont(new Font("SignPainter", Font.PLAIN, 60));
		lblSpotThenSequence.setForeground(Color.WHITE);
		lblSpotThenSequence.setBounds(373, 318, 410, 65);
		frame.getContentPane().add(lblSpotThenSequence);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(48, 47, 313, 336);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		address = new JTextField();
		address.setToolTipText("");
		address.setForeground(Color.BLACK);
		address.setHorizontalAlignment(SwingConstants.CENTER);
		address.setBounds(67, 83, 180, 26);
		panel.add(address);
		address.setColumns(10);
		
		JButton btnBack = new JButton("<- Back");
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setFont(new Font("Yuanti SC", Font.PLAIN, 15));
		btnBack.setBounds(6, 6, 83, 29);
		frame.getContentPane().add(btnBack);
		btnBack.setVisible(false);
		
		JButton btnConnectToServer = new JButton("Connect to Server");
		btnConnectToServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Connecting to Server...");
				panel.setVisible(false);
				lblSpotThenSequence.setVisible(false);
				btnBack.setVisible(true);
			}
		});
		btnConnectToServer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnConnectToServer.setBounds(100, 275, 117, 29);
		panel.add(btnConnectToServer);
		
		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String key = KeyEvent.getKeyText(e.getKeyCode());
				if (key == "⏎") {
					btnConnectToServer.doClick();
				}
				
			}
		});
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(Color.BLACK);
		name.setColumns(10);
		name.setBounds(67, 194, 180, 26);
		panel.add(name);
		
		JLabel lblServersIpAddress = new JLabel("Server's IP Address");
		lblServersIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblServersIpAddress.setFont(new Font("Yuanti SC", Font.PLAIN, 20));
		lblServersIpAddress.setBounds(71, 55, 176, 26);
		panel.add(lblServersIpAddress);
		
		JLabel lblNameOfClient = new JLabel("Name of Client");
		lblNameOfClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameOfClient.setFont(new Font("Yuanti SC", Font.PLAIN, 20));
		lblNameOfClient.setBounds(67, 169, 176, 26);
		panel.add(lblNameOfClient);
		
	}
	
	private void connection(String address) throws IOException {
		Socket socket = new Socket (address, 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
}
