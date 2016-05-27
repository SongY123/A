package service;

import dao.NewsDao;
import model.News;
import sun.net.www.content.text.plain;

public class FindNewsService {
	private NewsDao newsDao;

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	public News findNews(String database){
		return newsDao.findNews(database);
	}
	public News findNewsById(String database,String id, int authority){
		return newsDao.findNewsById(id, database,authority);
	}
	public News findNewsByTitle(String database, String title){
		return newsDao.findNewsBytitle(title, database);
	}
	public boolean isFirst(String dbname,String id,int state,int authority){
		return newsDao.isFirst(dbname, id,state,authority);
	}
	public boolean isLast(String dbname, String id, int state, int authority){
		return newsDao.isLast(dbname, id, state, authority);
	}
	public long getLastNewsId(String dbname, String id, int state){
		return newsDao.getLastNewsId(dbname, id, state);
	}
	public long getNextNewsId(String dbname, String id, int state){
		return newsDao.getNextNewsId(dbname, id, state);
	}
}
