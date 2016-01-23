package dao.impl;
import model.News;
import model.SegLog;

public interface ISegLogDao {
	public SegLog findNewsById(int id,String database); 
    //public SegLog findNewsBytitle(String title,String database);
    public SegLog findNews(String database_id);
}
