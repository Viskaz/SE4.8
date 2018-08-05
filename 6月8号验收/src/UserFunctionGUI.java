import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserFunctionGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserFunctionGUI() {
	    //在这里加红绿灯！！！
        //加了红绿灯以后需要判断
		AccountSession accountSession = new AccountSession();
		String userName = accountSession.getUsername();

		new EnergyConsumeLogic().init(userName, "ele");
        new EnergyConsumeLogic().init(userName, "gas");

        setTitle(userName + " welcome!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnAccountManagement = new JButton("Account Management");
		btnAccountManagement.addActionListener(e -> {
			dispose();
			new AccountManagementGUI().setVisible(true);
		});

		btnAccountManagement.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnAccountManagement.setBounds(220, 53, 240, 49);
		contentPane.add(btnAccountManagement);
		
		JButton btnViewElectricity = new JButton("View Electricity");
		btnViewElectricity.addActionListener(e -> {
			dispose();
			//new UserViewEnergyGUI("ele");
            new UserViewEleGUI().setVisible(true);
		});

		btnViewElectricity.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnViewElectricity.setBounds(220, 153, 240, 49);
		contentPane.add(btnViewElectricity);
		
		JButton btnViewGas = new JButton("View Gas");
		btnViewGas.addActionListener(e -> {
			dispose();
			//new UserViewEnergyGUI("gas");
            new UserViewGasGUI().setVisible(true);
		});

		btnViewGas.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnViewGas.setBounds(220, 250, 240, 49);
		contentPane.add(btnViewGas);
		
		JButton btnReturnButton = new JButton("Return");
		btnReturnButton.addActionListener(e -> {
             dispose();
             new UserLoginGUI().setVisible(true);
        });
		btnReturnButton.setBounds(491, 319, 123, 39);
		contentPane.add(btnReturnButton);

        JLabel lblEleLED = new JLabel("");
        lblEleLED.setOpaque(true);
        lblEleLED.setBackground(Color.GREEN);
        lblEleLED.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblEleLED.setBounds(475, 158, 36, 33);
        contentPane.add(lblEleLED);

        JLabel lblGasLED = new JLabel("");
        lblGasLED.setOpaque(true);
        lblGasLED.setBackground(Color.GREEN);
        lblGasLED.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblGasLED.setBounds(475, 259, 36, 33);
        contentPane.add(lblGasLED);

		new InitGUI().initFrame(this);
        setLocationRelativeTo(this.getOwner());
        String sentMeters = AdminLogic.checkReceiveMeters(userName);
        if (sentMeters.equals("-1")) {
            JOptionPane.showMessageDialog(this,
                    "It's the Bill day! Already sent the meters to the Provider",
                    "Caution", JOptionPane.WARNING_MESSAGE);
        }
        else {
            if (sentMeters.equals("0")) {
                System.out.println("Not the Sent day!");
            }
            else {
                new UserSentMetersGUI(sentMeters).setVisible(true);
            }
        }

        boolean eleStatus = checkEnergyStatus(userName, "ele");
        boolean gasStatus = checkEnergyStatus(userName, "gas");

        if (eleStatus)
            lblEleLED.setBackground(Color.GREEN);
        else
            lblEleLED.setBackground(Color.RED);

        if (gasStatus)
            lblGasLED.setBackground(Color.GREEN);
        else
            lblGasLED.setBackground(Color.RED);
	}

    /**
     * @param userName the given username
     * @param fileType "ele" or "gas"
     * @return whether the usage or bill exceeds the budget
     */
	private static boolean checkEnergyStatus(String userName, String fileType) {
        UserHistoryLogic userHistoryLogic = new UserHistoryLogic(userName, fileType);
        userHistoryLogic.HistoryByDay();
        ArrayList<UserHistoryEntity> myHistoryList = (ArrayList<UserHistoryEntity>) userHistoryLogic.returnHistoryList.clone();
        UserHistoryEntity lastDayRecord = myHistoryList.get(myHistoryList.size() - 1);
        MyDate nowDate = new MyDate();
        String nowDateString = nowDate.getYear() + "-" + nowDate.getMonth() + "-" + nowDate.getDate();
        String consumption, cost;
        UserEntity currentUser = new UserDAO().readUser(userName);
        String currentBudget = null;
        if (fileType.equals("ele")) {
            currentBudget = currentUser.getEleBudget();
        }
        else if (fileType.equals("gas")) {
            currentBudget = currentUser.getGasBudget();
        }

        if (nowDateString.equals(lastDayRecord.getBillType())) {
            //说明是今天还有一些记录，那么直接读取就可以了
            consumption = String.valueOf(lastDayRecord.getUsage());
            cost = String.valueOf(lastDayRecord.getBill());
        }
        else {
            //从0开始的一天
            consumption = "0";
            cost = "0";
        }

        if (isCost(currentBudget)) {
            //is the format of pound
            if (Double.parseDouble(currentBudget.substring(0, currentBudget.indexOf('£'))) >= Double.parseDouble(cost))
                return true;
            else
                return false;
        }
        else {
            if (Double.parseDouble(currentBudget.substring(0, currentBudget.indexOf("k"))) >= Double.parseDouble(consumption))
                return true;
            else
                return false;
        }
    }

    private static boolean isCost(String str) {
	    if (str.indexOf('£')==-1) return false; else return true;
    }
}
