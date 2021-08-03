package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	
	static public int RESPONSE_STATUS_CODE_200 = 200;
	static public int RESPONSE_STATUS_CODE_400 = 400;
	static public int RESPONSE_STATUS_CODE_500 = 500;
	static public int RESPONSE_STATUS_CODE_204 = 204;
	static public int RESPONSE_STATUS_CODE_201 = 201;
	static public int RESPONSE_STATUS_CODE_401 = 401;
	
	public Properties prop ;
	
	public TestBase() {
		try {
			prop =  new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\akspo\\eclipse-workspace\\"
					+ "restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
