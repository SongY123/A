package action.statistics;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionContext;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.commons.dbcp.BasicDataSource;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.net.httpserver.HttpContext;

import Label.*;
import service.StatisticsService;
public class StatisticsAction  extends ActionSupport implements Action{
	private StatisticsService statisticsService;
	
	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String data = statisticsService.getAllCount();
	    session.put("data",data);
		return this.SUCCESS;
	}
}
