import Controllers.App;
import Controllers.SingletonDataHandler;
import View.ClientPanel;
import View.MainGUI;
import View.TextUpdater;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarkLaf());

        MainGUI gui = new MainGUI();
        TextUpdater clientPanel = new ClientPanel();
        App app = new App(clientPanel);
        gui.addClientPanel((JPanel) clientPanel);

        TextUpdater clientPanel2 = new ClientPanel();
        App app2 = new App(clientPanel2);
        gui.addClientPanel((JPanel) clientPanel2);
    }
}
