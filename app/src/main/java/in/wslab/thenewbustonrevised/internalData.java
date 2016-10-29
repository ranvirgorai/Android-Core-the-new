package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ranvir on 24/10/16.
 */
public class internalData extends Activity implements View.OnClickListener {
    FileOutputStream fos;
    String FILENAME="InternalString";

    EditText shareData;
    TextView dataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferances);
        try {
            setupVariable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void setupVariable() throws IOException {
        Button save=(Button)findViewById(R.id.bSave);
        Button load=(Button)findViewById(R.id.bLoad);
         shareData=(EditText)findViewById(R.id.etSharedPrefs);
        dataResult=(TextView)findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSave:
            String data =shareData.getText().toString();
               /* File f=new File(FILENAME);
                try {
                    fos=new FileOutputStream(f);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }*/
                try {
                    fos=openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.bLoad:
                new loadSomeStuff().execute(FILENAME);
                break;

        }

    }

    public  class loadSomeStuff extends AsyncTask<String,Integer,String>{

        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog=new ProgressDialog(internalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            String collected=null;
            FileInputStream fis=null;
            for(int i=0;i<20;i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();

            try {
                fis=openFileInput(FILENAME);
                byte[] dataArray=new byte[fis.available()];
                while (fis.read(dataArray) !=-1){
                    collected=new String(dataArray);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            dataResult.setText(result);
        }
    }

}
