import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UserRegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JLabel lblConfirmPassword;

	JPasswordField textFieldPassword;
	JPasswordField textFieldConpassword;
	private JButton btnRegister;
	private JButton btnReturn;

	/**
	 * Create the frame.
	 */
	public UserRegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblUsername.setBounds(43, 72, 193, 37);
		contentPane.add(lblUsername);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(315, 79, 163, 37);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblPassword.setBounds(43, 150, 193, 37);
		contentPane.add(lblPassword);



		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(315, 156, 163, 30);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblConfirmPassword.setBounds(43, 237, 213, 37);
		contentPane.add(lblConfirmPassword);
		
		textFieldConpassword = new JPasswordField();
		textFieldConpassword.setBounds(315, 243, 163, 30);
		contentPane.add(textFieldConpassword);
		textFieldConpassword.setColumns(10);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(e -> {
			String username = String.valueOf(textFieldUserName.getText());
			String password = String.valueOf(textFieldPassword.getPassword());
			String conpassword = String.valueOf(textFieldConpassword.getPassword());
			Boolean flagrepeat = false;
			Boolean flagcreating = false;
			flagrepeat = CheckInput.checkPasswordSetting(password , conpassword);
//			JOptionPane.showMessageDialog(this,
//					"Wrong Username or Password",
//					"Caution", JOptionPane.ERROR_MESSAGE);
			File myFolder = new File("./user" ,username);
			if(myFolder.exists()){
				JOptionPane.showMessageDialog(this,
					"This name is already used",
					"Caution", JOptionPane.ERROR_MESSAGE);

			}
			if (flagrepeat == true) {
				flagcreating = RegisterLogic.createUser(username, password, "user");
				if (flagcreating == true){
					//System.out.println("You have successfully created a new account!");
					dispose();
					new UserLoginGUI().setVisible(true);
				}
			}
			else{
				new CheckUserGUI().setVisible(true);
			}
		});
		btnRegister.setBounds(206, 309, 146, 41);
		contentPane.add(btnRegister);
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> {
			dispose();
			new UserChooseGUI().setVisible(true);
		});
		btnReturn.setBounds(482, 309, 131, 39);
		contentPane.add(btnReturn);
		setLocationRelativeTo(this.getOwner());
	}
}
