package dathtph20511.poly.PNLib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dathtph20511.poly.PNLib.database.DBHelper;
import dathtph20511.poly.PNLib.model.ThanhVien;

public class ThanhVienDAO {
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public static  final  String TABLE_NAME_THANH_VIEN = DBHelper.TABLE_NAME_THANH_VIEN;
    public ThanhVienDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public long insertThanhVien (ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.hoTen);
        values.put("namSinh", thanhVien.namSinh);

        return sqLiteDatabase.insert(TABLE_NAME_THANH_VIEN, null, values);
    }

    public int updateThanhVien (ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.hoTen);
        values.put("namSinh", thanhVien.namSinh);
        return sqLiteDatabase.update(TABLE_NAME_THANH_VIEN, values, "maTV=?", new String[]{String.valueOf(thanhVien.maTV)});
    }

    public int deleteThanhVien (String id) {
        return  sqLiteDatabase.delete(TABLE_NAME_THANH_VIEN, "maTV=?", new String[]{id});
    }

    public List<ThanhVien> getAllThanhVien() {
        String sql = "SELECT * FROM " + TABLE_NAME_THANH_VIEN;
        return getData(sql);
    }

    public ThanhVien getIdTV(String id) {
        String sql = "SELECT * FROM " + TABLE_NAME_THANH_VIEN + " WHERE maTV=?";
        List<ThanhVien> listTV = getData(sql, id);
        return listTV.get(0);
    }

    private List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> listTV = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            thanhVien.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            thanhVien.namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
            listTV.add(thanhVien);
        }
        return listTV;
    }
}
