package com.cs.track;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ab.activity.AbActivity;
import com.ab.db.storage.AbSqliteStorage;
import com.ab.db.storage.AbSqliteStorageListener.AbDataInsertListener;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.cs.track.model.Location;

public class MainActivity extends AbActivity {

	// 百度位置客户端及监听器
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	// andbase标题栏
	private AbTitleBar mAbTitleBar = null;
	// andbase 侧边隐藏菜单
	private SlidingMenu menu;
	// 数据库操作类
	public AbSqliteStorage mAbSqliteStorage = null;
	LocationDao mLocationDao;
	
	private MainMenuFragment mMainMenuFragment = null;
	private MainContentFragment mMainContentFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setAbContentView(R.layout.sliding_menu_content);
		// setContentView(R.layout.activity_main);

		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.app_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_menu);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		
		// 主视图的Fragment添加
		mMainContentFragment = new MainContentFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mMainContentFragment).commit();

		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		// menu视图的Fragment添加
		menu.setMenu(R.layout.sliding_menu_menu);
		mMainMenuFragment = new MainMenuFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, mMainMenuFragment).commit();

		// 左侧显示隐藏监听事件
		mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (menu.isMenuShowing()) {
					menu.showContent();
				} else {
					menu.showMenu();
				}
			}
		});
		
		// 标题右侧关于按钮
		mAbTitleBar.clearRightView();
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
			}

		});

		// 初始化AbSqliteStorage
		mAbSqliteStorage = AbSqliteStorage.getInstance(this);
		// 初始化数据库操作实现类
		mLocationDao = new LocationDao(this);

		mLocationClient = new LocationClient(getApplicationContext());
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setCoorType("bd09ll");
		option.setScanSpan(5 * 1000);
		option.setIsNeedAddress(true);
		option.setNeedDeviceDirect(true);
		mLocationClient.setLocOption(option);
		mLocationClient.registerLocationListener(myListener);
		mLocationClient.start();

	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	private class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				Log.d("track", "location null");
				return;
			}

			mAbSqliteStorage.insertData(new Location(location), mLocationDao, new AbDataInsertListener() {
				@Override
				public void onSuccess(long id) {
				}

				@Override
				public void onFailure(int errorCode, String errorMessage) {
					showToast(errorMessage);
				}

			});

			// StringBuilder sb = new StringBuilder();
			//
			// sb.append("time : ").append(location.getTime());
			// sb.append("\nerror code : ").append(location.getLocType());
			// sb.append("\nlatitude : ").append(location.getLatitude());
			// sb.append("\nlontitude : ").append(location.getLongitude());
			// sb.append("\nradius : ").append(location.getRadius());
			//
			// if (location.getLocType() == BDLocation.TypeGpsLocation) {
			// sb.append("\nspeed : ").append(location.getSpeed());
			// sb.append("\nsatellite : ").append(location.getSatelliteNumber());
			// } else if (location.getLocType() ==
			// BDLocation.TypeNetWorkLocation) {
			//
			// }
			// sb.append("\naddr : ").append(location.getAddrStr());
			// Log.d("track", sb.toString());
		}

	}

}
