package com.hqby.test;

import java.util.ArrayList;

import com.hqby.framework.UICore;
import com.hqby.widgets.BaseWindow;
import com.hqby.widgets.NavigationBar;
import com.hqby.widgets.NavigationBar.OnClickRefreshListener;
import com.hqby.widgets.NavigationBar.onNavigatonClickListener;
import com.hqby.widgets.NavigationBarItemView;
import com.hqby.widgets.WindowContainer;

import android.app.Activity;
import android.os.Bundle;

public class XFrameWorkActivity extends Activity implements onNavigatonClickListener, OnClickRefreshListener {
    /** Called when the activity is first created. */
	
	private NavigationBar mNavigationBar;
	
	private WindowContainer mWindowContainer;
	
	private ArrayList<BaseWindow> mWindows;

	private ThirdWindow mThirdWindow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWindowContainer = (WindowContainer) findViewById(R.id.window_container);
        mNavigationBar = (NavigationBar)findViewById(R.id.nav_bar);
        mNavigationBar.setOnNavigatonClickListener(this);
        mNavigationBar.setOnClickRefreshListener(this);
        
        mWindows = new ArrayList<BaseWindow>();
        mWindows.add(new FirstWindow(this));
        mWindows.add(new SecondWindow(this));
        
        mThirdWindow = new ThirdWindow(this);
        mWindows.add(mThirdWindow);
        mWindows.add(new FourthWindow(this));
        mWindows.add(new FifthWindow(this));
        mWindowContainer.initWindows(mWindows);
    }

	public void onNavItemClick(NavigationBarItemView v,int index) {
		mWindowContainer.showWindow(index);
	}

	public void onSingleTab(NavigationBarItemView v, int index) {
		UICore.getInstance().makeToast(this, "click" + index);
		if (index == 2) {
			mThirdWindow.adpt();
		}
	}

}