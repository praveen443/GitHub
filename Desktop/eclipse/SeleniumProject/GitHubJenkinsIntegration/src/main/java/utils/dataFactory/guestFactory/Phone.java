package utils.dataFactory.guestFactory;

import utils.Randomness;

/**
* @summary Container to store random or pre-defined Guest Phone Information
* @author Justin Phlegar
* @version 11/28/2015 Justin Phlegar - original
* @see com.disney.utils.dataFactory.guestFactory.Guest
*/
public class Phone{
	private boolean isPrimary = false;
	private String country = "";
	private String type = "";
	private String number = "";
	
	Phone(){
		this.country = "United States";
		this.type = "Home";
		this.number = "336" + Randomness.randomNumber(7);
	}
	
	public boolean isPrimary() {return isPrimary;}
	public void setPrimary(boolean isPrimary) {this.isPrimary = isPrimary;}

	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public String getNumber() {return number;}
	public void setNumber(String number) {this.number = number;}
}

