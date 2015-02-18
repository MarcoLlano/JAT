package providers;

import org.testng.annotations.DataProvider;
import utils.ReadFromExcel;
import java.io.IOException;

/**
 * Read data from excel and return an Excel object
 * @author Marco Llano
 *
 */
public class DataProviderClass {
	
	//update filepath to the path you will place the Excel file, otherwise will not work
	private static String filePath = System.getProperty("user.dir") + "\\src\\excelFiles";
	private static String fileName = "players.xlsx";
	private static String sheetName = "Players";
	private static ReadFromExcel objExcelFile;

	/**
	 * This method return data from the XLSX files to Create programs
	 * @throws IOException
	 */
	@DataProvider(name = "players")
	public static Object[][] programData() throws IOException {
		objExcelFile = new ReadFromExcel();
		return objExcelFile.readExcel(filePath,fileName,sheetName);
	}
}
