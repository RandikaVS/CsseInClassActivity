package com.employee.common;

import java.util.logging.Logger;
import java.lang.System.Logger.Level;
import java.util.Properties;

public class BaseUtility {
	
	public static final Properties properties = new Properties();
	
	private static final Logger log = Logger.getLogger(BaseUtility.class.getName());
	
	public static final String CONFIG_CONFIG_PROPERTIES = "../config/config.properties";


	static {
		
		try {
			// loading the properties file memory
			properties.load(EmployeeQueryUtility.class.getResourceAsStream(CONFIG_CONFIG_PROPERTIES));
		} 
		catch(NumberFormatException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (NullPointerException e) {
			log.log(Level.ERROR, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.ERROR, e.getMessage());
		}
	}

}
