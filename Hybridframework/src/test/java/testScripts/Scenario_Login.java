package testScripts;

import genericLibrary.Base;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageFactory.Pf_Homepage;
import pageFactory.Pf_Signin;

public class Scenario_Login extends Base{
	Logger login_Log = Logger.getLogger(Scenario_Login.class);
	
	@Test(dataProvider = "commondp",dataProviderClass=dataProvider.Dp_login.class,enabled=true,groups = {"UAT","QA","STAGE"})
	public void ValidLogin(Map<String,String> LoginMap) throws Exception{
		String exp = LoginMap.get("Exp_msg");
		String uname=  LoginMap.get("Uname");
		String pwd=LoginMap.get("Pwd");
		String tcid = LoginMap.get("TC_ID");
		String order = LoginMap.get("Order");
		startTest = eReports.startTest(tcid + "_" +order );
		startTest.assignCategory(browser_type);
		login_Log.info("Starting test:" + tcid + " and order: " + order);
		startTest.log(LogStatus.PASS, "Starting test","Starting test:" + tcid + " and order: " + order);
//		code change
//		driver.findElement(By.linkText("Sign In")).click();
		
		Pf_Homepage pf_Homepage = new Pf_Homepage(driver);		
		pf_Homepage.lk_Signin.click();		
		login_Log.info("Clicked on Sigin Link");
//		------------------------------------------------------------
//		driver.findElement(By.name("logid")).sendKeys(uname);
//		driver.findElement(By.name("pswd")).sendKeys(LoginMap.get("Pwd"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Pf_Signin pf_Signin = new Pf_Signin(driver);
		pf_Signin.cl_login(uname,pwd );
		login_Log.info("Entered User name and Password");
//		-----------------------------------------------------------
		
		String actual = pf_Homepage.lbl_username.getText();
		System.out.println("Actual: " + actual);
		System.out.println("Expected: " + exp);
//		--------------------------------------------
//		if(actual.equals(exp)){			
////			login_Log.info("Passed as user logged in");
//			startTest.log(LogStatus.PASS, "Valid user validation","Passed as user logged in" + startTest.addScreenCapture(getElementScreen(pf_Homepage.lbl_username)));
////			System.out.println("Passed as user logged in");
//		}else{
////			login_Log.error("Failed as user una ble to login");
//			startTest.log(LogStatus.FAIL, "Valid user validation","Failed as user unable to login" + startTest.addScreenCapture(getElementScreen(pf_Homepage.lbl_username)));
////			System.out.println("Failed as user unable to login");
//		}

		cv_equals(actual, exp, "Valid user validation",pf_Homepage.lbl_username);
		
	}
	 

	@Test(dataProvider = "commondp",dataProviderClass=dataProvider.Dp_login.class,enabled=false,groups = {"DEV","STAGE"})
	public void InvalidLogin(Map<String,String> LoginMap){
		
		String exp = LoginMap.get("Exp_msg");
		String uname=  LoginMap.get("Uname");
		String tcid = LoginMap.get("TC_ID");
		String order = LoginMap.get("Order");
		
//		code change
//		driver.findElement(By.linkText("Sign In")).click();
		
		Pf_Homepage pf_Homepage = new Pf_Homepage(driver);		
		pf_Homepage.lk_Signin.click();		
		
//		------------------------------------------------------------
//		driver.findElement(By.name("logid")).sendKeys(uname);
//		driver.findElement(By.name("pswd")).sendKeys(LoginMap.get("Pwd"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Pf_Signin pf_Signin = new Pf_Signin(driver);
		pf_Signin.cl_login(uname, LoginMap.get("Pwd"));
//		-----------------------------------------------------------
		
		String actual = pf_Signin.msg_loginerrormessage.getText();
		
		if(actual.equals(exp)){			
			System.out.println("Passed as succesfully va;idated the error message");
		}else{
			System.out.println("Failed as user unable to locate the error message");
		}
		
	
		
	}
	

}
