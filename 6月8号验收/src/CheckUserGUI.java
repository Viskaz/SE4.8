import javax.swing.*;
import java.awt.*;

public class CheckUserGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CheckUserGUI() {
		setTitle("Warning!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 265);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblWarning = new JLabel("Two different password,please input ");
		lblWarning.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblWarning.setBounds(51, 43, 406, 50);
		contentPane.add(lblWarning);
		
		JLabel lblAgain = new JLabel("again!");
		lblAgain.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblAgain.setBounds(51, 84, 111, 39);
		contentPane.add(lblAgain);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(e -> dispose());
		btnOK.setBounds(184, 150, 130, 44);
		contentPane.add(btnOK);
		setLocationRelativeTo(this.getOwner());
	}
}
