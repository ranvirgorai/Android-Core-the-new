package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by ranvir on 23/10/16.
 */
public class GFX extends Activity {

    MyBringBack ourView;

    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //wake Lock
        PowerManager pM=(PowerManager)getSystemService(Context.POWER_SERVICE);
        super.onCreate(savedInstanceState);
        ourView=new MyBringBack(this);
        setContentView(ourView);
    }
}
