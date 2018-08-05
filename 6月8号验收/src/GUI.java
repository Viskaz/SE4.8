import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GUI frame = new GUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btUser = new JButton("User");
		btUser.addActionListener(e -> {
			dispose();
			new UserChooseGUI().setVisible(true);
		});
		btUser.setBounds(81, 157, 180, 92);
		contentPane.add(btUser);
		
		JButton btAdmin = new JButton("Admin");
		btAdmin.addActionListener(e -> {
			dispose();
			new AdminLoginGUI().setVisible(true);
		});
		btAdmin.setBounds(365, 157, 188, 92);
		contentPane.add(btAdmin);

        new InitGUI().initFrame(this);
	}
}
