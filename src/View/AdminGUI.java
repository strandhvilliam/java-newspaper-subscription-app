package View;

import Controllers.AdminApp;

public interface AdminGUI {

    void showAddNewspaperDialog();

    void showPublishContentDialog();

    void setGUIController(AdminApp adminApp);
}
