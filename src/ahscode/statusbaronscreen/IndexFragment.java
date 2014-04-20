package ahscode.statusbaronscreen;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexFragment extends ListFragment {

	public static final String sTAG = "IndexFragment";
	protected final String mTAG = "IndexFragment";
	private List<IndexParam> mList;

	public IndexFragment() {
		//system use
	}

	public static Fragment newInstance() {
		return new IndexFragment();
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onActivityCreated";
		super.onActivityCreated(savedInstanceState);
		mList = createIndexList();
		List<String> list = createTitles(mList);
		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list));
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/onListItemClick";
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(getActivity().getApplicationContext(),ContainerActivity.class);
		IndexParam item = mList.get(position);
		intent.putExtra("id_style", item.id_style);
		intent.putExtra("title", item.name_style);
		intent.putExtra("option", item.window_option);
		startActivity(intent);
	}

	private List<String> createTitles(List<IndexParam> paramlist) {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/createTitles";
		List<String> list = new ArrayList<String>();
		for(int i = 0;i< paramlist.size();i++){
			list.add(paramlist.get(i).name_style);
		}
		return list;
	}
	private List<IndexParam> createIndexList() {
		@SuppressWarnings("unused")
		final String method_tag = mTAG+"/createIndexList";
		List<IndexParam> list = new ArrayList<IndexFragment.IndexParam>();
		IndexParam item = null;
		item = new IndexParam(android.R.style.Theme_Holo_Light_DarkActionBar,new int[]{});
		list.add(item);
		item = new IndexParam(android.R.style.Theme_Holo_Light_NoActionBar,new int[]{});
		list.add(item);
		item = new IndexParam(android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen, new int[]{});
		list.add(item);
		item = new IndexParam();
		item.id_style = android.R.style.Theme_Holo_Light_NoActionBar;
		item.name_style = "like_transactDecor";
		item.window_option =  new int[]{
				WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
				,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS};
		list.add(item);
		return list;
	}
	
	public class IndexParam {
		public static final String sTAG = "IndexParam";
		protected final String mTAG = "IndexParam";
		int id_style;
		String name_style;
		int[] window_option;
		public IndexParam() {
			//system use
		}
		public IndexParam(int id_style, int[] option) {
			//system use
			this.id_style = id_style;
			this.name_style = getResources().getResourceName(id_style);
			this.window_option = option;
		}
		
	}
}
