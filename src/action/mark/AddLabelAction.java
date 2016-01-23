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
//修改事件抽取
public class AddLabelAction extends ActionSupport implements Action{
	private ConnectLabelDB connectLabelDB;
	//private String newsContent;
	private int eventType;
	private String sourceActor;
	private String targetActor;
	private String triggerWord;
	private String eventTime;
	private String eventLocation;
	
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
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getSourceActor() {
		return sourceActor;
	}
	public void setSourceActor(String sourceActor) {
		this.sourceActor = sourceActor;
	}
	public String getTargetActor() {
		return targetActor;
	}
	public void setTargetActor(String targetActor) {
		this.targetActor = targetActor;
	}
	public String getTriggerWord() {
		return triggerWord;
	}
	public void setTriggerWord(String triggerWord) {
		this.triggerWord = triggerWord;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
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
		System.out.println(eventType);
		System.out.println(triggerWord);
		System.out.println(username);
		LabelItem item = null;
		if(eventType==21)//not event
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle);
		else
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle,
				eventType, sourceActor, targetActor, triggerWord, eventTime,
				eventLocation);
		connectLabelDB.RemarkLabel(item, username);
		return this.SUCCESS;
	}
}
