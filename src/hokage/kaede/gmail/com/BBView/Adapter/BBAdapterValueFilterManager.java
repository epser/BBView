package hokage.kaede.gmail.com.BBView.Adapter;

import java.util.ArrayList;

import hokage.kaede.gmail.com.BBViewLib.BBData;
import hokage.kaede.gmail.com.BBViewLib.BBDataFilter;
import hokage.kaede.gmail.com.BBViewLib.BBDataManager;
import hokage.kaede.gmail.com.Lib.Android.PreferenceIO;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BBAdapterValueFilterManager implements OnClickListener {
	private Activity mActivity;
	private BBDataFilter mFilter;
	
	private ArrayList<CheckBox> mCheckBoxs;
	private ArrayList<View> mValueViews;

	private String[] mKeys;    // 表示項目
	private boolean[] mFlags; // フィルタの有効無効切り替え
	private String[] mValues;  // フィルタの値
	private View[] UIs;      // UIのID値

	private String mSaveKey;
	private Dialog mDialog;
	
	private OnClickValueFilterButtonListener mListener;
	private BBData mSelectData;
	
	/**
	 * 初期化を行う。
	 * @param activity
	 * @param filter
	 */
	public BBAdapterValueFilterManager(Activity activity, BBDataFilter filter, ArrayList<String> target_keys) {
		mActivity = activity;
		mFilter = filter;

		int size = target_keys.size();
		mKeys = new String[size];
		mFlags = new boolean[size];
		mValues = new String[size];
		UIs = new View[size];
		
		for(int i=0; i<size; i++) {
			String key = target_keys.get(i);;
			mKeys[i] = key;
			mFlags[i] = false;
			mValues[i] = getInitValue(key);
			UIs[i] = null;
		}
	}
	
	/**
	 * 値の初期値を取得する。
	 * @param key キー
	 * @return 値の初期値。
	 */
	private String getInitValue(String key) {
		if(key.equals("重量")) {
			return "1000";
		}
		else if(key.equals("チップ容量")) {
			return "2.0";
		}
		else {
			return "C";
		}
	}
	
	public void setOnClickValueFilterButtonListener(OnClickValueFilterButtonListener listener) {
		mListener = listener;
	}
	
	/**
	 * 現在選択中のパーツを設定する。
	 * @param data パーツデータ
	 */
	public void setBBData(BBData data) {
		mSelectData = data;
	}
	
	/**
	 * フィルタ項目の選択用ダイアログを表示する。
	 */
	public void showDialog() {
		if(mKeys == null) {
			return;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setTitle("絞込み選択");
		builder.setIcon(android.R.drawable.ic_menu_more);
		builder.setPositiveButton("OK", this);
		builder.setView(createView());
		
		mDialog = builder.create();
		mDialog.setOwnerActivity(mActivity);
		mDialog.show();
	}
	
	/**
	 * ダイアログ画面内のビューを生成する。
	 * @return
	 */
	private ScrollView createView() {
		mCheckBoxs = new ArrayList<CheckBox>();
		mValueViews = new ArrayList<View>();
		
		TableLayout table_layout = new TableLayout(mActivity);
		table_layout.setColumnStretchable(0, true);
		
		ScrollView sv = new ScrollView(mActivity);
		sv.addView(table_layout);

		int size = mKeys.length;
		for(int i=0; i<size; i++) {
			TableRow row = new TableRow(mActivity);
			
			CheckBox box = new CheckBox(mActivity);
			box.setChecked(mFlags[i]);
			box.setText(mKeys[i]);
			
			mCheckBoxs.add(box);
			row.addView(box);
			
			if(mKeys[i].equals("重量") || mKeys[i].equals("チップ容量")) {
				EditText edit_text = new EditText(mActivity);
				//edit_text.setInputType(InputType.TYPE_CLASS_NUMBER);
				edit_text.setText(mValues[i]);
				UIs[i] = edit_text;
				
				mValueViews.add(edit_text);
				row.addView(edit_text);
			}
			else {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, BBDataManager.SPEC_POINT);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				Spinner spinner = new Spinner(mActivity);
				spinner.setAdapter(adapter);
				UIs[i] = spinner;
				
				int point_size = BBDataManager.SPEC_POINT.length;
				for(int j=0; j<point_size; j++) {
					if(BBDataManager.SPEC_POINT[j].equals(mValues[i])) {
						spinner.setSelection(j);
					}
				}
				
				mValueViews.add(spinner);
				row.addView(spinner);
			}
			
			table_layout.addView(row);
		}

		TableRow row = new TableRow(mActivity);
		
		TextView text_select_data = new TextView(mActivity);
		text_select_data.setText("選択中パーツ値");
		
		Button btn_select_data = new Button(mActivity);
		btn_select_data.setText("読込");
		btn_select_data.setOnClickListener(new OnClickSetDataListener());
		
		row.addView(text_select_data);
		row.addView(btn_select_data);
		table_layout.addView(row);
		
		return sv;
	}
	
	/**
	 * 選択中のパーツのデータをフィルタ値に設定する。
	 * @author kaede
	 */
	private class OnClickSetDataListener implements android.view.View.OnClickListener {

		@Override
		@SuppressWarnings("unchecked")  // spinner.getAdapter()の未検査キャスト警告を抑止
		public void onClick(View arg0) {
			
			if(mSelectData == null) {
				return;
			}

			int size = mKeys.length;
			for(int i=0; i<size; i++) {
				if(mKeys[i].equals("重量") || mKeys[i].equals("チップ容量")) {
					EditText edit_text = (EditText)UIs[i];
					edit_text.setText(mSelectData.get(mKeys[i]));

				}
				else {
					Spinner spinner = (Spinner)UIs[i];
					ArrayAdapter<String> adapter = (ArrayAdapter<String>)spinner.getAdapter();

					int count = adapter.getCount();
					for(int j=0; j<count; j++) {
						if(mSelectData.get(mKeys[i]).equals(adapter.getItem(j))) {
							spinner.setSelection(j);
							adapter.notifyDataSetChanged();
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * データ保存のキーを設定する。
	 * @param save_key
	 */
	public void setSaveKey(String save_key) {
		mSaveKey = save_key;
	}
	
	/**
	 * 表示項目設定を保存する。
	 */
	public void updateSetting() {
		String base_key = BBAdapterShownKeysManager.class.getSimpleName() + "/" + mSaveKey;
		
		int size = mKeys.length;
		for(int i=0; i<size; i++) {
			String flag_key = base_key + "/" + mKeys[i] + "_flag";
			String value_key = base_key + "/" + mKeys[i] + "_value";
			
			PreferenceIO.write(mActivity, flag_key, mFlags[i]);
			PreferenceIO.write(mActivity, value_key, mValues[i]);
		}
	}
	
	/**
	 * 表示項目設定をロードする。
	 */
	public void loadSetting() {
		String base_key = BBAdapterShownKeysManager.class.getSimpleName() + "/" + mSaveKey;

		int size = mKeys.length;
		for(int i=0; i<size; i++) {
			String flag_key = base_key + "/" + mKeys[i] + "_flag";
			String value_key = base_key + "/" + mKeys[i] + "_value";
			
			mFlags[i] = PreferenceIO.read(mActivity, flag_key, false);
			mValues[i] = PreferenceIO.readString(mActivity, value_key, getInitValue(mKeys[i]));
		}
		
		updateFilter();
	}
	
	/**
	 * フィルタを取得する。
	 * @return
	 */
	public BBDataFilter getFilter() {
		return mFilter;
	}
	
	/**
	 * ダイアログのOKボタンタップ時の処理を行う。
	 */
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		mFilter.clearKey();
		
		int size = mCheckBoxs.size();
		for(int i=0; i<size; i++) {
			CheckBox buf_check_box = mCheckBoxs.get(i);
			mFlags[i] = buf_check_box.isChecked();
			
			if(!buf_check_box.isChecked()) {
				continue;
			}
			
			String value = "";
			View buf_view = mValueViews.get(i);
			
			if(buf_view instanceof EditText) {
				value = ((EditText)buf_view).getText().toString();
				if(value != "") {
					mValues[i] = value;
				}
			}
			else if(buf_view instanceof Spinner) {
				value = BBDataManager.SPEC_POINT[((Spinner)buf_view).getSelectedItemPosition()];
				mValues[i] = value;
			}
		}

		updateFilter();
		
		if(mListener != null) {
			mListener.onClickValueFilterButton();
		}
		
		arg0.cancel();
	}
	
	/**
	 * フィルタデータを更新する。
	 */
	private void updateFilter() {
		int size = mKeys.length;
		for(int i=0; i<size; i++) {
			if(mFlags[i]) {
				mFilter.setValue(mKeys[i], mValues[i]);
			}
		}
	}
	
	public static interface OnClickValueFilterButtonListener {
		public void onClickValueFilterButton();
	}
}
