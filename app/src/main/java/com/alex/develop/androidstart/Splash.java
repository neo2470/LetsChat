package com.alex.develop.androidstart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alex.develop.adapter.FeatureAdapter;
import com.alex.develop.adapter.FeatureAdapter.OnPageScolledListener;

/**
 * App的启动画面，持续2.5s，可用于<br>
 * 1、展示App品牌LOGO<br>
 * 2、加载程序所需数据<br>
 * 3、介绍软件新特性
 * 4、广告展示
 * 
 * @author Created by alex 2014/11/07
 */
public class Splash extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		initialize();
		
//		initYoumiAd();// Youmi ads
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(isFirstLaunch());
			}
		}, getResources().getInteger(R.integer.splash_duration));
	}
	
	/**
	 * 使用SharedPreferences写入标志位的方式判断App的每次运行是否为安装后第一次运行
	 * 若是，则需要跳转到新特性介绍画面；若不是，则直接进入主界面
	 * @return true，App安装后第一次运行；false，不是第一次运行
	 */
	private boolean isFirstLaunch() {
		
		String preferFiles = getPackageName() + getString(R.string.app_info_file);
		SharedPreferences prefer = getSharedPreferences(preferFiles, Context.MODE_PRIVATE);
		
		boolean firstLaunch = prefer.getBoolean(getString(R.string.key_is_first_launch), true);
		
		if(firstLaunch) {
			SharedPreferences.Editor editor = prefer.edit();
			editor.putBoolean(getString(R.string.key_is_first_launch), false);
			editor.commit();
		}
		
		return firstLaunch;
	}
	
	private void startActivity(boolean isFirst) {
		if (isFirst) {
			
			/**
			 *  启动新特性介绍
			 */
			final ImageView splash = (ImageView) findViewById(R.id.splash);
			Animation splashAnim = AnimationUtils.loadAnimation(Splash.this, R.anim.out_from_left);
			splashAnim.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					Animation featureAnim = AnimationUtils.loadAnimation(Splash.this, R.anim.in_from_right);
					feature.startAnimation(featureAnim);
					feature.setVisibility(View.VISIBLE);
				}

				@Override
				public void onAnimationRepeat(Animation animation) {}

				@Override
				public void onAnimationEnd(Animation animation) {
					splash.setVisibility(View.GONE);
				}
			});
			
			splash.startAnimation(splashAnim);
			
		} else {
			
			// 程序主界面
			intent = new Intent(Splash.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@SuppressLint("InflateParams")
	private void initialize() {
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new View[]{
				inflater.inflate(R.layout.feature_1, null),
				inflater.inflate(R.layout.feature_2, null),
				inflater.inflate(R.layout.feature_3, null)
		};
		
		FeatureAdapter featureAdapter = new FeatureAdapter(views);
		featureAdapter.setPageScolledListener(new OnPageScolledListener() {
			
			@Override
			public void onLastPageScolled2Left() {
				
				// 进入程序主界面
				startActivity(false);
			}
		});
		
		feature = (ViewPager) findViewById(R.id.feature);
		feature.setAdapter(featureAdapter);
		feature.setOnPageChangeListener(featureAdapter);
	}

	private Intent intent;// 启动Activity
	private View[] views;// 存储ViewPager中的页面
	private ViewPager feature;// 新特性介绍
}
