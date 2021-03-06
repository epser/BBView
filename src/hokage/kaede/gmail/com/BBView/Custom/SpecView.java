package hokage.kaede.gmail.com.BBView.Custom;

import hokage.kaede.gmail.com.BBViewLib.BBData;
import hokage.kaede.gmail.com.BBViewLib.BBDataComparator;
import hokage.kaede.gmail.com.BBViewLib.BBDataManager;
import hokage.kaede.gmail.com.BBViewLib.CustomData;
import hokage.kaede.gmail.com.BBViewLib.CustomDataManager;
import hokage.kaede.gmail.com.BBViewLib.SpecValues;
import hokage.kaede.gmail.com.BBViewLib.Android.BBViewSettingManager;
import hokage.kaede.gmail.com.BBViewLib.Android.ViewBuilder;
import hokage.kaede.gmail.com.Lib.Android.SettingManager;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SpecView extends LinearLayout implements OnClickListener, OnCheckedChangeListener {
	
	private static final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private static final int FP = LinearLayout.LayoutParams.FILL_PARENT;
	
	private static final int TOGGLE_BUTTON_SB_ID     = 1000;
	private static final int TOGGLE_BUTTON_SBR_ID    = 2000;
	private static final int TOGGLE_BUTTON_REQARM_ID = 3000;
	
	private static final int TABLELAYOUT_ID = 4000;
	
	private Context mContext;
	
	public SpecView(Context context) {
		super(context);
		mContext = context;

		// スペック管理クラスのロード
		CustomData custom_data = CustomDataManager.getCustomData();

		this.setOrientation(LinearLayout.VERTICAL);
		this.setGravity(Gravity.LEFT | Gravity.TOP);
		this.setLayoutParams(new LinearLayout.LayoutParams(FP, WC));

		ScrollView data_view = new ScrollView(mContext);
		data_view.addView(createSpecTable(custom_data));
		data_view.setLayoutParams(new LayoutParams(FP, WC, 1));
		
		this.addView(data_view);

		LayoutParams layout_param = new LayoutParams(WC, WC, 1);
		layout_param.setMargins(0, 0, 0, 0);  // 余白を消す
		
		// 画面下部のレイアウト
		LinearLayout bottom_layout = new LinearLayout(context);
		bottom_layout.setOrientation(LinearLayout.HORIZONTAL);
		
		ToggleButton sb_button = new ToggleButton(context);
		sb_button.setText("SB");
		sb_button.setId(TOGGLE_BUTTON_SB_ID);
		sb_button.setLayoutParams(layout_param);
		sb_button.setOnClickListener(this);
		sb_button.setOnCheckedChangeListener(this);
		bottom_layout.addView(sb_button);
		
		ToggleButton sbr_button = new ToggleButton(context);
		sbr_button.setText("SBR");
		sbr_button.setId(TOGGLE_BUTTON_SBR_ID);
		sbr_button.setLayoutParams(layout_param);
		sbr_button.setOnClickListener(this);
		sbr_button.setOnCheckedChangeListener(this);
		bottom_layout.addView(sbr_button);
		
		ToggleButton reqarm_button = new ToggleButton(context);
		reqarm_button.setText("要請兵器");
		reqarm_button.setId(TOGGLE_BUTTON_REQARM_ID);
		reqarm_button.setLayoutParams(layout_param);
		reqarm_button.setOnClickListener(this);
		reqarm_button.setOnCheckedChangeListener(this);
		bottom_layout.addView(reqarm_button);
		
		// 各兵装スペック詳細画面表示ボタン
		Button type_show_button = new Button(context);
		type_show_button.setText("兵装詳細");
		type_show_button.setLayoutParams(layout_param);
		type_show_button.setGravity(Gravity.CENTER);
		type_show_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, SpecBlustActivity.class);
				mContext.startActivity(intent);
			}
			
		});
		bottom_layout.addView(type_show_button);
		
		this.addView(bottom_layout);
	}
	
	/**
	 * 性能テーブルを生成する。
	 * @return 生成したビュー
	 */
	private View createSpecTable(CustomData custom_data) {
		int color = Color.rgb(255, 255, 255);
		int bg_color = Color.rgb(60, 60, 180);
		
		LinearLayout layout_table = new LinearLayout(mContext);
		layout_table.setOrientation(LinearLayout.VERTICAL);
		layout_table.setLayoutParams(new LinearLayout.LayoutParams(FP, WC));
		layout_table.setId(TABLELAYOUT_ID);

		// 兵装スペックを画面に表示する
		TextView blust_spec_view = ViewBuilder.createTextView(mContext, "兵装スペック", SettingManager.FLAG_TEXTSIZE_SMALL, color, bg_color);
		layout_table.addView(blust_spec_view);
		layout_table.addView(createBlustSpeedViews(custom_data));

		// 総合スペックを画面に表示する
		TextView common_spec_view = ViewBuilder.createTextView(mContext, "総合スペック", SettingManager.FLAG_TEXTSIZE_SMALL, color, bg_color);
		layout_table.addView(common_spec_view);
		layout_table.addView(createBlustSpecView(custom_data));
		
		// パーツスペックを画面に表示する
		TextView parts_spec_view = ViewBuilder.createTextView(mContext, "パーツスペック", SettingManager.FLAG_TEXTSIZE_SMALL, color, bg_color);
		layout_table.addView(parts_spec_view);
		if(BBViewSettingManager.IS_SHOW_REALSPEC) {
			layout_table.addView(createCustomBlustPartsViews(custom_data));
		}
		else {
			layout_table.addView(createCustomBlustPartsNormalViews(custom_data));
		}
		
		// リロードスペックを画面に表示する
		TextView weapon_spec_view = ViewBuilder.createTextView(mContext, "武器スペック", SettingManager.FLAG_TEXTSIZE_SMALL, color, bg_color);
		layout_table.addView(weapon_spec_view);
		layout_table.addView(createWeaponRows(custom_data));
		
		// チップ一覧を画面に表示する
		TextView chip_spec_view = ViewBuilder.createTextView(mContext, "現在装着中のチップ", SettingManager.FLAG_TEXTSIZE_SMALL, color, bg_color);
		layout_table.addView(chip_spec_view);
		layout_table.addView(createChipTable(custom_data));
		
		return layout_table;
	}
	
	/**
	 * パーツスペックテーブルを生成する。
	 * @param data_list データ一覧
	 * @return パーツスペックのテーブル
	 */
	private TableLayout createCustomBlustPartsViews(CustomData custom_data) {
		TableLayout table = new TableLayout(mContext);
		table.setLayoutParams(new TableLayout.LayoutParams(FP, WC));
		
		table.addView(createCustomPartsRows(custom_data, "射撃補正"));
		table.addView(createCustomPartsRows(custom_data, "索敵"));
		table.addView(createCustomPartsRows(custom_data, "ロックオン"));
		table.addView(createCustomPartsRows(custom_data, "DEF回復"));
		table.addView(createCustomPartsRows(custom_data, "ブースター"));
		table.addView(createCustomPartsRows(custom_data, "SP供給率"));
		table.addView(createCustomPartsRows(custom_data, "エリア移動"));
		table.addView(createCustomPartsRows(custom_data, "DEF耐久"));
		table.addView(createCustomPartsRows(custom_data, "反動吸収"));
		table.addView(createCustomPartsRows(custom_data, "リロード"));
		table.addView(createCustomPartsRows(custom_data, "武器変更"));
		table.addView(createCustomPartsRows(custom_data, "予備弾数"));
		table.addView(createCustomPartsRows(custom_data, "歩行"));
		table.addView(createCustomPartsRows(custom_data, "ダッシュ"));
		table.addView(createCustomPartsRows(custom_data, "重量耐性"));
		table.addView(createCustomPartsRows(custom_data, "加速"));		
		
		return table;
	}
	
	/**
	 * パーツスペックテーブルの行を生成する。
	 * @param data アセンデータ
	 * @param parts_type パーツの種類
	 * @return 指定のパーツ種類に対応する行
	 */
	private TableRow createCustomPartsRows(CustomData custom_data, String target_key) {
		double target_value = custom_data.getSpecValue(target_key);
		String point = SpecValues.getPoint(target_key, target_value, BBViewSettingManager.IS_KB_PER_HOUR);
		String data_str = SpecValues.getSpecUnit(target_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);

		if(BBDataComparator.isPointKey(target_key)) {
			data_str = point + " (" + data_str + ")"; 
		}
		
		// DEF回復の場合、隣に回復時間を併記する
		if(target_key.equals("DEF回復")) {
			data_str = String.format("%s (%s)", data_str,
					SpecValues.getSpecUnit(custom_data.getDefRecoverTime(), "DEF回復時間", BBViewSettingManager.IS_KB_PER_HOUR));
		}

		// 兵装強化チップを反映
		if(custom_data.existChip("強襲兵装強化") || custom_data.existChip("強襲兵装強化II")) {
			if(target_key.equals("ブースター")) {
				double blust_value = custom_data.getBoost("強襲兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (強襲兵装時：" + blust_point + " (" + blust_str + "))";
			}
			else if(target_key.equals("ダッシュ")) {
				double blust_value = custom_data.getStartDush("強襲兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (強襲兵装時：" + blust_point + " (" + blust_str + "))";
			}
		}
		if(custom_data.existChip("重火力兵装強化") || custom_data.existChip("重火力兵装強化II")) {
			if(target_key.equals("重量耐性")) {
				double blust_value = custom_data.getAntiWeight("重火力兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (重火力兵装時：" + blust_point + " (" + blust_str + "))";
			}
		}
		if(custom_data.existChip("狙撃兵装強化") || custom_data.existChip("狙撃兵装強化II")) {
			if(target_key.equals("射撃補正")) {
				double blust_value = custom_data.getShotBonus("狙撃兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (狙撃兵装時：" + blust_point + " (" + blust_str + "))";
			}
			else if(target_key.equals("リロード")) {
				double blust_value = custom_data.getReload("狙撃兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (狙撃兵装時：" + blust_point + " (" + blust_str + "))";
			}
		}
		if(custom_data.existChip("支援兵装強化") || custom_data.existChip("支援兵装強化II")) {
			if(target_key.equals("SP供給率")) {
				double blust_value = custom_data.getSP("支援兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (支援兵装時：" + blust_point + " (" + blust_str + "))";
			}
			else if(target_key.equals("エリア移動")) {
				double blust_value = custom_data.getAreaMove("支援兵装");
				String blust_point = SpecValues.getPoint(target_key, blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
				String blust_str = SpecValues.getSpecUnit(blust_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
				data_str = data_str + " (支援兵装時：" + blust_point + " (" + blust_str + "))";
			}
		}

		return ViewBuilder.createTableRow(mContext, SettingManager.getColor(SettingManager.COLOR_BASE), target_key, data_str);
	}

	/**
	 * パーツスペックテーブルを生成する。
	 * @param data_list データ一覧
	 * @return パーツスペックのテーブル
	 */
	private TableLayout createCustomBlustPartsNormalViews(CustomData custom_data) {
		TableLayout table = new TableLayout(mContext);
		table.setLayoutParams(new TableLayout.LayoutParams(FP, WC));
		ArrayList<TableRow> rows;

		int data_len = BBDataManager.BLUST_PARTS_LIST.length;
		
		for(int i=0; i<data_len; i++) {
			rows = createCustomPartsNormalRows(custom_data, BBDataManager.BLUST_PARTS_LIST[i]);
			
			int size = rows.size();
			for(int j=0; j<size; j++) {
				table.addView(rows.get(j));
			}
		}
		
		return table;
	}
	
	/**
	 * パーツスペックテーブルの行を生成する。
	 * @param data アセンデータ
	 * @param parts_type パーツの種類
	 * @return 指定のパーツ種類に対応する行
	 */
	private ArrayList<TableRow> createCustomPartsNormalRows(CustomData custom_data, String parts_type) {
		ArrayList<TableRow> rows = new ArrayList<TableRow>();
		
		BBData parts_data = custom_data.getParts(parts_type);
		String[] cmp_target = getCmpTarget(parts_data);
		int size = cmp_target.length;
		
		for(int i=0; i<size; i++) {
			String target_key = cmp_target[i];
			String point = parts_data.get(target_key);
			// double target_value = custom_data.getSpecValue(target_key);
			// String data_str = SpecValues.getSpecUnit(target_value, target_key, BBViewSettingManager.IS_KB_PER_HOUR);
			String data_str = SpecValues.getSpecUnit(point, target_key, BBViewSettingManager.IS_KB_PER_HOUR);

			if(BBDataComparator.isPointKey(target_key)) {
				data_str = point + " (" + data_str + ")"; 
			}
			
			rows.add(ViewBuilder.createTableRow(mContext, Color.WHITE, target_key, data_str));
		}

		return rows;
	}

	private static final String[] CMP_HEAD_TARGET = {
		"射撃補正", "索敵", "ロックオン"
	};
	
	private static final String[] CMP_BODY_TARGET = {
		"ブースター", "SP供給率", "エリア移動"
	};
	
	private static final String[] CMP_ARMS_TARGET = {
		"反動吸収", "リロード", "武器変更"
	};
	
	private static final String[] CMP_LEGS_TARGET = {
		"歩行", "ダッシュ", "重量耐性"
	};

	/**
	 * パーツまたは武器の種類に応じた比較用のリストを取得する。
	 * @param item パーツまたは武器データ
	 * @return 比較用のリスト。パーツでない場合は武器用のリストを返す。
	 */
	public static String[] getCmpTarget(BBData item) {
		String[] ret = {};
		
		if(item.existCategory("頭部パーツ")) {
			ret = CMP_HEAD_TARGET;
		}
		else if(item.existCategory("胴部パーツ")) {
			ret = CMP_BODY_TARGET;
		}
		else if(item.existCategory("腕部パーツ")) {
			ret = CMP_ARMS_TARGET;
		}
		else if(item.existCategory("脚部パーツ")) {
			ret = CMP_LEGS_TARGET;
		}
		
		return ret;
	}
	
	/**
	 * 総合スペックテーブルを生成する。(セットボーナス、チップ容量、装甲平均値、総重量(猶予))
	 * @param custom_data 表示するカスタムデータ
	 * @return 総合スペックテーブル
	 */
	public TableLayout createBlustSpecView(CustomData custom_data) {
		TableLayout table = new TableLayout(mContext);
		table.setLayoutParams(new TableLayout.LayoutParams(FP, WC));
		
		double armor_value = custom_data.getArmorAve();
		String armor_point = SpecValues.getPoint("装甲", armor_value, BBViewSettingManager.IS_KB_PER_HOUR);
		String armor_str = SpecValues.getSpecUnit(armor_value, "装甲平均値", BBViewSettingManager.IS_KB_PER_HOUR);
		armor_str = armor_point + " (" + armor_str + ")"; 
		
		// 兵装強化チップを反映
		if(custom_data.existChip("重火力兵装強化") || custom_data.existChip("重火力兵装強化II")) {
			double blust_value = custom_data.getArmorAve("重火力兵装");
			String blust_point = SpecValues.getPoint("装甲", blust_value, BBViewSettingManager.IS_KB_PER_HOUR);
			String blust_str = SpecValues.getSpecUnit(blust_value, "装甲平均値", BBViewSettingManager.IS_KB_PER_HOUR);
			armor_str = armor_str + " (重火力兵装時：" + blust_point + " (" + blust_str + "))";
		}

		String[][] speclist = {
			{ "セットボーナス", custom_data.getSetBonus() },
			{ "チップ容量", String.format("%.1f", custom_data.getChipCapacity()) },
			{ "装甲平均値", armor_str },
			{ "総重量(猶予)", String.format("%d (%d)", custom_data.getPartsWeight(), custom_data.getSpacePartsWeight()) },
		};
		
		int size = speclist.length;
		for(int i=0; i<size; i++) {
			table.addView(ViewBuilder.createTableRow(mContext, SettingManager.getColor(SettingManager.COLOR_BASE), speclist[i][0], speclist[i][1]));
		}
		
		return table;
	}

	private static final String[] TOTAL_SPEC_LIST = {
		"兵装", "重量(猶予)", "初速(巡航)", "歩速", "低下率"
	};
	
	/**
	 * 兵装スペックテーブルを生成する。(全兵装におけるアセンの重量と速度を表示)
	 * @param data データ
	 * @return 兵装スペックテーブル
	 */
	private TableLayout createBlustSpeedViews(CustomData data) {
		TableLayout table = new TableLayout(mContext);
		table.setLayoutParams(new TableLayout.LayoutParams(FP, WC));

		String[] blust_list = BBDataManager.BLUST_TYPE_LIST;
		int blust_list_len = blust_list.length;

		// タイトル行を生成
		table.addView(ViewBuilder.createTableRow(mContext, SettingManager.getColor(SettingManager.COLOR_YELLOW), TOTAL_SPEC_LIST));
		
		for(int blust_idx=0; blust_idx<blust_list_len; blust_idx++) {
			String blust_name = blust_list[blust_idx];
			table.addView(createBlustSpeedRow(data, blust_name));
		}
		
		return table;
	}

	/**
	 * 兵装スペックテーブルの行を生成する。(指定の兵装におけるアセンの重量と速度)
	 * @param data データ
	 * @param blust_name 兵装名
	 * @return 生成した行
	 */
	private TableRow createBlustSpeedRow(CustomData data, String blust_name) {
		double rate = data.getSpeedDownRate(blust_name);
		int color = SettingManager.getColor(SettingManager.COLOR_BASE);

		if(rate < 0) {
			color = Color.RED;
		}

		String[] cols = {
				blust_name.substring(0, 2),
				String.format("%d", data.getWeight(blust_name)) + "(" + String.format("%d", data.getSpaceWeight(blust_name)) + ")",
				String.format("%.2f", data.getStartDush(blust_name)) + "(" + String.format("%.2f", data.getNormalDush(blust_name)) + ")",
				SpecValues.getSpecUnit(data.getWalk(blust_name), "歩速", BBViewSettingManager.IS_KB_PER_HOUR),
				SpecValues.getSpecUnit(rate, "低下率", BBViewSettingManager.IS_KB_PER_HOUR),
			};
		
		return ViewBuilder.createTableRow(mContext, color, cols);
	}
	
	private static final String[] WEAPON_TITLE_ROW = { "武器名", "マガジン火力", "瞬間火力", "戦術火力", "リロード時間", "総弾数" };

	/**
	 * 武器スペックテーブルを生成する。(マガジン火力、瞬間火力、戦術火力、リロード時間)
	 * @param data データ
	 * @return 武器スペックテーブル
	 */
	private TableLayout createWeaponRows(CustomData data) {
		TableLayout table = new TableLayout(mContext);
		table.setLayoutParams(new TableLayout.LayoutParams(FP, WC));

		String[] blust_list = BBDataManager.BLUST_TYPE_LIST;
		int blust_list_len = blust_list.length;
		
		table.addView(ViewBuilder.createTableRow(mContext, SettingManager.getColor(SettingManager.COLOR_YELLOW), WEAPON_TITLE_ROW));
		
		for(int blust_idx=0; blust_idx<blust_list_len; blust_idx++) {
			String blust_type = blust_list[blust_idx];

			int weapon_list_len = BBDataManager.WEAPON_TYPE_LIST.length;
			for(int weapon_idx=0; weapon_idx<weapon_list_len; weapon_idx++) {
				BBData weapon = data.getWeapon(blust_type, BBDataManager.WEAPON_TYPE_LIST[weapon_idx]);
				
				/* 補助装備、特別装備の場合は表示しない */
				if(weapon.existCategory(BBDataManager.WEAPON_TYPE_SUPPORT) || weapon.existCategory(BBDataManager.WEAPON_TYPE_SPECIAL)) {
					continue;
				}
				
				double magazine_power = data.getMagazinePower(weapon);
				double sec01_power = data.get1SecPower(weapon);
				//double sec10_power = data.get10SecPower(weapon);
				double battle_power = data.getBattlePower(weapon);
				double reload_time = data.getReloadTime(weapon);
				
				/* 総弾数の文字列を生成する */
				double sum_bullet = data.getBulletSum(weapon);
				double magazine_bullet = weapon.getMagazine();
				double over_bullet = sum_bullet % magazine_bullet;
				double magazine_count = Math.floor(sum_bullet / magazine_bullet);
				
				String bullet_str = "";
				if(magazine_bullet == 1) {
					bullet_str = String.format("1x%.0f", sum_bullet);
				}
				else {
					bullet_str = String.format("%.0fx%.0f +%.0f", magazine_bullet, magazine_count, over_bullet);
				}
				
				String[] cols = { 
						weapon.get("名称"),
						String.format("%.0f", magazine_power), 
						String.format("%.0f", sec01_power), 
						String.format("%.0f", battle_power),
						//String.format("%.0f", sec10_power), 
						String.format("%.1f(秒)", reload_time),
						bullet_str
				};
				table.addView(ViewBuilder.createTableRow(mContext, SettingManager.getColor(SettingManager.COLOR_BASE), cols));
			}
		}
		
		return table;
	}
	
	/**
	 * チップテーブルを生成する
	 * @param data データ
	 * @return チップデータのテーブル
	 */
	private LinearLayout createChipTable(CustomData custom_data) {
		LinearLayout layout_chip= new LinearLayout(mContext);
		layout_chip.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
		layout_chip.setOrientation(LinearLayout.VERTICAL);
		layout_chip.setGravity(Gravity.LEFT | Gravity.TOP);

		ArrayList<BBData> chip_list = custom_data.getChips();
		int size = chip_list.size();
		
		for(int i=0; i<size; i++) {
			layout_chip.addView(ViewBuilder.createTextView(mContext, chip_list.get(i).get("名称"), BBViewSettingManager.FLAG_TEXTSIZE_SMALL));
		}
		
		return layout_chip;
	}

	/**
	 * トグルボタン押下時の処理を行う。
	 */
	@Override
	public void onClick(View v) {
		CustomData custom_data = CustomDataManager.getCustomData();
		
		if(v instanceof ToggleButton) {
			int id = v.getId();
			ToggleButton btn = (ToggleButton)v;
			boolean is_checked = !btn.isChecked();
			
			if(is_checked) {
				updateButton(false, false, false);
				custom_data.setHavingExtraItem(CustomData.HAVING_NOTHING);
			}
			else if(id == TOGGLE_BUTTON_SB_ID) {
				updateButton(true, false, false);
				custom_data.setHavingExtraItem(CustomData.HAVING_SB);
			}
			else if(id == TOGGLE_BUTTON_SBR_ID) {
				updateButton(false, true, false);
				custom_data.setHavingExtraItem(CustomData.HAVING_SBR);
			}
			else if(id == TOGGLE_BUTTON_REQARM_ID) {
				updateButton(false, false, true);
				custom_data.setHavingExtraItem(CustomData.HAVING_REQARM);
			}
			
			LinearLayout layout = (LinearLayout)this.findViewById(TABLELAYOUT_ID);
			layout.removeViews(1, 1);
			layout.addView(createBlustSpeedViews(custom_data), 1);
		}
	}
	
	/**
	 * トグルボタンの表示を更新する。
	 * @param sb 
	 * @param sbr
	 * @param reqarm
	 */
	private void updateButton(boolean sb, boolean sbr, boolean reqarm) {
		ToggleButton sb_button = (ToggleButton)this.findViewById(TOGGLE_BUTTON_SB_ID);
		sb_button.setChecked(sb);
		sb_button.setText("SB");
		
		ToggleButton sbr_button = (ToggleButton)this.findViewById(TOGGLE_BUTTON_SBR_ID);
		sbr_button.setChecked(sbr);
		sbr_button.setText("SBR");
		
		ToggleButton reqarm_button = (ToggleButton)this.findViewById(TOGGLE_BUTTON_REQARM_ID);
		reqarm_button.setChecked(reqarm);
		reqarm_button.setText("要請兵器");
	}

	/**
	 * トグルボタンのチェックが変更された場合の処理。既存の処理の実行を防ぐため、本関数では何も処理を行わない。
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// Do Nothing
	}
}
