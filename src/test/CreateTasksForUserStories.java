package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainBoardPage;

/**
 * This test implement the data provider method with task for user stories
 * @author Marco Llano
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
				{"Task 1"},        
				{"Task 2"},
				{"Task 3"},
				{"Task 4"} 
		};
	}

	@Test(dataProvider = "tasksForUserStory")
	public void verifyThatUserStoryStoreTasks(String task) {
		mainBoard.addTask(task);	
		Assert.assertTrue(mainBoard.getTasksFromUserStory(task).contains(task));
	}

	@BeforeMethod
	public void beforeTest(){
		mainBoard.clickAddStoryButton();
		mainBoard.setUserStoryTitle(userStoryTitle);
	}

	@AfterMethod
	public void afterTest() {		
		mainBoard.clickSaveUserStoryButton();
		//mainBoard.deleteUserStory();
		//mainBoard.clickDashboardButton();		//*This code line is needed in the last TC of suite.xml to finish execution*//
	}

}
