//written by Masaki Nakamura

package com.slothius.chessplayer.androidcountdownpausable;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    TextView timerText;
    Button buttonStart, buttonPause;
    long aux;   //variable used in the  SetTheSameTimeAfterPause() method
    CounterClass timerCountDown;     //just the logic CountDown timer that will be shown in a TextView using the variable timerText.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView)findViewById(R.id.textView1);
        buttonStart= (Button) findViewById(R.id.button1);
        buttonPause = (Button) findViewById(R.id.button2);

        aux = 120000;
        timerCountDown = new CounterClass(aux, 1000);   // 1000ms = 1 sec. It means that the initial timer will begin from the variable aux (in this example
                                                           // aux value is 120000 = 2 min) and will countdown second by second. Set aux with the value u need.


//Start Button

buttonStart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        timerCountDown.start();
    }
});

    //Pause Button


buttonPause.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        timerCountDown.cancel();
        timerCountDown = new CounterClass(aux, 1000);   //sets the timer with the same time when I pushed on the button Pause
    }
});

    }



    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;

            //constructs the variable hms as Hours:Mins:Secs
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            timerText.setText(hms);   //sets the time as hh:mm:ss
            SetTheSameTimeAfterPause(millis);
        }

        @Override
        public void onFinish() {      //when the timer finishes
           timerText.setText("Time Up");

        }

        public void  SetTheSameTimeAfterPause(long set){
            aux = set;
        }
    }



}
