package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ranvir on 25/10/16.
 */
public class SQLView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv=(TextView)findViewById(R.id.tvSqlInfo);
        Scale info =new Scale(this);
        info.open();
        String data=info.getData();
        info.close();
        tv.setText(data);
    }
}
