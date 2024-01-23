package Controllers;

import views.DashboardAdminView;
import views.DashboardUserView;
import views.LoginView;

public class RootController {
    
    public void loginPage(){
        new LoginView();
    }

    public void dashboardAdmin(String username){
        new DashboardAdminView(username);
    }

    public void dashboardUser(String username){
        new DashboardUserView(username);
    }
}
