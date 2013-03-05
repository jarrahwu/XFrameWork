package com.hqby.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.hqby.framework.DataAdapter;
import com.hqby.framework.UICore;
import com.hqby.widgets.BaseListItemView;
import com.hqby.widgets.BaseWindow;
import com.hqby.widgets.ListViewEx;

public class FirstWindow extends BaseWindow{
	
	static final String url = "http://api.test.szhqby.com/j2/waterfall";
	
	public FirstWindow(Context context) {
		super(context);
	}

	public FirstWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setupViews() {
		setBackgroundColor(Color.RED);
		setContentView(R.layout.test);
		
		ListViewEx listViewEx = (ListViewEx) findView(R.id.list);
		
		listViewEx.setAdapter(new DataAdapter(mContext) {
			
			@Override
			public void configureItem(BaseListItemView itemView) {
				bindClickEvent(itemView, "itemClick");
			}

			@Override
			public BaseListItemView instanceItemView() {
				return new CommentItemView(mContext);
			}
		});
		listViewEx.loadDataWithUrl(url, "items", "links", "next");
	}
	
	public void itemClick(View v) {
		UICore.getInstance().makeToast(mContext, "" + ((BaseListItemView)v).getPosition());
	}

	
}
