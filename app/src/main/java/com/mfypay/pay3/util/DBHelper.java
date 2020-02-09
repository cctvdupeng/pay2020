package com.mfypay.pay3.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
        super(context, "trade.db", null, 1);  
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS qrcode" + 
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, money varchar, mark text, type varchar, payurl varchar, dt varchar)");
		db.execSQL("CREATE TABLE IF NOT EXISTS payorder" + 
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, money varchar, mark text, type varchar, tradeno varchar, dt varchar, result varchar, time integer)");
		db.execSQL("CREATE TABLE IF NOT EXISTS payaccount" +
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, acc_id varchar,  type varchar, socket_id varchar,   time integer)");
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}  
}
