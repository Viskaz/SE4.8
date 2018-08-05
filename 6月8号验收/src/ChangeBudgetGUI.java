import javax.swing.*;
import java.awt.*;

public class ChangeBudgetGUI extends JFrame {

	private JPanel contentPane;
	static JLabel lblBudget;
    static String fileType;
    private JTextField textFieldChangeValue;
    
    
    private void setFileType(String fileType) {
		ChangeBudgetGUI.fileType = fileType;
		
	}
	
	private String getFileType() { return fileType; }

	private void setLabelBudget(JLabel lblBudget) {
        ChangeBudgetGUI.lblBudget=lblBudget;
		
	}
	
	private JLabel getLabelBudget() {
		return lblBudget;
	}

	/**
	 * Create the frame.
	 */
	public ChangeBudgetGUI(String fileType, JLabel lblBudget) {
		setTitle(" User Account Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		setLabelBudget(lblBudget);
        setFileType(fileType);
        
       
        AccountSession session = new AccountSession();
        switch (fileType) {
            case "electricity":
            	textFieldChangeValue = new JTextField(new UserDAO().readUser(session.getUsername()).getEleBudget());
            	textFieldChangeValue.setBounds(115, 100, 146, 27);
        		contentPane.add(textFieldChangeValue);
        		textFieldChangeValue.setColumns(10);
                break;
            case "gas":
            	textFieldChangeValue = new JTextField(new UserDAO().readUser(session.getUsername()).getGasBudget());
            	textFieldChangeValue.setBounds(115, 100, 146, 27);
        		contentPane.add(textFieldChangeValue);
        		textFieldChangeValue.setColumns(10);
                break;
            default:
                System.out.println("fileType wrong!!");
        }
		
		JLabel lblRemind = new JLabel("Please change the " + fileType + " budget:");
		lblRemind.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		lblRemind.setBounds(15, 15, 515, 76);
		contentPane.add(lblRemind);

        JComboBox<String> budgetType = new JComboBox<>();
        budgetType.addItem("£");
        budgetType.addItem("kwh");
        budgetType.setBounds(300, 100, 50, 27);
        contentPane.add(budgetType);

		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(e -> {
			String changeValue = textFieldChangeValue.getText();
			String setBudgetType = (String) budgetType.getSelectedItem();
			String setValue = changeValue + setBudgetType;
			if (new CheckInput().checkSetPrice(changeValue)) {
                switch (fileType) {
                    case "electricity":
                        new UserDAO().updateBudget("electricity", new AccountSession().getUsername(), setValue);
                        getLabelBudget().setText(textFieldChangeValue.getText());
                        dispose();
                        break;
                    case "gas":
                        new UserDAO().updateBudget("gas", new AccountSession().getUsername(), setValue);
                        getLabelBudget().setText(textFieldChangeValue.getText());
                        dispose();
                        break;
                }
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Wrong Input",
                        "Wrong", JOptionPane.ERROR_MESSAGE);
            }
		});
		btnChange.setBounds(37, 176, 123, 29);
		contentPane.add(btnChange);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		btnCancel.setBounds(235, 176, 123, 29);
		contentPane.add(btnCancel);
        setLocationRelativeTo(this.getOwner());
	}
}

	