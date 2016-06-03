package apps.lilo.bankOut;

import api.soapServices.folioBankServicesV2.operations.UserBankIn;

/**
 * 
 * @summary This class is basically contains API's for bankIn in a resort. If
 *          the user is not BankedIn.
 * 
 * @version 9/14/2015
 * @author Brajesh Ahirwar
 * @param NA
 * @throws NA
 * @return NA
 * 
 */
public class BankInUsingAPI {
	/**
	 * 
	 * @summary Method to bankIn into a resort. If the user is not bankedIn.
	 * 
	 * @version Created 9/14/2015
	 * @author Brajesh Ahirwar
	 * @param Environment
	 *            and User
	 * @throws NA
	 * @return NA
	 * @throws Exception
	 * 
	 */
	public void bankIn(String environment, String username, String locationId)
			throws Exception {

		UserBankIn bankin = new UserBankIn(environment);
		bankin.setLocationId(locationId);
		bankin.setUser(username);
		System.out
				.println("Request   ****************************************************************");
		System.out.println(bankin.sendRequest());
		System.out
				.println("Ressponse  ****************************************************************");
		System.out.println(bankin.getResponse());
	}
}
