package vianet.jhd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONArray;

public class AlertRule {

	private String id;
	private boolean ignore;
	private int countdown;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

}
