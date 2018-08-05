import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class UserHistoryGUI extends JFrame {

    protected static final String String = null;
    //private JPanel contentPane;
    private JPanel contentPaneDay, contentPaneWeek, contentPaneMonth;
    private JTabbedPane tabbedPane;

    //private DefaultListModel listModel;

    /**
     * Create the frame.
     */
    public UserHistoryGUI(String userName, String fileType) {
        setTitle("Welcome," + userName);
        new EnergyConsumeLogic().init(userName, fileType);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Day", null);
        tabbedPane.addTab("Week", null);
        tabbedPane.addTab("Month", null);

        Vector v1 = new Vector(); //v1 holds for the daily bill
        Vector v2 = new Vector(); //v2 holds for the weekly bill
        Vector v3 = new Vector(); //v3 holds for the monthly bill

        UserHistoryLogic myUser = new UserHistoryLogic(userName, fileType);
        myUser.HistoryByDay();
        ArrayList<UserHistoryEntity> myHistoryDay = (ArrayList<UserHistoryEntity>) myUser.returnHistoryList.clone();
        for (UserHistoryEntity temp:myHistoryDay) v1.addElement(temp.toString());

        myUser.HistoryByWeek();
        ArrayList<UserHistoryEntity> myHistoryWeek = (ArrayList<UserHistoryEntity>) myUser.returnHistoryList.clone();
        for (UserHistoryEntity temp:myHistoryWeek) v2.addElement(temp.toString());

        myUser.HistoryByMonth();
        ArrayList<UserHistoryEntity> myHistoryMonth = (ArrayList<UserHistoryEntity>) myUser.returnHistoryList.clone();
        for (UserHistoryEntity temp:myHistoryMonth) v3.addElement(temp.toString());

        JButton btReturn1= new JButton("Return");
        JButton btReturn2 = new JButton("Return");
        JButton btReturn3 = new JButton("Return");

        contentPaneDay = new JPanel();
        JList list1 = new JList(v1);
        JScrollPane myJscrollPane1 = new JScrollPane(list1);
        contentPaneDay.add(myJscrollPane1);
        contentPaneDay.add(btReturn1);
        btReturn1.addActionListener(e -> {
            dispose();
            new UserFunctionGUI().setVisible(true);
        });

        contentPaneWeek = new JPanel();
        JList list2 = new JList(v2);
        JScrollPane myJscrollPane2 = new JScrollPane(list2);
        contentPaneWeek.add(myJscrollPane2);
        contentPaneWeek.add(btReturn2);

        btReturn2.addActionListener(e -> {
            dispose();
            new UserFunctionGUI().setVisible(true);
        });

        contentPaneMonth = new JPanel();
        JList list3 = new JList(v3);
        JScrollPane myJscrollPane3 = new JScrollPane(list3);
        contentPaneMonth.add(myJscrollPane3);
        contentPaneMonth.add(btReturn3);

        btReturn3.addActionListener(e -> {
            dispose();
            new UserFunctionGUI().setVisible(true);
        });

        tabbedPane.setComponentAt(0, contentPaneDay);

        tabbedPane.setComponentAt(1, contentPaneWeek);
        tabbedPane.setComponentAt(2, contentPaneMonth);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 419);

        add(tabbedPane, "Center");
        setLocationRelativeTo(this.getOwner());
    }

}
