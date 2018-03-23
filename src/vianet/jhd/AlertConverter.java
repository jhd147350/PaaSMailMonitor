package vianet.jhd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertConverter {

	public static Alert convertAlertMail(AlertMail alertMail) {
		System.out.println(extractSev(alertMail.getSubject()));
		System.out.println(extractEnv(alertMail.getSubject()));
		System.out.println("issue:" + extractIssue(alertMail.getSubject()));
		System.out.println(extractRecovered(alertMail.getSubject()));
		System.out.println(extractIP(alertMail.getSubject()));

		Alert alert = new Alert();

		String id = extractIssue(alertMail.getSubject());
		alert.setId(id);

		boolean recovered = extractRecovered(alertMail.getSubject());
		alert.setRecovered(recovered);
		if (!recovered) {// 不对恢复类Alert设置倒计时 并且如果该id被忽略也就跳过
			for (int i = 0; i < Test.rules.size(); i++) {
				if (id.equals(Test.rules.get(i).getId())) {
					if (Test.rules.get(i).isIgnore()) {
						return null;
					}
					alert.setCountdown(Test.rules.get(i).getCountdown());
					System.out.println("一个工单设置了时间" + Test.rules.get(i).getCountdown());
					break;
				}
			}
		}
		alert.setSEV(extractSev(alertMail.getSubject()));
		alert.setEnv(extractEnv(alertMail.getSubject()));

		return alert;

	}

	private static int extractSev(String subject) {

		String reg = "(?<=SEV)(\\d){1}";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(subject);
		if (m.find()) {
			return Integer.valueOf(m.group());
		}
		return 0;
	}

	private static Alert.Env extractEnv(String subject) {
		// CN:bmxcn.yf
		// CN:bmxcn.ys1
		// CN:bmxcn.CYP
		String reg = "(?<=CN:bmxcn\\.)(\\w){2,3}";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(subject);
		if (m.find()) {
			System.out.println(m.group());
			switch (m.group()) {
			case "yf":
				return Alert.Env.yf;
			case "ys1":
				return Alert.Env.ys;
			case "CYP":
				return Alert.Env.yp;
			}
		}
		return null;
	}

	private static String extractIssue(String subject) {

		// 先将 括号内容都去掉，断言去不了多个括号，目前研究不出来了
		String divideReg = "\\(.*\\)";
		String divideSubject = subject.replaceAll(divideReg, "");
		System.out.println("after divide:" + divideSubject);

		String reg = "(?<=- ).*";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(divideSubject);
		if (m.find()) {
			return m.group();
		}
		return null;
	}

	private static boolean extractRecovered(String subject) {
		String reg = "\\(recovered\\)";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(subject);
		if (m.find()) {
			return true;
		}
		return false;
	}

	private static String extractIP(String subject) {
		// (Bosh (cell_az2 ["192.168.20.12"])
		// 直接去引号中间的内容即可
		String reg = "(?<=\").*?(?=\")";

		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(subject);
		if (m.find()) {
			return m.group();
		}
		return null;
	}

	public static void main(String[] args) {
		// test
		AlertMail alertMail = new AlertMail();
		String subject = "Bluemix Alert SEV2 - CN:bmxcn.CYP.fabric.bosh.memoryPercent : st_bosh_mem_high (Bosh (api_worker_2 [\"192.168.22.85\"]) memory percent > 80)";

		alertMail.setSubject(subject);
		AlertConverter.convertAlertMail(alertMail);
	}
}
