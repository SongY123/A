package dao.impl;
import model.News;
import model.PosLog;

public interface IPosLogDao {
	public PosLog findNewsById(int id,String database); 
    //public SegLog findNewsBytitle(String title,String database);
    public PosLog findNews(String database_id);
}
