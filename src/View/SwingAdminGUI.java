package View;

import Controllers.AdminApp;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class SwingAdminGUI extends JFrame implements AdminGUI{

    private JPanel tempPanel;

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu adminMenu = new JMenu("Admin Options");

    private final JMenu clientsMenu = new JMenu("Switch client");
    private final JMenuItem addNewspaperMenuItem = new JMenuItem("Add Newspaper");
    private final JMenuItem publishContentMenuItem = new JMenuItem("Publish Content");
    private final JMenuItem createUserMenuItem = new JMenuItem("Create User");

    private AdminApp adminApp;


    public SwingAdminGUI() {
        super("Admin App - Newspaper System");

        clientsMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                clientsMenu.removeAll();
                int numOfClients = adminApp.getClientPanels().size();
                for (int i = 0; i < numOfClients; i++) {
                    JMenuItem clientMenuItem = new JMenuItem(adminApp.getClientPanels().get(i).toString());
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
            showAddNewspaperWindow();
        });

        publishContentMenuItem.addActionListener(e -> {
            showPublishContentWindow();
        });

        createUserMenuItem.addActionListener(e -> {
            showCreateUserWindow();
        });

        adminMenu.add(addNewspaperMenuItem);
        adminMenu.add(publishContentMenuItem);
        adminMenu.add(createUserMenuItem);

        menuBar.add(adminMenu);
        menuBar.add(clientsMenu);

        setJMenuBar(menuBar);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showPublishContentWindow() {
        PublishContentDialog publishContentDialog = new PublishContentDialog(adminApp.getNewspapers());
        int option = JOptionPane.showConfirmDialog(null, publishContentDialog, "Publish Models.Content", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            adminApp.publishContent(
                    publishContentDialog.getIsAd(),
                    publishContentDialog.getSelectedNewspaper());
        }
    }

    public void switchClient(int clientIndex) {
        tempPanel = null;
        getContentPane().removeAll();
        getContentPane().add((Component) adminApp.getClientPanels().get(clientIndex));
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void showAddNewspaperWindow() {
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
        if (adminApp.getClientPanels().size() == 0) {
            System.out.println(adminApp.getClientPanels().size());
            JLabel label = new JLabel("No clients connected");
            JButton tempButton = new JButton("Create new client");
            tempButton.setPreferredSize(new Dimension(200, 35));
            tempButton.addActionListener(e -> showCreateUserWindow());
            tempPanel = new JPanel();
            tempPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(10, 0, 0, 0);
            c.gridy = 0;
            tempPanel.add(label, c);
            c.gridy = 1;
            tempPanel.add(tempButton, c);
            add(tempPanel, BorderLayout.CENTER);
        } else {
            System.out.println(adminApp.getClientPanels().size());
            tempPanel = null;
            switchClient(0);
        }
    }

    public void showCreateUserWindow() {
        CreateUserDialog createUserDialog = new CreateUserDialog();
        int option = JOptionPane.showConfirmDialog(
                null, createUserDialog, "Create User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION &&
                !createUserDialog.getNameField().equals("") &&
                !createUserDialog.getEmailField().equals("")) {
            adminApp.createUser(
                    createUserDialog.getNameField(),
                    createUserDialog.getEmailField(),
                    createUserDialog.isPremium());
        }
        switchClient(0);
    }

}
