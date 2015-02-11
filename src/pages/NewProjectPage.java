package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;

/**
 * This is the form where the user will create projects, is located in DashboardPage.
 * @author marco llano
 * 
 */
public class NewProjectPage {
	WebDriver driver;
	WebDriverWait wait;
	By projectTitleTextboxLocator = By.xpath(".//*[@id='newProjectForm']/div[1]/div[1]/div/input");
	By saveProjectButtonLocator = By.xpath(".//*[@id='newProjectForm']/div[2]/div/div/button[1]");

	/**
	 * Constructor method to initialize the driver and wait
	 */
	public NewProjectPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
	}

	/**
	 * Create new project methods
	 */
	public MainBoardPage createNewProject(String projectTitle){
		wait.until(ExpectedConditions.visibilityOfElementLocated(projectTitleTextboxLocator));
		setProjectTitle(projectTitle);
		return clickSaveProjectButton();
	}

	public void setProjectTitle(String projectTitle){
		wait.until(ExpectedConditions.visibilityOfElementLocated(projectTitleTextboxLocator));
		driver.findElement(projectTitleTextboxLocator).sendKeys(projectTitle);
	}

	public MainBoardPage clickSaveProjectButton(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(projectTitleTextboxLocator));
		driver.findElement(saveProjectButtonLocator).click();
		return new MainBoardPage();
	}
}
