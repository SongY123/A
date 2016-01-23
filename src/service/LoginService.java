package service;

import dao.UserDao;
import model.User;

public class LoginService {  
    private UserDao userDao;

    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {  
        this.userDao = userDao;  
    }  
    
    public boolean login(User user) throws Exception{  
        boolean flag=false;  
        if(userDao.checkUser(user.getUsername(), user.getPassword())){  
            flag=true;  
        }  
        return flag;  
    }  
}  