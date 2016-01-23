package model;
import java.io.Serializable;

public class Region implements Serializable{
	private long regionid;
	private String regionname;
	private String regioncode;
	private String regioninfor;
	public Region(long regionid, String regionname, String regioncode, String regioninfor){
		this.regionid = regionid;
		this.regionname = regionname;
		this.regioncode = regioncode;
		this.regioninfor = regioninfor;
	}
	public long getRegionid() {
		return regionid;
	}
	public void setRegionid(long regionid) {
		this.regionid = regionid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getRegioncode() {
		if(regioncode.length()==0)return null;
		return regioncode;
	}
	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}
	public String getRegioninfor() {
		if(regioninfor.length()==0)return null;
		return regioninfor;
	}
	public void setRegioninfor(String regioninfor) {
		this.regioninfor = regioninfor;
	}
	public void print(){
		System.out.println(regionid);
		System.out.println(regionname);
		System.out.println(regioncode);
		System.out.println(regioninfor);
	}
}
