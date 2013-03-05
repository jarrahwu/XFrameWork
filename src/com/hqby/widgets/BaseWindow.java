package com.hqby.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hqby.framework.XBaseView;

public abstract class BaseWindow extends XBaseView{
	
	/**
	 * 这个window是否在显示
	 */
	private  boolean isPresent = false;
	
	/**
	 * 这个window是否被添加过
	 */
	private boolean isAdded = false;
	
	public BaseWindow(Context context) {
		super(context);
	}

	public BaseWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 是否显示
	 * @return
	 */
	public boolean isPresent() {
		return isPresent;
	}
	
	/**
	 * 显示
	 */
	public void show() {
		setVisibility(View.VISIBLE);
		isPresent = true;
	}
	
	/**
	 * 隐藏
	 */
	public void hide() {
		setVisibility(View.GONE);
		isPresent = false;
	}
	
	public boolean isAdded() {
		return isAdded;
	}
	
	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}
}
