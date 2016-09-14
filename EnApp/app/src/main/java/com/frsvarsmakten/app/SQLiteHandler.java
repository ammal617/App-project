package com.frsvarsmakten.app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by T420S on 2014-03-28.
 */
public class SQLiteHandler extends SQLiteOpenHelper {



    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqliteDatabase";

    // Table Names
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_ASSIGNMENT = "deployments";
    public static final String TABLE_USERS = "users";



    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CONTACTS = "user_info";
    private static final String TAG_NUMBER = "Number";
    private static final String TAG_FIRSTNAME = "First name";
    private static final String TAG_LASTNAME = "Last name";
    private static final String TAG_USERID = "Userid";

    private static final String TAG_SUCCESSASSIGNMENT = "success";
    private static final String TAG_ASSIGNMENT = "deployments";
    private static final String TAG_DEPID = "Deployment ID";
    private static final String TAG_PRIO = "Prioritet";
    private static final String TAG_DEPTYPE = "Deployment typ";
    private static final String TAG_DEPDESCRIP = "Deployment description";
    private static final String TAG_LONG = "Longitud";
    private static final String TAG_LAT = "Latitud";

    // CONTACTS Table - column names
    public static final String KEY_NUMBER = "phonenumber";
    public static final String KEY_FIRST_NAME = "firstname";
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_CONTACT_ID = "contactid";


    //MISSION table - column names
    public static final String KEY_MISSION_ID = "missionid";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_LONGITUD = "Longitud";
    public static final String KEY_LATITUD = "Latitud";

    //USER TABLE - column names
    public static final String KEY_USER_ID = "userid";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_RESTRICTION = "restriction";
    public static final String KEY_GROUP = "group";




    // CONTACTS table create statement
    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "
            + TABLE_CONTACTS + "(" + KEY_NUMBER + " INTEGER," + KEY_FIRST_NAME
            + " TEXT," + KEY_LAST_NAME + " TEXT," + KEY_CONTACT_ID
            + " INTEGER PRIMARY KEY" + ");";

    // ASSIGNMENT table create statement
    private static final String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE "
            + TABLE_ASSIGNMENT + "(" + KEY_MISSION_ID
            + " INTEGER PRIMARY KEY" + KEY_PRIORITY + " INTEGER," +  KEY_TYPE
            + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_LONGITUD + " INTEGER,"+ KEY_LATITUD + " INTEGER,"+ ");";

    // USER table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USERS + "(" + KEY_USERNAME + " TEXT," + KEY_PASSWORD
            + " TEXT," + KEY_RESTRICTION + " TEXT," + KEY_USER_ID
            + " INTEGER PRIMARY KEY" + ");";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
        db.execSQL(CREATE_TABLE_CONTACTS);
        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

        if (!Contacts.isLowEnergy()) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        }

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);


        // create new tables
        onCreate(db);
    }


    // Adding new contact
    public void addAssignment(assignment assignment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TAG_DEPID, assignment.getDeploymentId());
        values.put(TAG_PRIO, assignment.getPrio());
        values.put(TAG_DEPTYPE, assignment.getDepType());
        values.put(TAG_DEPDESCRIP, assignment.getDepDescript());
        values.put(TAG_LONG, assignment.getLongitud());
        values.put(TAG_LAT, assignment.getLatitud());




        // Inserting Row
        db.insert(TABLE_ASSIGNMENT, null, values);
        db.close(); // Closing database connection
    }


    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, contact.getFirstname()); // Contact Name
        values.put(KEY_NUMBER, contact.getNumber()); // Contact Phone Number



        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    public void addSingleContact(String number, String firstname, String lastname, String contactid){

        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS,null,null);
        ContentValues values = new ContentValues();

        values.put(KEY_FIRST_NAME, firstname); // Contact Name
        values.put(KEY_NUMBER, Integer.parseInt(number)); // Contact Phone Number

        values.put(KEY_LAST_NAME, lastname); // Contact Name
        values.put(KEY_CONTACT_ID, Integer.parseInt(contactid)); // Contact Phone Number


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection

    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstname(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    /*
    public void setSqliteContactsList(ArrayList<HashMap<String, String>> contactsList) {


        String number = "0";
        String firstname = "jonas";
        String lastname = "skog";
        String contactid = "345";

        for(HashMap<String, String> map: contactsList) {
            for(Map.Entry<String, String> mapEntry: map.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();

            }
        }
        addSingleContact(number, firstname, lastname, contactid);


    }
    */

    public ArrayList<HashMap<String, String>> getSqliteContactsList(){

        ArrayList<HashMap<String, String>> sqliteContactsList = new ArrayList<HashMap<String, String>>();


        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> sqliteHashContactsList = new HashMap<String, String>();
                String number = (cursor.getString(0));
                String firstname = cursor.getString(1);
                String lastname = cursor.getString(2);
               String contactid = ( cursor.getString(3));


                Log.d(firstname, "första namn");

                // adding each child node to HashMap key => value
                sqliteHashContactsList.put(TAG_NUMBER, number);
                sqliteHashContactsList.put(TAG_FIRSTNAME, firstname);
                sqliteHashContactsList.put(TAG_LASTNAME, lastname);
                sqliteHashContactsList.put(TAG_USERID, contactid);


                sqliteContactsList.add(sqliteHashContactsList);


            } while (cursor.moveToNext());
        }



        return sqliteContactsList;
    }

    public ArrayList<HashMap<String, String>> getSqliteAssignmentList() {
        ArrayList<HashMap<String, String>> sqliteAssignmentList= new ArrayList<HashMap<String, String>>();


        // Select All Query
        String selectQuery = "SELECT  * FROM  " + TABLE_ASSIGNMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> sqliteHashAssignmentsList = new HashMap<String, String>();
                String id = (cursor.getString(0));
                String prio = cursor.getString(1);
                String type = cursor.getString(2);
                String descript = ( cursor.getString(3));
                String longitud = cursor.getString(4);
                String latitud = cursor.getString(5);


                // adding each child node to HashMap key => value
                sqliteHashAssignmentsList.put(TAG_DEPID, id);
                sqliteHashAssignmentsList.put(TAG_PRIO, prio);
                sqliteHashAssignmentsList.put(TAG_DEPTYPE, type);
                sqliteHashAssignmentsList.put(TAG_DEPDESCRIP, descript);
                sqliteHashAssignmentsList.put(TAG_LONG,longitud);
                sqliteHashAssignmentsList.put(TAG_LAT, latitud);


                sqliteAssignmentList.add(sqliteHashAssignmentsList);


            } while (cursor.moveToNext());
        }



        return sqliteAssignmentList;

    }

    public void addSingleAssignment(String depid, String prio, String type, String descript, String longit, String latit) {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS,null,null);
        ContentValues values = new ContentValues();


        values.put(KEY_MISSION_ID,depid);
        values.put(KEY_PRIORITY, prio);
        values.put(KEY_TYPE, type);
        values.put(KEY_DESCRIPTION, descript);
        values.put(KEY_LONGITUD, longit);
        values.put(KEY_LATITUD,latit);


        // Inserting Row
        db.insert(TABLE_ASSIGNMENT, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getSqliteInventoryList() {
        ArrayList<HashMap<String, String>> sqliteContactsList = new ArrayList<HashMap<String, String>>();


        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> sqliteHashContactsList = new HashMap<String, String>();
                String number = (cursor.getString(0));
                String firstname = cursor.getString(1);
                String lastname = cursor.getString(2);
                String contactid = ( cursor.getString(3));


                Log.d(firstname, "första namn");

                // adding each child node to HashMap key => value
                sqliteHashContactsList.put(TAG_NUMBER, number);
                sqliteHashContactsList.put(TAG_FIRSTNAME, firstname);
                sqliteHashContactsList.put(TAG_LASTNAME, lastname);
                sqliteHashContactsList.put(TAG_USERID, contactid);


                sqliteContactsList.add(sqliteHashContactsList);


            } while (cursor.moveToNext());
        }



        return sqliteContactsList;

    }

    public void addSingleInventory(String num, String fn, String ln, String ui) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS,null,null);
        ContentValues values = new ContentValues();

        values.put(KEY_FIRST_NAME, fn); // Contact Name
        values.put(KEY_NUMBER, Integer.parseInt(num)); // Contact Phone Number

        values.put(KEY_LAST_NAME, ln); // Contact Name
        values.put(KEY_CONTACT_ID, Integer.parseInt(ui)); // Contact Phone Number


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }




    /*
    // Getting All Contacts
    public List<Contact> getHashmapContacts() {
        ArrayList<HashMap<String, String>> contactsList = new ArrayList<HashMap<String, String>>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        HashMap<String, String> map = new HashMap<String, String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactId(Integer.parseInt(cursor.getString(0)));
                map.(KEY_NUMBER, )
                contact.setFirstname(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                // Adding contact to list
                contactsList.add(map);
            } while (cursor.moveToNext());
        }


        // return contact list
        return contactsList;
    }
    */


}