package utils.dataFactory.guestFactory;

import java.util.ArrayList;
import java.util.Calendar;

import org.testng.Reporter;

import utils.Datatable;
import utils.Randomness;
import utils.TestReporter;

/**
 * @summary Container for multiple Guests to store random or pre-defined Guest
 *          Information
 * @author Justin Phlegar
 * @version 11/28/2015 Justin Phlegar - original
 * @see utils.dataFactory.guestFactory.Guest
 */
public class HouseHold {
	private ArrayList<Guest> guests = new ArrayList<Guest>();

	/**
	 * @summary Create guests and populate them with random data. The first
	 *          guest created will be marked as primary. The first address,
	 *          phone and email will be marked as primary for each guest
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public HouseHold(int numberOfGuests) {
		for (int x = 0; x < numberOfGuests; x++) {
			addGuest(new Guest());

			guests.get(x).getAllAddresses().get(0).setPrimary(true);
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStreetName(
							guests.get(0).primaryAddress().getStreetName());
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStreetNumber(
							guests.get(0).primaryAddress().getStreetNumber());
			guests.get(x).getAllAddresses().get(0)
					.setCity(guests.get(0).primaryAddress().getCity());
			guests.get(x).getAllAddresses().get(0)
					.setState(guests.get(0).primaryAddress().getState());
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStateAbbv(guests.get(0).primaryAddress().getStateAbbv());
			guests.get(x).getAllAddresses().get(0)
					.setZipCode(guests.get(0).primaryAddress().getZipCode());
			guests.get(x).getAllAddresses().get(0).setOptIn(true);
			guests.get(x).getAllPhones().get(0).setPrimary(true);
			guests.get(x).getAllEmails().get(0).setPrimary(true);
		}

		guests.get(0).setPrimary(true);
		if (Integer.parseInt(guests.get(0).getAge()) <= 18) {
			guests.get(0).setAge("45");
			guests.get(0).setChild(false);
			guests.get(0).setBirthDate("1970-01-14");
		}
	}

	public HouseHold(String guestScenario) {
		Datatable datatable = new Datatable();
		datatable.setVirtualtablePath("METADATA_");
		datatable.setVirtualtablePage("GUESTS");
		datatable.setVirtualtableScenario(guestScenario);
		boolean finishedAdults = false;
		boolean finishedChildren = false;
		int numberAdultsCreated = 0;
		int numberChildrenCreated = 0;
		int numberInfantsCreated = 0;

		int numberAdults = Integer.valueOf(datatable
				.getDataParameter("NumberAdults"));
		int numberChildren = Integer.valueOf(datatable
				.getDataParameter("NumberChildren"));
		int numberInfants = Integer.valueOf(datatable
				.getDataParameter("NumberInfants"));
		int houseHoldSize = numberAdults + numberChildren + numberInfants;

		for (int x = 0; x < houseHoldSize; x++) {
			addGuest(new Guest());

			guests.get(x).getAllAddresses().get(0).setPrimary(true);
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStreetName(
							guests.get(0).primaryAddress().getStreetName());
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStreetNumber(
							guests.get(0).primaryAddress().getStreetNumber());
			guests.get(x).getAllAddresses().get(0)
					.setCity(guests.get(0).primaryAddress().getCity());
			guests.get(x).getAllAddresses().get(0)
					.setState(guests.get(0).primaryAddress().getState());
			guests.get(x)
					.getAllAddresses()
					.get(0)
					.setStateAbbv(guests.get(0).primaryAddress().getStateAbbv());
			guests.get(x).getAllAddresses().get(0)
					.setZipCode(guests.get(0).primaryAddress().getZipCode());
			guests.get(x).getAllAddresses().get(0).setOptIn(true);
			guests.get(x).getAllPhones().get(0).setPrimary(true);
			guests.get(x).getAllEmails().get(0).setPrimary(true);

			if (numberAdultsCreated < numberAdults
					&& Integer.valueOf(guests.get(x).getAge()) < 18) {
				guests.get(x).setAge(
						String.valueOf(Randomness.randomNumberBetween(18, 99)));
				guests.get(x).setBirthDate(
						"01/01/"
								+ (Integer.valueOf(Calendar.getInstance().get(
										Calendar.YEAR)) - Integer
										.valueOf(guests.get(x).getAge())));
				numberAdultsCreated++;
			}

			else
				finishedAdults = true;

			if (finishedAdults && numberChildrenCreated < numberChildren) {
				guests.get(x).setAge(
						String.valueOf(Randomness.randomNumberBetween(3, 17)));
				guests.get(x).setBirthDate(
						"01/01/"
								+ (Integer.valueOf(Calendar.getInstance().get(
										Calendar.YEAR)) - Integer
										.valueOf(guests.get(x).getAge())));
				numberChildrenCreated++;
			} else if (numberChildrenCreated == numberChildren) {
				finishedChildren = true;
			}

			if (finishedAdults && finishedChildren
					&& numberInfantsCreated < numberInfants) {
				guests.get(x).setAge(
						String.valueOf(Randomness.randomNumberBetween(1, 2)));
				guests.get(x).setBirthDate(
						"01/01/"
								+ (Integer.valueOf(Calendar.getInstance().get(
										Calendar.YEAR)) - Integer
										.valueOf(guests.get(x).getAge())));
				numberInfantsCreated++;
			}
		}

		guests.get(0).setPrimary(true);
		if (Integer.valueOf(guests.get(0).getAge()) < 18) {
			guests.get(0).setAge(
					String.valueOf(Randomness.randomNumberBetween(18, 99)));
		}
	}

	public void sendToApi(String environment) {
		TestReporter.logStep("Creating guest via GoMaster API");
		for (int x = 0; x < guests.size(); x++) {
			guests.get(x).sendToApi(environment);
			TestReporter.log(guests.get(x).toString());
			Reporter.log("");
		}
	}

	/**
	 * @summary Associate a new Guest to the guest using preset data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public void addGuest(Guest guest) {
		guests.add(guest);
	}

	/**
	 * @summary Return the guest marked as Primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the HouseHolds primary Guest
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public Guest primaryGuest() {
		Guest primaryGuest = null;

		for (Guest guest : guests) {
			if (guest.isPrimary())
				primaryGuest = guest;
		}

		return primaryGuest;
	}

	/**
	 * @summary Return all Guests associated to the HouseHold
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return all Guests as an ArrayList
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public ArrayList<Guest> getAllGuests() {
		return guests;
	}

	/**
	 * @summary Return the number of Children currently in Household
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the number of children in HouseHold
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public String numberOfChildren() {
		int numberOfChildren = 0;
		for (Guest guest : guests) {
			if (guest.isChild() && Integer.valueOf(guest.getAge()) > 2
					&& Integer.valueOf(guest.getAge()) < 18)
				numberOfChildren++;
		}
		return String.valueOf(numberOfChildren);
	}

	public String numberOfInfants() {
		int numberOfInfant = 0;
		for (Guest guest : guests) {
			if (guest.isChild() && Integer.valueOf(guest.getAge()) <= 2)
				numberOfInfant++;
		}
		return String.valueOf(numberOfInfant);
	}

	/**
	 * @summary Return the number of adults currently in Household
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the number of adults in HouseHold
	 * @see utils.dataFactory.guestFactory.Guest
	 */
	public String numberOfAdults() {
		int numberOfAdults = 0;
		for (Guest guest : guests) {
			if (!guest.isChild())
				numberOfAdults++;
		}
		return String.valueOf(numberOfAdults);
	}
}

