package com.hqby.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.hqby.widgets.BaseWindow;

public class FourthWindow extends BaseWindow {
	
	public FourthWindow(Context context) {
		super(context);
	}

	public FourthWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		setBackgroundColor(Color.GREEN);
	}

}
