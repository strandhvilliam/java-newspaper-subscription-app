package View;

import Controllers.App;
import Controllers.SingletonDataHandler;
import Models.ArticleReader;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.IOException;

public class ClientPanel extends JPanel implements TextUpdater{


    private final JPanel topPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel monthlyCostPanel = new JPanel();
    private final JTextPane clientTextPane = new JTextPane();
    private final JScrollPane clientScrollPane = new JScrollPane(clientTextPane);
    private final JButton paySubscriptionBtn = new JButton("Pay Subscription");
    private final JButton showCatalogBtn = new JButton("Show Catalog");
    private final JButton unsubscribe = new JButton("Unsubscribe");
    private final JLabel montlyCostLabel = new JLabel("Price:");
    private final JLabel montlyCostAmount = new JLabel("100.22");
    private final JLabel appNameLabel = new JLabel("MyNewspaperReader");

    private ArticleReader articleReader;

    private App app;
    private SingletonDataHandler singletonDataHandler = SingletonDataHandler.getInstance();

    public ClientPanel() {
        montlyCostAmount.setFont(new Font("sans-serif", Font.BOLD, 14));
        monthlyCostPanel.add(montlyCostLabel);
        monthlyCostPanel.add(montlyCostAmount);

        showCatalogBtn.setPreferredSize(new Dimension(160, 30));
        showCatalogBtn.addActionListener(e -> {
            CatalogListPanel catalogListPanel = new CatalogListPanel();
            catalogListPanel.setList(singletonDataHandler.getNewspapers());
            int option = JOptionPane.showConfirmDialog(this, catalogListPanel, "Models.Newspaper Catalog", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                //check if already subscribed
                //implement logic to add newspaper to client's subscription
                app.subscribe(catalogListPanel.getSelectedNewspaper().getId());
            }
        });

        monthlyCostPanel.setPreferredSize(new Dimension(160, 30));
        unsubscribe.setPreferredSize(new Dimension(160, 30));
        unsubscribe.addActionListener(e -> {
            //implement logic to show subscribed newspapers
            CatalogListPanel catalogListPanel = new CatalogListPanel();
            //catalogListPanel.setListData(subscribedList);
            int option = JOptionPane.showConfirmDialog(this, catalogListPanel, "Subscribed Newspapers", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                //implement logic to remove newspaper from client's subscription
                System.out.println(catalogListPanel.getSelectedNewspaper());
            }
        });


        paySubscriptionBtn.setPreferredSize(new Dimension(160, 30));
        paySubscriptionBtn.addActionListener(e -> {
            //implement logic to pay subscription
        });
        showCatalogBtn.setMinimumSize(new Dimension(160, 30));
        monthlyCostPanel.setMinimumSize(new Dimension(160, 30));
        unsubscribe.setMinimumSize(new Dimension(160, 30));
        paySubscriptionBtn.setMinimumSize(new Dimension(160, 30));

        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.weightx = 1;
        c.gridy = 0;
        buttonPanel.add(showCatalogBtn, c);
        buttonPanel.add(monthlyCostPanel, c);
        c.gridy = 1;
        buttonPanel.add(unsubscribe, c);
        buttonPanel.add(paySubscriptionBtn, c);
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

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(clientTextPane, BorderLayout.CENTER);
    }

    public void setArticleReader(ArticleReader reader) {
        this.articleReader = reader;
    }

    @Override
    public void setController(App app) {
        this.app = app;
    }

    public void updateTextPane() {
        StringBuilder readerContent = new StringBuilder();
        articleReader.getArticleList().forEach(readerContent::append);

        HTMLDocument doc = (HTMLDocument) clientTextPane.getStyledDocument();
        try {
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), String.valueOf(readerContent));
        } catch (BadLocationException | IOException e) {
            e.printStackTrace();
        }

    }

}
