package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;
import java.util.Random;

/**
 * Created by ranvir on 29/10/16.
 */
public class TextVoice extends Activity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    EditText et;
    Button bt;

    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;

    static final String[] texts = {
            "Who Are You Dear ", "I am Fine ",
            "what about u "
    };
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvoice);
        et = (EditText) findViewById(R.id.etTextToSpeech);
        bt = (Button) findViewById(R.id.btnTextToSpeech);
        bt.setOnClickListener(this);
        tts=new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    if(status!=TextToSpeech.ERROR){
                        tts.setLanguage(Locale.ENGLISH);
                    }
            }
        });
        sb=(SeekBar) findViewById(R.id.seekBar);
        mp=MediaPlayer.create(this,R.raw.splash_sound);
        mp.start();
        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxV=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(maxV);
        sb.setProgress(curV);
        sb.setOnSeekBarChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        String txt=et.getText().toString();
        Random r= new Random();
        String random=texts[r.nextInt(3)];
        tts.speak(txt,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
       mp.release();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
