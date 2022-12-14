package View;

import javax.swing.*;

public class AddNewspaperDialog extends JPanel {


    private JTextField nameField = new JTextField(20);
    private JTextField descriptionField = new JTextField(20);
    private JTextField monthlyPriceField = new JTextField(20);

    public AddNewspaperDialog() {

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Monthly Price:"));
        add(monthlyPriceField);

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
