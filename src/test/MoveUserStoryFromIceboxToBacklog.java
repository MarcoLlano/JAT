package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.MainBoardPage;
/**
 * 
 * @author marco llano
 *
 */
public class MoveUserStoryFromIceboxToBacklog {
	MainBoardPage mainBoard = new MainBoardPage();
	String userStoryTitle="automationTest";
	String initial = "icebox";
	String target = "backlog";

	/**
	 * Verify that a user story is able to move from Icebox to Backlog
	 */
	@Test
	public void verifyDragAndDropIceboxToBacklog() {
		String panelName = "backlog";
		mainBoard.dragAndDropUserStory(initial, target);
		String actualUserStoryFromBacklog = mainBoard.getUserStory(panelName);
		Assert.assertEquals(userStoryTitle, actualUserStoryFromBacklog);
	}

	@BeforeMethod
	public void beforeMethod(){
		mainBoard.createNewUserStory(userStoryTitle);
	}

	@AfterMethod
	public void afterTest() {
		mainBoard.deleteUserStory();
	}

}
