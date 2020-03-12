package com.me.domain;

/**
 * @author 王正帅
 * @date: 2020年3月10日 下午7:01:21
 * 
 */
public class Provinces {
	private int id;
	private String name;
	private String confirm;
	private String suspect;
	private String heal;
	private String dead;
	private String servere;
	private String lastUpdateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getSuspect() {
		return suspect;
	}
	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}
	public String getHeal() {
		return heal;
	}
	public void setHeal(String heal) {
		this.heal = heal;
	}
	public String getDead() {
		return dead;
	}
	public void setDead(String dead) {
		this.dead = dead;
	}
	public String getServere() {
		return servere;
	}
	public void setServere(String servere) {
		this.servere = servere;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
