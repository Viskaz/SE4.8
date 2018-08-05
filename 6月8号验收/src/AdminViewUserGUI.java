import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class AdminViewUserGUI extends JFrame {
    ArrayList<JButton> btUserList = new ArrayList<>();

    JPanel userPanel;

    /**
     * Create the frame.
     */
    public AdminViewUserGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 419);
        userPanel = new JPanel();
        userPanel.setLayout(null);
        setContentPane(userPanel);

        JLabel lblName = new JLabel("               Name");
        lblName.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setBackground(Color.WHITE);
        lblName.setBounds(43, 48, 134, 36);
        userPanel.add(lblName);

        JButton btnAdd = new JButton("Add");

        btnAdd.addActionListener(e -> {
            dispose();
            new AddNewUserGUI().setVisible(true);
        });

        btnAdd.setBounds(43, 299, 134, 36);
        userPanel.add(btnAdd);

        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(e -> {
            dispose();
            new AdminFunctionGUI().setVisible(true);
        });
        btnReturn.setBounds(480, 299, 134, 33);
        userPanel.add(btnReturn);

        JLabel lblDelete = new JLabel("       Option");
        lblDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblDelete.setForeground(new Color(0, 0, 0));
        lblDelete.setBackground(Color.WHITE);
        lblDelete.setBounds(464, 48, 81, 36);
        userPanel.add(lblDelete);

        JLabel lblGas = new JLabel("           Gas");
        lblGas.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblGas.setForeground(new Color(0, 0, 0));
        lblGas.setBackground(Color.WHITE);
        lblGas.setBounds(175, 48, 95, 36);
        userPanel.add(lblGas);

        JLabel lblEle = new JLabel("        Electricity");
        lblEle.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblEle.setForeground(new Color(0, 0, 0));
        lblEle.setBackground(Color.WHITE);
        lblEle.setBounds(268, 48, 114, 36);
        userPanel.add(lblEle);

        String[] userNameList = new UserDAO().getUserNameList();

        for (int i = 0; i < userNameList.length; i++){
            int index= i;
            JButton tempButton = new JButton(userNameList[i]);
            JButton btnDelete = new JButton("Delete");
            JButton btnViewEle = new JButton("View");
            JButton btnViewGas = new JButton("View");

            int finalI = i;
            int j = 35;
            if(finalI < 5) {
                int m = i*j;
                /*tempButton.addActionListener(e -> {
                    new AdminViewUserHistoryGUI(userNameList[finalI]).setVisible(true);
                    //new UserHistoryGUI(userNameList[finalI]).setVisible(true)
                });*/

                tempButton.setBackground(new Color(220,220,220));
                tempButton.setBounds(43,81 + m,134,36);
                userPanel.add(tempButton);
                btUserList.add(tempButton);
                userPanel.add(btUserList.get(i));

                btnDelete.setBackground(new Color(220,220,220));
                btnDelete.setBounds(464, 83 + m, 81, 36);
                userPanel.add(btnDelete);

                btnViewEle.setBackground(new Color(220,220,220));
                btnViewEle.setBounds(268, 83+m, 114, 36);
                userPanel.add(btnViewEle);

                btnViewGas.setBackground(new Color(220,220,220));
                btnViewGas.setBounds(175, 83+m, 95, 36);
                userPanel.add(btnViewGas);

                btnViewEle.addActionListener(e -> {
                    new AdminViewUserHistoryGUI(userNameList[finalI], "ele").setVisible(true);
                });

                btnViewGas.addActionListener(e -> {
                    new AdminViewUserHistoryGUI(userNameList[finalI], "gas").setVisible(true);
                });

                btnDelete.addActionListener(e -> {
                    dispose();
                    new AdminLogic().deleteUser(userNameList[index]);
                    new AdminViewUserGUI().setVisible(true);
                });
            }
        }
        setLocationRelativeTo(this.getOwner());
    }
}