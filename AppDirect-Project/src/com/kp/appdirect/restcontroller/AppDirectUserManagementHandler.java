package com.kp.appdirect.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * 
 * @author kiran.jangid
 *
 */

public class AppDirectUserManagementHandler extends AbstractHandler {

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
			System.out.println("subContext : " + subContext);
			

			requestType = request.getMethod();

		} catch (Exception e) {
			AppDirectCommonClass.getInstance().sendResponse(httpServletResponse, HttpServletResponse.SC_BAD_GATEWAY,
					null, null, "Request Not Handled", requestHeader);
			AppDirectCommonClass.getInstance().getStackTrace(e);
		}
	}

}
