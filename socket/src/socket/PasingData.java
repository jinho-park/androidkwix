package socket;

import java.io.Serializable;

public class PasingData implements Serializable{
	private String[] url;
	private String[] cat;
	private String[] date;
	private String[] title;
	
	public PasingData(String[] url, String[] cat, String[] date, String[] title){
		this.url=url;
		this.cat=cat;
		this.date=date;
		this.title=title;
	}
	
	public int getlistsize(){
		return url.length;
	}
	
	public String[] getUrls(){
		return url;
	}
	
	public String[] getCats(){
		return cat;
	}
	
	public String[] getDates(){
		return date;
	}
	
	public String[] getTitles(){
		return title;
	}
	
	public String getUrl(int index){
		return url[index];
	}
	
	public String getCat(int index){
		return cat[index];
	}
	
	public String getDate(int index){
		return date[index];
	}
	
	public String getTitle(int index){
		return title[index];
	}
}
