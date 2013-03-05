package com.hqby.widgets;

import java.util.ArrayList;

import com.hqby.test.R;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author Jarrah
 * 只有两个item的导航条
 */
public class DoubleItemNavigationBar extends NavigationBar{
	
	public DoubleItemNavigationBar(Context context) {
		super(context);
	}

	public DoubleItemNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected ArrayList<NavigationData> getNavigationData() {
		ArrayList<NavigationData> data = new ArrayList<NavigationData>();
		data.add(new NavigationData("text0", R.drawable.home_page_selected));
		data.add(new NavigationData("text1", R.drawable.hot_normal));
		return data;
	}

}
