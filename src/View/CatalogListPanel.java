package View;

import Models.Newspaper;

import javax.swing.*;
import java.awt.*;

public class CatalogListPanel extends JPanel {


    private JList<Newspaper> list = new JList<>();

    public CatalogListPanel() {
        setLayout(new BorderLayout());
        list = new JList<>();
        add(list, BorderLayout.CENTER);

        setSize(400, 400);

        setVisible(true);
    }

    public Newspaper getSelectedNewspaper() {
        return list.getSelectedValue();
    }

    public void setList(java.util.List<Newspaper> newspapers) {
        list.setListData(newspapers.toArray(new Newspaper[0]));

    }
}
