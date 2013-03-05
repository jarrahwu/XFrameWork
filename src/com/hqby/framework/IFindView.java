package com.hqby.framework;

import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author jarrahwu
 * findview 的规范接口
 */
public  interface IFindView {
	
	ImageView findImageView(int id);

	TextView findTextView(int id);

	Button findButton(int id);

	ImageButton findImageButton(int id);

	ProgressBar findProgressBar(int id);

	ListView findListView(int id);

	ViewPager findViewPager(int id);

	GridView findGridView(int id);
}
