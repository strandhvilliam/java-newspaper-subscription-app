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
    private final JPanel articlePanel = new JPanel();
    private final JTextPane clientTextPane = new JTextPane();
    private final JScrollPane clientScrollPane = new JScrollPane(clientTextPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private final JButton paySubscriptionBtn = new JButton("Pay Subscription");
    private final JButton showCatalogBtn = new JButton("Show Catalog");

    private final JButton memberTypeBtn = new JButton("Upgrade / Downgrade");

    private final JLabel nameLabel = new JLabel("Name: VILLIAM");
    private final JLabel montlyCostAmount = new JLabel("Price: 100.22");
    private final JLabel subTypeLabel = new JLabel("Member type: Standard");
    private final JLabel appNameLabel = new JLabel("MyNewspaperReader");

    private UserController userController;

    public SwingClientGUI() {
        showCatalogBtn.setPreferredSize(new Dimension(160, 30));
        paySubscriptionBtn.setPreferredSize(new Dimension(160, 30));
        memberTypeBtn.setPreferredSize(new Dimension(160, 30));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(nameLabel, c);
        c.gridx = 1;
        buttonPanel.add(showCatalogBtn, c);
        c.gridy = 1;
        c.gridx = 0;
        buttonPanel.add(montlyCostAmount, c);
        c.gridx = 1;
        buttonPanel.add(paySubscriptionBtn, c);
        c.gridy = 2;
        c.gridx = 0;
        buttonPanel.add(subTypeLabel, c);
        c.gridx = 1;
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
        clientTextPane.setMargin(new Insets(12, 12, 12, 12));
        clientTextPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        clientScrollPane.setBorder(BorderFactory.createLineBorder(new Color(97, 99, 100)));


        articlePanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        articlePanel.add(clientScrollPane);
        articlePanel.setLayout(new GridLayout());

        paySubscriptionBtn.addActionListener(e -> {
            showPaymentWindow();
        });
        showCatalogBtn.addActionListener(e -> {
            showCatalogWindow();
        });
        memberTypeBtn.addActionListener(e -> {
            userController.changePayBehavior();
            updateUserInfo();
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(articlePanel, BorderLayout.CENTER);
    }

    public void updateArticleFeed(List<String> articles) {

        articles.forEach(System.out::println);

        HTMLDocument doc = (HTMLDocument) clientTextPane.getStyledDocument();
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        articles.forEach(a -> {
            try {
                doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), a);
            } catch (BadLocationException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientTextPane.setCaretPosition(0);

    }

    @Override
    public void setGUIController(UserController userController) {
        this.userController = userController;
        updateUserInfo();
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
        updateUserInfo();
    }

    public void showPaymentWindow() {
        double amount = userController.getMonthlyCost();
        PayPanel payPanel = new PayPanel(amount);
        int result = JOptionPane.showConfirmDialog(null, payPanel, "Pay Subscription", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            userController.paySubscription();
        }
    }

    private void updateUserInfo() {
        nameLabel.setText("Name: " + userController.getUser().getName());
        subTypeLabel.setText("Member type: " + userController.getUser().getPayBehavior().toString());
        montlyCostAmount.setText("Price: " + userController.getMonthlyCost());
    }


    @Override
    public String toString() {
        return userController.getUser().getEmail();
    }


}
