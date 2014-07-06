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

		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ���ö�λģʽ���߾��ȶ�λģʽ���͹��Ķ�λģʽ�ͽ����豸��λģʽ���������������ͣ��Ƿ��GPS�ȵȡ�
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5 * 1000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���

		mLocationClient.setLocOption(option);

		mLocationClient.registerLocationListener(myListener); // ע���������

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

			// ��ǰ��λʱ��
			sb.append("time : ").append(location.getTime());
			// ��ȡ��λ����
			sb.append("\nerror code : ").append(location.getLocType());
			// ��ȡγ������
			sb.append("\nlatitude : ").append(location.getLatitude());
			// ��ȡ��������
			sb.append("\nlontitude : ").append(location.getLongitude());
			// ��ȡ��λ����
			sb.append("\nradius : ").append(location.getRadius());

			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				// ��ȡ�ٶȣ���GPSʱ������Ϣ
				sb.append("\nspeed : ").append(location.getSpeed());
				// GPS���������Ǹ���
				sb.append("\nsatellite : ").append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

			}
			// ��ȡ��ϸ��ַ��Ϣ
			sb.append("\naddr : ").append(location.getAddrStr());
			// System.out.println(sb.toString());

			Log.d("track", location.toString());
			// logMsg(sb.toString());
		}

	}

}
