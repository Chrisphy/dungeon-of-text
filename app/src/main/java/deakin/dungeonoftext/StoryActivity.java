package deakin.dungeonoftext;

import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
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


    //JSON Objects
    JSONObject jsonobj;
    JSONObject gunobj;


    //Assets
    AssetManager assetManager;
    InputStream dataStream = null;
    InputStream imageStream = null;



    //Strings
    String jsonText = null;
    String temp;
    String currentDungeon;


    //Mediaplayer
    MediaPlayer musicplayer;
    AssetFileDescriptor fd;

    //Arrays
    String[] textArray = new String[1000];


    //Int
    int size;
    int Count = 0;


    //Boolean
    boolean gunpresence;


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



        setImage("intro.jpg");

        Utilities.initialMatrix();

        loadJSONFromAsset();

        textArray("intro");

        playBG();

    }

    public void playBG() {


        try {
            // Get the file from the asset folder
            fd = getAssets().openFd("bgmusic.mp3");

            // Create media player object
            musicplayer = new MediaPlayer();

            // Set the music source.
            musicplayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),fd.getLength());

            //Play and loop
            musicplayer.setLooping(true);
            musicplayer.prepare();
            musicplayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Onpause method to check if activity is out of focus
    protected void onPause() {
        super.onPause();

        musicplayer.pause();
    }

    protected void onResume(){
        super.onResume();

        musicplayer.start();
    }


    //Play the ending background music for the pap
    public void playEndBG(){
        musicplayer.stop();

        try {
            // Get the file from the asset folder
            fd = getAssets().openFd("endmusic.mp3");

            // Create media player object
            musicplayer = new MediaPlayer();

            // Set the music source.
            musicplayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),fd.getLength());

            //Play and loop
            musicplayer.setLooping(true);
            musicplayer.prepare();
            musicplayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Click event that is triggered when the textview is clicked iterating through the array
    public void clickEvent(View view){

        if(Count < textArray.length){

            textview.setText(textArray[Count]);

            Count++;
        }
        else{

            if(currentDungeon == "dungeon-11"){
                Count = 0;
                endingAlert();

            }
            if(currentDungeon == "end"){
                textview.setText("We hope you enjoyed the game!");
            }

            else{
                Count = 0;
                textview.setText("Click on any of the buttons to move.");
            }

        }
    }



    //Array method that takes a string value and outputs the appropriate array from the JSON

    public String[] textArray(String str){

        try{
            // creating json array from json object
            textArray = new String[1000];

            if(jsonText != null){
                jsonobj = new JSONObject(jsonText);

                JSONObject mainobj = jsonobj.getJSONObject("Story");

                currentDungeon = str;

                temp = mainobj.getString(str);

                temp = temp.substring(1,temp.length()-1);

                temp = temp.replaceAll("[\\[\\]\\(\\)]", "");

                textArray = temp.split(",");

            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return textArray;

    }




    //Load the JSON file from the Assets folder into memory
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


    //Set image file from the assets folder when given the string name
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



    //Clears all textview
    public void clearAll(){
        textview.setText("");
        textArray = new String[1000];
    }


    //Start level methods

    public void levelOne() {

        clearAll();

        setImage("dungeon-1.png");

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


    }


    public void levelFive() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-5");


    }

    public void levelSix() {

        setImage("dungeon-6.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-6");


    }

    public void levelSeven() {

        setImage("dungeon-7.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-7");

        alertGun();

    }


    public void levelEight() {

        setImage("dungeon-2.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-8");


    }

    public void levelNine() {

        setImage("dungeon-4.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-9");


    }

    public void levelTen() {

        setImage("dungeon-8.png");

        textview.setText("*Eerie noises*");

        textArray("dungeon-10");


    }


    public void levelEleven() {

        setImage("monster.png");


        textArray("dungeon-11");


    }


    public void end(){

            playEndBG();

            setImage("end_image.jpg");

            textview.setText("Freedom...");

            textArray("end");

            leftbtn.setVisibility(View.INVISIBLE);


    }

    //End level methods


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


    //Alert for yes/no picking up the gun

    public void alertGun(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick up?");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                guncheck();

                dialog.dismiss();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();


    }

    //Alert when gun is picked up

    public void confirmGun(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("You've picked up a gun!");
        builder.setMessage("Congratulations!");

        AlertDialog alert = builder.create();
        alert.show();


    }


    //Check for gun boolean inside the JSOn

    public void guncheck(){

        try{
            // creating json array from json object
            if(jsonText != null){
                gunobj = new JSONObject(jsonText);

                gunpresence = gunobj.getBoolean("gunPresence");

                if(gunpresence == false){


                    confirmGun();

                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }



    //Alert for ending sequence

    public void endingAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Do you want to run?");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                end();

                dialog.dismiss();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();


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
