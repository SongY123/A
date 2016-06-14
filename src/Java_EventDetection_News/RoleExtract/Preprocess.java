package Java_EventDetection_News.RoleExtract;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sun.rowset.internal.Row;

import Label.ConnectLabelDB;
import edu.hit.ir.ltp4j.Pair;

public class Preprocess {
	private ConnectLabelDB connectLabelDB ;
	private   ArrayList<String> Potherpos = new ArrayList<String>();
	private  ArrayList<String> T1paticular = new ArrayList<String>();
	private ArrayList<String> Ltpposvalue = new ArrayList<String>();
	private ArrayList<String> Pand = new ArrayList<String>();
	private ArrayList<String> Pdui = new ArrayList<String>();
	private ArrayList<String> Pzai = new ArrayList<String>();
	private ArrayList<String> Pbei = new ArrayList<String>();
	private ArrayList<String> T1 = new ArrayList<String>();
	private ArrayList<String> Posvalue = new ArrayList<String>();
	private ArrayList<String> Segvalue = new ArrayList<String>();
	private ArrayList<String> Posvalue1 = new ArrayList<String>();
	private ArrayList<String> Pparticular = new ArrayList<String>();

	public static void main(String[]args) throws SQLException, IOException{
	
		List<Pair<String, String>> seg_tag_before = new ArrayList();
		System.out.println(seg_tag_before);
	}
	  
	  public ConnectLabelDB getConnectLabelDB() {
		  return connectLabelDB;
	  }
	  public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		  this.connectLabelDB = connectLabelDB;
	  }
	  public void init() {
//		  System.out.println('a');
		  SetPword();
//		  System.out.println('b');
	  }
	/**
	 * 判断word是否在wordlist中
	 * @param word
	 * @param wordlist
	 * @return
	 */
	
	public  boolean Settag(String word,ArrayList<String> wordlist){
		boolean tag = false;
		for(int i=0;i<wordlist.size();i++){
			if(wordlist.get(i).equals(word)){
				tag = true;
				break;
			}
		}
		return tag;
	}
	
	
	public  void SetPword(){
		String []zai = {"在","于","就","驻","派","用","入","因"};
		String []and = {"和","与","同","及"};
		String []dui = {"对","向","令","使","把","将","从","为"};
		String []bei = {"被","遭","受","获","引"};
		String []particular = {"朝","澳","缅","土","叙"};
		String []t1 = {"说","宣布","回应","发表","指出","称","曝",":","：","表示","指出","发布","同意","计划","打算","声称"};
		String []posvalue = {"人名","地名","国家名","机构名","角色名","名词"};	
		String []posvalue1 = {"方位词","量词","数词","字母专名","后缀","代词","专有名词","指示代词","人称代词"};	//
		String []segvalue = {"女","男","副","正"};	
		String []ltpposvalue = {"nh","j","ni","nz","ns","ws","nr","ndev"};//人名，缩写，组织名，位置，地理位置，外来名词，角色名
		String []t1paticular = {"说","宣布","指出","称","曝",":","：","计划","打算","声称"};
		String []potherpos = {"ADV","ATT","CMP","COO","LAD","RAD"};//

		Fill(t1paticular,T1paticular);
		Fill(potherpos, Potherpos);
		Fill(ltpposvalue,getLtpposvalue());
		Fill(posvalue1,Posvalue1);
		Fill(segvalue,Segvalue);
		Fill(posvalue,Posvalue);
		Fill(zai,Pzai);
		Fill(and,Pand);
		Fill(dui,Pdui);
		Fill(bei,Pbei);
		Fill(t1,getT1());
		Fill(particular,Pparticular);
	}
	
	/**
	 * 用words[]填充wordlist
	 * @param words
	 * @param wordlist
	 */
	public  void Fill(String[]words,ArrayList<String> wordlist){
		for(int i=0;i<words.length;i++){
			wordlist.add(words[i]);
		}
		
	}
	
	/**
	 * 在wordlist中寻找word
	 * @param word
	 * @param wordlist
	 * @return
	 */
	public  int Findpos(String word,ArrayList<String> wordlist){
		int pos = -1;
		for(int i=0;i<wordlist.size();i++){
			if(wordlist.get(i).equals(word)){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	
	/**
	 * List2ArrayList,将联合数组分别复制给两个单独的数组
	 * @param seg_tag_before
	 * @param newseg
	 * @param newspos
	 */
	public  void List2ArryList(List<Pair<String, String>> seg_tag_before,ArrayList<String> newseg,ArrayList<String> newspos){
		for(int i=0;i<seg_tag_before.size();i++){
			newspos.add(seg_tag_before.get(i).second);
			newseg.add(seg_tag_before.get(i).first);
		}
	}
	
	/**
	 * 动态链表复制给数组
	 * @param seg_tag
	 * @param newseg
	 * @param newspos
	 */
	public  void List2Arry(List<Pair<String, String>> seg_tag,String[]newseg,String[]newspos){
		for(int i=0;i<seg_tag.size();i++){
			newspos[i] = seg_tag.get(i).second;
			newseg[i] = seg_tag.get(i).first;
		}
	}
	/**
	 * 动态数组转为数组
	 * @param newseg
	 * @param seg
	 */
	public  void Arraylist2Array(ArrayList<String> newseg, String[] seg) {
		// TODO Auto-generated method stub
		for(int i=0;i<newseg.size();i++){
			seg[i] = newseg.get(i);
		}
	}
	
	
	/**
	 * 预处理，去掉一些累赘词
	 * @param newsInput
	 * @return
	 */
	public  String preInput(String newsInput){
		String s = "";
		if(newsInput.endsWith("频道")){
		    newsInput = newsInput.substring(0,newsInput.indexOf("频道")-2).trim();
		}
		if(newsInput.contains("（高清组图）")){
			newsInput = newsInput.replace("（高清组图）", "");
		}
		if(newsInput.contains("（组图）")){
			newsInput = newsInput.replace("（组图）", "");
		}
		if(newsInput.contains("（图）")){
			newsInput = newsInput.replace("（图）", "");
		}
		if(newsInput.contains("（高清）")){
			newsInput = newsInput.replace("（高清）", "");
		}
		if(newsInput.contains("(图)")){
			newsInput = newsInput.replace("(图)", "");
		}
		
		s = newsInput.replaceAll(" ", "，");
		return s;
	}

	/**
	 * 进行重分词
	 * @param words
	 * @param postags
	 * @return 
	 * @throws SQLException 
	 */
	public  void processLTP(List<String> words, List<String> postags,List<String> newwords, List<String> newpostags) throws SQLException {
		// TODO Auto-generated method stub
//		String []Role = {"组织","论坛","运动","团体","联盟","市场","中心","党","银行","媒"}; 
//		System.out.println("预处理之前：");
//		for(int i=start;i<end;i++){
//			System.out.print(segresult[i]+"/"+pos[i]+"  ");
//		}
//		System.out.println();
		
		List<Map<String, Object>> sn = this.connectLabelDB.searchall();
		List <Pair<String, String>>database = new ArrayList();
		for(int i=0;i<sn.size();i++){
			Pair<String, String> element = new Pair<String,String>((String)sn.get(i).get("nername"),(String)sn.get(i).get("relatingtable"));
			database.add(element);
		}
		String segresult[] = new String[words.size()];
		String pos[] = new String[words.size()];
		Arraylist2Array((ArrayList<String>) words,segresult);
		Arraylist2Array((ArrayList<String>) postags,pos);


		for(int i=0;i<words.size();i++){
			if(Settag(pos[i],getLtpposvalue() )){
				continue;
			}else if(Settag(segresult[i], Pparticular)){
				pos[i] = "ns";
			}else if(!this.connectLabelDB.IsActor(segresult[i]).equals("")){
				String table = this.connectLabelDB.IsActor(segresult[i]);
				if(table.equals("countryinfor")||table.equals("regioninfor")){
					//当前实体标识为地名
					pos[i] = "ns";
				}else if(table.equals("roleinfor")){
					//当前实体标识为组织名
					pos[i] = "ni";
				}else if(table.equals("personinfor")){
					//当前实体标识为人名
					pos[i] = "nh";
				}
			}
		}
		
		ReconstructWord_Pos(segresult,pos,words.size(),newwords,newpostags);
	}
	/**
	 * 重新构建Word_Pos
	 * @param segresult
	 * @param pos
	 * @param size
	 * @param newwords
	 * @param newpostags
	 */
	public  void ReconstructWord_Pos(String[] segresult, String[] pos,
			int size, List<String> newwords, List<String> newpostags) {
		// TODO Auto-generated method stub
		ArrayList <String>newseg0 = new ArrayList<String>();
		ArrayList <String>newpos0 = new ArrayList<String>();
		Afresh_Seg_LTP(segresult,pos,0,size,newseg0,newpos0);
		
		String newsegment[] = new String[newseg0.size()];
		String newtag[] = new String[newseg0.size()];
		for(int i=0;i<newseg0.size();i++){
			String segString = newseg0.get(i);
			newsegment[i] = segString;
			String poString = newpos0.get(i);
			newtag[i] = poString;
		}
//		System.out.println("第一次重分词之后newseg0："+newseg0);	
//		System.out.println("第一次重分词之后newpos0："+newpos0);	

		ArrayList <String>newseg = new ArrayList<String>();
		ArrayList <String>newpos  = new ArrayList<String>();
		Afresh_Seg_LTP(newsegment,newtag,0,newtag.length,newwords,newpostags);
	}

	/**
	 * 根据规则和实体表，进行重分词
	 * @param segment
	 * @param pos
	 * @param start
	 * @param end
	 * @param newseg
	 * @param newpos
	 */
	public  void Afresh_Seg_LTP(String segment[],String pos[],int start,int end,List newseg,List newpos ){
		for(int i=start;i<end;i++){		
			String compoundpos = pos[i];
			String compound = segment[i];
			if(Settag(compoundpos,getLtpposvalue())){//当前词为实体
				//	System.out.println("当前实体："+compound);						
				for(int j=i+1;j<end;j++){
					if(Settag(pos[j],getLtpposvalue())){
					//	System.out.println("当前实体11："+segment[j]);
						compound += segment[j];
						compoundpos = pos[j];
						i = j;
					}
					else {i = j-1;break;}
				}
			}else if(compoundpos.equals("b")&&i<end-1&&pos[i+1].equals("nr")){//副词+角色->nh
				compound = compound.concat(segment[i+1]);
				compoundpos = "nh";
				i++;
			}
			else if(i<end-1&&pos[i+1].equals("nr")&&segment[i+1].length()==1){//前缀+单位长度角色role
				compound = compound.concat(segment[i+1]);
				compoundpos = "nh";
				i++;
			}
			//System.out.println("当前实体："+compound);
			else if(i<end-1&&pos[i+1].equals("ndev")&&segment[i+1].length()==1){//前缀+单位长度device
				compound = compound.concat(segment[i+1]);
				compoundpos = "nh";
				i++;
			}
			if(compoundpos.equals("nr")||compoundpos.equals("ndev"))compoundpos = "nh";//role->nh,device->nh	
			newseg.add(compound);
			newpos.add(compoundpos);
		}
	}

	public ArrayList<String> getPotherpos() {
		return Potherpos;
	}

	public void setPotherpos(ArrayList<String> potherpos) {
		Potherpos = potherpos;
	}

	public ArrayList<String> getT1paticular() {
		return T1paticular;
	}

	public void setT1paticular(ArrayList<String> t1paticular) {
		T1paticular = t1paticular;
	}

	public ArrayList<String> getPand() {
		return Pand;
	}

	public void setPand(ArrayList<String> pand) {
		Pand = pand;
	}

	public ArrayList<String> getPdui() {
		return Pdui;
	}

	public void setPdui(ArrayList<String> pdui) {
		Pdui = pdui;
	}

	public ArrayList<String> getPzai() {
		return Pzai;
	}

	public void setPzai(ArrayList<String> pzai) {
		Pzai = pzai;
	}

	public ArrayList<String> getPbei() {
		return Pbei;
	}

	public void setPbei(ArrayList<String> pbei) {
		Pbei = pbei;
	}

	public ArrayList<String> getPosvalue() {
		return Posvalue;
	}

	public void setPosvalue(ArrayList<String> posvalue) {
		Posvalue = posvalue;
	}

	public ArrayList<String> getSegvalue() {
		return Segvalue;
	}

	public void setSegvalue(ArrayList<String> segvalue) {
		Segvalue = segvalue;
	}

	public ArrayList<String> getPosvalue1() {
		return Posvalue1;
	}

	public void setPosvalue1(ArrayList<String> posvalue1) {
		Posvalue1 = posvalue1;
	}

	public ArrayList<String> getPparticular() {
		return Pparticular;
	}

	public void setPparticular(ArrayList<String> pparticular) {
		Pparticular = pparticular;
	}

	public ArrayList<String> getLtpposvalue() {
		return Ltpposvalue;
	}

	public void setLtpposvalue(ArrayList<String> ltpposvalue) {
		Ltpposvalue = ltpposvalue;
	}

	public ArrayList<String> getT1() {
		return T1;
	}

	public void setT1(ArrayList<String> t1) {
		T1 = t1;
	}
	
}
