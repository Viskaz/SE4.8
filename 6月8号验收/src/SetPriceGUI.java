import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPriceGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */


	public SetPriceGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 419);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lbEle = new JLabel("Electric:");
		lbEle.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		lbEle.setBounds(15, 112, 152, 45);
		contentPane.add(lbEle);
		
		JLabel lbElePrice = new JLabel(String.valueOf(new PriceLogic().readPrice("Ele")));
		lbElePrice.setBounds(209, 112, 95, 40);
		contentPane.add(lbElePrice);
		
		JLabel lbGasPrice = new JLabel(String.valueOf(new PriceLogic().readPrice("Gas")));
		lbGasPrice.setBounds(209, 246, 81, 21);
		contentPane.add(lbGasPrice);
		
		JLabel lblGas = new JLabel("Gas:");
		lblGas.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		lblGas.setBounds(15, 246, 81, 21);
		contentPane.add(lblGas);
		
		
		JButton btnChangeGas = new JButton("Change");
		btnChangeGas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SetPriceDialog(SetPriceGUI.this,"gas",lbGasPrice).setVisible(true);
			}

			private JLabel getGasPriceLabel() {
				// TODO Auto-generated method stub
				return lbGasPrice;
			}
		});

		
		btnChangeGas.setBounds(377, 229, 100, 50);
		contentPane.add(btnChangeGas);
		
		JButton btnChangeEle = new JButton("Change");
		btnChangeEle.addActionListener(e -> new SetPriceDialog(SetPriceGUI.this,"ele",lbElePrice).setVisible(true));
		btnChangeEle.setBounds(377, 113, 100, 50);
		contentPane.add(btnChangeEle);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(e -> {
			 dispose();
			 new AdminFunctionGUI().setVisible(true);;
		});
		btnReturn.setBounds(511, 308, 123, 40);
		contentPane.add(btnReturn);
		setLocationRelativeTo(this.getOwner());
	}
}
