package genericLibrary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {
	
	public WebDriver driver;
	public static ExtentReports eReports;
	public ExtentTest startTest;
	public String browser_type;
	
	@BeforeSuite(groups = {"UAT","QA","STAGE","DEV"})
	public void initReports(){
		eReports = new ExtentReports("E:\\FebReports\\proj_" + getDatetimestamp() + ".html",false);
		
	}
	
	@Parameters({"browser"})
	@BeforeMethod(groups = {"UAT","QA","STAGE","DEV"})
	public void launchApp(String btype) throws Exception{
		browser_type=btype;
		if(btype.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(btype.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(btype.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/test/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.get(getPropertyVal(getPropertyVal("env")));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterMethod(groups = {"UAT","QA","STAGE","DEV"})
	public void tearDown(ITestResult result) throws Exception{		
		
		if(result.getStatus()==ITestResult.SUCCESS){
			startTest.log(LogStatus.PASS, "Test Status","Passed the Test"  + startTest.addScreenCapture(getScreen()));
		}else if(result.getStatus()==ITestResult.FAILURE){
			startTest.log(LogStatus.FAIL, "Test Status","Failed the Test"  + result.getThrowable().toString() + startTest.addScreenCapture(getScreen()));
		}else if(result.getStatus()==ITestResult.SKIP){
			startTest.log(LogStatus.SKIP, "Test Status","Passed the Test"  + result.getThrowable().toString() + startTest.addScreenCapture(getScreen()));
		}		
		
			
		eReports.endTest(startTest);
		eReports.flush();
		driver.quit();
	}
	
	
//	read property file
	public String getPropertyVal(String key) throws Exception{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalSettings.properties");	
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
//	get the screen shot
	public String getScreen() throws IOException{
		
		TakesScreenshot screen=(TakesScreenshot)driver;
		File screenshotAs = screen.getScreenshotAs(OutputType.FILE);
		String path="E:\\FebReports\\snapshot_" + getDatetimestamp() + ".png";
		FileUtils.copyFile(screenshotAs, new File(path));
		return path;
	}
	
//	get Element Screenshot
	public String getElementScreen(WebElement ele) throws Exception{

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
		    eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		String path="E:\\FebReports\\snapshot_" + getDatetimestamp() + ".png";
		// Copy the element screenshot to disk
		FileUtils.copyFile(screenshot, new File(path));
		
		return path;
		
		
		
	}
//	get date time stamp 
	public   String getDatetimestamp(){
		Date date = new Date();
		SimpleDateFormat specialformat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String customdate = specialformat.format(date);
		return customdate;		
		
	}
	
	
//	------Common Validation----------------
	
	public void cv_equals(String act,String exp,String Stepname,WebElement ele) throws Exception{
		
		if(act.equals(exp)){			

			startTest.log(LogStatus.PASS, Stepname,"Passed the step: " + Stepname  + startTest.addScreenCapture(getElementScreen(ele)));
		}else{
//			
			startTest.log(LogStatus.FAIL, Stepname,"Failed the step: " + Stepname + " as the Actual was " + act + " and the expected was" + exp + startTest.addScreenCapture(getElementScreen(ele)));
//			
		}
		
	}
	
	
	public void cv_equals(String act,String exp,String Stepname) throws Exception{
		
		if(act.equals(exp)){			

			startTest.log(LogStatus.PASS, Stepname,"Passed the step: " + Stepname  + startTest.addScreenCapture(getScreen()));
		}else{
//			
			startTest.log(LogStatus.FAIL, Stepname,"Failed the step: " + Stepname + " as the Actual was " + act + " and the expected was" + exp + startTest.addScreenCapture(getScreen()));
//			
		}
		
	
	}
	
	
//	contains
public void cv_contains(String act,String exp,String Stepname) throws Exception{
		
		if(act.contains(exp)){			

			startTest.log(LogStatus.PASS, Stepname,"Passed the step: " + Stepname  + startTest.addScreenCapture(getScreen()));
		}else{
//			
			startTest.log(LogStatus.FAIL, Stepname,"Failed the step: " + Stepname + " as the Actual was " + act + " and the expected was" + exp + startTest.addScreenCapture(getScreen()));
//			
		}
		
	
	}
	
	

}
