package config;

import java.io.FileInputStream;
import java.util.Properties;
import javax.management.RuntimeErrorException;
import java.io.File;
import java.io.IOException;

public class ConfigManager {

	private static Properties properties = new Properties();
	static {
		try {
			FileInputStream file = new FileInputStream(new File("./src/test/resources/utils/Config.properties"));
			properties.load(file);
		} catch (IOException e) {
			throw new RuntimeErrorException(null, "Failed to load configuration file due to " + e.getMessage());
		}

	}

	public static String getProperty(String KeyName) {
		return properties.getProperty(KeyName);
	}
}
