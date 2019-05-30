package com.example.quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class TuongTacDatabase {
    Database database;
    SQLiteDatabase sqLiteDatabase;

    public TuongTacDatabase(Context context){
        database = new Database(context);
    }
    public void  open()
    {
        sqLiteDatabase = database.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public long themSV(SinhVien sv){
        ContentValues cv = new ContentValues();
        cv.put(database.COLUMN_NAME,sv.getTen());
        cv.put(database.COLUMN_EMAIL,sv.getEmail());
        cv.put(database.COLUMN_GT,sv.getGioitinh());
        cv.put(database.COLUMN_LOP,sv.getLophoc());
        cv.put(database.COLUMN_SDT,sv.getSodt());

        return sqLiteDatabase.insert(database.TABLE_NAME,null,cv);

    }

    public long tsuaSV(SinhVien sv){
        ContentValues cv = new ContentValues();

        cv.put(database.COLUMN_NAME,sv.getTen());
        cv.put(database.COLUMN_EMAIL,sv.getEmail());
        cv.put(database.COLUMN_GT,sv.getGioitinh());
        cv.put(database.COLUMN_LOP,sv.getLophoc());
        cv.put(database.COLUMN_SDT,sv.getSodt());

        return sqLiteDatabase.update(database.TABLE_NAME,cv,database.COLUMN_ID+" = "+sv.getId(),null);

    }
    public long xoaSV(int id){
            return sqLiteDatabase.delete(database.TABLE_NAME,database.COLUMN_ID+" = "+id,null);
        }
    public ArrayList<SinhVien> getAlldata(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ database.TABLE_NAME+";",null);
        ArrayList<SinhVien> arr = new ArrayList<>();
        while (cursor.moveToNext()){
            SinhVien sv = new SinhVien();
            sv.setId(cursor.getInt(cursor.getColumnIndex(database.COLUMN_ID)));
            sv.setTen(cursor.getString(cursor.getColumnIndex(database.COLUMN_NAME)));
            sv.setEmail(cursor.getString(cursor.getColumnIndex(database.COLUMN_EMAIL)));
            sv.setGioitinh(cursor.getString(cursor.getColumnIndex(database.COLUMN_GT)));
            sv.setLophoc(cursor.getString(cursor.getColumnIndex(database.COLUMN_LOP)));
            sv.setSodt(cursor.getString(cursor.getColumnIndex(database.COLUMN_SDT)));

            arr.add(sv);
        }
        return arr;
    }
}

