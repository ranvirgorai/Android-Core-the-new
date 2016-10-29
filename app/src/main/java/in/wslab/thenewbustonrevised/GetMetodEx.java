package in.wslab.thenewbustonrevised;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by ranvir on 28/10/16.
 */
public class GetMetodEx {


    public String getInternetData() throws Exception {

        BufferedReader in = null;
        String data = null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("https://www.google.co.in/?gfe_rd=cr&ei=OdESWKvvCK_T8gfAhoGgDQ");
            Log.i("Url",website.toString());
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");
            while ((l = in.readLine()) != null) {
                sb.append(l + nl);
            }
            in.close();
            data = sb.toString();

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }


}
