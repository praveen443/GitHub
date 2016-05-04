package utils.dataFactory.guestFactory;

import java.io.IOException;

import utils.Randomness;

/**
* @summary Container to store random or pre-defined Guest Address Information
* @author Justin Phlegar
* @version 11/28/2015 Justin Phlegar - original
* @see com.disney.utils.dataFactory.guestFactory.Guest
*/
@SuppressWarnings("unused")
public class Address{

	private String guestSeedPath = "/com/disney/utils/guestFactory/seeds/";
	private String[] streets = Randomness.seedReader(guestSeedPath + "Streets");
	private String[] cities = Randomness.seedReader(guestSeedPath + "Cities");
	private String[] states = Randomness.seedReader(guestSeedPath + "States");
	Address(){
		
		this.type = "Home";
		this.country = "United States";
		//this.zipCode = String.valueOf(Randomness.randomNumberBetween(10000, 99999));
		this.zipCode = "27127";
		this.streetNumber = String.valueOf(Randomness.randomNumberBetween(100, 9999));
		this.streetName = (String) Randomness.randomizeArray(streets);
		this.city = (String) Randomness.randomizeArray(cities);
		this.state = "North Carolina" ;//(String) Randomness.randomizeArray(states);
		this.stateAbbv = "NC";StateMapper.getStateCode(getState());
		this.optIn = false;
		
		if(state == null || state.isEmpty()) this.state = (String) Randomness.randomizeArray(states);
	}
	
	private boolean isPrimary = false;
	private String type = null;
	private String country = "";
	private String zipCode = "";
	private String address1 = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private String stateAbbv = "";
	private boolean optIn;
	private String streetNumber = "";
	private String streetName = "";
	
	public boolean isPrimary() {return isPrimary;}
	public void setPrimary(boolean isPrimary) {this.isPrimary = isPrimary;}

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}

	public String getZipCode() {return zipCode;}
	public void setZipCode(String zipCode) {this.zipCode = zipCode;}

	public String getStreetNumber() {return streetNumber;}
	public void setStreetNumber(String streetNumber) {this.streetNumber = streetNumber; }

	public String getStreetName() {return streetName;}
	public void setStreetName(String streetName) {this.streetName= streetName; }

	public String getAddress1() {return this.streetNumber + " " + this.streetName;}
	public void setAddress1(String address1) {this.address1 = address1;}

	public String getAddress2() {return address2;}
	public void setAddress2(String address2) {this.address2 = address2;}

	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	public String getState() {return state;}
	public void setState(String state) {this.state = state;}

	public String getStateAbbv() {return stateAbbv;}
	public void setStateAbbv(String stateAbbv) {this.stateAbbv = stateAbbv;}

	public boolean isOptIn() {return optIn;}
	public void setOptIn(boolean optIn) {this.optIn = optIn;}
}
