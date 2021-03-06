package hokage.kaede.gmail.com.BBView.Custom;

import hokage.kaede.gmail.com.BBViewLib.BBData;
import hokage.kaede.gmail.com.BBViewLib.BBDataComparator;
import hokage.kaede.gmail.com.BBViewLib.BBDataManager;
import hokage.kaede.gmail.com.BBViewLib.SpecValues;
import hokage.kaede.gmail.com.BBViewLib.Android.ViewBuilder;
import hokage.kaede.gmail.com.Lib.Android.SettingManager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * 武器の比較ダイアログ管理クラス
 */
public class CmpWeaponTableBuilder {
	private Activity mActivity;
	private boolean mIsKmPerHour;
	
	/**
	 * 初期化を行う。
	 * @param context
	 * @param builder
	 * @param is_km_per_hour
	 */
	public CmpWeaponTableBuilder(Activity activity, boolean is_km_per_hour) {
		this.mActivity = activity;
		this.mIsKmPerHour = is_km_per_hour;
	}

	/**
	 * 武器の比較ビューを生成する
	 * @param from_data 比較元のデータ
	 * @param to_data 比較先のデータ
	 * @return 比較結果を示すビュー
	 */
	public void showDialog(BBData from_data, BBData to_data) {
		TableLayout table = new TableLayout(mActivity);
		ArrayList<TableRow> rows;
		
		rows = createCmpWeaponRows(from_data, to_data);
		
		int size = rows.size();
		for(int i=0; i<size; i++) {
			table.addView(rows.get(i));
		}

		// ダイアログを表示
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setTitle("武器性能比較");
		builder.setView(table);
		builder.setPositiveButton("閉じる", null);

		Dialog dialog = builder.create();
		dialog.setOwnerActivity(mActivity);
		dialog.show();
	}
	
	/**
	 * 武器の比較行のリストを生成する
	 * @param from_data 比較元のデータ
	 * @param to_data 比較先のデータ
	 * @return 比較結果を示すビュー
	 */
	private ArrayList<TableRow> createCmpWeaponRows(BBData from_data, BBData to_data) {
		ArrayList<TableRow> rows = new ArrayList<TableRow>();
		
		String[] cmp_target = BBDataManager.getCmpTarget(from_data);
		int size = cmp_target.length;
		
		rows.add(ViewBuilder.createTableRow(mActivity, SettingManager.getColor(SettingManager.COLOR_YELLOW), "名称", from_data.get("名称"), to_data.get("名称")));
		
		for(int i=0; i<size; i++) {
			String target_key = cmp_target[i];
			String from_str = SpecValues.getShowValue(from_data, target_key, mIsKmPerHour);
			String to_str = SpecValues.getShowValue(to_data, target_key, mIsKmPerHour);
			int[] colors = getColors(from_data, to_data, target_key);
			
			if(!from_str.equals(BBData.STR_VALUE_NOTHING) && !to_str.equals(BBData.STR_VALUE_NOTHING)) {
				rows.add(ViewBuilder.createTableRow(mActivity, colors, target_key, from_str, to_str));
			}
		}
		
		return rows;
	}
	
	private int[] getColors(BBData from_data, BBData to_data, String target_key) {
		int[] ret = new int[3];
		
		BBDataComparator cmp_data = new BBDataComparator(target_key, true, true);
		int cmp = cmp_data.compare(from_data, to_data);
		
		if(cmp > 0) {
			ret[0] = SettingManager.getColor(SettingManager.COLOR_BASE);
			ret[1] = SettingManager.getColor(SettingManager.COLOR_BLUE);
			ret[2] = SettingManager.getColor(SettingManager.COLOR_RED);
		}
		else if(cmp < 0) {
			ret[0] = SettingManager.getColor(SettingManager.COLOR_BASE);
			ret[1] = SettingManager.getColor(SettingManager.COLOR_RED);
			ret[2] = SettingManager.getColor(SettingManager.COLOR_BLUE);
		}
		else {
			ret[0] = SettingManager.getColor(SettingManager.COLOR_BASE);
			ret[1] = SettingManager.getColor(SettingManager.COLOR_BASE);
			ret[2] = SettingManager.getColor(SettingManager.COLOR_BASE);
		}
		
		return ret;
	}
}
