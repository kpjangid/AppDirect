package com.kp.appdirect.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.kp.appdirect.manage.AppDirectManagerBootstrap;
import com.kp.appdirect.management.RuntimeConfigurationEngine;
import com.kp.appdirect.pojo.Subscriber;
import com.kp.appdirect.utility.UserSubscriberCacheDAO;

/**
 * 
 * @author kiran.jangid
 *
 */

public class AppDirectSubscriptionManagementHandler extends AbstractHandler {

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

			Gson gson = new Gson();

			inputStream = request.getInputStream();

			String sub = IOUtils.toString(inputStream);

			Subscriber subcriber = gson.fromJson(sub, Subscriber.class);

			JSONObject json = new JSONObject();

			switch (subcriber.getType()) {
			case RequestParameters.SUBSCRIPTION_ORDER:

				subcriber.getPayload().getAccount()
						.setAccountIdentifier(RuntimeConfigurationEngine.getInstance().getSub_id() + 1);
				subcriber.getPayload().getAccount().setStatus("ACTIVE");

				UserSubscriberCacheDAO.getInstance()
						.addUserSubscriber(RuntimeConfigurationEngine.getInstance().getSub_id() + 1, subcriber);

				json.put("accountIdentifier", RuntimeConfigurationEngine.getInstance().getSub_id() + 1);

				break;
			case RequestParameters.SUBSCRIPTION_CHANGE:
				String accountIdentifier = subcriber.getPayload().getAccount().getAccountIdentifier();
				UserSubscriberCacheDAO.getInstance().updateUSerSubscriber(accountIdentifier, subcriber);
				break;
			case RequestParameters.SUBSCRIPTION_CANCEL:
				String accountIdentifier1 = subcriber.getPayload().getAccount().getAccountIdentifier();
				UserSubscriberCacheDAO.getInstance().removeUSerSubscriber(accountIdentifier1);
				break;
			default:
				break;
			}

			String eventUrl = request.getParameter("eventUrl");

			HttpGet httpGet = new HttpGet(eventUrl);
			CloseableHttpResponse httpResponse = AppDirectManagerBootstrap.smpManager.getHttpClient().execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
				System.out.println("Notification Send Successful");
			} else {
				System.out.println("Notification Sending Failure");
			}

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
