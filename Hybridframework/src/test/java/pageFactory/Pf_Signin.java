package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Signin extends Pf_Keywords {
	
	@FindBy(name="logid")
	public WebElement txt_uname;
	
	@FindBy(name="pswd")
	public WebElement txt_pwd;
	
	@FindBy(xpath="//input[@value='Login']")
	public WebElement btn_login;
	
	@FindBy(xpath="//b[contains(text(),'Sorry we were')]")
	public WebElement msg_loginerrormessage;
	
	public Pf_Signin(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void cl_login(String uname,String pwd){
		Enter(txt_uname,uname);
//		txt_uname.sendKeys(uname);
		
		Enter(txt_pwd,pwd);
//		txt_pwd.sendKeys(pwd);
		
		Click(btn_login);
//		btn_login.click();
		
	}
	
	
	
	
	
	
	

}
