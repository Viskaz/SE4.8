import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public AdminLoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lbwelcome = new JLabel("Welcome,Admin!");
		lbwelcome.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		lbwelcome.setBounds(42, 30, 216, 60);
		contentPane.add(lbwelcome);
		
		JLabel lbUsername = new JLabel("UserName:");
		lbUsername.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lbUsername.setBounds(42, 128, 113, 44);
		contentPane.add(lbUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(185, 130, 122, 44);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lbPassword.setBounds(42, 217, 105, 50);
		contentPane.add(lbPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(185, 217, 122, 44);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btLogin = new JButton("Login");
		btLogin.addActionListener(e -> {

			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());
			boolean flag = LoginLogic.checkPassword(username, password, "admin");
			if (flag) {
				dispose();
				new AdminFunctionGUI().setVisible(true);
			}
			else {
                JOptionPane.showMessageDialog(this,
                        "Wrong Username or Password",
                        "Caution", JOptionPane.ERROR_MESSAGE);
			}
		});
		btLogin.setBounds(119, 304, 152, 44);
		contentPane.add(btLogin);
		
		JButton btReturm = new JButton("Return");
		btReturm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GUI().setVisible(true);
			}
		});
		btReturm.setBounds(465, 304, 152, 44);
		contentPane.add(btReturm);
        setLocationRelativeTo(this.getOwner());
	}
}
