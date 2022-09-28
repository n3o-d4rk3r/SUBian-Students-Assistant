package bd.edu.sub.subian.wtslider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import bd.edu.sub.subian.Activities.MainActivity;
import bd.edu.sub.subian.Activities.PhoneNumberActivity;
import bd.edu.sub.subian.R;


/*
*
* Developed by Aryan Kabir
* BSC in CSE, State University of Bangladesh
* Date : 08/07/2021, BD

================
Profile Sortlink:
================
* Github Link: https://github.com/n3od4rk3r
* Fb Link: https://fb.com/n3od4rk3r
* Website: https://n3o-d4rk3r.github.io/
================
*
*/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        int time = 1500;
        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            Intent mainIntent = new Intent(SplashActivity.this,ActivityWtslider.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }, time);
    }
}