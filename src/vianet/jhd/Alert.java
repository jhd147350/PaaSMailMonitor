package vianet.jhd;

import java.sql.Timestamp;

public class Alert {
	String name; // 可以给这个alert起个简单的名字
	String subject;// 邮件标题
	int SEV = 0;
	String metrics;
	String situation;
	String id;
	String status;// 这一项可以先忽略，有的alert会有很多 instance-status
	String details;
	String runbooks;

	String body;// 邮件原文

	Timestamp receiveTime;

	int countdown = 0;

	String color = "#00000000";// 根据严重等级划分颜色，也可以手动改

	class InstanceStatus {
		String instance;
		String status;
	}
}
