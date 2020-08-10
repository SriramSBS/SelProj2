package dataProvider;


import genericLibrary.ExcelRW;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class Dp_login {
	
	@DataProvider(name="commondp")
	public static  Iterator<Object[]> getData(Method m) throws Exception{
		Iterator<Object[]> commondp_Logic = null;
		
		switch (m.getName().toLowerCase()){
		
		case "invalidlogin":
			commondp_Logic = commondp_Logic("Login", "invalidlogin");
			break;
			
		case "validlogin":
			commondp_Logic = commondp_Logic("Login", "validlogin");
			break;
			
		case "invalidsearch":
			commondp_Logic = commondp_Logic("Search", "invalidsearch");
			break;
			
		case "validsearch":
			commondp_Logic = commondp_Logic("Search", "validsearch");
			break;	
			
		case "addtocart":
			commondp_Logic = commondp_Logic("Cart", "Addbook");
			break;
//		case default:
		}
		
//		if(m.getName().equalsIgnoreCase("invalidlogin")){
//			commondp_Logic = commondp_Logic("Login", "invalidlogin");
//		}else if(m.getName().equalsIgnoreCase("validlogin")){
//			commondp_Logic = commondp_Logic("Login", "validlogin");
//		}else if(m.getName().equalsIgnoreCase("invalidsearch")){
//			commondp_Logic = commondp_Logic("Search", "invalidsearch");
//		}else if(m.getName().equalsIgnoreCase("validsearch")){
//			commondp_Logic = commondp_Logic("Search", "validsearch");
//		}
//			
		return commondp_Logic;
	}
	
	
	
	
	
	
//	
//	@DataProvider(name="validLogin")
//	public static Iterator<Object[]> getValidLogin() throws Exception{
//
//		return commondp_Logic("Login", "validlogin");
//	}
//	
////	Invalid Login
//	@DataProvider(name="invalidLogin")
//	public static Iterator<Object[]> getInvalidLogin() throws Exception{
//		return commondp_Logic("Login", "invalidlogin");
//	
//		}
	
	
	public static Iterator<Object[]> commondp_Logic(String Sheetname, String Sname) throws Exception{
		
		ExcelRW ex = new ExcelRW(System.getProperty("user.dir") + "/src/test/resources/rediff.xlsx");
		
		int rowcount = ex.getRowcount(Sheetname);
		int colcount = ex.getcolcount(Sheetname);
		
		List<Object[]> dList = new ArrayList<Object[]>();
		
		for(int iRow = 1 ; iRow <=rowcount ; iRow++){
			
//			condition check
			String flag = ex.readcell(Sheetname, iRow, 2);
			String scriptName = ex.readcell(Sheetname, iRow, 3);
			
			if(flag.trim().equalsIgnoreCase("y") && scriptName.trim().equalsIgnoreCase(Sname)){
//			create map and object array
			Object[] dObj = new Object[1];
			Map<String, String> dMap=new HashMap<String, String>();
			
//			column iterator
			for(int iCol = 0 ; iCol<colcount;iCol++){
				
				String key = ex.readcell(Sheetname, 0, iCol);
				String value =  ex.readcell(Sheetname, iRow, iCol);
				dMap.put(key, value);
								
			}
//			add to object array
			dObj[0]=dMap;
//			add to list
			dList.add(dObj);	
			}
			
		}
		return dList.iterator();
		
		
	}
	
	
	

}
