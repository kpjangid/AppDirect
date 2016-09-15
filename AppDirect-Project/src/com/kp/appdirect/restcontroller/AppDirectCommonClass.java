package com.kp.appdirect.restcontroller;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class AppDirectCommonClass {

	private static AppDirectCommonClass appDirectCommonClass = null;

	private AppDirectCommonClass() {

	}

	public static synchronized AppDirectCommonClass getInstance() {
		if (appDirectCommonClass == null) {
			appDirectCommonClass = new AppDirectCommonClass();
		}
		return appDirectCommonClass;
	}

	public void getStackTrace(Exception e) {
		String error = e.toString() + "\n";
		for (StackTraceElement element : e.getStackTrace())
			error = error + "\tat " + element.getClassName() + "." + element.getMethodName() + "("
					+ element.getFileName() + ":" + element.getLineNumber() + ")\n";

		System.out.println(error);
	}

	void sendResponse(HttpServletResponse httpServletResponse, int httpCode, String errorCode, JSONObject appData,
			String log, String requestHeader) {

		try {

			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

			if (requestHeader != null) {
				httpServletResponse.setHeader("Access-Control-Allow-Headers", requestHeader);
			}

			httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");

			String json = appData.toString();

			try {

				httpServletResponse.getWriter().println(json.toString());

				System.out.println("log =" + log);

			} catch (Exception e) {
				getStackTrace(e);
			}
		} catch (Exception e) {

			getStackTrace(e);
		}

	}

}
