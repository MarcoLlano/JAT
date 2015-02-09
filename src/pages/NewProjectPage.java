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
	By setProjectTitleLocator = By.xpath(".//*[@id='newProjectForm']/div[1]/div[1]/div/input");
	By clickSaveProjectButton = By.xpath(".//*[@id='newProjectForm']/div[2]/div/div/button[1]");

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(setProjectTitleLocator));
		setProjectTitle(projectTitle);
		saveProjectButton();
		return new MainBoardPage();
	}

	public void setProjectTitle(String ProjectTitle){
		wait.until(ExpectedConditions.visibilityOfElementLocated(setProjectTitleLocator));
		String setProjectTitle = ProjectTitle;
		driver.findElement(setProjectTitleLocator).sendKeys(setProjectTitle);
	}

	public MainBoardPage saveProjectButton(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(setProjectTitleLocator));
		driver.findElement(clickSaveProjectButton).click();
		return new MainBoardPage();
	}
}
