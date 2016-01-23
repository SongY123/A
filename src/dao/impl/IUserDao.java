package dao.impl;
import model.User;
public interface IUserDao  {  
    public void addUser(User user);  
    public void delUser(User user);  
    public User findUserByName(String username);  
    public void updateUser(User user);  
    public boolean checkUser(String username,String password);  
    public boolean register(User user);  
} 