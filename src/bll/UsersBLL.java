package bll;

import dao.UserDAO;
import model.Users;

import java.util.List;

public class UsersBLL {
    private UserDAO userDAO;

    public UsersBLL() {
        userDAO = new UserDAO();
    }

    public Users findById(int id){
        Users user = userDAO.findById(id);

        if(user != null){
            return user;
        }
        else{
            return null;
        }

    }

    public List<Users> findAll(){
        List<Users> users = userDAO.findAll();

        if(users != null){
            return users;
        }
        else{
            return null;
        }
    }

    public Users insert(Users user){
        return userDAO.insert(user);
    }

    public void deleteUser(String username){
        userDAO.delete(username);
    }

    public Users updateUser(String oldUser, Users newUser){
        return userDAO.update(oldUser,newUser);
    }
}
