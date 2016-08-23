package com.example.user.navigationdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12/31/15.
 */
public class FirstFragment extends Fragment{

    View myView;
    TextView days,hours,min,seconds;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        days=(TextView) myView.findViewById(R.id.days);
        hours=(TextView)myView.findViewById(R.id.hours);
        min=(TextView)myView.findViewById(R.id.minutes);
        seconds=(TextView)myView.findViewById(R.id.seconds);

        long timeInMilliseconds=0,currenttime=0,time=0;

        String givenDateString = "Mon Sep 25 09:00:00 GMT+05:30 2016";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
            currenttime = (System.currentTimeMillis());
            time=timeInMilliseconds-currenttime;
        } catch (ParseException e) {
            e.printStackTrace();
        }



        final CounterClass timer=new CounterClass(time, 1000);
        timer.start();

        return myView;
    }

    public class CounterClass extends CountDownTimer {
        Animation animSlideDown,animBounce;

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            animSlideDown = AnimationUtils.loadAnimation(getContext(),
                    R.anim.slid);
            animBounce=AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onTick(long millisUntilFinished) {


            long millis = millisUntilFinished;
            long Days = TimeUnit.MILLISECONDS.toDays(millis);
            millis -= TimeUnit.DAYS.toMillis(Days);
            long Hours = TimeUnit.MILLISECONDS.toHours(millis);
            millis -= TimeUnit.HOURS.toMillis(Hours);
            long Minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
            millis -= TimeUnit.MINUTES.toMillis(Minutes);

            long Seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

            days.setText(String.format("%02d",Days));
            hours.setText(String.format("%02d",Hours)+":");
            min.setText(String.format("%02d",Minutes)+":");
            if(Seconds==0)
            {
                //seconds.startAnimation(animSlideDown);
                min.startAnimation(animBounce);
            }
            if(Minutes==0)
                hours.startAnimation(animBounce);


            seconds.setText(String.format("%02d",Seconds));

        }

        @Override
        public void onFinish() {

        }
    }
}
