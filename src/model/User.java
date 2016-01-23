package model;

import java.io.Serializable;
import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;

public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	private int authority;
	private String lastIp;
	private Date lastVisit;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	   
	public String getPassword() {
	   return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}