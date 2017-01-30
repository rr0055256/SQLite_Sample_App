package sqllite.sample.com.sqllitsampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by galaxy-user on 30/01/17.
 */

public class DbHelper extends SQLiteOpenHelper{

  //Databse version
  private static final int DATABASE_VERSION = 1;
  // Database name
  private static final String DATABASE_NAME = "shopsInfo";

  //Contacts table name
  private static final String TABLE_SHOPS = "shops";

  //Shops table column names
  private static final String KEY_ID = "id";

  private static final String KEY_NAME = "name";

  private static final String KEY_SH_ADDR = "shop_address";


  public DbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE = "CREATE TABLE "+TABLE_SHOPS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_SH_ADDR+" TEXT"+")";
    db.execSQL(CREATE_CONTACTS_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //drop old table id exists
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_SHOPS);
    //Create a new table
    onCreate(db);
  }

  //Adding new shop
  public void addShop(Shop shop){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_NAME,shop.getName());
    values.put(KEY_SH_ADDR,shop.getAddress());

    //Inserting row
    sqLiteDatabase.insert(TABLE_SHOPS,null,values);
    sqLiteDatabase.close();//close the connection
  }

  //getting one shop
  public Shop getShop(int id){
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.query(TABLE_SHOPS,new String[]{KEY_ID,KEY_NAME,KEY_SH_ADDR},KEY_ID + "=?",new String[]{
        String.valueOf(id)},null,null,null,null);
    if(cursor!=null)
      cursor.moveToFirst();
      //return shop
    assert cursor != null;
    Shop shop = new Shop(cursor.getString(1), cursor.getString(2));
    cursor.close();
    return shop;
  }

  //Getting All Shops
  public List<Shop> getAllShops() {
    List<Shop> shopList = new ArrayList<Shop>();
    //Select All query
    String selectQuery = "SELECT * FROM " + TABLE_SHOPS;
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
      do {
        Shop shop = new Shop();
        shop.setId(Integer.parseInt(cursor.getString(0)));
        shop.setName(cursor.getString(1));
        shop.setAddress(cursor.getString(2));
        //Adding contacts to list
        shopList.add(shop);
      } while (cursor.moveToNext());
    }
    cursor.close();
  return  shopList;
  }

  //Getting shops count
  public int getShopsCount(){
    String selectQuery = "SELECT * FROM "+TABLE_SHOPS;
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery(selectQuery,null);
    int count = cursor.getCount();
    cursor.close();
    return cursor.getCount();
  }

  //updating records
  public int updateShop(Shop shop){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_NAME, shop.getName());
    values.put(KEY_SH_ADDR,shop.getAddress());
    //updating row
    return db.update(TABLE_SHOPS,values,KEY_ID+" = ?",new String[]{String.valueOf(shop.getId())});
  }

  //deleting a record
  public void deleteShop(Shop shop){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    sqLiteDatabase.delete(TABLE_SHOPS,KEY_ID+" = ?",new String[]{String.valueOf(shop.getId())});
    sqLiteDatabase.close();
  }
}
