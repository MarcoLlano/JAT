package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;

/**
 * This is the page where users will manage their Projects.
 * @author Marco Llano
 * 
 */
public class DashboardPage {
	WebDriver driver;
	WebDriverWait wait;	
	By newProjectBtnLocator = By.xpath("//div[2]/button");
	By deleteProjectBtnLocator = By.xpath("//table/tbody/tr[1]/td[7]/div/a");
	By popupYesBtnLocator = By.xpath(".//*[@id='button-0']");

	/**
	 * Constructor method to initialize the driver and wait
	 */
	public DashboardPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
	}

	/**
	 * Create new project method
	 */
	public NewProjectPage clickNewProjectButton() {	    
		wait.until(ExpectedConditions.visibilityOfElementLocated(newProjectBtnLocator));
		driver.findElement(newProjectBtnLocator).click();;
		return new NewProjectPage();
	}

	/**
	 * Below the methods to delete project 
	 */
	public void deleteProject() {
		clickDeleteProject();
		clickPopupYesButton();
	}

	public void clickDeleteProject() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteProjectBtnLocator));
		driver.findElement(deleteProjectBtnLocator).click();
	}

	public void clickPopupYesButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(popupYesBtnLocator));
		driver.findElement(popupYesBtnLocator).click();
	}
}
