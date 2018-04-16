package deakin.dungeonoftext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final Button backbtn = (Button) findViewById(R.id.back_button);


        ImageView img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.help_screen);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
