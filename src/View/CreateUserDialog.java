package View;

import javax.swing.*;
import java.awt.*;

public class CreateUserDialog extends JPanel {

    private final JTextField nameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JRadioButton standardRadioButton = new JRadioButton("Standard");
    private final JRadioButton premiumRadioButton = new JRadioButton("Premium");
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public CreateUserDialog() {
        super();
        setLayout(new GridLayout(4, 2, 4, 8));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Subscription type:"));
        add(new JLabel(""));
        buttonGroup.add(standardRadioButton);
        buttonGroup.add(premiumRadioButton);
        add(standardRadioButton);
        add(premiumRadioButton);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public boolean isPremium() {
        return premiumRadioButton.isSelected();
    }
}
