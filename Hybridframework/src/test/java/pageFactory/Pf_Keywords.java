package pageFactory;

import org.openqa.selenium.WebElement;

public class Pf_Keywords {
	
	
//	Enter value
	public void Enter(WebElement ele,String val){
		ele.clear();
		ele.sendKeys(val);		
	}
	
	
//	Clicking on an element
	
	public void Click(WebElement ele){
		ele.click();		
	}
	
	

}
