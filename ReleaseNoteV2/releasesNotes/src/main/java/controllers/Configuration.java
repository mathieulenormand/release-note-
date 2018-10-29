package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exception.ConfigurationException;

public class Configuration {

	private String url;
	private String username;
	private String password;
	private String issueTypes;
	private String issueStates;
	private final String configPath;
	private final Properties properties;

	public Configuration() throws ConfigurationException {
		this.configPath = System.getProperty("config");
		this.properties = new Properties();
		this.load();
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getIssueTypes() {
		return issueTypes;
	}

	public String getIssueStates() {
		return issueStates;
	}

	public String getConfigPath() {
		return configPath;
	}

	private void load() throws ConfigurationException {
		try {
			load(new FileInputStream(this.configPath));
		} catch (final Exception e) {
			throw new ConfigurationException("load properties");
		}
	}

	private void load(final InputStream inputStream) throws IOException, ConfigurationException, ConfigurationException {
		this.properties.load(inputStream);
		
		this.url = getAndCheckProperty("url");
		this.username = getAndCheckProperty("login");
		this.password = getAndCheckProperty("password");
		this.issueStates = getAndCheckProperty("issue.states");
		this.issueTypes= getAndCheckProperty("issue.types");
		
		String[] valuesStates = this.issueStates.split(",");
		if(valuesStates.length < 1) {
			throw new ConfigurationException("issueStates valeur manquante");
		}
		
		String[] valuesTypes = this.issueTypes.split(",");
		if(valuesTypes.length < 1) {
			throw new ConfigurationException("issueTypes valeur manquante");
		}
	}

	private String getAndCheckProperty(final String propertySuffix) throws ConfigurationException {
		final String result = properties.getProperty("config." + propertySuffix);
		if (result == null || result.length() == 0) {
			throw new ConfigurationException("load " + propertySuffix);
		} else {
			//System.out.println(result);
		}
		return result;
	}

}
