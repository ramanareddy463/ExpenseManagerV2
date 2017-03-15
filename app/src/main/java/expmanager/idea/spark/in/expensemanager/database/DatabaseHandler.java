package expmanager.idea.spark.in.expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.model.Sales;
import expmanager.idea.spark.in.expensemanager.model.Staff;
import expmanager.idea.spark.in.expensemanager.model.TanExpenses;

/**
 * Created by kveldurty on 12/17/16.
 */





public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expensemanagerv2";

    // Contacts table name
    private static final String TABLE_TANEXPENSE = "tanexpenses";
    private static final String TABLE_STAFFDETAILS = "staffdetails";
    private static final String TABLE_SALES = "sales";


    // Category  Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_WHEN = "whentime";
    private static final String KEY_PRICE = "price";


    // Staff  Table Columns names
    private static final String KEY_STAFFNAME = "staff_name";
    private static final String KEY_DAY1 = "shift_days1";
    private static final String KEY_DAY2 = "shift_days2";
    private static final String KEY_TIME1 = "shift_time1";
    private static final String KEY_TIME2 = "shift_time2";
    private static final String KEY_STAFFSTARTDATE = "staff_startdate";
    private static final String KEY_STAFFPRICE= "price_perhr";

    private static final String SALE_NAME = "saletype";
    private static final String SALE_DATE = "saledate";
    private static final String SALE_PRICE = "saleamount";


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

        String CREATE_SALE_TABLE = "CREATE TABLE " + TABLE_SALES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + SALE_NAME + " TEXT,"
                + SALE_DATE + " TEXT,"
                + SALE_PRICE + " TEXT"
                + ")";

        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_STAFFDETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_STAFFNAME + " TEXT,"
                + KEY_DAY1 + " TEXT,"
                + KEY_DAY2 + " TEXT,"
                + KEY_TIME1 + " TEXT,"
                + KEY_TIME2 + " TEXT,"
                + KEY_STAFFSTARTDATE + " TEXT,"
                + KEY_STAFFPRICE + " TEXT"
                + ")";

        db.execSQL(CREATE_TANEXPENSE_TABLE);
        db.execSQL(CREATE_STAFF_TABLE);
        db.execSQL(CREATE_SALE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANEXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFFDETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);

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

    public  void addStaff(Staff staffdetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STAFFNAME, staffdetails.getStaff_name());
      //  values.put(KEY_DAY1, staffdetails.getShift_days1());
      //  values.put(KEY_DAY2, staffdetails.getShift_days2());
      //  values.put(KEY_TIME1, staffdetails.getShift_time1());
//        values.put(KEY_STAFFSTARTDATE, staffdetails.getStaff_startdate());
       // values.put(KEY_STAFFPRICE, staffdetails.getPrice_perhr());





        // Inserting Row
        db.insert(TABLE_STAFFDETAILS, null, values);
        db.close(); // Closing database connection
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<Staff>();


        String selectQuery = "SELECT  * FROM " + TABLE_TANEXPENSE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Staff detailstaff = new Staff();
                detailstaff.setStaff_name(cursor.getString(1));
              //  detailstaff.setShift_days1(cursor.getString(2));
              //  detailstaff.setShift_days2(cursor.getString(3));
              //  detailstaff.setShift_time1(cursor.getString(4));
              //  detailstaff.setShift_time2(cursor.getString(5));
              //  detailstaff.setStaff_startdate(cursor.getString(6));
               // detailstaff.setPrice_perhr(cursor.getString(7));



                // Adding contact to list
                staffList.add(detailstaff);
            } while (cursor.moveToNext());
        }

        // return contact list
        return staffList;
    }


    // Adding new products
    public  void addSalesDetails(Sales contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SALE_NAME, contact.getSaletype());
        values.put(SALE_DATE, contact.getDate());
        values.put(SALE_PRICE, contact.getSameamount());

        // Inserting Row
        db.insert(TABLE_SALES, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<Sales> getAllSalesDetails() {
        List<Sales> contactList = new ArrayList<Sales>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SALES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Sales contact = new Sales();
                contact.setSaletype(cursor.getString(1));
                contact.setDate(cursor.getString(2));
                contact.setSameamount(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}