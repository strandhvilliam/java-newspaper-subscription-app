package Controllers;

import Models.Newspaper;
import Models.NewspaperDAO;
import Models.UserDAO;
import View.AdminGUI;
import View.ClientGUI;

import java.util.ArrayList;
import java.util.List;

public class AdminApp {


    private List<ClientGUI> clientPanels;

    private AdminGUI adminGUI;
    private NewspaperDAO newspaperDAO;
    private UserDAO userDAO;

    public AdminApp(AdminGUI adminGUI, NewspaperDAO newspaperDAO, UserDAO userDAO) {
        this.adminGUI = adminGUI;
        this.newspaperDAO = newspaperDAO;
        this.userDAO = userDAO;
        clientPanels = new ArrayList<>();
        adminGUI.setGUIController(this);
    }

    public void createNewspaper(String name, String publisherName, double monthlyCost) {
        // TODO implement here
        newspaperDAO.createNewspaper(name, publisherName, monthlyCost);
    }


    public void createClient(String name, String email, String payBehavior) {
        // TODO implement here
    }

    public List<Newspaper> getNewspapers() {
        return newspaperDAO.readNewspapers();
    }

    public void publishContent(boolean isAd, Newspaper newspaper) {
        newspaper.publishContent(isAd);
    }

    public List<ClientGUI> getClientPanels() {
        return clientPanels;
    }

    public void addClientPanel(ClientGUI clientPanel) {
        clientPanels.add(clientPanel);
    }

    public int exitApp() {
        newspaperDAO.saveNewspapers();
        return 0;
    }
}
