package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;
/**
 * 
 * @author marco llano
 * This is the page where all the User Stories will be, that's why it calls Main.
 */
public class MainBoardPage {
	WebDriver driver;
	WebDriverWait wait;	
	By saveUserStoryBtnLocator = By.xpath(".//*[@id='createUS']");
	By addUserStoryBtnLocator = By.xpath(".//*[@id='newUserStory_link']");
	By userStoryTitleLocator = By.xpath(".//*[@id='us-title']");	
	By deleteUserStoryBtnLocator = By.xpath("//div[2]/div/div/form/div[2]");
	By popupYesBtnLocator = By.xpath("//li/div/div[2]/button[1]");
	By dashboardBtnLocator = By.xpath("//div[1]/div/div[2]/div/a");
	By expandUserStoryBtnLocator = By.xpath(".//*[@ng-click ='loadUS(userStory)']");
	By taskFieldInUserStoryLocator = By.xpath("//div/div/div/textarea");
	By addTaskBtnLocator = By.xpath("(//button[@type='button'])[4]");
	By addPlayerBtnLocator = By.xpath("//div[@id='playersContent']/div/div/a[3]");
	By setPlayerEmailLocator = By.xpath("//input[@id='player-emailPlayer']");
	By playerRoleComboBoxLocator = By.xpath("//div[@id='addPlayer']/div/form/div[2]/select");
	By playerRoleName = By.xpath("//div[@id='addPlayer']/div/form/div[2]/select/option[2]");
	By savePlayerBtnLocator = By.xpath("//button[@type='submit']");
	By storedPlayerInPlayers = By.xpath("//div[@id='playersContent']/div/div[2]");
	//By storedTasksInUserStory = By.xpath("//div[7]/div/div[1]/div/div/div/div/div/div/div[1]/div/div");

	/**
	 * Constructor method
	 */
	public MainBoardPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
	}

	/**
	 * Add players to project methods
	 */
	public void clickAddPlayerBtn() {		//This method click on add player button to open player panel
		wait.until(ExpectedConditions.visibilityOfElementLocated(addPlayerBtnLocator));
		driver.findElement(addPlayerBtnLocator).click();
	}

	public void setPlayerEmail(String playerEmail) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(setPlayerEmailLocator));
		driver.findElement(setPlayerEmailLocator).sendKeys(playerEmail);
	}

	public void setPlayerRol (String playerRole) {
		driver.findElement(playerRoleComboBoxLocator).sendKeys(playerRole);
	}

	public void clickSavePlayerBtn() {		//This method click on add player button to save the player in project
		driver.findElement(savePlayerBtnLocator).click();
		driver.navigate().refresh();
	}

	/**
	 * Create User Story methods
	 * @param userStoryTitle
	 */
	public void createNewUserStory(String userStoryTitle) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(addUserStoryBtnLocator));
		clickAddStoryButton();
		setUserStoryTitle(userStoryTitle);
		clickSaveUserStoryButton();
	}

	public void clickAddStoryButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(addUserStoryBtnLocator));
		driver.findElement(addUserStoryBtnLocator).click();
	}

	public void setUserStoryTitle(String userStoryTitle) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userStoryTitleLocator));
		driver.findElement(userStoryTitleLocator).sendKeys(userStoryTitle);
	}

	public void clickSaveUserStoryButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveUserStoryBtnLocator));
		driver.findElement(saveUserStoryBtnLocator).click();
	}

	/**
	 * Delete user story methods
	 * @param tasks
	 */
	public void deleteUserStory() {
		clickExpandUserStoryButton();
		clickDeleteUserStoryButton();
		clickYesPopupBtn();
		driver.navigate().refresh();
	}		

	public void clickExpandUserStoryButton(){
		driver.findElement(expandUserStoryBtnLocator).click();
	}

	public void clickDeleteUserStoryButton(){
		driver.findElement(deleteUserStoryBtnLocator).click();
	}	

	public void clickYesPopupBtn(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(popupYesBtnLocator));
		driver.findElement(popupYesBtnLocator).click();
	}

	/**
	 * This method perform the drag and drop of user stories
	 */
	public void dragAndDropUserStory(String initial, String target) {
		(new Actions(driver)).dragAndDrop(driver.findElement(By.xpath(".//*[@id='"+initial+"']/div/div/div/div")), 
				driver.findElement(By.xpath(".//*[@id='"+target+"']"))).perform();
	}

	/**
	 * Method to insert tasks into user stories
	 * @param tasks
	 */
	public void addTask(String task) {
		setTaskForUserStory(task);
		clickAddTaskButton();
	}
	public void setTaskForUserStory(String tasks) {
		driver.findElement(taskFieldInUserStoryLocator).sendKeys(tasks);
	}	

	public void clickAddTaskButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(addTaskBtnLocator));
		driver.findElement(addTaskBtnLocator).click();
	}	

	/**\
	 * This method click on dashboard button
	 */
	public DashboardPage clickDashboardButton(){
		driver.findElement(dashboardBtnLocator).click();
		return new DashboardPage();
	}

	/**
	 * Method to get user story from panel
	 */	
	public String getUserStory(String panelName) {
		return driver.findElement(By.xpath(".//*[@id='"+panelName+"']/div/div/div/div/div[3]")).getText();		
	}

	/**
	 * Method to get tasks from user story in icebox
	 */
	public String getTasksFromUserStory(String task){
		return driver.findElement(By.xpath("//descendant::*/label[contains(text(),'"+task+"')]")).getText();
	}

	/**
	 * Method to get players from projects
	 */
	public String getPlayerFromProject(String playerEmail) {
		return driver.findElement(By.xpath("//div[@id='playersContent']/div/div[2]/descendant::*/div[contains(text(),'"+playerEmail+"')]")).getText();
	}
}
