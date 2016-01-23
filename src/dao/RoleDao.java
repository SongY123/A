package dao;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.impl.IRoleDao;
import model.Role;

public class RoleDao implements IRoleDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}

	public void addRole(Role role) {
		String insertsql = "insert into roleinfor values(null,?,?,?,?,?);";
		jdbcTemplate.update(insertsql,
				new Object[]{role.getRolename(), role.getRolecode(), role.getRegionname(), role.getCountryname(), role.getOther()});
	}
	
}
