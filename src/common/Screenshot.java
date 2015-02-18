package common;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class Screenshot extends TestListenerAdapter {
	
	//Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult tr) {
		ScreenShot();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		//ScreenShot();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		ScreenShot();
	}

	private void ScreenShot() {
		try {
			String NewFileNamePath;

			//Get the dir path
			File directory = new File (".");
			
			//get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			
			//get current date time with Date()
			Date date = new Date();

			//To identify the system
			InetAddress ownIP=InetAddress.getLocalHost();
			NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ dateFormat.format(date)+"_"+ownIP.getHostAddress()+ ".png";
			System.out.println(NewFileNamePath);

			//Capture the screen shot of the area of the screen defined by the rectangle
			Robot robot = new Robot();
			BufferedImage bi=robot.createScreenCapture(new Rectangle(1280,1024));
			ImageIO.write(bi, "png", new File(NewFileNamePath));
			
			//Place the reference in TestNG web report
			Reporter.log(NewFileNamePath);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}