package org.java;

import java.util.Properties;

import org.sikuli.android.*;
import org.sikuli.basics.Debug;
import org.sikuli.script.*;

public class HUDemo {
	private ADBScreen adb;
	private static final int HOME = 4;
	
	private Properties prop = TestBase.loadConfig("config.cfg");
	private String soure_img = "img/hu/";
	private String soure_img2 = "img/hu2/";
	
	private Pattern setting_icon = new Pattern(soure_img + prop.getProperty("HU_SETTING_ICON"));
	private Pattern setting_bt_active = new Pattern(soure_img + prop.getProperty("HU_SETTING_BT_ACTIVE"));
	private Pattern setting_bt_connection = new Pattern(soure_img + prop.getProperty("HU_SETTING_BT_CONNECTION"));
	private Pattern device_name = new Pattern(soure_img + prop.getProperty("HU_DEVICE_NAME"));
	
	private Pattern dial_not_selected = new Pattern(soure_img + prop.getProperty("HU_DIAL_NOT_SELECTED"));
	private Pattern n_0 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_0")).exact();
	private Pattern n_1 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_1")).exact();
	private Pattern n_2 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_2")).exact();
	private Pattern n_3 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_3")).exact();
	private Pattern n_4 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_4")).exact();
	private Pattern n_5 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_5")).exact();
	private Pattern n_6 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_6")).exact();
	private Pattern n_7 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_7")).exact();
	private Pattern n_8 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_8")).exact();
	private Pattern n_9 = new Pattern(soure_img + prop.getProperty("HU_NUMBER_9")).exact();
	private Pattern make_call = new Pattern(soure_img + prop.getProperty("HU_MAKE_CALL"));
	
	private Pattern aa_menu = new Pattern(soure_img2 + prop.getProperty("AA_MENU"));
	private Pattern menu_icon_music = new Pattern(soure_img2 + prop.getProperty("MENU_ICON_MUSIC"));
	private Pattern menu_icon_navigation = new Pattern(soure_img2 + prop.getProperty("MENU_ICON_NAVIGATION"));
	private Pattern menu_icon_phone = new Pattern(soure_img2 + prop.getProperty("MENU_ICON_PHONE"));
	
	private Pattern list_song_icon = new Pattern(soure_img2 + prop.getProperty("LIST_SONG_ICON"));
	private Pattern recent_active = new Pattern(soure_img2 + prop.getProperty("RECENT_ACTIVE"));
	private Pattern sroll_down_icon = new Pattern(soure_img2 + prop.getProperty("SCROLL_DOWN_ICON"));
	private Pattern song_selection = new Pattern(soure_img2 + prop.getProperty("SONG_SELECTION"));
	private Pattern button_play = new Pattern(soure_img2 + prop.getProperty("BUTTON_PLAY"));
	private Pattern button_unplay = new Pattern(soure_img2 + prop.getProperty("BUTTON_UNPLAY"));
	
	private Pattern my_location = new Pattern(soure_img2 + prop.getProperty("MY_LOCATION"));
	private Pattern get_my_location = new Pattern(soure_img2 + prop.getProperty("GET_MY_LOCATION"));
	private Pattern zoom = new Pattern(soure_img2 + prop.getProperty("ZOOM"));
	private Pattern un_zoom = new Pattern(soure_img2 + prop.getProperty("UN_ZOOM"));
	private Pattern search_field = new Pattern(soure_img2 + prop.getProperty("SEARCH_FIELD"));
	private Pattern search_button = new Pattern(soure_img2 + "/text/" + prop.getProperty("SEARCH_BUTTON"));
	private Pattern navigation_screen = new Pattern(soure_img2 + prop.getProperty("NAVIGATION_SCREEN"));
	
	private Pattern kb_a = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_A")).exact();
	private Pattern kb_d = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_D")).exact();
	private Pattern kb_g = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_G")).exact();
	private Pattern kb_h = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_H")).exact();
	private Pattern kb_n = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_N")).exact();
	private Pattern kb_r = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_R")).exact();
	private Pattern kb_t = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_T")).exact();
	private Pattern kb_u = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_U")).exact();
	private Pattern kb_y = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_Y")).exact();
	private Pattern kb_space = new Pattern(soure_img2 + "/text/" + prop.getProperty("KB_SPACE")).exact();
	
	private Pattern phone_menu = new Pattern(soure_img2 + prop.getProperty("PHONE_MENU"));
	private Pattern phone_menu_dial = new Pattern(soure_img2 + prop.getProperty("PHONE_MENU_DIAL"));
	
	private Pattern dial_0 = new Pattern(soure_img2 + prop.getProperty("DIAL_0")).exact();
	private Pattern dial_1 = new Pattern(soure_img2 + prop.getProperty("DIAL_1")).exact();
	private Pattern dial_4 = new Pattern(soure_img2 + prop.getProperty("DIAL_4")).exact();
	private Pattern dial_6 = new Pattern(soure_img2 + prop.getProperty("DIAL_6")).exact();
	private Pattern dial_8 = new Pattern(soure_img2 + prop.getProperty("DIAL_8")).exact();
	private Pattern dial_9 = new Pattern(soure_img2 + prop.getProperty("DIAL_9")).exact();
	
	private Pattern make_call_2 = new Pattern(soure_img2 + prop.getProperty("MAKE_CALL_2"));
	public HUDemo(){
		adb = new ADBScreen();
	}
	
	public HUDemo(String serialID){
		adb = new ADBScreen(serialID);
	}
	
	public HUDemo(ADBScreen adbh){
		this.adb = adbh;
	}
	
	public void destroy(){
	}
	
	public boolean connectBT(){
		try{
			adb.aKey(HOME);
			adb.wait(3f);
			adb.aTap(setting_icon);
			while(adb.exists(setting_bt_active.exact(),3f) == null){
				adb.aTap(setting_bt_active.similar(0.5f));;
			}
			adb.wait(1f);
			adb.aTap(setting_bt_connection);
			if(adb.exists(device_name.exact(),3f) == null){
				Debug.error("Device not found");
				return false;
			}
			if(adb.exists(device_name.exact()) == null){
				adb.aTap(device_name.similar(0.6f));
			}
			
		}catch(FindFailed e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void makeCall(){
		try{
			while(adb.exists(dial_not_selected.exact(), 4f) != null){
				adb.aTap(dial_not_selected.exact());
			}
			adb.aTap(n_0);
			adb.aTap(n_1);
			adb.aTap(n_6);
			adb.aTap(n_8);
			adb.aTap(n_8);
			adb.aTap(n_4);
			adb.aTap(n_4);
			adb.aTap(n_1);
			adb.aTap(n_9);
			adb.aTap(n_4);
			adb.aTap(n_4);
			
			adb.aTap(make_call);
		}catch(FindFailed e){
			e.printStackTrace();
		}finally{
			//this.destroy();
		}
	}
	
	public void AndroidAuto(){
		try{
			boolean check = false;
			while(adb.exists(aa_menu) == null){
				System.out.println("Waiting for device connection!");
			}
			System.out.println("Device connected! Open android auto!");
			adb.wait(5f);
			
			System.out.println("---- play music ----\n");
			while(adb.exists(list_song_icon) == null){
				adb.aEventTap(menu_icon_music.exact());
			}
			adb.aEventTap(list_song_icon);
			adb.wait(2f);
			adb.aEventTap(recent_active);
			for(int i=1;i<=5;i++){
				if(adb.exists(song_selection,1f) != null){
					adb.aEventTap(song_selection);
					check = true;
					break;
				}
				adb.aEventTap(sroll_down_icon);
			}
			if(!check){
				adb.aEventTap(new Location(240, 480));
			}
			adb.wait(5f);
			System.out.println("---- Google Map ----\n");
			adb.aEventTap(menu_icon_navigation.exact());
			adb.wait(5f);
			adb.aSwipeRight();
			adb.wait(1f);
			adb.aEventTap(get_my_location);
			if(adb.exists(my_location.exact(),3f) != null){
				System.out.println("Get current location: Success!");
			}else{
				System.out.println("Get current location: Failed!");
			}
			System.out.println("-- Do some zoom, un-zoom action--");
			adb.aEventTap(zoom);
			adb.aEventTap(zoom);
			adb.aEventTap(un_zoom);
			
			System.out.println("-- Do some swipe action--");
			adb.aSwipeRight();
			adb.aSwipeRight();
			adb.aSwipeLeft();
			adb.aSwipeDown();
			adb.aSwipeUp();
			
			System.out.println("-- Do some search--");
			adb.aEventTap(search_field);
			adb.wait(1f);
			this.doType("tran duy hung");
			adb.aEventTap(search_button);
			if(adb.exists(navigation_screen.similar(0.8f),5f) != null){
				System.out.println("Get navigation: Success!");
			}else{
				System.out.println("Get navigation: Failed!");
			}
			
			
			System.out.println("---- Phone ----\n");
			adb.aEventTap(menu_icon_phone.exact());
			adb.wait(1f);
			adb.aEventTap(phone_menu);
			adb.wait(1f);
			adb.aEventTap(phone_menu_dial);
			adb.wait(1f);
			adb.aEventTap(dial_0);
			adb.aEventTap(dial_1);
			adb.aEventTap(dial_6);
			adb.aEventTap(dial_8);
			adb.aEventTap(dial_8);
			adb.aEventTap(dial_4);
			adb.aEventTap(dial_4);
			adb.aEventTap(dial_1);
			adb.aEventTap(dial_9);
			adb.aEventTap(dial_4);
			adb.aEventTap(dial_4);
			
			adb.aEventTap(make_call_2);
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	public void doType(String text){
		try{
			adb.aEventTap(kb_t);
			adb.aEventTap(kb_r);
			adb.aEventTap(kb_a);
			adb.aEventTap(kb_n);
			
			adb.aEventTap(kb_space);
			
			adb.aEventTap(kb_d);
			adb.aEventTap(kb_u);
			adb.aEventTap(kb_y);
			
			adb.aEventTap(kb_space);
			
			adb.aEventTap(kb_h);
			adb.aEventTap(kb_u);
			adb.aEventTap(kb_n);
			adb.aEventTap(kb_g);
		}catch(FindFailed e){
			e.printStackTrace();
		}
	}
}
