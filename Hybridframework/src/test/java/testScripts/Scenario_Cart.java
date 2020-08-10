package testScripts;

import genericLibrary.Base;

import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageFactory.Pf_Cart;
import pageFactory.Pf_Homepage;
import pageFactory.Pf_Productdetails;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Scenario_Cart extends Base{
	
	@Test(dataProvider ="commondp",dataProviderClass = dataProvider.Dp_login.class,groups = {"QA"})
	public void AddtoCart(Map<String,String> hmap) throws Exception{
		
		String bookname = hmap.get("bookname");
		String tcid = hmap.get("TC_ID");
		String order = hmap.get("Order");
		
		startTest = eReports.startTest(tcid + "_" + order);
		startTest.assignCategory(browser_type);
		Pf_Homepage Homepage = new Pf_Homepage(driver);
		cv_contains(driver.getTitle(), "Buy Books Online", "Validate Home Page Title");
		Homepage.search(bookname);
		
//		wait for count
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(Homepage.lbl_searchcount));
		
		Homepage.Click(Homepage.img_book);
		
//		Product details page
		
		Pf_Productdetails Productdetails = new Pf_Productdetails(driver);
		cv_contains(driver.getTitle(),bookname , "Validate Product Page Title");
		Productdetails.btn_Buynow.click();
		
//		Cart page
		
		Pf_Cart Cart = new Pf_Cart(driver);
//		driver.switchTo().frame(Cart.frm_cart);
		
		if(Cart.lbl_bookname.isDisplayed()==true){
			startTest.log(LogStatus.PASS, "Validating the added book","Passed as found the book");
			
		}else{
			
			startTest.log(LogStatus.FAIL, "Validating the added book","Failed as not able to locate the book" + startTest.addScreenCapture(getScreen()));
		}
		
//		driver.switchTo().defaultContent();		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
