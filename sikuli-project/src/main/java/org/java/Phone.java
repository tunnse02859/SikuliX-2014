package org.java;

import java.util.*;

import junit.framework.Assert;

import org.java.TestBase;
import org.sikuli.android.*;
import org.sikuli.basics.Debug;
import org.sikuli.script.*;

public class Phone {
		private ADBScreen adb;
		
		private Properties prop = TestBase.loadConfig("config.cfg");;
		private String soure_img = "img/phone/";
		
		private Pattern adb_apps = new Pattern(soure_img + prop.getProperty("ADB_APPS"));
		private Pattern setting_icon = new Pattern(soure_img + prop.getProperty("SETTING_ICON"));
		private Pattern setting_screen = new Pattern(soure_img + prop.getProperty("SETTING_SCREEN"));
		private Pattern bt_icon = new Pattern(soure_img + prop.getProperty("BT_ICON"));
		private Pattern bt_inside = new Pattern(soure_img + prop.getProperty("BT_INSIDE"));
		private Pattern on_icon = new Pattern(soure_img + prop.getProperty("ON_ICON"));
		private Pattern off_icon = new Pattern(soure_img + prop.getProperty("OFF_ICON"));
		
		public Phone(String serialID){
			adb = new ADBScreen(serialID);
		}
		
		public Phone(ADBScreen adbp){
			this.adb = adbp;
		}
		
		public void goHome(){
			adb.aKey(ADBDevice.KEY_HOME);
			Debug.log("Go Home");
		}
		
		public void closeAllApp(){
			//adb.keyDown(ADBDevice.KEY_HOME);
		}
		
		public static void getScreen(String serialID){
			ADBScreen x = new ADBScreen(serialID);
			System.out.println(x.capture().save("img/screen","screen"));
		}
		
		public void openMap(){
			try{
				this.closeAllApp();
				this.goHome();
				adb.aTap(adb_apps);
				boolean check = false;
				for(int i = 0; i < 7; i++){
					if(adb.exists(setting_icon.exact(),10f) == null){
						adb.aSwipeRight();
					}else {
						adb.aTap(adb.find(setting_icon.exact()).above(50));
						check = true;
						break;
					}	
				}
				Assert.assertTrue("Cant find app Map in devices",check);
				//Assert.assertTrue(TestBase.isExists(load_screen,0.5f, "Could not open app", "cant open app"));
				//wait for loading the map
				adb.wait(5f);
				
				//check item on home screen
				/*List<Pattern> list = new ArrayList<Pattern>();
				list.add(search_field);
				list.add(route_icon_2);
				list.add(mylocation_icon);
				list.add(bottom);
				Assert.assertTrue(TestBase.confirmTrue(TestBase.isExists(list), "Map - something wrong with home screen", "Home Screen",adb));*/
				adb.aTap(setting_icon);
				adb.type("Ha noi");
			}catch(FindFailed e){
				System.out.println("[error][Screenshot] : " + adb.capture().save("img/failed",e.getClass().toString()));
				e.printStackTrace();
			}
		}
		
		public boolean connectBT(){
			try{
				while(adb.exists(adb_apps,3f) == null){
					this.goHome();
				}
				adb.aTap(adb_apps);
				Debug.log("Tap on: Apps");
				
				//find setting icon and tap into it
				boolean check = false;
				for(int i = 0; i < 7; i++){
					if(adb.exists(setting_icon.exact(),3f) == null){
						adb.aSwipeRight();
					}else {
						adb.aTap(adb.find(setting_icon.exact()));
						check = true;
						break;
					}	
				}
				if(!check) Debug.error("Cant find setting icon");
				
				//go into bluetooth
				adb.wait(3f);
				Match x = adb.find(setting_screen.exact());
				adb.aTap(x.below(100));
				adb.aTap(bt_icon);
				
				// is inside bluetooth
				if(adb.exists(bt_inside,4f) == null){
					Debug.error("cant go inside bluetooth");
					return false;
				}
				
				// turn on bluetooth
				while(adb.exists(on_icon.exact(),4f) == null){
					adb.aTap(off_icon.exact());
				}
			}catch(FindFailed e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
}
