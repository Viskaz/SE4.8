import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserViewEnergyGUI extends JFrame implements ActionListener
{

    JLabel lblConsumption, lblCost, lblTariff,  lblBudget, lblCostValue, lblTariffValue, lblBudgetValue;
    private JPanel contentPane;
    String consumption,cost,tariff,budget;
    AccountSession session = new AccountSession();
    JLabel lblConsumptionValue;
    JButton btnHistoryBill, btnReturn;
    /**
     * Create the frame.
     */
    public UserViewEnergyGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 419);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblConsumption = new JLabel("Consumption:");
        lblConsumption.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblConsumption.setBounds(43, 59, 180, 34);
        contentPane.add(lblConsumption);

        lblCost = new JLabel("Cost:");
        lblCost.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblCost.setBounds(43, 118, 81, 21);
        contentPane.add(lblCost);

        lblTariff = new JLabel("Tariff:");
        lblTariff.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblTariff.setBounds(43, 174, 81, 21);
        contentPane.add(lblTariff);

        lblBudget = new JLabel("Budget:");
        lblBudget.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblBudget.setBounds(43, 224, 126, 34);
        contentPane.add(lblBudget);

        lblConsumptionValue = new JLabel();
        lblConsumptionValue.setBounds(266, 59, 109, 31);
        contentPane.add(lblConsumptionValue);

        lblCostValue = new JLabel(cost);
        lblCostValue.setBounds(266, 118, 109, 34);
        contentPane.add(lblCostValue);

        lblTariffValue = new JLabel();
        lblTariffValue.setBounds(266, 167, 99, 39);
        contentPane.add(lblTariffValue);

        lblBudgetValue = new JLabel();
        lblBudgetValue.setBounds(266, 224, 109, 34);
        contentPane.add(lblBudgetValue);

        btnHistoryBill = new JButton("History Bill");
        //会有不同的监听
        btnHistoryBill.setBounds(112, 295, 152, 39);
        contentPane.add(btnHistoryBill);

        btnReturn = new JButton("Return");
        /*btnReturn.addActionListener(e -> {
            dispose();
            new UserFunctionGUI().setVisible(true);
        });*/
        btnReturn.setBounds(488, 297, 126, 34);
        contentPane.add(btnReturn);

        setLocationRelativeTo(this.getOwner());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
