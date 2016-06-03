package Utils;

public class WebDriverSetUp {

	public static int getDefaultTestTimeout() {
		return Integer.parseInt(System.getProperty("selenium.default_timeout"));
	}
}
