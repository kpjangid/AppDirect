package com.kp.appdirect.manage;

import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.kp.appdirect.management.RuntimeConfigurationEngine;

/**
 * 
 * @author Kiran.Jangid
 *
 */

public class AppDirectManagerBootstrap {

	public static AppDirectManagerBootstrap smpManager = new AppDirectManagerBootstrap();

	public RuntimeConfigurationEngine engine = RuntimeConfigurationEngine.getInstance();

	private final ServerState serverState = new ServerState();

	private CloseableHttpClient httpClient = null;

	public ServerState getServerState() {
		return serverState;
	}

	public EnumState getServerStateInformation() {
		return serverState.getServerState();
	}

	public static void main(String[] args) {
		AppDirectManagerBootstrap.smpManager.initializeSystem();
	}

	public void initializeSystem() {

		this.serverState.setStart(true);

		// initializing http client
		init_http_client();

		// initializing jetty interface
		AppDirectJettyInitializer.jettyManager.initializeJetty();

		System.out.println("");
		System.out.println("########################################################");
		System.out.println("       Appp Direct Server has been started");
		System.out.println("########################################################");
		System.out.println("");

		try {
			this.serverState.setStart(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * A method to initialize Apache HTTP Client for sending callback to client
	 * through POST method.
	 * 
	 * @return void
	 */
	public void init_http_client() {

		try {
			MultiThreadedHttpConnectionManager connection = new MultiThreadedHttpConnectionManager();
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();

			params.setConnectionTimeout(5000);

			connection.setParams(params);

			httpClient = HttpClients.createDefault();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public CloseableHttpClient getHttpClient() {
		return this.httpClient;
	}

}
