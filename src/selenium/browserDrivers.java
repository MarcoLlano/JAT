package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Marco Llano
 *
 */
public class BrowserDrivers {
	private WebDriver driver;
	public WebDriver browserDriver(String driverName) {
		switch (driverName) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir") + "\\lib\\chromeDriver\\chromedriver.exe"));
				driver = new ChromeDriver();
				break;
		}
		return driver;
	}
}
