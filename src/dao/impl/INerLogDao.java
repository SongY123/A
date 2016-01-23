package dao.impl;
import model.News;
import model.NerLog;;

public interface INerLogDao {
	public NerLog findNewsById(int id,String database); 
    //public SegLog findNewsBytitle(String title,String database);
    public NerLog findNews(String database_id);
}
