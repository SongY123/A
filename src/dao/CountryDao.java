package dao;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.impl.ICountryDao;
import model.Country;

public class CountryDao implements ICountryDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	
	public void addCountry(Country country){
		String insertsql = "insert into countryinfor values(null,?,?);";
		jdbcTemplate.update(insertsql,
				new Object[]{country.getCountryname(),country.getCountrycode()});
	}
}
