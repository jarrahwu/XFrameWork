package com.hqby.framework;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.hqby.test.R;

public abstract  class XBaseView extends FrameLayout implements IFindView{

	protected AQuery mAq;
	
	protected Activity mActivity;
	
	protected Context mContext;
	
	protected View mView;
	
	protected LayoutInflater mInflater;

	private boolean isDebug = true;
	
	public XBaseView(Context context){
		super(context);
		if(isDebug)
			Log.d("VIEW", this.getClass().toString());
		init();
		setupViews();
	}
	
	public XBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if(isDebug)
			Log.d("VIEW", this.getClass().toString());
		init();
		setupViews();
	}
	
	private void init() {
		mContext = getContext();
		mAq = new AQuery(mContext);
		mActivity = (Activity)mContext;
		mInflater = LayoutInflater.from(mContext);
	}
	
	public void setContentView(int layoutId){
		mView = mInflater.inflate(layoutId, null);
		addView(mView);
		
	}
	
	public void setContentView(View v){
		addView(v);
	}
	
	/**
	 * 拉取图片方法.
	 * @param imageview
	 * @param url
	 */
	public void ajaxImage(ImageView imageview,String url){
		mAq.id(imageview).image(url, false, true, 600, R.drawable.ic_launcher, new BitmapAjaxCallback(){
			@Override
			protected void callback(String url, ImageView iv, Bitmap bm,
					AjaxStatus status) {
				iv.setImageBitmap(bm);				
				super.callback(url, iv, bm, status);
			}
		});
	}
	

	
	
	/**
	 * 拉取网络图片并切成圆角
	 * @param imageview
	 * @param url
	 * @param px 圆角的像素大小
	 */
	public void ajaxCornerImage(ImageView imageview,String url,final int px){
		mAq.id(imageview).image(url, false, true, 0, R.drawable.ic_launcher, new BitmapAjaxCallback(){
			@Override
			protected void callback(String url, ImageView iv, Bitmap bm,
					AjaxStatus status) {
				iv.setImageBitmap(cornerBitmap(bm,px));
			}
		});
	}
	
	/**
	 * 切成圆角的图片
	 * @param bitmap 数据源
	 * @param pixels 圆角的像素
	 * @return
	 */
	public Bitmap cornerBitmap(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff000000;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
	

	//findviewbyid 各种find
	public ImageView findImageView(int id) {
		ImageView iv = null;
		iv = (ImageView) findView(id);
		return iv;
	}
	
	public TextView findTextView(int id) {
		TextView tv = null;
		tv = (TextView) findView(id);
		return tv;
	}
	
	public Button findButton(int id) {
		Button btn = null;
		btn = (Button) findView(id);
		return btn;
	}
	
	public ImageButton findImageButton(int id){
		ImageButton ib = null;
		ib = (ImageButton) findView(id);
		return ib;
	}
	
	public ProgressBar findProgressBar(int id){
		ProgressBar pb = null;
		pb = (ProgressBar) findView(id);
		return pb;
	}
	
	public ListView findListView(int id){
		ListView lv = null;
		lv = (ListView) findListView(id);
		return lv;
	}
	
	public ViewPager findViewPager(int id) {
		ViewPager vp = null;
		vp = (ViewPager) findView(id);
		return vp;
	}
	
	public GridView findGridView(int id) {
		GridView gd = null;
		gd = (GridView) findView(id);
		return gd;
	}

	
	protected View findView(int id) {
		View v = null;
		v = findViewById(id);
		if (v == null) {
			Log.e("can not find view id", "~~~~~~~");
		}
		return v;
	}
	
	/**
	 * 绑定点击事件
	 * @param v 需要绑定的View
	 * @param methodName 绑定的方法名 目前只是支持void类型,参数为View的,
	 * 如: private void abc(View v) {
	 * 	   }
	 */
	protected void bindClickEvent(final View v, final String methodName) {
		v.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					Method method = XBaseView.this.getClass().getMethod(methodName, new java.lang.Class[]{View.class});
					method.invoke(XBaseView.this, v);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * view的显示方法,需要重写
	 */
	public abstract void setupViews();
	
}
