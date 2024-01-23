package Models;

import java.util.ArrayList;

import Entities.UserEntity;

public class UserModel {
     
    private static ArrayList<UserEntity> users = new ArrayList<>();

    public static void initialData()
    {
        users.add(new UserEntity("123","123", "123", "admin"));
        users.add(new UserEntity("user","user", "user", "user"));
        users.add(new UserEntity("user2","user2", "user2", "user"));
        users.add(new UserEntity("user3","user3", "user3", "user"));
    }

    public static void create(UserEntity newUser)
    {
        users.add(newUser);
    }

    public static UserEntity findByUsername(String username)
    {
        for (UserEntity user : users)
        {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

}
