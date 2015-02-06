package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainBoardPage;
/**
 * 
 * @author marco llano
 *
 */
public class CreateTasksForUserStories {
	MainBoardPage mainBoard = new MainBoardPage();
	String userStoryTitle="automationTest";

	/**
	 * Verify that a user story store tasks
	 */
	@DataProvider(name = "tasksForUserStory")
	public Object[][] createData1() {
		return new Object[][] {
				{ "Task1"}          
		};
	}

	@Test(dataProvider = "tasksForUserStory")
	public void verifyThatUserStoryStoreTasks(String tasks) {
		mainBoard.setTaskForUserStory(tasks);
		mainBoard.clickAddTaskButton();		
		String str = mainBoard.getTasksFromUserStory();		
		Assert.assertTrue(tasks.equals(str.trim()));
	}

	@BeforeMethod
	public void beforeTest(){
		mainBoard.clickAddStoryButton();
		mainBoard.setUserStoryTitle(userStoryTitle);
	}

	@AfterMethod
	public void afterTest() {		
		mainBoard.clickSaveUserStoryButton();
		mainBoard.deleteUserStory();
		//mainBoard.clickDashboardButton();		//*This code line is needed in the last TC of suite.xml to finish execution*//
	}

}
