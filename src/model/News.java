package model;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.print.DocFlavor.STRING;
import com.mysql.jdbc.Blob;
import com.opensymphony.xwork2.ActionSupport;
public class News implements Serializable{
	private long id;
	private String title;
	private String agent;
	private String author;
	private String url;
	private byte[] content;
	private byte[] picture;
	private Date updateTime;
	private Date saveTime;
	private int state;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAgent() {
		return agent;
	}
	
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getSaveTime() {
		return saveTime;
	}
	
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void print(){
		System.out.println(id+","+title+","+agent+","+author+","+url+","+","+content+","+picture+updateTime+","+saveTime);
	}
}