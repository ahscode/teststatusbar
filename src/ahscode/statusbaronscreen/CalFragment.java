package ahscode.statusbaronscreen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalFragment extends Fragment {
	public class Param {
		public static final String TAG = "Param";
		protected final String mTAG = "Param";
		String mTitle;
		String mValue;
	}

	public static final String TAG = "CalFragment";
	protected final String mTAG = "CalFragment";
	private Button mButton;
	private RelativeLayout mBody;
	private GridLayout mGridLayout;

	public CalFragment() {
		//system use
	}

	public static Fragment newInstance() {
		return new CalFragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onCreateView";
		View v = inflater.inflate(R.layout.fragment_test, container, false);
		mBody = (RelativeLayout)v.findViewById(R.id.test_body);
		mButton = (Button)v.findViewById(R.id.button2);
		mGridLayout = (GridLayout)v.findViewById(R.id.gridlayout);
		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onActivityCreated";
		super.onActivityCreated(savedInstanceState);
		mButton.setOnClickListener(getClickListener());
	}

	private OnClickListener getClickListener() {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/getClickListener";
		return new OnClickListener() {

			private boolean mEnabled = true;

			@Override
			public void onClick(View v) {
				@SuppressWarnings("unused")
				final String method_tag = mTAG + "/onClick";
				if(mEnabled == false)return;
				mEnabled = false;
				List<Param> list = new ArrayList<Param>();
				StringBuilder sb = new StringBuilder();
				//レイアウトリソースの幅
				Param item = new Param();
				item.mTitle = sb.append("layoutリソースでつっこめる幅：").toString();sb.setLength(0);
				item.mValue = sb.append(mBody.getWidth()).append("px").toString();sb.setLength(0);
				list.add(item);
				//レイアウトリソースの高さ
				item = new Param();
				item.mTitle = sb.append("layoutリソースでつっこめる高さ：").toString();sb.setLength(0);
				item.mValue = sb.append(mBody.getHeight()).append("px") .toString();sb.setLength(0);
				list.add(item);
				//ディスプレイの幅
				item = new Param();
				WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
				Display disp = wm.getDefaultDisplay();
				Point size = new Point();
				disp.getSize(size);
				item.mTitle = sb.append("displayの幅：").toString();sb.setLength(0);
				item.mValue = sb.append(size.x).append("px") .toString();sb.setLength(0);
				list.add(item);
				//ディスプレイの高さ
				item = new Param();
				item.mTitle = sb.append("displayの高さ：").toString();sb.setLength(0);
				item.mValue = sb.append(size.y).append("px") .toString();sb.setLength(0);
				list.add(item);
				//deviceのdensity
				DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
				float density = displaymetrics.density;
				item = new Param();
				item.mTitle = sb.append("deviceのdensity：").toString();sb.setLength(0);
				item.mValue = sb.append(density).toString();sb.setLength(0);
				list.add(item);
				//deviceのdensityDpi
				float densityDpi = displaymetrics.densityDpi;
				item = new Param();
				item.mTitle = sb.append("deviceのdensityDpi：").toString();sb.setLength(0);
				item.mValue = sb.append(densityDpi).toString();sb.setLength(0);
				list.add(item);
				//densityDpiのお名前
				item = new Param();
				item.mTitle = sb.append("densityDpiのお名前：").toString();sb.setLength(0);
				item.mValue = sb.append(getDpiName(densityDpi)).toString();sb.setLength(0);
				list.add(item);
				//ステータスバー？
				int statusbarheight = getStatusBarPxHeightFromResouce();
				item = new Param();
				item.mTitle = sb.append("android.resourcesから得たstatusbarの高さ：").toString();sb.setLength(0);
				item.mValue = sb.append(statusbarheight).append("px") .toString();sb.setLength(0);
				list.add(item);
				//ステータスバーの長さをDPに変換
				float statusbarheight_f = (float)statusbarheight;
				int dp = (int) (statusbarheight_f / density + 0.5f);
				item = new Param();
				item.mTitle = sb.append("ステータスバーの長さをDPに変換：").toString();sb.setLength(0);
				item.mValue = sb.append(dp).append("dp") .toString();sb.setLength(0);
				list.add(item);
				//Device全体の幅と高さ
				getsize();
				//グリッドレイアウトにつっこむ
				mGridLayout.setColumnCount(2);
				mGridLayout.setRowCount(list.size());
				mGridLayout.setOrientation(0);
				TextView tx = null;
				Context context = getActivity();
				float textsize = 14f;
				int code_as_unit = TypedValue.COMPLEX_UNIT_DIP;
				int text_color = Color.BLACK;
				for(int i = 0;i < list.size();i++){
					tx = new TextView(context);
					tx.setText(list.get(i).mTitle);
					tx.setTextSize(code_as_unit, textsize);
					tx.setTextColor(text_color);
					mGridLayout.addView(tx);
					tx = new TextView(context);
					tx.setText(list.get(i).mValue);
					tx.setTextSize(code_as_unit, textsize);
					tx.setTextColor(text_color);
					mGridLayout.addView(tx);
				}
			}

			@SuppressLint("NewApi")
			private void getsize() {
				@SuppressWarnings("unused")
				final String method_tag = mTAG+"/getsize";
				int width = 0, height = 0;
				final DisplayMetrics metrics = new DisplayMetrics();
				Display display = getActivity().getWindowManager().getDefaultDisplay();
				Method mGetRawH = null, mGetRawW = null;

				try {
					// For JellyBean 4.2 (API 17) and onward
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
						display.getRealMetrics(metrics);

						width = metrics.widthPixels;
						height = metrics.heightPixels;
					} else {
						mGetRawH = Display.class.getMethod("getRawHeight");
						mGetRawW = Display.class.getMethod("getRawWidth");

						try {
							width = (Integer) mGetRawW.invoke(display);
							height = (Integer) mGetRawH.invoke(display);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				} catch (NoSuchMethodException e3) {  
					e3.printStackTrace();
				}
				Log.d(method_tag, "width/height?"+width+"/"+height);
			}

			private String getDpiName(float densityDpi) {
				@SuppressWarnings("unused")
				final String method_tag = mTAG+"/getDpiName";
				if(densityDpi <=DisplayMetrics.DENSITY_LOW){
					return "ldpi";
				}else if(densityDpi <=DisplayMetrics.DENSITY_MEDIUM){
					return "mdpi";
				}else if(densityDpi <=DisplayMetrics.DENSITY_TV){
					return "tvdpi";
				}else if(densityDpi <=DisplayMetrics.DENSITY_HIGH){
					return "hdpi";
				}else if(densityDpi <=DisplayMetrics.DENSITY_XHIGH){
					return "xhdpi";
				}else if(densityDpi <=480){
					return "xxhdpi";
				}else if(densityDpi <=640){
					return "xxxhdpi";
				}
				return "no much";
			}
		};
	}
	public int getStatusBarPxHeightFromResouce() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
