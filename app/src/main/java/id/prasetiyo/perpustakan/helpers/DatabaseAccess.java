package id.prasetiyo.perpustakan.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.prasetiyo.perpustakan.models.Books;
import id.prasetiyo.perpustakan.models.Peminjaman;
import id.prasetiyo.perpustakan.models.Pengguna;

/**
 * Created by aoktox on 30/04/16.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public ArrayList<Books> getBooks() {
        ArrayList<Books> list = new ArrayList<>();
        Books buku;
        Cursor cursor = database.rawQuery("SELECT * FROM buku", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            buku=new Books(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            list.add(buku);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Map<String, String>> getPenerbit() {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> item;
        Cursor cursor = database.rawQuery("SELECT penerbit,COUNT(penerbit) FROM buku GROUP BY penerbit", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item = new HashMap<String, String>();
            item.put("nama",cursor.getString(0) );
            item.put("jumlahBuku",cursor.getString(1));
            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Pengguna> getUsers() {
        ArrayList<Pengguna> list = new ArrayList<>();
        Pengguna user;
        Cursor cursor = database.rawQuery("SELECT * FROM anggota", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user=new Pengguna(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            list.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Pengguna getUsers(int id) {
        Pengguna user;
        Cursor cursor = database.rawQuery("SELECT * FROM anggota WHERE id_anggota="+"'"+id+"'", null);
        cursor.moveToFirst();
        user=new Pengguna(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return user;
    }

    public ArrayList<Peminjaman> getPeminjaman() {
        ArrayList<Peminjaman> list = new ArrayList<>();
        Peminjaman item;
        Cursor cursor = database.rawQuery("SELECT * FROM peminjaman", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item=new Peminjaman(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4),Integer.parseInt(cursor.getString(5)));
            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Peminjaman> getPeminjamanUser(int id) {
        ArrayList<Peminjaman> list = new ArrayList<>();
        Peminjaman item;
        Cursor cursor = database.rawQuery("SELECT * FROM peminjaman where id_anggota="+"'"+id+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item=new Peminjaman(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4),Integer.parseInt(cursor.getString(5)));
            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Books> getBooksByPenerbit(String p) {
        ArrayList<Books> list = new ArrayList<>();
        Books buku;
        Cursor cursor = database.rawQuery("SELECT * FROM buku where penerbit="+"'"+p+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            buku=new Books(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            list.add(buku);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Cursor do_login(String email, String password){
        Cursor cursor = database.rawQuery("SELECT id_anggota FROM anggota where email="+"'"+email+"' AND no_telp="+"'"+password+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    public boolean add_pinjam(int id_user,int id_buku){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));

        ContentValues initialValues = new ContentValues();
        initialValues.put("id_anggota", id_user);
        initialValues.put("id_buku", id_buku);
        initialValues.put("tgl_pinjam", dateFormat.format(date));
        initialValues.put("tgl_kembali", dateFormat.format(tomorrow));
        initialValues.put("status", 1);

        database.insert("peminjaman", null, initialValues);
        return true;
    }

}