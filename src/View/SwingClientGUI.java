package View;

import Controllers.UserController;
import Models.Newspaper;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SwingClientGUI extends JPanel implements ClientGUI {


    private final JPanel topPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel monthlyCostPanel = new JPanel();
    private final JTextPane clientTextPane = new JTextPane();
    private final JScrollPane clientScrollPane = new JScrollPane(clientTextPane);
    private final JButton paySubscriptionBtn = new JButton("Pay Subscription");
    private final JButton showCatalogBtn = new JButton("Show Catalog");

    private final JButton memberTypeBtn = new JButton("Member Type");
    private final JLabel montlyCostLabel = new JLabel("Price:");
    private final JLabel montlyCostAmount = new JLabel("100.22");
    private final JLabel appNameLabel = new JLabel("MyNewspaperReader");

    private UserController userController;

    public SwingClientGUI() {
        montlyCostAmount.setFont(new Font("sans-serif", Font.BOLD, 14));
        monthlyCostPanel.add(montlyCostLabel);
        monthlyCostPanel.add(montlyCostAmount);

        showCatalogBtn.setPreferredSize(new Dimension(160, 30));


        paySubscriptionBtn.setPreferredSize(new Dimension(160, 30));
        memberTypeBtn.setPreferredSize(new Dimension(160, 30));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(showCatalogBtn, c);
        c.gridheight = 1;
        c.gridy = 1;
        buttonPanel.add(paySubscriptionBtn, c);
        c.gridy = 0;
        c.gridx = 1;
        buttonPanel.add(monthlyCostPanel, c);
        c.gridy = 1;
        buttonPanel.add(memberTypeBtn, c);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        topPanel.setLayout(new GridLayout());
        appNameLabel.setFont(new Font("sans-serif", Font.BOLD, 24));
        c.weightx = 0.75;
        topPanel.add(appNameLabel);
        c.weightx = 0.25;
        topPanel.add(buttonPanel);
        topPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 0));

        clientTextPane.setEditable(false);
        clientTextPane.setContentType("text/html");
        clientTextPane.setBackground(new Color(70, 73, 75));
        clientTextPane.setBorder(BorderFactory.createLineBorder(new Color(97, 99, 100)));
        clientScrollPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        paySubscriptionBtn.addActionListener(e -> {
            showPaymentWindow();
        });
        showCatalogBtn.addActionListener(e -> {
            showCatalogWindow();
        });
        memberTypeBtn.addActionListener(e -> userController.changePayBehavior());

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(clientScrollPane, BorderLayout.CENTER);
    }

    public void updateArticleFeed(List<String> articles) {
        try {
            StringBuilder sb = new StringBuilder();
            articles.forEach(a -> sb.append(a));
            System.out.println(sb.toString());
            HTMLDocument doc = (HTMLDocument) clientTextPane.getStyledDocument();
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), sb.toString());
            System.out.println("Updated article feed");

        } catch (BadLocationException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setGUIController(UserController userController) {
        this.userController = userController;
    }

    public void showCatalogWindow() {
        List<Newspaper> subbed = userController.getSubbedNewspapers();
        List<Newspaper> unsubbed = userController.getUnsubbedNewspapers();
        CatalogListPanel panel =
                new CatalogListPanel(subbed, unsubbed);

        int option = JOptionPane.showConfirmDialog(null, panel, "Catalog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {

            List<Newspaper> newSubbed = panel.getSubbedNewspapers();

            newSubbed.forEach(n -> {
                if (!subbed.contains(n)) {
                    userController.subscribe(n);
                }
            });

            subbed.forEach(n -> {
                if (!newSubbed.contains(n)) {
                    userController.unsubscribe(n);
                }
            });

        }
    }

    public void showPaymentWindow() {
        double amount = userController.getMonthlyCost();
        PayPanel payPanel = new PayPanel(amount);
        int result = JOptionPane.showConfirmDialog(null, payPanel, "Pay Subscription", JOptionPane.OK_CANCEL_OPTION);
    }


}
