package testScripts;

import genericLibrary.Base;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import pageFactory.Pf_Homepage;
import pageFactory.Pf_Signin;

public class Scenario_Search extends Base{
	
	
//	invalid Search
	@Test(dataProvider = "commondp",dataProviderClass=dataProvider.Dp_login.class,enabled=true,groups = {"UAT","QA","DEV"})
	public void invalidsearch(Map<String,String> SearchMap) throws Exception{
		String tcid = SearchMap.get("TC_ID");
		String order= SearchMap.get("Order");
		String search= SearchMap.get("searchoption");
		String exp_result= SearchMap.get("exp_result");
		
		 startTest= eReports.startTest(tcid + "_" + order);
		 startTest.assignCategory(browser_type);
		Pf_Homepage pf_Homepage = new Pf_Homepage(driver);
		cv_contains(driver.getTitle(), "Buy Books Online", "Validate Home Page Title");
				
		pf_Homepage.lk_Signin.click();		
		
		Pf_Signin pf_Signin = new Pf_Signin(driver);
		cv_contains(driver.getTitle(), "rediff.com", "Validate Sigin Page Title");
		pf_Signin.cl_login(getPropertyVal("uname"),getPropertyVal("pwd"));
		
		pf_Homepage.search(search);
		
		String act = pf_Homepage.msg_searcherror.getText();
		
		cv_equals(act, exp_result, "Validate invalid Search message",pf_Homepage.msg_searcherror);
		
	}
	
	
//	Valid Search
	@Test(dataProvider = "commondp",dataProviderClass=dataProvider.Dp_login.class,enabled=true,groups = {"STAGE"})
	public void validsearch(Map<String,String> SearchMap) throws Exception{
		String tcid = SearchMap.get("TC_ID");
		String order= SearchMap.get("Order");
		String search= SearchMap.get("searchoption");
		String exp_result= SearchMap.get("exp_result");
		
		 startTest= eReports.startTest(tcid + "_" + order);
		 startTest.assignCategory(browser_type);
		Pf_Homepage pf_Homepage = new Pf_Homepage(driver);
		cv_contains(driver.getTitle(), "Buy Books Online", "Validate Home Page Title");
				
		pf_Homepage.lk_Signin.click();		
		
		Pf_Signin pf_Signin = new Pf_Signin(driver);
		cv_contains(driver.getTitle(), "rediff.com", "Validate Sigin Page Title");
		pf_Signin.cl_login(getPropertyVal("uname"),getPropertyVal("pwd"));
		
		pf_Homepage.search(search);	
		
		cv_equals(pf_Homepage.lbl_searchcount.getText(), exp_result, "Validate the search count",pf_Homepage.lbl_searchcount);
		
	}
	
	
	
	
	
	
	
	
	
	

}
