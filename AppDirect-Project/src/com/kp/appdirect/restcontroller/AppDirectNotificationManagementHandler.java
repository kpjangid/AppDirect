package com.kp.appdirect.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.kp.appdirect.management.RuntimeConfigurationEngine;
import com.kp.appdirect.pojo.Subscriber;
import com.kp.appdirect.response.Response;

/**
 * 
 * @author kiran.jangid
 *
 */

public class AppDirectNotificationManagementHandler extends AbstractHandler {

	String requestHeader = null;
	String requestType = null;

	InputStream inputStream;

	@Override
	public void handle(String arg0, Request request, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException, ServletException {

		request.setHandled(true);

		requestHeader = request.getHeader("Access-Control-Request-Headers");

		try {

			request.setHandled(true);

			if (request.getMethod().equals(HttpMethod.OPTIONS.asString())) {
				AppDirectCommonClass.getInstance().sendResponse(httpServletResponse, 200, null, null,
						"received options", requestHeader);
			}

			String subContext = request.getPathInfo();

			System.out.println("subcontext : " + subContext);

			requestType = request.getMethod();

			Gson gson = new Gson();

			inputStream = request.getInputStream();

			String sub = IOUtils.toString(inputStream);

			Subscriber subcriber = gson.fromJson(sub, Subscriber.class);

			JSONObject json = new JSONObject();

			switch (subcriber.getType()) {
			case RequestParameters.SUBSCRIPTION_ORDER:
				json.put("accountIdentifier", RuntimeConfigurationEngine.getInstance().getSub_id() + 1);
				json.put("success", true);
				break;
			case RequestParameters.SUBSCRIPTION_CHANGE:
				json.put("accountIdentifier", RuntimeConfigurationEngine.getInstance().getSub_id() + 1);
				json.put("success", true);
				break;
			case RequestParameters.SUBSCRIPTION_CANCEL:
				json.put("accountIdentifier", RuntimeConfigurationEngine.getInstance().getSub_id() + 1);
				json.put("success", true);
				break;
			default:
				break;
			}
			
			json.put("accountIdentifier", RuntimeConfigurationEngine.getInstance().getSub_id() + 1);
			json.put("success", true);

			AppDirectCommonClass.getInstance().sendResponse(httpServletResponse, HttpServletResponse.SC_BAD_GATEWAY,
					null, json, "Request Not Handled", requestHeader);

		} catch (Exception e) {
			AppDirectCommonClass.getInstance().sendResponse(httpServletResponse, HttpServletResponse.SC_BAD_GATEWAY,
					null, null, "Request Not Handled", requestHeader);
			AppDirectCommonClass.getInstance().getStackTrace(e);
		}
	}

}
