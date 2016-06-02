package Java_EventDetection_News.RoleExtract;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Label.ConnectLabelDB;
import edu.hit.ir.ltp4j.Pair;


public class Preprocess {
	  ArrayList<String> Ltpposvalue = new ArrayList<String>();
	  ArrayList<String> Pand = new ArrayList<String>();
	  ArrayList<String> Pdui = new ArrayList<String>();
	  ArrayList<String> Pzai = new ArrayList<String>();
	  ArrayList<String> Pbei = new ArrayList<String>();
	  ArrayList<String> T1 = new ArrayList<String>();
	  ArrayList<String> Posvalue = new ArrayList<String>();
	  ArrayList<String> Segvalue = new ArrayList<String>();
	  ArrayList<String> Posvalue1 = new ArrayList<String>();
	  ArrayList<String> Pparticular = new ArrayList<String>();
	  private ConnectLabelDB connectLabelDB ;
	  
	  public ConnectLabelDB getConnectLabelDB() {
		  return connectLabelDB;
	  }
	  public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		  this.connectLabelDB = connectLabelDB;
	  }
	public void init() {
		System.out.println('a');
		  SetPword();
		  System.out.println('b');
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
		String []t1 = {"说","宣布","回应","发表","指出","称","曝",":","：","表示","指出","发布"};
		String []posvalue = {"人名","地名","国家名","机构名","角色名","名词"};	
		String []posvalue1 = {"方位词","量词","数词","字母专名","后缀","代词","专有名词","指示代词","人称代词"};	//
		String []segvalue = {"女","男","左","右","上","下","前","后"};	
		String []ltpposvalue = {"nh","j","ni","nl","nz","ns","ws"};
		
		Fill(ltpposvalue,Ltpposvalue);
		Fill(posvalue1,Posvalue1);
		Fill(segvalue,Segvalue);
		Fill(posvalue,Posvalue);
		Fill(zai,Pzai);
		Fill(and,Pand);
		Fill(dui,Pdui);
		Fill(bei,Pbei);
		Fill(t1,T1);
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
		
		s = newsInput;
		return s;
	}
	/**
	 * 进行重分词
	 * @param words
	 * @param postags
	 * @throws SQLException 
	 */
	public  void processLTP(List<String> words, List<String> postags,List<String> newwords, List<String> newpostags) throws SQLException {
		// TODO Auto-generated method stub
		SetPword();
		String segresult[] = new String[words.size()];
		String pos[] = new String[words.size()];
		Arraylist2Array((ArrayList<String>) words,segresult);
		Arraylist2Array((ArrayList<String>) postags,pos);

		for(int i=0;i<words.size();i++){
			if(Settag(pos[i],Ltpposvalue )){
				continue;
			}else if(Settag(segresult[i], Pparticular)){
				pos[i] = "ns";
			}else if(!connectLabelDB.isActor(segresult[i]).equals("")){
				String table = connectLabelDB.isActor(segresult[i]);
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
		ArrayList <String>newseg0 = new ArrayList<String>();
		ArrayList <String>newpos0 = new ArrayList<String>();
		Afresh_Seg_LTP(segresult,pos,0,words.size(),newseg0,newpos0);
		
		String newsegment[] = new String[newseg0.size()];
		String newtag[] = new String[newseg0.size()];
		for(int i=0;i<newseg0.size();i++){
			String segString = newseg0.get(i);
			newsegment[i] = segString;
			String poString = newpos0.get(i);
			newtag[i] = poString;
		}
//		System.out.println("第一次重分词之后：");	
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
		//重分词
//		for(String s:segment)System.out.println(s);
		for(int i=start;i<end;i++){		
			String compoundpos = pos[i];
			String compound = segment[i];
			//System.out.println("当前实体："+compound);
			if(Settag(compoundpos,Ltpposvalue)||Settag(compound,Segvalue)){//当前词为实体
			//	System.out.println("当前实体："+compound);
				for(int j=i+1;j<end;j++){
					if(Settag(pos[j],Ltpposvalue)||Settag(segment[j],Segvalue)){
				//	System.out.println("当前实体11："+segment[j]);
						compound += segment[j];
						compoundpos = pos[j];
						i = j;
					}
					else {i = j-1;break;}
				}
				if(!Settag(compoundpos,Ltpposvalue))compoundpos = "n";
			}else if(compound.equals("“")){
				for(int j=i+1;j<end;j++){
					if(!segment[j].equals("”")){compound += segment[j];}
						//System.out.println("当前实体11："+segment[j]);
					else{
						compound += segment[j];
						i = j;
						break;
					}
				}
				compoundpos = "n";
			}else if(compound.equals("\"")){
				for(int j=i+1;j<end;j++){
					if(!segment[j].equals("\"")){compound += segment[j];}
						//System.out.println("当前实体11："+segment[j]);
					else{
						compound += segment[j];
						i = j;
						break;
					}
				}
				compoundpos = "n";
			}else if(compound.equals("《")){
				for(int j=i+1;j<end;j++){
					if(!segment[j].equals("》")){compound += segment[j];}
						//System.out.println("当前实体11："+segment[j]);
					else{
						compound += segment[j];
						i = j;
						break;
					}
				}
				compoundpos = "n";
			}
			newseg.add(compound);
			newpos.add(compoundpos);
		}
	}
}
