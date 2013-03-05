package com.hqby.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class AutoFitImageView extends ImageView {
	
	private int mWidth;
	
	private float mPercentage;
	
	public AutoFitImageView(Context context) {
		super(context);
	}

	public AutoFitImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	public AutoFitImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获取宽度
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	
	@Override
	public void setImageBitmap(Bitmap bm) {
		mPercentage = bm.getHeight() / (float) bm.getWidth();
		 super.setImageBitmap(bm);
	}
	
	/**
	 * 按照比例适配图片
	 */
	public void adptImageWithLayout() {
		int imageHeight = (int) (mWidth * mPercentage);
		LayoutParams params = getLayoutParams();
		params.height = imageHeight;
		setLayoutParams(params);
	}

}
