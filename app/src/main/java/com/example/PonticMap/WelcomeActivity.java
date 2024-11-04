// WelcomeActivity.java
package com.example.PonticMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button startMapButton;

    private Button manual;

    private static final int CLICK_THRESHOLD = 5;
    private int clickCount = 0;
    private boolean easterEggAccessed = false;
    private ImageButton circularImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // In onCreate() method of WelcomeActivity
        circularImageButton = findViewById(R.id.circularImageButton);
        Button startMapButton = findViewById(R.id.startMapButton);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        startMapButton.startAnimation(anim);
        Animation circularImageViewAnimation = AnimationUtils.loadAnimation(this , R.anim.scale_anim);
        circularImageButton.startAnimation(circularImageViewAnimation);





        startMapButton = findViewById(R.id.startMapButton);
        startMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the map activity
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        circularImageButton = findViewById(R.id.circularImageButton);
        circularImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easterEggAccessed) {
                    Toast.makeText(WelcomeActivity.this, "Είσαι ήδη πολύ Πόντιος!!", Toast.LENGTH_SHORT).show();
                } else {
                    clickCount++;
                    if (clickCount < CLICK_THRESHOLD) {
                        int remainingClicks = CLICK_THRESHOLD - clickCount;
                        vibrateDevice();
                        Toast.makeText(WelcomeActivity.this, "Για να γίνεις ακόμη πιο πόντιος έχεις " + remainingClicks + " clicks...", Toast.LENGTH_SHORT).show();
                    }
                    if (clickCount == CLICK_THRESHOLD) {
                        easterEggAccessed = true;
                        startActivity(new Intent(WelcomeActivity.this, EasterEggActivity.class));
                        Toast.makeText(WelcomeActivity.this, "ΕΓΊΝΕΣ ΑΚΌΜΗ ΠΙΟ ΠΌΝΤΙΟΣ!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void vibrateDevice() {
        // Get instance of Vibrator service
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Vibrate for 500 milliseconds
        if (vibrator != null) {
            vibrator.vibrate(300);
        }
    }


    }
