package model;
import java.io.Serializable;

public class Country implements Serializable{
	private long countryid;
	private String countryname;
	private String countrycode;
	public Country(long countryid, String countryname, String countrycode){
		this.countryid = countryid;
		this.countryname = countryname;
		this.countrycode = countrycode;
	}
	public long getCountryid() {
		return countryid;
	}
	public void setCountryid(long countryid) {
		this.countryid = countryid;
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
	public void print(){
		System.out.println(countryid);
		System.out.println(countryname);
		System.out.println(countrycode);
	}
}
