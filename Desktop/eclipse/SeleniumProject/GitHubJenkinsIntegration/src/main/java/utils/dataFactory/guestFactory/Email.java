package utils.dataFactory.guestFactory;

import utils.Randomness;

/**
* @summary Container to store random or pre-defined Guest Email Information
* @author Justin Phlegar
* @version 11/28/2015 Justin Phlegar - original
* @see com.disney.utils.dataFactory.guestFactory.Guest
*/
public class Email{
	private boolean isPrimary = false;
	private String country = "";
	private String type = "";
	private String email = "";
	private boolean optIn = false;
	
	Email(){
		this.country = "United States";
		this.type = "Personal";
		this.email = Randomness.randomString(1).substring(0, 1).toUpperCase()  + Randomness.randomString(11).substring(1).toLowerCase()  + "@test.disney.com";
		this.optIn = false;
	}
	
	public boolean isPrimary() {return isPrimary;}
	public void setPrimary(boolean isPrimary) {this.isPrimary = isPrimary;}

	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public boolean isOptIn() {return optIn;}
	public void setOptIn(boolean optIn) {this.optIn = optIn;}
}

