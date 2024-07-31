package com.kh.house.java.chap02.store;

public abstract class HamburgerStore {
	private String menu;
	
	public HamburgerStore(String menu) {
		this.menu = menu;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
}
