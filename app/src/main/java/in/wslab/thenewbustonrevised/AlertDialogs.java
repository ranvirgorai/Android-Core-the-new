package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ranvir on 27/10/16.
 */
public class AlertDialogs extends Activity implements View.OnClickListener{

    Button btnAlertMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdialogs);
        btnAlertMe=(Button)findViewById(R.id.btnOpenAlert);
        btnAlertMe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenAlert:
                AlertDialog alert=new AlertDialog.Builder(AlertDialogs.this).create();
                alert.setTitle("This Is an ALert");
                alert.setMessage("Please Select Any Option");
                alert.setButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.setButton2("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.setButton3("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.show();
                break;
        }
    }
}
