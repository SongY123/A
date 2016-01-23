package model;
import java.io.Serializable;

public class Role implements Serializable{
	private long roleid;
	private String rolename;
	private String regionname;
	private String countryname;
	private String rolecode;
	private String other;
	public Role(long roleid,String rolename,String regionname,String countryname,String rolecode,String other){
		this.roleid = roleid;
		this.rolename = rolename;
		this.regionname = regionname;
		this.countryname = countryname;
		this.rolecode = rolecode;
		this.other = other;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRegionname() {
		if(regionname.length()==0)return null;
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getCountryname() {
		if(countryname.length()==0)return null;
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getRolecode() {
		if(rolecode.length()==0)return null;
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getOther() {
		if(other.length()==0)return null;
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void print(){
		System.out.println(roleid);
		System.out.println(rolename);
		System.out.println(regionname);
		System.out.println(countryname);
		System.out.println(rolecode);
		System.out.println(other);
	}
}
