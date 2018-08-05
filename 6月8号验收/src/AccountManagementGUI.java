import javax.swing.*;
import java.awt.*;

public class AccountManagementGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AccountManagementGUI() {
		setTitle(" User Account Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblPassword.setBounds(53, 67, 161, 61);
		contentPane.add(lblPassword);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(e -> new ChangePasswordGUI().setVisible(true));
		btnChange.setBounds(241, 78, 123, 39);
		contentPane.add(btnChange);
		
		JLabel lblGasBudget = new JLabel("Gas Budget:");
		lblGasBudget.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblGasBudget.setBounds(51, 154, 143, 49);
		contentPane.add(lblGasBudget);
		
		AccountSession session = new AccountSession();
		JLabel lblGasValue = new JLabel(new UserDAO().readUser(session.getUsername()).getGasBudget());
		lblGasValue.setBounds(263, 168, 101, 35);
		contentPane.add(lblGasValue);
		
		JButton btnChangeGas = new JButton("Change");

		btnChangeGas.setBounds(491, 159, 123, 39);
		contentPane.add(btnChangeGas);
		
		JLabel lblElectricityBudget = new JLabel("Electricity Budget:");
		lblElectricityBudget.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblElectricityBudget.setBounds(53, 218, 187, 54);
		contentPane.add(lblElectricityBudget);
		
		JLabel lblEleValue = new JLabel(new UserDAO().readUser(session.getUsername()).getEleBudget());
		lblEleValue.setBounds(263, 237, 101, 35);
		contentPane.add(lblEleValue);
		
		JButton btnChangeEle = new JButton("Change");
		btnChangeEle.setBounds(491, 226, 123, 39);
		contentPane.add(btnChangeEle);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> {
            dispose();
            new UserFunctionGUI().setVisible(true);
        });
		btnReturn.setBounds(491, 309, 123, 39);
		contentPane.add(btnReturn);

        btnChangeGas.addActionListener(e -> new ChangeBudgetGUI("gas",lblGasValue).setVisible(true));
        btnChangeEle.addActionListener(e -> new ChangeBudgetGUI("electricity",lblEleValue).setVisible(true));

		new InitGUI().initFrame(this);
	}

}
