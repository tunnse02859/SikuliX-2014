package org.java;

import java.util.Properties;

import junit.framework.Assert;

import org.java.TestBase;
import org.sikuli.script.*;

public class CreateDocument {
	
	private static Screen screen = new Screen();
	
	private static Properties prop = TestBase.loadConfig("config.cfg");
	private static String soure_img = "img/";
	private static Pattern word_icon = new Pattern(soure_img + prop.getProperty("WORD_ICON"));
	private static Pattern word_head = new Pattern(soure_img + prop.getProperty("WORD_HEAD"));
	private static Pattern popup = new Pattern(soure_img + prop.getProperty("WORD_POPUP"));
	private static Pattern popup_close = new Pattern(soure_img + prop.getProperty("WORD_POPUP_CLOSE"));
	private static Pattern input_area = new Pattern(soure_img + prop.getProperty("WORD_INPUT_AREA"));
	private static Pattern unikey_en = new Pattern(soure_img + prop.getProperty("UNIKEY_EN"));
	private static Pattern unikey_vn = new Pattern(soure_img + prop.getProperty("UNIKEY_VN"));
	private static Pattern desktop_icon = new Pattern(soure_img + prop.getProperty("DESKTOP_ICON"));
	private static Pattern label_filename = new Pattern(soure_img + prop.getProperty("WORD_LABEL_FILENAME"));
	private static Pattern button_save = new Pattern(soure_img + prop.getProperty("WORD_BUTTON_SAVE"));
	private static Pattern ask_saving = new Pattern(soure_img + prop.getProperty("WORD_ASKFORSAVING"));
	private static Pattern file = new Pattern(soure_img + prop.getProperty("WORD_FILE_IN_DESKTOP"));
	private static Pattern popup_ask_replace = new Pattern(soure_img + prop.getProperty("WORD_REPLACE_FILE"));
	
	public static void createDoc(String fileName){
		try{
			/*if(browser != "chrome" && browser != "firefox" && browser != "Internet explorer"){
				Debug.error("Open brower: Invalid browser name");
				return false;
			}*/

			if(screen.exists(unikey_vn.similar(1f),0.1f) != null){
				screen.click(unikey_vn);
			}
			screen.type(Key.WIN);
			screen.wait(1f);
			screen.type("Microsoft Word");
			Assert.assertTrue(TestBase.isExists(word_icon, 2f, "Cant find word icon", "word icon not found"));
			screen.type(Key.ENTER);
			Assert.assertTrue(TestBase.isExists(word_head, 5f, "Cant find open word", "cant open ms word"));
			if(screen.exists(popup,5f) != null){
				screen.click(popup_close);
			}
			if(screen.exists(unikey_en.similar(1f),0.1f) != null){
				screen.click(unikey_en);
			}
			
			//select input area
			screen.click(input_area);
			screen.type("e",Key.CTRL);
			
			//type content
			screen.type("Coongj hoaf xax hooij chur nghiax vieetj nam");
			screen.type(Key.ENTER);
			screen.type("DDoocj laapj - Tuwj do - Hanhj phucs");
			screen.type(Key.ENTER);
			screen.type("Banr Kieerm DDieemr");
			
			
			//Saving
			saveFile(fileName);
			
			//close word - check if popup: save before close is not displayed
			screen.type(Key.F4,Key.ALT);
			Assert.assertTrue(TestBase.isNotExists(ask_saving, 1f, "Something went wrong, cant save the file", "still ask for saving"));
			
			//open desktop, check if file is saved/exists
			screen.type("d",Key.WIN);
			Assert.assertTrue(TestBase.isExists(file, 1f, "File does not exists in desktop", "no file saved in desktop"));
			System.out.println("[Success][Screenshot] : " + screen.capture(screen.getBounds()).save("img/success","Create Document"));
		}catch(FindFailed e){
			screen.capture(screen.getBounds()).save("img/failed","failed");
			e.printStackTrace();
		}finally{
			//App.close("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Microsoft Office\\Microsoft Word 2010.exe");
		}
	}
	
	public static void saveFile(String fileName){
		try{
			screen.type("s",Key.CTRL);	
			//choose save filelocation
			if(!TestBase.isExists(desktop_icon, 1f, "Cant find desktop icon", "desktop icon not found")) return;
			screen.click(desktop_icon);
			
			//select input filename field
			screen.click(screen.find(label_filename).rightAt(100));
			screen.type("a",Key.CTRL);
			
			//type filename
			screen.type(fileName);
			
			//save
			screen.click(button_save);
			if(screen.exists(popup_ask_replace,1f) != null){
				screen.type(Key.ENTER);
			}
			
		}catch(FindFailed e){
			e.printStackTrace();
		}
	}
	
	public static void deleteAllWordFile(){
		
	}
}
