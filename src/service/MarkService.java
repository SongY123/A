package service;
import java.util.ArrayList;
import java.util.List;
import dao.NerLogDao;
import dao.PosLogDao;
import dao.SegLogDao;
import javafx.util.Pair;
import model.SegLog;
import model.PosLog;
import model.NerLog;

public class MarkService {
	private SegLogDao segLogDao;
	private NerLogDao nerLogDao;
	private PosLogDao posLogDao;
	public SegLogDao getSegLogDao() {
		return segLogDao;
	}

	public void setSegLogDao(SegLogDao segLogDao) {
		this.segLogDao = segLogDao;
	}

	public NerLogDao getNerLogDao() {
		return nerLogDao;
	}

	public void setNerLogDao(NerLogDao nerLogDao) {
		this.nerLogDao = nerLogDao;
	}

	public PosLogDao getPosLogDao() {
		return posLogDao;
	}

	public void setPosLogDao(PosLogDao posLogDao) {
		this.posLogDao = posLogDao;
	}

	public String segResult(String input){
		//need to modify
		SegLog segLog = segLogDao.findNews(input);
		return segLog.getOriginal_seg();
	}
	
	public List<Pair<String, String>> nerResult(String input){
		NerLog nerLog = nerLogDao.findNews(input);
		String result = null;
		if(nerLog.getChangeflag().equals("0"))
			result = nerLog.getOriginal_ner();
		else 
			result = nerLog.getChange_ner();
		System.out.println("result:"+result);
		List<Pair<String, String>>s = new ArrayList<Pair<String, String>>();
		if(result.length()<=2)return null;
		String []list = result.substring(1,result.length()-1).split(",");
		for(String t:list){
			String[] f = t.split("=");
			s.add(new Pair<String,String>(f[0],f[1]));
		}
		return s;
	}
	public void nerChange(String result,String database_id){
		nerLogDao.nerChange(result,database_id);
	}
	
	public List<Pair<String, String>> tagResult(String input){
		PosLog posLog = posLogDao.findNews(input);
		String result = null;
		if(posLog.getChangeflag().equals("0"))
			result = posLog.getOriginal_pos();
		else 
			result = posLog.getChange_pos();
		List<Pair<String, String>>s = new ArrayList<Pair<String, String>>();
		String []list = result.substring(1,result.length()-1).split(",\\s");
		for(String t:list){
			String[] f = t.split("=");
			s.add(new Pair<String,String>(f[0],f[1]));
		}
		return s;
	}
	
}
