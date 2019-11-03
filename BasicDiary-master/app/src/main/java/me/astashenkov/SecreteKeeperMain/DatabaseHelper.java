package me.astashenkov.SecreteKeeperMain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "diary_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DiaryAdapter.Diary.CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

        _db.execSQL("DROP TABLE IF EXISTS " + DiaryAdapter.Diary.DIARY);
        onCreate(_db);
    }

    public void setupDiary(DiaryAdapter.Diary[] diaries) {
        for (int i = 0; i < diaries.length; i++) {
            insertDiary(diaries[i]);
        }
    }

    public long insertDiary(DiaryAdapter.Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiaryAdapter.Diary.TITLE, diary.getTitle());
        values.put(DiaryAdapter.Diary.DESCRIPTION, diary.getDescription());

        long id = db.insert(DiaryAdapter.Diary.DIARY, null, values);

        db.close();
        return id;
    }

    public DiaryAdapter.Diary getDiary(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DiaryAdapter.Diary.DIARY,
                null,
                DiaryAdapter.Diary.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        DiaryAdapter.Diary diary = new DiaryAdapter.Diary(cursor.getInt(cursor.getColumnIndex(DiaryAdapter.Diary.ID)),
                cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.TITLE)),
                cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.CREATED)),
                cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.MODIFIED)));

        cursor.close();
        return diary;
    }

    public ArrayList<DiaryAdapter.Diary> getAllDiaries(String sortColumn) {
        ArrayList<DiaryAdapter.Diary> diaries = new ArrayList<>();

        // Select All Query

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DiaryAdapter.Diary.DIARY, null, null, null, null, null, sortColumn);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DiaryAdapter.Diary diary = new DiaryAdapter.Diary();
                diary.setId(cursor.getInt(cursor.getColumnIndex(DiaryAdapter.Diary.ID)));
                diary.setTitle(cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.TITLE)));
                diary.setDescription(cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.DESCRIPTION)));
                diary.setDateCreated(cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.CREATED)));
                diary.setDateModified(cursor.getString(cursor.getColumnIndex(DiaryAdapter.Diary.MODIFIED)));

                diaries.add(diary);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return diaries;
    }

    public int getDiariesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DiaryAdapter.Diary.DIARY, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateDiary(DiaryAdapter.Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiaryAdapter.Diary.TITLE, diary.getTitle());
        values.put(DiaryAdapter.Diary.DESCRIPTION, diary.getDescription());
        values.put(DiaryAdapter.Diary.MODIFIED, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int id = db.update(DiaryAdapter.Diary.DIARY, values, DiaryAdapter.Diary.ID + "=?",
                new String[]{String.valueOf(diary.getId())});
        db.close();
        return id;
    }

    public void deleteDiary(DiaryAdapter.Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DiaryAdapter.Diary.DIARY, DiaryAdapter.Diary.ID + "=?",
                new String[]{String.valueOf(diary.getId())});
        db.close();
    }

}
