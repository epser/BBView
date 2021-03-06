package hokage.kaede.gmail.com.BBView.Item;

import hokage.kaede.gmail.com.BBView.Adapter.BBArrayAdapter;
import hokage.kaede.gmail.com.BBViewLib.BBData;
import hokage.kaede.gmail.com.BBViewLib.BBDataFilter;
import hokage.kaede.gmail.com.BBViewLib.BBDataManager;
import hokage.kaede.gmail.com.BBViewLib.BBNetDatabase;
import hokage.kaede.gmail.com.BBViewLib.Android.BaseActivity;
import hokage.kaede.gmail.com.BBViewLib.Android.IntentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class BBDataListActivity extends BaseActivity implements OnItemClickListener  {
	private static final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private static final int FP = LinearLayout.LayoutParams.FILL_PARENT;
	
	public static final String INTENTEY_FILTER_MAIN = "INTENTEY_FILTER_MAIN";
	public static final String INTENTEY_FILTER_SUB = "INTENTEY_FILTER_SUB";
	
	/**
	 * 画面生成時の処理を行う。
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		if(intent == null) {
			finish();
		}
		
		String main_filter_str = intent.getStringExtra(INTENTEY_FILTER_MAIN);
		String sub_filter_str = intent.getStringExtra(INTENTEY_FILTER_SUB);

		BBDataManager data_manager = BBDataManager.getInstance();
		data_manager.setSortKey(null);
		
		BBDataFilter filter = new BBDataFilter();

		// タイトル名を決定する
		String filter_name = "";
		
		if(sub_filter_str.equals("")) {
			filter.setType(main_filter_str);
			filter_name = main_filter_str;
		}
		else {
			filter.setType(main_filter_str);
			filter.setType(sub_filter_str);
			filter_name = main_filter_str + "/" + sub_filter_str;
		}

        String card_name = BBNetDatabase.getInstance().getCardName();
        if(card_name.equals(BBNetDatabase.NO_CARD_DATA)) {
            setTitle(getTitle() + " (" + filter_name + ")");
        }
        else {
            setTitle(getTitle() + " (" + filter_name + "/カード名：" + card_name + ")");
        }

		// 全体レイアウト設定
		LinearLayout layout_all = new LinearLayout(this);
		layout_all.setLayoutParams(new LinearLayout.LayoutParams(FP, FP));
        layout_all.setOrientation(LinearLayout.VERTICAL);
		layout_all.setGravity(Gravity.TOP);

		// リスト設定
		ListView list_view = new ListView(this);
		list_view.setLayoutParams(new LinearLayout.LayoutParams(FP, WC, 1));
		list_view.setOnItemClickListener(this);
		layout_all.addView(list_view);
		
		// リストの生成
		BBArrayAdapter adapter = new BBArrayAdapter(this, data_manager.getList(filter));
		adapter.setBaseItem(null);
		list_view.setAdapter(adapter);

		setContentView(layout_all);
	}
	
	/**
	 * リストの項目選択時の処理を行う。
	 */
	@Override
	public void onItemClick(AdapterView<?> adapter_view, View view, int position, long id) {
		BBArrayAdapter adapter = (BBArrayAdapter)(adapter_view.getAdapter());
		BBData data = (BBData)(adapter.getItem(position));
		moveInfoActivity(data);
	}

	/**
	 * 詳細画面へ移動する。
	 * @param to_item 詳細画面で表示するデータ
	 */
	private void moveInfoActivity(BBData to_item) {
		Intent intent = new Intent(this, InfoActivity.class);
		IntentManager.setSelectedData(intent, to_item);
		startActivity(intent);
	}
}
