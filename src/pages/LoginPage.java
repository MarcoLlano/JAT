package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.SeleniumDriverManager;

/**
 * This is the page to login into JAT
 * @author Marco Llano
 * 
 */
public class LoginPage {
	WebDriver 		driver;
	WebDriverWait 	wait;		
	By emailTextBoxLocator = By.xpath("//input[@type='text']");
	By passwordTextBoxLocator = By.xpath("//input[@type='password']");
	By signInBtnLocator = By.xpath("//input[@value='Sign in']");
	By displayLogoutBtnLocator = By.xpath("//div[1]/div[1]/div/div/ul/div[1]");
	By logoutBtnLocator = By.xpath("//a[contains(text(),'Logout')]");

	/**
	 * Constructor method to initialize driver, wait and get the URL from page
	 */
	public LoginPage(String jatURL) {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
		driver.get(jatURL);
	}

	/**
	 * Below the methods to Login into JAT
	 * @param email = userName
	 * @param password
	 * @return
	 */
	public DashboardPage loginJAT(String email,String password) {
		setEmailAddress(email);
		setEmailPassword(password);				
		return clickSignInButton();
	}

	public void setEmailAddress(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBoxLocator));
		driver.findElement(emailTextBoxLocator).clear();
		driver.findElement(emailTextBoxLocator).sendKeys(email);
	}

	public void setEmailPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBoxLocator));
		driver.findElement(passwordTextBoxLocator).clear();
		driver.findElement(passwordTextBoxLocator).sendKeys(password);
	}

	public DashboardPage clickSignInButton() {
		driver.findElement(signInBtnLocator).click();
		return new DashboardPage();
	}
	
	/**
	 * method to close the window
	 */
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * Below the methods to Logout and quit from JAT
	 */
	public void logoutJAT() {
		clickDisplayLogoutButton();
		clickOnLogoutButton();
	}

	public void clickDisplayLogoutButton() {
		driver.findElement(displayLogoutBtnLocator).click();		
	}

	public void clickOnLogoutButton() {
		driver.findElement(logoutBtnLocator).click();
	}
}
