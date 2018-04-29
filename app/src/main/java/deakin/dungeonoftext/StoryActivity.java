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

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public class StoryActivity extends MainActivity {


    //Views

    ImageView img = (ImageView) findViewById(R.id.storybg);
    TextView textview = (TextView) findViewById(R.id.textView);


    //Assets
    AssetManager assetManager = getResources().getAssets();
    InputStream dataStream = null;
    InputStream imageStream = null;


    //Array for text to load into
    String[] textarray = new String[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        int usermovement_number = 0;



        final Button upbtn = (Button) findViewById(R.id.up_move);
        final Button leftbtn = (Button) findViewById(R.id.left_move);
        final Button rightbtn = (Button) findViewById(R.id.right_move);



        try {
            dataStream = assetManager.open("Data/Data.json");
            if ( dataStream != null)

                Log.e("Error", "Loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }





        img.setImageResource(R.drawable.intro);



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


        textview.setText("Hello world!");

    }



    public void levelOne() {

        try {
            imageStream = assetManager.open("images/intro.jpeg");

            Bitmap image = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(image);

            textarray[0] = "Load something from JSON and put it here";

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

            textarray[0] = "Load something from JSON and put it here";

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
