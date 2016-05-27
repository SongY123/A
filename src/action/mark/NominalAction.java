package action.mark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import model.News;

import org.apache.catalina.connector.InputBuffer;
import org.apache.catalina.core.ApplicationContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import service.FindNewsService;
import service.MarkService;

public class NominalAction extends ActionSupport{
	private MarkService markService;
	private JSONObject jo;
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
	
	public void initJSONObject(){
		String rootPath;
		try {
			rootPath = this.getClass().getResource("").toURI().getPath();
			File f = new File(rootPath+"nominaldata1.json");
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
	
	public String execute() throws Exception
	{
		News news = findNewsService.findNewsById(dbname, newsid, 1);
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String title = news.getTitle();
	    String context = Byte.toString(news.getContent()[0]);
	    String titletags = "";
	    String titles = "";
	    List<Pair<String, String>>tagPair = markService.tagResult(dbname+"_"+newsid);
	   /* for(Pair<String, String>p:tagPair)
	    {
	    	System.out.println(p.getKey()+"£º"+p.getValue());
	    }
	    */
	    for(Iterator<Pair<String, String>>i=tagPair.iterator();i.hasNext();){
	    	Pair<String, String>p = i.next();
	    	titles+=p.getKey()+", ";
	    	titletags+=jo.get(p.getValue())+" ";
	    }
	    session.put("title", news.getTitle());
	    session.put("titles",titles);
	    session.put("titletags", titletags);
	    
	    String contexts = "";
	    String contexttags = "";
	    
	    List<Pair<String, String>>tagPair2 = markService.tagResult(dbname+"_"+newsid);
	    for(Iterator<Pair<String, String>>i=tagPair2.iterator();i.hasNext();){
	    	Pair<String, String>p = i.next();
	    	contexts+=p.getKey()+" ";
	    	contexttags+=jo.get(p.getValue())+" ";
	    }
	    session.put("context",null);
	    session.put("contexts",contexts);
	    session.put("contexttags", contexttags);
	    return this.SUCCESS;
	}
}
