import javax.swing.*;
import java.awt.*;

public class CheckPriceGUI extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public CheckPriceGUI() {
		setTitle("Warning!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 254);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblWarning = new JLabel("Invalid input,please set again!");
		lblWarning.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblWarning.setBounds(89, 52, 383, 36);
		contentPane.add(lblWarning);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(e -> dispose());
		btnNewButton.setBounds(194, 147, 115, 36);
		contentPane.add(btnNewButton);
        setLocationRelativeTo(this.getOwner());
	}

}
