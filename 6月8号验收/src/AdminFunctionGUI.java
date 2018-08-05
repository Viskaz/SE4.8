import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFunctionGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminFunctionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnDeleteUser = new JButton("User Management");
		btnDeleteUser.addActionListener(e -> {
            dispose();
            new AdminViewUserGUI().setVisible(true);
        });
		btnDeleteUser.setBounds(188, 83, 231, 73);
		contentPane.add(btnDeleteUser);
		
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> {
            dispose();
            new AdminLoginGUI().setVisible(true);
        });
		btnReturn.setBounds(501, 319, 123, 29);
		contentPane.add(btnReturn);
		
		JButton btnSetPrice = new JButton("Set Price");
		btnSetPrice.addActionListener(e -> {
            dispose();
            new SetPriceGUI().setVisible(true);
        });
		btnSetPrice.setBounds(211, 220, 203, 61);
		contentPane.add(btnSetPrice);
		setLocationRelativeTo(this.getOwner());
	}
}
