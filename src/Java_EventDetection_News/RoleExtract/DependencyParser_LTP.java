/*  
* ava_EventDetection_News  
* @author GreatShang  
* @version 1.0   
* @since JDK 1.8.0_21  
* DependencyParser_LTP.java  
* Windows Server 2008
* 
*/
package Java_EventDetection_News.RoleExtract;
import java.util.ArrayList;
import java.util.List;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.Parser;
import edu.hit.ir.ltp4j.Segmentor;
import edu.hit.ir.ltp4j.SRL;
import edu.hit.ir.ltp4j.NER;


public class DependencyParser_LTP {
	
	public  void init(){
		String root = "C:/dll_model/";
	//	System.out.print(System.getProperty("java.library.path"));
		//System.loadLibrary("dll");
		//System.load(root+"dll/segmentor.dll"); 
		//System.load(root+"dll/postagger.dll"); 
		//System.load(root+"dll/parser.dll");  
		//System.load(root+"dll/srl.dll"); 
		//System.load(root+"dll/ner.dll");
		
		if (Parser.create(root+"ltp_data/parser.model") < 0||Segmentor.create(root+"ltp_data/cws.model") < 0||
				Postagger.create(root+"ltp_data/pos.model") < 0||SRL.create(root+"ltp_data/srl")<0
				||NER.create(root+"ltp_data/ner.model")<0) 
		{
			System.out.println("load failed");
		}
		else System.out.println("load succeed");
	}
	
	public List<edu.hit.ir.ltp4j.Pair<Integer, List<edu.hit.ir.ltp4j.Pair<String, edu.hit.ir.ltp4j.Pair<Integer, Integer>>>>> SRL(
			List<String> words, List<String> postags, List<String> ners, List<Integer> heads, 
			List<String> deprels){
		    ArrayList<Integer> newheads = new ArrayList<Integer>();
  
		  for(int i=0;i<heads.size();i++)newheads.add(heads.get(i)-1);
		  List<edu.hit.ir.ltp4j.Pair<Integer, List<edu.hit.ir.ltp4j.Pair<String, edu.hit.ir.ltp4j.Pair<Integer, Integer>>>>> srls = new ArrayList();
		  SRL.srl(words, postags, ners, newheads,deprels,srls);
		  return srls;
	}
}
