package deakin.dungeonoftext;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class StoryActivity extends MainActivity implements View.OnClickListener {


    //Views

    ImageView img;
    TextView textview;
    JSONObject jsonobj;

    //Assets
    AssetManager assetManager;
    InputStream dataStream = null;
    InputStream imageStream = null;
    static MediaPlayer m;

    //Array for text to load into
    String jsonText = null;



    //Arrays
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

        playBG();

    }

    public void playBG() {
        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
                m = new MediaPlayer();
            }

            AssetFileDescriptor descriptor = getAssets().openFd("bgmusic.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(50,50);
            m.setLooping(true);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public void setText(final String s)
    {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if (i[0] == length - 1) {
                    textview.setText(null);
                }

                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                textview.append(String.valueOf(c));
                i[0]++;



            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 100);
    }





    public void clickEvent(View view){


        if(Count < textArray.length || textArray == null){
           // textview.setText(textArray[Count]);
            setText(textArray[Count]);

            Count++;
        }
        else{
            Count = 0;

            textview.setText("Click on any of the buttons to move.");
        }
    }





    public String[] textArray(String str){

        try{
            // creating json array from json object
            textArray = new String[1000];

            if(jsonText != null){
                jsonobj = new JSONObject(jsonText);

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



            if ( imageStream == null)
                Log.e("Error", "Failed to load");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void levelOne() {

        setImage("dungeon-1.png");

        playBG();

        textArray("dungeon-1");


        Log.e("Json String", "JSON String"+ textArray[0]);

    }



    public void levelTwo() {

        setImage("dungeon-4.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-2");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }



    public void levelThree() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-3");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }


    public void levelFour() {

        setImage("dungeon-1.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-4");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }


    public void levelFive() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-5");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }

    public void levelSix() {

        setImage("dungeon-6.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-6");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }

    public void levelSeven() {

        setImage("dungeon-7.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-7");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }


    public void levelEight() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-8");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }

    public void levelNine() {

        setImage("dungeon-4.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-9");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }

    public void levelTen() {

        setImage("dungeon-8.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-10");

        Log.e("Json String", "JSON String"+ textArray[0]);

    }


    public void levelEleven() {

        setImage("monster.png");

        textview.setText("*You hear a lot of movement in the darkness...*");

        textArray("dungeon-11");

    }


    public void end(boolean bool){
        if(bool == true){
            setImage("end_image.png");

            textArray("end");
        }

        else{
            setImage("game-over.png");

            textArray("end-2");

        }

    }


    //Function to check current room and load appropriate room
    public void checkRoom() {
        Utilities.room = Utilities.getRoom();
        switch (Utilities.room){
            case 1:
                levelThree();
                break;
            case 2:
                levelOne();
                break;
            case 3:
                levelTwo();
                break;
            case 4:
                levelSix();
                break;
            case 5:
                levelFive();
                break;
            case 6:
                levelFour();
                break;
            case 7:
                levelNine();
                break;
            case 10:
                levelSeven();
                break;
            case 11:
                levelEight();
                break;
            case 13:
                levelEleven();
                break;
            case 14:
                levelTen();
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
               // Toast.makeText(this,"Go to level 1", Toast.LENGTH_LONG).show();
                checkButtons();
                levelOne();
            }else{
                Utilities.moveUp();
                checkButtons();
                checkRoom();
                //Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
            }
        }
        if(v.getId() == R.id.down_move){
            Utilities.moveDown();
            checkButtons();
            checkRoom();
            //Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
        if(v.getId() == R.id.left_move){
            Utilities.moveLeft();
            checkButtons();
            checkRoom();
            //Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
        if(v.getId() == R.id.right_move){
            Utilities.moveRight();
            checkButtons();
            checkRoom();
            //Toast.makeText(this, String.valueOf(Utilities.getRoom()), Toast.LENGTH_LONG).show();
        }
    }


}
