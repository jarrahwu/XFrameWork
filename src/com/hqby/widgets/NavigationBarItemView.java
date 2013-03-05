package com.hqby.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hqby.framework.XBaseView;

public class NavigationBarItemView extends XBaseView{
	
	/*
	 *  导航条的item 
	 *  包含一个ImageView ， 一个TextView 线性布局。
	 */
	
	private ImageView mImageView;
	
	private TextView mTextView;
	
	private LinearLayout mContainer;
	
	private String key;
	
	private int mIndex;

	public NavigationBarItemView(Context context) {
		super(context);
	}

	public NavigationBarItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		// init container
		mContainer = new LinearLayout(mContext);
		mContainer.setOrientation(LinearLayout.VERTICAL);
		mContainer.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT));
		//init image
		mImageView = new ImageView(mContext);
		mImageView.setLayoutParams(getWeightParams(3.50f));
		//init text
		mTextView = new TextView(mContext);
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setLayoutParams(getWeightParams(1.5f));
		
		mContainer.addView(mImageView);
		mContainer.addView(mTextView);
		
		setContentView(mContainer);
	}

	/**
	 * 获取比重参数
	 * @param weight
	 * @return
	 */
	private LinearLayout.LayoutParams getWeightParams(float weight) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 0, weight);
		return params;
	}
	
	public void setData(NavigationData data) {
		key = data.getText();
		mTextView.setText(data.getText());
		mImageView.setScaleType(ScaleType.CENTER_INSIDE);
		mImageView.setImageResource(data.getDrawable());
	}
	
	
	public void setData(NavigationData data,int index) {
		mIndex = index;
		mTextView.setText(data.getText());
		mImageView.setScaleType(ScaleType.CENTER_INSIDE);
		mImageView.setImageResource(data.getDrawable());
	}
	
	public String getKey() {
		return key;
	}
	
	public int getIndex() {
		return mIndex;
	}
}
