package action.mark.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import service.FindNewsService;
import service.MarkService;

public class NominalChangeAction extends ActionSupport implements Action{
	private MarkService markService;
	private String dbname;
	private String newsid;
	private String changener;
	
	private InputStream successString = new ByteArrayInputStream("SUCCESS".getBytes());
	private InputStream errorString = new ByteArrayInputStream("ERROR".getBytes());
	
	public String execute() throws Exception{
		markService.nerChange(changener,dbname+"_"+newsid);
		return this.SUCCESS;
	}
	public MarkService getMarkService() {
		return markService;
	}
	public void setMarkService(MarkService markService) {
		this.markService = markService;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getChangener() {
		return changener;
	}
	public void setChangener(String changener) {
		this.changener = changener;
	}
	public InputStream getSuccessString() {
		return successString;
	}
	public void setSuccessString(InputStream successString) {
		this.successString = successString;
	}
	public InputStream getErrorString() {
		return errorString;
	}
	public void setErrorString(InputStream errorString) {
		this.errorString = errorString;
	}
	
}
