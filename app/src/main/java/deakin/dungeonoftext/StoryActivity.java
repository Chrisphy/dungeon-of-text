package deakin.dungeonoftext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Array;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        int usermovement_number = 0;



        final Button upbtn = (Button) findViewById(R.id.up_move);
        final Button leftbtn = (Button) findViewById(R.id.left_move);
        final Button rightbtn = (Button) findViewById(R.id.right_move);


        ImageView img= (ImageView) findViewById(R.id.storybg);
        img.setImageResource(R.drawable.cloudy_intro_bg);



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


    public void screenTapped(View view) {
        // Your code here

    }








}
