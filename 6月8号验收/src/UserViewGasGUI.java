import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserViewGasGUI extends UserViewEnergyGUI{
    public UserViewGasGUI() {
        super();
        UserHistoryLogic myGas = new UserHistoryLogic(session.getUsername(), "gas");
        myGas.HistoryByDay();
        ArrayList<UserHistoryEntity> myHistoryList = (ArrayList<UserHistoryEntity>) myGas.returnHistoryList.clone();
        UserHistoryEntity lastDayRecord = myHistoryList.get(myHistoryList.size() - 1);
        MyDate nowDate = new MyDate();
        String nowDateString = nowDate.getYear() + "-" + nowDate.getMonth() + "-" + nowDate.getDate();

        double tempOriginalUsage = 0.0;
        double tempOriginalCost = 0.0;

        if (nowDateString.equals(lastDayRecord.getBillType())) {
            //说明是今天还有一些记录，那么直接读取就可以了
            consumption = String.valueOf(lastDayRecord.getUsage());
            cost = String.valueOf(lastDayRecord.getBill());

            tempOriginalUsage = lastDayRecord.getUsage();
            tempOriginalCost = lastDayRecord.getBill();
        }
        else {
            //从0开始的一天
            consumption = "0";
            cost = "0";
        }
        final double originalCost = tempOriginalCost;
        final double originalUsage = tempOriginalUsage;

        ActionListener listener = new ActionListener() {
            double temEnergy;
            @Override
            public void actionPerformed(ActionEvent e) {
                temEnergy = Math.random() * 3;
                consumption = String.valueOf(Double.parseDouble(consumption) + temEnergy);
                cost = String.valueOf(Double.parseDouble(cost) + temEnergy * Double.parseDouble(tariff));
                lblConsumptionValue.setText(String.format("%.2f", Double.parseDouble(consumption)) + "   kwh");
                lblCostValue.setText("£  " + String.format("%.2f", Double.parseDouble(cost)));
            }
        };

        Timer timer = new Timer(1800000, listener);
        timer.start();

        lblConsumptionValue.setText(consumption);
        lblCostValue.setText(cost);

        tariff=Double.toString(new PriceLogic().readPrice("ele"));
        budget= new UserDAO().readUser(session.getUsername()).getEleBudget();
        lblBudgetValue.setText(budget);
        lblTariffValue.setText("£" + tariff + "per kwh");

        btnHistoryBill.addActionListener(e -> {
            new UserHistoryGUI(session.getUsername(), "gas").setVisible(true);
            dispose();
            //一会儿再说
        });

        btnReturn.addActionListener(e -> {
            MyDate x = new MyDate();
            double usage = Double.parseDouble(consumption) - originalUsage;
            double bill = Double.parseDouble(cost) - originalCost;
            EnergyConsumeEntity energyConsumeEntity = new EnergyConsumeEntity(
                    usage, x.getYear(), x.getMonth(), x.getDate(), x.getHour(), x.getMinute(), x.getSecond(), bill
            );

            UserDAO.writeCurrentFile(session.getUsername(), "gas", energyConsumeEntity);

            dispose();
            new UserFunctionGUI().setVisible(true);
        });
    }
}
