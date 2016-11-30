package model;


public class University {

	private String name;
	private String homePage;
	private String location;
	private String state;
	private String chairPerson;
	private String mapURL;
	public University(String name, String homePage, String location, String state, String chairPerson, String mapURL) {
		super();
		this.name = name;
		this.homePage = homePage;
		this.location = location;
		this.state = state;
		this.chairPerson = chairPerson;
		this.mapURL = mapURL;
    }
    public String getMapURL() {
        return mapURL;
    }
    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public University(){
		this.name = "";
		this.homePage = "";
		this.location = "";
		this.chairPerson = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getChairPerson() {
		return chairPerson;
	}
	public void setChairPerson(String chairPerson) {
		this.chairPerson = chairPerson;
	}
}
