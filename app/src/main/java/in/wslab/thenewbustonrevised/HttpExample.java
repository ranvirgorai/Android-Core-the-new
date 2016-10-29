package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by ranvir on 28/10/16.
 */
public class HttpExample extends Activity {
    TextView httpStuff;
    HttpClient client;
    JSONObject json;
    final static String URL="http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexample);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();

        new Read().execute("text");



    /*    GetMetodEx test= new GetMetodEx();
        try {
            String returened=test.getInternetData();
            httpStuff.setText(returened);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/
    }

    public JSONObject lastTweet(String usrname) throws ClientProtocolException,IOException,JSONException{
StringBuilder url=new StringBuilder(URL);
        //Makeing Url
        url.append(usrname);

        HttpGet get = new HttpGet(url.toString());
        HttpResponse r=client.execute(get);
        int status=r.getStatusLine().getStatusCode();
        if(status==200){
            HttpEntity e=r.getEntity();
            String data= EntityUtils.toString(e);
            JSONArray timeline=new JSONArray(data);
            JSONObject last=timeline.getJSONObject(0);
            return  last;
        }else {
            Toast.makeText(HttpExample.this,"Error",Toast.LENGTH_LONG).show();
            return null;
        }

    }

    public  class  Read extends AsyncTask<String, Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                json =lastTweet("mybringback");
                return json.getString(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            httpStuff.setText(s);
        }
    }
}
