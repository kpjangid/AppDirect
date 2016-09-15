package com.kp.appdirect.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.util.log.Logger;

public class AppDirectLogger  implements Logger {

	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppDirectLogger.class);

	public static Level defaultLogLevel = Level.INFO;
	private static Level currentLogLevel;

	public static void initLogger() throws IOException {

		InputStream in = null;

		try {

			ClassLoader classLoader = AppDirectLogger.class.getClassLoader();
			in = classLoader.getResourceAsStream("log4j.properties");

			Properties configProps = new Properties();
			configProps.load(in);
			in.close();
			PropertyConfigurator.configure(configProps);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}
	}

	public static Level getCurrentLogLevel() {
		return currentLogLevel;
	}

	public static void setCurrentLogLevel(Level level) {

		try {

			if (level.equals(org.apache.log4j.Level.INFO)) {
				logger.setLevel(org.apache.log4j.Level.INFO);
				currentLogLevel = Level.INFO;
			}

			else if (level.equals(org.apache.log4j.Level.ERROR)) {
				logger.setLevel(org.apache.log4j.Level.ERROR);
				currentLogLevel = Level.ERROR;
			}

			else if (level.equals(org.apache.log4j.Level.DEBUG)) {
				logger.setLevel(org.apache.log4j.Level.DEBUG);
				currentLogLevel = Level.DEBUG;
			}

			else if (level.equals(org.apache.log4j.Level.ALL)) {
				logger.setLevel(org.apache.log4j.Level.ALL);
				currentLogLevel = Level.ALL;
			}

			else if (level.equals(org.apache.log4j.Level.WARN)) {
				logger.setLevel(org.apache.log4j.Level.WARN);
				currentLogLevel = Level.WARN;
			}

			else if (level.equals(org.apache.log4j.Level.FATAL)) {
				logger.setLevel(org.apache.log4j.Level.FATAL);
				currentLogLevel = Level.FATAL;
			}

			else {

				logger.setLevel(org.apache.log4j.Level.INFO);
				currentLogLevel = Level.INFO;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void debug(String message, Throwable exception) {
		if (logger.isDebugEnabled())
			logger.debug(message);

	}

	/*
	 * public void debug(String message, Object arg1, Object arg2) {
	 * if(logger.isDebugEnabled()) logger.debug(message);
	 * 
	 * }
	 */

	public Logger getLogger(String arg0) {
		return this;
	}

	/*
	 * public void info(String message, Object arg1, Object arg2) {
	 * if(logger.isInfoEnabled()) logger.info(message);
	 * 
	 * }
	 */

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public void setDebugEnabled(boolean arg0) {
		if (arg0) {
			AppDirectLogger.setCurrentLogLevel(Level.DEBUG);
		} else {
			logger.setLevel(AppDirectLogger.defaultLogLevel);
			AppDirectLogger
					.setCurrentLogLevel(AppDirectLogger.defaultLogLevel);
		}
	}

	@SuppressWarnings("deprecation")
	public void warn(String message, Throwable arg1) {
		if (logger.isEnabledFor(Priority.WARN))
			logger.warn(message);

	}

	/*
	 * @SuppressWarnings("deprecation") public void warn(String message, Object
	 * arg1, Object arg2) { if(logger.isEnabledFor(Priority.WARN))
	 * logger.warn(message);
	 * 
	 * }
	 */

	@Override
	public void debug(Throwable arg0) {
		if (logger.isDebugEnabled())
			logger.debug(arg0);

	}

	@Override
	public void debug(String arg0, Object... arg1) {

		if (logger.isDebugEnabled())
			logger.debug(arg0);
	}

	@Override
	public String getName() {

		return logger.getName();
	}

	@Override
	public void ignore(Throwable arg0) {

	}

	@Override
	public void info(Throwable arg0) {

		logger.info(arg0);

	}

	@Override
	public void info(String arg0, Object... arg1) {

		logger.info(arg0);
	}

	@Override
	public void info(String arg0, Throwable arg1) {

		logger.info(arg0);
	}

	@Override
	public void warn(Throwable arg0) {

		logger.warn(arg0);
	}

	@Override
	public void warn(String arg0, Object... arg1) {

		logger.warn(arg0);

	}

	@Override
	public void debug(String arg0, long arg1) {
		logger.debug(arg0);
	}

}
