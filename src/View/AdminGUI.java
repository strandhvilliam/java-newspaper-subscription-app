package View;

import Controllers.AdminApp;

public interface AdminGUI {

    void showAddNewspaperWindow();

    void showPublishContentWindow();

    void showCreateUserWindow();

    void setGUIController(AdminApp adminApp);
}
