package org.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.sikuli.android.ADBScreen;
import org.sikuli.basics.Debug;
import org.sikuli.script.*;
import org.sikuli.vnc.VNCScreen;

public class TestBase {
	public static Screen screen = new Screen();
	public static boolean waitFor(int time,String path, Screen screen){
		for(int i = 0; i < time; i++){
			if(screen.exists(path) != null){
				return true;
			}
			screen.wait(1f);
		}
		return false;
	}
	
	public static Properties loadConfig(String fileName){
		Properties prop = null;
		FileInputStream in;
		try{
			in = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(in);
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return prop;
	}
	
	public static boolean isExists(Pattern pattern,double time,String mess,String fileName){
		if(screen.exists(pattern,time) == null){
			Debug.log(mess);
			System.out.println("[Screenshot] : " + screen.capture(screen.getBounds()).save("img/failed",fileName));
			return false;
		}
		return true;
	}
	
	public static boolean isNotExists(Pattern pattern,double time,String mess,String fileName){
		if(screen.exists(pattern,time) != null){
			Debug.error(mess);
			System.out.println("[Screenshot] : " + screen.capture(screen.getBounds()).save("img/screen",fileName));
			return false;
		}
		return true;
	}
	
	public static boolean isExists(List<Pattern> list){
		for(Pattern p : list){
			if(screen.exists(p,0.5f) == null){
				Debug.error("Could not find this pattern: " + p.getFilename());
				return false;
			}
		}
		return true;
	}
	
	public static boolean confirmTrue(boolean check, String mess,String fileName, Screen scr){
		if(!check){
			Debug.error(mess);
			System.out.println("[Screenshot] : " + scr.capture().save("img/failed",fileName));
			return false;
		}
		return true;
	}
	
	public static boolean confirmTrue(boolean check, String mess,String fileName, ADBScreen adbs){
		if(!check){
			Debug.error(mess);
			System.out.println("[Screenshot] : " + adbs.capture().save("img/failed",fileName));
			return false;
		}
		return true;
	}
	
	public static void takeScreen(Object screen){
		if(screen instanceof ADBScreen ){
			System.out.println("[Screenshot] : " + ((ADBScreen) screen).capture().save("img/screen","screen"));
		}else if(screen instanceof Screen){
			System.out.println("[Screenshot] : " + ((Screen) screen).capture().save("img/screen","screen"));
		}else if(screen instanceof VNCScreen){
			System.out.println("[Screenshot] : " + ((VNCScreen) screen).capture().save("img/screen","screen"));
		}
	}
	public static void takeScreen(String serialID){
		ADBScreen adb = new ADBScreen(serialID);
		System.out.println("[Screenshot] : " + adb.capture().save("img/failed","screen"));
	}
}
