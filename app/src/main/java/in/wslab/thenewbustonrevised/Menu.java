package in.wslab.thenewbustonrevised;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ranvir on 23/10/16.
 */
public class Menu extends ListActivity {

    String classes[] = {"StatusBarNotifiaction","TextVoice","Voice","GLExample","HttpExample","AlertDialogs","Accelerate","SQLliteExample","internalData","GFXSurface","GFX","Menu","MainActivity","Camera","ExternalData"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this,
                android.R.layout.simple_list_item_1, classes));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String classPosition = classes[position];
        try {
            @SuppressWarnings("rawtypes")
            Class ourClass = Class.forName("in.wslab.thenewbustonrevised." + classPosition);
            Intent myintent = new Intent(Menu.this, ourClass);
            startActivity(myintent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.aboutus:
                Intent i = new Intent("in.wslab.thenewbustonrevised.ABOUT");
                startActivity(i);
                break;

            case R.id.prefrances:
                Intent p = new Intent("in.wslab.thenewbustonrevised.PREFS");
                startActivity(p);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }

}
