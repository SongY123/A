package action.mark.entity;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import model.Country;
import model.Person;
import model.Region;
import model.Role;
import service.EntityAttrService;

public class EntityAttrAction extends ActionSupport implements Action{
	private EntityAttrService entityAttrService;
	private String selecttype;
	private String countryname;
	private String countrycode;
	private String personname;
	private String regionname;
	private String rolename;
	private String position;
	private String time;
	private String other;
	private String regioncode;
	private String regioninfor;
	private String rolecode;
	
	private InputStream successString = new ByteArrayInputStream("SUCCESS".getBytes());
	private InputStream errorString = new ByteArrayInputStream("ERROR".getBytes());
	
	public String execute() throws Exception{
		if(selecttype.equals("人物")){
			Person person = new Person(1,personname, countryname, regionname, rolename, position, other, time);
			person.print();
			entityAttrService.insertPerson(person);
		}
		else if(selecttype.equals("国家")){
			Country country = new Country(1, countryname, countrycode);
			country.print();
			entityAttrService.insertCountry(country);
		}
		else if(selecttype.equals("地区")){
			Region region = new Region(1, regionname, regioncode, regioninfor);
			region.print();
			entityAttrService.insertRegion(region);
		}
		else if(selecttype.equals("组织")){
			Role role = new Role(1, rolename, regionname, countryname, rolecode, other);
			role.print();
			entityAttrService.insertRole(role);
		}
		else return this.ERROR;
		return this.SUCCESS;
	}
	
	public EntityAttrService getEntityAttrService() {
		return entityAttrService;
	}

	public void setEntityAttrService(EntityAttrService entityAttrService) {
		this.entityAttrService = entityAttrService;
	}

	public String getSelecttype() {
		return selecttype;
	}

	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getRegioninfor() {
		return regioninfor;
	}

	public void setRegioninfor(String regioninfor) {
		this.regioninfor = regioninfor;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public InputStream getSuccessString() {
		return successString;
	}

	public void setSuccessString(InputStream successString) {
		this.successString = successString;
	}

	public InputStream getErrorString() {
		return errorString;
	}

	public void setErrorString(InputStream errorString) {
		this.errorString = errorString;
	}
	
}
