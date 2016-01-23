package action.mark;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.dbcp.BasicDataSource;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Label.ConnectLabelDB;
import Label.LabelItem;
import model.News;
import net.sf.json.JSONObject;
import service.FindNewsService;


public class IncidentAction extends ActionSupport implements Action{
	private ConnectLabelDB connectLabelDB;
	private FindNewsService findNewsService;
	private String dbname;
	private String newsid;
	private JSONObject jo;
	private int if_commit;//新闻是否已经提交
	private int if_relevant;//新闻是否相关
	private String marker_name;
	private int incidenttype;
	private int isfirst;//是否是第一条新闻
	private int islast;//是否是最后一条新闻
	private String nextnewsid;//下一条新闻id
	private String lastnewsid;//上一条新闻id
	private int state;
	public ConnectLabelDB getConnectLabelDB() {
		return connectLabelDB;
	}

	public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		this.connectLabelDB = connectLabelDB;
	}

	public FindNewsService getFindNewsService() {
		return findNewsService;
	}

	public void setFindNewsService(FindNewsService findNewsService) {
		this.findNewsService = findNewsService;
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

	public JSONObject getJo() {
		return jo;
	}

	public void setJo(JSONObject jo) {
		this.jo = jo;
	}

	public String getMarker_name() {
		return marker_name;
	}

	public void setMarker_name(String marker_name) {
		this.marker_name = marker_name;
	}

	public int getIf_commit() {
		return if_commit;
	}

	public void setIf_commit(int if_commit) {
		this.if_commit = if_commit;
	}

	public int getIf_relevant() {
		return if_relevant;
	}

	public void setIf_relevant(int if_relevant) {
		this.if_relevant = if_relevant;
	}

	public int getIncidenttype() {
		return incidenttype;
	}

	public void setIncidenttype(int incidenttype) {
		this.incidenttype = incidenttype;
	}

	public void initJSONObject(){
		String rootPath;
		try {
			rootPath = this.getClass().getResource("").toURI().getPath();
			File f = new File(rootPath+"incidentdata.json");
		    InputStreamReader fis = new InputStreamReader(new FileInputStream(f),"UTF-8");
		    BufferedReader br = new BufferedReader(fis);
		    String result = "";
		    String line = null;
		    while((line=br.readLine())!=null){
		    	result+=line;
		    }
		    jo = JSONObject.fromObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIsfirst() {
		return isfirst;
	}

	public void setIsfirst(int isfirst) {
		this.isfirst = isfirst;
	}

	public long getIslast() {
		return islast;
	}

	public void setIslast(int islast) {
		this.islast = islast;
	}
	
	public String getNextnewsid() {
		return nextnewsid;
	}

	public void setNextnewsid(String nextnewsid) {
		this.nextnewsid = nextnewsid;
	}

	public String getLastnewsid() {
		return lastnewsid;
	}

	public void setLastnewsid(String lastnewsid) {
		this.lastnewsid = lastnewsid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String execute() throws Exception {
		if_commit = 0;
		if_relevant = -1;
		News news = findNewsService.findNewsById(dbname, newsid);
		isfirst = findNewsService.isFirst(dbname, newsid, state)?1:0;
		islast = findNewsService.isLast(dbname, newsid, state)?1:0;
		if(isfirst!=1){//获取下一条新闻id
			nextnewsid = Long.toString(findNewsService.getNextNewsId(dbname, Long.toString(news.getId()), state));
			System.out.println("nextnewsid "+nextnewsid);
		}
		if(islast!=1){//获取上一条新闻id
			lastnewsid = Long.toString(findNewsService.getLastNewsId(dbname, Long.toString(news.getId()), state));
			System.out.println("lastnewsid+ "+lastnewsid);
		}
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String title = news.getTitle();
	    String context = Byte.toString(news.getContent()[0]);
	    session.put("title",title);
	    session.put("context",null);
		LabelItem label = connectLabelDB.GetTempLabelByNewsID(news.getId(), dbname);
	    //System.out.println(label.eventType);
	    //System.out.println(label.sourceActor);
	    //System.out.println(label.triggerWord);
	    //System.out.println(label.targetActor);
	    //System.out.println(label.eventTime);
	    //System.out.println(label.eventLocation);
	    //System.out.println(jo.get(Integer.toString(label.eventType)));
	    if_commit = news.getState()==1?1:0;
	    if_relevant = news.getState()==2?1:0;
		if(label!=null){
	    	if(label.if_remark==1){
	    		label=connectLabelDB.GetFormalLabelByNewsID(news.getId(), dbname);
	    		marker_name = label.marker_name;
	    		System.out.println(marker_name);
	    	}
	    	System.out.println("if_remark="+if_commit+" if_relevant="+if_relevant);
	    	if(label.ifEvent==false){
	    		incidenttype = 21;
	    		session.put("incident", jo.get("21"));
	    	}
	    	else{
	    		incidenttype = label.eventType;
	    		session.put("incident", jo.get(Integer.toString(label.eventType)));
	    	}
	    	session.put("sponsor",label.sourceActor);
	    	session.put("triggerWord",label.triggerWord);
	    	session.put("bearer",label.targetActor);
	    	session.put("time",label.eventTime);
	    	session.put("location",label.eventLocation);
	    }
	    else{
	    	session.put("incident", null);
	    	session.put("sponsor",null);
	    	session.put("triggerWord",null);
	    	session.put("bearer",null);
	    	session.put("time",null);
	    	session.put("location",null);
	    }
		return this.SUCCESS;
	}
}
