package service;
import dao.NewsDao;
public class StatisticsService {
	private NewsDao newsDao;

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	public String getAllCount(){
		String result= "";
		String[]database = {"fenghuang","huanqiu_china","xinhua","xinhua_world","xinlang","huanqiu_world"};
		for(int i = 0;i<database.length;i++)
			if(i<database.length)
				result+=(Integer.toString(newsDao.getCount(database[i]))+",");
			else 
				result+=Integer.toString(newsDao.getCount(database[i]));
		return result;
	}
}
