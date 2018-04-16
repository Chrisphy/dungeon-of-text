package deakin.dungeonoftext;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends Activity {
    // Set timer for splash screen
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            // Showing splash screen with a timer. This will be useful when you

            @Override
            public void run() {
                // Start app main activity when timer finishes
                Intent i = new Intent(splashscreen.this, MainActivity.class);
                startActivity(i);
                // finish this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
