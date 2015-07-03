package com.piotr.piotr.vocabtrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Piotr on 03/07/2015.
 * code based on example from:
 * http://hmkcode.com/android-simple-sqlite-database-tutorial/
 */
public class DictDBHelper extends SQLiteOpenHelper {

    // DB version
    private static final int DB_VER = 1;
    //DB name
    private static final String DB_NAME = "DBVocabTrainer";

    public DictDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql for creating dictionary table
        String CREATE_DICTIONARY_TABLE = "CREATE TABLE dictionary ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "item TEXT, " +  //not sure if INDEX is needed here and correctly declared
                "translation TEXT )";
        //create dictionary table using above statement
        db.execSQL(CREATE_DICTIONARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        //drops old dictionary table if exists
        db.execSQL("DROP TABLE IF EXISTS dictionary");

        //create a new dictionary table
        this.onCreate(db);
    }

    //****************************************************************************

    /**
     * CRUD operations
     */

    //static constants for table name and columns
    private static final String TABLE_DICTIONARY = "dictionary";
    private static final String KEY_ID = "id";
    private static final String KEY_ITEM = "item";
    private static final String KEY_TRANSLATION = "translation";
    private static final String[] COLUMNS = {KEY_ID,KEY_ITEM,KEY_TRANSLATION};


    //CREATE - adding item to dictionary table
    public void addDictItem(DictItem dictItem){
        //for logging - not sure if I need it
        Log.d("addDictItem", dictItem.toString());

        //1.reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2.creating ContentValues to add key column/value
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM, dictItem.getItem()); //getting dictItem
        values.put(KEY_TRANSLATION, dictItem.getTranslation()); //getting translation

        //3.inserting into dictionary table
        db.insert(TABLE_DICTIONARY,
                null,
                values); //key/value where key=column name and value=column value

        //4.closing DB
        db.close();
    }

    //READ - getting one item from dictionary table using Cursor
    public DictItem getDictItem(int id) {
        //1.reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        //2.build query
        Cursor cursor =
                db.query(TABLE_DICTIONARY, //a.table
                COLUMNS,//b.columns names - selected all columns but may need only item column
                "id = ?", //c. selections
                new String[] {String.valueOf(id)}, //d.selections args
                null, //e.group by
                null, //f.having
                null, //g.order by
                null); //limit
        //3.if there is results get the firs
        if (cursor !=null)
            cursor.moveToFirst();

        //4.build dictItem object
        DictItem dictItem = new DictItem();
        dictItem.setId(Integer.parseInt(cursor.getString(0)));
        dictItem.setItem(cursor.getString(1));
        dictItem.setTranslation(cursor.getString(2));

        //log - not sure if I need it
        Log.d("getItem(" + id + ")", dictItem.toString());

        //5.returning dictItem
        return dictItem;
    }

    //READ - getting all items
    public List<DictItem> getAllDictItems() {
        List<DictItem> dictItems = new LinkedList<DictItem>();

        //1.query
        String query = "SELECT * FROM " + TABLE_DICTIONARY;

        //2.reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3.building DictItems from each row and adding to list
        DictItem dictItem = null;
        if (cursor.moveToFirst()) {
            do{
                dictItem = new DictItem();
                dictItem.setId(Integer.parseInt(cursor.getString(0)));
                dictItem.setItem(cursor.getString(1));
                dictItem.setTranslation(cursor.getString(2));

                //add dictItem to dictItems
                dictItems.add(dictItem);
            }while (cursor.moveToNext());
        }

        Log.d("getAllDictItems()", dictItems.toString());

        //return all dictItems
        return dictItems;
    }

    //UPDATE - updating dictItem
    public int updateDictItem(DictItem dictItem) {
        //1.ref to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2.creating ContentValues to add key=column/value
        ContentValues values = new ContentValues();
        values.put("dictItem", dictItem.getItem()); //getting dictItem
        values.put("translation", dictItem.getTranslation()); //getting translation

        //3.updating dictItem
        int i = db.update(TABLE_DICTIONARY,
                values,
                KEY_ID+" = ?",
                new String[] {String.valueOf(dictItem.getId())});

        //4.closing DB
        db.close();
        return i;
    }

    //DELETE - deleting from Dictionary table
    public void deleteDictItem(DictItem dictItem) {
        //1.ref to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2.delete
        db.delete(TABLE_DICTIONARY,
                KEY_ID+"= ?",
                new String[] {String.valueOf(dictItem.getId())});
        //3.close DB
        db.close();

        //log - probably dont need it
        Log.d("deleteDictItem", dictItem.toString());
    }
}