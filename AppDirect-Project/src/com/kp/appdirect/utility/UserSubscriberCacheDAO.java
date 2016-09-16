package com.kp.appdirect.utility;

import java.util.HashMap;
import java.util.Map;

import com.kp.appdirect.pojo.Subscriber;

public class UserSubscriberCacheDAO {

	private static UserSubscriberCacheDAO userSubscriberCacheEngine;

	private Map<String, Subscriber> subcriberDao = new HashMap<>();

	private UserSubscriberCacheDAO() {
	}

	static public UserSubscriberCacheDAO getInstance() {

		if (userSubscriberCacheEngine == null) {
			synchronized (UserSubscriberCacheDAO.class) {
				userSubscriberCacheEngine = new UserSubscriberCacheDAO();
			}
		}

		return userSubscriberCacheEngine;

	}

	public Map<String, Subscriber> getUserSubscriberDao() {
		return subcriberDao;
	}

	public Subscriber getUserSubscriberDao(String user) {
		return subcriberDao.get(user);
	}

	public void setUserSubscriberDao(Map<String, Subscriber> userSubscriberDao) {
		this.subcriberDao = userSubscriberDao;
	}

	public void addUserSubscriber(String user, Subscriber subscriber) {
		this.subcriberDao.put(user, subscriber);
	}

	public void removeUSerSubscriber(String user) {
		this.subcriberDao.remove(user);
	}

	public void updateUSerSubscriber(String user, Subscriber subscriber) {

		if (this.subcriberDao.get(user) != null) {
			this.subcriberDao.remove(user);
		}

		this.subcriberDao.put(user, subscriber);

	}

}
