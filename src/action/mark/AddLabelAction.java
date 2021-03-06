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
//�޸��¼���ȡ
public class AddLabelAction extends ActionSupport implements Action{
	private ConnectLabelDB connectLabelDB;
	//private String newsContent;
	private int eventType;
	private String sourceActor;
	private String targetActor;
	private String triggerWord;
	private String eventTime;
	private String eventLocation;
	private String sourceActorPro;
	private String targetActorPro;
	private String actorIndex;
	private String actorLen;
	private String actorPro;
	private String actorName;
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
	public String getSourceActorPro() {
		return sourceActorPro;
	}
	public void setSourceActorPro(String sourceActorPro) {
		this.sourceActorPro = sourceActorPro;
	}
	public String getTargetActorPro() {
		return targetActorPro;
	}
	public void setTargetActorPro(String targetActorPro) {
		this.targetActorPro = targetActorPro;
	}
	public String getActorIndex() {
		return actorIndex;
	}
	public void setActorIndex(String actorIndex) {
		this.actorIndex = actorIndex;
	}
	public String getActorLen() {
		return actorLen;
	}
	public void setActorLen(String actorLen) {
		this.actorLen = actorLen;
	}
	public String getActorPro() {
		return actorPro;
	}
	public void setActorPro(String actorPro) {
		this.actorPro = actorPro;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
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
		System.out.println(sourceActorPro);
		System.out.println(targetActorPro);
		System.out.println(actorIndex);
		System.out.println(actorLen);
		System.out.println(actorPro);
		System.out.println(actorName);
		LabelItem item = null;
		if(eventType==21)//not event
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle);
		else{
			connectLabelDB.addtrigger(triggerWord);
			
			String []source = sourceActor.split("_");
			String []sourcetype = sourceActorPro.split("_");
			for(int i = 0;i<source.length;i++)
				connectLabelDB.addentity(source[i], sourcetype[i]);
				
			String []target = targetActor.split("_");
			String []targettype = targetActorPro.split("_");
			for(int i = 0;i<target.length;i++)
				connectLabelDB.addentity(target[i], targettype[i]);
			
			String []actor = actorName.split("_");
			String []actortype = actorPro.split("_");
			for(int i = 0;i<actor.length;i++)
				connectLabelDB.addentity(actor[i], actortype[i]);
			
			item = new LabelItem(label.labelID, dbname, newsid, label.newsTitle,
				eventType, sourceActor, targetActor, triggerWord, sourceActorPro, targetActorPro, eventTime,
				eventLocation,actorIndex,actorLen,actorPro,actorName);
		}
		
		connectLabelDB.RemarkLabel(item, username);
		return this.SUCCESS;
	}
}
