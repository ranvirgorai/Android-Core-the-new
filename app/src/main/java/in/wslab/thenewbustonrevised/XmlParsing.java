package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by ranvir on 28/10/16.
 */
public class XmlParsing extends Activity implements View.OnClickListener {

    TextView WeatherInfo;
    Button btnGetData;
    EditText etCity, etState;

    String baseURL = "http://www.google.com/ig/api?weather=";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlparsing);

        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);

        btnGetData = (Button) findViewById(R.id.btnData);

        WeatherInfo = (TextView) findViewById(R.id.tvWeather);

    }

    @Override
    public void onClick(View v) {
        String c = etCity.getText().toString();
        String s = etState.getText().toString();

        StringBuilder URL=new StringBuilder(baseURL);
        URL.append(c+","+s);
        String fullUrl=URL.toString();

        try{

            java.net.URL website=new URL(fullUrl);
            SAXParserFactory spf=SAXParserFactory.newInstance();
            SAXParser sp=spf.newSAXParser();
            XMLReader xr=sp.getXMLReader();
            HandlingXmlParsing doingWork=new HandlingXmlParsing();
            xr.setContentHandler(doingWork);
            xr.parse(new InputSource(website.openStream()));
            String information = doingWork.getInfromation();
            WeatherInfo.setText(information);


        }catch (Exception e){
            WeatherInfo.setText("error:");
        }

    }
}
