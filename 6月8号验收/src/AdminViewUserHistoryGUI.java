import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminViewUserHistoryGUI extends JFrame implements ActionListener {

    private JPanel historyPanel;
    public AdminViewUserHistoryGUI(String userName, String fileType) {
        new InitGUI().initFrame(this);
        new EnergyConsumeLogic().init(userName, fileType);
        setTitle("The History of " + userName);

        historyPanel = new JPanel();
        historyPanel.setLayout(null);
        setContentPane(historyPanel);

        JButton btnAdd = new JButton("Send");

        btnAdd.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Successfully Sent to the user",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        btnAdd.setBounds(43, 299, 134, 36);
        historyPanel.add(btnAdd);

        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(e -> {
            dispose();
            new AdminViewUserGUI().setVisible(true);
        });
        btnReturn.setBounds(480, 299, 134, 33);
        historyPanel.add(btnReturn);

        JLabel lblName = new JLabel("Month", SwingConstants.CENTER);
        lblName.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setBackground(Color.WHITE);
        lblName.setBounds(43, 48, 134, 36);
        historyPanel.add(lblName);


        JLabel lblDelete = new JLabel("Usage/Bill", SwingConstants.CENTER);
        lblDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblDelete.setForeground(new Color(0, 0, 0));
        lblDelete.setBackground(Color.WHITE);
        lblDelete.setBounds(400, 48, 200, 36);
        historyPanel.add(lblDelete);

        UserHistoryLogic myUser = new UserHistoryLogic(userName, fileType);
        myUser.HistoryByMonth();
        ArrayList<UserHistoryEntity> myHistoryMonth = (ArrayList<UserHistoryEntity>) myUser.returnHistoryList.clone();
        //for (UserHistoryEntity temp:myHistoryMonth) System.out.println(temp.toString());

        for (int i = 0; i < myHistoryMonth.size(); i++) {
            JButton tempButton = new JButton(myHistoryMonth.get(i).getBillType());
            JLabel lblHistory = new JLabel(String.format("%.2f", myHistoryMonth.get(i).getUsage()) + "kwh" +
                    "/" + "£" + String.format("%.2f", myHistoryMonth.get(i).getBill()), SwingConstants.CENTER);

            int j = 35;
            int m = i * j;

            tempButton.setBackground(new Color(220,220,220));
            tempButton.setBounds(43,81+m,134,36);
            historyPanel.add(tempButton);

            lblHistory.setBackground(new Color(220,220,220));
            lblHistory.setBounds(400, 83+m, 200, 36);
            historyPanel.add(lblHistory);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
