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
				{ 
					"Task 1",        
					"Task 2",
					"Task 3",
					"Task 4"
				} 
		};
	}

	@Test(dataProvider = "tasksForUserStory")
	public void verifyThatUserStoryStoreTasks(String task1, String task2, String task3, String task4) {
		mainBoard.addTask(task1);
		mainBoard.addTask(task2);
		mainBoard.addTask(task3);
		mainBoard.addTask(task4);	
		Assert.assertTrue(mainBoard.getTasksFromUserStory(task1).contains(task1));
		Assert.assertTrue(mainBoard.getTasksFromUserStory(task2).contains(task2));
		Assert.assertTrue(mainBoard.getTasksFromUserStory(task3).contains(task3));
		Assert.assertTrue(mainBoard.getTasksFromUserStory(task4).contains(task4));
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
