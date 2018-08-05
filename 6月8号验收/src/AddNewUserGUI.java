import javax.swing.*;
import java.awt.*;

public class AddNewUserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;

	/**
	 * Create the frame.
	 */
	public AddNewUserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblAddUser = new JLabel("Add User:");
		lblAddUser.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		lblAddUser.setBounds(43, 68, 124, 36);
		contentPane.add(lblAddUser);
		
		JLabel lblUserName = new JLabel("Input Username:");
		lblUserName.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblUserName.setBounds(43, 147, 214, 81);
		contentPane.add(lblUserName);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(e -> {
            String username= textFieldUserName.getText();
            System.out.println(username);

          	UserEntity user = new UserEntity(username,"00000","100","100");
          	new AdminLogic().addUser(user);
          	dispose();
          	new AdminViewUserGUI().setVisible(true);
        });
		btnConfirm.setBounds(189, 288, 157, 46);
		contentPane.add(btnConfirm);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(272, 172, 214, 36);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> new AdminViewUserGUI().setVisible(true));
		btnReturn.setBounds(490, 288, 124, 46);
		contentPane.add(btnReturn);
        setLocationRelativeTo(this.getOwner());
	}
}
