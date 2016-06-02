package action.mark;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.commons.dbcp.BasicDataSource;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.util.Strings;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.net.httpserver.HttpContext;

import Java_EventDetection_News.RoleExtract.Based_rule;
import Label.*;
//根据事件类型和触发词重新抽取
public class RestartAction extends ActionSupport implements Action{
	private ConnectLabelDB connectLabelDB;
	//private String newsContent;
	private int eventType;
	private String triggerWord;
	private String dbname;
	private String newsid;
	private int state;
	private Based_rule based_rule;
	public void init(){
		based_rule.init();
	}
	public ConnectLabelDB getConnectLabelDB() {
		return connectLabelDB;
	}
	public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		this.connectLabelDB = connectLabelDB;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getTriggerWord() {
		return triggerWord;
	}
	public void setTriggerWord(String triggerWord) {
		this.triggerWord = triggerWord;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Based_rule getBased_rule() {
		return based_rule;
	}
	public void setBased_rule(Based_rule based_rule) {
		this.based_rule = based_rule;
	}
	public String execute() throws Exception {
		System.out.println(eventType);
		System.out.println(triggerWord);
		System.out.println(newsid);
		System.out.println(dbname);
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String title = (String) session.get("title");
		based_rule.Refresh(Integer.valueOf(newsid), dbname, title, triggerWord, eventType);
		return this.SUCCESS;
	}
}
