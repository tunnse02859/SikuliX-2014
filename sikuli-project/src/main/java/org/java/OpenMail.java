package org.java;

import org.sikuli.script.*;

public class OpenMail {
	public static Screen screen = new Screen();
	public static boolean openBrowser(String browser)throws FindFailed{
		try{
			/*if(browser != "chrome" && browser != "firefox" && browser != "Internet explorer"){
				Debug.error("Open brower: Invalid browser name");
				return false;
			}*/
			screen.type(Key.WIN);
			screen.wait(1f);
			screen.type(browser);
			screen.wait("img/chrome.png",2f);
			screen.type(Key.ENTER);
			screen.wait("img/new-tab.png",3f);
			screen.type("n",Key.CTRL + Key.SHIFT);
			//screen.click("img/new-tab.png");
		}catch(FindFailed e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean openBrowser2(String path){
		if(App.open(path) == null)
		return false;
		return true;
	}
	
	public static void openPage(String url,String browser){
		
		try{
			if(!openBrowser(browser)) {
				return;
			}
			//if(!openBrowser2(path)){System.out.println("???"); return;}
			screen.type(url);
			screen.type(Key.ENTER);
			screen.wait("img/login-page.png",10f);
			doLogin("tunn6","123@123abc");
		}catch(FindFailed e){
			e.printStackTrace();
		}
	}
	
	public static void doLogin(String username, String password){
		try{
			screen.click("img/inputAcc.png");
			screen.type(username);
			screen.type(Key.TAB);
			screen.type(password);
			screen.type(Key.ENTER);
			screen.wait(20f);
			closeBrowser();
			closeBrowser();
		}catch(FindFailed e){
			e.printStackTrace();
	}
	}
	
	public static void closeBrowser(){
		screen.type(Key.F4,Key.ALT);
	}

}
