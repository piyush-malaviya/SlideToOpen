package com.pcm.slidetoopensample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pcm.slidetoopen.OnSliderStatusChange;
import com.pcm.slidetoopen.SlideToOpen;

public class MainActivity extends AppCompatActivity {

    private SlideToOpen slideToOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideToOpen = (SlideToOpen) findViewById(R.id.slideToOpen);
        slideToOpen.setOnSliderStatusChange(new OnSliderStatusChange() {
            @Override
            public void onSliderStatusChange(boolean isOpen) {
                if (isOpen) {
                    Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
