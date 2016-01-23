package action.login;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import model.User;
import service.LoginService;
import util.Md5Encode;

public class LoginAction extends ActionSupport implements Action{  
    private String username;  
    private String password;  
    private LoginService loginService;  
    @Override  
    public void validate()  
    {  
    }  
    
    public String execute() throws Exception  
    {    
    	System.out.println("user-login");
        User user=new User();  
        user.setUsername(username);  
        user.setPassword(password); 
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("username", username);
        boolean result=this.loginService.login(user);  
        System.out.println("user-logined");
        if(result){  
            return this.SUCCESS;  
        }else{  
            return this.ERROR ;  
        }    
    }  
    public String getUsername() {  
        return username;  
    }  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    public LoginService getLoginService() {  
        return loginService;  
    }  
    public void setLoginService(LoginService loginService) {  
        this.loginService = loginService;  
    }  
}  