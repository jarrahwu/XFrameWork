package com.hqby.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.hqby.widgets.BaseWindow;

public class FifthWindow extends BaseWindow {
	
	public FifthWindow(Context context) {
		super(context);
	}

	public FifthWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		setBackgroundColor(Color.MAGENTA);
	}

}
