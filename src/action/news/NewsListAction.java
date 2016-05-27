package action.news;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import model.News;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import service.NewsListService;

public class NewsListAction extends ActionSupport implements Action{
	private NewsListService newsListService;
	private String dbname;
	private Map<String, Object> newsList;
	private int beginid;

	public NewsListService getNewsListService() {
		return newsListService;
	}

	public void setNewsListService(NewsListService newsListService) {
		this.newsListService = newsListService;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	

	public Map<String, Object> getNewsList() {
		return newsList;
	}

	public void setNewsList(Map<String, Object> newsList) {
		this.newsList = newsList;
	}

	public int getBeginid() {
		return beginid;
	}

	public void setBeginid(int beginid) {
		this.beginid = beginid;
	}
	
	public String execute() throws Exception 
	{
		System.out.println(dbname);
		newsList = new HashMap<String,Object>();
		List<News> list = newsListService.getNewsList(dbname,(beginid-1)*10,0,1);
		newsList.put("size", list.size());
		
		List<Map<String, String>> l = new ArrayList<Map<String, String>>();
		for(News n:list){
			Map<String, String> m = new HashMap<String, String>();
			m.put("id",Long.toString(n.getId()));
			m.put("title", n.getTitle());
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			m.put("time",df.format(n.getUpdateTime()));
			l.add(m);
		}
		newsList.put("news", l);
		return this.SUCCESS;
	}
}
