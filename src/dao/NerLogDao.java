package dao;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.Blob;
import dao.impl.INerLogDao;
import model.NerLog;
public class NerLogDao implements INerLogDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	public NerLog findNewsById(int id,String database){
		String findsql = "select * from nerlog where id = '"+database+"_"+id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		NerLog nerlog = new NerLog();
		nerlog.setId((String)line.get("id"));
		nerlog.setSentence((String)line.get("sentence"));
		nerlog.setOriginal_ner((String)line.get("original_ner"));
		nerlog.setChange_ner((String)line.get("change_ner"));
		nerlog.setChangeflag((String)line.get("changeflag"));
		nerlog.setOriginaltime((Date)line.get("originaltime"));
		nerlog.setChangetime((Date)line.get("changetime"));
		nerlog.setUsername((String)line.get("username"));
		return nerlog;
	}
    public NerLog findNews(String database_id){
    	String findsql = "select * from nerlog where id = '"+database_id+"'";
    	List rows = jdbcTemplate.queryForList(findsql);
		Map line = (Map)rows.get(0);
		NerLog nerlog = new NerLog();
		nerlog.setId((String)line.get("id"));
		nerlog.setSentence((String)line.get("sentence"));
		nerlog.setOriginal_ner((String)line.get("original_ner"));
		nerlog.setChange_ner((String)line.get("change_ner"));
		nerlog.setChangeflag((String)line.get("changeflag"));	
		nerlog.setOriginaltime((Date)line.get("originaltime"));
		nerlog.setChangetime((Date)line.get("changetime"));
		nerlog.setUsername((String)line.get("username"));
		return nerlog;
    }
    public void nerChange(String result,String database_id){
    	String sql = "update nerlog set change_ner=?, changeflag=1,changetime=? where id='"+database_id+"';";
    	jdbcTemplate.update(sql,new Object[]{result,new Date()});
    }
}
