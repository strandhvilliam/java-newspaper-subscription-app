package View;

import Models.Newspaper;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class CatalogListPanel extends JPanel {


    private final DefaultListModel<Newspaper> unsubbedListModel = new DefaultListModel<>();
    private final DefaultListModel<Newspaper> subbedListModel = new DefaultListModel<>();
    private final JList<Newspaper> unsubbedList = new JList<>();
    private final JList<Newspaper> subbedList = new JList<>();

    private final JScrollPane scrollPane1 = new JScrollPane(unsubbedList);
    private final JScrollPane scrollPane2 = new JScrollPane(subbedList);

    private final JPanel buttonPanel = new JPanel();
    private final JLabel unsubbedLabel = new JLabel("Available Newspapers");
    private final JLabel subbedLabel = new JLabel("Subscribed Newspapers");

    private final JButton subscribeBtn = new JButton("Subscribe");
    private final JButton unsubscribeBtn = new JButton("Unsubscribe");

    private JPanel listPanel = new JPanel();

    public CatalogListPanel(List<Newspaper> subbedNewspapers, List<Newspaper> unsubbedNewspapers) {
        subbedListModel.addAll(subbedNewspapers);
        unsubbedListModel.addAll(unsubbedNewspapers);
        unsubbedList.setModel(unsubbedListModel);
        subbedList.setModel(subbedListModel);

        setLayout(new BorderLayout());
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(0, 0, 10, 0);
        listPanel.add(unsubbedLabel, c);
        c.gridx = 2;
        listPanel.add(subbedLabel, c);
        c.gridy = 1;
        scrollPane1.setPreferredSize(new Dimension(200, 200));
        scrollPane2.setPreferredSize(new Dimension(200, 200));

        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        subscribeBtn.addActionListener(e -> {
            List<Newspaper> selected = unsubbedList.getSelectedValuesList();
            for (Newspaper n : selected) {
                subbedListModel.addElement(n);
            }
        });
        buttonPanel.add(subscribeBtn);

        unsubscribeBtn.addActionListener(e -> {
            List<Newspaper> selected = subbedList.getSelectedValuesList();
            for (Newspaper n : selected) {
                subbedListModel.removeElement(n);
            }
        });
        buttonPanel.add(unsubscribeBtn);

        c.gridx = 0;
        listPanel.add(scrollPane1, c);
        c.gridx = 1;
        listPanel.add(buttonPanel, c);
        c.gridx = 2;
        listPanel.add(scrollPane2, c);
        add(listPanel, BorderLayout.CENTER);

        setSize(400, 400);

        setVisible(true);
    }

    public List<Newspaper> getSubbedNewspapers() {
        return Collections.list(subbedListModel.elements());
    }

}
