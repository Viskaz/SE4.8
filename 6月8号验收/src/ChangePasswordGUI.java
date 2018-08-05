import javax.swing.*;
import java.awt.*;

public class ChangePasswordGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField jpOriginalPassword;
	private JLabel lblNewPassword;
	private JPasswordField jpNewPassword;
	private JLabel lblConfirmPassword;
	private JPasswordField jpConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                ChangePasswordGUI frame = new ChangePasswordGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public ChangePasswordGUI() {
		setTitle("Update the Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblOriginalPassword = new JLabel("Please input your original password:");
		lblOriginalPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblOriginalPassword.setBounds(15, 51, 360, 40);
		contentPane.add(lblOriginalPassword);
		
		jpOriginalPassword = new JPasswordField();
		jpOriginalPassword.setBounds(449, 60, 150, 27);
		contentPane.add(jpOriginalPassword);
		jpOriginalPassword.setColumns(10);
		
		lblNewPassword = new JLabel("Please input the new password:");
		lblNewPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblNewPassword.setBounds(15, 128, 389, 27);
		contentPane.add(lblNewPassword);
		
		jpNewPassword = new JPasswordField();
		jpNewPassword.setBounds(449, 130, 150, 27);
		contentPane.add(jpNewPassword);
		jpNewPassword.setColumns(10);
		
		lblConfirmPassword = new JLabel("Please input the new password again:");
		lblConfirmPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblConfirmPassword.setBounds(15, 202, 360, 21);
		contentPane.add(lblConfirmPassword);
		
		jpConfirmPassword = new JPasswordField();
		jpConfirmPassword.setBounds(449, 201, 150, 27);
		contentPane.add(jpConfirmPassword);
		jpConfirmPassword.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(e -> {
			AccountSession session = new AccountSession();
			String originalPassword = String.valueOf(jpOriginalPassword.getPassword());
			String newPassword = String.valueOf(jpNewPassword.getPassword());
			String confirmPassword = String.valueOf(jpConfirmPassword.getPassword());

			if (newPassword.equals(originalPassword)) {
				lblNewPassword.setText("Repetitive Password!Please try another!");
			}
			else {
			if ((session.changePasswordCheck(originalPassword)) && (newPassword.equals(confirmPassword))) {
				System.out.println("correct");
				dispose();
				System.out.println(originalPassword + " "+ newPassword + " " + confirmPassword );

				new UserDAO().updatePassword(newPassword, "user", new AccountSession().getUsername());


			}
			if (!session.changePasswordCheck(originalPassword)) {
				lblOriginalPassword.setText("Wrong! Please input Again!");
			}
			if (!newPassword.equals(confirmPassword)) {
				lblConfirmPassword.setText("Two different input, please input again!");
			}
		}
		});

		btnConfirm.setBounds(94, 256, 123, 29);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		btnCancel.setBounds(319, 256, 123, 29);
		contentPane.add(btnCancel);

		new InitGUI().initFrame(this);
	}
}
