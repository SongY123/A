package Label;
/*
Created on 2015年10月19日 上午10:40:20

@author: GreatShang
*/

public class LabelItem 
{
	/*
	 * 标注数据
	 * */
	public String labelID;			//数据库中该条标注的ID
	
	public String newsSource;		//新闻来源
	public String newsID;			//新闻ID
	public String newsTitle;		//新闻标题
	public String newsContent;		//新闻正文
	
	public boolean ifEvent = false;	//是否事件相关
	public int eventType = -1;		//事件类型（1-20）
	
	public String sourceActor;		//事件发出者
	public String targetActor;		//事件承受者
	public String triggerWord;		//事件触发词
	public String sourceActorPro;   //事件发出者类型
	public String targetActorPro;   //事件承受者类型
	
	public String eventTime;		//事件发生时间
	public String eventLocation;	//事件发生地点
	public int if_remark;           //临时表是否重新标注
	public int if_confirmed;		//正式表是否确认
	public String marker_name;      //修改的人的用户名
	public String confirmed_name;   //标注的人的用户名
	
	public String actor_index;		//实体index
	public String actor_len;		//实体长度
	public String actor_Pro;		//实体类型
	public String actor;			//实体名
	
	public LabelItem(String labelID,String newsSource,String newsID,String newsTitle)
	{
		//与事件无关新闻标题标注构造函数
		this.newsSource = newsSource;
		this.labelID = labelID;
		this.ifEvent = false;
		this.newsID = newsID;
		this.newsTitle = newsTitle;
	}
	
	public LabelItem(String labelID,String newsSource,String newsID,String newsTitle,
			int eventType,String sourceActor,String targetActor,String triggerWord,String sourceActorPro, String targetActorPro,
			String eventTime,String eventLocation, String actor_index, String actor_len, String actor_Pro, String actor)
	{
		//事件有关新闻标题标注构造函数
		this.newsSource = newsSource;
		this.labelID = labelID;
		this.ifEvent = true;
		this.newsID = newsID;
		this.newsTitle = newsTitle;
		this.eventType = eventType;
		this.sourceActor = sourceActor;
		this.targetActor = targetActor;
		this.triggerWord = triggerWord;
		this.sourceActorPro = sourceActorPro;
		this.targetActorPro = targetActorPro;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.actor_index = actor_index;
		this.actor_len = actor_len;
		this.actor_Pro = actor_Pro;
		this.actor = actor;
	}
	public String toString()
	{
		return this.labelID+this.newsSource+" "+this.newsID+" " +this.newsTitle;
	}
	
}
