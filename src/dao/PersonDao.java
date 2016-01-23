package dao;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.impl.IPersonDao;
import model.Person;

public class PersonDao implements IPersonDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	
	public void addPerson(Person person) {
		String insertsql = "insert into personinfor values(null,?,?,?,?,?,?,?);";
		jdbcTemplate.update(insertsql,
				new Object[]{person.getPersonname(), person.getCountryname(), person.getRegionname(), person.getRolename(), person.getPosition(), person.getOther(), person.getTime()});
	}
}
