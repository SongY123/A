package dao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javax.sql.DataSource;
import dao.impl.INewsDao;
import model.News;
import sun.util.logging.resources.logging;
import org.apache.commons.collections.map.ListOrderedMap;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.Blob;

public class NewsDao implements INewsDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	
	@Override
	public News findNewsById(String id, String database) {
		String findsql = "select * from "+database+" where id=?";
		Object[]args ={id};
		List rows = jdbcTemplate.queryForList(findsql,args);
		Map line = (Map)rows.get(0);
		News news = new News();
		news.setId((long)line.get("id"));
		news.setTitle((String)line.get("title"));
		news.setAgent((String)line.get("agent"));
		news.setAuthor((String)line.get("author"));
		news.setUrl((String)line.get("url"));
		news.setContent((byte[])line.get("content"));
		news.setPicture((byte[])line.get("picture"));
		news.setUpdateTime((Date)line.get("updateTime"));
		news.setSaveTime((Date)line.get("saveTime"));
		news.setState((int)line.get("state"));
		return news;
	}

	@Override
	public News findNewsBytitle(String title, String database) {
		String findsql = "select * from "+database+" where title=?";
		Object[]args ={title};
		System.out.println(findsql);
		List rows = jdbcTemplate.queryForList(findsql,args);
		Map line = (Map)rows.get(0);
		News news = new News();
		news.setId((long)line.get("id"));
		news.setTitle((String)line.get("title"));
		news.setAgent((String)line.get("agent"));
		news.setAuthor((String)line.get("author"));
		news.setUrl((String)line.get("url"));
		news.setContent((byte[])line.get("content"));
		news.setPicture((byte[])line.get("picture"));
		news.setUpdateTime((Date)line.get("updateTime"));
		news.setSaveTime((Date)line.get("saveTime"));
		news.setState((int)line.get("state"));
		return news;
	}

	@Override
	public News findNews(String database) {//random select one
		int index = (int)(Math.random()*50);
		String findsql = "select * from "+database+" limit "+index+",1";
		System.out.println(findsql);
		List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		News news = new News();
		news.setId((long)line.get("id"));
		news.setTitle((String)line.get("title"));
		news.setAgent((String)line.get("agent"));
		news.setAuthor((String)line.get("author"));
		news.setUrl((String)line.get("url"));
		//System.out.println(line.get("content").getClass());
		news.setContent((byte[])line.get("content"));
		news.setPicture((byte[])line.get("picture"));
		news.setUpdateTime((Date)line.get("updateTime"));
		news.setSaveTime((Date)line.get("saveTime"));
		news.setState((int)line.get("state"));
		return news;
	}

	@Override
	public List<News> getNewsList(String database,int beginid,int state) {
		List<News> newsList = new ArrayList<News>();
		String findsql = null;
		if(state==1)//未修改 database.state=0
			findsql = "select * from "+database+" where state=0 order by savetime desc limit "+beginid+",10";
		else if(state==2)//未确认database.state=1
			findsql = "select * from "+database+" where state=1 order by savetime desc limit "+beginid+",10";
		else if(state==3)//已确认database.state=2
			findsql = "select * from "+database+" where state=2 order by savetime desc limit "+beginid+",10";
		else //所有database.state=0,1,2
			findsql = "select * from "+database+" order by savetime desc limit "+beginid+",10";
		System.out.println(findsql);
		List rows = jdbcTemplate.queryForList(findsql);
		for(int i = 0;i<rows.size();i++)
		{
			Map line = (Map)rows.get(i);
			News news = new News();
			news.setId((long)line.get("id"));
			news.setTitle((String)line.get("title"));
			news.setAgent((String)line.get("agent"));
			news.setAuthor((String)line.get("author"));
			news.setUrl((String)line.get("url"));
			//System.out.println(line.get("content").getClass());
			news.setContent((byte[])line.get("content"));
			news.setPicture((byte[])line.get("picture"));
			news.setUpdateTime((Date)line.get("updateTime"));
			news.setSaveTime((Date)line.get("saveTime"));
			news.setState((int)line.get("state"));
			newsList.add(news);
		}
		return newsList;
	}
	
	public Integer getCount(String database){
		String findsql = "select count(*) from "+database;
		Integer count = jdbcTemplate.queryForObject(findsql, Integer.class);
		return count;
	}
	
	public int getCountLessThanThisID(String database,int beginid,int state){
		String findsql = null;
		if(state==1)//未修改 database.state=0
			findsql = "select count(*) from "+database+" where state=0";
		else if(state==2)//未确认database.state=1
			findsql = "select count(*) from "+database+" where state=1";
		else if(state==3)//已确认database.state=2
			findsql = "select count(*) from "+database+" where state=2";
		else //所有database.state=0,1,2
			findsql = "select count(*) from "+database;
		Integer count = (int)jdbcTemplate.queryForObject(findsql,Integer.class);
		return count-beginid;
	}
	
	public boolean isFirst(String dbname,String id, int state){
		String findsql = null;
		if(state==1)//未修改 database.state=0
			findsql = "select  count(*) from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=0";
		else if(state==2)//未确认database.state=1
			findsql = "select  count(*) from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=1";
		else if(state==3)//已确认database.state=2
			findsql = "select  count(*) from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=2";
		else //所有database.state=0,1,2
			findsql = "select  count(*) from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+")";
		int count = jdbcTemplate.queryForObject(findsql, Integer.class);
		System.out.println("isFirst "+count);
		return count==0?true:false;
	}
	
	public boolean isLast(String dbname, String id, int state){
		String findsql = null;
		if(state==1)//未修改 database.state=0
			findsql = "select  count(*) from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=0";
		else if(state==2)//未确认database.state=1
			findsql = "select  count(*) from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=1";
		else if(state==3)//已确认database.state=2
			findsql = "select  count(*) from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=2";
		else //所有database.state=0,1,2
			findsql = "select  count(*) from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+")";
		int count = jdbcTemplate.queryForObject(findsql, Integer.class);
		System.out.println("isLast "+count);
		return count==0?true:false;
	}
	public long getLastNewsId(String dbname, String id, int state){
		
		String findsql = null;
		if(state==1)//未修改
			findsql = "select * from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=0 order by savetime asc ";
		else if(state==2)//未确认database.state=1
			findsql = "select * from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=1 order by savetime asc ";
		else if(state==3)//已确认database.state=2
			findsql = "select * from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") and state=2 order by savetime asc ";
		else //所有database.state=0,1,2
			findsql = "select * from "+dbname+" where saveTime > (select saveTime from "+dbname+" where id="+id+") order by savetime asc ";
		System.out.println(findsql);
		List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		News news = new News();
		news.setId((long)line.get("id"));
		news.setTitle((String)line.get("title"));
		news.setAgent((String)line.get("agent"));
		news.setAuthor((String)line.get("author"));
		news.setUrl((String)line.get("url"));
		//System.out.println(line.get("content").getClass());
		news.setContent((byte[])line.get("content"));
		news.setPicture((byte[])line.get("picture"));
		news.setUpdateTime((Date)line.get("updateTime"));
		news.setSaveTime((Date)line.get("saveTime"));
		news.setState((int)line.get("state"));
		return news.getId();
	}
	public long getNextNewsId(String dbname, String id, int state){
		String findsql = null;
		if(state==1)//未修改
			findsql = "select * from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=0 order by savetime desc ";
		else if(state==2)//未确认database.state=1
			findsql = "select * from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=1 order by savetime desc ";
		else if(state==3)//已确认database.state=2
			findsql = "select * from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") and state=2 order by savetime desc ";
		else //所有database.state=0,1,2
			findsql = "select * from "+dbname+" where saveTime < (select saveTime from "+dbname+" where id="+id+") order by savetime desc";
		System.out.println(findsql);
		List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		News news = new News();
		news.setId((long)line.get("id"));
		news.setTitle((String)line.get("title"));
		news.setAgent((String)line.get("agent"));
		news.setAuthor((String)line.get("author"));
		news.setUrl((String)line.get("url"));
		//System.out.println(line.get("content").getClass());
		news.setContent((byte[])line.get("content"));
		news.setPicture((byte[])line.get("picture"));
		news.setUpdateTime((Date)line.get("updateTime"));
		news.setSaveTime((Date)line.get("saveTime"));
		news.setState((int)line.get("state"));
		return news.getId();
	}		
}
