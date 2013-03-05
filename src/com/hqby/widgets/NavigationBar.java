package com.hqby.widgets;

import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hqby.framework.XBaseView;
import com.hqby.framework.XLog;
import com.hqby.test.R;

public class NavigationBar extends XBaseView {
	
	/**
	 * 导航条的容器
	 */
	private LinearLayout  mContainer;
	
	private NavigationBarItemView mItem;
	
	/**
	 * 滑动的view
	 */
	private View mSlide;

	/**
	 * 判断滑动的view是否已经添加进去了
	 */
	private boolean isAdded = false;
	
	private boolean isMoving = false;

	private onNavigatonClickListener mOnNavigatonClickListener;
	
	private OnClickRefreshListener mOnClickRefreshListener;
	
	/**
	 * 把每个item的weight设置一样，平均分配
	 */
	private static final float ITEM_WEIGHT = 1.0f;
	
	public NavigationBar(Context context) {
		super(context);
	}

	public NavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setupViews() {
		initItems();
		setContentView(mContainer);
	}	

	/**
	 * 初始化item
	 */
	private void initItems() {
		mContainer = new LinearLayout(mContext);
		
		mContainer.setOrientation(LinearLayout.HORIZONTAL);
		mContainer.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT));
		
		setConfigure(getNavigationData());
	}
	
	/**
	 * 获取导航条的每个item的数据
	 * @return
	 */
	protected ArrayList<NavigationData> getNavigationData() {
		ArrayList<NavigationData> data = new ArrayList<NavigationData>();
		data.add(new NavigationData("text0", R.drawable.home_page_selected));
		data.add(new NavigationData("text1", R.drawable.hot_normal));
		data.add(new NavigationData("text2", R.drawable.cam_normal));
		data.add(new NavigationData("text3", R.drawable.act_normal));
		data.add(new NavigationData("text4", R.drawable.me_normal));
		return data;
	}
	
	/**
	 * 设置
	 */
	private void setConfigure(ArrayList<NavigationData> data) {
			
		for (int i = 0; i < data.size(); i++) {
			NavigationBarItemView item = new NavigationBarItemView(mContext);
			item.setLayoutParams(getWeightParams(ITEM_WEIGHT));
			item.setData(data.get(i),i);
			bindClickEvent(item, "navItemClick");
			mContainer.addView(item);
			
			//随便获取一个item的引用，用来获取他的大小，以方便设置slide的大小
			if (mItem == null) {
				mItem = item;
			}
		}
		
	}
	
	/**
	 * 获取比重参数
	 * @param weight
	 * @return
	 */
	private LinearLayout.LayoutParams getWeightParams(float weight) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				0,LinearLayout.LayoutParams.FILL_PARENT, weight);
		return params;
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		
		if (!isAdded ) {
			XLog.red("onlayout");
			mSlide = new View(mContext);
			mSlide.setBackgroundResource(R.drawable.slide_bar);
			mSlide.setLayoutParams(new FrameLayout.LayoutParams(mItem
					.getMeasuredWidth(), mItem.getMeasuredHeight()));
			addView(mSlide, 0);
			isAdded = true;
		}
		super.onLayout(changed, left, top, right, bottom);
	}
	
	
	
	/**
	 * 点击的时候触发
	 * @param v
	 */
	public void navItemClick(View v) {
		moveTo(v);
	}

	/**
	 * 移动到目标view所在的位置
	 * @param v
	 */
	private void moveTo (final View v) {
		
		//正在滑动的时候不处理
		if (isMoving) {
			return;
		}
		
		//相等的话，证明位置相同不处理，并触发singletab
		if (mSlide.getRight() == v.getRight()
				&& mSlide.getLeft() == v.getLeft()
				&& mSlide.getTop() == v.getTop()
				&& mSlide.getBottom() == v.getBottom()) {
			if(mOnClickRefreshListener != null)
				mOnClickRefreshListener.onSingleTab((NavigationBarItemView) v,((NavigationBarItemView) v).getIndex());
			return;
		}
		
		//计算移动的距离
		float toXDelta = v.getRight() - mSlide.getRight();
	
		TranslateAnimation translateAnimation = new TranslateAnimation(0, toXDelta , 0, 0);
		translateAnimation.setDuration(200);
		translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		translateAnimation.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				isMoving = true;
			}
			
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			public void onAnimationEnd(Animation animation) {
				mSlide.clearAnimation();
				mSlide.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
				isMoving = false;
				dispatchClickEvent(v);
			}
		});
		mSlide.startAnimation(translateAnimation);
	}

	
	/**
	 * 分配点击事件
	 * @param v
	 */
	private void dispatchClickEvent(View v) {
		NavigationBarItemView navItem = (NavigationBarItemView) v;
		if (mOnNavigatonClickListener != null) {
			mOnNavigatonClickListener.onNavItemClick(navItem,navItem.getIndex());
		}
	}


	/**
	 * @author jarrahWu
	 * 导航条监听器
	 */
	public interface onNavigatonClickListener {
		
		void onNavItemClick(NavigationBarItemView v, int index);
	}
	
	/**
	 * @author jarrahwu
	 * 当slide滑倒一个item的时候，再次点击slide所在的位置触发的监听器
	 */
	public interface OnClickRefreshListener{
		void onSingleTab(NavigationBarItemView v, int index);
	}
	
	/**
	 * 设置监听
	 * @param l
	 */
	public void setOnNavigatonClickListener(onNavigatonClickListener l) {
		mOnNavigatonClickListener = l;
	}
	
	/**
	 * 设置点击刷新的监听事件
	 * @param l
	 */
	public void setOnClickRefreshListener(OnClickRefreshListener l){
		mOnClickRefreshListener = l;
	}
}
