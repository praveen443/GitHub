package apps.lilo.couponDetails;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 11/16/2015 Waightstill W Avery - original
 */
public class CouponDetailsPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private List<WebElement> rows;
	private Datatable datatable = new Datatable();

	//Array of rows by entitlement
	private String[][] rowsByEntitlementName;

	//Array of column header texts and column numbers
	private String[][] columnHeaderTextAndIndex;

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "couponGridForm:CouponDetailsIdDT")
	private Webtable tblCouponDetailsTable;

	@FindBy(id = "couponGridForm:CouponDetailsIdDT:tb")
	private Webtable eleCouponDetailsTableBody;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public CouponDetailsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public CouponDetailsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleCouponDetailsTableBody);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************

	public void verifyCouponDetails(String scenario) {
		datatable.setVirtualtablePage("CouponDetails");
		datatable.setVirtualtableScenario(scenario);
		String columnName[] = datatable.getDataParameter("ColumnName").split(";");
		String entitlementName[] = datatable.getDataParameter("Entitlement").split(";");
		String issued[] = datatable.getDataParameter("Issued").split(";");
		String redeemed[] = datatable.getDataParameter("Redeemed").split(";");
		String available[] = datatable.getDataParameter("Available").split(";");

		getColumnHeaderRowAndIndex();
		getRowsByEntitlement();
		verifyEntitlement(columnName, entitlementName, issued, redeemed, available);
	}

	private void getColumnHeaderRowAndIndex() {
		List<WebElement> headers = tblCouponDetailsTable.findElements(By.xpath("thead/tr/th"));
		columnHeaderTextAndIndex = new String[headers.size()][2];

		// Iterate through each header to determine if the desired text is
		// contained within
		for (int header = 0; header < headers.size(); header++) {
			new ElementImpl(headers.get(header)).highlight(driver);
			String text = headers.get(header).getText();
			columnHeaderTextAndIndex[header][0] = text;
			columnHeaderTextAndIndex[header][1] = String.valueOf(header);
		}
	}

	private int getColumnNumberByName(String columnName) {
		int colNumber = -1;
		// Iterate through each array and determine if the desired column is
		// found
		for (int col = 0; col < columnHeaderTextAndIndex.length; col++) {
			if (columnHeaderTextAndIndex[col][0].equalsIgnoreCase(columnName)) {
				colNumber = col + 1;
			}
		}
		TestReporter.assertTrue(colNumber > -1, "The column name [" + columnName + "] was not found.");
		return colNumber;
	}

	private void getRowsByEntitlement() {
		rows = eleCouponDetailsTableBody.findElements(By.xpath("tr"));
		System.out.println("Number of rows: " + rows.size());
		rowsByEntitlementName = new String[rows.size()][2];
		// Iterate through each row
		for (int row = 0; row < rows.size(); row++) {
			new ElementImpl(rows.get(row)).highlight(driver);
			String text = rows.get(row).findElement(By.xpath("td[1]/span")).getText();
			rowsByEntitlementName[row][0] = text;
			rowsByEntitlementName[row][1] = String.valueOf(row);
		}

		for (int i = 0; i < rowsByEntitlementName.length; i++) {
			System.out.println(rowsByEntitlementName[i][0] + " : " + rowsByEntitlementName[i][1]);
		}
	}

	private int getRowNumberByEntitlementName(String entitlement) {
		int rowNumber = -1;
		// Iterate through each array and determine if the desired entitlement
		// is found
		for (int ent = 0; ent < rowsByEntitlementName.length; ent++) {
			if (rowsByEntitlementName[ent][0].equalsIgnoreCase(entitlement)) {
				rowNumber = ent + 1;
			}
		}
		TestReporter.assertTrue(rowNumber > -1, "The entitlement name [" + entitlement + "] was not found.");
		return rowNumber;
	}

	private void verifyEntitlement(String[] columnName, String[] entitlementName, String[] issued, String[] redeemed,
			String[] available) {
		int columnNumber;
		int rowNumber;
		String actualValue;

		// Iterate through each array to be validated
		for (int value = 0; value < columnName.length; value++) {
			System.out.println("Column Name: " + columnName[value]);
			if (!columnName[value].isEmpty()) {
				columnNumber = getColumnNumberByName(columnName[value]);
				System.out.println("Column Number: " + columnNumber);
				rowNumber = getRowNumberByEntitlementName(entitlementName[value]);
				System.out.println("Row Number: " + rowNumber);
				actualValue = eleCouponDetailsTableBody
						.findElement(By.xpath("tr[" + rowNumber + "]/td[" + columnNumber + "]/span")).getText();
				System.out.println("Actual value: " + actualValue);
				if (!issued[value].isEmpty()) {
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(issued[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + issued[value] + "].");
				} else if (!redeemed[value].isEmpty()) {
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(redeemed[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + redeemed[value] + "].");
				} else if (!available[value].isEmpty()) {
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(available[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + available[value] + "].");
				} else {
					TestReporter.log("Skipping evaluation as no value were passed with which to validate.");
				}
			} else {
				rowNumber = getRowNumberByEntitlementName(entitlementName[value]);

				if (!issued[value].isEmpty()) {
					actualValue = eleCouponDetailsTableBody
							.findElement(By.xpath(
									"tr[" + rowNumber + "]/td[" + getColumnNumberByName("Issued") + "]/span"))
							.getText();
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(issued[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + issued[value] + "].");
				}
				if (!redeemed[value].isEmpty()) {
					actualValue = eleCouponDetailsTableBody
							.findElement(By.xpath(
									"tr[" + rowNumber + "]/td[" + getColumnNumberByName("Redeemed") + "]/span"))
							.getText();
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(redeemed[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + redeemed[value] + "].");
				}
				if (!available[value].isEmpty()) {
					actualValue = eleCouponDetailsTableBody
							.findElement(By.xpath(
									"tr[" + rowNumber + "]/td[" + getColumnNumberByName("Available") + "]/span"))
							.getText();
					TestReporter.assertTrue(actualValue.equalsIgnoreCase(available[value]),
							"The [" + columnName[value] + "] actual value [" + actualValue
									+ "] did not match the expected value [" + available[value] + "].");
				}
			}
		}
	}
}

