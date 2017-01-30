package sqllite.sample.com.sqllitsampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DbHelper dbHelper = new DbHelper(this);

    //inserting values into database
    dbHelper.addShop(new Shop("SHOPRITE","Padmanabhanagar main road, Bangalore - 560061"));
    dbHelper.addShop(new Shop("VISHAL MART","Uttrahalli Main Road, Bangalore - 560067"));
    dbHelper.addShop(new Shop("ANAGHA STORES","Sayyaji Rao Road, Mysore - 570001"));
    dbHelper.addShop(new Shop("RAGHAVENDRA SURGICALS","Sayyaji Rao Road, Bannimantap, Mysore - 570001"));

    //reading data from database
    List<Shop> allShops = dbHelper.getAllShops();
    if(allShops.size()>0){
      for (Shop shop:allShops
      ) {
        Log.d("Shop details", "Shop name :"+shop.getName() +" ,Shop Address: "+shop.getAddress()+" ,Shop ID :"+shop.getId());
      }
    }

    Log.d("Total number of shops", String.valueOf(dbHelper.getShopsCount()));

    Shop shop = dbHelper.getShop(100);
    Log.d("Updated Address", shop.getAddress());
    /*shop.setId(100);
    shop.setAddress("Cognitive Clouds, KSRTC Layout, Bangalore - 560001");
    dbHelper.updateShop(shop);*/

    Log.d("After delete count", String.valueOf(dbHelper.getShopsCount()));
  }
}
