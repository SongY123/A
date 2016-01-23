package dao;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.Blob;
import dao.impl.IPosLogDao;
import model.PosLog;

public class PosLogDao implements IPosLogDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	public PosLog findNewsById(int id,String database){
		String findsql = "select * from poslog where id = '"+database+"_"+id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		PosLog poslog = new PosLog();
		poslog.setId((String)line.get("id"));
		poslog.setSentence((String)line.get("sentence"));
		poslog.setOriginal_pos((String)line.get("original_pos"));
		poslog.setChange_pos((String)line.get("change_pos"));
		poslog.setChangeflag((String)line.get("changeflag"));
		poslog.setOriginaltime((Date)line.get("originaltime"));
		poslog.setChangetime((Date)line.get("changetime"));
		poslog.setUsername((String)line.get("username"));
		return poslog;
	}
    public PosLog findNews(String database_id){
    	String findsql = "select * from poslog where id = '"+database_id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
    	System.out.println(findsql);
		Map line = (Map)rows.get(0);
		PosLog poslog = new PosLog();
		poslog.setId((String)line.get("id"));
		poslog.setSentence((String)line.get("sentence"));
		poslog.setOriginal_pos((String)line.get("original_pos"));
		poslog.setChange_pos((String)line.get("change_pos"));
		poslog.setChangeflag((String)line.get("changeflag"));
		poslog.setOriginaltime((Date)line.get("originaltime"));
		poslog.setChangetime((Date)line.get("changetime"));
		poslog.setUsername((String)line.get("username"));
		return poslog;
    }
}
