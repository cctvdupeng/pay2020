package com.mfypay.pay3.util;//package com.mfypay.pay3.util;
//
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.mfypay.pay3.m.AccModel;
import com.mfypay.pay3.m.OB;

import java.util.ArrayList;


public class DBManager {
    private SQLiteDatabase db;
    private DBHelper helper;

    public DBManager(Context context) {
        try {
            if (helper == null) {
                helper = new DBHelper(context);
            }
            if (db == null) {
                db = helper.getWritableDatabase();
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public ArrayList<OB> FindOrders(String mark) {
        try {
            String sql = "SELECT * FROM payorder WHERE mark='" + mark + "'";
            ArrayList<OB> list = new ArrayList<OB>();
            Cursor c = ExecSQLForCursor(sql);
            while (c.moveToNext()) {
                OB info = new OB();
                info.setMark(c.getString(c.getColumnIndex("mark")));
                info.setResult(c.getString(c.getColumnIndex("result")));

                list.add(info);
            }
            c.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<OB> FindByNo(String no) {
        try {
            String sql = "SELECT * FROM payorder WHERE tradeno='" + no + "'";
            ArrayList<OB> list = new ArrayList<OB>();
            Cursor c = ExecSQLForCursor(sql);
            while (c.moveToNext()) {
                OB info = new OB();
                info.setMark(c.getString(c.getColumnIndex("mark")));
                info.setResult(c.getString(c.getColumnIndex("result")));

                list.add(info);
            }
            c.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public void addOrder(OB ordereBean) {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务
        try {
            String dt = System.currentTimeMillis() / 1000 + "";
            db.execSQL("INSERT INTO payorder VALUES(null,?,?,?,?,?,?,?)", new Object[]{ordereBean.getMoney(), ordereBean.getMark(), ordereBean.getType(), ordereBean.getNo(), dt, ordereBean.getResult(), ordereBean.getTime()});
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }


    public void addAccount(AccModel accModel) {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务acc_id varchar,  type varchar, socket_id varchar,   time integer
        try {
            String dt = System.currentTimeMillis() / 1000 + "";
            db.execSQL("INSERT INTO payaccount VALUES(null,?,?,?,?)", new Object[]{
                    accModel.getAcc_id(),
                    accModel.getType(),
                    accModel.getSocket_id(),
                    accModel.getTime(),

            });
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }


    public void updateAccount(AccModel accModel) {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务acc_id varchar,  type varchar, socket_id varchar,   time integer
        try {
            String dt = System.currentTimeMillis() / 1000 + "";
            db.execSQL("UPDATE   payaccount set acc_id=?, type=? , socket_id=? ,time=? where _id=?", new Object[]{
                    accModel.getAcc_id(),
                    accModel.getType(),
                    accModel.getSocket_id(),
                    accModel.getTime(),
                    accModel.getId()

            });
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }


    public void delAccount(AccModel accModel) {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务acc_id varchar,  type varchar, socket_id varchar,   time integer
        try {
            String dt = System.currentTimeMillis() / 1000 + "";
            db.execSQL("DELETE from   payaccount   where _id=?", new Object[]{
                    accModel.getId()

            });
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }


    public void delAccount() {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务acc_id varchar,  type varchar, socket_id varchar,   time integer
        try {
            String dt = System.currentTimeMillis() / 1000 + "";
            db.execSQL("DELETE from   payaccount ", new Object[]{


            });
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }





    public void delAccByAccId(String accId) {
        if (db == null) {
            return;
        }
        db.beginTransaction();// 开始事务acc_id varchar,  type varchar, socket_id varchar,   time integer
        try {
            db.execSQL("DELETE from   payaccount where acc_id =?", new Object[]{
                    accId

            });
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }







//acc_id varchar,  type varchar, socket_id varchar,   time integer

    public ArrayList<AccModel> findAcc() {
        try {
            String sql = "SELECT * FROM payaccount";
            ArrayList<AccModel> list = new ArrayList<AccModel>();
            Cursor c = ExecSQLForCursor(sql);
            while (c.moveToNext()) {
                AccModel info = new AccModel();
                info.setId(c.getInt(c.getColumnIndex("_id")));
                info.setAcc_id(c.getString(c.getColumnIndex("acc_id")));
                info.setType(c.getString(c.getColumnIndex("type")));
                info.setSocket_id(c.getString(c.getColumnIndex("socket_id")));
                info.setTime(c.getLong(c.getColumnIndex("time")));
                list.add(info);
            }
            c.close();
            return list;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 执行SQL，返回一个游标
     *
     * @param sql
     * @return
     */
    private Cursor ExecSQLForCursor(String sql) throws NullPointerException {
        if (db == null) {
            return null;
        }
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
