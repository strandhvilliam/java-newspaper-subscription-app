package View;

import javax.swing.*;

public class PayPanel extends JPanel {
    private final JLabel amountLabel = new JLabel("Amount: ");
    private final JTextField amountField = new JTextField();

    public PayPanel(double amount) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        amountLabel.setText("Amount: " + amount);
        add(amountLabel);
        add(amountField);
    }
}
