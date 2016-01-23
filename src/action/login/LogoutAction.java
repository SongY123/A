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

public class LogoutAction extends ActionSupport implements Action{  
    private LoginService loginService;  
    public String execute() throws Exception  
    {
    	ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    session.clear();
    	return this.SUCCESS;
    }
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}  
}