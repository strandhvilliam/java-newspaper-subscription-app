package Controllers;

import Models.*;
import View.ClientGUI;
import View.SwingClientGUI;
import View.SwingAdminGUI;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarkLaf());

        NewspaperDAO newspaperDAO = new FileNewspaperDAO("newspapers-file.txt");
        UserDAO userDAO = new FileUserDAO("users-file.txt");

        SwingAdminGUI gui = new SwingAdminGUI();
        AdminApp adminApp = new AdminApp(gui, newspaperDAO, userDAO);


    }
}
