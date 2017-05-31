package org.java;

import org.sikuli.android.ADBScreen;
import org.sikuli.script.*;



public class Test {	
	
	public static ADBScreen adb_phone;
	//= new ADBScreen("4d000b1448084069");
	public static ADBScreen adb_hu;
	//= new ADBScreen("0123456789ABCDEF");
	public static Phone phone = new Phone(adb_phone);
	public static HUDemo hu = new HUDemo(adb_hu);
	
	public static void init(){
		System.loadLibrary("opencv_java310");
	}
	
	
	public static void scenario1(){
		try{
			//init
			adb_phone = new ADBScreen("4d000b1448084069");
			adb_hu = new ADBScreen("0123456789ABCDEF");
			Phone phone = new Phone(adb_phone);
			HUDemo hu = new HUDemo(adb_hu);
			// enable bluetooth on phone
			if(!phone.connectBT()) return;
			
			// connect to phone using HU though bluetooth
			if(!hu.connectBT()) return;
			
			//check remote request from hu on phone:
			if(adb_phone.exists(new Pattern("img/phone/remote_request.png").exact(), 3f) != null){
				adb_phone.aTap(new Pattern("img/phone/allow.png").exact());
			}
			
			// make call from hu
			adb_hu.wait(5f);
			hu.makeCall();
					
		}catch(FindFailed e){
			e.printStackTrace();
		}
	}
	
	public static void scenario2(){
		//init
		adb_hu = new ADBScreen("0123456789ABCDEF");
		HUDemo hu = new HUDemo(adb_hu);
		hu.AndroidAuto();
	}
	
	public static void main(String[] agrs) throws FindFailed{
		init();
		scenario2();
		//TestBase.takeScreen("0123456789ABCDEF");
	}
	
	
}