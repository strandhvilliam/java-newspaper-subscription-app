package Controllers;

import Models.*;
import View.ClientGUI;
import View.SwingClientGUI;
import View.SwingMainGUI;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarkLaf());

        NewspaperDAO newspaperDAO = new FileNewspaperDAO("newspapers-file.txt");
        UserDAO userDAO = new FileUserDAO("users.ser");

        SwingMainGUI gui = new SwingMainGUI();
        AdminApp adminApp = new AdminApp(gui, newspaperDAO, userDAO);

        User user1 = new User("John Johnsson", "john@mail.com", new StandardPayBehavior());
        ClientGUI swingClientGUI1 = new SwingClientGUI();
        UserController userController1 = new UserController(swingClientGUI1, user1, newspaperDAO, userDAO);
        adminApp.addClientPanel(swingClientGUI1);

    }
}
