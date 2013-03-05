package com.hqby.widgets;

public class NavigationData {
	
	private String text;
	
	private int drawable;
	
	public NavigationData(String text, int drawable) {
		this.text = text;
		this.drawable = drawable;
	}
	
	public void put(String text, int drawable) {
		this.text = text;
		this.drawable = drawable;
	}
	
	public String getText() {
		return this.text;
	}
	
	public int getDrawable() {
		return this.drawable;
	}
}
