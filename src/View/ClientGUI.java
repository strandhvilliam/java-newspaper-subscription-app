package View;


import Controllers.UserController;

import java.util.List;

public interface ClientGUI {

    void updateArticleFeed(List<String> articles);
    void setGUIController(UserController userController);

    void showCatalogWindow();

    void showPaymentWindow();

}
