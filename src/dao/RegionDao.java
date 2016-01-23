package dao;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.impl.IRegionDao;
import model.Country;
import model.Region;

public class RegionDao implements IRegionDao{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {  
	    return this.jdbcTemplate;  
	}
	
	public void addRegion(Region region) {
		String insertsql = "insert into regioninfor values(null,?,?,?);";
		jdbcTemplate.update(insertsql,
				new Object[]{region.getRegionname(), region.getRegioncode(), region.getRegioninfor()});
	}

}
