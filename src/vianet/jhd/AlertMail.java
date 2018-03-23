package vianet.jhd;

public class AlertMail {
	private String subject;
	private String body;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AlertMail() {
		super();

	}

	public AlertMail(String subject, String body) {
		super();
		this.subject = subject;
		this.body = body;
	}

}
