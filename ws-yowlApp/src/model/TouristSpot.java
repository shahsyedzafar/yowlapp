package model;


public class TouristSpot {
	private String name;
	private String location;
	private String img_path;
	private String comment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public TouristSpot(String name, String location, String img_path, String comment) {
		super();
		this.name = name;
		this.location = location;
		this.img_path = img_path;
		this.comment = comment;
	}
	public TouristSpot() {
		super();
		this.name = "";
		this.location = "";
		this.img_path = "";
		this.comment = "";
	}
}
