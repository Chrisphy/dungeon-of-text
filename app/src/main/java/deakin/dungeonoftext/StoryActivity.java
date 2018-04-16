package deakin.dungeonoftext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button helpbtn = (Button) findViewById(R.id.help_button);
        final Button startbtn = (Button) findViewById(R.id.start_button);
        final Button routebtn = (Button) findViewById(R.id.routes_button);





        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                    startActivity(intent);


                }
                catch (Exception e){
                    Log.e("Error", "Failed to go to Helpactivity");
                    e.printStackTrace();
                }

            }
        });




        routebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                    startActivity(intent);


                }
                catch (Exception e){
                    Log.e("Error", "Failed to go to Helpactivity");
                    e.printStackTrace();
                }

            }
        });



        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                    startActivity(intent);


                }
                catch (Exception e){
                    Log.e("Error", "Failed to go to Helpactivity");
                    e.printStackTrace();
                }

            }
        });



    }







}
