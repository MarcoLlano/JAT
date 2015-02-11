package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.SeleniumDriverManager;

/**
 * This is the page where all the User Stories will be manage.
 * @author Marco Llano
 * 
 */
public class MainBoardPage {
	WebDriver driver;
	WebDriverWait wait;	
	By userStorySaveBtnLocator = By.xpath(".//*[@id='createUS']");
	By userStoryAddBtnLocator = By.xpath(".//*[@id='newUserStory_link']");
	By userStoryTitleLocator = By.xpath(".//*[@id='us-title']");	
	By userStoryDeleteBtnLocator = By.xpath("//div[2]/div/div/form/div[2]");
	By popupYesBtnLocator = By.xpath("//li/div/div[2]/button[1]");
	By dashboardBtnLocator = By.xpath("//div[1]/div/div[2]/div/a");
	By userStoryExpandBtnLocator = By.xpath(".//*[@ng-click ='loadUS(userStory)']");
	By taskDescriptionTextBoxLocator = By.xpath("//div/div/div/textarea");
	By taskAddBtnLocator = By.xpath("(//button[@type='button'])[4]");
	By addPlayerBtnLocator = By.xpath("//div[@id='playersContent']/div/div/a[3]");
	By playerEmailTextBoxLocator = By.xpath("//input[@id='player-emailPlayer']"); 
	By playerRoleComboBoxLocator = By.xpath("//div[@id='addPlayer']/div/form/div[2]/select");
	By savePlayerBtnLocator = By.xpath("//button[@type='submit']");
	By expandPlayerLayoutBtnLocator = By.xpath("//div[6]/div/div/div[2]/div[4]/div/div[1]/div[1]");
	By deletePlayerBtnLocator = By.xpath("//div[4]/div/div[2]/div/div[3]");
	By searchUserStoryTextBoxLocator = By.xpath("//input[@id='word-search-uss']");
	By searchUserStoryButtonLocator = By.xpath("//button[@type='button']");

	/**
	 * Constructor method to initialize the driver and wait
	 */
	public MainBoardPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
	}

	/**
	 * Add players to project methods
	 */
	public void addPlayer(String playerEmail, String playerRole) {
		clickAddPlayerBtn();
		setPlayerEmail(playerEmail);
		setPlayerRol(playerRole);
		clickSavePlayerBtn();
	}
	
	/**
	 * Methods to add players
	 */
	public void clickAddPlayerBtn() {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(addPlayerBtnLocator));
		driver.findElement(addPlayerBtnLocator).click();
	}

	public void setPlayerEmail(String playerEmail) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(playerEmailTextBoxLocator));
		driver.findElement(playerEmailTextBoxLocator).sendKeys(playerEmail);
	}

	public void setPlayerRol (String playerRole) {
		driver.findElement(playerRoleComboBoxLocator).sendKeys(playerRole);
	}

	/**
	 * This method click on add player button to save the player in project
	 */
	public void clickSavePlayerBtn() {		
		driver.findElement(savePlayerBtnLocator).click();
		driver.navigate().refresh();
	}

	/**
	 * Delete PLayers
	 */
	public void deletePlayer() {
		expandPlayerLayout();
		clickDeletePlayer();
		clickYesPopupBtn();
	}

	public void clickDeletePlayer() {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(deletePlayerBtnLocator));
		driver.findElement(deletePlayerBtnLocator).click();
	}

	public void expandPlayerLayout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(expandPlayerLayoutBtnLocator));
		driver.findElement(expandPlayerLayoutBtnLocator).click();
	}
	
	/**
	 * Create User Story methods
	 * @param userStoryTitle
	 */
	public void createNewUserStory(String userStoryTitle) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userStoryAddBtnLocator));
		clickAddStoryButton();
		setUserStoryTitle(userStoryTitle);
		clickSaveUserStoryButton();
	}

	public void clickAddStoryButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userStoryAddBtnLocator));
		driver.findElement(userStoryAddBtnLocator).click();
	}

	public void setUserStoryTitle(String userStoryTitle) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userStoryTitleLocator));
		driver.findElement(userStoryTitleLocator).sendKeys(userStoryTitle);
	}

	public void clickSaveUserStoryButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userStorySaveBtnLocator));
		driver.findElement(userStorySaveBtnLocator).click();
	}
	
	/**
	 * Methods for search user stories
	 */
	public String searchUserStory(String userStory) {
		setUserStoryTitleInSearchTextBox(userStory);
		clickSearchUserStoryButton();
		return getUserStoryFromSearchResultPanel(userStory);
	}
	
	public void setUserStoryTitleInSearchTextBox(String userStory) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchUserStoryTextBoxLocator));
		driver.findElement(searchUserStoryTextBoxLocator).clear();
		driver.findElement(searchUserStoryTextBoxLocator).sendKeys(userStory);
	}
	
	public void clickSearchUserStoryButton() {
		driver.findElement(searchUserStoryButtonLocator).click();
	}	

	/**
	 * Delete user story methods
	 * @param tasks
	 */
	public void deleteUserStory() {
		clickExpandUserStoryButton();
		clickDeleteUserStoryButton();
		clickYesPopupBtn();
		driver.navigate().refresh(); //have to refresh every time because the locator hidden, so to fix this I apply this method 
	}		

	public void clickExpandUserStoryButton(){
		driver.findElement(userStoryExpandBtnLocator).click();
	}

	public void clickDeleteUserStoryButton(){
		driver.findElement(userStoryDeleteBtnLocator).click();
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
		driver.findElement(taskDescriptionTextBoxLocator).sendKeys(tasks);
	}	

	public void clickAddTaskButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(taskAddBtnLocator));
		driver.findElement(taskAddBtnLocator).click();
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
	
	/**
	 * Method to get user stories from the search result panel
	 */
	public String getUserStoryFromSearchResultPanel(String userStory) {
		return driver.findElement(By.xpath("//div[8]/div/div/div[2]/descendant::*/div[contains(text(),'" + userStory + "')]")).getText();
	}
}
