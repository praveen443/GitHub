package apps.lilo.bankOut;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.commons.lang3.RandomStringUtils;

import api.soapServices.folioBankServicesV2.operations.UserBankOut;

/**
 * 
 * @summary This class is basically contains API's for bank out in a resort. If
 *          the user is already banked in.
 * 
 * @version 9/14/2015
 * @author Brajesh Ahirwar
 * @param NA
 * @throws NA
 * @return NA
 * 
 */
public class BankOutUsingAPI {
	/**
	 * 
	 * @summary Method to bankOut into a resort. If the user is already banked
	 *          in.
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

	public void bankOut(String environment, String username) throws Exception {
		UserBankOut bankOut = new UserBankOut(environment);
		bankOut.setBagnumber(getBagNumber());
		bankOut.setUser(username);
		System.out
				.println("Request   ****************************************************************");
		System.out.println(bankOut.sendRequest());
		System.out
				.println("Ressponse  ****************************************************************");
		System.out.println(bankOut.getResponse());
		System.out.println("My Status Is :" + bankOut.getStatus());

	}

	public String getBagNumber() {
		new RandomStringUtils();
		return RandomStringUtils.randomNumeric(5);

	}
}