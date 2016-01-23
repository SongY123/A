package interceptor;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
public class UserInterceptor  extends AbstractInterceptor{
	public void destroy() {
        // TODO Auto-generated method stub
    }
 
    public void init() {
        // TODO Auto-generated method stub
    }
 
    public String intercept(ActionInvocation invocation) throws Exception {
         
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        String username= (String) ActionContext.getContext().getSession().get("username");
        if(username!=null)
        	return invocation.invoke();
        else{
        	System.out.println("User not login,Please login first");
        	return "NOTLOGINED";
        }
             
    }
}
