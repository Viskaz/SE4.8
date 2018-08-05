import javax.swing.*;
import java.awt.*;

public class SetPriceDialog extends JFrame {

	private JPanel ChangeGas;

    public JFrame getFrame() {
        return getFrame();
    }

    public JLabel getLabelPriceLast() {
        return getLabelPriceLast();
    }

    public void setLabelPriceLast(JLabel labelPriceLast) {
    }
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        SetPriceDialog.fileType = fileType;
    }
    
    private static String fileType;
    private JTextField PriceField;

    /**
     * @param frame the containing frame
     * @param fileType "ele" or "gas"
     * @param labelPriceLast the label containing the price information
     */
	public SetPriceDialog(JFrame frame,String fileType,JLabel labelPriceLast) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 356);
		ChangeGas = new JPanel();
		ChangeGas.setLayout(null);
		setContentPane(ChangeGas);
		
		 setFileType(fileType);
	     //setFrame(frame);
	     setLabelPriceLast(labelPriceLast);
		
		JLabel lblNewLabel = new JLabel("Please change the "+fileType+" price or click on cancel:");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblNewLabel.setBounds(15, 54, 542, 67);
		ChangeGas.add(lblNewLabel);
		
		PriceField = new JTextField();
		PriceField.setBounds(214, 136, 136, 47);
		ChangeGas.add(PriceField);
		PriceField.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(e -> {

        String priceString = PriceField.getText();
            if (new CheckInput().checkPositiveDouble(priceString)) {
                //System.out.println("Okay");
                new PriceLogic().writePrice(getFileType(), priceString);
                labelPriceLast.setText(priceString);
                dispose();
            }
            else {
                new CheckPriceGUI().setVisible(true);
            }

        });

		btnOkay.setBounds(83, 226, 147, 40);
		ChangeGas.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		btnCancel.setBounds(367, 226, 147, 40);
		ChangeGas.add(btnCancel);

        setLocationRelativeTo(this.getOwner());
	}
}
