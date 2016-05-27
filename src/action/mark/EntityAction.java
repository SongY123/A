package action.mark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import model.News;

import org.apache.catalina.connector.InputBuffer;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import service.FindNewsService;
import service.MarkService;

public class EntityAction extends ActionSupport{
	private MarkService markService;
	private FindNewsService findNewsService;
	private String dbname;
	private String newsid;
	
	public MarkService getMarkService() {
		return markService;
	}
	
	public void setMarkService(MarkService markService) {
		this.markService = markService;
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
	
	public String execute() throws Exception
	{
		News news = findNewsService.findNewsById(dbname, newsid,1);
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String title = news.getTitle();
	    String context = Byte.toString(news.getContent()[0]);
	    String titles = markService.segResult(dbname+"_"+newsid);
	    session.put("title",title);
	    session.put("context",null);
	    session.put("titles",titles);
	    String titletags = "";
	    List<Pair<String, String>>tagPair = markService.nerResult(dbname+"_"+newsid);
	    if(tagPair!=null){
	    	for(Iterator<Pair<String, String>>i=tagPair.iterator();i.hasNext();){
	    		Pair<String, String>p = i.next();
	    		titletags+=p.getKey()+"="+p.getValue()+" ";
	    	}
	    	session.put("titletags", titletags);
	    }
	    else{
	    	session.put("titletags",null);
	    }
	    String contexts = markService.segResult(dbname+"_"+newsid);
	    session.put("contexts",contexts);
	    String contexttags = "";
	    
	    List<Pair<String, String>>tagPair2 = markService.nerResult(dbname+"_"+newsid);
	    if(tagPair2!=null){
	    	for(Iterator<Pair<String, String>>i=tagPair2.iterator();i.hasNext();){
	    		Pair<String, String>p = i.next();
	    		contexttags+=p.getKey()+"="+p.getValue()+" ";
	    	}
	    	session.put("contexttags", contexttags);
	    }
	    else session.put("contexttags",null);
	    return this.SUCCESS;
	}
}
