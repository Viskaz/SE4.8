import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitGUI extends JFrame implements ActionListener {

    void initFrame(JFrame myFrame) {
        //when trying to call this method, remember to pass this

        //myFrame.setSize(800, 600);
        //myFrame.setLocation(400, 150);
        myFrame.setBounds(100, 100, 636, 419);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(myFrame.getOwner());
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }

    void initDialog(JDialog myDialog) {
        myDialog.setSize(600, 175);
        myDialog.setVisible(true);
        myDialog.setLocationRelativeTo(myDialog.getOwner());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
