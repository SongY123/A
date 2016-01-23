package model;
import java.io.Serializable;

public class Person implements Serializable{
	private long personid;
	private String personname;
	private String countryname;
	private String regionname;
	private String rolename;
	private String position;
	private String other;
	private String time;
	public Person(long personid,String personname,String countryname,String regionname,String rolename,String position,String other,String time){
		this.personid = personid;
		this.personname = personname;
		this.countryname = countryname;
		this.regionname = regionname;
		this.rolename = rolename;
		this.position = position;
		this.other = other;
		this.time = time;
	}
	public long getPersonid() {
		return personid;
	}
	public void setPersonid(long personid) {
		this.personid = personid;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getCountryname() {
		if(countryname.length()==0)
			return null;
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getRegionname() {
		if(regionname.length()==0)
			return null;
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getRolename() {
		if(rolename.length()==0)
			return null;
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPosition() {
		if(position.length()==0)
			return null;
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOther() {
		if(other.length()==0)
			return null;
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void print(){
		System.out.println(personid);
		System.out.println(personname);
		System.out.println(countryname);
		System.out.println(regionname);
		System.out.println(rolename);
		System.out.println(position);
		System.out.println(other);
		System.out.println(time);
	}
}
