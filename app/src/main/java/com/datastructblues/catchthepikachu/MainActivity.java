package com.datastructblues.catchthepikachu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView pikachu;
    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText=findViewById(R.id.scoreText);
        timeText=findViewById(R.id.timeText);
        pikachu=findViewById(R.id.pikachu);

        score=0;
        scoreText.setText("Score:" + score);
        moveImage();
        timer();

    }
    public void onClick(View view){
        score++;
        scoreText.setText("Score: " + score);
    }






    public void timer(){
        new CountDownTimer(11000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("Time:" +millisUntilFinished/1000 + " second");
            }

            @Override
            public void onFinish() {
                timeText.setText("Time expired.");
                handler.removeCallbacks(runnable) ;
                alert();

            }
        }.start();
    }


    public void alert(){
        AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Restart game?");
        alert.setMessage("Are you sure to restart game?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //restart
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this,MainScreen.class);
                startActivity(intent);
                finish();
            }
        });
        alert.show();
    }

    public void moveImage(){
        handler = new Handler();
        runnable = new Runnable(){
            @Override
            public void run() {
                Random random = new Random();
                int x = random.nextInt(800);
                int y = random.nextInt(1000);
                pikachu.setX(x);
                pikachu.setY(y);
                scoreText.setText("Score:" + Integer.toString(score));
                handler.postDelayed(this,500);
            }

        };
        handler.post(runnable);
    }
}


