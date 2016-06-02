package Java_EventDetection_News.RoleExtract;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Label.ConnectLabelDB;
import edu.hit.ir.ltp4j.NER;
import edu.hit.ir.ltp4j.Pair;
import edu.hit.ir.ltp4j.Parser;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.Segmentor;

/**
 * 创建时间：2016.03.17
 * @author daij  
 * @version 1.0   
 * @since JDK 1.8 
 * 文件名称：Based_rule.java  
 * 系统信息：Win10
 * 类说明： 	
 * 功能描述：提供基于规则进行source_target的抽取
 * @author nlp_daij
 *
 */
public class Based_rule {
	/**
	 * @param args
	 * @throws LoadModelException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private  ConnectLabelDB  connectLabelDB;
	private  Preprocess preprocess;
	private  DependencyParser_LTP dependencyParser_LTP;
	public void init(){
		dependencyParser_LTP.init();
		preprocess.init();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//例子
		ConnectLabelDB coon = new ConnectLabelDB();
		Preprocess preprocess = new Preprocess();
		DependencyParser_LTP parse = new DependencyParser_LTP();
		Based_rule bsl = new Based_rule(coon,preprocess,parse);
		try {
			bsl.Refresh(1, "huanqiu_china","坠毁歼10飞行员50天后驾机升空 画面激动人心", "坠毁", 2);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public ConnectLabelDB getConnectLabelDB() {
		return connectLabelDB;
	}

	public void setConnectLabelDB(ConnectLabelDB connectLabelDB) {
		this.connectLabelDB = connectLabelDB;
	}

	public Preprocess getPreprocess() {
		return preprocess;
	}

	public void setPreprocess(Preprocess preprocess) {
		this.preprocess = preprocess;
	}

	public DependencyParser_LTP getDependencyParser_LTP() {
		return dependencyParser_LTP;
	}

	public void setDependencyParser_LTP(DependencyParser_LTP dependencyParser_LTP) {
		this.dependencyParser_LTP = dependencyParser_LTP;
	}

	public Based_rule() {
	}
	public Based_rule(ConnectLabelDB coon,Preprocess preprocess,DependencyParser_LTP parse) {
		this.connectLabelDB = coon;
		this.preprocess = preprocess;
		this.dependencyParser_LTP = parse;
	}
	/**
	 * 返回当前seg_tag中的所有实体和其位置
	 * @param seg_tag
	 * @param actor
	 * @throws LoadModelException 
	 * @throws SQLException 
	 */
	public  void Before_After(List<Pair<String, String>> seg_tag,List<Pair<String, Integer>> actor) throws SQLException{
		for(int i=0;i<seg_tag.size();i++){
			if(preprocess.Settag(seg_tag.get(i).second,preprocess.Posvalue)){
				Pair<String,Integer> element = new Pair<String,Integer>(seg_tag.get(i).first, i);
				actor.add(element);
			}
		}
	}
	public  String[] setactor(String s,String triggerword,int eventType) throws SQLException, ClassNotFoundException, IOException{
		String actor[] = {"",""};
		List<Pair<String, Integer>> actor_T1 = new ArrayList();	
		List<Pair<String, String>> seg_tag_T1 = new ArrayList();
		if(triggerword==null||triggerword.equals("")){
			return actor;
		}else{
		s = preprocess.preInput(s);
		int triggerpos = s.indexOf(triggerword);			
		int T1pos = -1;	
		int adverse = -1;
		int T1len = 0;
		String t1="",st1 = "";
		for(int i=0;i<preprocess.T1.size();i++){
			if((s.substring(0, triggerpos).indexOf(preprocess.T1.get(i).trim()))!= -1){
				T1pos=s.indexOf(preprocess.T1.get(i).trim());
				t1 = preprocess.T1.get(i);
				T1len = t1.length();
				break;
			}
		}
		if(T1pos!=-1){
				actor = PreInput((s.substring(T1pos+T1len,s.length())),triggerword,eventType);
				if(actor[0].equals(""))actor[0] = s.substring(0, T1pos);
		}else 
			actor = PreInput(s,triggerword,eventType);
		for(int i = 0;i<actor.length;i++){
			actor[i] = discusstag(actor[i]);
		}
		

		return actor;
	}
}
	public  String [] extractRole(String newsInput,String triggerword) throws SQLException, IOException{
		String actorHz[] = And(newsInput,triggerword);
		String actorDui[] = Dui(newsInput,triggerword);
		String actorBei[] = Bei(newsInput,triggerword);
        String actor[] = {"",""};
		if(!(actorHz[0].equals("")&&actorHz[1].equals("")))return actorHz;//XX与XXtriggerWord
		else if(!(actorDui[0].equals("")&&actorDui[1].equals("")))return actorDui;//XX将/把/向/对  XXtriggerWord
		else if(!(actorBei[0].equals("")&&actorBei[1].equals("")))return actorBei;//XX被  XXtriggerWord
		else return actor;
	}
	
	/**
	 * XX被XXtriggerWord
	 * @param newsInput
	 * @param triggerword
	 * @return
	 */
	private  String[] Bei(String newsInput, String triggerWord) {
		// TODO Auto-generated method stub
		String actor[] = {"",""};
		int duipos = -1;
		int hzpos = -1;
		for(String s:preprocess.Pbei)if((duipos=newsInput.indexOf(s))>-1){//System.out.println(s);
			break;
		}
		hzpos=newsInput.indexOf(triggerWord);
		if((hzpos+triggerWord.length()==newsInput.length())&&duipos>-1&&hzpos>duipos){
			actor[1] = (newsInput.substring(0, duipos));
			actor[0] = (newsInput.substring(duipos+1, hzpos));
		}
		return actor;
	}
	/**
	 * XX将/把/向/对XXtriggerWord
	 * @param newsInput
	 * @param triggerword
	 * @return
	 */
	private  String[] Dui(String newsInput, String triggerWord) {
		// TODO Auto-generated method stub
		String actor[] = {"",""};
		//System.out.println("Dui");
		int duipos = -1;
		int hzpos = -1;
		for(String s:preprocess.Pdui)if((duipos=newsInput.indexOf(s))>-1)break;
		hzpos=newsInput.indexOf(triggerWord);
		if((hzpos+triggerWord.length()==newsInput.length())&&duipos>-1&&hzpos>duipos){
				actor[0] = (newsInput.substring(0, duipos));
				actor[1] = (newsInput.substring(duipos+1, hzpos));
		}
		return actor;
	}
	/**
	 * 判断句型为“XX与XXtriggerWord”
	 * @param newsInput
	 * @return
	 */
	private   String[] And(String newsInput,String triggerWord) {
		// TODO Auto-generated method stub
		String actor[] = {"",""};
		int yupos = -1;
		String yu = "";
		int tempyupos = -1;
		int hzpos = -1;
		for(String s:preprocess.Pand){
			if((tempyupos=newsInput.indexOf(s))>-1&&!(s.equals("及")&&newsInput.substring(tempyupos-1, tempyupos+1).equals("埃及"))){
				yu = s;
				yupos = tempyupos;
				break;
			}
		}
		
		hzpos=newsInput.indexOf(triggerWord);
		if((hzpos+triggerWord.length()==newsInput.length())&&yupos>-1&&hzpos>yupos){
				actor[0] = (newsInput.substring(0, yupos+yu.length()));
				actor[1] = (newsInput.substring(yupos+yu.length(), hzpos));
		}
		return actor;
	}
	/**
	 * 
	 * @param id
	 * @param news_source
	 * @param title
	 * @param trigger
	 * @param type
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public  void Refresh(int id,String news_source,String title,String trigger,int type) throws ClassNotFoundException, SQLException, IOException{
		System.out.println("eventType："+type);
		type=type==21?0:type;//21类
		String actor[] = setactor(title, trigger, type);
		connectLabelDB.UpdateLabeltoTempTable(id,news_source,title,trigger,type, actor[0],actor[1]);
	}
	
	/**
	 * 主程序
	 * 1、进行预处理：去掉无用的词（图、高清）；根据空格分为前后两部分，对每一部分分别处理
	 * 2、识别触发词，分为前后两个部分
	 * 3、
	 * @param newsInput
	 * @param triggerWord
	 * @param eventType
	 * @return
	 * @throws SQLException
	 * @throws LoadModelException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public  String[] PreInput(String newsInput,String triggerWord,int eventType) throws SQLException, ClassNotFoundException, IOException{
		String actorrule[] = {"",""};
		{
			String finalactor[] = {"",""};	
			String spart[] = newsInput.split(" ");
			actorrule = extractRole(newsInput, triggerWord);
			if(actorrule[0].equals("")&&actorrule[1].equals("")){
				if(spart.length>1){
					if(spart[0].indexOf(triggerWord)!=-1){
						int T1pos = -1;
						String T1 = "";
						for(String s1:preprocess.T1){
							if(spart[0].substring(0, spart[0].indexOf(triggerWord)).indexOf(s1)!=-1){
								T1pos = spart[0].indexOf(s1);
								T1 = s1;
								break;
							}
						}
						if(T1pos!=-1){
							actorrule = findactor(spart[0].substring(T1pos+T1.length()), triggerWord);//基于规则当前句子
							if(actorrule[0].equals("")){
								String actorT1[] =  setactor(spart[0].substring(0, T1pos+T1.length()),T1,1);
								actorrule[0] = actorT1[0];
							}
						}else{
							actorrule = findactor(spart[0], triggerWord);//基于规则当前句子
						}
					}else if(spart[1].indexOf(triggerWord)!=-1){
						actorrule = findactor(spart[1], triggerWord);
						if(actorrule[0].equals("")){
							int T1pos = -1;
							String T1 = "";
							for(String s1:preprocess.T1){
								if(spart[0].indexOf(s1)!=-1){
									T1pos = spart[0].indexOf(s1);
									T1 = s1;
									break;
								}
							}						
							String actor0[] = {"",""};
							if(T1pos != -1){
								actor0 = setactor(spart[0].substring(0, T1pos+T1.length()), T1, 1);
								actorrule[0] = actor0[0];
							}
						}	
					}
				}else if(newsInput.indexOf(triggerWord)!=-1){
					int T1pos = -1;
					String T1 = "";
					for(String s1:preprocess.T1){
						if(newsInput.substring(0, newsInput.indexOf(triggerWord)).indexOf(s1)!=-1){
							T1pos = newsInput.substring(0, newsInput.indexOf(triggerWord)).indexOf(s1);
							T1 = s1;
							break;
						}
					}
				//	System.out.println("T1:"+T1);
					String actor0[] = {"",""};
					if(T1pos == -1)actorrule = findactor(newsInput,triggerWord);
					else {
						actorrule = findactor(newsInput.substring(T1pos+T1.length(),newsInput.length()),triggerWord);
						if(actorrule[0].equals("")){
							actor0 = setactor(newsInput.substring(0, T1pos+T1.length()), T1, 1);
							actorrule[0] = actor0[0];
						}
					}	
				}
			}
			/**
			 * 判断所抽出来的是否为实体
			 */
			if(actorrule[0].equals("")&&(eventType == 1&&triggerWord.equals(":")||triggerWord.equals("：")))
				actorrule[0] = newsInput.substring(0, newsInput.indexOf(triggerWord));
		}
		return actorrule;
	}
	
	
	/**
	 * 返回句子的主语和宾语
	/**
	 * 返回句子的语义角色标注
	 * @param newsInput
	 * @param triggerWord
	 * @return
	 * @throws SQLException 
	 */
	private  String[] findactor(String newsInput, String triggerWord) throws SQLException {
		// TODO Auto-generated method stub
		String[] actor = new String[2];
		actor[0] = "";
		actor[1] = "";
		if(actor[0].equals("")||actor[1].equals("")){//yyyyy
		//System.out.println("进入findactor");
			List<String> words = new ArrayList<String>();
			List<String> postags = new ArrayList<String>();
			List<String> ners = new ArrayList();
			if(!triggerWord.equals("-1")){
				int position = newsInput.indexOf(triggerWord);
			//System.out.println(newsInput.substring(0, position));
				Segmentor.segment(newsInput.substring(0, position), words);
				words.add(triggerWord);
				Segmentor.segment(newsInput.substring(position+triggerWord.length(), newsInput.length()), words);
			}else 
				Segmentor.segment(newsInput, words);
			Postagger.postag(words, postags);
			List<String> newwords = new ArrayList();
			List<String> newpostags =  new ArrayList();
			preprocess.processLTP(words,postags, newwords, newpostags);
			
			NER.recognize(newwords, newpostags, ners);
			List<Integer> heads = new ArrayList();
			List<String> deprels  = new ArrayList();
			int size = Parser.parse(newwords,newpostags,heads,deprels);
			List<edu.hit.ir.ltp4j.Pair<Integer, List<edu.hit.ir.ltp4j.Pair<String, edu.hit.ir.ltp4j.Pair<Integer, Integer>>>>> srls = new ArrayList();
			srls = dependencyParser_LTP.SRL(newwords, newpostags, ners, heads, deprels);
			if(!triggerWord.equals("-1")){
				for (int i = 0; i < srls.size(); ++i) {
					if(newwords.get(srls.get(i).first).equals(triggerWord)){
						for (int j = 0; j < srls.get(i).second.size(); ++j) {
							String Aflag = srls.get(i).second.get(j).first;
							String act = construct(newwords,srls.get(i).second.get(j).second.first,srls.get(i).second.get(j).second.second);
							if(Aflag.equals("A1"))actor[1] = act;
							else if(Aflag.equals("A0"))actor[0] = act;
						}
						break;
					}
				}
			}
		if(actor[0].equals("")&&actor[1].equals("")){
			for (int i = 0; i < srls.size(); ++i) {
					if(heads.get(srls.get(i).first)==0){
						for (int j = 0; j < srls.get(i).second.size(); ++j) {
							String Aflag = srls.get(i).second.get(j).first;
							String act = construct(newwords,srls.get(i).second.get(j).second.first,srls.get(i).second.get(j).second.second);
							if(Aflag.equals("A1"))actor[1] = actor[1].concat(act);
							else if(Aflag.equals("A0"))actor[0] = actor[0].concat(act);
						}
						break;
					}
				}
			}
		}
		return actor;
	}
	
	/**
	 * 返回指定起始和结束位置的words串
	 * @param words
	 * @param first
	 * @param second
	 * @return
	 */
	private  String construct(List<String> words, int begin,int end) {
		// TODO Auto-generated method stub
		String word = "";
		for(int i=begin;i<=end;i++)word = word.concat(words.get(i));
		return word;
	}
	
	/**
	 * 返回当前短语所包含的实体
	 * @param actor
	 * @return
	 * @throws SQLException 
	 */
	public  String discusstag(String actor) throws SQLException {
		// TODO Auto-generated method stub
		String newactor = "";
		List<Pair<String,Integer>> news = new ArrayList();
		List<String> words = new ArrayList();
		List<String> postags = new ArrayList();
		List<String> ners = new ArrayList();
		List<String> ns = new ArrayList();
		Segmentor.segment(actor, words);
		Postagger.postag(words, postags);		
		for(int i=0;i<words.size();i++){
			String pos = postags.get(i);
			if(preprocess.Settag(pos, preprocess.Ltpposvalue)||isActor(words.get(i),pos)||!connectLabelDB.isActor(words.get(i)).equals(""))
				news.add(new Pair<String,Integer>(words.get(i),i));
		}
		
		if(news.size()>0){
			newactor = news.get(0).first;
			for(int i=1;i<news.size();i++)
			{
				if(i-1>=0&&news.get(i).second == news.get(i-1).second+1)newactor = newactor.concat(news.get(i).first);
				else newactor = newactor.concat("_"+news.get(i).first);
			}
		}
		return newactor;
	}
	/**
	 * 利用规则判断是否为实体
	 * @param string
	 * @param pos
	 * @return
	 */
	private  boolean isActor(String word, String pos) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String Role[] = {"导弹","军","国","方","组织","论坛","运动","团体","联盟","市场","中心","党","银行","媒","队"};
		for(String s:Role){
			if(word.endsWith(s)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
