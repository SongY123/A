package model;
import java.io.Serializable;
import java.util.Date;
public class NerLog implements Serializable{
	private String id;
	private String sentence;
	private String original_ner;
	private String change_ner;
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
	public String getOriginal_ner() {
		return original_ner;
	}
	public void setOriginal_ner(String original_ner) {
		this.original_ner = original_ner;
	}
	public String getChange_ner() {
		return change_ner;
	}
	public void setChange_ner(String change_ner) {
		this.change_ner = change_ner;
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
