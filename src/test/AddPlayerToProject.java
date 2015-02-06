package test;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.MainBoardPage;
import common.DataProviderClass;

/**
 * Title: Register new applicant-JagdPanther allows create new applicants
 * @author marco llano
 *
 */
public class AddPlayerToProject {

	MainBoardPage mainboard = new MainBoardPage();
	/**
	 * Verify that is able to add players to a project
	 * @param playerEmail
	 * @param playerRole
	 */
	@Test(dataProvider = "players",dataProviderClass = DataProviderClass.class)
	public void createApplicant(String playerEmail,String playerRole) {
		mainboard.clickAddPlayerBtn();
		mainboard.setPlayerEmail(playerEmail);
		mainboard.setPlayerRol(playerRole);
		mainboard.clickSavePlayerBtn();
		Assert.assertTrue(mainboard.getPlayerFromProject().contains(playerEmail));
	}

	@AfterTest
	public void afterTest() {
		mainboard.clickDashboardButton();
	}
}
