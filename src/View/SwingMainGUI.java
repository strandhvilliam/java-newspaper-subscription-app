package View;

import Controllers.AdminApp;
import Models.Newspaper;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.WindowAdapter;


public class SwingMainGUI extends JFrame implements AdminGUI{

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu adminMenu = new JMenu("Admin Options");

    private final JMenu clientsMenu = new JMenu("Switch client");
    private final JMenuItem addNewspaperMenuItem = new JMenuItem("Add Models.Newspaper");
    private final JMenuItem publishContentMenuItem = new JMenuItem("Publish Models.Content");

    private AdminApp adminApp;


    public SwingMainGUI() {
        super("Admin App - Newspaper System");

        clientsMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                clientsMenu.removeAll();
                int numOfClients = adminApp.getClientPanels().size();
                for (int i = 0; i < numOfClients; i++) {
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
            showAddNewspaperDialog();
        });

        publishContentMenuItem.addActionListener(e -> {
            showPublishContentDialog();
        });

        adminMenu.add(addNewspaperMenuItem);
        adminMenu.add(publishContentMenuItem);

        menuBar.add(adminMenu);
        menuBar.add(clientsMenu);


        setJMenuBar(menuBar);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showPublishContentDialog() {
        PublishContentDialog publishContentDialog = new PublishContentDialog(adminApp.getNewspapers());
        int option = JOptionPane.showConfirmDialog(null, publishContentDialog, "Publish Models.Content", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            adminApp.publishContent(
                    publishContentDialog.getIsAd(),
                    publishContentDialog.getSelectedNewspaper());
        }
    }

    public void switchClient(int clientIndex) {
        getContentPane().removeAll();
        getContentPane().add((JPanel) adminApp.getClientPanels().get(clientIndex));
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void showAddNewspaperDialog() {
        AddNewspaperDialog addNewspaperDialog = new AddNewspaperDialog();
        int option = JOptionPane.showConfirmDialog(
                null, addNewspaperDialog, "Add Models.Newspaper", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION &&
                !addNewspaperDialog.getNameField().equals("") &&
                !addNewspaperDialog.getDescriptionField().equals("") &&
                !addNewspaperDialog.getMonthlyPriceField().equals("")) {
            adminApp.createNewspaper(
                    addNewspaperDialog.getNameField(),
                    addNewspaperDialog.getDescriptionField(),
                    Double.parseDouble(addNewspaperDialog.getMonthlyPriceField()));
        }
    }

    public void setGUIController(AdminApp adminApp) {
        this.adminApp = adminApp;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                adminApp.exitApp();
            }
        });

    }

}
