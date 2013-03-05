package com.hqby.widgets;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;

import com.androidquery.AQuery;
import com.hqby.framework.AQHttpManager;
import com.hqby.framework.DataAdapter;
import com.hqby.framework.JSONUtil;
import com.hqby.framework.NormalAPICallBack;
import com.hqby.widgets.XListView.IXListViewListener;

public class ListViewEx extends XListView implements IXListViewListener{
	
	private String mUrl;
	
	private String mJSONKeyWord;
	
	private Context mContext;
	
	private AQuery mAq;
	
	private AQHttpManager mAqHttpManager;
	
	/**
	 * 数据源
	 */
	private JSONArray mDataSource;
	
	/**
	 * 用于拉取更多的数据
	 */
	private JSONArray mLinks;
	
	private DataAdapter mDataAdapter;

	/**
	 * 获取links 的关键字
	 */
	private String mLinksKeyWord;

	/**
	 * 获取拉取更多数据的关键字
	 */
	private String mNextKeyWord;
	
	public ListViewEx(Context context) {
		super(context);
		init(context);
	}

	public ListViewEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ListViewEx(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context) {
		setPullRefreshEnable(true);
		setPullLoadEnable(true);
		setXListViewListener(this);
		mContext = context;
		mAq = new AQuery(mContext);
		mAqHttpManager = new AQHttpManager(mAq);
		//修改它的样式
		setCacheColorHint(0);//修改滑动的时候不出现背景颜色
		setFooterDividersEnabled(false);//不要底部的分割线
		setHeaderDividersEnabled(false);//不要头部的分割线
	}

	public void onRefresh() {
		ajax(mUrl);
	}

	public void onLoadMore() {
		ajaxMore();
	}
	

	/**
	 * 载入数据
	 * @param url 拉取数据的URL
	 * @param jsonKey 成功拉取数据的时候，填充listview的关键字
	 * @param linksKey links的关键字
	 * @param nextKey 在links中获取拉取更多的url的关键字
	 */
	public void loadDataWithUrl(String url,String jsonKey,String linksKey,String nextKey){
		mUrl = url;
		mJSONKeyWord = jsonKey;
		mLinksKeyWord = linksKey;
		mNextKeyWord = nextKey;
		ajax(mUrl);
	}

	/**
	 * 拉取数据
	 * @param url
	 */
	private void ajax(String url) {
		mAqHttpManager.get(url, new NormalAPICallBack(mContext,"加载数据") {
			
			@Override
			public void handleCallBack(JSONObject obj) {
				stopRefresh();
				updateSelf(obj);
				
				mLinks = JSONUtil.getJsonArrays(obj, mLinksKeyWord);
			}

			@Override
			public void onError(int httpCode) {
				super.onError(httpCode);
				stopRefresh();
			}
		});
	}
	
	/**
	 * 拉取更多
	 */
	private void ajaxMore() {
		//拉取更多的url
		String url = JSONUtil.getHrefByRel(mLinks, mNextKeyWord);
		
		mAqHttpManager.get(url, new NormalAPICallBack(mContext,"拉取更多数据") {
			
			@Override
			public void handleCallBack(JSONObject obj) {
				stopLoadMore();
				joinData(obj);
				mDataAdapter.setData(mDataSource);
				mLinks = JSONUtil.getJsonArrays(obj, mLinksKeyWord);
			}
			
			@Override
			public void onError(int httpCode) {
				super.onError(httpCode);
				stopLoadMore();
			}
		});
	}
	
	/**
	 * 刷新显示的数据
	 * @param obj
	 */
	private void updateSelf(JSONObject obj) {
		mDataSource = JSONUtil.getJsonArrays(obj, mJSONKeyWord);
		mDataAdapter.setData(mDataSource);
		//这里可以添加对数据的处理
	}
		
	@Override
	public void setAdapter(ListAdapter adapter) {
		mDataAdapter = (DataAdapter) adapter;
		super.setAdapter(adapter);
	}
	
	/**
	 * 组合数据
	 * @param obj
	 */
	private void joinData(JSONObject obj) {
		JSONArray arrys = JSONUtil.getJsonArrays(obj, mJSONKeyWord);
		for(int i = 0; i < arrys.length(); i++){
			JSONObject itemObj = JSONUtil.getJsonObjByIndex(arrys, i);
			mDataSource.put(itemObj);
		}
	}

}
