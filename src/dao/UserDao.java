package dao;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.impl.IUserDao;
import model.User;

public class UserDao implements IUserDao  {  
    private JdbcTemplate jdbcTemplate;  

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    }
    public JdbcTemplate getJdbcTemplate() {  
        return this.jdbcTemplate;  
    }
    public void addUser(User user) {  
    	String addUsersql = "insert into user values(*, *, *, *, *, *);";
    	Object[]args = {user.getId(),user.getUsername(),user.getPassword(),user.getAuthority(),user.getLastVisit(),user.getLastIp()};
    	try {
			jdbcTemplate.update(addUsersql,args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
    }  
    
    public void delUser(User user) {  
    	String delUsersql = "drop * from user where username = *";
    	Object[]args = {user.getUsername()};
    	try {
			jdbcTemplate.update(delUsersql,args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
    }  
    
    public int getAuthorityByName(String username){
    	String findUsersql = "select authority from user where username = ?";
    	Object[]args = {username};
    	int authority = jdbcTemplate.queryForObject(findUsersql, args, int.class);
        return authority;
    }
    public User findUserByName(String username) {  
    	String findUsersql = "select * from user where username = ?";
    	Object[]args = {username};
    	List rows = jdbcTemplate.queryForList(findUsersql, args);
        User user = new User();
        user.setId((int)rows.get(0));
        user.setUsername((String)rows.get(1));
        user.setPassword((String)rows.get(2));
        user.setAuthority((int)rows.get(3));
        user.setLastVisit((Date)rows.get(4));
        user.setLastIp((String)rows.get(5));
        return user;
  
    }  
    public void updateUser(User user) {  
    	//String updateUsersql = "update user set ...."
    }  
  
   
  
    public boolean checkUser(String username, String password) {  
        // TODO Auto-generated method stub  
  
  //      System.out.println(password);  
    	System.out.println("begin query user");
        String sql="select count(*) from user where username=? and password=? ";  
  
        int count=jdbcTemplate.queryForObject(sql,new Object[]{username,password},Integer.class);  
        System.out.println("end query user");
        return count>0;  
  
    }  
    public boolean register(User user)  {  
  
        // TODO Auto-generated method stub  
  
        System.out.println("beign dao");  
  
        String sql="insert into  user (name,credits,password,last_visit,last_ip) values (?,?,?,?,?)";
        Object[] args={user.getUsername(),user.getPassword(),user.getLastVisit(),user.getLastIp()};  
  
        try{  
  
            jdbcTemplate.update(sql, args);  
        }catch (DataAccessException e)  
  
        {  
  
            e.printStackTrace();  
  
            return false;  
  
        }  
        return true;  
    }  
  
}  