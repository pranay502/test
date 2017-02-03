/**
 * 
 */
package com.jira.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 1084131
 *
 */
public class PropertyLoader {

	private static PropertyLoader propertyLoader;
	/**
	 * Logger
	 */
	//private Logger log = Logger.getLogger(PropertyLoader.class);
	Properties properties;
	
	/**
	 * External setting file 
	 */
	private PropertyLoader() {
	}
	
	public static PropertyLoader getInstance() {
		if (propertyLoader == null) {
			propertyLoader = new PropertyLoader();
		}
		
		return propertyLoader;
	}
	
	
	/**
	 * loads the external properties (report server settings).
	 * 
	 * @return Properties
	 * @throws IOException
	 */
	public Properties getExternalProperties() throws IOException {
		if(properties != null)
			return properties;
		String packName = "/" + Constants.EXTERNAL_PROPERTIES_FILE;
		Properties contextProperties = new Properties();
		Class<? extends PropertyLoader> clazz = getClass();
		contextProperties.load(clazz.getResourceAsStream(packName));
		properties = contextProperties;
		return properties;
		
	}

}
