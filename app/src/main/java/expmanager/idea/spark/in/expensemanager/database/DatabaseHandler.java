package expmanager.idea.spark.in.expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import expmanager.idea.spark.in.expensemanager.model.AddExpenseRequest;
import expmanager.idea.spark.in.expensemanager.model.Invoice;
import expmanager.idea.spark.in.expensemanager.model.Sales;
import expmanager.idea.spark.in.expensemanager.model.Staff;
import expmanager.idea.spark.in.expensemanager.model.TanExpenses;
import expmanager.idea.spark.in.expensemanager.utils.Utils;

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
    private static final String TABLE_MANUAL_EXPENSE = "manualexpense";
    private static final String TABLE_USERS = "users";


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
    private static final String KEY_STAFFEMAIL= "staff_email";
    private static final String KEY_DESIGNATION= "staff_designation";
    private static final String KEY_ADDRESS= "staff_address";
    private static final String KEY_PHONE= "staff_phone";
    private static final String KEY_PROFILEPIC= "staff_photo";

    private static final String SALE_NAME = "saletype";
    private static final String SALE_DATE = "saledate";
    private static final String SALE_PRICE = "saleamount";

    private static final String MEXPENSE_CATEGORY = "category";
    private static final String MEXPENSE_INVOICEID = "invoiceid";
    private static final String MEXPENSE_DATE = "mexpensedate";
    private static final String MEXPENSE_DESCRIPTION = "description";
    private static final String MEXPENSE_UNIT = "unit";
    private static final String MEXPENSE_AMOUNT = "mexpenseamount";


    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PIN = "user_pin";
    private static final String KEY_ISADMIN = "user_isadmin";
    private static final String KEY_ISAUTHORIZED = "user_isauthorized";
    private static final String KEY_COMANYID = "user_comanyid";
    private static final String KEY_STATUS= "user_status";
    private static final String KEY_CUSTOMERID= "user_customerid";
    private static final String KEY_CREATEDAT= "user_createdat";
    private static final String KEY_UDATEDAT= "user_updatedat";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PIN + " TEXT,"
                + KEY_ISADMIN + " TEXT,"
                + KEY_ISAUTHORIZED + " TEXT,"
                + KEY_COMANYID + " TEXT,"
                + KEY_STATUS + " TEXT,"
                + KEY_CUSTOMERID + " TEXT,"
                + KEY_CREATEDAT + " TEXT,"
                + KEY_UDATEDAT + " TEXT"
                + ")";


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
                + KEY_STAFFEMAIL + " TEXT,"
                + KEY_DESIGNATION + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_PROFILEPIC + " TEXT,"
                + KEY_STAFFPRICE + " TEXT"
                + ")";

        String CREATE_MEXPENSE_TABLE = "CREATE TABLE " + TABLE_MANUAL_EXPENSE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + MEXPENSE_CATEGORY + " TEXT,"
                + MEXPENSE_INVOICEID + " TEXT,"
                + MEXPENSE_DATE + " TEXT,"
                + MEXPENSE_DESCRIPTION + " TEXT,"
                + MEXPENSE_AMOUNT + " TEXT"
                + ")";


        db.execSQL("CREATE TABLE `categories` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT NOT NULL, `description` TEXT, `created_at` TEXT, `created_by` INTEGER )");

        db.execSQL("CREATE TABLE `expenses` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `category_id` INTEGER, `invoice_id` INTEGER, `date` TEXT, `description` TEXT,`unit` INTEGER, `amount` NUMERIC, `is_approved` INTEGER, `is_recurssive` INTEGER, `created_at` TEXT, `created_by` INTEGER, `is_saved` INTEGER, 'week_index' INTEGER)");

        db.execSQL("CREATE TABLE `income_types` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT NOT NULL, `description` TEXT, `created_at` TEXT, `created_by` INTEGER )");

        db.execSQL("CREATE TABLE `incomes` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `income_type_id` INTEGER, `date` TEXT, `weekid` INTEGER , `amount` INTEGER, `created_at` TEXT, `created_by` INTEGER )");

        db.execSQL("CREATE TABLE `invoices` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `date` TEXT, `image_path` TEXT, `description` TEXT, `amount` NUMERIC, `discount` NUMERIC, `payment_mode` TEXT, `bill_number` TEXT, `created_at` TEXT, `created_by` INTEGER )");

        db.execSQL(CREATE_TANEXPENSE_TABLE);
        db.execSQL(CREATE_STAFF_TABLE);
        db.execSQL(CREATE_SALE_TABLE);
        db.execSQL(CREATE_MEXPENSE_TABLE);
        db.execSQL(CREATE_USERS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANEXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFFDETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUAL_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        db.execSQL("drop table IF EXISTS categories");
        db.execSQL("drop table IF EXISTS expenses");
        db.execSQL("drop table IF EXISTS income_types");
        db.execSQL("drop table IF EXISTS incomes");
        db.execSQL("drop table IF EXISTS invoices");

        // Create tables again
        onCreate(db);
    }



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


    // Adding new products
    public  void addManualExpenses(AddExpenseRequest contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEXPENSE_CATEGORY, contact.getCategory());
        values.put(MEXPENSE_INVOICEID, contact.getInvoiceNumber());
        values.put(MEXPENSE_DATE, contact.getDate());
        values.put(MEXPENSE_DESCRIPTION, contact.getDescription());
        //values.put(MEXPENSE_UNIT, contact.getSameamount());
        values.put(MEXPENSE_AMOUNT, contact.getAmount());

        // Inserting Row
        db.insert(TABLE_SALES, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Expenses
    public List<AddExpenseRequest> getAllExpenses() {
        List<AddExpenseRequest> contactList = new ArrayList<AddExpenseRequest>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MANUAL_EXPENSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddExpenseRequest contact = new AddExpenseRequest();
                contact.setCategory(cursor.getString(1));
                contact.setInvoiceNumber(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setDescription(cursor.getString(4));
                contact.setAmount(Integer.parseInt(cursor.getString(5)));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public void insetInvoice(Invoice inv) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("description", inv.getInvDesc());
        cv.put("date", inv.getInvDate());
        cv.put("image_path", inv.getInvImgPath());
        cv.put("amount", inv.getInvAmt());
        cv.put("discount", inv.getInvDisc());
        cv.put("amount", inv.getInvAmt());
        cv.put("payment_mode", inv.getInvPayMode());
        cv.put("bill_number", inv.getInvNo());
        cv.put("created_at", Utils.getDateTime());
        cv.put("created_by", inv.getInvCreateBy());
        db.insert("invoices", null, cv);
    }


}