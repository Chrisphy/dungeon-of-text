package deakin.dungeonoftext;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class StoryActivity extends MainActivity implements View.OnClickListener {


    //Views

    ImageView img;
    TextView textview;
    JSONObject jsonobj;

    //Assets
    AssetManager assetManager;
    InputStream dataStream = null;
    InputStream imageStream = null;

    //Array for text to load into
    String jsonText = null;



    //Arrays
    String[] introtextarray = new String[500];
    String[] textArray = new String[1000];



    //Int
    int size;
    int Count = 0;


    Handler h = new Handler();

    //Navigation buttons
    Button upbtn;
    Button dwnbtn;
    Button leftbtn;
    Button rightbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);




        /**
         final Button upbtn = (Button) findViewById(R.id.up_move);
        final Button leftbtn = (Button) findViewById(R.id.left_move);
        final Button rightbtn = (Button) findViewById(R.id.right_move);
         */
        //Initial navigation buttons
        upbtn = (Button) findViewById(R.id.up_move);
        dwnbtn = (Button)findViewById(R.id.down_move);
        leftbtn = (Button) findViewById(R.id.left_move);
        rightbtn = (Button) findViewById(R.id.right_move);
        //Set navigation button response when clicking
        upbtn.setOnClickListener(this);
        dwnbtn.setOnClickListener(this);
        leftbtn.setOnClickListener(this);
        rightbtn.setOnClickListener(this);
        //Hide unnecessary navigation buttons
        dwnbtn.setVisibility(View.INVISIBLE);
        leftbtn.setVisibility(View.INVISIBLE);
        rightbtn.setVisibility(View.INVISIBLE);

        img = (ImageView) findViewById(R.id.storybg);
        textview = (TextView) findViewById(R.id.textView);
        assetManager = getApplicationContext().getAssets();


        img.setImageResource(R.drawable.intro);


        Utilities.initialMatrix();

        loadJSONFromAsset();

        textArray("intro");


        textLoad();



        /*
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


                for (int i = 0; i < jsonarr.length(); i++) {
                    JSONObject jsonObject = jsonarr.optJSONObject(i);
                    // getting data from individual object
                    String intro = jsonObject.getString("intro");
                    //introtextarray = intro.split(",");
                    Log.e("Json String", "JSON String"+ introtextarray[0]);


                }


            }

        } catch(Exception e){
            e.printStackTrace();
        }





        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                levelOne();

            }
        });




        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                levelTwo();

            }
        });



        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    */
    }


    //Change this to a timer progression instead of onclick? Discussion with duc necessary.

    //todo Timer class
    //todo Pass in array needed from JSON
    //todo Movement


    public void textLoad() {

        h.postDelayed(new Runnable(){
            public void run(){
            //change your text here
            if(Count <= textArray.length){
                textview.setText(textArray[Count]);
                Count++;
            }
            else{
                Count = 0;
                return;
            }
            }
        }, 1000);


    }

    public void clickEvent(View view){
        if(Count < textArray.length || textArray == null){
            textview.setText(textArray[Count]);
            Count++;
        }
        else{
            textview.setText("Click on any of the 4 buttons to move.");
        }
    }




    public String[] textArray(String str){

        try{
            // creating json array from json object
            if(jsonText != null){
                jsonobj = new JSONObject(jsonText);
                // Log.e("Json String", "JSON String"+jsonobj.toString());

                JSONObject mainobj = jsonobj.getJSONObject("Story");

                String temp = mainobj.getString(str);

                temp = temp.substring(1,temp.length()-1);

                temp = temp.replaceAll("[\\[\\]\\(\\)]", "");

                textArray = temp.split(",");


            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return textArray;

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



    public void setImage(String str){

        try {
            imageStream = assetManager.open("images/" + str);

            Bitmap image = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(image);



            if ( imageStream != null)
                Log.e("Error", "Failed to load");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void levelOne() {

        setImage("dungeon-1.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-1");

    }



    public void levelTwo() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-2");


    }



    public void levelThree() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("second-dungeon");


    }


    public void levelFour() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("second-dungeon");


    }


    public void levelFive() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("second-dungeon");


    }




    public void checkRoom() {
        Utilities.room = Utilities.getRoom();
        switch (Utilities.room){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 10:
                break;
            case 11:
                break;
            case 13:
                break;
            case 14:
                break;
        }



    }
    //Function to hide navigation refer to map
    public void checkButtons(){
        if(Utilities.a != 0 && Utilities.b == 2) {
            dwnbtn.setVisibility(View.VISIBLE);
        }else {dwnbtn.setVisibility(View.INVISIBLE);}
        if(Utilities.a != 3 && Utilities.b == 2) {
            upbtn.setVisibility(View.VISIBLE);
        }else {upbtn.setVisibility(View.INVISIBLE);}
        if(Utilities.b == 0 || (Utilities.a == 0 && Utilities.b == 1) || (Utilities.a == 2 && Utilities.b == 2)
                || (Utilities.a == 2 && Utilities.b == 1) || (Utilities.a == 3 && Utilities.b == 1)) {
            rightbtn.setVisibility(View.INVISIBLE);
        }else {rightbtn.setVisibility(View.VISIBLE);}
        if(Utilities.b == 3|| (Utilities.a == 3 && Utilities.b == 2 || (Utilities.a == 2 && Utilities.b == 0)) ) {
            leftbtn.setVisibility(View.INVISIBLE);
        }else {leftbtn.setVisibility(View.VISIBLE);}
    }

    //Function which is called when navigation button is clicked
    public void onClick(View v){
        if(v.getId() == R.id.up_move){
            if(Utilities.level == 0){
                Utilities.a=0;
                Utilities.b=2;
                Utilities.matrix[Utilities.a][Utilities.b]=1;
                Utilities.level = 1;
                Toast.makeText(this,"Go to level 1", Toast.LENGTH_LONG).show();
                checkButtons();
                levelOne();
            }else{
                Utilities.moveUp();
                checkButtons();
                Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
            }
        }
        if(v.getId() == R.id.down_move){
            Utilities.moveDown();
            checkButtons();
            Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
        if(v.getId() == R.id.left_move){
            Utilities.moveLeft();
            checkButtons();
            Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
        if(v.getId() == R.id.right_move){
            Utilities.moveRight();
            checkButtons();
            Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
    }
}
