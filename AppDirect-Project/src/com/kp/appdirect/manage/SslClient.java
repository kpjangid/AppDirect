package com.kp.appdirect.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author Ekansh.Singhal
 * 16-feb-2016
 *
 */
@SuppressWarnings("deprecation")
public class SslClient {

	CloseableHttpResponse response = null;
	
	public static SslClient client = new SslClient();

	public CloseableHttpResponse sendPostRequest(CloseableHttpClient httpclient,
			File file, String keystore_password, HttpPost httpPost,
			Logger logger) throws Exception {

		try {

			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());

			FileInputStream instream = new FileInputStream(file);
			try {
				trustStore.load(instream, keystore_password.toCharArray());
			} catch (IOException ioe) {

				if (logger != null && logger.isDebugEnabled()) {
					StringWriter sw = new StringWriter();
					ioe.printStackTrace(new PrintWriter(sw));
					logger.debug("Exception in keystore " + ioe.toString()
							+ "\n>> Exception Stack Trace ::" + sw.toString());
				} else if (logger != null && logger.isEnabledFor(Level.ERROR)) {
					logger.error("Error in handler method of SslClient class "
							+ ioe.getMessage());
				}
			
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts
					.custom()
					.loadTrustMaterial(trustStore,
							new TrustSelfSignedStrategy()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = null;
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
					.build();

			if (logger.isDebugEnabled())
				logger.debug("executing request " + httpPost.getRequestLine());
			response = httpclient.execute(httpPost);
		} catch (ConnectException conn) {

			if (logger != null && logger.isDebugEnabled()) {
				StringWriter sw = new StringWriter();
				conn.printStackTrace(new PrintWriter(sw));
				logger.debug("Exception in connection " + conn.toString()
						+ "\n>> Exception Stack Trace ::" + sw.toString());
			} else if (logger != null && logger.isEnabledFor(Level.ERROR)) {
				logger.error("Error in handler method of SslClient class "
						+ conn.getMessage());
			}
		
		}

		return response;
	}
	
	public static void printError(int status, String description, Logger logger) {
		logger.info("Error Code : " + status);
		logger.info("Description : " + description);
	}
}
