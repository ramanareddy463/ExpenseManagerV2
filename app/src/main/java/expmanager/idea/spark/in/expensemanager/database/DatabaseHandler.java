package expmanager.idea.spark.in.expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.model.TanExpenses;

/**
 * Created by kveldurty on 12/17/16.
 */





public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expensemanagerv2";

    // Contacts table name
    private static final String TABLE_TANEXPENSE = "tanexpenses";


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_WHEN = "whentime";
    private static final String KEY_PRICE = "price";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TANEXPENSE_TABLE = "CREATE TABLE " + TABLE_TANEXPENSE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_WHEN + " TEXT,"
                + KEY_PRICE + " TEXT"
                + ")";

        db.execSQL(CREATE_TANEXPENSE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANEXPENSE);

        // Create tables again
        onCreate(db);
    }




/*    // Adding new products
    public  void UpdateQuoteStatus(QuoteModel quote) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where="qnumber=?";
        String[] whereArgs = new String[]{quote.getQuoteid()};
        ContentValues values = new ContentValues();
        values.put(Q_STATUS, quote.getStatus());



        // Inserting Row
        db.update(TABLE_QUOTES,values,where,whereArgs);
        db.close(); // Closing database connection
    }

    // Adding new products
    public  void addQuote(QuoteModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Q_EXPIRYDATE, contact.getExpiry_date());
        values.put(Q_NAME, contact.getQuotename());
        values.put(Q_CREATEDDATE, contact.getQuotedate());


        // Inserting Row
        db.insert(TABLE_QUOTES, null, values);
        db.close(); // Closing database connection
    }*/

    public  void addTanExpenses(TanExpenses texpense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, texpense.getCategory());
        values.put(KEY_WHEN, texpense.getWhen());
        values.put(KEY_PRICE, texpense.getPrice());



        // Inserting Row
        db.insert(TABLE_TANEXPENSE, null, values);
        db.close(); // Closing database connection
    }

    public List<TanExpenses> getAllTanExpenses() {
        List<TanExpenses> expList = new ArrayList<TanExpenses>();


        String selectQuery = "SELECT  * FROM " + TABLE_TANEXPENSE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                TanExpenses tanexpen = new TanExpenses();
                tanexpen.setCategory(cursor.getString(1));
                tanexpen.setWhen(cursor.getString(2));
                tanexpen.setPrice(cursor.getString(3));



                // Adding contact to list
                expList.add(tanexpen);
            } while (cursor.moveToNext());
        }

        // return contact list
        return expList;
    }

}