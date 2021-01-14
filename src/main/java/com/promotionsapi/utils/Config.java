package com.promotionsapi.utils;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class Config {

	private static Properties properties;
	private static final String configPath = "src\\main\\resources\\config.properties";

	public static void initConfigRead() throws Exception {
		properties = new Properties();
		Reader fileReader = new FileReader(configPath);
		properties.load(fileReader);
	}

	public static String getBaseURI() {
		return properties.get("BaseURI").toString();
	}
	
	public static String getAPIKey() {
		return properties.get("apikey").toString();
	}
	
	public static String getInvalidAPIKey() {
		return properties.get("invalidapikey").toString();
	}
}