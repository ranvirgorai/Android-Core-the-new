package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

/**
 * Created by ranvir on 29/10/16.
 */
public class StatusBarNotifiaction extends Activity implements View.OnClickListener {

    NotificationManager nm;
    static  final  int uniqueID=123456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbarnotification);
        Button btns=(Button)findViewById(R.id.btnStatus);
        btns.setOnClickListener(this);
        nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,StatusBarNotifiaction.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);

        String body="This is a mesasge from Triuavas,thanks for ypur support";

        String title="Ranvir's App";


        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            Notification n = new Notification(R.drawable.greenball, body, System.currentTimeMillis());
           // n.setLatestEventInfo(this, title, body, pi);
            n.defaults=Notification.DEFAULT_ALL;
            nm.notify(uniqueID,n);
        }



    }
}
