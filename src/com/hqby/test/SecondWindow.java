package com.hqby.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.hqby.framework.HDataAdapter;
import com.hqby.framework.UICore;
import com.hqby.widgets.BaseListItemView;
import com.hqby.widgets.BaseWindow;
import com.hqby.widgets.ListViewEx;

public class SecondWindow extends BaseWindow {

	public SecondWindow(Context context) {
		super(context);
	}

	public SecondWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		setBackgroundColor(Color.BLUE);
		setContentView(R.layout.test);

		ListViewEx listViewEx = (ListViewEx) findView(R.id.list);

		listViewEx.setAdapter(new HDataAdapter(mContext) {

			@Override
			public void configureItem(BaseListItemView itemView) {
				bindClickEvent(itemView, "itemClick");
			}

			@Override
			public BaseListItemView instanceItemView() {
				return new CommentItemView(mContext);
			}
		});
		listViewEx.loadDataWithUrl(FirstWindow.url, "items", "links", "next");
	}

	public void itemClick(View v) {
		UICore.getInstance().makeToast(mContext,
				"" + ((BaseListItemView) v).getPosition());
	}

}
