package com.kp.appdirect.manage;

public class ServerState {	
	private boolean sipIntf;
	private boolean diaOrChargingIntf;
	private boolean logMod;
	private boolean alarmMod;
	private boolean counterMod;
	private boolean configMod;
	private boolean jettyIntf;
	private boolean loadMod;
	private boolean nettyLB;
	private boolean emsMod;
	private boolean isStart ;
	
	private boolean isShuttingDown;
	
	private EnumState serverState;
	
	public EnumState getServerState() {
		return serverState;
	}

	public ServerState () {
		serverState = EnumState.Init;
	}
	
	
	public boolean isSipIntf() {
		return sipIntf;
	}
	public void setSipIntf(boolean sipIntf) {
		this.sipIntf = sipIntf;
		update();
	}
	public boolean isDiaOrChargingIntf() {
		return diaOrChargingIntf;
	}
	public void setDiaOrChargingIntf(boolean diaOrChargingIntf) {
		this.diaOrChargingIntf = diaOrChargingIntf;
		update();
	}
	public boolean isLogMod() {
		return logMod;
	}
	public void setLogMod(boolean logMod) {
		this.logMod = logMod;
		update();
	}
	public boolean isNettyLB(){
		return nettyLB;
	}
	public void setNettyLB(boolean nettyLB){
		this.nettyLB = nettyLB;
		update();
	}
	public boolean isAlarmMod() {
		return alarmMod;
	}
	public void setAlarmMod(boolean alarmMod) {
		this.alarmMod = alarmMod;
		update();
	}
	public boolean isCounterMod() {
		return counterMod;
	}
	public void setCounterMod(boolean counterMod) {
		this.counterMod = counterMod;
		update();
	}
	public boolean isConfigMod() {
		return configMod;
	}
	public void setConfigMod(boolean configMod) {
		this.configMod = configMod;
		update();
	}
	public boolean isJettyIntf() {
		return jettyIntf;
	}
	public void setJettyIntf(boolean jettyIntf) {
		this.jettyIntf = jettyIntf;
		update();
	}
	public boolean isLoadMod() {
		return loadMod;
	}
	public void setLoadMod(boolean loadMod) {
		this.loadMod = loadMod;
		update();
	}
	public boolean isEmsMod() {
		return emsMod;
	}
	public void setEmsMod(boolean emsMod) {
		this.emsMod = emsMod;
		update();
	}
	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
		update();
	}

	public boolean isShuttingDown() {
		return isShuttingDown;
	}

	public void setShuttingDown(boolean isShuttingDown) {
		this.isShuttingDown = isShuttingDown;
		update();
	}
	
	private void update(){
		if(isStart){
			this.serverState = EnumState.Init;
			return;
		}
		
		if(isShuttingDown){
			this.serverState = EnumState.Stopping;
		}
		
		boolean isSuccess = (this.alarmMod && this.configMod && this.counterMod 
						&& this.sipIntf && this.diaOrChargingIntf && this.jettyIntf && this.logMod);
		
		if(isSuccess){
			if(this.loadMod || this.nettyLB){
				this.serverState = EnumState.Active;
			}else {
				this.serverState = EnumState.Passive;
			}
		}else {
			this.serverState = EnumState.Failed;
		}
	}
}

