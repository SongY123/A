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
import service.LoginService;


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
	private LoginService loginService;
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
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
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
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String username = (String) session.get("username");
	    int authority = loginService.getAuthority(username);
	    System.out.println("authority:"+authority);
		News news = findNewsService.findNewsById(dbname, newsid,authority);
		System.out.println("title:"+news.getTitle());
		isfirst = findNewsService.isFirst(dbname, newsid, state, authority)?1:0;
		islast = findNewsService.isLast(dbname, newsid, state, authority)?1:0;
		
		if(authority==0){
			if(isfirst!=1){//获取下一条新闻id
				nextnewsid = Long.toString(news.getId()+1);
				System.out.println("nextnewsid "+nextnewsid);
			}
			if(islast!=1){//获取上一条新闻id
				lastnewsid = Long.toString(news.getId()-1);
				System.out.println("lastnewsid+ "+lastnewsid);
			}
		}
		else{
			if(isfirst!=1){//获取下一条新闻id
				nextnewsid = Long.toString(findNewsService.getNextNewsId(dbname, Long.toString(news.getId()), state));
				System.out.println("nextnewsid "+nextnewsid);
			}
			if(islast!=1){//获取上一条新闻id
				lastnewsid = Long.toString(findNewsService.getLastNewsId(dbname, Long.toString(news.getId()), state));
				System.out.println("lastnewsid+ "+lastnewsid);
			}
		}
	    String title = news.getTitle();
	    session.put("title",title);
	    session.put("context",null);
	    LabelItem label = null;
	    if(authority==0){
	    	label = connectLabelDB.GetTestdataByNewsID(news.getId(), dbname);
	    }
	    else
	    	label = connectLabelDB.GetTempLabelByNewsID(news.getId(), dbname);
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
			//强制将if_remark设置为不可更改
			if(authority==0)
				label.if_remark=0;
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
	    		incidenttype = label.eventType==0?21:label.eventType;
	    		session.put("incident", jo.get(Integer.toString(incidenttype)));
	    	}
	    	session.put("sponsor",label.sourceActor);
	    	int sponsorlength = label.sourceActor==null?0:label.sourceActor.split("_").length;
	    	String sponsortype=label.sourceActorPro;
	    	session.put("sponsortype",sponsortype);
	    	session.put("triggerWord",label.triggerWord);
	    	session.put("bearer",label.targetActor);
	    	int bearerlength = label.targetActor==null?0:label.targetActor.split("_").length;
	    	String bearertype=label.targetActorPro;
	    	session.put("bearertype",bearertype);
	    	session.put("time",label.eventTime);
	    	session.put("location",label.eventLocation);
	    	session.put("actor_index",label.actor_index);
	    	session.put("actor_len", label.actor_len);
	    	session.put("actor_Pro", label.actor_Pro);
	    	session.put("actor", label.actor);
	    }
	    else{
	    	session.put("incident", null);
	    	session.put("sponsor",null);
	    	session.put("sponsortype",null);
	    	session.put("triggerWord",null);
	    	session.put("bearer",null);
	    	session.put("bearertype",null);
	    	session.put("time",null);
	    	session.put("location",null);
	    	session.put("actor_index",null);
	    	session.put("actor_len", null);
	    	session.put("actor_Pro", null);
	    	session.put("actor", null);
	    }
		return this.SUCCESS;
	}
}
