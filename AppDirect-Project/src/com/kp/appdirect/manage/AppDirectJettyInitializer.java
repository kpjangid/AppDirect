package com.kp.appdirect.manage;

import com.kp.appdirect.management.EnumParameters;
import com.kp.appdirect.restcontroller.AppDirectNotificationManagementHandler;
import com.kp.appdirect.restcontroller.AppDirectSubscriptionManagementHandler;
import com.kp.appdirect.restcontroller.AppDirectUserManagementHandler;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * 
 * @author Kiran.Jangid
 *
 */

public class AppDirectJettyInitializer {

	public static AppDirectJettyInitializer jettyManager = new AppDirectJettyInitializer();
	private Server server;
	private ContextHandlerCollection contexts = null;
	private SslContextFactory sslContextFactory;

	AppDirectJettyInitializer() {
		System.out.println("Initializing the Jetty interfaces of the system....");
	}

	public Server getServer() {
		return server;
	}

	/**
	 * 
	 * @author Kiran.Jangid
	 *
	 */

	public void initializeJetty() {

		try {

			System.setProperty("org.eclipse.jetty.util.log.class", Logger.class.getName());

			// Creating a basic Jetty Server Object

			QueuedThreadPool threadpool = new QueuedThreadPool();
			threadpool.setMinThreads(8);
			threadpool.setMaxThreads(24);

			server = new Server(threadpool);

			// Creating HTTP connector object that will listen on the configured
			// host and port
			ServerConnector connector = new ServerConnector(server);
			connector.setHost(EnumParameters.http_host.getStringDescription());
			connector.setPort(EnumParameters.http_port.getIntDescription());

			HttpConfiguration https = new HttpConfiguration();
			https.addCustomizer(new SecureRequestCustomizer());

			sslContextFactory = new SslContextFactory(EnumParameters.sslKeystorePath.getStringDescription() + "/"
					+ EnumParameters.sslKeystoreName.getStringDescription());

			sslContextFactory.setKeyStorePassword(EnumParameters.sslKeystorePassword.getStringDescription());
			sslContextFactory.setKeyManagerPassword(EnumParameters.sslKeymanagerPassword.getStringDescription());

			ServerConnector sslConnector = new ServerConnector(server,
					new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
			sslConnector.setPort(EnumParameters.host_https_port.getIntDescription());

			server.setConnectors(new Connector[] { connector, sslConnector });

			MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());

			server.addEventListener(mbContainer);
			server.addBean(mbContainer);
			server.addBean(Log.getLog());

			// Creating Context collection, Handler collection.
			contexts = new ContextHandlerCollection();

			// BTAS User Controller Handler
			ContextHandler userManagementControllerContext = new ContextHandler();
			userManagementControllerContext
					.setContextPath(EnumParameters.http_user_management_context.getStringDescription());
			Handler userManagementControllerHandler = new AppDirectUserManagementHandler();
			userManagementControllerContext.setHandler(userManagementControllerHandler);
			contexts.addHandler(userManagementControllerContext);

			// BTAS role Controller Handler
			ContextHandler subscriptionManagementControllerContext = new ContextHandler();
			subscriptionManagementControllerContext
					.setContextPath(EnumParameters.http_subscription_management_context.getStringDescription());
			Handler subscriptionManagementControllerHandler = new AppDirectSubscriptionManagementHandler();
			subscriptionManagementControllerContext.setHandler(subscriptionManagementControllerHandler);
			contexts.addHandler(subscriptionManagementControllerContext);

			// BTAS role Controller Handler
			ContextHandler notificationManagementControllerContext = new ContextHandler();
			notificationManagementControllerContext
					.setContextPath(EnumParameters.http_notification_management_context.getStringDescription());
			Handler notificationManagementControllerHandler = new AppDirectNotificationManagementHandler();
			notificationManagementControllerContext.setHandler(notificationManagementControllerHandler);
			contexts.addHandler(notificationManagementControllerContext);

			HandlerCollection handlers;
			handlers = new HandlerCollection();
			handlers.setHandlers(new Handler[] { contexts, new DefaultHandler() });
			this.server.setHandler(handlers);

			this.server.start();
			AppDirectManagerBootstrap.smpManager.getServerState().setJettyIntf(true);

		} catch (Exception e) {

			e.printStackTrace();

			System.exit(0);
		}

	}

}
