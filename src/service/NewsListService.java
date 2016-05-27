package service;
import java.util.ArrayList;
import java.util.List;
import Label.ConnectLabelDB;
import dao.NewsDao;
import model.News;

public class NewsListService {
	private NewsDao newsDao;
	private ConnectLabelDB connectLabelDB;
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	public List<News> getNewsList(String database, int beginid, int state,int authority){//所有新闻
		return newsDao.getNewsList(database,beginid,state,authority);
	}
	
	public int getPage(String database, int beginid, int sizeeachpage,int state,int authority){
		int count = newsDao.getCountLessThanThisID(database, beginid,state,authority);
		if(count%sizeeachpage!=0)return count/sizeeachpage+1;
		else return count/sizeeachpage;
	}
}
