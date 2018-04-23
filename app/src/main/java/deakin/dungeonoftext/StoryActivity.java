package deakin.dungeonoftext;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class StoryActivity extends MainActivity {


    //Views

    ImageView img;
    TextView textview;
    JSONObject jsonobj;
    JSONArray jsonarr;

    //Assets
    AssetManager assetManager;
    InputStream dataStream = null;
    InputStream imageStream = null;

    //Array for text to load into
    String jsonText = null;



    //Arrays
    String[] introtextarray = new String[50];


    //Int
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        int usermovement_number = 0;



        final Button upbtn = (Button) findViewById(R.id.up_move);
        final Button leftbtn = (Button) findViewById(R.id.left_move);
        final Button rightbtn = (Button) findViewById(R.id.right_move);


        img = (ImageView) findViewById(R.id.storybg);
        textview = (TextView) findViewById(R.id.textView);
        assetManager = getApplicationContext().getAssets();


        img.setImageResource(R.drawable.intro);




        loadJSONFromAsset();

        try{
            // creating json array from json object
            if(jsonText != null){
                jsonobj = new JSONObject(jsonText);
               // Log.e("Json String", "JSON String"+jsonobj.toString());

                 JSONObject mainobj = jsonobj.getJSONObject("Story");

                String intro = mainobj.getString("intro");
                intro = intro.replaceAll("[\\[\\]\\(\\)]", "");
                introtextarray = intro.split(",");
                Log.e("Json String", "JSON String"+ introtextarray[0]);

                 /*
                for (int i = 0; i < jsonarr.length(); i++) {
                    JSONObject jsonObject = jsonarr.optJSONObject(i);
                    // getting data from individual object
                    String intro = jsonObject.getString("intro");
                    //introtextarray = intro.split(",");
                    Log.e("Json String", "JSON String"+ introtextarray[0]);


                }
                */

            }

        } catch(Exception e){
            e.printStackTrace();
        }





        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }



    public void onClickView (View v) {


        textview.setText(introtextarray[0]);

        Log.e("Json String", "JSON String"+ introtextarray[0]);



    }



    public String loadJSONFromAsset() {
        try {
            dataStream = assetManager.open("Data/Data.json");
            size = dataStream.available();
            byte[] buffer = new byte[size];
            dataStream.read(buffer);
            dataStream.close();
            jsonText = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonText;
    }










    public void levelOne() {

        try {
            imageStream = assetManager.open("images/intro.jpeg");

            Bitmap image = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(image);



            if ( imageStream != null)
                Log.e("Error", "Failed to load");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void levelTwo() {

        try {
            imageStream = assetManager.open("images/intro.jpg");

            Bitmap image = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(image);

            introtextarray[0] = "Load something from JSON and put it here";

            if ( imageStream != null)
                Log.e("Error", "Failed to load");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public void movement() {

        //TODO movement array here to keep track of where the player is to load the level classes above


    }


}
