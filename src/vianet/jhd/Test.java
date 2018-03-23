package vianet.jhd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	static public List<AlertRule> rules = new ArrayList<>();

	static {

		String str = "";
		File file = new File("alertRules.json");
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray ja = new JSONArray(str);
		for (int i = 0; i < ja.length(); i++) {
			AlertRule alertRule = new AlertRule();
			JSONObject jo = ja.getJSONObject(i);
			alertRule.setId(jo.getString("id"));
			alertRule.setIgnore((jo.getBoolean("ignore")));
			alertRule.setCountdown((jo.getInt("countdown")));

			rules.add(alertRule);
		}
		System.out.println("初始化了：" + rules.size());

	}

	public static void main(String[] args) {
		List<AlertMail> data = new ArrayList<>();
		AlertMail sampleapp = new AlertMail();
		sampleapp.setSubject(
				"Bluemix Alert SEV2 - CN:bmxcn.CYP.sampleApp : st_sampleApp (failed to access Bluemix sample app within 30 secs)");
		AlertMail runtimedown = new AlertMail();
		runtimedown.setSubject(
				"Bluemix Alert SEV2 - CN:bmxcn.CYP.estado.dotnetcore : st_runtime_down (dotnetcore is down)");
		AlertMail sampleapp_r = new AlertMail();
		sampleapp_r.setSubject("Bluemix Alert SEV4 - CN:bmxcn.CYP.sampleApp : st_sampleApp (recovered)");
		AlertMail runtimedown_r = new AlertMail();
		runtimedown_r.setSubject("Bluemix Alert SEV4 - CN:bmxcn.CYP.estado.dotnetcore : st_runtime_down (recovered)");

		data.add(sampleapp);
		data.add(runtimedown);
		data.add(sampleapp_r);
		data.add(runtimedown_r);

		List<Alert> alerts = new ArrayList<>();
		// List<Alert> alerts_r = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			Alert convertAlertMail = AlertConverter.convertAlertMail(data.get(i));
			if (convertAlertMail == null) {// 如果没转换成功 就是该alert被设置为忽略了，所以不用往下走了
				continue;
			}
			if (convertAlertMail.isRecovered()) {
				// alerts_r.add(convertAlertMail);
				for (int j = 0; j < alerts.size(); j++) {
					if (alerts.get(j).getId().equals(convertAlertMail.getId())) {
						System.err.println("准备移除" + convertAlertMail.getId());
						alerts.remove(j);
					}
				}
			} else {
				System.err.println("新建" + convertAlertMail.getId());
				alerts.add(convertAlertMail);
			}
		}
	}
}
