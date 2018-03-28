package vianet.jhd;

import java.sql.Timestamp;

public class Alert {

	//private String UID;

	private String name; // 可以给这个alert起个简单的名字
	private String subject;// 邮件标题
	private boolean recovered;
	private int SEV = 0;
	private Env env;
	private String metrics;
	private String situation;
	private String id;
	private String status;// 这一项可以先忽略，有的alert会有很多 instance-status
	private String details;
	private String runbooks;

	private String body;// 邮件原文

	private Timestamp receiveTime;

	private int countdown = 0;

	private String color = "#00000000";// 根据严重等级划分颜色，也可以手动改

	class InstanceStatus {
		String instance;
		String status;
	}

	public enum Env {
		yp, ys, yf
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getSEV() {
		return SEV;
	}

	public void setSEV(int sEV) {
		SEV = sEV;
	}

	public String getMetrics() {
		return metrics;
	}

	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getRunbooks() {
		return runbooks;
	}

	public void setRunbooks(String runbooks) {
		this.runbooks = runbooks;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Env getEnv() {
		return env;
	}

	public void setEnv(Env env) {
		this.env = env;
	}

	public boolean isRecovered() {
		return recovered;
	}

	public void setRecovered(boolean recovered) {
		this.recovered = recovered;
	}

	
}
