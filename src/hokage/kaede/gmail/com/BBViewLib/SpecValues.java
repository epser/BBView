package hokage.kaede.gmail.com.BBViewLib;

import hokage.kaede.gmail.com.Lib.Java.KeyValueStore;

import java.math.BigDecimal;

public class SpecValues {

	/**
	 * エラーの場合の値
	 */
	public static final int ERROR_VALUE = -1;
	
	/**
	 * 一致するデータが無かった場合の値 (文字列)
	 */
	public static final String NOTHING_STR = "情報無し";

	/**
	 * 	セットボーナスの文字列一覧
	 */
	public static KeyValueStore SETBONUS;
	
	/**
	 * 装甲のデータ一覧
	 */
	public static KeyValueStore ARMOR;
	
	/**
	 * 射撃補正のデータ一覧
	 */
	public static KeyValueStore SHOTBONUS;
	
	/**
	 * 索敵のデータ一覧
	 */
	public static KeyValueStore SEARCH;
	
	/**
	 * ロックオン距離のデータ一覧
	 */
	public static KeyValueStore ROCKON;
	
	/**
	 * ブースト容量のデータ一覧
	 */
	public static KeyValueStore BOOST;
	
	/**
	 * SP供給率のデータ一覧
	 */
	public static KeyValueStore SP;
	
	/**
	 * エリア移動のデータ一覧
	 */
	public static KeyValueStore AREAMOVE;
	
	/**
	 * 反動吸収のデータ一覧
	 */
	public static KeyValueStore RECOIL;
	
	/**
	 * リロードのデータ一覧
	 */
	public static KeyValueStore RELOAD;
	
	/**
	 * 武器変更のデータ一覧
	 */
	public static KeyValueStore CHANGEWEAPON;
	
	/**
	 * 重量耐性のデータ一覧
	 */
	public static KeyValueStore ANTIWEIGHT;
	
	/**
	 * ダッシュ速度(初速)のデータ一覧
	 */
	public static KeyValueStore DASH;
	
	/**
	 * 歩行のデータ一覧
	 */
	public static KeyValueStore WALK;
	
	/**
	 * ブラストの最大HP
	 */
	public static final int BLUST_LIFE_MAX = 10000;
	
	/**
	 * 走行速度下限値
	 */
	public static final double MIN_DASH = 10.5;
	
	/**
	 * 歩行速度下限値
	 */
	public static final double MIN_WALK = 3.15;
	
	/**
	 * サテライトバンカーの重量
	 */
	public static final int SB_WEIGHT = 800;

	/**
	 * サテライトバンカーRの重量
	 */
	public static final int SBR_WEIGHT = 1600;
	
	/**
	 * ブラストが大破するダメージ値
	 */
	public static final int BLUST_BREAK_DAMAGE = 10000;
	
	/**
	 * ブラストが転倒するダメージ値
	 */
	public static final int BLUST_DOWN_DAMAGE = 6000;
	
	/**
	 * ブラストがノックバックするダメージ値
	 */
	public static final int BLUST_KB_DAMAGE = 3000;
	
	/**
	 * ブラスト(ホバー)が転倒するダメージ値
	 */
	public static final int HOVER_DOWN_DAMAGE = 5000;
	
	/**
	 * ブラスト(ホバー)がノックバックするダメージ値
	 */
	public static final int HOVER_KB_DAMAGE = 2500;
	

	// 4.5対応
	/**
	 * DEF回復のデータ一覧
	 */
	public static KeyValueStore DEF_RECOVER;
	
	/**
	 * DEF耐久のデータ一覧
	 */
	public static KeyValueStore DEF_GUARD;
	
	/**
	 * 予備弾倉のデータ一覧
	 */
	public static KeyValueStore SPARE_BULLET;
	
	/**
	 * 加速のデータ一覧
	 */
	public static KeyValueStore ACCELERATION;

	/**
	 * 静的初期化子
	 */
	static {
		initSetBonus();
		initArmor();
		initShotBonus();
		initSearch();
		initRockOn();
		initBoost();
		initSP();
		initMoveArea();
		initRecoil();
		initReload();
		initChangeWeapon();
		initAntiWeight();
		initDush();
		initWalk();
		
		// 4.5対応
		initDefRecover();
		initDefGuard();
		initSpareBullet();
		initAcceleration();
	}

	/**
	 * DEF回復のデータの初期化
	 */
	private static void initDefRecover() {
		DEF_RECOVER = new KeyValueStore();
		DEF_RECOVER.set("S",  "137.5"); // 3.5該当パーツなし (2015/10/22)
		DEF_RECOVER.set("S-", "125.0"); // 3.5該当パーツなし (2015/10/31)
		DEF_RECOVER.set("A+", "112.5"); // 3.5該当パーツなし (2015/10/22)
		DEF_RECOVER.set("A",  "100.0");
		DEF_RECOVER.set("A-", "87.5");
		DEF_RECOVER.set("B+", "75.0");
		DEF_RECOVER.set("B",  "62.5");
		DEF_RECOVER.set("B-", "50.0");
		DEF_RECOVER.set("C+", "37.5");
		DEF_RECOVER.set("C",  "25.0");
		DEF_RECOVER.set("C-", "12.5");
		DEF_RECOVER.set("D+", "0.0");
		DEF_RECOVER.set("D",  "-12.5");
		DEF_RECOVER.set("D-", "-25.0");
		DEF_RECOVER.set("E+", "-37.5");  // 3.5該当パーツなし (2015/10/22)
		DEF_RECOVER.set("E",  "-50.0");  // 3.5該当パーツなし (2015/10/22)
	}

	/**
	 * DEF耐久のデータの初期化
	 */
	private static void initDefGuard() {
		DEF_GUARD = new KeyValueStore();
		DEF_GUARD.set("S",  "5000");  // 3.5該当パーツなし (2015/10/22)
		DEF_GUARD.set("S-", "4750");
		DEF_GUARD.set("A+", "4500");  // 3.5該当パーツなし (2015/10/22)
		DEF_GUARD.set("A",  "4250");  // 3.5該当パーツなし (2015/10/22)
		DEF_GUARD.set("A-", "4000");  // 3.5該当パーツなし (2015/10/22)
		DEF_GUARD.set("B+", "3750");
		DEF_GUARD.set("B",  "3500");
		DEF_GUARD.set("B-", "3250");
		DEF_GUARD.set("C+", "3000");
		DEF_GUARD.set("C",  "2750");
		DEF_GUARD.set("C-", "2500");
		DEF_GUARD.set("D+", "2250");
		DEF_GUARD.set("D",  "2000");
		DEF_GUARD.set("D-", "1750");
		DEF_GUARD.set("E+", "1500");
		DEF_GUARD.set("E",  "1250");  // 3.5該当パーツなし (2015/10/22)
	}

	/**
	 * 予備弾数のデータの初期化
	 */
	private static void initSpareBullet() {
		SPARE_BULLET = new KeyValueStore();
		SPARE_BULLET.set("S",  "0.68");  // 3.5該当パーツなし (2015/10/22)
		SPARE_BULLET.set("S-", "0.63");  // 3.5該当パーツなし (2015/10/31)
		SPARE_BULLET.set("A+", "0.59");
		SPARE_BULLET.set("A",  "0.54");
		SPARE_BULLET.set("A-", "0.50");
		SPARE_BULLET.set("B+", "0.45");
		SPARE_BULLET.set("B",  "0.41");
		SPARE_BULLET.set("B-", "0.36");
		SPARE_BULLET.set("C+", "0.32");
		SPARE_BULLET.set("C",  "0.27");
		SPARE_BULLET.set("C-", "0.23");
		SPARE_BULLET.set("D+", "0.18");
		SPARE_BULLET.set("D",  "0.14");
		SPARE_BULLET.set("D-", "0.09");
		SPARE_BULLET.set("E+", "0.05");
		SPARE_BULLET.set("E",  "0.00");
	}

	/**
	 * 加速のデータの初期化
	 */
	private static void initAcceleration() {
		ACCELERATION = new KeyValueStore();
		ACCELERATION.set("S",  "1.01");  // 3.5該当パーツなし (2015/10/22)
		ACCELERATION.set("S-", "1.34");  // 3.5該当パーツなし (2015/10/31)
		ACCELERATION.set("A+", "1.67");  // 3.5該当パーツなし (2015/10/22)
		ACCELERATION.set("A",  "2.01");
		ACCELERATION.set("A-", "2.34");
		ACCELERATION.set("B+", "2.67");
		ACCELERATION.set("B",  "3.00");
		ACCELERATION.set("B-", "3.33");
		ACCELERATION.set("C+", "3.66");
		ACCELERATION.set("C",  "3.99");
		ACCELERATION.set("C-", "4.32");
		ACCELERATION.set("D+", "4.65");
		ACCELERATION.set("D",  "4.98"); 
		ACCELERATION.set("D-", "5.31");
		ACCELERATION.set("E+", "5.64");
		ACCELERATION.set("E",  "5.97");
	}
	
	/**
	 * セットボーナスの説明文を初期化する
	 */
	private static void initSetBonus() {
		SETBONUS = new KeyValueStore();
		SETBONUS.set("クーガー", "重量耐性UP");
		SETBONUS.set("ヘヴィガード", "装甲UP");
		SETBONUS.set("シュライク", "歩行速度UP");
		SETBONUS.set("ツェーブラ", "索敵UP");
		SETBONUS.set("エンフォーサー", "ブースターUP");
		SETBONUS.set("ケーファー", "反動吸収UP");
		SETBONUS.set("E.D.G.", "リロード速度UP");
		SETBONUS.set("ヤクシャ", "ダッシュ速度UP");
		SETBONUS.set("セイバー", "エリア移動UP");
		SETBONUS.set("ディスカス", "SP供給率UP");
		SETBONUS.set("ネレイド", "射撃補正UP");
		SETBONUS.set("迅牙", "ロックオン距離UP");
		SETBONUS.set("ロージー", "装甲値上昇");
		SETBONUS.set("B.U.Z.", "通常移動UP");
		SETBONUS.set("ランドバルク", "重量耐性UP");
		SETBONUS.set("フォーミュラ", "高速移動UP");
		SETBONUS.set("雷花", "ブースターUP");
		SETBONUS.set("ヤーデ", "ダッシュ速度UP");
		SETBONUS.set("アスラ", "武器変更UP");
		SETBONUS.set("ジーシェン", "エリア移動UP");
		SETBONUS.set("月影", "リロードUP");
		SETBONUS.set("スペクター", "重量耐性UP");
		SETBONUS.set("グライフ", "DEF回復UP");
		SETBONUS.set("ザオレン", "SP供給率UP");
	}
	
	/**
	 * 装甲の値を初期化する
	 */
	private static void initArmor() {
		ARMOR = new KeyValueStore();
		ARMOR.set("S",  "137");
		ARMOR.set("S-", "133");
		ARMOR.set("A+", "129");
		ARMOR.set("A",  "122");
		ARMOR.set("A-", "118");
		ARMOR.set("B+", "115");
		ARMOR.set("B",  "110");
		ARMOR.set("B-", "105");
		ARMOR.set("C+", "100");
		ARMOR.set("C",  "95");
		ARMOR.set("C-", "90");
		ARMOR.set("D+", "87");
		ARMOR.set("D",  "81");
		ARMOR.set("D-", "75");
		ARMOR.set("E+", "72");
		ARMOR.set("E",  "68");
		ARMOR.set("E-", "64");
	}
	
	/**
	 * 射撃補正のデータを初期化する
	 */
	private static void initShotBonus() {
		SHOTBONUS = new KeyValueStore();
		SHOTBONUS.set("S",  "1.37");
		SHOTBONUS.set("S-", "1.34");
		SHOTBONUS.set("A+", "1.30");
		SHOTBONUS.set("A",  "1.25");
		SHOTBONUS.set("A-", "1.20");
		SHOTBONUS.set("B+", "1.16");
		SHOTBONUS.set("B",  "1.12");
		SHOTBONUS.set("B-", "1.08");
		SHOTBONUS.set("C+", "1.04");
		SHOTBONUS.set("C",  "1.00");
		SHOTBONUS.set("C-", "0.96");
		SHOTBONUS.set("D+", "0.92");
		SHOTBONUS.set("D",  "0.88");  // 3.5該当パーツなし (2012/10/31)
		SHOTBONUS.set("D-", "0.84");
		SHOTBONUS.set("E+", "0.80");  // 3.5該当パーツなし (2015/10/31)
		SHOTBONUS.set("E",  "0.76");
		SHOTBONUS.set("E-", "0.72");  // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * 索敵のデータ一覧
	 */
	private static void initSearch() {
		SEARCH = new KeyValueStore();
		SEARCH.set("S",  "330");  // 3.5該当パーツなし (2015/10/22)
		SEARCH.set("S-", "315");  // 3.5該当パーツなし (2015/10/31)
		SEARCH.set("A+", "300");
		SEARCH.set("A",  "285");
		SEARCH.set("A-", "270");
		SEARCH.set("B+", "255");
		SEARCH.set("B",  "240");
		SEARCH.set("B-", "225");
		SEARCH.set("C+", "210");
		SEARCH.set("C",  "195");
		SEARCH.set("C-", "180");
		SEARCH.set("D+", "165");
		SEARCH.set("D",  "150");
		SEARCH.set("D-", "135");
		SEARCH.set("E+", "120");
		SEARCH.set("E",  "105");
		SEARCH.set("E-", "90");  // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * ロックオン距離のデータの初期化
	 */
	private static void initRockOn() {
		ROCKON = new KeyValueStore();
		ROCKON.set("S",  "130");   // 3.5該当パーツなし (2015/10/22)
		ROCKON.set("S-", "125");   // 3.5該当パーツなし (2015/10/31)
		ROCKON.set("A+", "120");
		ROCKON.set("A",  "115");  // 2.7該当パーツなし (2012/03/27)
		ROCKON.set("A-", "110");
		ROCKON.set("B+", "100");
		ROCKON.set("B",  "90");
		ROCKON.set("B-", "85");
		ROCKON.set("C+", "80");
		ROCKON.set("C",  "70");
		ROCKON.set("C-", "65");
		ROCKON.set("D+", "60");
		ROCKON.set("D",  "50");
		ROCKON.set("D-", "45");  // 2.7該当パーツなし (2012/03/27)
		ROCKON.set("E+", "40");
		ROCKON.set("E",  "30");
		ROCKON.set("E-", "25");  // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * ブースト容量のデータを初期化
	 */
	private static void initBoost() {
		BOOST = new KeyValueStore();
		BOOST.set("S",  "140"); // 3.5該当パーツなし (2015/10/31)
		BOOST.set("S-", "135");
		BOOST.set("A+", "130");
		BOOST.set("A",  "125");
		BOOST.set("A-", "120");
		BOOST.set("B+", "115");
		BOOST.set("B",  "110");
		BOOST.set("B-", "105");
		BOOST.set("C+", "100");
		BOOST.set("C",  "95");
		BOOST.set("C-", "90");
		BOOST.set("D+", "85");
		BOOST.set("D",  "80");
		BOOST.set("D-", "75");  // 3.5該当パーツなし (2015/10/24)
		BOOST.set("E+", "70");
		BOOST.set("E",  "60");
		BOOST.set("E-", "50");  // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * SP供給率のデータを初期化
	 */
	private static void initSP() {
		SP = new KeyValueStore();
		SP.set("S",  "2.20");  // 3.5該当パーツなし (2015/10/31)
		SP.set("S-", "2.10");  // 3.5該当パーツなし (2015/10/31)
		SP.set("A+", "2.00");
		SP.set("A",  "1.85");  // ザオレン・リアン胴、暫定 (2015/07/04)
		SP.set("A-", "1.70");
		SP.set("B+", "1.60");
		SP.set("B",  "1.50");
		SP.set("B-", "1.40");
		SP.set("C+", "1.35");
		SP.set("C",  "1.20");
		SP.set("C-", "1.10");
		SP.set("D+", "1.00");
		SP.set("D",  "0.90");
		SP.set("D-", "0.80");
		SP.set("E+", "0.65");
		SP.set("E",  "0.50");
		SP.set("E-", "0.45");  // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * エリア移動のデータを初期化
	 */
	private static void initMoveArea() {
		AREAMOVE = new KeyValueStore();
		AREAMOVE.set("S",  "2.5");
		AREAMOVE.set("S-", "2.75");  // 2.7該当パーツなし (2015/10/31)
		AREAMOVE.set("A+", "3.0");
		AREAMOVE.set("A",  "3.25");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("A-", "3.5");
		AREAMOVE.set("B+", "4.0");
		AREAMOVE.set("B",  "4.25");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("B-", "4.5");
		AREAMOVE.set("C+", "5.0");
		AREAMOVE.set("C",  "5.25");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("C-", "5.5");
		AREAMOVE.set("D+", "5.75");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("D",  "6.0");
		AREAMOVE.set("D-", "6.25");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("E+", "6.5");  // 2.7該当パーツなし (2012/03/27)
		AREAMOVE.set("E",  "7.0");
		AREAMOVE.set("E-", "7.5");  // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * 反動吸収のデータ一覧を初期化する
	 */
	private static void initRecoil() {
		RECOIL = new KeyValueStore();
		RECOIL.set("S-", "150");  // 3.5該当パーツなし (2015/10/31)
		RECOIL.set("S-", "145");  // 3.5該当パーツなし (2015/10/31)
		RECOIL.set("A+", "140");
		RECOIL.set("A",  "135");
		RECOIL.set("A-", "130");
		RECOIL.set("B+", "125");
		RECOIL.set("B",  "120");
		RECOIL.set("B-", "115");
		RECOIL.set("C+", "110");
		RECOIL.set("C",  "105");
		RECOIL.set("C-", "100");
		RECOIL.set("D+", "95");
		RECOIL.set("D",  "90");
		RECOIL.set("D-", "85");
		RECOIL.set("E+", "80");
		RECOIL.set("E",  "75");
		RECOIL.set("E-", "70");   // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * リロードのデータを初期化する
	 */
	private static void initReload() {
		RELOAD = new KeyValueStore();
		RELOAD.set("S",  "0.45");   // 3.5該当パーツなし (2015/10/31)
		RELOAD.set("S-", "0.50");
		RELOAD.set("A+", "0.55");
		RELOAD.set("A",  "0.60");
		RELOAD.set("A-", "0.65");
		RELOAD.set("B+", "0.75");
		RELOAD.set("B",  "0.80");
		RELOAD.set("B-", "0.85");
		RELOAD.set("C+", "0.90");
		RELOAD.set("C",  "0.95");
		RELOAD.set("C-", "1.00");
		RELOAD.set("D+", "1.05");
		RELOAD.set("D",  "1.10");
		RELOAD.set("D-", "1.15");
		RELOAD.set("E+", "1.20");
		RELOAD.set("E",  "1.30");
		RELOAD.set("E-", "1.40");   // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * 武器変更のデータを初期化する
	 */
	private static void initChangeWeapon() {
		CHANGEWEAPON = new KeyValueStore();
		CHANGEWEAPON.set("S",  "1.60");
		CHANGEWEAPON.set("S-", "1.55");  // 3.5該当パーツなし (2015/10/31)
		CHANGEWEAPON.set("A+", "1.50");
		CHANGEWEAPON.set("A",  "1.40");
		CHANGEWEAPON.set("A-", "1.35");
		CHANGEWEAPON.set("B+", "1.30");
		CHANGEWEAPON.set("B",  "1.25");
		CHANGEWEAPON.set("B-", "1.20");
		CHANGEWEAPON.set("C+", "1.10");
		CHANGEWEAPON.set("C",  "1.05");
		CHANGEWEAPON.set("C-", "1.00");
		CHANGEWEAPON.set("D+", "0.95");
		CHANGEWEAPON.set("D",  "0.90");
		CHANGEWEAPON.set("D-", "0.80");
		CHANGEWEAPON.set("E+", "0.75");
		CHANGEWEAPON.set("E",  "0.70");
		CHANGEWEAPON.set("E-", "0.65");  // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * 重量耐性のデータを初期化する
	 */
	private static void initAntiWeight() {
		ANTIWEIGHT = new KeyValueStore();
		ANTIWEIGHT.set("S",  "7000"); // 3.5該当パーツなし (2015/10/24)
		ANTIWEIGHT.set("S-", "6850"); // 3.5該当パーツなし (2015/10/31)
		ANTIWEIGHT.set("A+", "6650");
		ANTIWEIGHT.set("A",  "6350");
		ANTIWEIGHT.set("A-", "6150");
		ANTIWEIGHT.set("B+", "5900");
		ANTIWEIGHT.set("B",  "5800");
		ANTIWEIGHT.set("B-", "5600");
		ANTIWEIGHT.set("C+", "5250");
		ANTIWEIGHT.set("C",  "5150");
		ANTIWEIGHT.set("C-", "5000");
		ANTIWEIGHT.set("D+", "4750");
		ANTIWEIGHT.set("D",  "4550");
		ANTIWEIGHT.set("D-", "4250");
		ANTIWEIGHT.set("E+", "4000");
		ANTIWEIGHT.set("E",  "3800");
		ANTIWEIGHT.set("E-", "3600");  // 3.5該当パーツなし (2015/10/31)
	}
	
	/**
	 * ダッシュ(初速)のデータを初期化する (km/h)
	 */
	private static void initDush() {
		DASH = new KeyValueStore();
		DASH.set("S",  "102.60");
		DASH.set("S-", "99.90");
		DASH.set("A+", "97.20");
		DASH.set("A",  "93.96");
		DASH.set("A-", "91.80");
		DASH.set("B+", "88.56");
		DASH.set("B",  "86.40");
		DASH.set("B-", "83.70");
		DASH.set("C+", "81.00");
		DASH.set("C",  "77.76");
		DASH.set("C-", "75.60");
		DASH.set("D+", "72.90");
		DASH.set("D",  "70.20");
		DASH.set("D-", "67.50");
		DASH.set("E+", "65.88");
		DASH.set("E",  "62.64");
		DASH.set("E-", "59.40");  // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * 歩行のデータを初期化する
	 */
	private static void initWalk() {
		WALK = new KeyValueStore();
		WALK.set("S",  "36.450");
		WALK.set("S-", "35.316");  // 3.5該当パーツなし (2015/10/31)
		WALK.set("A+", "34.020");
		WALK.set("A",  "32.724");  // 2.7該当パーツなし (2012/03/27)
		WALK.set("A-", "31.428");
		WALK.set("B+", "30.456");  // 2.7該当パーツなし (2012/03/27)
		WALK.set("B",  "29.160");
		WALK.set("B-", "27.540");
		WALK.set("C+", "25.920");
		WALK.set("C",  "25.110");
		WALK.set("C-", "24.300");
		WALK.set("D+", "22.680");
		WALK.set("D",  "21.060");
		WALK.set("D-", "20.088");
		WALK.set("E+", "18.792");
		WALK.set("E",  "16.848");
		WALK.set("E-", "14.904");  // 3.5該当パーツなし (2015/10/31)
	}

	/**
	 * 性能の値からポイント番号を取得する。
	 * @param key 性能の種類
	 * @param value 性能値
	 * @return ポイント番号(E～S)
	 */
	public static String getPoint(String key, String value, boolean is_km_per_hour) {
		String ret = NOTHING_STR;

		try {
			double buf = Double.valueOf(value);
			ret = getPoint(key, buf, is_km_per_hour);

		} catch(Exception e) {
			ret = NOTHING_STR;
		}
		
		return ret;
	}
	
	/**
	 * 性能の値からポイント番号を取得する。
	 * @param key 性能の種類
	 * @param value 性能値
	 * @return ポイント番号(E～S)
	 */
	public static String getPoint(String key, double value, boolean is_km_per_hour) {
		String point = "";
		
		if(key.equals("装甲")) {
			point = getPointAsc(SpecValues.ARMOR, value);
		}
		else if(key.equals("射撃補正")) {
			point = getPointAsc(SpecValues.SHOTBONUS, value);
		}
		else if(key.equals("索敵")) {
			point = getPointAsc(SpecValues.SEARCH, value);
		}
		else if(key.equals("ロックオン")) {
			point = getPointAsc(SpecValues.ROCKON, value);
		}
		else if(key.equals("ブースター")) {
			point = getPointAsc(SpecValues.BOOST, value);
		}
		else if(key.equals("SP供給率")) {
			point = getPointAsc(SpecValues.SP, value);
		}
		else if(key.equals("エリア移動")) {
			point = getPointDsc(SpecValues.AREAMOVE, value);
		}
		else if(key.equals("反動吸収")) {
			point = getPointAsc(SpecValues.RECOIL, value);
		}
		else if(key.equals("リロード")) {
			point = getPointDsc(SpecValues.RELOAD, value);
		}
		else if(key.equals("武器変更")) {
			point = getPointAsc(SpecValues.CHANGEWEAPON, value);
		}
		else if(key.equals("歩行")) {
			if(!is_km_per_hour) {
				value = value * 3600 / 1000;
			}
			
			point = getPointAsc(SpecValues.WALK, value);
		}
		else if(key.equals("ダッシュ")) {
			if(!is_km_per_hour) {
				value = value * 3600 / 1000;
			}
			
			point = getPointAsc(SpecValues.DASH, value);
		}
		else if(key.equals("重量耐性")) {
			point = getPointAsc(SpecValues.ANTIWEIGHT, value);
		}
		// 4.5対応
		else if(key.equals("DEF回復")) {
			point = getPointAsc(SpecValues.DEF_RECOVER, value);
		}
		else if(key.equals("DEF耐久")) {
			point = getPointAsc(SpecValues.DEF_GUARD, value);
		}
		else if(key.equals("予備弾数")) {
			point = getPointAsc(SpecValues.SPARE_BULLET, value);
		}
		else if(key.equals("加速")) {
			point = getPointDsc(SpecValues.ACCELERATION, value);
		}
		
		return point;
	}
	
	/**
	 * 性能の値からどのポイント番号に相当するか計算する。（昇順）
	 * @param store スペックテーブル
	 * @param value 性能値
	 * @return ポイント番号(E～S)
	 */
	private static String getPointAsc(KeyValueStore store, double value) {
		String point = "情報無し";
		double cmp_value = Double.MAX_VALUE;
		int size = BBDataManager.SPEC_POINT.length;
		
		for(int i=0; i<size; i++) {
			String buf_point = BBDataManager.SPEC_POINT[i];
			
			try {
				cmp_value = Double.valueOf(store.get(buf_point));
			} catch(Exception e) {
				cmp_value = Double.MAX_VALUE;
			}
			
			if(value >= cmp_value) {
				point = buf_point;
				break;
			}
		}
		
		return point;
	}

	/**
	 * 性能の値からどのポイント番号に相当するか計算する。（降順）
	 * @param store スペックテーブル
	 * @param value 性能値
	 * @return ポイント番号(E～S)
	 */
	private static String getPointDsc(KeyValueStore store, double value) {
		String point = "情報無し";
		double cmp_value = Double.MIN_VALUE;
		int size = BBDataManager.SPEC_POINT.length;
		
		for(int i=0; i<size; i++) {
			String buf_point = BBDataManager.SPEC_POINT[i];
			
			try {
				cmp_value = Double.valueOf(store.get(buf_point));
			} catch(Exception e) {
				cmp_value = Double.MIN_VALUE;
			}

			if(value <= cmp_value) {
				point = buf_point;
				break;
			}
		}
		
		return point;
	}
	
	/**
	 * 指定のデータの指定キーの具体値を取得する
	 * @param item 指定データ
	 * @param key 指定キー
	 * @return 具体値の数値データ。ポイントタイプ以外の値を数値に変換して返す。
	 */
	public static double getSpecValue(BBData item, String key, boolean is_km_per_hour) {
		String point = item.get(key);
		return getSpecValue(point, key, is_km_per_hour);
	}

	/**
	 * 指定のデータの指定キーの具体値を取得する
	 * @param point 指定のポイント値
	 * @param key 指定キー
	 * @return 具体値の数値データ。ポイントタイプ以外の値を数値に変換して返す。
	 */
	public static double getSpecValue(String point, String key, boolean is_km_per_hour) {
		String value_str = null;
		double value = 0;
		boolean is_speed = false;
		
		if(key.equals("装甲")) {
			value_str = SpecValues.ARMOR.get(point);
		}
		else if(key.equals("射撃補正")) {
			value_str = SpecValues.SHOTBONUS.get(point);
		}
		else if(key.equals("索敵")) {
			value_str = SpecValues.SEARCH.get(point);
		}
		else if(key.equals("ロックオン")) {
			value_str = SpecValues.ROCKON.get(point);
		}
		else if(key.equals("ブースター")) {
			value_str = SpecValues.BOOST.get(point);
		}
		else if(key.equals("SP供給率")) {
			value_str = SpecValues.SP.get(point);
		}
		else if(key.equals("エリア移動")) {
			value_str = SpecValues.AREAMOVE.get(point);
		}
		else if(key.equals("反動吸収")) {
			value_str = SpecValues.RECOIL.get(point);
		}
		else if(key.equals("リロード")) {
			value_str = SpecValues.RELOAD.get(point);
		}
		else if(key.equals("武器変更")) {
			value_str = SpecValues.CHANGEWEAPON.get(point);
		}
		else if(key.equals("歩行")) {
			value_str = SpecValues.WALK.get(point);
			is_speed = true;
		}
		else if(key.equals("ダッシュ")) {
			value_str = SpecValues.DASH.get(point);
			is_speed = true;
		}
		else if(key.equals("重量耐性")) {
			value_str = SpecValues.ANTIWEIGHT.get(point);
		}
		// 4.5対応
		else if(key.equals("DEF回復")) {
			value_str = SpecValues.DEF_RECOVER.get(point);
		}
		else if(key.equals("DEF耐久")) {
			value_str = SpecValues.DEF_GUARD.get(point);
		}
		else if(key.equals("予備弾数")) {
			value_str = SpecValues.SPARE_BULLET.get(point);
		}
		else if(key.equals("加速")) {
			value_str = SpecValues.ACCELERATION.get(point);
		}
		else {
			value_str = point;
		}
		
		try {
			value = Double.valueOf(value_str);
			
			if(is_speed && !is_km_per_hour) {
				value = value * 1000 / 3600;
			}

		} catch(Exception e) {
			value = ERROR_VALUE;
			// e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * 値を単位つきの文字列で取得する
	 * @param item パーツまたは武器データ
	 * @param key キー
	 * @return スペック値の文字列。スペック値を数値変換できなかった場合はvalueの文字列をそのまま返す。
	 */
	public static String getSpecUnit(BBData item, String key, boolean is_km_per_hour) {
		String value = item.get(key);
		return getSpecUnit(value, key, is_km_per_hour);
	}
	
	/**
	 * 値を単位つきの文字列で取得する
	 * @param value スペック値
	 * @param key キー
	 * @return スペック値の文字列。スペック値を数値変換できなかった場合はvalueの文字列をそのまま返す。
	 */
	public static String getSpecUnit(String value, String key, boolean is_km_per_hour) {
		double value_num = getSpecValue(value, key, is_km_per_hour);
		String ret = value;
		
		if(value_num != ERROR_VALUE) {
			ret = getSpecUnit(value_num, key, is_km_per_hour);
		}
		
		return ret;
	}
	
	/**
	 * 値を単位つきの文字列で取得する
	 * @param value スペック値
	 * @param key キー
	 * @return スペック値の文字列
	 */
	public static String getSpecUnit(double value, String key, boolean is_km_per_hour) {
		String ret = "";
		boolean is_speed = false;
		
		// パーツ合計性能系
		if(key.equals("チップ容量")) {
			ret = String.format("%.1f", value);
		}
		else if(key.equals("装甲平均値")) {
			ret = String.format("%.1f(%%)", value);
		}
		else if(key.equals("総重量")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("猶予")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("初速")) {
			ret = String.format("%.2f", value);
			is_speed = true;
		}
		else if(key.equals("巡航")) {
			ret = String.format("%.2f", value);
			is_speed = true;
		}
		else if(key.equals("歩速")) {
			ret = String.format("%.2f", value);
			is_speed = true;
		}
		else if(key.equals("低下率")) {
			ret = String.format("%.1f(%%)", value);
		}
		// パーツデータ系
		else if(key.equals("装甲")) {
			ret = String.format("%.0f(%%)", value);
		}
		else if(key.equals("重量")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("射撃補正")) {
			ret = String.format("%.2f", value);
		}
		else if(key.equals("索敵")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("ロックオン")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("ブースター")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("SP供給率")) {
			ret = String.format("x%.2f", value);
		}
		else if(key.equals("エリア移動")) {
			ret = String.format("%.2f(秒)", value);
		}
		else if(key.equals("反動吸収")) {
			ret = String.format("%.0f(%%)", value);
		}
		else if(key.equals("リロード")) {
			ret = String.format("x%.2f", value);
		}
		else if(key.equals("武器変更")) {
			ret = String.format("/%.2f", value);
		}
		else if(key.equals("重量耐性")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("ダッシュ")) {
			ret = String.format("%.2f", value);
			is_speed = true;
		}
		else if(key.equals("歩行")) {
			ret = String.format("%.2f", value);
			is_speed = true;
		}
		// 4.5対応
		else if(key.equals("DEF回復時間")) {
			ret = String.format("%.1f (秒)", value);
		}
		else if(key.equals("DEF回復")) {
			ret = String.format("%.1f (%%)", value);
		}
		else if(key.equals("DEF耐久")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("予備弾数")) {
			ret = String.format("%.2f", value);
		}
		else if(key.equals("加速")) {
			ret = String.format("%.2f (秒)", value);
		}
		// 武器データ系
		else if(key.equals("威力")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("連射速度")) {
			ret = String.format("%.0f", value) + "/min";
		}
		else if(key.equals("出力")) {
			ret = String.format("%.1f(倍)", value);
		}
		else if(key.equals("爆発半径")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("弾速(初速)")) {
			ret = String.format("%.0f(m/s)", value);
		}
		else if(key.equals("爆発回数")) {
			ret = String.format("%.0f(回)", value);
		}
		else if(key.equals("効果持続")) {
			ret = String.format("%.1f(秒)", value);
		}
		else if(key.equals("連続使用")) {
			ret = String.format("%.1f(秒)", value);
		}
		else if(key.equals("OH耐性")) {
			ret = String.format("%.2f(秒)", value);
		}
		else if(key.equals("空転停止")) {
			ret = String.format("%.2f(秒)", value);
		}
		else if(key.equals("射程距離")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("着弾誤差半径")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("爆発高度")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("有効距離")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("精密標準")) {
			ret = String.format("%.0f(倍)", value);
		}
		else if(key.equals("飛翔速度")) {
			ret = String.format("%.2f (m/s)", value);
		}
		else if(key.equals("索敵範囲")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("索敵角度")) {
			ret = String.format("%.0f(度)", value);
		}
		else if(key.equals("射角")) {
			ret = String.format("%.0f(度)", value);
		}
		else if(key.equals("精密照準")) {
			ret = String.format("%.1f(倍)", value);
		}
		else if(key.equals("射程")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.equals("修理速度")) {
			ret = String.format("%.0f(/s)", value);
		}
		// 武器データの計算結果系
		else if(key.contains("火力")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("全弾数")) {
			ret = String.format("%.0f", value);
		}
		else if(key.equals("飛翔距離")) {
			ret = String.format("%.0f(m)", value);
		}
		else if(key.contains("時間")) {
			ret = String.format("%.1f(秒)", value);
		}
		else if(key.contains("面積")) {
			ret = String.format("%.0f(m^2)", value);
		}
		else {
			ret = String.format("%.0f", value);
		}
		
		// 速度の場合の単位を設定する
		if(is_speed) {
			if(is_km_per_hour) {
				ret = ret + "(km/h)";
			}
			else {
				ret = ret + "(m/s)";
			}
		}
		
		return ret;
	}
	
	/**
	 * double型をint型に変換する。小数部は破棄する。精度は小数点第一位までとする。
	 * @param target 変換するdouble型の値
	 * @return 変換後のint型の値
	 */
	public static int castInteger(double target) {
		BigDecimal bd = new BigDecimal(target + 0.05);
		
		return bd.intValue();
	}
	
	/**
	 * 一部の値を画面表示用に文字列を変換する
	 * @param data
	 * @param key
	 * @param is_km_per_hour
	 * @return
	 */
	public static String getShowValue(BBData data, String key, boolean is_km_per_hour) {
		String ret = SpecValues.getSpecUnit(data, key, is_km_per_hour);

		// 拡散武器の場合、計算結果を合わせて表示する
		if(key.equals("威力")) {
			if(data.isSpreadWeapon()) {
				ret = ret + " (" + data.getOneShotPower() + ")";
			}
		}
		else if(key.equals("耐久力")) {
			if(data.existCategory("偵察機系統")) {
				ret = "破壊不可";
			}
		}

		return ret;
	}
}
