package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;
/**
 * 
 * @author marco llano
 * This is the page where users will administrate Projects.
 */
public class DashboardPage {
	WebDriver driver;
	WebDriverWait wait;	
	By emailNameLocator = By.xpath("//ul/span[@title='email']");
	//By newProjectBtnLocator = By.xpath("//div/div/div[1]/div[2]/button");

	By newProjectBtnLocator = By.xpath("//div[2]/button");
	By deleteProjectLocator = By.xpath("//table/tbody/tr[1]/td[7]/div/a");
	By popupYesBtnLocator = By.xpath(".//*[@id='button-0']");

	/**
	 * Constructor method
	 */
	public DashboardPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
	}

	/**
	 * Create new project method
	 */
	public NewProjectPage clickNewProjectButton(String title) {	    
		wait.until(ExpectedConditions.visibilityOfElementLocated(newProjectBtnLocator));
		driver.findElement(newProjectBtnLocator).click();;
		return new NewProjectPage(title);
	}

	/**
	 * Delete project method
	 */
	public void deleteProject() {
		clickDeleteProject();
		clickPopupYesButton();
	}

	public void clickDeleteProject() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteProjectLocator));
		driver.findElement(deleteProjectLocator).click();
	}

	public void clickPopupYesButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(popupYesBtnLocator));
		driver.findElement(popupYesBtnLocator).click();
	}
}
