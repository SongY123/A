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
	 * �ж�word�Ƿ���wordlist��
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
		String []zai = {"��","��","��","פ","��","��","��","��"};
		String []and = {"��","��","ͬ","��"};
		String []dui = {"��","��","��","ʹ","��","��","��","Ϊ"};
		String []bei = {"��","��","��","��","��"};
		String []particular = {"��","��","��","��","��"};
		String []t1 = {"˵","����","��Ӧ","����","ָ��","��","��",":","��","��ʾ","ָ��","����"};
		String []posvalue = {"����","����","������","������","��ɫ��","����"};	
		String []posvalue1 = {"��λ��","����","����","��ĸר��","��׺","����","ר������","ָʾ����","�˳ƴ���"};	//
		String []segvalue = {"Ů","��","��","��","��","��","ǰ","��"};	
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
	 * ��words[]���wordlist
	 * @param words
	 * @param wordlist
	 */
	public  void Fill(String[]words,ArrayList<String> wordlist){
		for(int i=0;i<words.length;i++){
			wordlist.add(words[i]);
		}
		
	}
	
	/**
	 * ��̬����תΪ����
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
	 * Ԥ����ȥ��һЩ��׸��
	 * @param newsInput
	 * @return
	 */
	public  String preInput(String newsInput){
		String s = "";
		if(newsInput.endsWith("Ƶ��")){
		    newsInput = newsInput.substring(0,newsInput.indexOf("Ƶ��")-2).trim();
		}
		if(newsInput.contains("��������ͼ��")){
			newsInput = newsInput.replace("��������ͼ��", "");
		}
		if(newsInput.contains("����ͼ��")){
			newsInput = newsInput.replace("����ͼ��", "");
		}
		if(newsInput.contains("��ͼ��")){
			newsInput = newsInput.replace("��ͼ��", "");
		}
		if(newsInput.contains("�����壩")){
			newsInput = newsInput.replace("�����壩", "");
		}
		if(newsInput.contains("(ͼ)")){
			newsInput = newsInput.replace("(ͼ)", "");
		}
		
		s = newsInput;
		return s;
	}
	/**
	 * �����طִ�
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
					//��ǰʵ���ʶΪ����
					pos[i] = "ns";
				}else if(table.equals("roleinfor")){
					//��ǰʵ���ʶΪ��֯��
					pos[i] = "ni";
				}else if(table.equals("personinfor")){
					//��ǰʵ���ʶΪ����
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
//		System.out.println("��һ���طִ�֮��");	
		ArrayList <String>newseg = new ArrayList<String>();
		ArrayList <String>newpos  = new ArrayList<String>();
		Afresh_Seg_LTP(newsegment,newtag,0,newtag.length,newwords,newpostags);
	}
	/**
	 * ���ݹ����ʵ��������طִ�
	 * @param segment
	 * @param pos
	 * @param start
	 * @param end
	 * @param newseg
	 * @param newpos
	 */
	public  void Afresh_Seg_LTP(String segment[],String pos[],int start,int end,List newseg,List newpos ){
		//�طִ�
//		for(String s:segment)System.out.println(s);
		for(int i=start;i<end;i++){		
			String compoundpos = pos[i];
			String compound = segment[i];
			//System.out.println("��ǰʵ�壺"+compound);
			if(Settag(compoundpos,Ltpposvalue)||Settag(compound,Segvalue)){//��ǰ��Ϊʵ��
			//	System.out.println("��ǰʵ�壺"+compound);
				for(int j=i+1;j<end;j++){
					if(Settag(pos[j],Ltpposvalue)||Settag(segment[j],Segvalue)){
				//	System.out.println("��ǰʵ��11��"+segment[j]);
						compound += segment[j];
						compoundpos = pos[j];
						i = j;
					}
					else {i = j-1;break;}
				}
				if(!Settag(compoundpos,Ltpposvalue))compoundpos = "n";
			}else if(compound.equals("��")){
				for(int j=i+1;j<end;j++){
					if(!segment[j].equals("��")){compound += segment[j];}
						//System.out.println("��ǰʵ��11��"+segment[j]);
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
						//System.out.println("��ǰʵ��11��"+segment[j]);
					else{
						compound += segment[j];
						i = j;
						break;
					}
				}
				compoundpos = "n";
			}else if(compound.equals("��")){
				for(int j=i+1;j<end;j++){
					if(!segment[j].equals("��")){compound += segment[j];}
						//System.out.println("��ǰʵ��11��"+segment[j]);
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
