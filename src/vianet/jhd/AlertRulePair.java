package vianet.jhd;

public abstract class AlertRulePair {
	String name;
	boolean ignore;// 是否忽略这一组
	// 手动指定ID
	int id;// 根据这个id去用新的恢复的邮件消除已有alert
}
