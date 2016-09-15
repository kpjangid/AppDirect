package com.kp.appdirect.manage;

/**
 * ServerState Enum Implies State of the Notification Server at any instant of time 
 *
 *
 */
public enum EnumState {

	/**
	 * Init state arises when server is starting and has not yet started.
	 */
	Init(1, "Initializing"),
	
	/**
	 * Failed state arises when server has encountered some exception during init.
	 * Some critical interface could not be initialized.
	 */
	Failed(2, "Failed to start"),
	
	/**
	 * Passive state arises when server has started. But it is not ready to process incoming messages.
	 */
	Passive(3, "Standby mode"),
	
	/**
	 * Active state arises when server has started. And it ready to / already processing incoming messages.
	 */
	Active(4, "Active mode"),
	
	/**
	 * Stopping state arises when server is shutting down.
	 */
	Stopping(5, "Shutting down");
	
	
	
	private EnumState(int id, String desc){
		this.stateId = id;
		this.stateDescription = desc;
		
	}
	
	public int getStateId() {
		return stateId;
	}
	public String getStateDescription() {
		return stateDescription;
	}
	
	private final int stateId;
	private final String stateDescription;
}