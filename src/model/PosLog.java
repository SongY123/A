package model;
import java.io.Serializable;
import java.util.Date;
public class PosLog implements Serializable{
	private String id;
	private String sentence;
	private String original_pos;
	private String change_pos;
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
	public String getOriginal_pos() {
		return original_pos;
	}
	public void setOriginal_pos(String original_pos) {
		this.original_pos = original_pos;
	}
	public String getChange_pos() {
		return change_pos;
	}
	public void setChange_pos(String change_pos) {
		this.change_pos = change_pos;
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
