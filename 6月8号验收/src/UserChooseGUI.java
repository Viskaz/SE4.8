import com.sun.java.accessibility.util.GUIInitializedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserChooseGUI extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserChooseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(e -> {
			dispose();
			new UserRegisterGUI().setVisible(true);
		});
		btnRegister.setBounds(230, 92, 200, 55);
		contentPane.add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			dispose();
			new UserLoginGUI().setVisible(true);
		});
		btnLogin.setBounds(230, 226, 200, 55);
		contentPane.add(btnLogin);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> {
			dispose();
			new GUI().setVisible(true);
		});
		btnReturn.setBounds(491, 311, 123, 37);
		contentPane.add(btnReturn);

        setLocationRelativeTo(this.getOwner());
	}
}
