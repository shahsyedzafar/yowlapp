package model;


public class City {
	private String name;
	private String state;
	private String zipcode;
	public City(String name, String state, String zipcode) {
		super();
		this.name = name;
		this.state = state;
		this.zipcode = zipcode;
	}
	public City() {
		super();
		this.name = "";
		this.state = "";
		this.zipcode = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
