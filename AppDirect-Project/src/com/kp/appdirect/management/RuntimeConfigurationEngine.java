package com.kp.appdirect.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author kiran.jangid
 *
 */

public class RuntimeConfigurationEngine {

	private static final RuntimeConfigurationEngine runtimeEngine = new RuntimeConfigurationEngine();

	private String sub_id;

	// List of All the configuration parameter
	private Properties systemSettings = new Properties();

	RuntimeConfigurationEngine() {
		loadPreferences();
	}

	public static RuntimeConfigurationEngine getInstance() {
		return runtimeEngine;
	}

	private void loadPreferences() {

		// load system settings
		File fileSystemSettings = null;

		FileInputStream fileStreamSystemSettings = null;

		try {

			fileSystemSettings = new File("./configuration/system_settings.properties");

			fileStreamSystemSettings = new FileInputStream(fileSystemSettings);
			this.systemSettings.load(fileStreamSystemSettings);

		} catch (FileNotFoundException e) {

			getStackTrace(e);

		} catch (Exception e) {
			getStackTrace(e);

		} finally {

			try {

				if (fileStreamSystemSettings != null) {
					fileStreamSystemSettings.close();
				}

			} catch (IOException e) {

				getStackTrace(e);

			}
		}

		// Reading parameters from Service_settings.properties file
		EnumParameters.http_host.setStringDescription(this.systemSettings.getProperty("http_host"));
		EnumParameters.http_port.setIntDescription(Integer.parseInt(this.systemSettings.getProperty("http_port")));
		EnumParameters.host_https_port
				.setIntDescription(Integer.parseInt(this.systemSettings.getProperty("https_port")));

		EnumParameters.http_user_management_context
				.setStringDescription(this.systemSettings.getProperty("user_context"));
		EnumParameters.http_subscription_management_context
				.setStringDescription(this.systemSettings.getProperty("subscription_context"));
		EnumParameters.http_notification_management_context
				.setStringDescription(this.systemSettings.getProperty("notification_context"));

		EnumParameters.sslKeystorePath.setStringDescription(this.systemSettings.getProperty("ssl_keystore_path"));
		EnumParameters.sslKeystoreName.setStringDescription(this.systemSettings.getProperty("ssl_keystore_name"));
		EnumParameters.sslKeystorePassword
				.setStringDescription(this.systemSettings.getProperty("ssl_keystore_password"));
		EnumParameters.sslKeymanagerPassword
				.setStringDescription(this.systemSettings.getProperty("ssl_manager_password"));
		
		setSub_id("Subcription_id_00");

	}

	private void getStackTrace(Exception e) {

		String error = e.toString() + "\n";
		for (StackTraceElement element : e.getStackTrace())
			error = error + "\tat " + element.getClassName() + "." + element.getMethodName() + "("
					+ element.getFileName() + ":" + element.getLineNumber() + ")\n";

		System.out.println(error);
	}

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

}