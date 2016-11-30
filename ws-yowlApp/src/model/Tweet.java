package model;


public class Tweet {
	private String Data;
	private String TouristSpot;
	private String media;
	private String hashTag;
	
	
	public Tweet(String data, String touristSpot, String media, String hashTag) {
		super();
		Data = data;
		TouristSpot = touristSpot;
		this.media = media;
		this.hashTag = hashTag;
	}
	
	public Tweet() {
		super();
		Data = "";
		TouristSpot = "";
		this.media = "";
		this.hashTag = "";
	}
	
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getTouristSpot() {
		return TouristSpot;
	}
	public void setTouristSpot(String touristSpot) {
		TouristSpot = touristSpot;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
}
