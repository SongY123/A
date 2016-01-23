package service;

import dao.CountryDao;
import dao.PersonDao;
import dao.RegionDao;
import dao.RoleDao;
import model.Country;
import model.Person;
import model.Region;
import model.Role;

public class EntityAttrService {
	private CountryDao countryDao;
	private PersonDao personDao;
	private RoleDao roleDao;
	private RegionDao regionDao;
	public CountryDao getCountryDao() {
		return countryDao;
	}
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public RegionDao getRegionDao() {
		return regionDao;
	}
	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}
	public void insertCountry(Country country){
		countryDao.addCountry(country);
	}
	public void insertPerson(Person person){
		personDao.addPerson(person);
	}
	public void insertRegion(Region region){
		regionDao.addRegion(region);
	}
	public void insertRole(Role role){
		roleDao.addRole(role);
	}
	
}
