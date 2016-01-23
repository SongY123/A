package model;
import java.io.Serializable;
import java.util.Date;
public class SegLog implements Serializable{
	private String id;
	private String sentence;
	private String original_seg;
	private String change_seg;
	private String changeflag;
	private Date originaltime;
	private Date changetime;
	private String username;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getOriginal_seg() {
		return original_seg;
	}
	public void setOriginal_seg(String original_seg) {
		this.original_seg = original_seg;
	}
	public String getChange_seg() {
		return change_seg;
	}
	public void setChange_seg(String change_seg) {
		this.change_seg = change_seg;
	}
	public String getChangeflag() {
		return changeflag;
	}
	public void setChangeflag(String changeflag) {
		this.changeflag = changeflag;
	}
	public Date getOriginaltime() {
		return originaltime;
	}
	public void setOriginaltime(Date originaltime) {
		this.originaltime = originaltime;
	}
	public Date getChangetime() {
		return changetime;
	}
	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
