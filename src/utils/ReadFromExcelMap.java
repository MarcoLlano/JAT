package utils;

import java.io.*;
import java.util.*;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 * @author Marco Llano
 *
 */
public class ReadFromExcelMap {
	public List<Map<String, String>> readFromExcel(String filePath, String sheetName) throws BiffException, IOException{
		List<Map<String, String>> values = new LinkedList<>();
		Workbook workbook = Workbook.getWorkbook(new File(filePath));
		Sheet sheet = workbook.getSheet(sheetName);
		
		for (int row = 1; row < sheet.getRows(); row++) { 
			Map<String, String> map = new HashMap<String, String>();			
			for (int col = 0; col < sheet.getColumns(); col++) { 
				String key = sheet.getCell(col,0).getContents();
				String value = sheet.getCell(col,row).getContents();
				map.put(key,value);
			}			
			values.add(map);
		}
		return values;
	}
}
