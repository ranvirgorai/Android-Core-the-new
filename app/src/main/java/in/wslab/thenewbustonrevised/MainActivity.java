package in.wslab.thenewbustonrevised;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int counter;
    Button add,sub,send;
    TextView display;
    EditText emailAddress,emailIntro;
    String emailAdd,EmailIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=0;
        add=(Button)findViewById(R.id.bADD);
        sub=(Button)findViewById(R.id.bSUB);
        send=(Button)findViewById(R.id.buttonSendMail);
        emailAddress=(EditText)findViewById(R.id.editTextEmailAddress);
        emailIntro=(EditText)findViewById(R.id.editTextEmailIntro);

        display=(TextView)findViewById(R.id.labelTxt);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.bADD:counter++;
               display.setText("Your Total No is= "+counter);
               break;
           case R.id.bSUB:counter--;
               display.setText("Your Total No is= "+counter);
               break;
           case R.id.buttonSendMail:counter++;

               emailAdd=emailAddress.getText().toString();
               EmailIntro=emailIntro.getText().toString();

               Intent emailIntent= new Intent(android.content.Intent.ACTION_SEND);
               emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAdd);
               emailIntent.putExtra(Intent.EXTRA_SUBJECT,"I LOVE YOU");
               emailIntent.setType("plain/text");
               emailIntent.putExtra(Intent.EXTRA_TEXT,EmailIntro);

               startActivity(emailIntent);
               break;
       }
    }
}
