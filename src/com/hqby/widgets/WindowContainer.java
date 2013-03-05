package com.hqby.widgets;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import com.hqby.framework.XBaseView;

public class WindowContainer extends XBaseView{
	
	private BaseWindow mPresentWindow;
	
	private ArrayList<BaseWindow> mWindows;
	
	public WindowContainer(Context context) {
		super(context);
	}

	public WindowContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		
	}
	
	public void initWindows (ArrayList<BaseWindow> windows) {
		mWindows = windows;
		showWindow(0);
	}

	public void showWindow(int index) {
	   BaseWindow current = mWindows.get(index);
		//把以前显示的隐藏掉
		if(mPresentWindow != null && mPresentWindow != current) {
			mPresentWindow.hide();
		}
		//没有添加过的添加进去，添加进去的直接隐藏
		if(!current.isPresent() && !current.isAdded()){
			setContentView(current);
			current.setAdded(true);
		}
	   current.show();
	   mPresentWindow = current;
	}

}
