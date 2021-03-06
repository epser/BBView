package hokage.kaede.gmail.com.BBView.Custom;


import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapter;
import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapter.CustomAdapterBaseItem;
import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapterItemCategory;
import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapterItemParts;
import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapterItemReqArm;
import hokage.kaede.gmail.com.BBView.Adapter.CustomAdapterItemWeapon;
import hokage.kaede.gmail.com.BBViewLib.BBData;
import hokage.kaede.gmail.com.BBViewLib.BBDataManager;
import hokage.kaede.gmail.com.BBViewLib.CustomData;
import hokage.kaede.gmail.com.BBViewLib.Android.BBViewSettingManager;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;

public class CustomView extends LinearLayout implements android.widget.AdapterView.OnItemClickListener {

	private static final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private static final int FP = LinearLayout.LayoutParams.FILL_PARENT;
	
	private static int sLastPosition = -1;
	private static int sLastListTop = -1;
	
	public CustomView(Context context, CustomData custom_data) {
		super(context);

		this.setOrientation(LinearLayout.VERTICAL);
		this.setLayoutParams(new LinearLayout.LayoutParams(FP, WC, 1));
		
		if(BBViewSettingManager.IS_SHOW_COLUMN2) {
			createViewColTwo(context, custom_data);
		}
		else {
			createViewColOne(context, custom_data);
		}
	}

	/**
	 * 1列設定時の画面設定を行う。
	 * @param context
	 * @param custom_data
	 */
	private void createViewColOne(Context context, CustomData custom_data) {

		ListView list_view = new ListView(context);
		CustomAdapter adapter = new CustomAdapter(context);
		
		list_view.setAdapter(adapter);
		list_view.setOnItemClickListener(this);

		String base_spec_str = String.format("機体 (装甲：%.1f / チップ容量：%.1f)", 
				custom_data.getArmorAve(),
				custom_data.getChipCapacity()); 
		
		adapter.addItem(new CustomAdapterItemCategory(context, base_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_HEAD));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_BODY));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_ARMS));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_LEGS));
		
		String assult_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_ASSALT, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_ASSALT), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_ASSALT));
		
		adapter.addItem(new CustomAdapterItemCategory(context, assult_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_MAIN));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SUB));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SUPPORT));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SPECIAL));

		String heavy_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_HEAVY, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_HEAVY), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_HEAVY));
		
		adapter.addItem(new CustomAdapterItemCategory(context, heavy_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_MAIN));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SUB));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SUPPORT));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SPECIAL));
	
		String sniper_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_SNIPER, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_SNIPER), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_SNIPER));
		
		adapter.addItem(new CustomAdapterItemCategory(context, sniper_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_MAIN));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SUB));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SUPPORT));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SPECIAL));

		String support_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_SUPPORT, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_SUPPORT), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_SUPPORT));
		
		adapter.addItem(new CustomAdapterItemCategory(context, support_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_MAIN));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SUB));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SUPPORT));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SPECIAL));

		adapter.addItem(new CustomAdapterItemCategory(context, "その他"));
		adapter.addItem(new CustomAdapterItemReqArm(context, custom_data.getReqArm()));

		if(sLastPosition >= 0) {
			list_view.setSelectionFromTop(sLastPosition, sLastListTop);
		}

		this.addView(list_view);
	}

	/**
	 * 2列設定時の画面設定を行う。
	 * @param context
	 * @param custom_data
	 */
	private void createViewColTwo(Context context, CustomData custom_data) {

		ListView list_view = new ListView(context);
		CustomAdapter adapter = new CustomAdapter(context);
		
		list_view.setAdapter(adapter);
		list_view.setOnItemClickListener(this);

		String base_spec_str = String.format("機体 (装甲：%.1f / チップ容量：%.1f)", 
				custom_data.getArmorAve(),
				custom_data.getChipCapacity()); 
		
		adapter.addItem(new CustomAdapterItemCategory(context, base_spec_str));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_HEAD));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_BODY));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_ARMS));
		adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_PARTS_LEGS));

		adapter.addItem(new CustomAdapterItemCategory(context, "その他"));
		adapter.addItem(new CustomAdapterItemReqArm(context, custom_data.getReqArm()));
		
		// 2段組みのリスト
		GridView grid_view = new GridView(context);
		CustomAdapter grid_adapter = new CustomAdapter(context);
		
		grid_view.setNumColumns(2);
		grid_view.setAdapter(grid_adapter);
		grid_view.setLayoutParams(new TableLayout.LayoutParams(FP, WC, 1));
		grid_view.setOnItemClickListener(this);
		
		String assult_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_ASSALT, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_ASSALT), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_ASSALT));

		String heavy_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_HEAVY, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_HEAVY), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_HEAVY));

		String sniper_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_SNIPER, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_SNIPER), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_SNIPER));

		String support_spec_str = String.format("%s (積載：%d / 初速：%.2f)", 
				BBDataManager.BLUST_TYPE_SUPPORT, 
				custom_data.getSpaceWeight(BBDataManager.BLUST_TYPE_SUPPORT), 
				custom_data.getStartDush(BBDataManager.BLUST_TYPE_SUPPORT));
		
		grid_adapter.addItem(new CustomAdapterItemCategory(context, assult_spec_str));
		grid_adapter.addItem(new CustomAdapterItemCategory(context, sniper_spec_str));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_MAIN));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_MAIN));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SUB));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SUB));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SUPPORT));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SUPPORT));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_ASSALT, BBDataManager.WEAPON_TYPE_SPECIAL));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SNIPER, BBDataManager.WEAPON_TYPE_SPECIAL));

		grid_adapter.addItem(new CustomAdapterItemCategory(context, heavy_spec_str));
		grid_adapter.addItem(new CustomAdapterItemCategory(context, support_spec_str));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_MAIN));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_MAIN));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SUB));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SUB));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SUPPORT));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SUPPORT));
		
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_HEAVY, BBDataManager.WEAPON_TYPE_SPECIAL));
		grid_adapter.addItem(createItem(context, custom_data, BBDataManager.BLUST_TYPE_SUPPORT, BBDataManager.WEAPON_TYPE_SPECIAL));

		this.addView(list_view);
		this.addView(grid_view);
	}
	
	private static CustomAdapterBaseItem createItem(Context context, CustomData custom_data, String type) {
		BBData data = custom_data.getParts(type);
		String title = data.get("名称");
		String summary = type;
	
		if(type.equals(BBDataManager.BLUST_PARTS_HEAD)) {
			title = title + " (頭部)";
			summary = String.format("装：%s / 射：%s / 索：%s / ロ：%s / 回：%s", 
					data.get("装甲"),
					data.get("射撃補正"),
					data.get("索敵"),
					data.get("ロックオン"),
					data.get("DEF回復"));
		}
		else if(type.equals(BBDataManager.BLUST_PARTS_BODY)) {
			title = title + " (胴部)";
			summary = String.format("装：%s / ブ：%s / SP：%s / エ：%s / 耐：%s", 
					data.get("装甲"),
					data.get("ブースター"),
					data.get("SP供給率"),
					data.get("エリア移動"),
					data.get("DEF耐久"));
		}
		else if(type.equals(BBDataManager.BLUST_PARTS_ARMS)) {
			title = title + " (腕部)";
			summary = String.format("装：%s / 反：%s / リ：%s / 武：%s / 弾：%s", 
					data.get("装甲"),
					data.get("反動吸収"),
					data.get("リロード"),
					data.get("武器変更"),
					data.get("予備弾数"));
		}
		else if(type.equals(BBDataManager.BLUST_PARTS_LEGS)) {
			title = title + " (脚部)";
			summary = String.format("装：%s / 歩：%s / ダ：%s / 重：%s / 加：%s", 
					data.get("装甲"),
					data.get("歩行"),
					data.get("ダッシュ"),
					data.get("重量耐性"),
					data.get("加速"));
		}
		
		return new CustomAdapterItemParts(context, title, summary, type);
	}

	private static CustomAdapterBaseItem createItem(Context context, CustomData custom_data, String blust_type, String weapon_type) {
		BBData data = custom_data.getWeapon(blust_type, weapon_type);
		return new CustomAdapterItemWeapon(context, data.get("名称"), blust_type, weapon_type);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		sLastPosition = arg0.getFirstVisiblePosition();
		sLastListTop = arg0.getChildAt(0).getTop();
		
		CustomAdapter adapter = (CustomAdapter)arg0.getAdapter();
		CustomAdapterBaseItem base_item = adapter.getItem(position);
		base_item.click();
	}
}
