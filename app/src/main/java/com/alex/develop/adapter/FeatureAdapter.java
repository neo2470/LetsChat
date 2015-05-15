package com.alex.develop.adapter;

import com.alex.develop.androidstart.R;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 新特性介绍页面（ViewPager）的生成
 */
public class FeatureAdapter extends PagerAdapter implements
		OnPageChangeListener {
	
	/**
	 * 当ViewPager处于最后一页，用户依然向左滑动（右侧没有页面）时触发的事件
	 */
	public interface OnPageScolledListener {
		
		/**
		 * ViewPager最后一页，手势向左滑动触发该方法
		 */
		public void onLastPageScolled2Left();
	}

	public FeatureAdapter(View[] views) {
		this.views = views;
	}
	
	public void setPageScolledListener(OnPageScolledListener pageScolledListener) {
		this.pageScolledListener = pageScolledListener;
	}

	@Override
	public int getCount() {
		return views.length;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		// 判断用户是否在拖拽画面
		isScolling = ViewPager.SCROLL_STATE_DRAGGING == arg0;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		// 到达ViewPager的最后一页，才可执行后面的操作
		if(views.length != (arg0+1)) {
			return ;
		}
		
		// 在ViewPager的最后一页，向左滑动，进入主界面
		if(0.0f == arg1 && 0 == arg2 && isScolling) {
			if(!lastPageWasScolledLeft) {
				pageScolledListener.onLastPageScolled2Left();
				lastPageWasScolledLeft = true;
			}
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		
		// 设置当前页面对应的指示器为激活状态
		ImageView indicator = (ImageView) views[arg0].findViewById(getIndicator(arg0));
		indicator.setImageResource(R.drawable.circle_dot_activited);
		
		// 设置其余的指示器为正常状态
		for(int i=0; i<views.length; ++i) {
			if(i!=arg0) {
				indicator = (ImageView) views[arg0].findViewById(getIndicator(i));
				indicator.setImageResource(R.drawable.circle_dot_normal);
			}
		}
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(views[position]);
		return views[position];
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(views[position]);
	}
	
	private int getIndicator(int index) {
		int resId = -1;
		switch (index) {
			case 0:
				resId = R.id.indicator_1;
				break;
			case 1:
				resId = R.id.indicator_2;
				break;
			case 2:
				resId = R.id.indicator_3;
				break;
		}
		return resId;
	}

	private View[] views;// 每个特性界面的布局
	private OnPageScolledListener pageScolledListener;// ViewPager最后一页向左滑动的监听器
	private boolean lastPageWasScolledLeft;// 在ViewPager最后一页是否向左滑动
	private boolean isScolling;// 是否正在滑动
}
