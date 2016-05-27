package dao.impl;
import java.util.List;
import model.News;
public interface INewsDao  {  
	public News findNewsById(String id,String database,int authority); 
    public News findNewsBytitle(String title,String database);
    public News findNews(String database);
	List<News> getNewsList(String database, int beginid, int state,int authority);
} 