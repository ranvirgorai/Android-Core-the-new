package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by ranvir on 29/10/16.
 */
public class Voice extends Activity implements View.OnClickListener {

    ListView lv;
    Button b;
    static final int check=1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);
        lv=(ListView)findViewById(R.id.lvVoiceReco);
        b=(Button)findViewById(R.id.btnVoiceReco);
        b.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak up !");
        startActivityForResult(i,check);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==check && resultCode==RESULT_OK){

        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
