package View;

import Models.Newspaper;

import javax.swing.*;
import java.util.List;

public class PublishContentDialog extends JPanel {

    private JComboBox<Newspaper> newspaperComboBox = new JComboBox<>();

    private JCheckBox checkBox = new JCheckBox("Is this an ad?");

    public PublishContentDialog(List<Newspaper> newspapers) {
        newspapers.forEach(newspaper -> newspaperComboBox.addItem(newspaper));
        add(newspaperComboBox);
        add(checkBox);
    }

    public Newspaper getSelectedNewspaper() {
        return (Newspaper) newspaperComboBox.getSelectedItem();
    }
    public boolean getIsAd() {
        return checkBox.isSelected();
    }
}
