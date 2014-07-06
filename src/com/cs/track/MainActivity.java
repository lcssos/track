package com.cs.track;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class MainActivity extends Activity {

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式（高精度定位模式，低功耗定位模式和仅用设备定位模式），返回坐标类型，是否打开GPS等等。
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5 * 1000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向

		mLocationClient.setLocOption(option);

		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		mLocationClient.start();

	}

	public void start(View view) {
		if (!mLocationClient.isStarted()) {
			mLocationClient.start();
		}
	}

	public void stop(View view) {
		if (mLocationClient.isStarted()) {
			mLocationClient.stop();
		}
	}

	private class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				Log.d("track", "location null");
				return;
			}

			StringBuilder sb = new StringBuilder();

			// 当前定位时间
			sb.append("time : ").append(location.getTime());
			// 获取定位类型
			sb.append("\nerror code : ").append(location.getLocType());
			// 获取纬度坐标
			sb.append("\nlatitude : ").append(location.getLatitude());
			// 获取经度坐标
			sb.append("\nlontitude : ").append(location.getLongitude());
			// 获取定位精度
			sb.append("\nradius : ").append(location.getRadius());

			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				// 获取速度，仅GPS时存在信息
				sb.append("\nspeed : ").append(location.getSpeed());
				// GPS锁定的卫星个数
				sb.append("\nsatellite : ").append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

			}
			// 获取详细地址信息
			sb.append("\naddr : ").append(location.getAddrStr());
			// System.out.println(sb.toString());

			Log.d("track", location.toString());
			// logMsg(sb.toString());
		}

	}

}
