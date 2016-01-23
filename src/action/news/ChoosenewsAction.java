package action.news;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.News;
import model.User;
import service.FindNewsService;
import service.NewsListService;

public class ChoosenewsAction extends ActionSupport implements Action{
	private NewsListService newsListService;
	private String dbname;
	private List<News>list;
	private int beginid;
	private int sizeeachpage = 10;//title num of each page
	private int newsListSize;
	private int pageCountActual;
	private int state;
	public List<News> getList() {
		return list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}
	
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

	public int getBeginid() {
		return beginid;
	}

	public void setBeginid(int beginid) {
		this.beginid = beginid;
	}
	
	public int getSizeeachpage() {
		return sizeeachpage;
	}

	public void setSizeeachpage(int sizeeachpage) {
		this.sizeeachpage = sizeeachpage;
	}

	public int getNewsListSize() {
		return newsListSize;
	}

	public void setNewsListSize(int newsListSize) {
		this.newsListSize = newsListSize;
	}

	public int getPageCountActual() {
		return pageCountActual;
	}

	public void setPageCountActual(int pageCountActual) {
		this.pageCountActual = pageCountActual;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String execute() throws Exception 
	{
		System.out.println(dbname);
		System.out.println(beginid);
		int pagecount = newsListService.getPage(dbname, (beginid-1)*sizeeachpage,sizeeachpage,state);
		System.out.println(pagecount);
		pageCountActual = pagecount>10?10:pagecount;
		System.out.println(pageCountActual);
		list = newsListService.getNewsList(dbname,(beginid-1)*sizeeachpage,state);
		int newsListSize = list.size();
		System.out.println("newsListSize "+newsListSize);
		return this.SUCCESS;
	}
}
