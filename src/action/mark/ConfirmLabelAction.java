package action.mark;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionContext;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.commons.dbcp.BasicDataSource;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.net.httpserver.HttpContext;
import Label.*;
//确认事件抽取
public class ConfirmLabelAction extends ActionSupport implements Action{
	private ConnectLabelDB connectLabelDB;
	private InputStream successString = new ByteArrayInputStream("SUCCESS".getBytes());
	private InputStream errorString = new ByteArrayInputStream("ERROR".getBytes());
	private String dbname;
	private String newsid;
	private String username;
	
	public ConnectLabelDB getConnectLabelDB() {
		return connectLabelDB;
	}
	public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		this.connectLabelDB = connectLabelDB;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public InputStream getSuccessString() {
		return successString;
	}
	public void setSuccessString(InputStream successString) {
		this.successString = successString;
	}
	public InputStream getErrorString() {
		return errorString;
	}
	public void setErrorString(InputStream errorString) {
		this.errorString = errorString;
	}
	public String execute() throws Exception {
		successString = new ByteArrayInputStream("SUCCESS".getBytes());
		errorString = new ByteArrayInputStream("ERROR".getBytes());
		LabelItem label = connectLabelDB.GetTempLabelByNewsID(Integer.parseInt(newsid), dbname);
		System.out.println(username);
		/*if(eventType==21)//not event
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle);
		else
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle,
				eventType, sourceActor, targetActor, triggerWord, eventTime,
				eventLocation);
		*/
		if(connectLabelDB.ConfirmLabel(Integer.parseInt(label.labelID), username,dbname,Integer.parseInt(newsid)))
			return this.SUCCESS;
		else return this.ERROR;
	}
}
