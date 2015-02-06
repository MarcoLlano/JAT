package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;
/**
 * 
 * @author marco llano
 * This is the form where the user will create projects, is located in DashboardPage.
 */
public class NewProjectPage {
	WebDriver driver;
	WebDriverWait wait;
	By setProjectTitleLocator = By.xpath(".//*[@id='newProjectForm']/div[1]/div[1]/div/input");
	By clickSaveProjectButton = By.xpath(".//*[@id='newProjectForm']/div[2]/div/div/button[1]");

	/**
	 * Constructor method
	 */
	public NewProjectPage(String title) {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
		createNewProject(title);
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
