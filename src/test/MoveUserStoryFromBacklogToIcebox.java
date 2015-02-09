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
public class MoveUserStoryFromBacklogToIcebox {	
	MainBoardPage mainBoard = new MainBoardPage();
	String userStoryTitle="automationTest";
	String target = "icebox";
	String initial = "backlog";

	/**
	 * Verify that a user story can be moved from Backlog to Icebox panel
	 */
	@Test
	public void verifyDragAndDropBacklogToIcebox(){		
		String panelName = "icebox";
		mainBoard.dragAndDropUserStory(initial, target);
		String actualUserStoryFromIcebox = mainBoard.getUserStory(panelName);
		Assert.assertEquals(userStoryTitle, actualUserStoryFromIcebox);
	}

	@BeforeMethod
	public void beforeTest(){
		mainBoard.createNewUserStory(userStoryTitle);
		mainBoard.dragAndDropUserStory(target, initial);
	}

	@AfterMethod
	public void afterTest() {
		mainBoard.deleteUserStory();		
	}	
}
