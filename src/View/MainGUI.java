package View;

import Controllers.SingletonDataHandler;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {

    private List<JPanel> clientPanels = new ArrayList<>();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu adminMenu = new JMenu("Admin Options");

    private JMenu clientsMenu = new JMenu("Switch client");
    private JMenuItem addNewspaperMenuItem = new JMenuItem("Add Models.Newspaper");
    private JMenuItem publishContentMenuItem = new JMenuItem("Publish Models.Content");

    private SingletonDataHandler singletonDataHandler = SingletonDataHandler.getInstance();


    public MainGUI() {
        super("Models.Newspaper Collection Controllers.App Demo");


        clientsMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                clientsMenu.removeAll();
                System.out.println(clientPanels.size());
                for (int i = 0; i < clientPanels.size(); i++) {
                    JMenuItem clientMenuItem = new JMenuItem("Client " + (i + 1));
                    int finalI = i;
                    clientMenuItem.addActionListener(e1 -> switchClient(finalI));
                    clientsMenu.add(clientMenuItem);
                }
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });


        addNewspaperMenuItem.addActionListener(e -> {
            AddNewspaperDialog addNewspaperDialog = new AddNewspaperDialog();
            int option = JOptionPane.showConfirmDialog(null, addNewspaperDialog, "Add Models.Newspaper", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION &&
                    !addNewspaperDialog.getNameField().equals("") &&
                    !addNewspaperDialog.getDescriptionField().equals("") &&
                    !addNewspaperDialog.getMonthlyPriceField().equals("")) {
                singletonDataHandler.addNewspaper(addNewspaperDialog.getNameField(), addNewspaperDialog.getDescriptionField(), Double.parseDouble(addNewspaperDialog.getMonthlyPriceField()));
            }
        });

        publishContentMenuItem.addActionListener(e -> {
            PublishContentDialog publishContentDialog = new PublishContentDialog(singletonDataHandler.getNewspapers());
            int option = JOptionPane.showConfirmDialog(null, publishContentDialog, "Publish Models.Content", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                boolean isAd = publishContentDialog.getIsAd();
                System.out.println(isAd);
                publishContentDialog.getSelectedNewspaper().publishContent(isAd);
            }
        });

        adminMenu.add(addNewspaperMenuItem);
        adminMenu.add(publishContentMenuItem);

        menuBar.add(adminMenu);
        menuBar.add(clientsMenu);

        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void switchClient(int finalI) {
        getContentPane().removeAll();
        getContentPane().add(clientPanels.get(finalI));
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    public void addClientPanel(JPanel clientPanel) {
        clientPanels.add(clientPanel);
        add(clientPanel);
        revalidate();
        repaint();
    }




}
