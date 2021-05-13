package modele;

public class Article {
	
	private String title;
	private String Corps;
	
	public Article(String title,String corps) {
		this.title=title;
		this.Corps=corps;
	}
	public String getTitle() {
		return title;
	}
	public String getCorps() {
		return Corps;
	}
}
