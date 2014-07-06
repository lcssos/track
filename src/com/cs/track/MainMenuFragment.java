package com.cs.track;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainMenuFragment extends Fragment {

	private MainActivity mActivity = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = (MainActivity) this.getActivity();

		View view = inflater.inflate(R.layout.main_menu, null);

		return view;
	}
}
