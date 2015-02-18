package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadFromXML;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Marco Llano
 * Manages the web browser
 */
public class SeleniumDriverManager
{
	private static SeleniumDriverManager manager = null;
	private WebDriver driver;
	private WebDriverWait wait;
	String xmlFilePath = (System.getProperty("user.dir") + "\\src\\config\\config.xml");
	public ReadFromXML xmlFile = new ReadFromXML(xmlFilePath);
	
	protected SeleniumDriverManager()
	{
		initializeDriver();
	}

	/**
	 * Initialize the browser driver and the implicitlyWait
	 */
	private void initializeDriver()
	{
		BrowserDrivers bDriver = new BrowserDrivers();
		String node = "drivers";
		String driverName = "driver";
		String browserDriver = xmlFile.read(node, driverName);
		driver = bDriver.browserDriver(browserDriver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30, 100);
	}

	public static SeleniumDriverManager getManager()
	{
		if (manager == null) {
			manager = new SeleniumDriverManager();
		}
		return manager;
	}

	/**
	 * Get the Web driver
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	/**
	 * Close the Web Page, return null if fails
	 */
	public void quitDriver() {    	
		try {
			driver.quit();
		} catch (Exception e) {
			
		}
		driver = null;
	}
}