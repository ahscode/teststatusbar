package ahscode.statusbaronscreen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {
//	public static final String sTAG = "MainActivity";
	protected final String mTAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onCreate";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_container);
		FragmentManager fm =getFragmentManager();
		Fragment fragment = fm.findFragmentByTag(IndexFragment.sTAG);
		if(fragment == null){
			fragment = IndexFragment.newInstance();
			fm.beginTransaction()
			.replace(R.id.container, fragment, IndexFragment.sTAG)
			.commit();
		}
	}
}
