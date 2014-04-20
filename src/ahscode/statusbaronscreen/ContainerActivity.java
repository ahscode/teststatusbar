package ahscode.statusbaronscreen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;

public class ContainerActivity extends Activity {
	public static final String sTAG = "ContainerActivity";
	protected final String mTAG = "ContainerActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onCreate";
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		int id_style = bundle.getInt("id_style");
		String title = bundle.getString("title");
		int[] optionArray = bundle.getIntArray("option");
		setTheme(id_style);
		setTitle(title);
		Window window = getWindow();
		for(int op:optionArray){
			window.addFlags(op);
		}
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
