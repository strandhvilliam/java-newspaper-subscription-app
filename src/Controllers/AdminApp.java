package Controllers;

import Models.*;
import View.AdminGUI;
import View.ClientGUI;
import View.SwingClientGUI;

import java.util.ArrayList;
import java.util.List;

public class AdminApp {
    private final List<ClientGUI> clientPanels;
    private final AdminGUI adminGUI;
    private final NewspaperDAO newspaperDAO;
    private final UserDAO userDAO;

    public AdminApp(AdminGUI adminGUI, NewspaperDAO newspaperDAO, UserDAO userDAO) {
        this.adminGUI = adminGUI;
        this.newspaperDAO = newspaperDAO;
        this.userDAO = userDAO;
        clientPanels = new ArrayList<>();
        userDAO.loadUsers();
        userDAO.readUsers().forEach(user -> initUser(user));
        adminGUI.setGUIController(this);
    }

    public void createNewspaper(String name, String publisherName, double monthlyCost) {
        newspaperDAO.createNewspaper(name, publisherName, monthlyCost);
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

    public void exitApp() {
        newspaperDAO.saveNewspapers();
        userDAO.saveUsers();
    }

    public void createUser(String name, String email, boolean isPremium) {
        User user;
        if (isPremium) {
            user = new User(name, email, new PremiumPayBehavior());
        } else {
            user = new User(name, email, new StandardPayBehavior());
        }
        userDAO.addUser(user);
        initUser(user);
    }

    public void initUser(User user) {
        ClientGUI clientGUI = new SwingClientGUI();
        UserController userController = new UserController(clientGUI, user, newspaperDAO);
        clientGUI.setGUIController(userController);
        user.setUserApp(userController);
        addClientPanel(clientGUI);
    }

}
