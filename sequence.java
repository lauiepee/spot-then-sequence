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

public class sequence extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			sequence dialog = new sequence();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public sequence() {
		setBounds(100, 100, 740, 772);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel a10 = new JLabel("");
		a10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A10");
			}
		});
		a10.setBounds(669, 11, 61, 58);
		contentPanel.add(a10);
		
		JLabel a2 = new JLabel("");
		a2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A2");
			}
		});
		a2.setBounds(85, 11, 61, 58);
		contentPanel.add(a2);
		
		JLabel a3 = new JLabel("");
		a3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A3");
			}
		});
		a3.setBounds(158, 11, 61, 58);
		contentPanel.add(a3);
		
		JLabel a4 = new JLabel("");
		a4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A4");
			}
		});
		a4.setBounds(231, 11, 61, 58);
		contentPanel.add(a4);
		
		JLabel a5 = new JLabel("");
		a5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A5");
			}
		});
		a5.setBounds(304, 11, 61, 58);
		contentPanel.add(a5);
		
		JLabel a6 = new JLabel("");
		a6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A6");
			}
		});
		a6.setBounds(377, 11, 61, 58);
		contentPanel.add(a6);
		
		JLabel a7 = new JLabel("");
		a7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A7");
			}
		});
		a7.setBounds(450, 11, 61, 58);
		contentPanel.add(a7);
		
		JLabel a8 = new JLabel("");
		a8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A8");
			}
		});
		a8.setBounds(523, 11, 61, 58);
		contentPanel.add(a8);
		
		JLabel a9 = new JLabel("");
		a9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A9");
			}
		});
		a9.setBounds(596, 11, 61, 58);
		contentPanel.add(a9);
		
		JLabel a1 = new JLabel("");
		a1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("A1");
			}
		});
		a1.setBounds(12, 11, 61, 58);
		contentPanel.add(a1);
		
		JLabel label = new JLabel("");
		label.setBounds(669, 81, 61, 58);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(85, 81, 61, 58);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(158, 81, 61, 58);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(231, 81, 61, 58);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(304, 81, 61, 58);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(377, 81, 61, 58);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(450, 81, 61, 58);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(523, 81, 61, 58);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(596, 81, 61, 58);
		contentPanel.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(12, 81, 61, 58);
		contentPanel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setBounds(669, 151, 61, 58);
		contentPanel.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setBounds(85, 151, 61, 58);
		contentPanel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setBounds(158, 151, 61, 58);
		contentPanel.add(label_12);
		
		JLabel label_13 = new JLabel("");
		label_13.setBounds(231, 151, 61, 58);
		contentPanel.add(label_13);
		
		JLabel label_14 = new JLabel("");
		label_14.setBounds(304, 151, 61, 58);
		contentPanel.add(label_14);
		
		JLabel label_15 = new JLabel("");
		label_15.setBounds(377, 151, 61, 58);
		contentPanel.add(label_15);
		
		JLabel label_16 = new JLabel("");
		label_16.setBounds(450, 151, 61, 58);
		contentPanel.add(label_16);
		
		JLabel label_17 = new JLabel("");
		label_17.setBounds(523, 151, 61, 58);
		contentPanel.add(label_17);
		
		JLabel label_18 = new JLabel("");
		label_18.setBounds(596, 151, 61, 58);
		contentPanel.add(label_18);
		
		JLabel label_19 = new JLabel("");
		label_19.setBounds(12, 151, 61, 58);
		contentPanel.add(label_19);
		
		table = new JTable();
		table.setEnabled(false);
		table.setToolTipText("null\n");
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setForeground(Color.WHITE);
		table.setBorder(new LineBorder(new Color(0, 255, 255), 3));
		table.setBackground(Color.DARK_GRAY);
		table.setBounds(6, 6, 727, 700);
		contentPanel.add(table);
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
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
