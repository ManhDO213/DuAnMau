package dathtph20511.poly.PNLib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dathtph20511.poly.PNLib.database.DBHelper;
import dathtph20511.poly.PNLib.model.LoaiSach;

public class LoaiSachDAO {
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public static  final  String TABLE_NAME_LOAI_SACH = DBHelper.TABLE_NAME_LOAI_SACH;
    public LoaiSachDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public long insertLoaiSach (LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.tenloai);

        return sqLiteDatabase.insert(TABLE_NAME_LOAI_SACH, null, values);
    }

    public long updateLoaiSach (LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.tenloai);

        return sqLiteDatabase.update(TABLE_NAME_LOAI_SACH, values, "maLoai=?", new String[]{String.valueOf(loaiSach.maloai)});
    }

    public int deleteLoaiSach(String id) {
        return sqLiteDatabase.delete(TABLE_NAME_LOAI_SACH, "maLoai=?", new String[]{id});
    }

    public List<LoaiSach> getAllLoaiSach() {
        String sql = "SELECT * FROM " + TABLE_NAME_LOAI_SACH;
        return getData(sql);
    }

    public LoaiSach getIdLoaiSach (String id) {
        String sql = "SELECT * FROM " + TABLE_NAME_LOAI_SACH + " WHERE maLoai=?";
        List<LoaiSach> listLoaiSach = getData(sql, id);
        return listLoaiSach.get(0);
    }


    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> listLoaiSach = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.maloai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            loaiSach.tenloai = cursor.getString(cursor.getColumnIndex("tenLoai"));
            listLoaiSach.add(loaiSach);
        }
        return listLoaiSach;
    }
}
