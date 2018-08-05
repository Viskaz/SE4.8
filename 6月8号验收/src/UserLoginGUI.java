import javax.swing.*;
import java.awt.*;

public class UserLoginGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserLoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblUserLogin = new JLabel("User Login:");
		lblUserLogin.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		lblUserLogin.setBounds(42, 30, 216, 60);
		contentPane.add(lblUserLogin);
		
		JLabel lbUsername = new JLabel("UserName:");
		lbUsername.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lbUsername.setBounds(42, 128, 113, 44);
		contentPane.add(lbUsername);
		
		JTextField usernameField = new JTextField();
		usernameField.setBounds(185, 130, 122, 44);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lbPassword.setBounds(42, 217, 105, 50);
		contentPane.add(lbPassword);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(185, 217, 122, 44);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());

			boolean flag = LoginLogic.checkPassword(username, password, "user");
			if (flag) {
				dispose();
				new UserFunctionGUI().setVisible(true);
			}
			else {
                JOptionPane.showMessageDialog(this,
                        "Wrong Username or Password",
                        "Caution", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnLogin.setBounds(119, 304, 152, 44);
		contentPane.add(btnLogin);
		
		JButton btReturn = new JButton("Return");
		btReturn.addActionListener(e -> {
            dispose();
            new GUI().setVisible(true);
        });
		btReturn.setBounds(465, 304, 152, 44);
		contentPane.add(btReturn);

		new InitGUI().initFrame(this);
	}
}
