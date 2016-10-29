package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by ranvir on 23/10/16.
 */
public class Splash extends Activity{
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
        setContentView(R.layout.splash);
        ourSong=MediaPlayer.create(this,R.raw.splash_sound);

        SharedPreferences getPrefs=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music=getPrefs.getBoolean("checkbox",true) ;
        if(music)
            ourSong.start();
        Thread timer=new Thread(){
          public void run(){
              try{
                sleep(0);
              }catch (InterruptedException e){
                    e.printStackTrace();
              }finally {
                  Intent mainActivity=new Intent("in.wslab.thenewbustonrevised.Menu");
                   startActivity(mainActivity);
              }
          }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
