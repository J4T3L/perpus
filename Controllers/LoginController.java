package Controllers;

import Entities.UserEntity;
import Models.UserModel;

public class LoginController {
    
    public static UserEntity login (String username, String password)
    {
        if ( UserModel.findByUsername(username) != null && UserModel.findByUsername(username).getPassword().equals(password) && UserModel.findByUsername(username).getRole().equals("admin") )
        {
            new Controllers.RootController().dashboardAdmin(username);
            return UserModel.findByUsername(username);
        
        }else if ( UserModel.findByUsername(username) != null && UserModel.findByUsername(username).getPassword().equals(password) && UserModel.findByUsername(username).getRole().equals("user") )
        {
            new Controllers.RootController().dashboardUser(username);
            return UserModel.findByUsername(username);
        }
        return null;
    }

    
}
