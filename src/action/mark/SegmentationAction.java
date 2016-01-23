package action.mark;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.News;
import service.FindNewsService;
import service.MarkService;

public class SegmentationAction extends ActionSupport{
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
		News news = findNewsService.findNewsById(dbname, newsid);
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String titles = markService.segResult(dbname+"_"+newsid);
	    session.put("title", news.getTitle());
	    session.put("titles",titles);
	    
	    session.put("context",null);
	    session.put("contexts","null");
	    return this.SUCCESS;
	}
}
