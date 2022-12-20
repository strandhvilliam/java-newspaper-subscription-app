package View;

import javax.swing.*;
import java.awt.*;

public class AddNewspaperDialog extends JPanel {

    private JTextField nameField = new JTextField(20);
    private JTextField descriptionField = new JTextField(20);
    private JTextField monthlyPriceField = new JTextField(20);

    public AddNewspaperDialog() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Name:"), c);
        c.gridx = 1;
        add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Description:"), c);
        c.gridx = 1;
        add(descriptionField, c);
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Monthly Price:"), c);
        c.gridx = 1;
        add(monthlyPriceField, c);

    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getDescriptionField() {
        return descriptionField.getText();
    }

    public String getMonthlyPriceField() {
        return monthlyPriceField.getText();
    }
}
