package utils.dataFactory.guestFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

import utils.Randomness;
import utils.date.DateTimeConversion;
import api.soapServices.core.BaseSoapCommands;
import api.soapServices.guestServiceV2.operations.Create;
import api.soapServices.guestServiceV2.operations.SearchByNameAndAddress;
import utils.Randomness;
import utils.date.DateTimeConversion;

/**
 * @summary Container to store random or pre-defined Guest Information
 * @author Justin Phlegar
 * @version 11/28/2015 Justin Phlegar - original
 * @see utils.dataFactory.guestFactory.HouseHold
 */
@SuppressWarnings("unused")
public class Guest {
	Random random = new Random();
	private ArrayList<Address> addresses = new ArrayList<Address>();
	private ArrayList<Phone> phones = new ArrayList<Phone>();
	private ArrayList<Email> emails = new ArrayList<Email>();
	private String guestSeedPath = "/com/disney/utils/guestFactory/seeds/";
	private String[] maleFirstNames = Randomness.seedReader(guestSeedPath
			+ "MaleFirstNames");
	private String[] femaleFirstNames = Randomness.seedReader(guestSeedPath
			+ "FemaleFirstNames");
	private String[] lastNames = Randomness.seedReader(guestSeedPath
			+ "LastNames");
	private boolean isPrimary = false;
	private String title = "";
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String suffix = "";
	private String certification = "";
	private String nickname = "";
	private String birthDate = "";
	private String age = "";
	private String languagePreference = "";
	private String ssn = "";
	private String username = "";
	private String password = "";
	private boolean deceased = false;
	private boolean isChild = false;
	private String odsId = "";

	/**
	 * @summary Upon instantiation, generate name guest data, along with
	 *          address, phone, and email data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 */
	public Guest() {
		populateSeededData();
	}

	public void sendToApi(String environment) {
		Create guest = new Create(environment, "Main");
		guest.setPrefix(title);
		guest.setFirstName(firstName);
		guest.setMiddleName(middleName);
		guest.setLastName(lastName);
		guest.setAge(age);
		guest.setPhoneNumber(phones.get(0).getNumber());
		guest.setEmail(emails.get(0).getEmail());
		guest.setAddress1(addresses.get(0).getAddress1());
		guest.setCity(addresses.get(0).getCity());
		guest.setCountry(addresses.get(0).getCountry());
		guest.setState(addresses.get(0).getStateAbbv());
		guest.setPostalCode(addresses.get(0).getZipCode());
		guest.sendRequest();

		SearchByNameAndAddress search = new SearchByNameAndAddress(environment,
				"GuestSearch-LastName_FirstName_StreetAddress_ZipCode");
		search.setLastName(lastName);
		search.setFirstName(firstName);
		search.setAddress(addresses.get(0).getAddress1());
		search.setPostalCode(addresses.get(0).getZipCode());
		search.sendRequest();
		if (search.getResponseStatusCode() != "200")
			guest.sendRequest();
	}

	public String getOdsId() {
		return odsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.substring(0, 1).toUpperCase()
				+ firstName.substring(1).toLowerCase();
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.substring(0, 1).toUpperCase()
				+ lastName.substring(1).toLowerCase();
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAge() {
		if (Integer.parseInt(age) < 0)
			return "0";
		return age;
	}

	public void setAge(String age) {
		this.age = age;
		if (Integer.parseInt(age) <= 18)
			isChild = true;
		else
			isChild = false;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
	}

	public boolean getDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isChild() {
		return (Integer.parseInt(getAge()) <= 18);
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	/**
	 * @summary Return all addresses associated to the Guest
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return all addresses as an ArrayList
	 * @see utils.dataFactory.guestFactory.Address
	 */
	public ArrayList<Address> getAllAddresses() {
		return addresses;
	}

	/**
	 * @summary Associate a new Address to the guest using random data. Will be
	 *          marked not a primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Address
	 */
	public void addAddress() {
		addresses.add(new Address());
	}

	/**
	 * @summary Associate a new Address to the guest using preset data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Address
	 */
	public void addAddress(Address address) {
		addresses.add(address);
	}

	/**
	 * @summary Return all phone numbers associated to the Guest
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return all phones as an ArrayList
	 * @see utils.dataFactory.guestFactory.Phone
	 */
	public ArrayList<Phone> getAllPhones() {
		return phones;
	}

	/**
	 * @summary Associate a new Phone to the guest using random data. Will be
	 *          marked not a primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Phone
	 */
	public void addPhone() {
		phones.add(new Phone());
	}

	/**
	 * @summary Associate a new Phone to the guest using preset data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Phone
	 */
	public void addPhone(Phone phone) {
		phones.add(phone);
	}

	/**
	 * @summary Return all email addresses associated to the Guest
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return all email addresses as an ArrayList
	 * @see utils.dataFactory.guestFactory.Email
	 */
	public ArrayList<Email> getAllEmails() {
		return emails;
	}

	/**
	 * @summary Associate a new Email to the guest using random data. Will be
	 *          marked not a primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Email
	 */
	public void addEmail() {
		emails.add(new Email());
	}

	/**
	 * @summary Associate a new Email to the guest using preset data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Email
	 */
	public void addEmail(Email email) {
		emails.add(email);
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	/**
	 * @summary Return the address marked as Primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the Guest's primary Address
	 * @see utils.dataFactory.guestFactory.Address
	 */
	public Address primaryAddress() {
		Address primaryAddress = null;

		for (Address address : addresses) {
			if (address.isPrimary())
				primaryAddress = address;
		}

		return primaryAddress;
	}

	/**
	 * @summary Return the phone number marked as Primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the Guest's primary Phone Number
	 * @see utils.dataFactory.guestFactory.Phone
	 */
	public Phone primaryPhone() {
		Phone primaryPhone = null;

		for (Phone phone : phones) {
			if (phone.isPrimary())
				primaryPhone = phone;
		}

		return primaryPhone;
	}

	/**
	 * @summary Return the Email Address marked as Primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the Guest's primary Email Address
	 * @see utils.dataFactory.guestFactory.Email
	 */
	public Email primaryEmail() {
		Email primaryEmail = null;

		for (Email email : emails) {
			if (email.isPrimary())
				primaryEmail = email;
		}

		return primaryEmail;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = "<br/>";

		result.append(" Name: " + title + " " + firstName + " " + lastName
				+ NEW_LINE);
		result.append(" Age: " + age + NEW_LINE);
		result.append(" Primary Street Address: "
				+ primaryAddress().getAddress1() + NEW_LINE);
		result.append(" Priamry Local Info: " + primaryAddress().getCity()
				+ ", " + primaryAddress().getStateAbbv() + " "
				+ primaryAddress().getZipCode() + NEW_LINE);
		result.append(" Primary Phone: " + primaryPhone().getNumber()
				+ NEW_LINE);
		result.append(" Primary Email: " + primaryEmail().getEmail() + NEW_LINE);

		return result.toString();
	}

	private void populateSeededData() {

		boolean isMale = (Math.random() < .5);

		if (isMale) {
			this.title = "Mr.";
			setFirstName((String) Randomness.randomizeArray(maleFirstNames));
		} else {
			this.title = "Mrs.";
			setFirstName((String) Randomness.randomizeArray(femaleFirstNames));
		}

		this.middleName = "Automation";
		setLastName((String) Randomness.randomizeArray(lastNames));

		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.US);
		Date date = Randomness.randomDate();
		String dateOfBirth = format.format(date);

		Calendar dob = Calendar.getInstance();
		dob.setTime(date);
		Calendar today = Calendar.getInstance();
		int convertedAge = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
			convertedAge--;

		if (convertedAge < 0)
			convertedAge = 0;
		this.age = String.valueOf(convertedAge);
		this.birthDate = DateTimeConversion.convert(dateOfBirth.toString(),
				"yyyy-MM-dd'T'hh:mm:ss'Z'", "yyyy-MM-dd");
		this.isChild = Integer.parseInt(getAge()) <= 18;
		this.username = getFirstName() + "." + getLastName();
		this.password = "Blah123!";
		this.ssn = Randomness.randomNumber(3) + "-"
				+ Randomness.randomNumber(2) + "-" + Randomness.randomNumber(4);
		addAddress(new Address());
		addPhone(new Phone());
		addEmail(new Email());
		addresses.get(0).setPrimary(true);
		phones.get(0).setPrimary(true);
		emails.get(0).setPrimary(true);
		emails.get(0).setEmail(
				getFirstName() + "." + getLastName() + "@AutomatedTesting.com");
	}

}
