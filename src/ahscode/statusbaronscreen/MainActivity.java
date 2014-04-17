package ahscode.statusbaronscreen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";
	protected final String mTAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onCreate";
		super.onCreate(savedInstanceState);
		/*extend statusbar*/
		/*this activity style is Theme.Holo.Light.NoActionBar*/
		getWindow()
		.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
				|WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
		);
		setContentView(R.layout.layout_container);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentByTag(CalFragment.TAG);
		if(fragment == null){
			fragment = CalFragment.newInstance();
			fm.beginTransaction()
			.replace(R.id.container, fragment, CalFragment.TAG)
			.commit();
		}
	}
}
