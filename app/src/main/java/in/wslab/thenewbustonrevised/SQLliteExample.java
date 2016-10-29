package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ranvir on 25/10/16.
 */
public class SQLliteExample extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlGetInfo, sqlEdit, sqlDelete;
    EditText sqlName, sqlScale, sqlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        sqlUpdate = (Button) findViewById(R.id.btnSql);
        sqlView = (Button) findViewById(R.id.btnView);
        sqlGetInfo = (Button) findViewById(R.id.btnGetInfo);
        sqlEdit = (Button) findViewById(R.id.btnEditEntery);
        sqlDelete = (Button) findViewById(R.id.btnDelete);

        sqlName = (EditText) findViewById(R.id.etName);
        sqlScale = (EditText) findViewById(R.id.etScale);
        sqlId = (EditText) findViewById(R.id.etID);

        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlEdit.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSql:
                boolean didItWork = true;

                try {
                    String name = sqlName.getText().toString();
                    String scale = sqlScale.getText().toString();

                    Scale entery = new Scale(SQLliteExample.this);
                    entery.open();
                    entery.createEntery(name, scale);
                    entery.close();
                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Wrong !");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("DATA BASE");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.btnView:
                try {
                    Intent intn = new Intent("in.wslab.thenewbustonrevised.SQLVIEW");
                    startActivity(intn);
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Wrong !");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.btnGetInfo:
                try {
                    String s = sqlId.getText().toString();
                    long l = Long.parseLong(s);
                    Scale sc = new Scale(this);
                    sc.open();
                    String returnedName = sc.getName(l);
                    String reurnedScale = sc.getScale(l);
                    sc.close();

                    sqlName.setText(returnedName);
                    sqlScale.setText(reurnedScale);

                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Wrong !");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }

                break;
            case R.id.btnEditEntery:
                try {
                    String mName = sqlName.getText().toString();
                    String mScale = sqlScale.getText().toString();
                    String st = sqlId.getText().toString();
                    long mRow = Long.parseLong(st);

                    Scale ex = new Scale(this);
                    ex.open();
                    ex.updateEntery(mRow, mName, mScale);

                    ex.close();
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Wrong !");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.btnDelete:
                try {
                    String str = sqlId.getText().toString();
                    long lRow = Long.parseLong(str);

                    Scale exe = new Scale(this);
                    exe.open();
                    exe.deleteEntery(lRow);

                    exe.close();

                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Wrong !");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }

    }
}
