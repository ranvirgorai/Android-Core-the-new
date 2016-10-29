package in.wslab.thenewbustonrevised;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by ranvir on 23/10/16.
 */
public class Prefs extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
