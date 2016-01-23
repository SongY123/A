package dao;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.Blob;
import dao.impl.ISegLogDao;
import model.SegLog;

public class SegLogDao implements ISegLogDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	public SegLog findNewsById(int id,String database){
		String findsql = "select * from seglog where id = '"+database+"_"+id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		SegLog seglog = new SegLog();
		seglog.setId((String)line.get("id"));
		seglog.setSentence((String)line.get("sentence"));
		seglog.setOriginal_seg((String)line.get("original_seg"));
		seglog.setChange_seg((String)line.get("change_seg"));
		seglog.setChangeflag((String)line.get("changeflag"));
		seglog.setOriginaltime((Date)line.get("originaltime"));
		seglog.setChangetime((Date)line.get("changetime"));
		seglog.setUsername((String)line.get("username"));
		return seglog;
	}
    public SegLog findNews(String database_id){
    	String findsql = "select * from seglog where id = '"+database_id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
    	System.out.println(findsql);
		Map line = (Map)rows.get(0);
		SegLog seglog = new SegLog();
		seglog.setId((String)line.get("id"));
		seglog.setSentence((String)line.get("sentence"));
		seglog.setOriginal_seg((String)line.get("original_seg"));
		seglog.setChange_seg((String)line.get("change_seg"));
		seglog.setChangeflag((String)line.get("changeflag"));
		seglog.setOriginaltime((Date)line.get("originaltime"));
		seglog.setChangetime((Date)line.get("changetime"));
		seglog.setUsername((String)line.get("username"));
		return seglog;
    }
}
