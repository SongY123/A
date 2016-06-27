package Label;
/*
Created on 2015��10��19�� ����10:40:20

@author: GreatShang
*/

public class LabelItem 
{
	/*
	 * ��ע����
	 * */
	public String labelID;			//���ݿ��и�����ע��ID
	
	public String newsSource;		//������Դ
	public String newsID;			//����ID
	public String newsTitle;		//���ű���
	public String newsContent;		//��������
	
	public boolean ifEvent = false;	//�Ƿ��¼����
	public int eventType = -1;		//�¼����ͣ�1-20��
	
	public String sourceActor;		//�¼�������
	public String targetActor;		//�¼�������
	public String triggerWord;		//�¼�������
	public String sourceActorPro;   //�¼�����������
	public String targetActorPro;   //�¼�����������
	
	public String eventTime;		//�¼�����ʱ��
	public String eventLocation;	//�¼������ص�
	public int if_remark;           //��ʱ���Ƿ����±�ע
	public int if_confirmed;		//��ʽ���Ƿ�ȷ��
	public String marker_name;      //�޸ĵ��˵��û���
	public String confirmed_name;   //��ע���˵��û���
	
	public String actor_index;		//ʵ��index
	public String actor_len;		//ʵ�峤��
	public String actor_Pro;		//ʵ������
	public String actor;			//ʵ����
	
	public LabelItem(String labelID,String newsSource,String newsID,String newsTitle)
	{
		//���¼��޹����ű����ע���캯��
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
		//�¼��й����ű����ע���캯��
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
