import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSentMetersGUI extends JDialog implements ActionListener {
    private static final String[] zeros = {"", "0", "00", "000", "0000"};
    private static String transform4_digit(String str) {
        return (zeros[4-str.length()] + str);
    }

    public UserSentMetersGUI(String meters) {
        String eleValue = transform4_digit(meters.split("#")[0]);
        String gasValue = transform4_digit(meters.split("#")[1]);
        lbEle = new JLabel();
        lbGas = new JLabel();
        btOkay = new JButton();
        lbEleValue = new JLabel();
        lbGasValue = new JLabel();
        lbMessage = new JLabel();

        //======== this ========
        setTitle("IMPORTANT");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- lbAccountMessage ----
        lbEle.setText("Your last Month Electricity Meters:");
        lbEle.setHorizontalAlignment(SwingConstants.CENTER);
        lbEle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(lbEle);
        lbEle.setBounds(5, 40, 250, 25);

        //---- lbPINMessage ----
        lbGas.setText("Your last Month Gas Meters:");
        lbGas.setHorizontalAlignment(SwingConstants.CENTER);
        lbGas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(lbGas);
        lbGas.setBounds(5, 64, 250, 25);

        //---- btOkay ----
        btOkay.setText("Okay!");
        contentPane.add(btOkay);
        btOkay.setBounds(new Rectangle(new Point(285, 105), btOkay.getPreferredSize()));

        //---- lbAccount ----
        lbEleValue.setText(eleValue);
        lbEleValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lbEleValue);
        lbEleValue.setBounds(406, 40, 185, 25);

        //---- lbPIN ----
        lbGasValue.setText(gasValue);
        lbGasValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lbGasValue);
        lbGasValue.setBounds(405, 65, 185, 25);

        //---- lbMessage ----
        lbMessage.setText("It's meter day! The following message is sending to the energy provider!");
        lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbMessage.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        contentPane.add(lbMessage);
        lbMessage.setBounds(20, 5, 600, lbMessage.getPreferredSize().height);

/*        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }*/

        setSize(650, 175);
        setLocationRelativeTo(getOwner());

        btOkay.addActionListener(e -> {
            dispose();
        });
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lbEle;
    private JLabel lbGas;
    private JButton btOkay;
    private JLabel lbEleValue;
    private JLabel lbGasValue;
    private JLabel lbMessage;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
