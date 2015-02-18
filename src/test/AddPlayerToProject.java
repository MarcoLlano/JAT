package test;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.MainBoardPage;
import providers.DataProviderClass;

/**
 * We need to make sure that the player to add exist in JAT
 * @author Marco Llano
 *
 */
public class AddPlayerToProject {
	MainBoardPage mainBoard = new MainBoardPage();
	
	/**
	 * Verify that is able to add players to a project
	 * @param playerEmail
	 * @param playerRole
	 */
	@Test(dataProvider = "players", dataProviderClass = DataProviderClass.class)
	public void createApplicant(String playerEmail,String playerRole) {
		mainBoard.addPlayer(playerEmail, playerRole);
		Assert.assertTrue(mainBoard.getPlayerFromProject(playerEmail).contains(playerEmail));
	}

	@AfterTest
	public void afterTest() {
		mainBoard.clickDashboardButton();	//This code line is needed if is the last TC in Test Suite
	}
}
