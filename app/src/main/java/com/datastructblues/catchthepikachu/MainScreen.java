package com.datastructblues.catchthepikachu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {
    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        music = MediaPlayer.create(
                this, R.raw.pokemontheme);
    }

    public void start(View view){
        Intent intent = new Intent(MainScreen.this,MainActivity.class);
        startActivity(intent);
        music.stop();
        finish();
    }

    public void exit(View view){
        finish();
    }
    public void pauseMusic(View view){
        music.pause();
    }
    public void startMusic(View view){
        music.start();
    }
}