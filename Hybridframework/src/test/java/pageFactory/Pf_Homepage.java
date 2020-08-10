package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Homepage extends Pf_Keywords{
	
	@FindBy(linkText="Sign In")
	public WebElement lk_Signin;
	
	@FindBy(id="username")
	public WebElement lbl_username;
	
	@FindBy(id="srchword")
	public WebElement txt_searchbox;
	
	@FindBy(className="newsrchbtn")
	public WebElement btn_search;
	
	@FindBy(className="sorrymsg")
	public WebElement msg_searcherror;
	
	@FindBy(id="find")
	public WebElement lbl_searchcount;
	
	@FindBy(xpath="//div[@class='bookbox'][1]//img")
	public WebElement img_book;
	
	
	public Pf_Homepage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void click_signin(){
		lk_Signin.click();
		
	}
	
	
	public void search(String val){
		Enter(txt_searchbox, val);
		Click(btn_search);		
		
	}
	
	
	
	
	
	
	
	
	
	

}
