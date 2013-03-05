package com.hqby.test;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;

import com.hqby.widgets.AutoFitImageView;
import com.hqby.widgets.BaseWindow;

public class ThirdWindow extends BaseWindow {
	
	private AutoFitImageView img;

	public ThirdWindow(Context context) {
		super(context);
	}

	public ThirdWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		setBackgroundColor(Color.YELLOW);
		setContentView(R.layout.third_window);
		img = (AutoFitImageView) findViewById(R.id.img);
		img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.test1));
	}
	
	public void adpt() {
		img.adptImageWithLayout();
	}

}
