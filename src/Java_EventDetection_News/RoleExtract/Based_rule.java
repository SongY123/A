package Java_EventDetection_News.RoleExtract;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.hit.ir.ltp4j.NER;
import edu.hit.ir.ltp4j.Pair;
import edu.hit.ir.ltp4j.Parser;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.Segmentor;
import Label.ConnectLabelDB;
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
	private  ArrayList<Pair<String,String>> nername_table;
	private  ConnectLabelDB  connectLabelDB;
	private  Preprocess preprocess;
	private  DependencyParser_LTP dependencyParser_LTP;
	private int posIndex;        //指针
    private int len;             //长度
    private int maxLen;          //最大长度
	
    public void init(){
		dependencyParser_LTP.init();
		preprocess.init();
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
    
	public Based_rule(ConnectLabelDB connectLabelDB,Preprocess preprocess,DependencyParser_LTP dependencyParser_LTP) {
		this.connectLabelDB = connectLabelDB;
		this.preprocess = preprocess;
		this.dependencyParser_LTP = dependencyParser_LTP;
		this.nername_table = new ArrayList<Pair<String,String>>();
		Fillner_table(this.nername_table);
	}
	
	//从数据库里找到ner_table
	public  void Fillner_table(ArrayList<Pair<String, String>> nername_table) {
		try {
			nername_table = this.connectLabelDB.selectnertable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//找到source/target
	public  String[] setactor(String s,String triggerword,int eventType) throws SQLException,ClassNotFoundException, IOException{
		s = this.preprocess.preInput(s);
		String actor[] = {"",""};
		if(triggerword==null||s==null||s.equals("")||triggerword.equals("")||s.indexOf(triggerword)==-1){
			return actor;
		}else{
			int triggerpos = s.indexOf(triggerword);
			int T1pos = -1;	
			int T1len = 0;
			String t1="",st1 = "";
			for(int i=0;i<this.preprocess.getT1().size();i++){
				if((s.substring(0, triggerpos).indexOf(this.preprocess.getT1().get(i).trim()))!= -1){
					T1pos=s.indexOf(this.preprocess.getT1().get(i).trim());
					t1 = this.preprocess.getT1().get(i);
					T1len = t1.length();
					break;
				}
			}
			if(T1pos!=-1){
				actor = PreInput((s.substring(T1pos+T1len,s.length())),triggerword,eventType);
				if(discusstag(actor[0]).equals(""))actor[0] = s.substring(0, T1pos);
				
				else {
					int yu = -1;
					for(int i=0;i<this.preprocess.getPand().size();i++){
						if(actor[0].startsWith(this.preprocess.getPand().get(i))){
							yu = i;
							break;
						}
					}
					if(yu!=-1)actor[0] = s.substring(0, T1pos).concat("_"+actor[0].substring(this.preprocess.getPand().get(yu).length()-1, actor[0].length()));
				}
					
			}else 
				actor = PreInput(s,triggerword,eventType);
			
			for(int i = 0;i<actor.length;i++){
				actor[i] = discusstag(actor[i]);
			}
			return actor;
		}
	}
	
	// 抽取source和target
	public  String[] PreInput(String newsInput,String triggerWord,int eventType) throws SQLException, 
	 ClassNotFoundException, IOException{
		String actorrule[] = {"",""};
		List<String> words = new ArrayList<String>();
		List<String> postags = new ArrayList<String>();
		ConstruceResegment(newsInput,triggerWord,words,postags);
    	actorrule = extractRole(newsInput, triggerWord, words, postags);
		if(actorrule[0].equals("")&&actorrule[1].equals("")){
			if(eventType==1&&this.preprocess.Settag(triggerWord, this.preprocess.getT1paticular())){
				actorrule[0] = newsInput.substring(0, newsInput.indexOf(triggerWord));
			}else {
				actorrule = findactor(newsInput,triggerWord,words,postags);
			}
		}
		return actorrule;
	}
	
	//重新进行分词的处理
	private  void ConstruceResegment(String newsInput,
			String triggerWord, List<String> words, List<String> postags) {
		// TODO Auto-generated method stub
		List<String> words0 = new ArrayList<String>();
		List<String> postags0 = new ArrayList<String>();
		List<Pair<String, String>> word_pos_1 = Resegment(newsInput.substring(0,newsInput.indexOf(triggerWord)), words0, postags0);
		List<String> words1 = new ArrayList<String>();
		List<String> postags1 = new ArrayList<String>();
		List<Pair<String, String>> word_pos_2 = Resegment(newsInput.substring(newsInput.indexOf(triggerWord)+triggerWord.length(),newsInput.length()), words1, postags1);
		for(Pair<String, String> e:word_pos_1){
			words.add(e.first);
			postags.add(e.second);
		}
		words.add(triggerWord);
		postags.add("v");
		for(Pair<String, String> e:word_pos_2){
			words.add(e.first);
			postags.add(e.second);
		}
	}

	//对句子进行分词处理
	private  void Segment(String newsInput, String triggerWord,
			List<String> words, List<String> postags) {
		if(!triggerWord.equals("-1")){
			int position = newsInput.indexOf(triggerWord);
			Segmentor.segment(newsInput.substring(0, position), words);
			words.add(triggerWord);
			List<String> words1 = new ArrayList<String>();
			Segmentor.segment(newsInput.substring(position+triggerWord.length(), newsInput.length()), words1);
			for(int i=0;i<words1.size();i++){
				words.add(words1.get(i));
			}
		}else 
			Segmentor.segment(newsInput, words);
		Postagger.postag(words, postags);
		List<String> newwords = new ArrayList();
		List<String> newpostags =  new ArrayList();
		try {
			this.preprocess.processLTP(words,postags, newwords, newpostags);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		words = newwords;
		postags = newpostags;
		
	}

	// 按照规则抽取的结果
	public  String [] extractRole(String newsInput,String triggerword,List<String>words,List<String>postags) throws SQLException, IOException{
		String actorHz[] = Rule(newsInput,triggerword,words,postags,this.preprocess.getPand());
		String actorDui[] = Rule(newsInput,triggerword,words,postags,this.preprocess.getPdui());
		String actorBei[] = Rule(newsInput,triggerword,words,postags,this.preprocess.getPbei());
        String actor[] = {"",""};
		if(!(actorHz[0].equals("")&&actorHz[1].equals("")))return actorHz;//XX与XXtriggerWord
		else if(!(actorDui[0].equals("")&&actorDui[1].equals("")))return actorDui;//XX将/把/向/对  XXtriggerWord
		else if(!(actorBei[0].equals("")&&actorBei[1].equals(""))){
			actor[0] = actorBei[1];
			actor[1] = actorBei[0];
		}
	    return actor;
	}
	
	// 规则
	private  String[] Rule(String newsInput, String triggerWord,
			List<String> words, List<String> postags, ArrayList<String> pdui) {
		String actor[] = {"",""};
		int beipos = -1;
		int hzpos = -1;
		int tempbeipos = -1; 
		String bei= "";
		for(String s:pdui){
			if((tempbeipos=IndexARR(words, s))>-1){
				//System.out.println("newsInput.substring():"+newsInput.substring(beipos,beipos+1));
				bei = s;
				beipos = tempbeipos;
				break;
			}
		}
		hzpos=IndexARR(words, triggerWord);
		if((hzpos==words.size()-1)&&beipos>-1&&hzpos>beipos){
			actor[0] = construct(words, 0, beipos-1);
			actor[1] = construct(words, beipos+1, hzpos-1);
		}
		return actor;
	}

   // 根据依存句法分析结果进行sourc/target抽取
	private  String[] findactor(String newsInput, String triggerWord,List<String> newwords,List<String> newpostags) throws SQLException {
		String[] actor = new String[2];
		actor[0] = "";
		actor[1] = "";
		List<String> ners =  new ArrayList();		
		NER.recognize(newwords, newpostags, ners);
		List<Integer> heads = new ArrayList();
		List<String> deprels  = new ArrayList();
		int size = Parser.parse(newwords,newpostags,heads,deprels);		
		List<edu.hit.ir.ltp4j.Pair<Integer, List<edu.hit.ir.ltp4j.Pair<String, edu.hit.ir.ltp4j.Pair<Integer, Integer>>>>> srls = new ArrayList();
		srls = this.dependencyParser_LTP.SRL(newwords, newpostags, ners, heads, deprels);
		newpostags.set(newwords.indexOf(triggerWord), "v");
		String actor_trigger_Srl[] = {"",""};
        String actor_trigger_deprel[] = {"",""};
        String actor_newtrigger_Srl[] = {"",""};
        String actor_newtrigger_deprel[] = {"",""};
        
        actor = ActorBySrl(triggerWord,srls,newwords,newpostags);
        if(!IsFull(actor)){//srl
            actor_trigger_deprel = ActorBydeprel(triggerWord,heads,deprels,newwords,newpostags);//deprl
            actor = Combine(actor,actor_trigger_deprel);
            if(!IsFull(actor)){
            	String newtrigger = findOther(triggerWord,heads,deprels,newwords,newpostags);
        		if(!newtrigger.equals("")){
        			actor_newtrigger_Srl = ActorBySrl(newtrigger,srls,newwords,newpostags);//向前找并列结构
        			if(this.preprocess.Settag(newtrigger, this.preprocess.getPbei()))ExchangerArray(actor_newtrigger_Srl);
        			actor = Combine(actor,actor_newtrigger_Srl);
        		    if(!IsFull(actor)){
        		    	actor_newtrigger_deprel = ActorBydeprel(newtrigger,heads,deprels,newwords,newpostags);
            			if(this.preprocess.Settag(newtrigger, this.preprocess.getPbei()))ExchangerArray(actor_newtrigger_deprel);
    		            actor = Combine(actor,actor_newtrigger_deprel);
        		    }
        		}
            }
        }
      	
		if(actor[0].contains(triggerWord))actor[0] = actor[0].replaceAll(triggerWord, "");
		if(actor[1].contains(triggerWord))actor[1] = actor[1].replaceAll(triggerWord, "");
		return actor;
	}
	/**
	 * 交换数组中的元素
	 * @param actor_newtrigger_Srl
	 */
	
	private  void ExchangerArray(String[] arr) {
	// TODO Auto-generated method stub
		String temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}

	private  String[] Combine(String[] arr1,
		String[] arr2) {
	// TODO Auto-generated method stub
		if(arr1[0].equals("")&&!arr2[0].equals("")&&!arr2[0].equals(arr1[1]))arr1[0] = arr2[0];
		if(arr1[1].equals("")&&!arr2[1].equals("")&&!arr2[1].equals(arr1[0]))arr1[1] = arr2[1];

		return arr1;
	
}

	/**
	 * 是否全部非空，全部非空则为true
	 * @param actor_trigger_Srl
	 * @return
	 */
	private  boolean IsFull(String[] actor_trigger_Srl) {
	// TODO Auto-generated method stub
		boolean flag = true;
		for(String s:actor_trigger_Srl){
			if(s.equals("")){
				flag = false;
				break;
			}
		}
	return flag;
}

	// 通过根据当前触发词在srls中的语义
	private  String findOther(
		String triggerWord,
		List<Integer> heads,
		List<String> deprels, List<String> newwords, List<String> newpostags) {
		String newword = "";
		String actor[] = {"",""};
		int triggerPos = IndexARR(newwords,triggerWord);
		int newwordpos = Integer.valueOf(heads.get(triggerPos))-1;
		if(triggerPos!=-1){
			String relation = deprels.get(triggerPos);
			if(relation.equals("VOB")||relation.equals("SBV")||relation.equals("COO")||relation.equals("IOB")||relation.equals("POB")||relation.equals("FOB")){
				newword = findOther(newwords.get(newwordpos),heads, deprels, newwords, newpostags);
			}else
				return triggerWord;
		}
	return newword;
}

	// 根据触发词、heads、deprels返回相关的sub/target
	private  String[] ActorBydeprel(String triggerWord, List<Integer> heads,
		List<String> deprels, List<String> newwords, List<String> newpostags) throws SQLException {
		String actor[] = {"",""};
		String temp0 = "";
		String temp1 = "";
		int triggerpos = newwords.indexOf(triggerWord);
		//System.out.println("triggerpos："+triggerpos);
		//找直接主语和直接宾语
		if(triggerpos!=-1){
			for(int i=0;i<heads.size();i++){
				String word = newwords.get(i);
				int position = Integer.valueOf(heads.get(i))-1;
				if(position== triggerpos&&deprels.get(i).equals("SBV"))temp0 = temp0.concat(word);
				else if(position == triggerpos&&(deprels.get(i).equals("VOB")||deprels.get(i).equals("POB")
						||deprels.get(i).equals("IOB")||deprels.get(i).equals("FOB"))&&!temp1.contains(word))temp1 = temp1.concat(word);
			}
		}
		//在触发词前面找间接主语
		if(temp0.equals("")){
			for(int i=0;i<triggerpos;i++){
				String word = newwords.get(i);
				//int position = Integer.valueOf(heads.get(i))-1;
				if(deprels.get(i).equals("SBV")&&isPath(i,triggerpos,heads,deprels)&&!temp0.contains(word))temp0 = temp0.concat(word);
			}
		}
		//在触发词后找间接宾语。。还应该判断实体是不是连在一起。
		if(temp1.equals("")){
			for(int i=triggerpos+1;i<heads.size();i++){
				String word = newwords.get(i);
			//	int position = Integer.valueOf(heads.get(i))-1;
				if((deprels.get(i).equals("VOB")||deprels.get(i).equals("POB")
						||deprels.get(i).equals("IOB")||deprels.get(i).equals("FOB"))
						&&isPath(triggerpos,i,heads,deprels)&&!temp1.contains(word))temp1 = temp1.concat(word);
			}
		}
		actor = FillActor(temp0,temp1,heads,deprels,newwords);
		actor[0] = discusstag(actor[0]);
		actor[1] = discusstag(actor[1]);
	return actor;
}
	

	// 两个节点之间有路径
	private  boolean isPath(int start, int end, List<Integer> heads,
			List<String> deprels) {
		boolean flag = false;
		int startpoint = Integer.valueOf(heads.get(start))-1;
		if(startpoint == end){
			flag = true;
			return flag;
		}else if(startpoint != -1){
			try {
				flag = isPath(startpoint,end,heads,deprels);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	// 填充实体，找到所有的修饰词
	private  String[] FillActor(String temp1, String temp2, List<Integer> heads,
			List<String> deprels, List<String> words) {
		String actor[] = {"",""};
		int temp1pos = words.indexOf(temp1)+1;
		int temp2pos = words.indexOf(temp2)+1;
		for(int i=0;i<heads.size();i++){
			int point = Integer.valueOf(heads.get(i));
			if(point==temp1pos&&preprocess.Settag(deprels.get(i),this.preprocess.getPotherpos())&&
					!words.get(i).equals(temp2)||i+1==temp1pos){
				actor[0] = actor[0].concat(words.get(i));
			}			
			else if(point==temp2pos&&preprocess.Settag(deprels.get(i),preprocess.getPotherpos())&&
					!words.get(i).equals(temp1)||i+1==temp2pos){
				actor[1] = actor[1].concat(words.get(i));
			}
				
		}
		if(actor[0].equals(""))actor[0] = temp1;
		if(actor[1].equals(""))actor[1] = temp2;
		return actor;
	}
	
	// 根据触发词、srls返回相关的sub/target
	private  String[] ActorBySrl(
		String triggerWord,
		List<edu.hit.ir.ltp4j.Pair<Integer, List<edu.hit.ir.ltp4j.Pair<String, edu.hit.ir.ltp4j.Pair<Integer, Integer>>>>> srls, List<String> newwords, List<String> newpostags) throws SQLException {
		String actor[] = {"",""};
		if(!triggerWord.equals("-1")){
			for (int i = 0; i < srls.size(); i++) {
				if(newwords.get(srls.get(i).first).equals(triggerWord)){
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
		actor[0] = discusstag(actor[0]);
		actor[1] = discusstag(actor[1]);
		return actor;
	}
	// 返回指定起始和结束位置的words串
	private  String construct(List<String> words, int begin,int end) {
		String word = "";
		for(int i=begin;i<=end;i++)word = word.concat(words.get(i));
		return word;
	}

	// 返回当前短语所包含的实体
	public  String discusstag(String actor) throws SQLException {
		String newactor = "";
			List<Pair<String,Integer>> news = new ArrayList();
			List<String> words0 = new ArrayList();
			List<String> postags0 = new ArrayList();
			List<Pair<String, String>> words_pos = Resegment(actor, words0, postags0);
			for(int i=0;i<words_pos.size();i++){
				Pair<String, String> e = words_pos.get(i);
				String pos = e.second;
				String word = e.first;
				if(this.preprocess.Settag(pos, this.preprocess.getLtpposvalue())||isActor(word,pos)||!this.connectLabelDB.IsActor(word).equals("")){
					news.add(new Pair<String,Integer>(word,i));
				}
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
	
	//利用规则判断是否为实体
	private  boolean isActor(String word, String pos) {
		boolean flag = false;
		String Role[] = {"会","导弹","军","国","方","组织","论坛","运动","团体","联盟","市场","中心","党","银行","媒",
				"队","会议","峰会","防长","合作","警","兵","员"};
		for(String s:Role){
			if(word.contains(s)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	//对一句话进行重分词
	public  List<Pair<String, String>> Resegment(String string,List<String> words,List<String>postags){
		List<Pair<String, String>> word_pos = new ArrayList<Pair<String,String>>();
		Segmentor.segment(string, words);
		Postagger.postag(words, postags);
		try {
			word_pos = forwardMaximumMatching(6, words, postags);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return word_pos;
	}
	
	 //---正向---
    public  List<Pair<String, String>> forwardMaximumMatching(int maxLength,List<String> str,List<String> pos) throws IOException{
    	List<String> newstr = new ArrayList<String>();
    	List<String> newpos = new ArrayList<String>();
        posIndex = 0;
        len = maxLength;
        maxLen = len;
        List<Pair<String, String>> Word_Pos = MM(str,pos, maxLen, 0,newstr,newpos);
        List<Pair<String, String>>  newWord_Pos = new ArrayList();
        String newsegment[] = new String[Word_Pos.size()];
		String newtag[] = new String[Word_Pos.size()];
		for(int i=0;i<Word_Pos.size();i++){
			 Pair<String, String> segString = Word_Pos.get(i);
			newsegment[i] = segString.first;
			newtag[i] = segString.second;
		}
		ArrayList <String> newseg= new ArrayList<String>();
		ArrayList <String>newpostag  = new ArrayList<String>();
		preprocess.ReconstructWord_Pos(newsegment,newtag,newtag.length,newseg,newpostag);		
		return Constructor(newseg,newpostag);
    }
    
  //最大匹配  
    public  List<Pair<String, String>> MM(List<String> source,List<String> pos, int len,int frompos, List<String> newstr, List<String> newpos) throws IOException{
    	if(posIndex>source.size()-1){
            return Constructor(newstr,newpos);
        }
        if(source.size()-frompos<len){
            len = source.size()-frompos;
        }
        String tmp = findsubstring(source,frompos, frompos+len);  //获得子串
        
        
        boolean flag = false;
        String posString = "";
        for(Pair<String, String> e:nername_table){
        	if(e.first.equals(tmp)){
        		flag = true;
        		posString = e.second;
        		break;
        	}
        }
        if(flag==true){  //字典里面有当前词里面存的是词
        	newstr.add(tmp);
        	newpos.add(changepos(posString));
            posIndex += len;
            len = maxLen;  //重置长度
            MM(source,pos, len, posIndex,newstr,newpos);
        }else if(len>1){

        	len -= 1;
            MM(source,pos, len, posIndex,newstr,newpos);
        }else{//字典中没有当前词或其任意组合
        	newstr.add(tmp);
        	newpos.add(pos.get(frompos));
            posIndex += 1;
            len = maxLen;
            MM(source,pos, len, posIndex,newstr,newpos);
        }
        return Constructor(newstr,newpos);
     }
    
    //--修改词性--
    private  String changepos(String table) {
    	table = table.trim();
    	if(table.equals("personinfor")) return "nh";
    	else if(table.equals("orgnizationinfor")) return "ni";
    	else if(table.equals("countryinfor")||table.equals("regioninfor")) return "ns";
    	else if(table.equals("roleinfor")) return "nr";//自己造名词，表角色
    	else if(table.equals("otherinfor")) return "nz";
    	else if(table.equals("deviceinfor")) return "ndev";//工具
    	else  return "n";		
    }
    
    //返回两个arry的list
	private  List<Pair<String, String>> Constructor(List<String> newstr,List<String> newpos) {
    	List<Pair<String, String>> newword_pos = new ArrayList<Pair<String,String>>();
    	for(int i=0;i<newpos.size();i++)newword_pos.add(new Pair<String, String>(newstr.get(i), newpos.get(i)));
	return newword_pos;
}
	/**
     * 返回子串
     * @param source
     * @param frompos
     * @param i
     * @return
     */
   private String findsubstring(List<String> source, int start, int end) {
	// TODO Auto-generated method stub
	   String subString = "";
	   for(int i=start;i<end;i++)subString = subString.concat(source.get(i).toString());
	   return subString;
   }
  
	/**
	 * 返回string在words中的索引
	 * @param ner_pos
	 * @param string
	 * @return
	 */
	private  int IndexARR(List<String> words, String string) {
			// TODO Auto-generated method stub
		int index = -1;
		for(int i=0;i<words.size();i++){
			if(string.equals((words.get(i)))){
				index = i;
				break;
			}
		} 
		return index;
	}
	//更新按钮
	public  void Refresh(int news_id,String news_source,String title,String trigger,int type) throws ClassNotFoundException, SQLException, IOException{
		this.nername_table = new ArrayList<Pair<String,String>>();
		Fillner_table(this.nername_table);
		type=type==21?0:type;//21类
		if(this.connectLabelDB.selectid_news(news_id,news_source).size()>0){
			System.out.println("title:"+title);
			System.out.println("trigger:"+trigger);
			System.out.println("type:"+type);
			String actor[] = setactor(title, trigger, type);
//			System.out.println("actor[0]:"+actor[0]);
//			System.out.println("actor[1]:"+actor[1]);

			this.connectLabelDB.UpdateLabeltoTempTable(news_id,news_source,title,trigger,type, actor[0],actor[1]);
		}else {
	//		System.out.println("当前条目不存在");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//例子
		ConnectLabelDB connectLabelDB = new ConnectLabelDB();
		Preprocess preprocess = new Preprocess();
		
		DependencyParser_LTP dependencyParser_LTP = new DependencyParser_LTP();
		
		Based_rule bsl = new Based_rule(connectLabelDB,preprocess,dependencyParser_LTP);
		bsl.init();
		try {
			//String actor[] = bsl.setactor("中国潜艇远航遭外军全方位监视 急速下潜突围？", "监视", 2);
			bsl.Refresh(317, "fenghuang", "德国也要出手：将派兵与法打击IS 不参加美国联盟", "打击",4);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
